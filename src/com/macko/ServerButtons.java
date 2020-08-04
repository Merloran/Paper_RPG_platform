package com.macko;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
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

    void menu_export_talents(Object source, JMenuItem button, JFileChooser fileChooser, List<Talent> talents) throws Exception {
        if(source==button)
        {
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showSaveDialog(null);
            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                PrintWriter writer = new PrintWriter(new FileWriter(fileToSave, false));
                String content = "";

                for (Talent talent : talents) {
                    content += (talent.getName() + ";" + talent.getTier() + ";" + talent.getStatistic() + "\n");
                }

                writer.print(content);
                writer.close();
            }
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

    void button_talent_creator(Object source, Button button, Server game, List<Talent> talents){
        if(source==button)
        {
            game.getContentPane().removeAll();

            if(talents.size()>0) {
                Collections.sort(talents);
                for (Talent talent : talents) {
                    String statistic;
                    switch (talent.getStatistic())
                    {
                        case 0:
                            statistic="(Siła)";
                            break;
                        case 1:
                            statistic="(Wytrzymałość)";
                            break;
                        case 2:
                            statistic="(Zręczność)";
                            break;
                        case 3:
                            statistic="(Inteligencja)";
                            break;
                        case 4:
                            statistic="(Szczęście)";
                            break;
                        default:
                            statistic="(Charyzma)";
                            break;
                    }
                    game.getTaTalent().setText(game.getTaTalent().getText() + talent.getName() + statistic + "\n");
                }
            } else {
                game.getTaTalent().setText("Lista Talentów jest pusta.");
            }

            game.add(game.getpTalentCreator());
            refresh(game);
        }

    }

    void button_add_talent(Object source, Button button, Server game, List<Talent> talents){
        if(source==button)
        {
            if(!game.getTfName().getText().equals("") && !findTalent(game.getTfName().getText(), talents)){
                talents.add(new Talent(game.getTfName().getText(), 0, game.getCbStatistic().getSelectedIndex()));

                game.getTfName().setText("");
                game.getCbStatistic().setSelectedIndex(0);

                game.getTaTalent().setText("");
                button_talent_creator(button, button, game, talents);
                game.getmExportTalents().setEnabled(true);
                refresh(game);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podałeś nazwy talentu lub taka nazwa już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void button_exit(Object source, WindowServer window, Button button){
        if(source==button)
        {
            window.dispose();
        }
    }

    void refresh(Server game){
        game.setVisible(false);
        game.setVisible(true);
    }

    boolean findTalent(String name, List<Talent> list){
        for (Talent talent : list) {
            if(talent.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
}
