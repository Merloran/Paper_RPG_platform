package com.macko;

        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.io.DataOutputStream;
        import java.io.OutputStream;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.List;

public class ConnectionListener extends Thread {
    private List<Socket> Sockets;
    private List<MessageListenerServer> Inputs;
    private List<DataOutputStream> Outputs;
    private ServerSocket Server;
    private WindowServer Window;

    ConnectionListener
            (ServerSocket Server, List<Socket> Sockets, List<MessageListenerServer> Inputs, List<DataOutputStream> Outputs, WindowServer Window){
        this.Sockets = Sockets;
        this.Server = Server;
        this.Inputs = Inputs;
        this.Outputs = Outputs;
        this.Window = Window;
    }

    void listener() throws Exception{
        for (int i = 0 ; i<Sockets.size(); i++){
            Sockets.set(i, Server.accept());
            Inputs.add(new MessageListenerServer(Sockets.get(i), "Klient"+(i+1)));
            Inputs.get(Inputs.size()-1).start();
            Outputs.add(new DataOutputStream(Sockets.get(i).getOutputStream()));
            System.out.println("Klient" + (i+1) + " się połączył!");
            Outputs.get(Outputs.size()-1).writeInt(i);
            Button button = Window.getbPlayer(i);
            button.setVisible(true);
            Window.setbPlayer(button, i);
        }

    }

    @Override
    public void run() {
        try {
            listener();
        } catch (Exception e) {
            //ignore
        }
    }
    public List<DataOutputStream> getOutputs() {
        return Outputs;
    }
    public void setOutputs(List<DataOutputStream> outputs) {
        Outputs = outputs;
    }
}
