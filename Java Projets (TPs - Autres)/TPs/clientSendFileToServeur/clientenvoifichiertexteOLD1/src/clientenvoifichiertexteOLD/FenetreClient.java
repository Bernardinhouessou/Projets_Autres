/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientenvoifichiertexteOLD;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * CLIENT GUI CLASS
 * 
 * @author Bernardin Houessou
 */
public final class FenetreClient extends JFrame{
        //implements Runnable  
// Le pattern de l'adresse IP adresse 
private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
// Le pattern du numéro du port
private static final String portPattern = "^[0-9]+$";
// Le menu
private JMenu menu; 
private JMenuBar menuBar;
private JMenuItem item1, item2, item3, item4; 
private File fichier; 
private static JFileChooser fileChooser;                 
private BufferedReader bufferReader;                    
private String fichierTxtEnvParClient;
private String  ligne;                             
// Le container des panneaux
private Container ConteneurDesPanneaux; 
// Les panneaux    
private JPanel panneauEntrees; 
private JPanel panneauCsMsgs;
private JPanel panneauCtrlBoutons;
// Les Entrées
private JLabel serveurAdresseLbl;
private JLabel portnumeroLbl;
private JTextField serveurAdresse;        
private JTextField portnumero;
// Les zones de messages
private JLabel clientMsgLbl;
private JLabel serveurMsgLbl;
private JTextArea clientMsg;
private JTextArea serveurMsg;
private JScrollPane clientMsgscroll;
private JScrollPane serveurMsgscroll;
// Les boutons
private JButton connexionBtn;                                   
private JButton terminerBtn;   
// Heure et autres
private JLabel labeldateheure1;      
private JLabel lblvide;  

// Les Classes de MonClient et autres.
private MonClient monClient = null;  //monClient ou mc ds le code du serveur
//
private MonServeur monServeur = null; //
   
public JMenu creerMenu() {
    // Création et ajout de la barre de menu
    menu = new JMenu("Fichier");
    menu.setMnemonic(KeyEvent.VK_F); // ajout de mnémonique (correspondant au soulignement de la lettre << F >> dans Fichier 
    menuBar = new JMenuBar();            
    setJMenuBar(menuBar); 
    menuBar.add(menu);  
    // Création et ajout des éléments du menu ainsi que de leur écouteur d'événements
    item1 = new JMenuItem("Ouvrir");
    item1.setMnemonic(KeyEvent.VK_O);
    //item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));// ajoute d'accélérateur 'F' ctrl+shift+O
    item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));// ajoute d'accélérateur 'F'
//    item2 = new JMenuItem("Connexion");
//    item2.setMnemonic(KeyEvent.VK_C);
//    item3 = new JMenuItem("Terminé");
//    item4 = new JMenuItem("Quitter"); 
    menu.add(item1);
//    menu.add(item2);
//    menu.add(item3);
//    menu.add(item4);
    item1.addActionListener(new OpenListener());
//    item2.addActionListener(new OpenListener());  
//    item3.addActionListener(new OpenListener()); 
//    item4.addActionListener(new OpenListener()); 
    return menu; // Retourner le menu si la méthode "creerMenu()" est appellée
}

