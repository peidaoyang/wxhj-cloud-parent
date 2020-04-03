package com.wxhj.cloud.core.design.observer;

public interface Observerable<T> {
	void registerObserver(Observer<T> obaserver);

	void execute(T t) throws Exception;
}
