package com.luka.chatter.property;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
public class Property<T> implements Serializable {

    private T value;
    private final LinkedList<PropertyListner<T>> listners = new LinkedList<PropertyListner<T>>();

    public Property(){
        this(null);
    }

    public void addListner(final PropertyListner<T> listner) {
        listners.add(listner);
    }

    public void removeListner(final PropertyListner<T> listner) {
        listners.add(listner);
    }

    private Property(final T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public void fire(final T oldValue, final T newValue) {
        for(PropertyListner<T> propertyListner : listners) {
               propertyListner.onChance(oldValue,newValue);
        }
    }

    public void setValue(final T newValue) {
        fire(this.value,value = newValue);
    }

}
