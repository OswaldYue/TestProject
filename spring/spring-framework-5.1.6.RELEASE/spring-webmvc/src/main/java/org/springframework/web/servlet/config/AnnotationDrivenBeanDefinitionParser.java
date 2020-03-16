/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.servlet.config;

import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import org.w3c.dom.Element;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.parsing.CompositeComponentDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.ResourceRegionHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.feed.AtomFeedHttpMessageConverter;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.smile.MappingJackson2SmileHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.xml.DomUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.method.support.CompositeUriComponentsContributor;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.ConversionServiceExposingInterceptor;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewRequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

/**
 * A {@link BeanDefinitionParser} that provides the configuration for the
 * {@code <annotation-driven/>} MVC namespace element.
 *
 * <p>This class registers the following {@link HandlerMapping HandlerMappings}:</p>
 * <ul>
 * <li>{@link RequestMappingHandlerMapping}
 * ordered at 0 for mapping requests to annotated controller methods.
 * <li>{@link BeanNameUrlHandlerMapping}
 * ordered at 2 to map URL paths to controller bean names.
 * </ul>
 *
 * <p><strong>Note:</strong> Additional HandlerMappings may be registered
 * as a result of using the {@code <view-controller>} or the
 * {@code <resources>} MVC namespace elements.
 *
 * <p>This class registers the following {@link HandlerAdapter HandlerAdapters}:
 * <ul>
 * <li>{@link RequestMappingHandlerAdapter}
 * for processing requests with annotated controller methods.
 * <li>{@link HttpRequestHandlerAdapter}
 * for processing requests with {@link HttpRequestHandler HttpRequestHandlers}.
 * <li>{@link SimpleControllerHandlerAdapter}
 * for processing requests with interface-based {@link Controller Controllers}.
 * </ul>
 *
 * <p>This class registers the following {@link HandlerExceptionResolver HandlerExceptionResolvers}:
 * <ul>
 * <li>{@link ExceptionHandlerExceptionResolver} for handling exceptions through
 * {@link org.springframework.web.bind.annotation.ExceptionHandler} methods.
 * <li>{@link ResponseStatusExceptionResolver} for exceptions annotated
 * with {@link org.springframework.web.bind.annotation.ResponseStatus}.
 * <li>{@link DefaultHandlerExceptionResolver} for resolving known Spring
 * exception types
 * </ul>
 *
 * <p>This class registers an {@link org.springframework.util.AntPathMatcher}
 * and a {@link org.springframework.web.util.UrlPathHelper} to be used by:
 * <ul>
 * <li>the {@link RequestMappingHandlerMapping},
 * <li>the {@link HandlerMapping} for ViewControllers
 * <li>and the {@link HandlerMapping} for serving resources
 * </ul>
 * Note that those beans can be configured by using the {@code path-matching}
 * MVC namespace element.
 *
 * <p>Both the {@link RequestMappingHandlerAdapter} and the
 * {@link ExceptionHandlerExceptionResolver} are configured with instances of
 * the following by default:
 * <ul>
 * <li>A {@link ContentNegotiationManager}
 * <li>A {@link DefaultFormattingConversionService}
 * <li>A {@link org.springframework.validation.beanvalidation.LocalValidatorFactoryBean}
 * if a JSR-303 implementation is available on the classpath
 * <li>A range of {@link HttpMessageConverter HttpMessageConverters} depending on which third-party
 * libraries are available on the classpath.
 * </ul>
 *
 * @author Keith Donald
 * @author Juergen Hoeller
 * @author Arjen Poutsma
 * @author Rossen Stoyanchev
 * @author Brian Clozel
 * @author Agim Emruli
 * @since 3.0
 */
class AnnotationDrivenBeanDefinitionParser implements BeanDefinitionParser {

	public static final String HANDLER_MAPPING_BEAN_NAME = RequestMappingHandlerMapping.class.getName();

	public static final String HANDLER_ADAPTER_BEAN_NAME = RequestMappingHandlerAdapter.class.getName();

	public static final String CONTENT_NEGOTIATION_MANAGER_BEAN_NAME = "mvcContentNegotiationManager";


	private static final boolean javaxValidationPresent;

	private static boolean romePresent;

	private static final boolean jaxb2Present;

	private static final boolean jackson2Present;

	private static final boolean jackson2XmlPresent;

	private static final boolean jackson2SmilePresent;

