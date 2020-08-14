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
            buttons.menu_import_item(source, getmImportItems(), items, game);
            buttons.menu_import_talents(source, getmImportTalents(), talents, game);

            buttons.button_start(source, getbStart(), connection, clients, inputs, game);
            buttons.button_back_lobby(source, getbBackLobby(), game);
            buttons.button_talent_creator(source, getbTalentCreator(), talents, game);
            buttons.button_add_talent(source, getbAddTalent(), talents, game);
            buttons.button_item_creator(source, getbItemCreator(), items, game);
            buttons.button_add_item(source, getbAddItem(), items, game);
        } catch (Exception exception) {
            //ignore
        }
    }
}
