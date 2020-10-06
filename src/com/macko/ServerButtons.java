package com.macko;

import javax.swing.*;
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
                StringBuilder content = new StringBuilder();

                for (Item item : items) {
                    content.append(item.getName()).append(";").append(item.getHealth()).append(";").append(item.getCondition()).append(";").append(item.getMana()).append(";").append(item.getStrength()).append(";").append(item.getEndurance()).append(";").append(item.getAgility()).append(";").append(item.getIntelligence()).append(";").append(item.getLuck()).append(";").append(item.getCharisma()).append(";").append(item.getMass()).append(";").append(item.getDamage()).append(";").append(item.getDefence()).append(";").append(item.getDescription()).append("\n");
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
                StringBuilder content = new StringBuilder();

                for (String name : names){
                    content.append(name).append(";");
                }
                content.append("\n");

                for (String surname : surnames){
                    content.append(surname).append(";");
                }
                content.append("\n");

                for (String nation : nations){
                    content.append(nation).append(";");
                }
                content.append("\n");

                for (String title : titles){
                    content.append(title).append(";");
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
                StringBuilder content= new StringBuilder();
                for (Profession profession : professions){
                    content.append(profession.getName()).append(";").append(profession.getDescription()).append(";");
                    for (int i=0; i<profession.getTalents().size(); i++){
                        content.append(profession.getTalents().get(i).getName()).append(";");
                    }
                    content.append("\n");
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
                StringBuilder content= new StringBuilder();
                for (Race race : races){
                    content.append(race.getName()).append(";").append(race.getDescription()).append(";").append(race.getStrength()).append(";").append(race.getEndurance()).append(";").append(race.getAgility()).append(";").append(race.getIntelligence()).append(";").append(race.getLuck()).append(";").append(race.getCharisma()).append(";\n");
                }
                writer.print(content);
                writer.close();
            }
        }
    }



    //---------- Export ----------//



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
                    if(tab.length==14 && !find_item(tab[0], items)) items.add(new Item(tab[0], Integer.parseInt(tab[1]), Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]), Integer.parseInt(tab[5]), Integer.parseInt(tab[6]), Integer.parseInt(tab[7]), Integer.parseInt(tab[8]), Integer.parseInt(tab[9]), Integer.parseInt(tab[10]), tab[11], tab[12], tab[13]));
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
                    if(tab.length==3 && !find_talent(tab[0], talents)) talents.add(new Talent(tab[0], Integer.parseInt(tab[1]), Integer.parseInt(tab[2])));
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

    void menu_import_evidences(Object source, JMenuItem button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game) throws Exception {
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Import Danych Osobowych");
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
                if(game.getpPersonCreator().isShowing()) button_person_creator(buttonToCheck, buttonToCheck, game.getPersons(), game);
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
                        if(!find_talent(tab[i], talents)) {
                            JOptionPane.showMessageDialog(null, "Profesja " + tab[0] + " zawiera talent " + tab[i] + " którego nie ma na liście talentów!", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
                            continue label;
                        }
                    }
                    List<Talent> list = new ArrayList<>();
                    for (int i=2; i<tab.length; i++){
                        list.add(talents.get(find_talent_index(tab[i], talents)));
                    }
                    professions.add(new Profession(tab[0], tab[1], list));
                }

                if (professions.size()>0) game.getmExportProfessions().setEnabled(true);

                Button buttonToCheck = new Button();
                if(game.getpProfessionCreator().isShowing()) button_profession_creator(buttonToCheck, buttonToCheck, talents, game.getProfessions(), game);
                if(game.getpPersonCreator().isShowing()) button_person_creator(buttonToCheck, buttonToCheck, game.getPersons(), game);
            }
        }
    }

    void menu_import_races(Object source, JMenuItem button, List<Race> races, Server game) throws Exception {
        if(source==button)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Import Ras");
            fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
            int selectFile = fileChooser.showOpenDialog(null);

            if(selectFile == JFileChooser.APPROVE_OPTION) {
                File fileWithData = fileChooser.getSelectedFile();
                Scanner scanner = new Scanner(fileWithData);
                while(scanner.hasNextLine()){
                    String[] tab;
                    tab = scanner.nextLine().split(";");
                    races.add(new Race(tab[0], tab[1], Integer.parseInt(tab[2]), Integer.parseInt(tab[3]), Integer.parseInt(tab[4]), Integer.parseInt(tab[5]), Integer.parseInt(tab[6]), Integer.parseInt(tab[7])));
                }

                if (races.size()>0) game.getmExportRaces().setEnabled(true);

                Button buttonToCheck = new Button();
                if(game.getpRaceCreator().isShowing()) button_race_creator(buttonToCheck, buttonToCheck, races, game);
                if(game.getpPersonCreator().isShowing()) button_person_creator(buttonToCheck, buttonToCheck, game.getPersons(), game);
            }
        }
    }



    //---------- Import ----------//



    void button_talent_creator(Object source, Button button, List<Talent> talents, Server game){
        if(source==button)
        {
            game.getContentPane().removeAll();
            game.getTfNameTalent().setText("");
            game.getCbStatistic().setSelectedIndex(0);
            game.getTaTalent().setText("");
            Collections.sort(talents);

            for (Talent talent : talents)
            {
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

            game.getpTalentCreator().add(game.getbBackLobby());
            game.add(game.getpTalentCreator());
            refresh(game);
        }
    }

    void button_add_talent(Object source, Button button, List<Talent> talents, Server game){
        if(source==button)
        {
            if(!game.getTfNameTalent().getText().equals("") && !find_talent(game.getTfNameTalent().getText(), talents)){
                if (game.getCbStatistic().getSelectedIndex()!=0)
                {
                    talents.add(new Talent(game.getTfNameTalent().getText(), 0, game.getCbStatistic().getSelectedIndex()-1));

                    button_talent_creator(button, button, talents, game);
                    game.getmExportTalents().setEnabled(true);
                    game.getmImportProfessions().setEnabled(true);
                    refresh(game);
                } else {
                    JOptionPane.showMessageDialog(null, "Nie wybrano statystyki.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nie wpisano nazwy talentu lub taka nazwa już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }



    //---------- Talent creator ----------//



    void button_item_creator(Object source, Button button, List<Item> items, Server game){
        if(source==button)
        {
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
        if(source==button)
        {
           if (!game.getTfItemValues().get(0).getText().equals("") && !find_item(game.getTfItemValues().get(0).getText(), items)){


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
               JOptionPane.showMessageDialog(null, "Nie podano nazwy przedmiotu lub taka nazwa już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
           }
        }
    }



    //---------- Item creator ----------//



    void button_evidence_creator(Object source, Button button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game){
        if(source==button)
        {
            game.getContentPane().removeAll();

            game.getTaName().setText("");
            Collections.sort(names);
            for (String name : names) game.getTaName().setText(game.getTaName().getText() + name + "\n");

            game.getTaSurname().setText("");
            Collections.sort(surnames);
            for (String surname : surnames) game.getTaSurname().setText(game.getTaSurname().getText() + surname + "\n");

            game.getTaNation().setText("");
            Collections.sort(nations);
            for (String nation : nations) game.getTaNation().setText(game.getTaNation().getText() + nation + "\n");

            game.getTaTitle().setText("");
            Collections.sort(titles);
            for (String title : titles) game.getTaTitle().setText(game.getTaTitle().getText() + title + "\n");

            for (int i=0; i<game.getTfEvidences().size(); i++) game.getTfEvidences().get(i).setText("");

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
                button_evidence_creator(button, button, names, surnames, nations, titles, game);
                game.getmExportEvidences().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podano imienia lub takie imię już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void button_add_surname(Object source, Button button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game){
        if(source==button)
        {
            if(!game.getTfEvidences().get(1).getText().equals("") && !surnames.contains(game.getTfEvidences().get(1).getText())){
                surnames.add(game.getTfEvidences().get(1).getText());
                button_evidence_creator(button, button, names, surnames, nations, titles, game);
                game.getmExportEvidences().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podano nazwiska lub takie nazwisko już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void button_add_nation(Object source, Button button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game){
        if(source==button)
        {
            if(!game.getTfEvidences().get(2).getText().equals("") && !nations.contains(game.getTfEvidences().get(2).getText())){
                nations.add(game.getTfEvidences().get(2).getText());
                button_evidence_creator(button, button, names, surnames, nations, titles, game);
                game.getmExportEvidences().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podano państwa lub takie państwo już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void button_add_title(Object source, Button button, List<String> names, List<String> surnames, List<String> nations, List<String> titles, Server game){
        if(source==button)
        {
            if(!game.getTfEvidences().get(3).getText().equals("") && !titles.contains(game.getTfEvidences().get(3).getText())){
                titles.add(game.getTfEvidences().get(3).getText());
                button_evidence_creator(button, button, names, surnames, nations, titles, game);
                game.getmExportEvidences().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podano tytułu lub taki tytuł już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
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



    //---------- Evidence creator ----------//



    void button_profession_creator(Object source, Button button, List<Talent> talents, List<Profession> professions, Server game){
        if(source==button)
        {
            int counter=0;
            Collections.sort(professions);
            for (Profession profession : professions)
            {
                game.getlProfessions().add(new JLabel());
                game.getlProfessions().get(counter).setText(profession.getName());
                game.getlProfessions().get(counter).setBorder(new EtchedBorder());

                StringBuilder info= new StringBuilder("<html>");
                String newLine = "<br/>";
                for (int i=0; i<profession.getTalents().size(); i++){
                    info.append(profession.getTalents().get(i).getName()).append(newLine);
                }
                if(!profession.getDescription().equals("") && !profession.getDescription().equals(" ")) info.append("Opis: ").append(profession.getDescription());
                info.append("</html>");
                game.getlProfessions().get(counter).setToolTipText(info.toString());
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
            if (!game.getTfProfessionName().getText().equals("") && !find_profession(game.getTfProfessionName().getText(), professions)){

                List<Talent> list = new ArrayList<>();
                for(int i=0; i<game.getCbProfessionTalents().size(); i++){
                    if(game.getCbProfessionTalents().get(i).getSelectedIndex()!=0 && !find_talent(talents.get(game.getCbProfessionTalents().get(i).getSelectedIndex()-1).getName(), list))
                        list.add(talents.get(game.getCbProfessionTalents().get(i).getSelectedIndex()-1));
                }

                professions.add(new Profession(game.getTfProfessionName().getText(), game.getTfProfessionDescription().getText(), list));

                game.getmExportProfessions().setEnabled(true);
                Button buttonToCheck = new Button();
                button_profession_creator(buttonToCheck, buttonToCheck, talents, professions, game);
                refresh(game);
            } else {
                JOptionPane.showMessageDialog(null, "Nie podano nazwy profesji lub taka nazwa już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }



    //---------- Profession creator ----------//



    void button_race_creator(Object source, Button button, List<Race> races, Server game){
        if(source==button)
        {

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
        if(source==button)
        {
            if (!game.getTfRaceValues().get(0).getText().equals("") && !find_race(game.getTfRaceValues().get(0).getText(), races))
            {
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
                JOptionPane.showMessageDialog(null, "Nie podano nazwy rasy lub taka nazwa już istnieje.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }



    //---------- Race creator ----------//



    void button_person_creator(Object source, Button button, List<Person> persons, Server game){
        if(source==button)
        {
            if(game.getProfessions().size()>0 && game.getNames().size()>0 && game.getSurnames().size()>0 && game.getNations().size()>0 && game.getTitles().size()>0 && game.getRaces().size()>0) game.getbAddPerson().setEnabled(true);
            if(persons.size()>0){
                game.getbSelectPerson().setEnabled(true);
                game.getbEditPerson().setEnabled(true);
            }

            game.getTaDescription().setText("");
            game.getTaEvidences().setText("");
            game.getTaValues().setText("");
            game.getTaStatistics().setText("");
            game.getlEquipment().clear();
            game.getpPersonItems().removeAll();
            game.getlTalents().clear();
            game.getpPersonTalents().removeAll();

            game.getpPersonCreator().remove(game.getCbPerson());
            JComboBox comboBox = new JComboBox();
            comboBox.setBorder(new TitledBorder(new EtchedBorder(), "Postać"));
            comboBox.addItem("-----");
            comboBox.setBounds(game.getCbPerson().getBounds());
            for (Person person : persons) comboBox.addItem(person.getName());
            game.setCbPerson(comboBox);
            game.getCbPerson().setSelectedIndex(0);
            game.getpPersonCreator().add(game.getCbPerson());

            game.getContentPane().removeAll();
            game.getpPersonCreator().add(game.getbBackLobby());
            game.add(game.getpPersonCreator());
            refresh(game);
        }
    }

    void button_add_person(Object source, Button button, List<Person> persons, Server game){
        if(source==button)
        {
            int counter = 0;
            String name = game.getNames().get(random_value(game.getNames().size()))+" "+game.getSurnames().get(random_value(game.getSurnames().size()));
            while (find_person(name, persons)){
                if (counter==100){
                    JOptionPane.showMessageDialog(null, "Nie udało się wylosować unikalnej nazwy, powiększ listę imion/nazwisk i spróbuj jeszcze raz.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                name = game.getNames().get(random_value(game.getNames().size()))+" "+game.getSurnames().get(random_value(game.getSurnames().size()));
                counter++;
            }
            persons.add(new Person(name, game.getTitles().get(random_value(game.getTitles().size())), game.getNations().get(random_value(game.getNations().size())), game.getProfessions().get(random_value(game.getProfessions().size())), game.getRaces().get(random_value(game.getRaces().size()))));
            persons.get(persons.size()-1).update_statistics();
            persons.get(persons.size()-1).update_values(persons.get(persons.size()-1).getProfession().getTalents());
            Collections.sort(persons);

            Button buttonToCheck = new Button();
            button_person_creator(buttonToCheck, buttonToCheck, persons, game);
            refresh(game);
        }
    }

    void button_select_person(Object source, Button button, List<Person> persons, Server game){
        if(source==button)
        {
            if(game.getCbPerson().getSelectedIndex()!=0)
            {
                game.getTaDescription().setText("");
                game.getTaEvidences().setText("");
                game.getTaValues().setText("");
                game.getTaStatistics().setText("");
                game.getlEquipment().clear();
                game.getpPersonItems().removeAll();
                game.getlTalents().clear();
                game.getpPersonTalents().removeAll();

                Person person = persons.get(game.getCbPerson().getSelectedIndex() - 1);

                String newLine = "<br/>";
                String info = person.getName() + "\n"
                        + "Profesja: " + person.getProfession().getName() + "\n"
                        + "Rasa: " + person.getRace().getName() + "\n"
                        + "Wiek: " + person.getAge() + "\n"
                        + "Tytuł: " + person.getTitle() + "\n"
                        + "Nacja: " + person.getNation();
                game.getTaEvidences().setText(info);


                info = "Siła: " + person.getStrength() + "\n"
                        + "Wytrzymałość: " + person.getEndurance() + "\n"
                        + "Zręczność: " + person.getAgility() + "\n"
                        + "Inteligencja: " + person.getIntelligence() + "\n"
                        + "Szczęście: " + person.getLuck() + "\n"
                        + "Charyzma: " + person.getCharisma();
                game.getTaStatistics().setText(info);


                info = "Zdrowie: " + person.getHealth() + "/" + person.getHealthMax() + "\n"
                        + "Mana: " + person.getMana() + "/" + person.getManaMax() + "\n"
                        + "Kondycja: " + person.getCondition() + "/" + person.getConditionMax() + "\n"
                        + "Los: " + person.getFate() + "\n"
                        + "Złoto: " + person.getGold() + "\n"
                        + "Udźwig: " + person.getCapacity();
                game.getTaValues().setText(info);


                info = "Rasa: " + person.getRace().getDescription() + "\n"
                        + "Profesja: " + person.getProfession().getDescription();
                game.getTaDescription().setText(info);

                for (int i = 0; i < person.getTalents().size(); i++) {
                    game.getlTalents().add(new JLabel());
                    game.getlTalents().get(i).setText(person.getTalents().get(i).getName());
                    game.getlTalents().get(i).setBorder(new EtchedBorder());

                    int statistic, level = 3, chance;
                    info = "<html>";
                    switch (person.getTalents().get(i).getStatistic()) {
                        case 0 -> {
                            info += "Siła";
                            statistic = person.getStrength();
                        }
                        case 1 -> {
                            info += "Wytrzymałość";
                            statistic = person.getEndurance();
                        }
                        case 2 -> {
                            info += "Zręczność";
                            statistic = person.getAgility();
                        }
                        case 3 -> {
                            info += "Inteligencja";
                            statistic = person.getIntelligence();
                        }
                        case 4 -> {
                            info += "Szczęście";
                            statistic = person.getLuck();
                        }
                        default -> {
                            info += "Charyzma";
                            statistic = person.getCharisma();
                        }
                    }

                    chance = (statistic * level + person.getTalents().get(i).getTier() * 5);
                    if (chance > 100) chance = 100;
                    info += newLine + "Szansa: " + chance + "%" + newLine + "Poziom: ";

                    switch (person.getTalents().get(i).getTier()) {
                        case 1 -> info += "Podstawowy";
                        case 2 -> info += "Zaawansowany";
                        case 3 -> info += "Ekspercki";
                        case 4 -> info += "Legendarny";
                        default -> info += "Nieznany";
                    }

                    info += "</html>";
                    game.getlTalents().get(i).setToolTipText(info);

                    game.getpPersonTalents().add(game.getlTalents().get(i));
                }

                for (int i = 0; i < person.getItems().size(); i++) {
                    Item item = person.getItems().get(i);

                    game.getlItems().add(new JLabel());
                    game.getlItems().get(i).setText(item.getName());
                    game.getlItems().get(i).setBorder(new EtchedBorder());

                    info = "<html>";
                    if (!item.getDamage().equals("") && !item.getDamage().equals(" "))
                        info += "Obrażenia: " + item.getDamage() + newLine;
                    if (!item.getDefence().equals("") && !item.getDefence().equals(" "))
                        info += "Obrona: " + item.getDefence() + newLine;
                    if (item.getHealth() != 0) info += "Zdrowie: " + item.getHealth() + newLine;
                    if (item.getCondition() != 0) info += "Kondycja: " + item.getCondition() + newLine;
                    if (item.getMana() != 0) info += "Mana: " + item.getMana() + newLine;
                    if (item.getStrength() != 0) info += "Siła: " + item.getStrength() + newLine;
                    if (item.getEndurance() != 0) info += "Wytrzymałość: " + item.getEndurance() + newLine;
                    if (item.getAgility() != 0) info += "Zręczność: " + item.getAgility() + newLine;
                    if (item.getIntelligence() != 0) info += "Inteligencja: " + item.getIntelligence() + newLine;
                    if (item.getLuck() != 0) info += "Szczęście: " + item.getLuck() + newLine;
                    if (item.getCharisma() != 0) info += "Charyzma: " + item.getCharisma() + newLine;
                    info += "Masa: " + item.getMass() + newLine;
                    if (!item.getDescription().equals("") && !item.getDescription().equals(" "))
                        info += "Opis: " + item.getDescription() + newLine;
                    info += "</html>";

                    game.getlItems().get(i).setToolTipText(info);
                    game.getpPersonItems().add(game.getlItems().get(i));
                }
                refresh(game);
            } else {
                JOptionPane.showMessageDialog(null, "Nie wybrano postaci w polu Postać", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void button_edit_person(Object source, Button button, List<Person> persons, Server game){
        if(source==button)
        {
            if(game.getCbPerson().getSelectedIndex()!=0)
            {
                Person person = persons.get(game.getCbPerson().getSelectedIndex()-1);

                game.getCbProfession().removeAllItems();
                for (Profession profession : game.getProfessions()){
                    game.getCbProfession().addItem(profession.getName());
                }
                game.getCbProfession().setSelectedIndex(find_profession_index(person.getProfession().getName(), game.getProfessions()));


                game.getCbRace().removeAllItems();
                for (Race race : game.getRaces()){
                    game.getCbRace().addItem(race.getName());
                }
                game.getCbRace().setSelectedIndex(find_race_index(person.getRace().getName(), game.getRaces()));


                game.getTfPersonEditValues().get(0).setText( person.getTitle() );
                game.getTfPersonEditValues().get(1).setText( person.getNation() );
                game.getTfPersonEditValues().get(2).setText( String.valueOf(person.getStrength()) );
                game.getTfPersonEditValues().get(3).setText( String.valueOf(person.getEndurance()) );
                game.getTfPersonEditValues().get(4).setText( String.valueOf(person.getAgility()) );
                game.getTfPersonEditValues().get(5).setText( String.valueOf(person.getIntelligence()) );
                game.getTfPersonEditValues().get(6).setText( String.valueOf(person.getLuck()) );
                game.getTfPersonEditValues().get(7).setText( String.valueOf(person.getCharisma()) );
                game.getTfPersonEditValues().get(8).setText( String.valueOf(person.getHealth()) );
                game.getTfPersonEditValues().get(9).setText( String.valueOf(person.getHealthMax()) );
                game.getTfPersonEditValues().get(10).setText( String.valueOf(person.getMana()) );
                game.getTfPersonEditValues().get(11).setText( String.valueOf(person.getManaMax()) );
                game.getTfPersonEditValues().get(12).setText( String.valueOf(person.getCondition()) );
                game.getTfPersonEditValues().get(13).setText( String.valueOf(person.getConditionMax()) );
                game.getTfPersonEditValues().get(14).setText( String.valueOf(person.getAge()) );
                game.getTfPersonEditValues().get(15).setText( String.valueOf(person.getFate()) );
                game.getTfPersonEditValues().get(16).setText( String.valueOf(person.getGold()) );
                game.getTfPersonEditValues().get(17).setText( String.valueOf(person.getCapacity()) );
                game.getTfPersonEditValues().get(18).setText( String.valueOf(person.getStatistic()) );
                game.getTfPersonEditValues().get(19).setText( String.valueOf(person.getTalent()) );
                game.getTfPersonEditValues().get(20).setText( String.valueOf(person.getDefence()) );
                game.getTfPersonEditValues().get(21).setText( String.valueOf(person.getDamage()) );


                game.getCbEditTalents().clear();
                game.getpPersonEditTalents().removeAll();
                for (int i=0; i<person.getTalents().size(); i++)
                {
                    JComboBox comboBox = new JComboBox();
                    comboBox.setBorder(new TitledBorder(new EtchedBorder(), "Talent"));
                    comboBox.addItem("-----");
                    for (Talent talent : game.getTalents()){
                        comboBox.addItem(talent.getName());
                    }
                    comboBox.addActionListener(game);
                    comboBox.setSelectedIndex(find_talent_index(person.getTalents().get(i).getName(), game.getTalents())+1);
                    game.getCbEditTalents().add(comboBox);

                    game.getpPersonEditTalents().add(game.getCbEditTalents().get(i));
                }

                JComboBox comboBox = new JComboBox();
                comboBox.setBorder(new TitledBorder(new EtchedBorder(), "Talent"));
                comboBox.addItem("-----");
                for (Talent talent : game.getTalents()){
                    comboBox.addItem(talent.getName());
                }
                comboBox.addActionListener(game);
                comboBox.setSelectedIndex(0);
                game.getCbEditTalents().add(comboBox);
                game.getpPersonEditTalents().add(game.getCbEditTalents().get(game.getCbEditTalents().size()-1));

                game.getContentPane().removeAll();
                game.add(game.getpPersonEdit());
                refresh(game);
            } else {
                JOptionPane.showMessageDialog(null, "Nie wybrano postaci w polu Postać", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    void combo_box_edit_talent(Object source, JComboBox button, List<Talent> talents, Server game){
        if(source==button && button.getSelectedIndex()!=0)
        {
            JComboBox comboBox = new JComboBox();
            comboBox.setBorder(new TitledBorder(new EtchedBorder(), "Talent"));
            comboBox.addItem("-----");
            for (Talent talent : talents){
                comboBox.addItem(talent.getName());
            }

            game.getCbEditTalents().add(comboBox);
            game.getCbEditTalents().get(game.getCbEditTalents().size()-1).addActionListener(game);
            game.getpPersonEditTalents().add(game.getCbEditTalents().get(game.getCbEditTalents().size()-1));
            refresh(game);
        }
    }

    void combo_box_edit_item(Object source, JComboBox button, List<Item> items, Server game){
        if(source==button && button.getSelectedIndex()!=0)
        {
            JComboBox comboBox = new JComboBox();
            comboBox.setBorder(new TitledBorder(new EtchedBorder(), "Przedmiot"));
            comboBox.addItem("-----");
            for (Item item : items){
                comboBox.addItem(item.getName());
            }

            game.getCbEditItems().add(comboBox);
            game.getCbEditItems().get(game.getCbEditItems().size()-1).addActionListener(game);
            game.getpPersonEditTalents().add(game.getCbEditItems().get(game.getCbEditItems().size()-1));
            refresh(game);
        }
    }

    void button_confirm_edit(Object source, Button button, List<Person> persons, Server game){
        if(source==button)
        {
            for(int i=2; i<game.getTfPersonEditValues().size()-3; i++)
            {
                if (!game.getTfPersonEditValues().get(i).getText().matches("-?[0-9]+") && !game.getTfPersonEditValues().get(i).getText().equals("")){
                    TitledBorder border = (TitledBorder)game.getTfPersonEditValues().get(i).getBorder();
                    JOptionPane.showMessageDialog(null, "Wartość w polu " + border.getTitle() + " musi być liczbą całkowitą.", "Komunikat" , JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }

            Person person = persons.get(game.getCbPerson().getSelectedIndex()-1);
            refresh(game);
        }
    }

    void button_cancel_edit(Object source, Button button, List<Person> persons, Server game){
        if(source==button) button_person_creator(source, button, persons, game);
    }



    //---------- Person creator ----------//



    void button_back_lobby(Object source, Button button, Server game){
        if(source==button) {
            game.getContentPane().removeAll();
            game.add(game.getpStart());
            refresh(game);
        }
    }

    void refresh(Server game){
        game.setVisible(false);
        game.setVisible(true);
    }

    boolean find_talent(String name, List<Talent> list){
        for (Talent talent : list) if (talent.getName().equalsIgnoreCase(name)) return true;
        return false;
    }

    int find_talent_index(String name, List<Talent> list){
        for (int i=0; i<list.size(); i++) if(list.get(i).getName().equalsIgnoreCase(name)) return i;
        return 0;
    }

    boolean find_item(String name, List<Item> list){
        for (Item item : list) if(item.getName().equalsIgnoreCase(name)) return true;
        return false;
    }

    boolean find_profession(String name, List<Profession> list){
        for (Profession profession : list) if(profession.getName().equalsIgnoreCase(name)) return true;
        return false;
    }

    int find_profession_index(String name, List<Profession> list){
        for (int i=0; i<list.size(); i++) if(list.get(i).getName().equalsIgnoreCase(name)) return i;
        return 0;
    }

    boolean find_race(String name, List<Race> list){
        for (Race race : list) {
            if(race.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    int find_race_index(String name, List<Race> list){
        for (int i=0; i<list.size(); i++) if(list.get(i).getName().equalsIgnoreCase(name)) return i;
        return 0;
    }

    boolean find_person(String name, List<Person> list){
        for (Person person : list) {
            if(person.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    int random_value(int value){
        Random random = new Random();
        return random.nextInt(value);
    }
}
