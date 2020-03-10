/*
 * Copyright 2002-2012 the original author or authors.
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

/**
 * 切入点类型:
 * 	1.静态方法切点–>org.springframework.aop.support.StaticMethodMatcherPointcut
 * 	静态方法切点的抽象基类，默认情况下匹配所有的类。最常用的两个子类NameMatchMethodPointcut和 AbstractRegexpMethodPointcut,
 * 	前者提供简单字符串匹配方法签名，后者使用正则表达式匹配方法签名
 * 	2.动态方法切点–>org.springframework.aop.support.DynamicMethodMatcherPointcut
 * 	动态方法切点的抽象基类，默认情况下匹配所有的类
 *	3.注解切点–>org.springframework.aop.support.annotation.AnnotationMatchingPointcut
 *	4.表达式切点–>org.springframework.aop.support.ExpressionPointcut
 * 	提供了对AspectJ切点表达式语法的支持
 *	5.流程切点–>org.springframework.aop.support.ControlFlowPointcut
 * 	该切点是一个比较特殊的节点，它根据程序执行的堆栈信息查看目标方法是否由某一个方法直接或间接发起调用，一次来判断是否为匹配的链接点
 * 	Core Spring pointcut abstraction.
 *	6.复合切点–>org.springframework.aop.support.ComposablePointcut
 * 	该类是为实现创建多个切点而提供的操作类
 *
 * <p>A pointcut is composed of a {@link ClassFilter} and a {@link MethodMatcher}.
 * Both these basic terms and a Pointcut itself can be combined to build up combinations
 * (e.g. through {@link org.springframework.aop.support.ComposablePointcut}).
 *
 * @author Rod Johnson
 * @see ClassFilter
 * @see MethodMatcher
 * @see org.springframework.aop.support.Pointcuts
 * @see org.springframework.aop.support.ClassFilters
 * @see org.springframework.aop.support.MethodMatchers
 */
public interface Pointcut {

	/**
	 * 返回当前切点匹配的类
	 * ClassFilter可以定位到具体的类上
	 *
	 * Return the ClassFilter for this pointcut.
	 * @return the ClassFilter (never {@code null})
	 */
	ClassFilter getClassFilter();

	/**
	 * 返回当前切点匹配的方法
	 * MethodMatcher可以定位到具体的方法上
	 *
	 * Return the MethodMatcher for this pointcut.
	 * @return the MethodMatcher (never {@code null})
	 */
	MethodMatcher getMethodMatcher();


	/**
	 * Canonical Pointcut instance that always matches.
	 */
	Pointcut TRUE = TruePointcut.INSTANCE;

}