// Création de la méthode "creerPanneauEntrees()"
public JPanel creerPanneauEntrees() {    
    panneauEntrees = new JPanel(new GridLayout(3, 3));
    //Séparation des elements labels et textfield correspondant à chaque entree
    JPanel zone1= new JPanel(); 
    JPanel zone2= new JPanel(); 
    JPanel zone3= new JPanel();     
    //Heure affichage ds zone 2
    labeldateheure1 = new JLabel("Horloge");                           //Instantiation du label "labeldateheure1" pour y afficher l'heure 
    labeldateheure1.setFont(new Font("sansserif", Font.PLAIN, 40));    
    zone3.add(labeldateheure1);
    panneauEntrees.add(zone3);
    // Création des elements d'entrees
    serveurAdresseLbl= new JLabel("Serveur :");
    serveurAdresse= new JTextField("localhost",25);
    portnumeroLbl= new JLabel("  Port :");         
    portnumero= new JTextField("2222",25); 
    // Ajout des éléments d'entrées à chaque zone dans le panneaux des entrees
    zone1.add(serveurAdresseLbl);
    zone1.add(serveurAdresse);
    panneauEntrees.add(zone1);
    zone2.add(portnumeroLbl);
    zone2.add(portnumero);
    panneauEntrees.add(zone2);
    
    ConteneurDesPanneaux = this.getContentPane();
    ConteneurDesPanneaux.add(panneauEntrees,BorderLayout.NORTH);
    
    return panneauEntrees; // Retourner le panneau d'Entrees si la méthode "creerPanneauEntrees()" est appellée
}
// Création de la méthode "creerpanneauCsMsgs()"
public Container creerPanneauCsMsgs() {
    panneauCsMsgs = new JPanel();    
    //Séparation des elements labels et textfield correspondant à chaque entree
    JPanel zoneOuest= new JPanel(new GridBagLayout());
    JPanel zoneEst= new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.VERTICAL;
    // TextArea du Client 
    clientMsgLbl= new JLabel("Client" );
    c.insets = new Insets(10,10,10,20);  //top padding - espace de 5 
    c.gridx = 0;       
    zoneOuest.add(clientMsgLbl,c);
    clientMsg = new JTextArea("",20, 40); 
    //clientMsg.getDocument().addDocumentListener(new MyDocumentListener()); // Docment listener  
    clientMsgscroll=new JScrollPane(clientMsg);                                                                                    
    clientMsgscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    zoneOuest.add(clientMsgscroll,c);  
    add(zoneOuest,BorderLayout.WEST);  
    panneauCsMsgs.add(zoneOuest, BorderLayout.CENTER);
    // TextArea du Serveur
    serveurMsgLbl= new JLabel("Serveur");
    c.insets = new Insets(10,10,10,20);  //top padding - espace de 5 
    c.gridx = 0;       
    c.gridwidth = 0;   
    zoneEst.add(serveurMsgLbl,c); 
    serveurMsg = new JTextArea("",20, 40);
    serveurMsg.setEditable(false);
    //serveurMsg.addTextListener(new MyTextListener("Text Area"));
    serveurMsgscroll=new JScrollPane(serveurMsg);                                                                         
    serveurMsgscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    zoneEst.add(serveurMsgscroll); 
    add(zoneEst,BorderLayout.EAST);                           
    panneauCsMsgs.add(zoneEst, BorderLayout.CENTER);  
    ConteneurDesPanneaux = this.getContentPane();
    ConteneurDesPanneaux.add(panneauCsMsgs,BorderLayout.CENTER);      
        
    return panneauCsMsgs;// Retourner le panneau d'Entrees si la méthode "creerpanneauCsMsgs()" est appellée
}
// Création de la méthode "creerPanneauCtrlBoutons()"
public JPanel creerPanneauCtrlBoutons() {        
    panneauCtrlBoutons = new JPanel();  

    // Création du bouton "Connexion" ainsi que de son écouteur d'événements
    connexionBtn =new JButton("Connexion");                                                             
    connexionBtn.addActionListener(new OpenListener());                                             
    panneauCtrlBoutons.add(connexionBtn,BorderLayout.WEST); 

    // Création du bouton "Terminé" ainsi que de son écouteur d'événements
    terminerBtn=new JButton("Terminé");                                                          
    terminerBtn.addActionListener(new OpenListener());                                       
    panneauCtrlBoutons.add(terminerBtn,BorderLayout.EAST); 
    terminerBtn.setEnabled(false); 
    ConteneurDesPanneaux = this.getContentPane();
    ConteneurDesPanneaux.add(panneauCtrlBoutons,BorderLayout.SOUTH);
            
    return panneauCtrlBoutons;// Retourner le panneau d'Entrées si la méthode "panneauCtrlBoutons()" est appellée
}

/**
 * Valide l'adresse ip avec une expression regulière 
 * @param ipStr adresse ip pour la validation
 * @return boolean -true pour la valide adresse ip et false pour l'invalide adresse ip
 */
public boolean validateIP(String ipStr) {
    String regex = ipv4Pattern;
    // Si adresse IP est localhost
    if(ipStr.equals("localhost")) return Pattern.matches("localhost", ipStr);  
    // Si le cas contraire
    if ( Pattern.matches(regex, ipStr)==false)  {    
         JOptionPane.showMessageDialog(null, "Adresse IP invalide -Entrer le bon format","Erreur",JOptionPane.ERROR_MESSAGE);
         System.err.println(Pattern.matches(regex, ipStr)+" - Adresse IP  " +ipStr + " est non valide");
         System.out.println("-------------------------------------------------------------");
    }
    return Pattern.matches(regex, ipStr);
}

/**
 * Valide le numéro de port avec une expression régulière
 * @param portStr numéro de port pour la validation
 * @return boolean -true pour le valide numéro de port et false pour l'invalide numéro de port
 */
public boolean validatePort(String portStr) {
    String regex = portPattern ;
    if ( Pattern.matches(regex, portStr)==false ){    
        JOptionPane.showMessageDialog(null, "Port invalide -Entrer que des chifres  Ex:2222 ","Erreur",JOptionPane.ERROR_MESSAGE);
        System.err.println(Pattern.matches(regex, portStr)+" - Numéro de port  " +portStr + " non valide");
        System.out.println("-------------------------------------------------------------");
    }
    return Pattern.matches(regex, portStr);
}

