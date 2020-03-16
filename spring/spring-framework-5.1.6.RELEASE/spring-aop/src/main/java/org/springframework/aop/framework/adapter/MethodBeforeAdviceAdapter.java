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

package org.springframework.aop.framework.adapter;

import java.io.Serializable;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * Adapter to enable {@link org.springframework.aop.MethodBeforeAdvice}
 * to be used in the Spring AOP framework.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 */
@SuppressWarnings("serial")
class MethodBeforeAdviceAdapter implements AdvisorAdapter, Serializable {

	@Override
	public boolean supportsAdvice(Advice advice) {
		return (advice instanceof MethodBeforeAdvice);
	}

	/**
	 * 增强适配器
	 * 这个方法在获取拦截器链的时候调用,从这里也可以看出,Spring中的advisor(增强/切面)
	 * 最终还是被转换为MethodInterceptor对象
	 *
	 * AdvisorAdapter的实现类有AfterReturningAdviceAdapter,MethodBeforeAdviceAdapter,ThrowsAdviceAdapter三个
	 *
	 * AfterReturningAdviceAdapter -> new AfterReturningAdviceInterceptor(advice) -> 后置返回增强
	 * MethodBeforeAdviceAdapter -> new MethodBeforeAdviceInterceptor(advice) -> 前置增强
	 * ThrowsAdviceAdapter -> new ThrowsAdviceInterceptor(advisor.getAdvice()) -> 该适配器有些特殊...看源码吧
	 */
	@Override
	public MethodInterceptor getInterceptor(Advisor advisor) {
		MethodBeforeAdvice advice = (MethodBeforeAdvice) advisor.getAdvice();
		return new MethodBeforeAdviceInterceptor(advice);
	}

}
