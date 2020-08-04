package com.macko;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class WindowServer extends JFrame implements ActionListener {
    @Override
    public abstract void actionPerformed(ActionEvent e);
    private final int windowWidth=900, windowHeight=700, fontSize=15;
    private Button bStart, bTalentCreator, bItemCreator, bAddTalent;
    private JPanel pStart, pTalentCreator;
    private JTextArea taTalent;
    private JTextField tfName;
    private JComboBox cbStatistic;
    private JMenuBar menu = new JMenuBar();
    private JMenu mfile = new JMenu("Plik");
    private JMenu mExport = new JMenu("Eksportuj"), mImport = new JMenu("Importuj");
    private JMenuItem mImportTalents = new JMenuItem("Talenty"), mImportItems = new JMenuItem("Przedmioty"), mImportPerson = new JMenuItem("Postać");
    private JMenuItem mExportTalents = new JMenuItem("Talenty"), mExportItems = new JMenuItem("Przedmioty"), mExportPerson = new JMenuItem("Postać");
    private List<Button> bPlayers = new ArrayList<>();
    private JFileChooser file = new JFileChooser();
    private File ftalent = new File("none.txt"), fitem = new File("none.txt");
    static final int PLAYERS = 5;

    WindowServer(){
        setSize(windowWidth, windowHeight);
        setTitle("Platform RPG-Server");
        setLayout(null);

        menu.add(mfile);
        mExportItems.addActionListener(this);
        mExportItems.setEnabled(false);
        mExportTalents.addActionListener(this);
        mExportTalents.setEnabled(false);
        mImportItems.addActionListener(this);
        mExport.add(mExportTalents);
        mExport.add(mExportItems);
        mExport.add(mExportPerson);
        mImport.add(mImportTalents);
        mImport.add(mImportItems);
        mImport.add(mImportPerson);
        mfile.add(mExport);
        mfile.add(mImport);
        setJMenuBar(menu);

        pStart = new JPanel();
        pStart.setLayout(null);
        pStart.setBounds(0, 0, windowWidth, windowHeight);

        bPlayers.add(createButton(1/10f, 1/10f, 1/10f, 1/20f, "Gracz" + (bPlayers.size()+1)));
        bPlayers.get(bPlayers.size()-1).setVisible(false);
        bPlayers.get(bPlayers.size()-1).setEnabled(false);
        pStart.add(bPlayers.get(bPlayers.size()-1));

        bPlayers.add(createButton(1/10f, 1/4f, 1/10f, 1/20f, "Gracz" + (bPlayers.size()+1)));
        bPlayers.get(bPlayers.size()-1).setVisible(false);
        bPlayers.get(bPlayers.size()-1).setEnabled(false);
        pStart.add(bPlayers.get(bPlayers.size()-1));

        bPlayers.add(createButton(1/10f, 2/5f, 1/10f, 1/20f, "Gracz" + (bPlayers.size()+1)));
        bPlayers.get(bPlayers.size()-1).setVisible(false);
        bPlayers.get(bPlayers.size()-1).setEnabled(false);
        pStart.add(bPlayers.get(bPlayers.size()-1));

        bPlayers.add(createButton(1/10f, 11/20f, 1/10f, 1/20f, "Gracz" + (bPlayers.size()+1)));
        bPlayers.get(bPlayers.size()-1).setVisible(false);
        bPlayers.get(bPlayers.size()-1).setEnabled(false);
        pStart.add(bPlayers.get(bPlayers.size()-1));

        bStart = createButton(1/2f, 17/36f, 1/8f, 1/18f, "Start");
        pStart.add(bStart);

        bTalentCreator = createButton(3/4f, 1/8f, 1/8f, 1/18f, "Nowy Talent");
        pStart.add(bTalentCreator);

        bItemCreator = createButton(3/4f, 2/8f, 1/8f, 1/18f, "Nowy Przedmiot");
        pStart.add(bItemCreator);

        add(pStart);

        pTalentCreator = new JPanel();
        pTalentCreator.setLayout(null);
        pTalentCreator.setBounds(0, 0, windowWidth, windowHeight);

        taTalent = new JTextArea();
        taTalent.setBounds((int)(windowWidth*(1/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/2f)), (int)(windowHeight*(5/6f)));
        taTalent.setEditable(false);
        pTalentCreator.add(taTalent);

        tfName = new JTextField();
        tfName.setBounds((int)(windowWidth*(9/16f)), (int)(windowHeight*(1/2f)), (int)(windowWidth*(1/8f)), (int)(windowHeight*(1/18f)));
        pTalentCreator.add(tfName);

        String[] statistics = {"Siła", "Wytrzymałość", "Zręczność", "Inteligencja", "Szczęście", "Charyzma"};
        cbStatistic = new JComboBox(statistics);
        cbStatistic.setBounds((int)(windowWidth*(3/4f)), (int)(windowHeight*(1/2f)), (int)(windowWidth*(1/8f)), (int)(windowHeight*(1/18f)));
        cbStatistic.setSelectedIndex(0);
        pTalentCreator.add(cbStatistic);

        bAddTalent = createButton(11/16f,5/8f, 1/8f, 1/18f, "Dodaj");
        pTalentCreator.add(bAddTalent);
    }

    Button createButton(float x, float y, float w, float h, String name){
        Button button = new Button(name);
        button.setBounds((int)(windowWidth * x), (int)(windowHeight * y), (int)(windowWidth * w), (int)(windowHeight * h));
        button.setFont(new Font("Arial", Font.PLAIN, fontSize));
        button.addActionListener(this);
        return button;
    }

    public Button getbAddTalent() {
        return bAddTalent;
    }

    public void setbAddTalent(Button bAddTalent) {
        this.bAddTalent = bAddTalent;
    }

    public JComboBox getCbStatistic() {
        return cbStatistic;
    }

    public void setCbStatistic(JComboBox cbStatistic) {
        this.cbStatistic = cbStatistic;
    }

    public JTextField getTfName() {
        return tfName;
    }

    public void setTfName(JTextField tfName) {
        this.tfName = tfName;
    }

    public JPanel getpStart() {
        return pStart;
    }

    public void setpStart(JPanel pStart) {
        this.pStart = pStart;
    }

    public JPanel getpTalentCreator() {
        return pTalentCreator;
    }

    public void setpTalentCreator(JPanel pTalentCreator) {
        this.pTalentCreator = pTalentCreator;
    }

    public JTextArea getTaTalent() {
        return taTalent;
    }

    public void setTaTalent(JTextArea taTalent) {
        this.taTalent = taTalent;
    }

    public File getFtalent() {
        return ftalent;
    }

    public void setFtalent(File ftalent) {
        this.ftalent = ftalent;
    }

    public Button getbTalentCreator() {
        return bTalentCreator;
    }

    public void setbTalentCreator(Button bTalentCreator) {
        this.bTalentCreator = bTalentCreator;
    }

    public Button getbPlayer(int i) {
        return bPlayers.get(i);
    }

    public void setbPlayer(Button bPlayer, int i) {
        this.bPlayers.set(i, bPlayer);
    }

    public Button getbStart() {
        return bStart;
    }

    public void setbStart(Button bStart) {
        this.bStart = bStart;
    }

    public JFileChooser getFile() {
        return file;
    }

    public void setFile(JFileChooser file) {
        this.file = file;
    }

    public JMenuItem getmImportTalents() {
        return mImportTalents;
    }

    public void setmImportTalents(JMenuItem mImportTalents) {
        this.mImportTalents = mImportTalents;
    }

    public JMenuItem getmImportItems() {
        return mImportItems;
    }

    public void setmImportItems(JMenuItem mImportItems) {
        this.mImportItems = mImportItems;
    }

    public JMenuItem getmImportPerson() {
        return mImportPerson;
    }

    public void setmImportPerson(JMenuItem mImportPerson) {
        this.mImportPerson = mImportPerson;
    }

    public JMenuItem getmExportTalents() {
        return mExportTalents;
    }

    public void setmExportTalents(JMenuItem mExportTalents) {
        this.mExportTalents = mExportTalents;
    }

    public JMenuItem getmExportItems() {
        return mExportItems;
    }

    public void setmExportItems(JMenuItem mExportItems) {
        this.mExportItems = mExportItems;
    }

    public JMenuItem getmExportPerson() {
        return mExportPerson;
    }

    public void setmExportPerson(JMenuItem mExportPerson) {
        this.mExportPerson = mExportPerson;
    }
}
