package com.macko;

import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client extends WindowClient{
    private static final int PORT = 7888;

    public static void main(String[] args) throws Exception{
        Client Game = new Client();
        Game.setVisible(true);
        Game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Game.setLocationRelativeTo(null);

        Socket Client = new Socket("localhost", PORT);
        DataOutputStream out = new DataOutputStream(Client.getOutputStream());

        MessageListenerClient listener = new MessageListenerClient(Client, "Server", Game);
        listener.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