//255.245.188.123 -ok
//255.245.188.451 -no ok
/**
 * Lance les processus de validation des entrées (adresse IP et du Port)
 */
public void checkZoneDeText (){
    System.out.println("-------------------------------------------------------------");
    //Validation de l'adresse IP et du Port
    if((validateIP(serveurAdresse.getText()))==false)
    serveurAdresse.requestFocus();
    if(validatePort(portnumero.getText())==false)
    portnumero.requestFocus();
}

	/**methode qui permet de choisir un fichier avec une interface graphique
	 * 
	 * @return string qui contient le chemin du fichier
	 */
    public String obtenirCheminDuFichier(){
        
        JFileChooser fileChooser = new JFileChooser();
        
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
        	// retourner le chemin du fichier choisie
            return fileChooser.getSelectedFile().getPath();
        }
                
        return "";
    }
    
private class OpenListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // Création et assignation de ma commande à ma chaîne de caractères "btnSelectionner" 
        String btnSelectionner;
        btnSelectionner = e.getActionCommand();

        // Si btnSelectionner est "Ouvrir"
        if (btnSelectionner.equals("Ouvrir")) { 
            System.out.println("++++L'ecouteur du bouton \"Ouvrir\" recoit un ActionEvent");
            System.out.println("-->Lancemet du Thread CLientEnvoiFIchierTexte");
            //  CLientEnvoiFIchierTexte Thread 
            Thread Lecture;
            Lecture=new Thread(new Runnable() {
            public void run() {
                //            
                fichierTxtEnvParClient = obtenirCheminDuFichier();
                    try { bufferReader = new BufferedReader(new FileReader(fichierTxtEnvParClient)); } 
                    catch (FileNotFoundException e1) { }
                    try { ligne = bufferReader.readLine(); } 
                    catch (IOException e1) {}
                    
                    // Mon EDT: invokeLater
                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            //                            
                            while (ligne != null) {                                
                                clientMsg.append(ligne + "\n");
                                try {  ligne = bufferReader.readLine();} 
                                catch (IOException e1) { }                    
                            }
                        }
                    });
                }
            });
            Lecture.start();    
       
    }
                
        // Si btnSelectionner est "Connexion"
        if (btnSelectionner.equals("Connexion")) {
            // Faire si le bouton "Connexion" est cliqué
            System.out.println("-------------------------------------------------------------");
            System.out.println("+L'ecouteur du bouton\"Connexion\" recoit un ActionEvent"); 
            // Appelle de la méthode de vérification des entrées - zones de textes (adresse IP et port)
            checkZoneDeText();
            // Création d'un nouveau client si pas existant            
            if (monClient==null){ 
                try {   
                        
                        monClient =new MonClient(serveurAdresse.getText(),Integer.parseInt(portnumero.getText()));
                        System.out.println("mc = "+monClient+ "client:"+monClient);
                        connexionBtn.setText("Transmettre");                                   
                        serveurMsg.append(monClient.lireServeur()+'\n');
                        serveurAdresse.setEnabled(false);
                        portnumero.setEnabled(false);
                        terminerBtn.setEnabled(true);// Active le bouton "Terminé"
                        connexionBtn.addActionListener(new OpenListener2());
                        clientMsg.requestFocus();     
                                                
                    } 
                    catch (IOException ex) {
                        //Logger.getLogger(FenetreClient.class.getName()).log(Level.SEVERE, null, ex);
                        System.err.println("!! monClient= "+monClient);
                        
                    }     
            } 
            else {  
                    
                    monClient.ecrireServeur(clientMsg.getText());
                    serveurMsg.append(monClient.lireServeur()+" : "+clientMsg.getText() +'\n'); //Ecrire ce que le serveur répond dans la jTextArea du serveur
                    clientMsg.setText("");
                    clientMsg.requestFocus();
            }
                
        }
        
        // Si btnSelectionner est "Terminé"
        if (btnSelectionner.equals("Terminé")) {
            // Faire si le bouton Terminé" est cliqué                    
            System.out.println("++L'ecouteur du bouton \"Terminé\" recoit un ActionEvent");           
            clientMsg.setText("fin");
            monClient.ecrireServeur(clientMsg.getText());
            terminerBtn.setEnabled(false);
            if (clientMsg.getText().equals("fin")) { 
                serveurMsg.append(monClient.lireServeur());
                clientMsg.setText("");
                clientMsg.setVisible(false);
                connexionBtn.setEnabled(false);
                clientMsg.setEditable(false);
                JOptionPane.showMessageDialog(null,"Déconnexion du serveur effectué ","Information",JOptionPane.INFORMATION_MESSAGE);
                terminerBtn.setText("Quitter");
                terminerBtn.setEnabled(true);                        
            }
        }

        // Si btnSelectionner est "Quitter"
        if (btnSelectionner.equals("Quitter")) {
            // Faire si le bouton "Quitter" est cliqué
            System.out.println("+++L'ecouteur du bouton \"Quitter\" du menu ou pas du menu recoit un ActionEvent");
            System.exit(0); // Fermer la fenetre ou quitter le programme.
        }
    }
}      

