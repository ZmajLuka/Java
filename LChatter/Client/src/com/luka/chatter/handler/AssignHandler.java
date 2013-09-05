package com.luka.chatter.handler;

import com.luka.chatter.Client;
import com.luka.chatter.data.impl.AssignData;
import com.luka.chatter.property.user.User;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 11:54
 * To change this template use File | Settings | File Templates.
 */
public class AssignHandler extends AbstractHandler<AssignData> {


    @Override
    public void handle(AssignData data) {
        final User user = data.getValue();
        Client.getUser().getName().setValue(user.getName().getValue());
        Client.getUser().getUid().setValue(user.getUid().getValue());
    }
}
