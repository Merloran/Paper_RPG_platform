package com.macko;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.List;

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

    void menu_export_evidences(Object source, JMenuItem button, List<String> names, List<String> surnames, List<String> nations, List<String> titles) throws Exception {
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export Danych osobowych");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showSaveDialog(null);
            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                PrintWriter writer = new PrintWriter(new FileWriter(fileToSave, false));
                String content = "";

                for (String name : names){
                    content += name + ";";
                }
                content += "\n";

                for (String surname : surnames){
                    content += surname + ";";
                }
                content += "\n";

                for (String nation : nations){
                    content += nation + ";";
                }
                content += "\n";

                for (String title : titles){
                    content += title + ";";
                }

                writer.print(content);
                writer.close();
            }
        }
    }

    void menu_export_professions(Object source, JMenuItem button, List<Profession> professions) throws Exception {
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export Profesji");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showSaveDialog(null);
            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                PrintWriter writer = new PrintWriter(new FileWriter(fileToSave, false));
                String content="";
                for (Profession profession : professions){
                    content += profession.getName() + ";" + profession.getDescription() + ";";
                    for (int i=0; i<profession.getTalents().size(); i++){
                        content += profession.getTalents().get(i).getName() + ";";
                    }
                    content += "\n";
                }
                writer.print(content);
                writer.close();
            }
        }
    }

    void menu_export_races(Object source, JMenuItem button, List<Race> races) throws Exception {
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Export Ras");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showSaveDialog(null);
            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                PrintWriter writer = new PrintWriter(new FileWriter(fileToSave, false));
                String content="";
                for (Race race : races){
                    content += race.getName() + ";" + race.getDescription() + ";" + race.getStrength() + ";" + race.getEndurance() + ";" + race.getAgility() + ";" + race.getIntelligence() + ";" + race.getLuck() + ";" + race.getCharisma() + ";\n";
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

    void menu_import_evidences(Object source, JMenuItem button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game) throws Exception {
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Import Talentów");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showOpenDialog(null);

            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileWithData = fileChooser.getSelectedFile();
                Scanner scanner = new Scanner(fileWithData);
                String[] tab;

                tab = scanner.nextLine().split(";");
                if(!tab[0].equals("")) names.addAll(Arrays.asList(tab));

                tab = scanner.nextLine().split(";");
                if(!tab[0].equals("")) surnames.addAll(Arrays.asList(tab));

                tab = scanner.nextLine().split(";");
                if(!tab[0].equals("")) nations.addAll(Arrays.asList(tab));

                tab = scanner.nextLine().split(";");
                if(!tab[0].equals("")) titles.addAll(Arrays.asList(tab));

                if (names.size()>0 || surnames.size()>0 || nations.size()>0 || titles.size()>0) game.getmExportEvidences().setEnabled(true);
                Button buttonToCheck = new Button();
                if(game.getpEvidenceCreator().isShowing()) button_evidence_creator(buttonToCheck, buttonToCheck, names, surnames, nations, titles, game);
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
                if (talents.size()>0) {
                    game.getmExportTalents().setEnabled(true);
                    game.getmImportProfessions().setEnabled(true);
                }

                Button buttonToCheck = new Button();
                if(game.getpTalentCreator().isShowing()) button_talent_creator(buttonToCheck, buttonToCheck, talents, game);
                if(game.getpProfessionCreator().isShowing()) button_profession_creator(buttonToCheck, buttonToCheck, talents, game.getProfessions(), game);
            }
        }
    }

    void menu_import_professions(Object source, JMenuItem button, List<Talent> talents, List<Profession> professions, Server game) throws Exception {
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Import Profesji");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showOpenDialog(null);

            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileWithData = fileChooser.getSelectedFile();
                Scanner scanner = new Scanner(fileWithData);
                label: while(scanner.hasNextLine()){
                    String[] tab;
                    tab = scanner.nextLine().split(";");
                    for (int i=2; i<tab.length; i++)
                    {
                        if(!findTalent(tab[i], talents)) {
                            JOptionPane.showMessageDialog(null, "Profesja " + tab[0] + " zawiera talent " + tab[i] + " którego nie ma na liście talentów!", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
                            continue label;
                        }
                    }
                    List<Talent> list = new ArrayList<>();
                    for (int i=2; i<tab.length; i++){
                        list.add(talents.get(findTalentIndex(tab[i], talents)));
                    }
                    professions.add(new Profession(tab[0], tab[1], list));
                }

                if (professions.size()>0) game.getmExportProfessions().setEnabled(true);

                Button buttonToCheck = new Button();
                if(game.getpProfessionCreator().isShowing()) button_profession_creator(buttonToCheck, buttonToCheck, talents, game.getProfessions(), game);
            }
        }
    }

    void menu_import_races(Object source, JMenuItem button, List<Race> races, Server game) throws Exception {
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Import Profesji");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showOpenDialog(null);

            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileWithData = fileChooser.getSelectedFile();
                Scanner scanner = new Scanner(fileWithData);
                label: while(scanner.hasNextLine()){
                    String[] tab;
                    tab = scanner.nextLine().split(";");
                    races.add(new Race(tab[0], tab[1], Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]), Integer.parseInt(tab[5]), Integer.parseInt(tab[6]), Integer.parseInt(tab[7])));
                }

                if (races.size()>0) game.getmExportRaces().setEnabled(true);

                Button buttonToCheck = new Button();
                if(game.getpRaceCreator().isShowing()) button_race_creator(buttonToCheck, buttonToCheck, races, game);
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
                game.getmImportProfessions().setEnabled(true);
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
                if(item.getHealth()!=0) info += "Zdrowie: " + item.getHealth() + newLine;
                if(item.getCondition()!=0) info += "Kondycja: " + item.getCondition() + newLine;
                if(item.getMana()!=0) info += "Mana: " + item.getMana() + newLine;
                if(item.getStrength()!=0) info += "Siła: " + item.getStrength() + newLine;
                if(item.getEndurance()!=0) info += "Wytrzymałość: " + item.getEndurance() + newLine;
                if(item.getAgility()!=0) info += "Zręczność: " + item.getAgility() + newLine;
                if(item.getIntelligence()!=0) info += "Inteligencja: " + item.getIntelligence() + newLine;
                if(item.getLuck()!=0) info += "Szczęście: " + item.getLuck() + newLine;
                if(item.getCharisma()!=0) info += "Charyzma: " + item.getCharisma() + newLine;
                info += "Masa: " + item.getMass() + newLine;
                if(!item.getDescription().equals("") && !item.getDescription().equals(" ")) info += "Opis: " + item.getDescription() + newLine;
                info += "</html>";

                game.getlItems().get(counter).setToolTipText(info);
                game.getpItemList().add(game.getlItems().get(counter));
                counter++;
            }


            for(int i = 0; i<game.getTfItemValues().size(); i++) game.getTfItemValues().get(i).setText("");

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
                   if (!game.getTfItemValues().get(i).getText().matches("-?[0-9]+") && !game.getTfItemValues().get(i).getText().equals("")){
                       TitledBorder border = (TitledBorder)game.getTfItemValues().get(i).getBorder();
                       JOptionPane.showMessageDialog(null, "Wartość w polu " + border.getTitle() + " musi być liczbą całkowitą.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
                       return;
                   }
               }

               for(int i = 0; i<game.getTfItemValues().size(); i++)
               {
                   if (i > 10 && game.getTfItemValues().get(i).getText().equals("")){
                       game.getTfItemValues().get(i).setText(" ");
                   } else {
                       if(game.getTfItemValues().get(i).getText().equals("")) game.getTfItemValues().get(i).setText("0");
                   }
               }

               items.add(new Item(game.getTfItemValues().get(0).getText(), Integer.parseInt(game.getTfItemValues().get(1).getText()), Integer.parseInt(game.getTfItemValues().get(2).getText()), Integer.parseInt(game.getTfItemValues().get(3).getText()), Integer.parseInt(game.getTfItemValues().get(4).getText()), Integer.parseInt(game.getTfItemValues().get(5).getText()), Integer.parseInt(game.getTfItemValues().get(6).getText()), Integer.parseInt(game.getTfItemValues().get(7).getText()), Integer.parseInt(game.getTfItemValues().get(8).getText()), Integer.parseInt(game.getTfItemValues().get(9).getText()), Integer.parseInt(game.getTfItemValues().get(10).getText()), game.getTfItemValues().get(11).getText(), game.getTfItemValues().get(12).getText(), game.getTfItemValues().get(13).getText()));

               if (items.size()>0) game.getmExportItems().setEnabled(true);

               Button buttonToCheck = new Button();
               button_item_creator(buttonToCheck, buttonToCheck, items, game);
               refresh(game);
           } else {
               JOptionPane.showMessageDialog(null, "Nie podałeś nazwy przedmiotu lub taka nazwa już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
           }
        }
    }

    void button_evidence_creator(Object source, Button button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game){
        if(source==button)
        {
            game.getContentPane().removeAll();

            if(names.size()>0) {
                game.getTaName().setText("");
                Collections.sort(names);
                for (String name : names) {
                    game.getTaName().setText(game.getTaName().getText() + name + "\n");
                }
            } else {
                game.getTaName().setText("Pusta lista imion.");
            }

            if(surnames.size()>0) {
                game.getTaSurname().setText("");
                Collections.sort(surnames);
                for (String surname : surnames) {
                    game.getTaSurname().setText(game.getTaSurname().getText() + surname + "\n");
                }
            } else {
                game.getTaSurname().setText("Pusta lista nazwisk.");
            }

            if(nations.size()>0) {
                game.getTaNation().setText("");
                Collections.sort(nations);
                for (String nation : nations) {
                    game.getTaNation().setText(game.getTaNation().getText() + nation + "\n");
                }
            } else {
                game.getTaNation().setText("Pusta lista państw.");
            }

            if(titles.size()>0) {
                game.getTaTitle().setText("");
                Collections.sort(titles);
                for (String title : titles) {
                    game.getTaTitle().setText(game.getTaTitle().getText() + title + "\n");
                }
            } else {
                game.getTaTitle().setText("Pusta lista tytułów.");
            }

            game.getpEvidenceCreator().add(game.getbBackLobby());
            game.add(game.getpEvidenceCreator());
            refresh(game);
        }
    }

    void button_add_name(Object source, Button button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game){
        if(source==button)
        {
            if(!game.getTfEvidences().get(0).getText().equals("") && !names.contains(game.getTfEvidences().get(0).getText())){
                names.add(game.getTfEvidences().get(0).getText());

                game.getTfEvidences().get(0).setText("");

                button_evidence_creator(button, button, names, surnames, nations, titles, game);
                game.getmExportEvidences().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podałeś imienia lub takie imię już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void button_add_surname(Object source, Button button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game){
        if(source==button)
        {
            if(!game.getTfEvidences().get(1).getText().equals("") && !surnames.contains(game.getTfEvidences().get(1).getText())){
                surnames.add(game.getTfEvidences().get(1).getText());

                game.getTfEvidences().get(1).setText("");

                button_evidence_creator(button, button, names, surnames, nations, titles, game);
                game.getmExportEvidences().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podałeś nazwiska lub takie nazwisko już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void button_add_nation(Object source, Button button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game){
        if(source==button)
        {
            if(!game.getTfEvidences().get(2).getText().equals("") && !nations.contains(game.getTfEvidences().get(2).getText())){
                nations.add(game.getTfEvidences().get(2).getText());

                game.getTfEvidences().get(2).setText("");

                button_evidence_creator(button, button, names, surnames, nations, titles, game);
                game.getmExportEvidences().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podałeś państwa lub takie państwo już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void button_add_title(Object source, Button button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game){
        if(source==button)
        {
            if(!game.getTfEvidences().get(3).getText().equals("") && !titles.contains(game.getTfEvidences().get(3).getText())){
                titles.add(game.getTfEvidences().get(3).getText());

                game.getTfEvidences().get(3).setText("");

                button_evidence_creator(button, button, names, surnames, nations, titles, game);
                game.getmExportEvidences().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podałeś tytułu lub taki tytuł już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void text_field_evidences(Object source, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game){
        Button buttonToCheck = new Button();
        if(source==game.getTfEvidences().get(0)) button_add_name(buttonToCheck, buttonToCheck, names, surnames, nations, titles, game);
        if(source==game.getTfEvidences().get(1)) button_add_surname(buttonToCheck, buttonToCheck, names, surnames, nations, titles, game);
        if(source==game.getTfEvidences().get(2)) button_add_nation(buttonToCheck, buttonToCheck, names, surnames, nations, titles, game);
        if(source==game.getTfEvidences().get(3)) button_add_title(buttonToCheck, buttonToCheck, names, surnames, nations, titles, game);
    }

    void button_profession_creator(Object source, Button button, List<Talent> talents, List<Profession> professions, Server game){
        if(source==button) {
            int counter=0;
            Collections.sort(professions);
            for (Profession profession : professions)
            {
                game.getlProfessions().add(new JLabel());
                game.getlProfessions().get(counter).setText(profession.getName());
                game.getlProfessions().get(counter).setBorder(new EtchedBorder());

                String info="<html>", newLine = "<br/>";
                for (int i=0; i<profession.getTalents().size(); i++){
                    info += profession.getTalents().get(i).getName() + newLine;
                }
                if(!profession.getDescription().equals("") && !profession.getDescription().equals(" ")) info += "Opis: " + profession.getDescription();
                info += "</html>";
                game.getlProfessions().get(counter).setToolTipText(info);
                game.getpProfessionList().add(game.getlProfessions().get(counter));
                counter++;
            }

            game.getpProfessionBox().removeAll();
            game.getpProfessionBox().setSize((int)(game.getWidth()*(3/10f)), (int)(game.getHeight()*(6/30f)));
            game.getpProfessionBox().add(game.getTfProfessionName());
            game.getpProfessionBox().add(game.getTfProfessionDescription());

            JComboBox comboBox = new JComboBox();
            comboBox.setBorder(new TitledBorder(new EtchedBorder(), "Talent"));
            comboBox.addItem("-----");
            for (Talent talent : talents){
                comboBox.addItem(talent.getName());
            }

            if(talents.size()>1) game.getbAddProfession().setEnabled(true);

            game.getTfProfessionName().setText("");
            game.getTfProfessionDescription().setText("");
            game.getCbProfessionTalents().clear();
            game.getCbProfessionTalents().add(comboBox);
            game.getCbProfessionTalents().get(0).addActionListener(game);
            game.getpProfessionBox().add(game.getCbProfessionTalents().get(0));

            game.getContentPane().removeAll();
            game.getpProfessionCreator().add(game.getbBackLobby());
            game.add(game.getpProfessionCreator());
            refresh(game);
        }
    }

    void combo_box_add(Object source, JComboBox button, List<Talent> talents, Server game){
        if(source==button && button.getSelectedIndex()!=0 && game.getCbProfessionTalents().size()<9)
        {
            JComboBox comboBox = new JComboBox();
            comboBox.setBorder(new TitledBorder(new EtchedBorder(), "Talent"));
            comboBox.addItem("-----");
            for (Talent talent : talents){
                comboBox.addItem(talent.getName());
            }

            game.getpProfessionBox().setSize(game.getpProfessionBox().getWidth(), game.getpProfessionBox().getHeight()+(int)(game.getHeight()*(1/15f)));
            game.getCbProfessionTalents().add(comboBox);
            game.getCbProfessionTalents().get(game.getCbProfessionTalents().size()-1).addActionListener(game);
            game.getpProfessionBox().add(game.getCbProfessionTalents().get(game.getCbProfessionTalents().size()-1));
            refresh(game);
        }
    }

    void button_add_profession(Object source, Button button, List<Talent> talents, List<Profession> professions, Server game){
        if(source==button)
        {
            if (!game.getTfProfessionName().getText().equals("") && !findProfession(game.getTfProfessionName().getText(), professions)){

                List<Talent> list = new ArrayList<>();
                for(int i=0; i<game.getCbProfessionTalents().size(); i++){
                    if(game.getCbProfessionTalents().get(i).getSelectedIndex()!=0) list.add(talents.get(game.getCbProfessionTalents().get(i).getSelectedIndex()-1));
                }

                professions.add(new Profession(game.getTfProfessionName().getText(), game.getTfProfessionDescription().getText(), list));

                game.getmExportProfessions().setEnabled(true);
                Button buttonToCheck = new Button();
                button_profession_creator(buttonToCheck, buttonToCheck, talents, professions, game);
                refresh(game);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podałeś nazwy profesji lub taka nazwa już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void button_race_creator(Object source, Button button, List<Race> races, Server game){
        if(source==button) {

            int counter=0;
            Collections.sort(races);
            for (Race race : races)
            {
                game.getlRaces().add(new JLabel());
                game.getlRaces().get(counter).setText(race.getName());
                game.getlRaces().get(counter).setBorder(new EtchedBorder());

                String info="<html>", newLine = "<br/>";
                if(race.getStrength()!=0) info += "Siła: " + race.getStrength() + newLine;
                if(race.getEndurance()!=0) info += "Wytrzymałość: " + race.getEndurance() + newLine;
                if(race.getAgility()!=0) info += "Zręczność: " + race.getAgility() + newLine;
                if(race.getIntelligence()!=0) info += "Inteligencja: " + race.getIntelligence() + newLine;
                if(race.getLuck()!=0) info += "Szczęście: " + race.getLuck() + newLine;
                if(race.getCharisma()!=0) info += "Charyzma: " + race.getCharisma() + newLine;
                if(!race.getDescription().equals("") && !race.getDescription().equals(" ")) info += "Opis: " + race.getDescription() + newLine;
                info += "</html>";

                game.getlRaces().get(counter).setToolTipText(info);
                game.getpRaceList().add(game.getlRaces().get(counter));
                counter++;
            }
            for (int i=0; i<game.getTfRaceValues().size(); i++) game.getTfRaceValues().get(i).setText("");

            game.getContentPane().removeAll();
            game.getpRaceCreator().add(game.getbBackLobby());
            game.add(game.getpRaceCreator());
            refresh(game);
        }
    }

    void button_add_race(Object source, Button button, List<Race> races, Server game){
        if(source==button) {
            if (!game.getTfRaceValues().get(0).getText().equals("") && !findRace(game.getTfRaceValues().get(0).getText(), races)){


                for(int i=2; i<game.getTfRaceValues().size(); i++)
                {
                    if (!game.getTfRaceValues().get(i).getText().matches("-?[0-9]+") && !game.getTfRaceValues().get(i).getText().equals("")){
                        TitledBorder border = (TitledBorder)game.getTfRaceValues().get(i).getBorder();
                        JOptionPane.showMessageDialog(null, "Wartość w polu " + border.getTitle() + " musi być liczbą całkowitą.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }

                for(int i = 0; i<game.getTfRaceValues().size(); i++)
                {
                    if (i==1 && game.getTfRaceValues().get(i).getText().equals("")){
                        game.getTfRaceValues().get(i).setText(" ");
                    } else if (game.getTfRaceValues().get(i).getText().equals("")){
                        game.getTfRaceValues().get(i).setText("0");
                    }
                }

                races.add(new Race(game.getTfRaceValues().get(0).getText(), game.getTfRaceValues().get(1).getText(), Integer.parseInt(game.getTfRaceValues().get(2).getText()), Integer.parseInt(game.getTfRaceValues().get(3).getText()), Integer.parseInt(game.getTfRaceValues().get(4).getText()), Integer.parseInt(game.getTfRaceValues().get(5).getText()), Integer.parseInt(game.getTfRaceValues().get(6).getText()), Integer.parseInt(game.getTfRaceValues().get(7).getText())));

                if (races.size()>0) game.getmExportRaces().setEnabled(true);

                Button buttonToCheck = new Button();
                button_race_creator(buttonToCheck, buttonToCheck, races, game);
                refresh(game);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podałeś nazwy rasy lub taka nazwa już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
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

    void button_exit(Object source, Button button, Server game){
        if(source==button)
        {
            game.dispose();
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

    int findTalentIndex(String name, List<Talent> list){
        for (int i=0; i<list.size(); i++) {
            if(list.get(i).getName().equalsIgnoreCase(name)) return i;
        }
        return 0;
    }

    boolean findItem(String name, List<Item> list){
        for (Item item : list) {
            if(item.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    boolean findProfession(String name, List<Profession> list){
        for (Profession profession : list) {
            if(profession.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    boolean findRace(String name, List<Race> list){
        for (Race race : list) {
            if(race.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
}
