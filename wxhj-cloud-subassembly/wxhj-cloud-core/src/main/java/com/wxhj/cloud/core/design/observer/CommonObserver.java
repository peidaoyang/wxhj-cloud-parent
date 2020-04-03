package com.wxhj.cloud.core.design.observer;

public abstract class CommonObserver<T> implements Observer<T> {
	@Override
	public T confirm(T t) {
		return t;
	}

	@Override
	public T rollBack(T t) {
		return t;
	}
}
