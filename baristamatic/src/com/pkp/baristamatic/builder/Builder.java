package com.pkp.baristamatic.builder;

/**
 * Builds an instance given the Instance class and the instance Type
 * @author pravat
 *
 * @param <P> the type
 * @param <Q> the instance
 */
public interface Builder<P, Q> {
	Q build(P type);
}
