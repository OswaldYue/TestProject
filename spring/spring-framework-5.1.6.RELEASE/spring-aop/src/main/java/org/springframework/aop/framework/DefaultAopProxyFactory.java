/*
 * Copyright 2002-2015 the original author or authors.
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

package org.springframework.aop.framework;

import org.springframework.aop.SpringProxy;

import java.io.Serializable;
import java.lang.reflect.Proxy;

/**
 * Default {@link AopProxyFactory} implementation, creating either a CGLIB proxy
 * or a JDK dynamic proxy.
 *
 * <p>Creates a CGLIB proxy if one the following is true for a given
 * {@link AdvisedSupport} instance:
 * <ul>
 * <li>the {@code optimize} flag is set
 * <li>the {@code proxyTargetClass} flag is set
 * <li>no proxy interfaces have been specified
 * </ul>
 *
 * <p>In general, specify {@code proxyTargetClass} to enforce a CGLIB proxy,
 * or specify one or more interfaces to use a JDK dynamic proxy.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 12.03.2004
 * @see AdvisedSupport#setOptimize
 * @see AdvisedSupport#setProxyTargetClass
 * @see AdvisedSupport#setInterfaces
 */
@SuppressWarnings("serial")
public class DefaultAopProxyFactory implements AopProxyFactory, Serializable {


	/**
	 * 创建代理
	 * 1、config.isOptimize()：判断通过CGLIB创建的代理是否使用了优化策略
	 * 2、config.isProxyTargetClass()：是否配置了proxy-target-class为true
	 * 3、hasNoUserSuppliedProxyInterfaces(config)：是否存在代理接口
	 * 4、targetClass.isInterface()-->目标类是否为接口
	 * 5、Proxy.isProxyClass-->如果targetClass类是代理类，则返回true，否则返回false
	*/
	@Override
	public AopProxy createAopProxy(AdvisedSupport config) throws AopConfigException {
		// 1、判断是否需要创建CGLIB动态代理
		//先判断是否使用cglib代理 若@EnableAspectJAutoProxy的proxyTargetClass值设置为true 则可能使用cglib代理
		/*
		* 说明几个问题:
		* 1.config.isOptimize()：判断通过CGLIB创建的代理是否使用了优化策略
		* 该条件取值于ProxyConfig类的optimize属性。此属性标记是否对代理进行优化。
		* 启动优化通常意味着在代理对象被创建后，增强的修改将不会生效，因此默认值为false。
		* 如果exposeProxy设置为true，即使optimize为true也会被忽略
		*
		* 2.config.isProxyTargetClass()：是否配置了proxy-target-class为true
		* 该条件取值于ProxyConfig类的proxyTargetClass属性。此属性标记是否直接对目标类进行代理，而不是通过接口产生代理
		*
		* 3.hasNoUserSuppliedProxyInterfaces(config)：是否存在代理接口
		*
		* 满足以上三者条件的任何一个，则会考虑开启CGLIB动态代理，
		* 但是在该if条件里还有另外一层判断 targetClass.isInterface() || Proxy.isProxyClass(targetClass),
		* 即如果目标类本身就是一个接口，或者目标类是由Proxy.newProxyInstance()或Proxy.getProxyClass()生成时，
		* 则依然采用jdk动态代理
		 * */
		if (config.isOptimize() || config.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(config)) {
			Class<?> targetClass = config.getTargetClass();
			if (targetClass == null) {
				throw new AopConfigException("TargetSource cannot determine target class: " +
						"Either an interface or a target is required for proxy creation.");
			}
			//若代理对象有接口 依然使用jdk动态代理
			if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
				return new JdkDynamicAopProxy(config);
			}
			// 创建CGLIB动态代理
			return new ObjenesisCglibAopProxy(config);
		}
		else {
			//使用jdk动态代理
			return new JdkDynamicAopProxy(config);
		}
	}

	/**
	 * Determine whether the supplied {@link AdvisedSupport} has only the
	 * {@link org.springframework.aop.SpringProxy} interface specified
	 * (or no proxy interfaces specified at all).
	 */
	private boolean hasNoUserSuppliedProxyInterfaces(AdvisedSupport config) {
		Class<?>[] ifcs = config.getProxiedInterfaces();
		return (ifcs.length == 0 || (ifcs.length == 1 && SpringProxy.class.isAssignableFrom(ifcs[0])));
	}

}
