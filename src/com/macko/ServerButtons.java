package com.macko;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ServerButtons {

    void button_start(Object source, Button button, ConnectionListener connection, List<Socket> clients, List<MessageListenerServer> inputs, Server game) throws Exception{
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

    void menu_export_item(Object source, JMenuItem button, List<Item> items) throws Exception{
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export Przedmiotów");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showSaveDialog(null);
            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                PrintWriter writer = new PrintWriter(new FileWriter(fileToSave, false));
                String content = "";

                for (Item item : items) {
                    content += item.getName() + ";" + item.getHealth() + ";" + item.getCondition() + ";" + item.getMana() + ";" + item.getStrength() + ";" + item.getEndurance() + ";" + item.getAgility() + ";" + item.getIntelligence() + ";" + item.getLuck() + ";" + item.getCharisma() + ";" + item.getMass() + ";" + item.getDamage() + ";" + item.getDefence() + ";" + item.getDescription() + "\n";
                }

                writer.print(content);
                writer.close();
            }
        }
    }

    void menu_export_talents(Object source, JMenuItem button, List<Talent> talents) throws Exception {
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export Talentów");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showSaveDialog(null);
            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                PrintWriter writer = new PrintWriter(new FileWriter(fileToSave, false));
                StringBuilder content = new StringBuilder();

                for (Talent talent : talents) {
                    content.append(talent.getName()).append(";").append(talent.getTier()).append(";").append(talent.getStatistic()).append("\n");
                }

                writer.print(content);
                writer.close();
            }
        }
    }

    void menu_import_item(Object source, JMenuItem button, List<Item> items, Server game) throws  Exception{
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Import Przedmiotów");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showOpenDialog(null);

            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileWithData = fileChooser.getSelectedFile();
                Scanner scanner = new Scanner(fileWithData);
                while(scanner.hasNextLine()){
                    String[] tab;
                    tab = scanner.nextLine().split(";");
                    if(tab.length==14 && !findItem(tab[0], items)) items.add(new Item(tab[0], Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]), Integer.parseInt(tab[5]), Integer.parseInt(tab[6]), Integer.parseInt(tab[7]), Integer.parseInt(tab[8]), Integer.parseInt(tab[9]), Integer.parseInt(tab[10]), tab[11], tab[12], tab[13]));
                }
                if (items.size()>0) game.getmExportItems().setEnabled(true);

                Button buttonToCheck = new Button();
                if(game.getpItemCreator().isShowing()) button_item_creator(buttonToCheck, buttonToCheck, items, game);
            }
        }
    }

    void menu_import_talents(Object source, JMenuItem button, List<Talent> talents, Server game) throws Exception {
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Import Talentów");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showOpenDialog(null);

            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileWithData = fileChooser.getSelectedFile();
                Scanner scanner = new Scanner(fileWithData);
                while(scanner.hasNextLine()){
                    String[] tab;
                    tab = scanner.nextLine().split(";");
                    if(tab.length==3 && !findTalent(tab[0], talents)) talents.add(new Talent(tab[0], Integer.parseInt(tab[1]), Integer.parseInt(tab[2])));
                }
                if (talents.size()>0) game.getmExportTalents().setEnabled(true);

                Button buttonToCheck = new Button();
                if(game.getpTalentCreator().isShowing()) button_talent_creator(buttonToCheck, buttonToCheck, talents, game);
            }
        }
    }

    void button_talent_creator(Object source, Button button, List<Talent> talents, Server game){
        if(source==button)
        {
            game.getContentPane().removeAll();

            if(talents.size()>0) {
                game.getTaTalent().setText("");
                Collections.sort(talents);
                for (Talent talent : talents) {
                    String statistic = switch (talent.getStatistic()) {
                        case 0 -> "(Siła)";
                        case 1 -> "(Wytrzymałość)";
                        case 2 -> "(Zręczność)";
                        case 3 -> "(Inteligencja)";
                        case 4 -> "(Szczęście)";
                        default -> "(Charyzma)";
                    };
                    game.getTaTalent().setText(game.getTaTalent().getText() + talent.getName() + statistic + "\n");
                }
            } else {
                game.getTaTalent().setText("Lista Talentów jest pusta.");
            }

            game.getpTalentCreator().add(game.getbBackLobby());
            game.add(game.getpTalentCreator());
            refresh(game);
        }

    }

    void button_add_talent(Object source, Button button, List<Talent> talents, Server game){
        if(source==button)
        {
            if(!game.getTfNameTalent().getText().equals("") && !findTalent(game.getTfNameTalent().getText(), talents)){
                talents.add(new Talent(game.getTfNameTalent().getText(), 0, game.getCbStatistic().getSelectedIndex()));

                game.getTfNameTalent().setText("");
                game.getCbStatistic().setSelectedIndex(0);

                button_talent_creator(button, button, talents, game);
                game.getmExportTalents().setEnabled(true);
                refresh(game);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podałeś nazwy talentu lub taka nazwa już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void button_item_creator(Object source, Button button, List<Item> items, Server game){
        if(source==button) {

            int counter=0;
            Collections.sort(items);
            for (Item item : items)
            {
                game.getlItems().add(new JLabel());
                game.getlItems().get(counter).setText(item.getName());
                game.getlItems().get(counter).setBorder(new EtchedBorder());

                String info="<html>", newLine = "<br/>";
                if(!item.getDamage().equals("") && !item.getDamage().equals(" ")) info += "Obrażenia: " + item.getDamage() + newLine;
                if(!item.getDefence().equals("") && !item.getDefence().equals(" ")) info += "Obrona: " + item.getDefence() + newLine;
                if(item.getHealth()>0) info += "Zdrowie: " + item.getHealth() + newLine;
                if(item.getCondition()>0) info += "Kondycja: " + item.getCondition() + newLine;
                if(item.getMana()>0) info += "Mana: " + item.getMana() + newLine;
                if(item.getStrength()>0) info += "Siła: " + item.getStrength() + newLine;
                if(item.getEndurance()>0) info += "Wytrzymałość: " + item.getEndurance() + newLine;
                if(item.getAgility()>0) info += "Zręczność: " + item.getAgility() + newLine;
                if(item.getIntelligence()>0) info += "Inteligencja: " + item.getIntelligence() + newLine;
                if(item.getLuck()>0) info += "Szczęście: " + item.getLuck() + newLine;
                if(item.getCharisma()>0) info += "Charyzma: " + item.getCharisma() + newLine;

                info += "Masa: " + item.getMass() + newLine;
                if(!item.getDescription().equals("") && !item.getDescription().equals(" ")) info += "Opis: " + item.getDescription() + newLine;

                info += "</html>";
                game.getlItems().get(counter).setToolTipText(info);
                game.getpItemList().add(game.getlItems().get(counter));
                counter++;
            }


            for(int i = 1; i<game.getTfItemValues().size(); i++)
            {
                if (i > 10){
                    game.getTfItemValues().get(i).setText(" ");
                } else {
                    game.getTfItemValues().get(i).setText("0");
                }
            }
            game.getTfItemValues().get(0).setText("");

            game.getContentPane().removeAll();
            game.getpItemCreator().add(game.getbBackLobby());
            game.add(game.getpItemCreator());
            refresh(game);
        }
    }

    void button_add_item(Object source, Button button, List<Item> items, Server game){
        if(source==button) {
           if (!game.getTfItemValues().get(0).getText().equals("") && !findItem(game.getTfItemValues().get(0).getText(), items)){

               for(int i=1; i<11; i++)
               {
                   if (!game.getTfItemValues().get(i).getText().matches("[0-9]+")){
                       JOptionPane.showMessageDialog(null, "Wartość w polu " + (i+1) + " musi być liczbą całkowitą.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
                       break;
                   }
               }

               items.add(new Item(game.getTfItemValues().get(0).getText(), Integer.parseInt(game.getTfItemValues().get(1).getText()), Integer.parseInt(game.getTfItemValues().get(2).getText()), Integer.parseInt(game.getTfItemValues().get(3).getText()), Integer.parseInt(game.getTfItemValues().get(4).getText()), Integer.parseInt(game.getTfItemValues().get(5).getText()), Integer.parseInt(game.getTfItemValues().get(6).getText()), Integer.parseInt(game.getTfItemValues().get(7).getText()), Integer.parseInt(game.getTfItemValues().get(8).getText()), Integer.parseInt(game.getTfItemValues().get(9).getText()), Integer.parseInt(game.getTfItemValues().get(10).getText()), game.getTfItemValues().get(11).getText(), game.getTfItemValues().get(12).getText(), game.getTfItemValues().get(13).getText()));

               for(int i = 0; i<game.getTfItemValues().size(); i++)
               {
                   if (i > 10){
                       game.getTfItemValues().get(i).setText(" ");
                   } else {
                       game.getTfItemValues().get(i).setText("0");
                   }
               }

               if (items.size()>0) game.getmExportItems().setEnabled(true);

               Button buttonToCheck = new Button();
               button_item_creator(buttonToCheck, buttonToCheck, items, game);
               refresh(game);
           } else {
               JOptionPane.showMessageDialog(null, "Nie podałeś nazwy przedmiotu lub taka nazwa już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
           }
        }
    }

    void button_back_lobby(Object source, Button button, Server game){
        if(source==button) {
            game.getContentPane().removeAll();
            game.add(game.getpStart());
            refresh(game);
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

    boolean findItem(String name, List<Item> list){
        for (Item item : list) {
            if(item.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
}