	private static final boolean jackson2CborPresent;

	private static final boolean gsonPresent;

	static {
		ClassLoader classLoader = AnnotationDrivenBeanDefinitionParser.class.getClassLoader();
		javaxValidationPresent = ClassUtils.isPresent("javax.validation.Validator", classLoader);
		romePresent = ClassUtils.isPresent("com.rometools.rome.feed.WireFeed", classLoader);
		jaxb2Present = ClassUtils.isPresent("javax.xml.bind.Binder", classLoader);
		jackson2Present = ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", classLoader) &&
						ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", classLoader);
		jackson2XmlPresent = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.xml.XmlMapper", classLoader);
		jackson2SmilePresent = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.smile.SmileFactory", classLoader);
		jackson2CborPresent = ClassUtils.isPresent("com.fasterxml.jackson.dataformat.cbor.CBORFactory", classLoader);
		gsonPresent = ClassUtils.isPresent("com.google.gson.Gson", classLoader);
	}


	/**
	 * 解析 mvc:annotation-driven 标签
	 * 如果<mvc:annotation-driven>没有配置任何子标签的话，Spring会如何处理呢？
	 * 1.RootBeanDefinition handlerMappingDef = new RootBeanDefinition(RequestMappingHandlerMapping.class);
	 * 2.RootBeanDefinition bindingDef = new RootBeanDefinition(ConfigurableWebBindingInitializer.class);
	 * 3.RootBeanDefinition handlerAdapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
	 * 4.RootBeanDefinition uriContributorDef = new RootBeanDefinition(CompositeUriComponentsContributorFactoryBean.class);
	 * 5.RootBeanDefinition csInterceptorDef = new RootBeanDefinition(ConversionServiceExposingInterceptor.class);
	 * 6.RootBeanDefinition mappedInterceptorDef = new RootBeanDefinition(MappedInterceptor.class);
	 * 7.RootBeanDefinition methodExceptionResolver = new RootBeanDefinition(ExceptionHandlerExceptionResolver.class);
	 * 8.RootBeanDefinition statusExceptionResolver = new RootBeanDefinition(ResponseStatusExceptionResolver.class);
	 * 9.RootBeanDefinition defaultExceptionResolver = new RootBeanDefinition(DefaultHandlerExceptionResolver.class);
	 */
	@Override
	@Nullable
	public BeanDefinition parse(Element element, ParserContext context) {
		Object source = context.extractSource(element);
		XmlReaderContext readerContext = context.getReaderContext();

		CompositeComponentDefinition compDefinition = new CompositeComponentDefinition(element.getTagName(), source);
		context.pushContainingComponent(compDefinition);

		/**
		 * 获取协商内容视图配置
		 */
		RuntimeBeanReference contentNegotiationManager = getContentNegotiationManager(element, source, context);

		/**
		 * 创建RequestMappingHandlerMapping的RootBeanDefinition
		 * 从这里也可以看出，开启mvc:annotation-driven标签后，
		 * 将会默认注册RequestMappingHandlerMapping作为默认的HandlerMapping
		 */
		RootBeanDefinition handlerMappingDef = new RootBeanDefinition(RequestMappingHandlerMapping.class);
		handlerMappingDef.setSource(source);
		handlerMappingDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		handlerMappingDef.getPropertyValues().add("order", 0);
		handlerMappingDef.getPropertyValues().add("contentNegotiationManager", contentNegotiationManager);

		// 是否开启矩阵变量
		if (element.hasAttribute("enable-matrix-variables")) {
			Boolean enableMatrixVariables = Boolean.valueOf(element.getAttribute("enable-matrix-variables"));
			handlerMappingDef.getPropertyValues().add("removeSemicolonContent", !enableMatrixVariables);
		}

		// 解析path-matching路径匹配标签
		configurePathMatchingProperties(handlerMappingDef, element, context);
		readerContext.getRegistry().registerBeanDefinition(HANDLER_MAPPING_BEAN_NAME, handlerMappingDef);

		// 解析cors跨域标签
		RuntimeBeanReference corsRef = MvcNamespaceUtils.registerCorsConfigurations(null, context, source);
		handlerMappingDef.getPropertyValues().add("corsConfigurations", corsRef);

		// 解析conversion-service数据转换、格式化标签
		RuntimeBeanReference conversionService = getConversionService(element, source, context);
		// 解析validator标签
		RuntimeBeanReference validator = getValidator(element, source, context);
		// 解析message-codes-resolver标签
		RuntimeBeanReference messageCodesResolver = getMessageCodesResolver(element);

		/**
		 * 创建ConfigurableWebBindingInitializer的RootBeanDefinition对象
		 * 并将上一步解析的conversionService、validator、messageCodesResolver
		 * 作为属性注入到该对象中
		 */
		RootBeanDefinition bindingDef = new RootBeanDefinition(ConfigurableWebBindingInitializer.class);
		bindingDef.setSource(source);
		bindingDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		bindingDef.getPropertyValues().add("conversionService", conversionService);
		bindingDef.getPropertyValues().add("validator", validator);
		bindingDef.getPropertyValues().add("messageCodesResolver", messageCodesResolver);

		// 解析message-converters标签
		ManagedList<?> messageConverters = getMessageConverters(element, source, context);
		// 解析argument-resolvers标签
		ManagedList<?> argumentResolvers = getArgumentResolvers(element, context);
		// 解析return-value-handlers标签
		ManagedList<?> returnValueHandlers = getReturnValueHandlers(element, context);
		// 解析async-support标签
		String asyncTimeout = getAsyncTimeout(element);
		// 解析async-support的task-executor子标签
		RuntimeBeanReference asyncExecutor = getAsyncExecutor(element);
		// 解析async-support的callable-interceptors子标签
		ManagedList<?> callableInterceptors = getCallableInterceptors(element, source, context);
		// 解析async-support的deferred-result-interceptors子标签
		ManagedList<?> deferredResultInterceptors = getDeferredResultInterceptors(element, source, context);

		/**
		 * 创建RequestMappingHandlerAdapter的RootBeanDefinition
		 * 从这里也可以看出，开启mvc:annotation-driven标签后，
		 * 将会默认注册RequestMappingHandlerAdapter作为默认的HandlerAdapter
		 * 并将上面解析的内容绑定到该HandlerAdapter中
		 */
		RootBeanDefinition handlerAdapterDef = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
		handlerAdapterDef.setSource(source);
		handlerAdapterDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		handlerAdapterDef.getPropertyValues().add("contentNegotiationManager", contentNegotiationManager);
		handlerAdapterDef.getPropertyValues().add("webBindingInitializer", bindingDef);
		handlerAdapterDef.getPropertyValues().add("messageConverters", messageConverters);
		addRequestBodyAdvice(handlerAdapterDef);
		addResponseBodyAdvice(handlerAdapterDef);

		if (element.hasAttribute("ignore-default-model-on-redirect")) {
			Boolean ignoreDefaultModel = Boolean.valueOf(element.getAttribute("ignore-default-model-on-redirect"));
			handlerAdapterDef.getPropertyValues().add("ignoreDefaultModelOnRedirect", ignoreDefaultModel);
		}
		if (argumentResolvers != null) {
			handlerAdapterDef.getPropertyValues().add("customArgumentResolvers", argumentResolvers);
		}
		if (returnValueHandlers != null) {
			handlerAdapterDef.getPropertyValues().add("customReturnValueHandlers", returnValueHandlers);
		}
		if (asyncTimeout != null) {
			handlerAdapterDef.getPropertyValues().add("asyncRequestTimeout", asyncTimeout);
		}
		if (asyncExecutor != null) {
			handlerAdapterDef.getPropertyValues().add("taskExecutor", asyncExecutor);
		}

		handlerAdapterDef.getPropertyValues().add("callableInterceptors", callableInterceptors);
		handlerAdapterDef.getPropertyValues().add("deferredResultInterceptors", deferredResultInterceptors);
		readerContext.getRegistry().registerBeanDefinition(HANDLER_ADAPTER_BEAN_NAME, handlerAdapterDef);

		/**
		 * 创建CompositeUriComponentsContributorFactoryBean的RootBeanDefinition
		 * CompositeUriComponentsContributorFactoryBean是一个工厂bean，
		 * 可以用来获取RequestMappingHandlerAdapter中的HandlerMethodArgumentResolver配置
		 */
		RootBeanDefinition uriContributorDef =
				new RootBeanDefinition(CompositeUriComponentsContributorFactoryBean.class);
		uriContributorDef.setSource(source);
		uriContributorDef.getPropertyValues().addPropertyValue("handlerAdapter", handlerAdapterDef);
		uriContributorDef.getPropertyValues().addPropertyValue("conversionService", conversionService);
		String uriContributorName = MvcUriComponentsBuilder.MVC_URI_COMPONENTS_CONTRIBUTOR_BEAN_NAME;
		readerContext.getRegistry().registerBeanDefinition(uriContributorName, uriContributorDef);

		/**
		 * 创建ConversionServiceExposingInterceptor的RootBeanDefinition
		 * 主要用来解析spring:eval标签
		 */
		RootBeanDefinition csInterceptorDef = new RootBeanDefinition(ConversionServiceExposingInterceptor.class);
		csInterceptorDef.setSource(source);
		csInterceptorDef.getConstructorArgumentValues().addIndexedArgumentValue(0, conversionService);
		RootBeanDefinition mappedInterceptorDef = new RootBeanDefinition(MappedInterceptor.class);
		mappedInterceptorDef.setSource(source);
		mappedInterceptorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		mappedInterceptorDef.getConstructorArgumentValues().addIndexedArgumentValue(0, (Object) null);
		mappedInterceptorDef.getConstructorArgumentValues().addIndexedArgumentValue(1, csInterceptorDef);
		String mappedInterceptorName = readerContext.registerWithGeneratedName(mappedInterceptorDef);

		/**
		 * 创建ExceptionHandlerExceptionResolver的RootBeanDefinition
		 */
		RootBeanDefinition methodExceptionResolver = new RootBeanDefinition(ExceptionHandlerExceptionResolver.class);
		methodExceptionResolver.setSource(source);
		methodExceptionResolver.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		methodExceptionResolver.getPropertyValues().add("contentNegotiationManager", contentNegotiationManager);
		methodExceptionResolver.getPropertyValues().add("messageConverters", messageConverters);
		methodExceptionResolver.getPropertyValues().add("order", 0);
		addResponseBodyAdvice(methodExceptionResolver);
		if (argumentResolvers != null) {
			methodExceptionResolver.getPropertyValues().add("customArgumentResolvers", argumentResolvers);
		}
		if (returnValueHandlers != null) {
			methodExceptionResolver.getPropertyValues().add("customReturnValueHandlers", returnValueHandlers);
		}
		String methodExResolverName = readerContext.registerWithGeneratedName(methodExceptionResolver);

		/**
		 * 创建ResponseStatusExceptionResolver的RootBeanDefinition
		 *
		 */
		RootBeanDefinition statusExceptionResolver = new RootBeanDefinition(ResponseStatusExceptionResolver.class);
		statusExceptionResolver.setSource(source);
		statusExceptionResolver.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		statusExceptionResolver.getPropertyValues().add("order", 1);
		String statusExResolverName = readerContext.registerWithGeneratedName(statusExceptionResolver);

		/**
		 * 创建DefaultHandlerExceptionResolver的RootBeanDefinition
		 * 该类是HandlerExceptionResolver的默认实现，可以解析http异常并将相应的http状态码返回
		 * 例如：404
		 */
		RootBeanDefinition defaultExceptionResolver = new RootBeanDefinition(DefaultHandlerExceptionResolver.class);
		defaultExceptionResolver.setSource(source);
		defaultExceptionResolver.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		defaultExceptionResolver.getPropertyValues().add("order", 2);
		String defaultExResolverName = readerContext.registerWithGeneratedName(defaultExceptionResolver);

		/**
		 * 将上面创建的RootBeanDefinition以组件形式纳入SpringIOC容器
		 */
		context.registerComponent(new BeanComponentDefinition(handlerMappingDef, HANDLER_MAPPING_BEAN_NAME));
		context.registerComponent(new BeanComponentDefinition(handlerAdapterDef, HANDLER_ADAPTER_BEAN_NAME));
		context.registerComponent(new BeanComponentDefinition(uriContributorDef, uriContributorName));
		context.registerComponent(new BeanComponentDefinition(mappedInterceptorDef, mappedInterceptorName));
		context.registerComponent(new BeanComponentDefinition(methodExceptionResolver, methodExResolverName));
		context.registerComponent(new BeanComponentDefinition(statusExceptionResolver, statusExResolverName));
		context.registerComponent(new BeanComponentDefinition(defaultExceptionResolver, defaultExResolverName));

		// 注册默认组件
		// Ensure BeanNameUrlHandlerMapping (SPR-8289) and default HandlerAdapters are not "turned off"
		MvcNamespaceUtils.registerDefaultComponents(context, source);

		context.popAndRegisterContainingComponent();

		return null;
	}

