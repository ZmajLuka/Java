package com.luka.chatter.property.user;

import com.luka.chatter.property.Property;

import java.awt.*;
import java.io.Serializable;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 12/08/13
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class User implements Serializable {

    private final Property<Long> uid;
    private final Property<String> name;
    private final Property<Point> point;
    private final Property<Color> color;

    public User() {
        this.uid = new Property<Long>();
        this.name = new Property<String>();
        this.point = new Property<Point>();
        this.color = new Property<Color>();
        this.color.setValue(Color.black);
        this.point.setValue(new Point(30,30));
    }

    public Property<Long> getUid() {
        return this.uid;
    }

    public Property<Point> getPoint() {
        return this.point;
    }

    public Property<Color> getColor() {
        return this.color;
    }

    public Property<String> getName() {
        return this.name;
    }

    public boolean equals(final Object o){
        if(o == null)
            return false;
        if(o instanceof Long)
            return uid.getValue().equals(o) || uid.getValue() == o;
        else if(o instanceof User)
            return equals(((User)o).uid.getValue());
        return false;
    }

}
