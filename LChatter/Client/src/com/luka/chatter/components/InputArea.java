package com.luka.chatter.components;

import com.luka.chatter.Client;
import com.luka.chatter.data.impl.ColorData;
import com.luka.chatter.data.impl.MessageData;
import com.luka.chatter.property.PropertyListner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Created For Educational Purposes, please do not use maliciously.
 * User: Iterator
 * Date: 08/08/13
 * Time: 20:43
 * To change this template use File | Settings | File Templates.
 */
public class InputArea extends JPanel{

    private final JLabel nameLabel;
    private final JTextField inputBox;
    private static final Map<String, Color> NAME_TO_COLOR_MAP = new HashMap<String, Color>();

    static {
        NAME_TO_COLOR_MAP.put("black" , Color.black);
        NAME_TO_COLOR_MAP.put("blue", Color.blue);
        NAME_TO_COLOR_MAP.put("green" , Color.green);
        NAME_TO_COLOR_MAP.put("cyan" , Color.cyan);
        NAME_TO_COLOR_MAP.put("yellow" , Color.yellow);
        NAME_TO_COLOR_MAP.put("pink", Color.pink);
    }

    public InputArea(){
        super(new BorderLayout());

        nameLabel = new JLabel();
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setBorder(new EmptyBorder(0, 5, 0, 5));


        inputBox = new JTextField();
        inputBox.setFocusable(false);
        inputBox.setEditable(false);
        inputBox.addActionListener(
                new ActionListener(){
                    public void actionPerformed(final ActionEvent e){
                        final String message = inputBox.getText().trim();
                        if(message.isEmpty()) {
                            return;
                        }

                        if(message.startsWith("/")){
                            try{
                                final String command = message.substring(1).split(" ")[0].trim();
                                final String data = message.split(" ")[1].trim();
                                if(command.equalsIgnoreCase("color") || command.equalsIgnoreCase("colour")){
                                    colorHandler(data.toLowerCase());
                                    inputBox.setText("");
                                    inputBox.repaint();
                                }
                            }catch(Exception ex){
                                Client.getConnection().send(new MessageData("Invalid Command"));
                            }
                        } else {
                        Client.getConnection().send(new MessageData( Client.getUser().getUid().getValue(), message));
                        inputBox.setText("");
                        inputBox.repaint();
                        }


                    }
                });
        Client.getUser().getName().addListner(
                new PropertyListner<String>() {
                    @Override
                    public void onChance(String oldValue, String newValue) {
                        nameLabel.setText(newValue);
                        nameLabel.revalidate();
                        nameLabel.repaint();
                        revalidate();
                        repaint();
                    }
                }
        );
        add(nameLabel, BorderLayout.WEST);
        add(inputBox, BorderLayout.CENTER);
    }

    public void process(final KeyEvent e){
        final int keycode = e.getKeyCode();
        final char c = e.getKeyChar();
        final String text = inputBox.getText();
        if(c >= 32 && c <= 126){
            inputBox.setText(text + c);
            return;
        }
        if(keycode == KeyEvent.VK_ENTER) {
            inputBox.postActionEvent();
        } else if(keycode == KeyEvent.VK_BACK_SPACE){
            if(text.isEmpty()) {
                return;
            }
            inputBox.setText(text.substring(0, text.length()-1));
        }else if(keycode == KeyEvent.VK_A && e.isControlDown()) {
            inputBox.selectAll();
        }
    }

    private void colorHandler(String colorName) {
        if(NAME_TO_COLOR_MAP.containsKey(colorName)) {
            Color color = NAME_TO_COLOR_MAP.get(colorName);
            Client.getConnection().send(new ColorData(Client.getUser().getUid().getValue(), color));
        } else {
            Client.getConnection().send(new MessageData("Invalid Color!"));
        }
    }

}