private class OpenListener2 implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e2) {
        // Création et assignation de ma commande à ma chaîne de caractères "btnSelectionner" 
        String btnSelectionner2;
        btnSelectionner2 = e2.getActionCommand();
        
        // Si btnSelectionner est "Transmettre"
        if (btnSelectionner2.equals("Transmettre")) { 
            // Faire clientMsg est vide
            if(clientMsg.getText().equals("")) {                       
                JOptionPane.showMessageDialog(null,"!!Pas d'instructions entrées par le client !! ","Information",JOptionPane.ERROR_MESSAGE);              
                clientMsg.requestFocus();     
            }
            // Faire si non vide
            while (!clientMsg.getText().equals("")){    
                if (monClient==null){ 
                    try {
                            monClient =new MonClient(serveurAdresse.getText(),Integer.parseInt(portnumero.getText()));
                            clientMsg.requestFocus();
                            terminerBtn.setEnabled(true);// Active le bouton "Terminé"
                            monClient.ecrireServeur(clientMsg.getText());
                            serveurMsg.append(monClient.lireServeur()+'\n'); //Ecrire ce que le serveur répond dans la jTextArea du serveur
                            clientMsg.setText("");
                            clientMsg.requestFocus();


                        } 
                        catch (IOException ex) {
                            Logger.getLogger(FenetreClient.class.getName()).log(Level.SEVERE, null, ex);
                        }               

                } 
                else 
                {  // Faire au cas de commande "fin"
                        monClient.ecrireServeur(clientMsg.getText());
                        if (clientMsg.getText().equals("fin")) { 
                            serveurMsg.append(monClient.lireServeur());                            
                            connexionBtn.setEnabled(false);
                            clientMsg.setText("");
                            clientMsg.setVisible(false);
                            clientMsg.setEditable(false);
                            JOptionPane.showMessageDialog(null,"Déconnexion du serveur effectué ","Information",JOptionPane.INFORMATION_MESSAGE);
                            terminerBtn.setText("Quitter");
                            terminerBtn.setEnabled(true);                        
                        }
                        // Faire Sinon
                        else {                        
                            serveurMsg.append(monClient.lireServeur()+" : "+clientMsg.getText()+'\n'); //Ecrire ce que le serveur répond dans la jTextArea du serveur
                            clientMsg.setText("");
                            clientMsg.requestFocus();
                        }
                }   
            }    
            
        }
    }
}

    // constructeur par défaut
    FenetreClient() {
        setTitle("Interface Client pour se connecter à serveur spécifié et un port indiqué");
        setSize(200,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // choix du comportement à la fermeture de la fenetre 
        // Appelle des méthodes de créations du menu , des panneaux d'entrées, de messages et des boutons de contrôle
        creerMenu();                   // appelle de la méthode "creerMenu()"
        creerPanneauEntrees();        // appelle de la méthode "creerPanneauEntrees()"
        creerPanneauCsMsgs();        // appelle de la méthode "creerMenu()"
        creerPanneauCtrlBoutons();  // appelle de la méthode "creerMenu()"   
                    //CREATION DE NOTRE TIMER
            Timer timer = new Timer(1000, new HorlogeListener());//Nouveau thread (timer) va se creer
            timer.start();//Lancer l'affichage du timer
    } 
       
    
    	public void run() {
		while (true) {
                   	;		
                    }
	}  


   /*
   GESTION DU TIMER ET DU CONTENU DES JLIST QUAND VALEUR DE L'HEURE OU DATE DEPASSE OU OBSOLETE
   -->ASSOCIATION D'UNE CLASSE DÉTECTEUR D'ÉVÉNEMENT (ÉCOUTEUR)AU TIMER
   */
    class HorlogeListener implements ActionListener {
        //ce que l'on fait quand on détecte un événement : 
        public void actionPerformed(ActionEvent e) {
          //MON FORMAT AFFICHAGE DATE ET HEURE COURANTE DANS HORLOGE
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            labeldateheure1.setText(df.format(Calendar.getInstance().getTime()));
            
        }
                  
        
   } 

}


