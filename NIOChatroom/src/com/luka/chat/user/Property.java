package com.luka.chat.user;

/**
 * Please do not use maliciously but for educational purposes
 * Created with IntelliJ IDEA.
 * User: Luka
 * Date: 04/09/13
 * Time: 17:51
 * To change this template use File | Settings | File Templates.
 */
public class Property<T> {

    private T value;

    private Property(final T value) {
        this.value = value;
    }

    public Property() {
        this(null);
    }
    public T getValue() {
        return this.value;
    }

    public void setValue(final T value) {
        this.value = value;
    }


}
