package com.luka.chatter.property;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public interface PropertyListner<T> {

    public void onChance(final T oldValue, final T newValue);

}
