package com.mgw.aopprepare.aspectjaop;

/**
 * 引介增强实现
 * */
public class IntroduceImpl implements IIntroduce {
	@Override
	public void sayIntroduce() {
		System.out.println("--引入");
	}
}