	protected void addRequestBodyAdvice(RootBeanDefinition beanDef) {
		if (jackson2Present) {
			beanDef.getPropertyValues().add("requestBodyAdvice",
					new RootBeanDefinition(JsonViewRequestBodyAdvice.class));
		}
	}

	protected void addResponseBodyAdvice(RootBeanDefinition beanDef) {
		if (jackson2Present) {
			beanDef.getPropertyValues().add("responseBodyAdvice",
					new RootBeanDefinition(JsonViewResponseBodyAdvice.class));
		}
	}

	private RuntimeBeanReference getConversionService(Element element, @Nullable Object source, ParserContext context) {
		RuntimeBeanReference conversionServiceRef;
		if (element.hasAttribute("conversion-service")) {
			conversionServiceRef = new RuntimeBeanReference(element.getAttribute("conversion-service"));
		}
		else {
			RootBeanDefinition conversionDef = new RootBeanDefinition(FormattingConversionServiceFactoryBean.class);
			conversionDef.setSource(source);
			conversionDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			String conversionName = context.getReaderContext().registerWithGeneratedName(conversionDef);
			context.registerComponent(new BeanComponentDefinition(conversionDef, conversionName));
			conversionServiceRef = new RuntimeBeanReference(conversionName);
		}
		return conversionServiceRef;
	}

