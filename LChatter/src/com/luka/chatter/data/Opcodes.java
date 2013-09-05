package com.luka.chatter.data;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 20:52
 * To change this template use File | Settings | File Templates.
 */
public interface Opcodes {

    byte JOIN = 0x1;
    byte LEAVE = 0X2;
    byte MESSAGE = 0x4;
    byte MOVE = 0x5;
    byte ASSIGN = 0x3;
    byte COLOR = 0x6;
}
