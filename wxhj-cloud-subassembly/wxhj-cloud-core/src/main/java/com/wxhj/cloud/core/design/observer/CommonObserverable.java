package com.wxhj.cloud.core.design.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonObserverable<T> implements Observerable<T> {
	protected T generalVariable;

	protected List<Observer<T>> observerList = new ArrayList<>();

	@Override
	public void registerObserver(Observer<T> obaserver) {
		observerList.add(obaserver);
	}

	@Override
	public void execute(T t) throws Exception {
		generalVariable = t;
		int i = 0;
		try {
			for (; i < observerList.size(); i++) {
				generalVariable = observerList.get(i).execute(generalVariable);
				generalVariable = observerList.get(i).confirm(generalVariable);
			}
		} catch (Exception e) {
			for (; i >= 0; i--) {
				generalVariable = observerList.get(i).rollBack(generalVariable);
			}
			throw e;
		}
	}
}
