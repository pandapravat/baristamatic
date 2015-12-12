package com.pkp.baristamatic.utl;

public interface Visitee<T extends Visitor<?>> {
	public void accept(T visitor);
}