	@Nullable
	private RuntimeBeanReference getValidator(Element element, @Nullable Object source, ParserContext context) {
		if (element.hasAttribute("validator")) {
			return new RuntimeBeanReference(element.getAttribute("validator"));
		}
		else if (javaxValidationPresent) {
			RootBeanDefinition validatorDef = new RootBeanDefinition(
					"org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean");
			validatorDef.setSource(source);
			validatorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			String validatorName = context.getReaderContext().registerWithGeneratedName(validatorDef);
			context.registerComponent(new BeanComponentDefinition(validatorDef, validatorName));
			return new RuntimeBeanReference(validatorName);
		}
		else {
			return null;
		}
	}

	/**
	 * 如果我们自定义了content-negotiation-manager标签，那么则使用自定义的视图协商；否则创建一个默认的视图协商
	 * */
	private RuntimeBeanReference getContentNegotiationManager(
			Element element, @Nullable Object source, ParserContext context) {

		RuntimeBeanReference beanRef;
		if (element.hasAttribute("content-negotiation-manager")) {
			String name = element.getAttribute("content-negotiation-manager");
			beanRef = new RuntimeBeanReference(name);
		}
		else {
			RootBeanDefinition factoryBeanDef = new RootBeanDefinition(ContentNegotiationManagerFactoryBean.class);
			factoryBeanDef.setSource(source);
			factoryBeanDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
			factoryBeanDef.getPropertyValues().add("mediaTypes", getDefaultMediaTypes());
			String name = CONTENT_NEGOTIATION_MANAGER_BEAN_NAME;
			context.getReaderContext().getRegistry().registerBeanDefinition(name, factoryBeanDef);
			context.registerComponent(new BeanComponentDefinition(factoryBeanDef, name));
			beanRef = new RuntimeBeanReference(name);
		}
		return beanRef;
	}

