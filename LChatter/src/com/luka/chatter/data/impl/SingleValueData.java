package com.luka.chatter.data.impl;

import com.luka.chatter.data.Data;

import java.io.Serializable;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class SingleValueData<T> extends Data  implements Serializable {

    private final T value;

    public SingleValueData(final byte opcode, final T value) {
        super(opcode);
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}
