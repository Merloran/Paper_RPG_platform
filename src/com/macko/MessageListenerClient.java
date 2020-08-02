package com.macko;

import java.awt.*;
import java.io.DataInputStream;
import java.net.Socket;

public class MessageListenerClient extends Thread{
    private Socket Client;
    private String message, name;
    private boolean exit=false;
    private DataInputStream in;
    private WindowClient Window;
    private static int INDEX=-1;

    MessageListenerClient(Socket Client, String name, WindowClient Window) {
        this.Client = Client;
        this.name = name;
        this.Window = Window;
    }

    public void listener() throws Exception{
        in = new DataInputStream (Client.getInputStream());     //Receive variables
        while (true)
        {
            INDEX = in.readInt();
            message = in.readUTF();
            switch (message){
                case "Start":
                    Button button = Window.getbJoin();
                    button.setLabel("Dołącz");
                    button.setEnabled(true);
                    Window.setbJoin(button);
                    break;
            }
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