	private void configurePathMatchingProperties(
			RootBeanDefinition handlerMappingDef, Element element, ParserContext context) {

		Element pathMatchingElement = DomUtils.getChildElementByTagName(element, "path-matching");
		if (pathMatchingElement != null) {
			Object source = context.extractSource(element);

			if (pathMatchingElement.hasAttribute("suffix-pattern")) {
				Boolean useSuffixPatternMatch = Boolean.valueOf(pathMatchingElement.getAttribute("suffix-pattern"));
				handlerMappingDef.getPropertyValues().add("useSuffixPatternMatch", useSuffixPatternMatch);
			}
			if (pathMatchingElement.hasAttribute("trailing-slash")) {
				Boolean useTrailingSlashMatch = Boolean.valueOf(pathMatchingElement.getAttribute("trailing-slash"));
				handlerMappingDef.getPropertyValues().add("useTrailingSlashMatch", useTrailingSlashMatch);
			}
			if (pathMatchingElement.hasAttribute("registered-suffixes-only")) {
				Boolean useRegisteredSuffixPatternMatch = Boolean.valueOf(pathMatchingElement.getAttribute("registered-suffixes-only"));
				handlerMappingDef.getPropertyValues().add("useRegisteredSuffixPatternMatch", useRegisteredSuffixPatternMatch);
			}

			RuntimeBeanReference pathHelperRef = null;
			if (pathMatchingElement.hasAttribute("path-helper")) {
				pathHelperRef = new RuntimeBeanReference(pathMatchingElement.getAttribute("path-helper"));
			}
			pathHelperRef = MvcNamespaceUtils.registerUrlPathHelper(pathHelperRef, context, source);
			handlerMappingDef.getPropertyValues().add("urlPathHelper", pathHelperRef);

			RuntimeBeanReference pathMatcherRef = null;
			if (pathMatchingElement.hasAttribute("path-matcher")) {
				pathMatcherRef = new RuntimeBeanReference(pathMatchingElement.getAttribute("path-matcher"));
			}
			pathMatcherRef = MvcNamespaceUtils.registerPathMatcher(pathMatcherRef, context, source);
			handlerMappingDef.getPropertyValues().add("pathMatcher", pathMatcherRef);
		}
	}

