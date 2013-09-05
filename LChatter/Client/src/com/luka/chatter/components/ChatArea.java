package com.luka.chatter.components;

import com.luka.chatter.Client;
import com.luka.chatter.data.impl.MessageData;

import javax.swing.*;
import java.awt.*;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 08/08/13
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */
public class ChatArea extends JPanel {

    private final JList<MessageData> list;
    private final DefaultListModel<MessageData> model;

    public ChatArea(){
        super(new BorderLayout());
        model = new DefaultListModel<MessageData>();

        list = new JList<MessageData>();
        list.setFocusable(false);
        list.setDragEnabled(false);
        list.setModel(model);
        list.setCellRenderer(
                new DefaultListCellRenderer(){
                    public Component getListCellRendererComponent(JList l, Object o, int i, boolean s, boolean f){
                        final Component c = super.getListCellRendererComponent(l, o, i, false, false);
                        if(o == null)
                            return c;
                        final MessageData msg = (MessageData)o;
                        final JLabel label = (JLabel) c;
                        label.setText(msg.getValue());
                        if(msg.getUid() != -1) {
                            label.setForeground(Client.getUsers().getUser(msg.getUid()).getColor().getValue());
                        } else {
                            label.setForeground(Color.black);
                        }

                        return label;
                    }
                }
        );

        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    public void addMessage(final MessageData msg){
        model.addElement(msg);
    }

}
