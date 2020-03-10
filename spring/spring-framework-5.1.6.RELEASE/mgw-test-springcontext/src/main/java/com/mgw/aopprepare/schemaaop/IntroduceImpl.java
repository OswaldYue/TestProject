package com.mgw.aopprepare.schemaaop;

/**
 * 引介增强实现
 * */
public class IntroduceImpl implements IIntroduce {
	@Override
	public void sayIntroduce() {
		System.out.println("--引入");
	}
}