	private Properties getDefaultMediaTypes() {
		Properties defaultMediaTypes = new Properties();
		if (romePresent) {
			defaultMediaTypes.put("atom", MediaType.APPLICATION_ATOM_XML_VALUE);
			defaultMediaTypes.put("rss", MediaType.APPLICATION_RSS_XML_VALUE);
		}
		if (jaxb2Present || jackson2XmlPresent) {
			defaultMediaTypes.put("xml", MediaType.APPLICATION_XML_VALUE);
		}
		if (jackson2Present || gsonPresent) {
			defaultMediaTypes.put("json", MediaType.APPLICATION_JSON_VALUE);
		}
		if (jackson2SmilePresent) {
			defaultMediaTypes.put("smile", "application/x-jackson-smile");
		}
		if (jackson2CborPresent) {
			defaultMediaTypes.put("cbor", "application/cbor");
		}
		return defaultMediaTypes;
	}

	@Nullable
	private RuntimeBeanReference getMessageCodesResolver(Element element) {
		if (element.hasAttribute("message-codes-resolver")) {
			return new RuntimeBeanReference(element.getAttribute("message-codes-resolver"));
		}
		else {
			return null;
		}
	}

	@Nullable
	private String getAsyncTimeout(Element element) {
		Element asyncElement = DomUtils.getChildElementByTagName(element, "async-support");
		return (asyncElement != null ? asyncElement.getAttribute("default-timeout") : null);
	}

	@Nullable
	private RuntimeBeanReference getAsyncExecutor(Element element) {
		Element asyncElement = DomUtils.getChildElementByTagName(element, "async-support");
		if (asyncElement != null && asyncElement.hasAttribute("task-executor")) {
			return new RuntimeBeanReference(asyncElement.getAttribute("task-executor"));
		}
		return null;
	}

	private ManagedList<?> getCallableInterceptors(
			Element element, @Nullable Object source, ParserContext context) {

		ManagedList<Object> interceptors = new ManagedList<>();
		Element asyncElement = DomUtils.getChildElementByTagName(element, "async-support");
		if (asyncElement != null) {
			Element interceptorsElement = DomUtils.getChildElementByTagName(asyncElement, "callable-interceptors");
			if (interceptorsElement != null) {
				interceptors.setSource(source);
				for (Element converter : DomUtils.getChildElementsByTagName(interceptorsElement, "bean")) {
					BeanDefinitionHolder beanDef = context.getDelegate().parseBeanDefinitionElement(converter);
					if (beanDef != null) {
						beanDef = context.getDelegate().decorateBeanDefinitionIfRequired(converter, beanDef);
						interceptors.add(beanDef);
					}
				}
			}
		}
		return interceptors;
	}

