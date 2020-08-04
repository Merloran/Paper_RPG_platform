package com.macko;

        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.io.DataOutputStream;
        import java.io.OutputStream;
        import java.net.ServerSocket;
        import java.net.Socket;
        import java.util.List;

public class ConnectionListener extends Thread {
    private List<Socket> sockets;
    private List<MessageListenerServer> inputs;
    private List<DataOutputStream> outputs;
    private ServerSocket server;
    private WindowServer window;

    ConnectionListener(ServerSocket server, List<Socket> sockets, List<MessageListenerServer> inputs, List<DataOutputStream> outputs, WindowServer window){
        this.sockets = sockets;
        this.server = server;
        this.inputs = inputs;
        this.outputs = outputs;
        this.window = window;
    }

    void listener() throws Exception{
        for (int i = 0 ; i<sockets.size(); i++){
            sockets.set(i, server.accept());
            inputs.add(new MessageListenerServer(sockets.get(i), "Klient"+(i+1)));
            inputs.get(inputs.size()-1).start();
            outputs.add(new DataOutputStream(sockets.get(i).getOutputStream()));
            System.out.println("Klient" + (i+1) + " się połączył!");
            outputs.get(outputs.size()-1).writeInt(i);
            Button button = window.getbPlayer(i);
            button.setVisible(true);
            window.setbPlayer(button, i);
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
        return outputs;
    }
    public void setOutputs(List<DataOutputStream> outputs) {
        outputs = outputs;
    }
}
