package com.macko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class WindowClient extends JFrame implements ActionListener {
    @Override
    public abstract void actionPerformed(ActionEvent e);
    private int windowWidth=900, windowHeight=700, fontSize=15;
    private Button bJoin;

    WindowClient(){
        setSize(windowWidth, windowHeight);
        setTitle("Platform RPG-Client");
        setLayout(null);

        bJoin = createButton(9/20f, 19/40f, 1/10f, 1/20f, "Oczekiwanie");
        add(bJoin);

    }

    Button createButton(float x, float y, float w, float h, String name){
        Button button = new Button(name);
        button.setBounds((int)(windowWidth * x), (int)(windowHeight * y), (int)(windowWidth * w), (int)(windowHeight * h));
        button.setFont(new Font("Arial", Font.PLAIN, fontSize));
        button.setEnabled(false);
        button.addActionListener(this);
        return button;
    }

    public Button getbJoin() {
        return bJoin;
    }

    public void setbJoin(Button bJoin) {
        this.bJoin = bJoin;
    }
}
