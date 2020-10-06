package com.macko;

import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends WindowServer{
    private static final int PORT = 7888;
    private ServerButtons buttons = new ServerButtons();
    private static Server game;
    private static ConnectionListener connection;
    private static List<Socket> clients = new ArrayList<>();
    private static List<MessageListenerServer> inputs = new ArrayList<>();
    private static List<DataOutputStream> outputs = new ArrayList<>();
    private List<Talent> talents = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Profession> professions = new ArrayList<>();
    private List<Race> races = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();

    private List<String> titles = new ArrayList<>();
    private List<String> nations = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<String> surnames = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        ServerSocket Server= new ServerSocket(PORT);

        for (int i =0; i<PLAYERS; i++) clients.add(new Socket());

        game = new Server();
        game.setVisible(true);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);

        connection = new ConnectionListener(Server, clients, inputs, outputs, game);
        connection.start();

    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        try {
            buttons.menu_export_item(source, getmExportItems(), items);
            buttons.menu_export_talents(source, getmExportTalents(), talents);
            buttons.menu_export_evidences(source, getmExportEvidences(), names, surnames, nations, titles);
            buttons.menu_export_professions(source, getmExportProfessions(), professions);
            buttons.menu_export_races(source, getmExportRaces(), races);

            buttons.menu_import_item(source, getmImportItems(), items, game);
            buttons.menu_import_talents(source, getmImportTalents(), talents, game);
            buttons.menu_import_evidences(source, getmImportEvidences(), names, surnames, nations, titles, game);
            buttons.menu_import_professions(source, getmImportProfessions(), talents, professions, game);
            buttons.menu_import_races(source, getmImportRaces(), races, game);

            buttons.button_start(source, getbStart(), connection, clients, inputs, game);
            buttons.button_back_lobby(source, getbBackLobby(), game);
            buttons.button_talent_creator(source, getbTalentCreator(), talents, game);
            buttons.button_add_talent(source, getbAddTalent(), talents, game);
            buttons.button_item_creator(source, getbItemCreator(), items, game);
            buttons.button_add_item(source, getbAddItem(), items, game);
            buttons.button_evidence_creator(source, getbEvidenceCreator(), names, surnames, nations, titles, game);
            buttons.button_add_name(source, getbAddName(), names, surnames, nations, titles, game);
            buttons.button_add_surname(source, getbAddSurname(), names, surnames, nations, titles, game);
            buttons.button_add_nation(source, getbAddNation(), names, surnames, nations, titles, game);
            buttons.button_add_title(source, getbAddTitle(), names, surnames, nations, titles, game);
            buttons.text_field_evidences(source, names, surnames, nations, titles, game);
            buttons.button_profession_creator(source, getbProfessionCreator(), talents, professions, game);
            buttons.combo_box_add(source, getCbProfessionTalents().get(getCbProfessionTalents().size()-1), talents, game);
            buttons.button_add_profession(source, getbAddProfession(), talents, professions, game);
            buttons.button_race_creator(source, getbRaceCreator(), races, game);
            buttons.button_add_race(source, getbAddRace(), races, game);
            buttons.button_person_creator(source, getbPersonCreator(), persons, game);
            buttons.button_add_person(source, getbAddPerson(), persons, game);
            buttons.button_select_person(source, getbSelectPerson(), persons, game);
            buttons.button_edit_person(source, getbEditPerson(), persons, game);
            buttons.combo_box_edit_talent(source, getCbEditTalents().get(getCbEditTalents().size()-1), talents, game);
            buttons.button_cancel_edit(source, getbCancelEdit(), persons, game);
            buttons.button_confirm_edit(source, getbConfirmEdit(), persons, game);
        } catch (Exception exception) {
            //ignore
        }
    }

    public List<Person> getPersons() {
        return persons;
    }

    public List<Profession> getProfessions() {
        return professions;
    }

    public List<Talent> getTalents() {
        return talents;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Race> getRaces() {
        return races;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getNations() {
        return nations;
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getSurnames() {
        return surnames;
    }
}
