package com.macko;

import java.io.DataInputStream;
import java.net.Socket;

public class MessageListenerServer extends Thread{
    private Socket Client;
    private String message, name;
    private boolean exit=false;
    private DataInputStream in;

    MessageListenerServer(Socket Client, String name) {
        this.Client = Client;
        this.name = name;
    }

    public void listener() throws Exception{
        in = new DataInputStream (Client.getInputStream());     //Receive variables
        while (true)
        {
            message = in.readUTF();
            System.out.println(name + ": " + message);
        }
    }

    @Override
    public void run() {
        try {
            listener();
        } catch (Exception e) {
            System.out.println(name + " się rozłączył.");
            exit=true;
        }
    }

    public boolean isExit() {
        return exit;
    }
}