	private ManagedList<?> getDeferredResultInterceptors(
			Element element, @Nullable Object source, ParserContext context) {

		ManagedList<Object> interceptors = new ManagedList<>();
		Element asyncElement = DomUtils.getChildElementByTagName(element, "async-support");
		if (asyncElement != null) {
			Element interceptorsElement = DomUtils.getChildElementByTagName(asyncElement, "deferred-result-interceptors");
			if (interceptorsElement != null) {
				interceptors.setSource(source);
				for (Element converter : DomUtils.getChildElementsByTagName(interceptorsElement, "bean")) {
					BeanDefinitionHolder beanDef = context.getDelegate().parseBeanDefinitionElement(converter);
					if (beanDef != null) {
						beanDef = context.getDelegate().decorateBeanDefinitionIfRequired(converter, beanDef);
						interceptors.add(beanDef);
					}
				}
			}
		}
		return interceptors;
	}

	@Nullable
	private ManagedList<?> getArgumentResolvers(Element element, ParserContext context) {
		Element resolversElement = DomUtils.getChildElementByTagName(element, "argument-resolvers");
		if (resolversElement != null) {
			ManagedList<Object> resolvers = extractBeanSubElements(resolversElement, context);
			return wrapLegacyResolvers(resolvers, context);
		}
		return null;
	}

	private ManagedList<Object> wrapLegacyResolvers(List<Object> list, ParserContext context) {
		ManagedList<Object> result = new ManagedList<>();
		for (Object object : list) {
			if (object instanceof BeanDefinitionHolder) {
				BeanDefinitionHolder beanDef = (BeanDefinitionHolder) object;
				String className = beanDef.getBeanDefinition().getBeanClassName();
				Assert.notNull(className, "No resolver class");
				Class<?> clazz = ClassUtils.resolveClassName(className, context.getReaderContext().getBeanClassLoader());
				if (WebArgumentResolver.class.isAssignableFrom(clazz)) {
					RootBeanDefinition adapter = new RootBeanDefinition(ServletWebArgumentResolverAdapter.class);
					adapter.getConstructorArgumentValues().addIndexedArgumentValue(0, beanDef);
					result.add(new BeanDefinitionHolder(adapter, beanDef.getBeanName() + "Adapter"));
					continue;
				}
			}
			result.add(object);
		}
		return result;
	}

	@Nullable
	private ManagedList<?> getReturnValueHandlers(Element element, ParserContext context) {
		Element handlers = DomUtils.getChildElementByTagName(element, "return-value-handlers");
		return (handlers != null ? extractBeanSubElements(handlers, context) : null);
	}

	private ManagedList<?> getMessageConverters(Element element, @Nullable Object source, ParserContext context) {
		Element convertersElement = DomUtils.getChildElementByTagName(element, "message-converters");
		ManagedList<Object> messageConverters = new ManagedList<>();
		if (convertersElement != null) {
			messageConverters.setSource(source);
			for (Element beanElement : DomUtils.getChildElementsByTagName(convertersElement, "bean", "ref")) {
				Object object = context.getDelegate().parsePropertySubElement(beanElement, null);
				messageConverters.add(object);
			}
		}

		if (convertersElement == null || Boolean.valueOf(convertersElement.getAttribute("register-defaults"))) {
			messageConverters.setSource(source);
			messageConverters.add(createConverterDefinition(ByteArrayHttpMessageConverter.class, source));

			RootBeanDefinition stringConverterDef = createConverterDefinition(StringHttpMessageConverter.class, source);
			stringConverterDef.getPropertyValues().add("writeAcceptCharset", false);
			messageConverters.add(stringConverterDef);

			messageConverters.add(createConverterDefinition(ResourceHttpMessageConverter.class, source));
			messageConverters.add(createConverterDefinition(ResourceRegionHttpMessageConverter.class, source));
			messageConverters.add(createConverterDefinition(SourceHttpMessageConverter.class, source));
			messageConverters.add(createConverterDefinition(AllEncompassingFormHttpMessageConverter.class, source));

			if (romePresent) {
				messageConverters.add(createConverterDefinition(AtomFeedHttpMessageConverter.class, source));
				messageConverters.add(createConverterDefinition(RssChannelHttpMessageConverter.class, source));
			}

			if (jackson2XmlPresent) {
				Class<?> type = MappingJackson2XmlHttpMessageConverter.class;
				RootBeanDefinition jacksonConverterDef = createConverterDefinition(type, source);
				GenericBeanDefinition jacksonFactoryDef = createObjectMapperFactoryDefinition(source);
				jacksonFactoryDef.getPropertyValues().add("createXmlMapper", true);
				jacksonConverterDef.getConstructorArgumentValues().addIndexedArgumentValue(0, jacksonFactoryDef);
				messageConverters.add(jacksonConverterDef);
			}
			else if (jaxb2Present) {
				messageConverters.add(createConverterDefinition(Jaxb2RootElementHttpMessageConverter.class, source));
			}

			if (jackson2Present) {
				Class<?> type = MappingJackson2HttpMessageConverter.class;
				RootBeanDefinition jacksonConverterDef = createConverterDefinition(type, source);
				GenericBeanDefinition jacksonFactoryDef = createObjectMapperFactoryDefinition(source);
				jacksonConverterDef.getConstructorArgumentValues().addIndexedArgumentValue(0, jacksonFactoryDef);
				messageConverters.add(jacksonConverterDef);
			}
			else if (gsonPresent) {
				messageConverters.add(createConverterDefinition(GsonHttpMessageConverter.class, source));
			}

			if (jackson2SmilePresent) {
				Class<?> type = MappingJackson2SmileHttpMessageConverter.class;
				RootBeanDefinition jacksonConverterDef = createConverterDefinition(type, source);
				GenericBeanDefinition jacksonFactoryDef = createObjectMapperFactoryDefinition(source);
				jacksonFactoryDef.getPropertyValues().add("factory", new SmileFactory());
				jacksonConverterDef.getConstructorArgumentValues().addIndexedArgumentValue(0, jacksonFactoryDef);
				messageConverters.add(jacksonConverterDef);
			}
			if (jackson2CborPresent) {
				Class<?> type = MappingJackson2CborHttpMessageConverter.class;
				RootBeanDefinition jacksonConverterDef = createConverterDefinition(type, source);
				GenericBeanDefinition jacksonFactoryDef = createObjectMapperFactoryDefinition(source);
				jacksonFactoryDef.getPropertyValues().add("factory", new CBORFactory());
				jacksonConverterDef.getConstructorArgumentValues().addIndexedArgumentValue(0, jacksonFactoryDef);
				messageConverters.add(jacksonConverterDef);
			}
		}
		return messageConverters;
	}

