package com.macko;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class WindowServer extends JFrame implements ActionListener {
    @Override
    public abstract void actionPerformed(ActionEvent e);
    private final int windowWidth=900, windowHeight=700, fontSize=15;
    private Button bStart, bTalentCreator, bItemCreator, bAddTalent, bBackLobby, bAddItem;
    private List<JLabel> lItems = new ArrayList<>();
    private JPanel pStart, pTalentCreator, pItemCreator, pItemBox, pItemList;
    private JTextArea taTalent;
    private JTextField tfNameTalent;
    private JComboBox cbStatistic;
    private List<JComboBox> cbItemValues = new ArrayList<>();
    private List<JTextField> tfItemValues = new ArrayList<>();
    private JScrollPane spTalent, spItem, spItemPanel;
    private JMenuBar menu = new JMenuBar();
    private JMenu mfile = new JMenu("Plik");
    private JMenu mExport = new JMenu("Eksportuj"), mImport = new JMenu("Importuj");
    private JMenuItem mImportTalents = new JMenuItem("Talenty"), mImportItems = new JMenuItem("Przedmioty"), mImportPerson = new JMenuItem("Postać");
    private JMenuItem mExportTalents = new JMenuItem("Talenty"), mExportItems = new JMenuItem("Przedmioty"), mExportPerson = new JMenuItem("Postać");
    private List<Button> bPlayers = new ArrayList<>();
    static final int PLAYERS = 4;

    WindowServer(){
        setSize(windowWidth, windowHeight);
        setTitle("Platform RPG-Server");
        setLayout(null);

        //---------- Window customization variables ----------//

        menu.add(mfile);
        mExportItems.addActionListener(this);
        mExportItems.setEnabled(false);
        mExportTalents.addActionListener(this);
        mExportTalents.setEnabled(false);

        mImportItems.addActionListener(this);
        mImportTalents.addActionListener(this);
        mExport.add(mExportTalents);
        mExport.add(mExportItems);
        mExport.add(mExportPerson);
        mImport.add(mImportTalents);
        mImport.add(mImportItems);
        mImport.add(mImportPerson);
        mfile.add(mExport);
        mfile.add(mImport);
        setJMenuBar(menu);

        //---------- Add menu bar ----------//

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

        //---------- Add lobby ----------//

        bBackLobby = createButton(27/32f, 5/6f, 1/8f, 1/18f, "Cofnij");

        pTalentCreator = new JPanel();
        pTalentCreator.setLayout(null);
        pTalentCreator.setBounds(0, 0, windowWidth, windowHeight);

        taTalent = new JTextArea();
        taTalent.setEditable(false);
        taTalent.setBorder(new TitledBorder(new EtchedBorder(), "Lista Talentów"));

        spTalent = new JScrollPane(taTalent, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spTalent.setBounds((int)(windowWidth*(1/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/2f)), (int)(windowHeight*(5/6f)));
        pTalentCreator.add(spTalent);

        tfNameTalent = new JTextField();
        tfNameTalent.setBounds((int)(windowWidth*(9/16f)), (int)(windowHeight*(1/2f)), (int)(windowWidth*(1/8f)), (int)(windowHeight*(1/18f)));
        pTalentCreator.add(tfNameTalent);

        String[] statistics = {"Siła", "Wytrzymałość", "Zręczność", "Inteligencja", "Szczęście", "Charyzma"};
        cbStatistic = new JComboBox(statistics);
        cbStatistic.setBounds((int)(windowWidth*(3/4f)), (int)(windowHeight*(1/2f)), (int)(windowWidth*(1/8f)), (int)(windowHeight*(1/18f)));
        cbStatistic.setSelectedIndex(0);
        pTalentCreator.add(cbStatistic);

        bAddTalent = createButton(11/16f,5/8f, 1/8f, 1/18f, "Dodaj");
        pTalentCreator.add(bAddTalent);

        //---------- Add talent creator ----------//

        pItemCreator = new JPanel();
        pItemCreator.setLayout(null);
        pItemCreator.setBounds(0, 0, windowWidth, windowHeight);

        pItemList = new JPanel();
        pItemList.setLayout(new BoxLayout(pItemList, BoxLayout.Y_AXIS));
        pItemList.setBorder(new TitledBorder(new EtchedBorder(), "Lista Przedmiotów"));

        spItem = new JScrollPane(pItemList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spItem.setBounds((int)(windowWidth*(1/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/2f)), (int)(windowHeight*(5/6f)));
        pItemCreator.add(spItem);

        pItemBox = new JPanel();
        pItemBox.setLayout(new GridLayout(0,2));

        spItemPanel = new JScrollPane(pItemBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spItemPanel.setBounds((int)(windowWidth*(3/5f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(3/10f)), (int)(windowHeight*(2/3f)));

        String[] features = {"Nazwa", "Zdrowie", "Kondycja", "Mana", "Siła", "Wytrzymałość", "Zręczność", "Inteligencja", "Szczęście", "Charyzma", "Masa", "Obrażenia", "Obrona", "Opis"};
        for (int i = 0; i < features.length; i++)
        {
            String[] feature = {features[i]};
            cbItemValues.add(new JComboBox(feature));
            cbItemValues.get(i).setBounds((int)(spItemPanel.getBounds().width*(1/2f)), i*((int)(windowHeight*(1/18f))+50), (int)(windowWidth*(1/8f)), (int)(windowHeight*(1/18f)));
            cbItemValues.get(i).setSelectedIndex(0);

            tfItemValues.add(new JTextField());
            tfItemValues.get(i).setBounds(0, i*((int)(windowHeight*(1/18f))+50), (int)(windowWidth*(1/8f)), (int)(windowHeight*(1/18f)));
            if(i==0 || i>10) {
                tfItemValues.get(i).setText("");
            } else {
                tfItemValues.get(i).setText("0");
            }

            pItemBox.add(tfItemValues.get(i));
            pItemBox.add(cbItemValues.get(i));
        }
        pItemCreator.add(spItemPanel);

        bAddItem = createButton(11/16f,5/6f, 1/8f, 1/18f, "Dodaj");
        pItemCreator.add(bAddItem);

        //---------- Add item creator ----------//
    }

    Button createButton(float x, float y, float w, float h, String name){
        Button button = new Button(name);
        button.setBounds((int)(windowWidth * x), (int)(windowHeight * y), (int)(windowWidth * w), (int)(windowHeight * h));
        button.setFont(new Font("Arial", Font.PLAIN, fontSize));
        button.addActionListener(this);
        return button;
    }

    //---------- createButton accelerates adding buttons and saves time on types lines of code ----------//
    //---------- it takes float values that customize with window size ----------//


    public Button getbAddItem() {
        return bAddItem;
    }

    public List<JLabel> getlItems() {
        return lItems;
    }

    public JPanel getpItemList() {
        return pItemList;
    }

    public List<JComboBox> getCbItemValues() {
        return cbItemValues;
    }

    public List<JTextField> getTfItemValues() {
        return tfItemValues;
    }

    public Button getbItemCreator() {
        return bItemCreator;
    }

    public Button getbBackLobby() {
        return bBackLobby;
    }

    public JPanel getpItemCreator() {
        return pItemCreator;
    }

    public JScrollPane getSpTalent() {
        return spTalent;
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

    public JTextField getTfNameTalent() {
        return tfNameTalent;
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
