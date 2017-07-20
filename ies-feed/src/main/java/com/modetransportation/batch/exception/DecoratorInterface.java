package com.modetransportation.batch.exception;

public interface DecoratorInterface
{
	/**
	 * Decorates the given object. It can either use simple or detailed decoration
	 * or provide a completely new implementation.
	 *
	 * @param obj The object to be decorated.
	 * @return Object The resultant object
	 */
	public Object decorate( Object obj );

	/**
	 * Decorates the given object in a detailed manner and returns the resultant object
	 *
	 * @param obj The object to be decorated.
	 * @return Object The resultant object
	 */
	public Object decorateDetailed( Object obj );

	/**
	 * Decorates the given object in a simple manner and returns the resultant object
	 *
	 * @param obj The object to be decorated.
	 * @return Object The resultant object
	 */
	public Object decorateSimple( Object obj ) ;
}
