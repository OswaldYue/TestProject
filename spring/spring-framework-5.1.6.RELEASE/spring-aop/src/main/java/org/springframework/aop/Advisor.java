/*
 * Copyright 2002-2017 the original author or authors.
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

package org.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * 切面可以分为3类：一般切面、切点切面、引介切面
 * 1.一般切面Advisor
 * org.springframework.aop.Advisor代表一般切面，仅包含一个Advice ,因为Advice包含了横切代码和连接点信息，
 * 所以Advice本身一个简单的切面，只不过它代表的横切的连接点是所有目标类的所有方法，因为这个横切面太宽泛，所以一般不会直接使用
 * 2.切点切面PointcutAdvisor
 * org.springframework.aop.PointcutAdvisor ,代表具有切点的切面，包括Advice和Pointcut两个类，
 * 这样就可以通过类、方法名以及方位等信息灵活的定义切面的连接点，提供更具实用性的切面。PointcutAdvisor主要有5个具体的实现类：
 *		1.DefaultPointcutAdvisor：最常用的切面类型，它可以通过任意Pointcut和Advice定义一个切面，
 *		唯一不支持的就是引介的切面类型，一般可以通过扩展该类实现自定义的切面
 *		2.NameMatchMethodPointcutAdvisor：通过该类可以定义按方法名定义切点的切面
 *		3.AspectJExpressionPointcutAdvisor：用于AspectJ切点表达式定义切点的切面
 *		4.StaticMethodMatcherPointcutAdvisor：静态方法匹配器切点定义的切面，默认情况下匹配所有的的目标类
 *		5.AspectJPointcutAdvisor：用于AspectJ语法定义切点的切面
 *	3.引介切面IntroductionAdvisor
 *	org.springframework.aop.IntroductionAdvisor代表引介切面， 引介切面是对应引介增强的特殊的切面，
 *  它应用于类层上面，所以引介切点使用ClassFilter进行定义
 *
 * 举例说明一下：
 * 如果我们定义了一个DogAspect类，并用@AspectJ对其进行注解,那么该类仅仅代表一个切面类，会被Spring扫描并解析，
 * 仅此而已，该类不代表SpringAop概念中的切面。那么Spring如果通过解析该类得到具体的切面呢?
 *
 * 首先,关于SpringAop中的切面概念，可以理解为 切面=连接点+增强
 *
 * 其次，而标记了@AspectJ注解的类在被Spring解析的时候,
 *		1.提取该类的方法上的切点表达式注解：例如–>@Pointcut(“execution(* com.mgw.aopprepare.aspectjaop..*.*(…))”)，解析之后,就可以的到具体的切点.
 * 		2.提取该类的方法上的增强注解:例如：@Before(“test()”)解析之后,就可以得到具体的增强代码
 * 最后,通过第一步和第二步的操作,就可以得到切点+增强,那么自然就构成了一个切面
 *
 * 但是Advisor接口里只包含了一个Advice,并且Advisor一般不直接提供给用户使用,所以这里也可以理解为获取增强，当然如果理解为切面也是没有问题的
 *
 * Base interface holding AOP <b>advice</b> (action to take at a joinpoint)
 * and a filter determining the applicability of the advice (such as
 * a pointcut). <i>This interface is not for use by Spring users, but to
 * allow for commonality in support for different types of advice.</i>
 *
 * <p>Spring AOP is based around <b>around advice</b> delivered via method
 * <b>interception</b>, compliant with the AOP Alliance interception API.
 * The Advisor interface allows support for different types of advice,
 * such as <b>before</b> and <b>after</b> advice, which need not be
 * implemented using interception.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 */
public interface Advisor {

	/**
	 * Common placeholder for an empty {@code Advice} to be returned from
	 * {@link #getAdvice()} if no proper advice has been configured (yet).
	 * @since 5.0
	 */
	Advice EMPTY_ADVICE = new Advice() {};


	/**
	 * Return the advice part of this aspect. An advice may be an
	 * interceptor, a before advice, a throws advice, etc.
	 * @return the advice that should apply if the pointcut matches
	 * @see org.aopalliance.intercept.MethodInterceptor
	 * @see BeforeAdvice
	 * @see ThrowsAdvice
	 * @see AfterReturningAdvice
	 */
	Advice getAdvice();

	/**
	 * Return whether this advice is associated with a particular instance
	 * (for example, creating a mixin) or shared with all instances of
	 * the advised class obtained from the same Spring bean factory.
	 * <p><b>Note that this method is not currently used by the framework.</b>
	 * Typical Advisor implementations always return {@code true}.
	 * Use singleton/prototype bean definitions or appropriate programmatic
	 * proxy creation to ensure that Advisors have the correct lifecycle model.
	 * @return whether this advice is associated with a particular target instance
	 */
	boolean isPerInstance();

}