	private GenericBeanDefinition createObjectMapperFactoryDefinition(@Nullable Object source) {
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(Jackson2ObjectMapperFactoryBean.class);
		beanDefinition.setSource(source);
		beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		return beanDefinition;
	}

	private RootBeanDefinition createConverterDefinition(Class<?> converterClass, @Nullable Object source) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition(converterClass);
		beanDefinition.setSource(source);
		beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		return beanDefinition;
	}

	private ManagedList<Object> extractBeanSubElements(Element parentElement, ParserContext context) {
		ManagedList<Object> list = new ManagedList<>();
		list.setSource(context.extractSource(parentElement));
		for (Element beanElement : DomUtils.getChildElementsByTagName(parentElement, "bean", "ref")) {
			Object object = context.getDelegate().parsePropertySubElement(beanElement, null);
			list.add(object);
		}
		return list;
	}


	/**
	 * A FactoryBean for a CompositeUriComponentsContributor that obtains the
	 * HandlerMethodArgumentResolver's configured in RequestMappingHandlerAdapter
	 * after it is fully initialized.
	 */
	static class CompositeUriComponentsContributorFactoryBean
			implements FactoryBean<CompositeUriComponentsContributor>, InitializingBean {

		@Nullable
		private RequestMappingHandlerAdapter handlerAdapter;

		@Nullable
		private ConversionService conversionService;

		@Nullable
		private CompositeUriComponentsContributor uriComponentsContributor;

		public void setHandlerAdapter(RequestMappingHandlerAdapter handlerAdapter) {
			this.handlerAdapter = handlerAdapter;
		}

		public void setConversionService(ConversionService conversionService) {
			this.conversionService = conversionService;
		}

		@Override
		public void afterPropertiesSet() {
			Assert.state(this.handlerAdapter != null, "No RequestMappingHandlerAdapter set");
			this.uriComponentsContributor = new CompositeUriComponentsContributor(
					this.handlerAdapter.getArgumentResolvers(), this.conversionService);
		}

		@Override
		@Nullable
		public CompositeUriComponentsContributor getObject() {
			return this.uriComponentsContributor;
		}

		@Override
		public Class<?> getObjectType() {
			return CompositeUriComponentsContributor.class;
		}

		@Override
		public boolean isSingleton() {
			return true;
		}
	}

}
