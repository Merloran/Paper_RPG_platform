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
    private Button bStart, bTalentCreator, bItemCreator, bAddTalent, bBackLobby, bAddItem, bEvidenceCreator, bAddName, bAddSurname, bAddNation, bAddTitle, bProfessionCreator, bAddProfession, bRaceCreator, bAddRace, bPersonCreator, bAddPerson, bEditPerson, bSelectPerson, bConfirmEdit, bCancelEdit;
    private List<JLabel> lItems = new ArrayList<>(), lProfessions = new ArrayList<>(), lRaces = new ArrayList<>(), lEquipment = new ArrayList<>(), lTalents = new ArrayList<>();
    private JPanel pStart, pTalentCreator, pTalentBox, pItemCreator, pItemBox, pItemList, pEvidenceCreator, pEvidenceBox, pProfessionCreator, pProfessionList, pProfessionBox, pRaceCreator, pRaceList, pRaceBox, pPersonCreator, pPersonProperties, pPersonTalents, pPersonItems, pPersonEdit, pPersonEditBox, pPersonEditTalents, pPersonEditItems;
    private JTextArea taTalent, taName, taSurname, taNation, taTitle, taEvidences, taStatistics, taValues, taDescription;
    private JTextField tfNameTalent, tfProfessionName, tfProfessionDescription;
    private JComboBox cbStatistic, cbPerson, cbProfession, cbRace;
    private List<JComboBox> cbProfessionTalents = new ArrayList<>(), cbEditTalents = new ArrayList<>(), cbEditItems = new ArrayList<>();
    private List<JTextField> tfItemValues = new ArrayList<>(), tfEvidences = new ArrayList<>(), tfRaceValues = new ArrayList<>(), tfPersonEditValues = new ArrayList<>();
    private JScrollPane spTalent, spItem, spName, spSurname, spNation, spTitle, spProfession, spRace, spPersonTalents, spPersonItems, spEditTalents, spEditItems;
    private JMenuBar menu = new JMenuBar();
    private JMenu mfile = new JMenu("Plik"), mExport = new JMenu("Eksportuj"), mImport = new JMenu("Importuj");
    private JMenuItem mImportTalents = new JMenuItem("Talenty"), mImportItems = new JMenuItem("Przedmioty"), mImportPersons = new JMenuItem("Postać"), mImportEvidences = new JMenuItem("Dane osobowe"), mImportProfessions = new JMenuItem("Profesje"), mImportRaces = new JMenuItem("Rasy");
    private JMenuItem mExportTalents = new JMenuItem("Talenty"), mExportItems = new JMenuItem("Przedmioty"), mExportPersons = new JMenuItem("Postać"), mExportEvidences = new JMenuItem("Dane osobowe"), mExportProfessions = new JMenuItem("Profesje"), mExportRaces = new JMenuItem("Rasy");
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
        mExportEvidences.addActionListener(this);
        mExportEvidences.setEnabled(false);
        mExportProfessions.addActionListener(this);
        mExportProfessions.setEnabled(false);
        mExportRaces.addActionListener(this);
        mExportRaces.setEnabled(false);
        mExport.add(mExportTalents);
        mExport.add(mExportItems);
        mExport.add(mExportEvidences);
        mExport.add(mExportProfessions);
        mExport.add(mExportRaces);
        mExport.add(mExportPersons);

        mImportItems.addActionListener(this);
        mImportTalents.addActionListener(this);
        mImportEvidences.addActionListener(this);
        mImportProfessions.addActionListener(this);
        mImportProfessions.setEnabled(false);
        mImportRaces.addActionListener(this);
        mImport.add(mImportTalents);
        mImport.add(mImportItems);
        mImport.add(mImportEvidences);
        mImport.add(mImportProfessions);
        mImport.add(mImportRaces);
        mImport.add(mImportPersons);

        mfile.add(mExport);
        mfile.add(mImport);
        setJMenuBar(menu);


        //---------- Add menu bar ----------//


        pStart = new JPanel();
        pStart.setLayout(null);
        pStart.setBounds(0, 0, windowWidth, windowHeight);

        for(int i=0; i<PLAYERS; i++)
        {
            bPlayers.add(create_button(1/10f, (i+2)/8f, 1/10f, 1/20f, "Gracz" + (bPlayers.size()+1)));
            bPlayers.get(bPlayers.size()-1).setVisible(false);
            bPlayers.get(bPlayers.size()-1).setEnabled(false);
            pStart.add(bPlayers.get(bPlayers.size()-1));
        }

        bStart = create_button(3/8f, 15/36f, 1/4f, 1/9f, "Start");
        pStart.add(bStart);

        bTalentCreator = create_button(3/4f, 1/8f, 1/8f, 1/18f, "Talenty");
        pStart.add(bTalentCreator);

        bItemCreator = create_button(3/4f, 2/8f, 1/8f, 1/18f, "Przedmioty");
        pStart.add(bItemCreator);

        bEvidenceCreator = create_button(3/4f, 3/8f, 1/8f, 1/18f, "Dane osobowe");
        pStart.add(bEvidenceCreator);

        bProfessionCreator = create_button(3/4f, 4/8f, 1/8f, 1/18f, "Profesje");
        pStart.add(bProfessionCreator);

        bRaceCreator = create_button(3/4f, 5/8f, 1/8f, 1/18f, "Rasy");
        pStart.add(bRaceCreator);

        bPersonCreator = create_button(3/4f, 6/8f, 1/8f, 1/18f, "Postacie");
        pStart.add(bPersonCreator);

        add(pStart);

        bBackLobby = create_button(27/32f, 5/6f, 1/8f, 1/18f, "Cofnij");


        //---------- Add lobby ----------//


        pTalentCreator = new JPanel();
        pTalentCreator.setLayout(null);
        pTalentCreator.setBounds(0, 0, windowWidth, windowHeight);

        taTalent = new JTextArea();
        taTalent.setEditable(false);
        taTalent.setBorder(new TitledBorder(new EtchedBorder(), "Lista Talentów"));

        spTalent = new JScrollPane(taTalent, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spTalent.setBounds((int)(windowWidth*(1/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/2f)), (int)(windowHeight*(5/6f)));
        pTalentCreator.add(spTalent);

        pTalentBox = new JPanel();
        pTalentBox.setLayout(new GridLayout(0, 3));
        pTalentBox.setBounds((int)(windowWidth*(19/32f)), (int)(windowHeight*(3/8f)), (int)(windowWidth*(3/8f)), (int)(windowHeight*(1/18f)));

        tfNameTalent = new JTextField();
        pTalentBox.add(tfNameTalent);

        String[] statistics = {"-----", "Siła", "Wytrzymałość", "Zręczność", "Inteligencja", "Szczęście", "Charyzma"};
        cbStatistic = new JComboBox(statistics);
        cbStatistic.setSelectedIndex(0);
        pTalentBox.add(cbStatistic);

        bAddTalent = create_button("Dodaj");
        pTalentBox.add(bAddTalent);

        pTalentCreator.add(pTalentBox);


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
        pItemBox.setLayout(new GridLayout(0,1));
        pItemBox.setBounds((int)(windowWidth*(3/5f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(3/10f)), (int)(windowHeight*(23/30f)));

        String[] features = {"Nazwa", "Zdrowie", "Kondycja", "Mana", "Siła", "Wytrzymałość", "Zręczność", "Inteligencja", "Szczęście", "Charyzma", "Masa", "Obrażenia", "Obrona", "Opis"};
        for (int i = 0; i < features.length; i++)
        {
            tfItemValues.add(new JTextField());
            tfItemValues.get(i).setBorder(new TitledBorder(new EtchedBorder(), features[i]));

            pItemBox.add(tfItemValues.get(i));
        }
        pItemCreator.add(pItemBox);

        bAddItem = create_button(11/16f,5/6f, 1/8f, 1/18f, "Dodaj");
        pItemCreator.add(bAddItem);


        //---------- Add item creator ----------//


        pEvidenceCreator = new JPanel();
        pEvidenceCreator.setLayout(null);
        pEvidenceCreator.setBounds(0, 0, windowWidth, windowHeight);

        taName = new JTextArea();
        taName.setEditable(false);
        taName.setBorder(new TitledBorder(new EtchedBorder(), "Lista Imion"));

        spName = new JScrollPane(taName, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spName.setBounds((int)(windowWidth*(1/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/8f)), (int)(windowHeight*(5/6f)));
        pEvidenceCreator.add(spName);

        taSurname = new JTextArea();
        taSurname.setEditable(false);
        taSurname.setBorder(new TitledBorder(new EtchedBorder(), "Lista Nazwisk"));

        spSurname = new JScrollPane(taSurname, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spSurname.setBounds((int)(windowWidth*(7/40f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/8f)), (int)(windowHeight*(5/6f)));
        pEvidenceCreator.add(spSurname);

        taNation = new JTextArea();
        taNation.setEditable(false);
        taNation.setBorder(new TitledBorder(new EtchedBorder(), "Lista Państw"));

        spNation = new JScrollPane(taNation, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spNation.setBounds((int)(windowWidth*(12/40f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/8f)), (int)(windowHeight*(5/6f)));
        pEvidenceCreator.add(spNation);

        taTitle = new JTextArea();
        taTitle.setEditable(false);
        taTitle.setBorder(new TitledBorder(new EtchedBorder(), "Lista Tytułów"));

        spTitle = new JScrollPane(taTitle, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spTitle.setBounds((int)(windowWidth*(17/40f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/8f)), (int)(windowHeight*(5/6f)));
        pEvidenceCreator.add(spTitle);

        pEvidenceBox = new JPanel();
        pEvidenceBox.setLayout(new GridLayout(0,2));
        pEvidenceBox.setBounds((int)(windowWidth*(3/5f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(3/10f)), (int)(windowHeight*(8/30f)));

        String[] evidences = {"Imię", "Nazwisko", "Państwo", "Tytuł"};
        for (int i = 0; i < evidences.length; i++)
        {
            tfEvidences.add(new JTextField());
            tfEvidences.get(i).setBorder(new TitledBorder(new EtchedBorder(), evidences[i]));
            tfEvidences.get(i).addActionListener(this);

            pEvidenceBox.add(tfEvidences.get(i));
            switch (i) {
                case 0 -> {
                    bAddName = create_button("Dodaj");
                    pEvidenceBox.add(bAddName);
                }
                case 1 -> {
                    bAddSurname = create_button("Dodaj");
                    pEvidenceBox.add(bAddSurname);
                }
                case 2 -> {
                    bAddNation = create_button("Dodaj");
                    pEvidenceBox.add(bAddNation);
                }
                default -> {
                    bAddTitle = create_button("Dodaj");
                    pEvidenceBox.add(bAddTitle);
                }
            }
        }
        pEvidenceCreator.add(pEvidenceBox);


        //---------- Add Evidence creator ----------//


        pProfessionCreator = new JPanel();
        pProfessionCreator.setLayout(null);
        pProfessionCreator.setBounds(0, 0, windowWidth, windowHeight);

        pProfessionList = new JPanel();
        pProfessionList.setLayout(new BoxLayout(pProfessionList, BoxLayout.Y_AXIS));
        pProfessionList.setBorder(new TitledBorder(new EtchedBorder(), "Lista Przedmiotów"));

        spProfession = new JScrollPane(pProfessionList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spProfession.setBounds((int)(windowWidth*(1/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/2f)), (int)(windowHeight*(5/6f)));
        pProfessionCreator.add(spProfession);

        pProfessionBox = new JPanel();
        pProfessionBox.setLayout(new GridLayout(0,1));
        pProfessionBox.setBounds((int)(windowWidth*(3/5f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(3/10f)), (int)(windowHeight*(6/30f)));

        tfProfessionName = new JTextField();
        tfProfessionName.setBorder(new TitledBorder(new EtchedBorder(), "Nazwa"));
        pProfessionBox.add(tfProfessionName);

        tfProfessionDescription = new JTextField();
        tfProfessionDescription.setBorder(new TitledBorder(new EtchedBorder(), "Opis"));
        pProfessionBox.add(tfProfessionDescription);

        JComboBox comboBox = new JComboBox();
        comboBox.setBorder(new TitledBorder(new EtchedBorder(), "Talent"));
        comboBox.addItem("-----");
        cbProfessionTalents.add(comboBox);
        cbProfessionTalents.get(0).addActionListener(this);
        pProfessionBox.add(cbProfessionTalents.get(0));

        pProfessionCreator.add(pProfessionBox);

        bAddProfession = create_button(11/16f,5/6f, 1/8f, 1/18f, "Dodaj");
        bAddProfession.setEnabled(false);
        pProfessionCreator.add(bAddProfession);


        //---------- Add profession creator ----------//


        pRaceCreator = new JPanel();
        pRaceCreator.setLayout(null);
        pRaceCreator.setBounds(0, 0, windowWidth, windowHeight);

        pRaceList = new JPanel();
        pRaceList.setLayout(new BoxLayout(pRaceList, BoxLayout.Y_AXIS));
        pRaceList.setBorder(new TitledBorder(new EtchedBorder(), "Lista Ras"));

        spRace = new JScrollPane(pRaceList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spRace.setBounds((int)(windowWidth*(1/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/2f)), (int)(windowHeight*(5/6f)));
        pRaceCreator.add(spRace);

        pRaceBox = new JPanel();
        pRaceBox.setLayout(new GridLayout(0,1));
        pRaceBox.setBounds((int)(windowWidth*(3/5f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(3/10f)), (int)(windowHeight*(16/30f)));

        String[] raceValues = {"Nazwa", "Opis", "Siła", "Wytrzymałość", "Zręczność", "Inteligencja", "Szczęście", "Charyzma"};
        for (int i=0; i<raceValues.length; i++){
            tfRaceValues.add(new JTextField());
            tfRaceValues.get(i).setBorder(new TitledBorder(new EtchedBorder(), raceValues[i]));
            pRaceBox.add(tfRaceValues.get(i));
        }
        pRaceCreator.add(pRaceBox);

        bAddRace = create_button(11/16f,5/6f, 1/8f, 1/18f, "Dodaj");
        pRaceCreator.add(bAddRace);


        //---------- Add race creator ----------//


        pPersonCreator = new JPanel();
        pPersonCreator.setLayout(null);
        pPersonCreator.setBounds(0, 0, windowWidth, windowHeight);

        bAddPerson = create_button(27/32f, 4/6f, 1/8f, 1/18f, "Stwórz");
        bAddPerson.setEnabled(false);
        pPersonCreator.add(bAddPerson);

        bEditPerson = create_button(27/32f, 3/6f, 1/8f, 1/18f,"Edytuj");
        bEditPerson.setEnabled(false);
        pPersonCreator.add(bEditPerson);

        bSelectPerson = create_button(27/32f, 2/6f, 1/8f, 1/18f,"Zobacz");
        bSelectPerson.setEnabled(false);
        pPersonCreator.add(bSelectPerson);

        cbPerson = new JComboBox();
        cbPerson.addItem("-----");
        cbPerson.setBounds((int)(windowWidth*27/32f), (int)(windowHeight*1/6f), (int)(windowWidth*1/8f), (int)(windowHeight*1/14f));
        cbPerson.setBorder(new TitledBorder(new EtchedBorder(), "Postać"));
        pPersonCreator.add(cbPerson);

        pPersonProperties = new JPanel();
        pPersonProperties.setLayout(new GridLayout(0,1));
        pPersonProperties.setBounds((int)(windowWidth*(1/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/4f)), (int)(windowHeight*(5/6f)));

        taEvidences = new JTextArea();
        taEvidences.setEditable(false);
        taEvidences.setBorder(new TitledBorder(new EtchedBorder(), "Dane osobowe"));
        pPersonProperties.add(taEvidences);

        taStatistics = new JTextArea();
        taStatistics.setEditable(false);
        taStatistics.setBorder(new TitledBorder(new EtchedBorder(), "Statystyki"));
        pPersonProperties.add(taStatistics);

        taValues = new JTextArea();
        taValues.setEditable(false);
        taValues.setBorder(new TitledBorder(new EtchedBorder(), "Wartości"));
        pPersonProperties.add(taValues);

        taDescription = new JTextArea();
        taDescription.setEditable(false);
        taDescription.setBorder(new TitledBorder(new EtchedBorder(), "Opisy"));
        pPersonProperties.add(taDescription);

        pPersonCreator.add(pPersonProperties);

        pPersonItems = new JPanel();
        pPersonItems.setLayout(new BoxLayout(pPersonItems, BoxLayout.Y_AXIS));
        pPersonItems.setBorder(new TitledBorder(new EtchedBorder(), "Ekwipunek"));

        spPersonItems = new JScrollPane(pPersonItems, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spPersonItems.setBounds((int)(windowWidth*(6/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(1/4f)), (int)(windowHeight*(5/12f)));
        pPersonCreator.add(spPersonItems);

        pPersonTalents = new JPanel();
        pPersonTalents.setLayout(new BoxLayout(pPersonTalents, BoxLayout.Y_AXIS));
        pPersonTalents.setBorder(new TitledBorder(new EtchedBorder(), "Talenty"));

        spPersonTalents = new JScrollPane(pPersonTalents, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spPersonTalents.setBounds((int)(windowWidth*(6/20f)), (int)(windowHeight*(28/60f)), (int)(windowWidth*(1/4f)), (int)(windowHeight*(5/12f)));
        pPersonCreator.add(spPersonTalents);


        //---------- Add person creator ----------//


        pPersonEdit = new JPanel();
        pPersonEdit.setLayout(null);
        pPersonEdit.setBounds(0, 0, windowWidth, windowHeight);

        pPersonEditBox = new JPanel();
        pPersonEditBox.setLayout(new GridBagLayout());
        pPersonEditBox.setBounds((int)(windowWidth*(1/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(3/10f)), (int)(windowHeight*(23/30f)));

        String[] personValues = {"Profesja", "Rasa", "Tytuł", "Nacja",
                                "Siła", "Wytrzymałość", "Zręczność", "Inteligencja", "Szczęście", "Charyzma",
                                "Zdrowie", "Maksymalne Zdrowie", "Mana", "Maksymalna Mana", "Kondycja", "Maksymalna Kondycja",
                                "Wiek", "Los", "Złoto", "Udźwig", "Punkty Statystyk", "Punkty Talentów", "Obrona", "Obrażenia"};

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        cbProfession = new JComboBox();
        cbProfession.setBorder(new TitledBorder(new EtchedBorder(), personValues[0]));
        pPersonEditBox.add(cbProfession, gridBagConstraints);

        cbRace = new JComboBox();
        cbRace.setBorder(new TitledBorder(new EtchedBorder(), personValues[1]));
        gridBagConstraints.gridx = 1;
        pPersonEditBox.add(cbRace, gridBagConstraints);

        for (int i=2; i<personValues.length; i++)
        {
            JTextField textField = new JTextField();
            textField.setBorder(new TitledBorder(new EtchedBorder(), personValues[i]));
            tfPersonEditValues.add(textField);

            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridx = i%2;
            gridBagConstraints.gridy = i/2;

            pPersonEditBox.add(tfPersonEditValues.get(i-2), gridBagConstraints);
        }
        pPersonEdit.add(pPersonEditBox);

        pPersonEditTalents = new JPanel();
        pPersonEditTalents.setLayout(new GridLayout(0,1));
        pPersonEditTalents.setBorder(new TitledBorder(new EtchedBorder(), "Talenty"));

        spEditTalents = new JScrollPane(pPersonEditTalents, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spEditTalents.setBounds((int)(windowWidth*(7/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(3/10f)), (int)(windowHeight*(23/30f)));
        pPersonEdit.add(spEditTalents);

        pPersonEditItems = new JPanel();
        pPersonEditItems.setLayout(new GridLayout(0,1));
        pPersonEditItems.setBorder(new TitledBorder(new EtchedBorder(), "Przedmioty"));

        spEditItems = new JScrollPane(pPersonEditItems, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spEditItems.setBounds((int)(windowWidth*(13/20f)), (int)(windowHeight*(1/20f)), (int)(windowWidth*(3/10f)), (int)(windowHeight*(23/30f)));
        pPersonEdit.add(spEditItems);

        bCancelEdit = create_button(27/32f, 5/6f, 1/8f, 1/18f, "Anuluj");
        pPersonEdit.add(bCancelEdit);

        bConfirmEdit = create_button(11/16f,5/6f, 1/8f, 1/18f, "Zatwierdź");
        pPersonEdit.add(bConfirmEdit);


        //---------- Add edit person ----------//
    }

    Button create_button(float x, float y, float w, float h, String name){
        Button button = new Button(name);
        button.setBounds((int)(windowWidth * x), (int)(windowHeight * y), (int)(windowWidth * w), (int)(windowHeight * h));
        button.setFont(new Font("Arial", Font.PLAIN, fontSize));
        button.addActionListener(this);
        return button;
    }

    //---------- createButton accelerates adding buttons and saves time on types lines of code ----------//
    //---------- it takes float values that customize with window size ----------//

    Button create_button(String name){
        Button button = new Button(name);
        button.setFont(new Font("Arial", Font.PLAIN, fontSize));
        button.addActionListener(this);
        return button;
    }

    //---------- createButton this is for layouts that can't set bounds in them ----------//


    public void setCbProfession(JComboBox cbProfession) {
        this.cbProfession = cbProfession;
    }

    public void setCbRace(JComboBox cbRace) {
        this.cbRace = cbRace;
    }

    public JComboBox getCbProfession() {
        return cbProfession;
    }

    public JComboBox getCbRace() {
        return cbRace;
    }

    public Button getbConfirmEdit() {
        return bConfirmEdit;
    }

    public Button getbCancelEdit() {
        return bCancelEdit;
    }

    public JPanel getpPersonEditTalents() {
        return pPersonEditTalents;
    }

    public JPanel getpPersonEditItems() {
        return pPersonEditItems;
    }

    public List<JComboBox> getCbEditTalents() {
        return cbEditTalents;
    }

    public List<JComboBox> getCbEditItems() {
        return cbEditItems;
    }

    public List<JTextField> getTfPersonEditValues() {
        return tfPersonEditValues;
    }

    public JPanel getpPersonEdit() {
        return pPersonEdit;
    }

    public List<JLabel> getlEquipment() {
        return lEquipment;
    }

    public List<JLabel> getlTalents() {
        return lTalents;
    }

    public void setCbPerson(JComboBox cbPerson) {
        this.cbPerson = cbPerson;
    }

    public JComboBox getCbPerson() {
        return cbPerson;
    }

    public Button getbPersonCreator() {
        return bPersonCreator;
    }

    public Button getbAddPerson() {
        return bAddPerson;
    }

    public Button getbEditPerson() {
        return bEditPerson;
    }

    public Button getbSelectPerson() {
        return bSelectPerson;
    }

    public JPanel getpPersonTalents() {
        return pPersonTalents;
    }

    public JPanel getpPersonItems() {
        return pPersonItems;
    }

    public JTextArea getTaEvidences() {
        return taEvidences;
    }

    public JTextArea getTaStatistics() {
        return taStatistics;
    }

    public JTextArea getTaValues() {
        return taValues;
    }

    public JTextArea getTaDescription() {
        return taDescription;
    }

    public JPanel getpPersonCreator() {
        return pPersonCreator;
    }

    public JMenuItem getmImportRaces() {
        return mImportRaces;
    }

    public JMenuItem getmExportRaces() {
        return mExportRaces;
    }

    public Button getbRaceCreator() {
        return bRaceCreator;
    }

    public Button getbAddRace() {
        return bAddRace;
    }

    public List<JLabel> getlRaces() {
        return lRaces;
    }

    public JPanel getpRaceList() {
        return pRaceList;
    }

    public List<JTextField> getTfRaceValues() {
        return tfRaceValues;
    }

    public JPanel getpRaceCreator() {
        return pRaceCreator;
    }

    public JMenuItem getmImportProfessions() {
        return mImportProfessions;
    }

    public JMenuItem getmExportProfessions() {
        return mExportProfessions;
    }

    public List<JLabel> getlProfessions() {
        return lProfessions;
    }

    public Button getbProfessionCreator() {
        return bProfessionCreator;
    }

    public Button getbAddProfession() {
        return bAddProfession;
    }

    public JPanel getpProfessionCreator() {
        return pProfessionCreator;
    }

    public JPanel getpProfessionList() {
        return pProfessionList;
    }

    public JPanel getpProfessionBox() {
        return pProfessionBox;
    }

    public JTextField getTfProfessionName() {
        return tfProfessionName;
    }

    public JTextField getTfProfessionDescription() {
        return tfProfessionDescription;
    }

    public List<JComboBox> getCbProfessionTalents() {
        return cbProfessionTalents;
    }

    public JMenuItem getmImportEvidences() {
        return mImportEvidences;
    }

    public JMenuItem getmExportEvidences() {
        return mExportEvidences;
    }

    public List<JTextField> getTfEvidences() {
        return tfEvidences;
    }

    public Button getbEvidenceCreator() {
        return bEvidenceCreator;
    }

    public Button getbAddName() {
        return bAddName;
    }

    public Button getbAddSurname() {
        return bAddSurname;
    }

    public Button getbAddNation() {
        return bAddNation;
    }

    public Button getbAddTitle() {
        return bAddTitle;
    }

    public JPanel getpEvidenceCreator() {
        return pEvidenceCreator;
    }

    public JTextArea getTaName() {
        return taName;
    }

    public JTextArea getTaSurname() {
        return taSurname;
    }

    public JTextArea getTaNation() {
        return taNation;
    }

    public JTextArea getTaTitle() {
        return taTitle;
    }

    public Button getbAddItem() {
        return bAddItem;
    }

    public List<JLabel> getlItems() {
        return lItems;
    }

    public JPanel getpItemList() {
        return pItemList;
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

    public Button getbAddTalent() {
        return bAddTalent;
    }

    public JComboBox getCbStatistic() {
        return cbStatistic;
    }

    public JTextField getTfNameTalent() {
        return tfNameTalent;
    }

    public JPanel getpStart() {
        return pStart;
    }

    public JPanel getpTalentCreator() {
        return pTalentCreator;
    }

    public JTextArea getTaTalent() {
        return taTalent;
    }

    public Button getbTalentCreator() {
        return bTalentCreator;
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

    public JMenuItem getmImportTalents() {
        return mImportTalents;
    }

    public JMenuItem getmImportItems() {
        return mImportItems;
    }

    public JMenuItem getmImportPersons() {
        return mImportPersons;
    }

    public JMenuItem getmExportTalents() {
        return mExportTalents;
    }

    public JMenuItem getmExportItems() {
        return mExportItems;
    }

    public JMenuItem getmExportPersons() {
        return mExportPersons;
    }
}
