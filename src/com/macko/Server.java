package com.macko;

import javax.naming.event.ObjectChangeListener;
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
    private static List<Talent> talents = new ArrayList<>();

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
            buttons.menu_export_item(source, getmExportItems(), game);
            buttons.menu_export_talents(source, getmExportTalents(), talents);
            buttons.menu_import_item(source, getmImportItems(), game);
            buttons.menu_import_talents(source, game, getmImportTalents(), talents);

            buttons.button_start(source, game, getbStart(), connection, clients, inputs);
            buttons.button_back_lobby(source, game, getbBackLobby());
            buttons.button_talent_creator(source, getbTalentCreator(), game, talents);
            buttons.button_add_talent(source, getbAddTalent(), game, talents);
        } catch (Exception exception) {
            //ignore
        }
    }
}
