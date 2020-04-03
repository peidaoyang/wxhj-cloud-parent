package com.wxhj.cloud.core.design.observer;

public interface Observer<T> {
	public T execute(T t) throws Exception;

	public T confirm(T t) throws Exception;

	public T rollBack(T t) throws Exception;
}
