package com.luka.chatter.components;

import com.luka.chatter.Client;
import com.luka.chatter.data.impl.MoveData;
import com.luka.chatter.property.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 15/08/13
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
public class UserArea extends JPanel implements KeyListener , Runnable{


    public UserArea() {
        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(this);
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }


    public void paint(Graphics g) {
        super.paint(g);
        for(Object object : Client.getUsers()) {
            User user = (User) object;
            if(user != null) {
                if(user.getPoint() != null) {
                    if(user.getPoint().getValue() != null) {
                      g.setColor(user.getColor().getValue());
                     g.fillRect(user.getPoint().getValue().x, user.getPoint().getValue().y, 30, 30);
                    }
                }
            }
        }
        g.setColor(Client.getUser().getColor().getValue());
        g.fillRect(Client.getUser().getPoint().getValue().x, Client.getUser().getPoint().getValue().y, 30, 30);
    }

    @Override
    public void run() {
        while(true) {
            repaint();
        }
    }

    public void update(int keyCode) {
        if(keyCode != -1) {
            final Point point = new Point(Client.getUser().getPoint().getValue());
            int speed = 5;
            if(keyCode == KeyEvent.VK_UP) {
                if(Client.USERWINDOW.height < point.getY()) {
                point.y = 2;
                Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(),point));
                } else if(point.getY() < 0) {
                    point.y = (int) Client.USERWINDOW.getWidth()-100;
                    System.out.println(point.y);
                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(), point));
                } else {
                    point.y -= speed;

                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(), point));
                }
            } else if(keyCode == KeyEvent.VK_DOWN) {
                if(Client.USERWINDOW.height < point.getY()) {
                    point.y = 2;
                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(),point));
                } else if(point.getY() < 0) {
                    point.y = (int) Client.USERWINDOW.getWidth()-2;
                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(), point));
                } else {
                    point.y += speed;
                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(), point));
                }
            } else if(keyCode == KeyEvent.VK_LEFT) {
                if(Client.USERWINDOW.width < point.getX()) {
                    point.x = 2;
                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(),point));
                } else if(point.getX() < 0) {
                    point.x = (int) Client.USERWINDOW.getWidth()-2;
                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(), point));
                } else {
                    point.x -= speed;
                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(), point));
                }
            } else if(keyCode == KeyEvent.VK_RIGHT) {
                if(Client.USERWINDOW.width < point.getX()) {
                    point.x = 2;
                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(),point));
                } else if(point.getX() < 0) {
                    point.x = (int) Client.USERWINDOW.getWidth()-2;
                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(), point));
                } else {
                    point.x += speed;
                    Client.getConnection().send(new MoveData(Client.getUser().getUid().getValue(), point));
                }
            }
        }
    }


    public void keyTyped(KeyEvent e) {
        update(e.getKeyCode());
    }

    public void keyPressed(KeyEvent e) {
      update(e.getKeyCode());
       Client.getInputArea().process(e);

    }

    public void keyReleased(KeyEvent e) {
      update(-1);
    }


}
