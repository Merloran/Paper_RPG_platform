package com.macko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.Socket;
import java.util.List;

public class ServerButtons {

    void button_start(Object source, WindowServer window, Button button, ConnectionListener connection, List<Socket> clients, List<MessageListenerServer> inputs) throws Exception{
        if(source==button)
        {
            for (int i = 0; i < connection.getOutputs().size(); i++) {
                if(clients.get(i).isBound() && !clients.get(i).isClosed() && !inputs.get(i).isExit())
                {
                    connection.getOutputs().get(i).writeUTF("Start");
                }
            }
        }
    }

    void menu_export_item(Object source, JMenuItem button, JFileChooser fileChooser, Server game){
        if(source==button)
        {
            fileChooser.showSaveDialog(null);
            fileChooser.getSelectedFile().getPath();
        }

    }

    void menu_import_item(Object source, JMenuItem button, JFileChooser fileChooser, Server game){
        if(source==button)
        {
            int i = fileChooser.showOpenDialog(null);
            if(i == JFileChooser.APPROVE_OPTION) {

                fileChooser.getSelectedFile().getPath();
            }
        }

    }

    void button_talent_creator(Object source, Button b, Server game){
        if(source==b)
        {
            game.getContentPane().removeAll();
            game.add(game.getpTalentCreator());
            refresh(game);
        }

    }

    void button_exit(Object source, WindowServer window, Button b){
        if(source==b)
        {
            window.dispose();
        }
    }

    void refresh(Server game){
        game.setVisible(false);
        game.setVisible(true);
    }
}
