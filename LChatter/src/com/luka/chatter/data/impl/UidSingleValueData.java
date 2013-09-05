package com.luka.chatter.data.impl;

import java.io.Serializable;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 16/08/13
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */
public class UidSingleValueData<T> extends SingleValueData<T> implements Serializable {

    private final long uid;

    public UidSingleValueData(final byte opcode, final long uid, final T value) {
        super(opcode, value);
        this.uid = uid;
    }

    public long getUid() {
        return this.uid;
    }
}

