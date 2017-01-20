/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dateheure;
/**
 *
 * @author Bernardin Houessou
 */

/**********************************************************************************************************************
 //AUTRES PACKAGES POUVANT ETRE UTILISES QUE JE N'AI PAS UTILISE
***********************************************************************************************************************
import java.text.DateFormat;//Pour l'utilisation de la classe 'DateFormat' de la bibrairie AWT specialement
import java.util.Locale;//Pour l'utilisation de la classe 'Locale' de la bibrairie UTIL specialement
import java.util.Timer.*;//Pour l'utilisation de la classe 'Timer' de la bibrairie UTIL specialement
import javax.swing.border.*; //Pour l'utilisation de la classe 'border' de la bibrairie SWING specialement pour gérer les bordures des composants Swing
import java.awt.Dialog ;//Pour l'utilisation de la classe 'Dialog' de la bibrairie AWT specialement pour la création de fenêtre de dialogue avec l'utilisateur
import javax.swing.JList;//Pour l'utilisation de la classe 'JList' de la bibrairie SWING specialement pour gérer les listes 
import java.io.*;//Pour l'utilisation de la classe 'io' pour la gestion des flux d'entrées et de sorties. 
*********************************************************************************************************************
 */

//LISTE DE MES PACKAGES UTILISES
    import java.awt.*;//Pour l'utilisation de toutes les classes de la bibrairie AWT(i.e Toolkit) pour interfaces graphiques
    import java.awt.event.*;//Pour l'utilisation de la classe 'event' de la bibrairie AWT specialement pour la gestion des événements utilisateurs
    import javax.swing.*;//Pour l'utilisation de toutes les classes de la bibrairie SWING pour Swing pour développer des interfaces graphiques
    import javax.swing.Timer;//Pour l'utilisation de la classe 'Timer' de la bibrairie SWING specialement
    import javax.swing.JOptionPane;//Pour l'utilisation de la classe 'JOptionPane' de la bibrairie SWING specialement
    import java.util.Calendar;//Pour l'utilisation de la classe utilitaire 'Calendar' de la bibrairie UTIL specialement pour la gestion des dates
    import java.text.SimpleDateFormat;//Pour l'utilisatin de la classe 'SimpleDateFormat' de la bibrairies AWT specialement


/**********************************************************************************************************************
CREATION DE MA CLASSE "DateHeure" CONTENANT MA FENÊTRE PRINCIPALE ET AUTRES...
***********************************************************************************************************************
  + 1 JLabel "labeldateheure1" qui est un label pour y afficher la date et l'heure  
  + 4 boutons: "set" pour configurer les alarmes en ouvrant une fenetre jdialog  popup ,"quitter" pour fermer ma fenêtre principale ouverte ou mon application
    ,"effacer une alarme" pour effacer un des éléme,ts de la liste des alarmes
    ,"effacer la liste des alarmes" pour effacertous les éléments de la liste des alarmes .
  + une jlist1 contenant ma liste des messages et une autre jlist2 contenant ma liste des alarmes 
 *********************************************************************************************************************
 */
public class DateHeure extends JFrame {
//CREATION DES VARIABLES POUR MA FENETRE PRINCIPALE ET MON POPUP
  JFrame frame;                               //fenêtre principale 
  JButton settime;                           //btn set
  JButton quitterApp;                       //btn quitter
  JButton resetAll;                        //btn quitter
  JButton resetOne;                       //btn quitter
  JLabel labeldateheure1;                //label pour y aficher l'heure et la date 
  JButton ok;                           //btn OK
  JButton Cancel;                      //btn Cancel
  JPanel panel;                       //Panneau panel pour contenir mon borloge et mes boutons set et quitter...
  JPanel paneldroit;                 //Panneau paneldroit pour contenir mes JLIST list1 et list2
//CREATION DES VARIABLES POUR MA JLIST
  DefaultListModel listModel1;       //Modèle de ma JList 1
  DefaultListModel listModel2;      //Modèle de ma JList 2
  JList list1;                     //JList 1
  JList list2;                    //JList 2
  int count=0;                   //compteur pour les elements de la JList list1 et list2
 

/******************************************************************************************************************
CREATION DE MA CLASSE "BoiteDeDialogue" CONTENANT MA FENÊTRE POPUP
***********************************************************************************************************************
  + Pour la saisie par 'utilisateur de la date/l'heure ainsi que son message à afficher 
  + 2 boutons: "ok" pour Configurer et ajouter des alarmes,"cancel" pour fermer ma fenêtre popup ouverte
 *********************************************************************************************************************
 */
    public class BoiteDeDialogue extends JDialog {
   //CREATION DES VARIABLES POUR MA FENETRE SECONDAIRE POPUP
        JTextField message;        //Zone de texte pour y saisir le message 
        JTextField dateheure2;    //Zone de texte pour y saisir l'heure ou la date  
        JPanel panneau;          // Panneau "panneau" pour contenir ma zone de texte de texte et autres...


       //CREATION DU CONSTRUCTOR DE "BoiteDeDialogue" FENETRE SECONDAIRE
        public  BoiteDeDialogue() {                     
            Box boite = Box.createVerticalBox();                               //création d'une boîte affichant ses composants de haut en bas
          //AJOUTER UN PANNEAU AVEC UNE ZONE DE TEXTE DE TEXTE ET AUTRES... 
            panneau = new JPanel();                                          //Instantiation du Jpanel  "panneau"
            // 
            panneau.add(new JLabel("Message:"));                            //Ajout du label "Message:" dans le panneau
            message=new JTextField("Le message à afficher");               //Instantiation de la zone de texte  "message"
            message.setFont(new Font("sansserif", Font.PLAIN, 20));       //Mise en forme du contenu de la zone de texte "message"
            panneau.add(message);                                        //Ajout du label la zone de texte "message:" dans le panneau 
            // 
            panneau.add(new JLabel("Date:"));                           //Ajout du label "Date" dans le panneau
            dateheure2=new JTextField("ActueldateHeure");              //Instantiation de la zone de texte  "dateheure2"
            dateheure2.setFont(new Font("sansserif", Font.PLAIN, 20));//Mise en forme du contenu de la zone de texte "dateheure2"
            //MON FORMAT AFFICHAGE DATE ET HEURE 
            SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  //Instantiation de SimpleDateFormat au format donné
            dateheure2.setText(df1.format(Calendar.getInstance().getTime()));   //Renvoie un Calendar objet dont les champs ont été initialisé avec la date et l'heure après calcul de la valeur              
            panneau.add(dateheure2);                                           //Ajout de "dateheure2" dans le panneau
            boite.add(panneau);                                               //Ajout du panneau dans la boîte
            add(boite) ;                                                     //Ajout de la boîte à ma  BoiteDeDialogue JDialog
            //INSTANTIATION DU BOUTON "OK",AJOUT DS LE PANNEAU,CREATION DE SON ÉCOUTEUR
            ok=new JButton("OK");                                          //instanciation du bouton "OK"           
            panneau.add(ok);                                              //Ajout du bouton "OK" dans le panneau 
            ok.setFont(new Font("sansserif", Font.PLAIN, 20));           //Mise en forme du bouton "OK"  
            //ASSOCIATION DU DÉTECTEUR D'ÉVÉNEMENT (ÉCOUTEUR)AU BOUTON "OK"  
            ok.addActionListener(new ActionListener() {
            //ce que l'on fait quand on détecte un événement :    
                public void actionPerformed(ActionEvent e) 
                {
                //AJOUT DE NOUVEAUX ELEMENTS DANS (MA/MES) JLIST(S)                 
                    listModel1.addElement(message.getText());    // Ajout du contenu de la zone de texte "message" dans le listModel1
                    listModel2.addElement(dateheure2.getText());// Ajout du contenu de la zone de texte "dateheure2" dans le listModel2
                    count++;                                   //Incrementation des elements de la JList
                    dispose();                                //Fermer la fenetre actuellement ouverte (i.e ma Boite de Dialogue ou popup)
                }
            });  

            //INSTANTIATION DU BOUTON "Cancel",AJOUT DS LE PANNEAU,CREATION DE SON ÉCOUTEUR
            Cancel=new JButton("Cancel");                           //instanciation du bouton "Cancel"           
            Cancel.setFont(new Font("sansserif", Font.PLAIN, 20)); //Mise en forme du bouton "Cancel"  
            panneau.add(Cancel);                                  //Ajout du bouton "OK" dans le panneau 
            //ASSOCIATION DU DÉTECTEUR D'ÉVÉNEMENT (ÉCOUTEUR)AU BOUTON "Cancel"  
            Cancel.addActionListener(new ActionListener() {
            //ce que l'on fait quand on détecte un événement :   
                public void actionPerformed(ActionEvent e) {
                        dispose();//Fermer la fenetre actuellement ouverte (i.e ma Boite de Dialogue ou popup)
                }
            });
            //LES PROPRIETES DE MON POPUP OU MA BOITE DE DIALOGUE 
          /***************
              setModal(true);//Indique que la boîte de dialogue est modale mais 
          ++ cette méthode est obsolète
             modale - spécifie si les blocs de dialogue entrée à d'autres fenêtres lorsque indiqué; 
             appelant à setModal (vrai) est équivalent à setModalityType (Dialog.DEFAULT_MODALITY_TYPE),
             et appelant à setModal (false) est equvivalent à setModalityType (Dialog.ModalityType.MODELESS)
          ***************
          */
          setModalityType(DEFAULT_MODALITY_TYPE); //Définit le type de modalité de ma boîte de dialogue en empêchant l'accès aux autres fenêtres lorsque celle ci est ouverte.
          setTitle("Configuration d'un Alarme ");//Définition du titre de ma boîte de Dialogue ou popup
          setSize(300, 150);                    //Definir la taille de ma boîte de Dialogue ou popup
          setLocationRelativeTo(null);        //Definir la localisation de ma boîte de Dialogue ou popup par défaut=centrale
          setVisible(true);                  //Rendre visible ma boîte de Dialogue ou popup 
           
        }
 }  
       //CREATION DU CONSTRUCTOR DE "DateHeure" FENETRE PRINCIPALE
        public DateHeure() {
            
          //LES PROPRIETES DE MA FENETRE PRNCIPALE "DateHeure" affichant l'heure et autre...
            this.setTitle("Affichage de la date et de l'heure");//Définition d'un titre à ma fenetre principale 
            this.pack();                       //Permet le dimensionnement de la fenetre en fonction de la taille et de la mise en page de ses sous-composants.
            this.setSize(900, 350);           //Definir la taille ou la résolution de la fenetre principale
            this.setLocationRelativeTo(null);//Definir la localisation de la fenetre principale par défaut=centrale
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//La fermeture de la fenetre principale cause celle de l'application
            
          //ELEMENTS DU PANEL             
            panel = new JPanel();                                           //Instantiation du JPanel  "panel"
                //CREATION DU CONTAINER OU CONTENT PANEL
            Container c = getContentPane();                                //Renvoie l'objet contentPane pour cette fenetre ou trame.       
            c.setLayout(new BorderLayout());                              //ajout d'une nouvelle bordure ou frontière au container
            c.add(panel, BorderLayout.SOUTH);                            // Ajout de la frontière Sud au panneau panel
            settime=new JButton("Set");                                          //Instantiation du bouton "Set"     
            quitterApp=new JButton("Quitter");                                  //Instantiation du bouton "Quitter"     
            labeldateheure1 = new JLabel("Horloge");                           //Instantiation du label "labeldateheure1" pour y afficher l'heure     
            labeldateheure1.setFont(new Font("sansserif", Font.PLAIN, 70));   //Mise en forme du label "labeldateheure1"
            panel.add(settime);                                         // Ajout du bouton "Set"  au panneau panel
            panel.add(quitterApp);                                     // Ajout du bouton "Quitter" au panneau panel
            panel.setLayout(new FlowLayout());                        //ajout d'une nouvelle bordure ou frontière au panneau panel
            c.add(labeldateheure1, BorderLayout.NORTH);              // Ajout de la frontière Nord à labeldateheure1
            //ASSOCIATION DU DÉTECTEUR D'ÉVÉNEMENT (ÉCOUTEUR)AU BOUTON "set"  
            settime.addActionListener(new ActionListener() {
               //ce que l'on fait quand on détecte un événement :
                public void actionPerformed(ActionEvent e) {
                      //Instantiation de la classe BoiteDeDialogue
                        BoiteDeDialogue alarmefentre1=new BoiteDeDialogue();  
                }       

            });   
            //ASSOCIATION DU DÉTECTEUR D'ÉVÉNEMENT (ÉCOUTEUR)AU BOUTON "Quitter" 
            quitterApp.addActionListener(new ActionListener() {
               //ce que l'on fait quand on détecte un événement :
                public void actionPerformed(ActionEvent e) {
                       System.exit(0);//Sort de l'application ou ferme la fenetre principale
                }
            }); 
            

          //ELEMENTS DU PANELDROIT
            paneldroit = new JPanel();                             //Instantiation du JPanel  "paneldroit"
            resetAll=new JButton("Effacer la liste des alarmes ");//Instantiation du bouton "resetAll"
            resetOne=new JButton("Effacer une alarme ");         //Instantiation du bouton "resetOne"
            c.add(paneldroit, BorderLayout.EAST);               // Ajout de la frontière Est au panneau paneldroit
            paneldroit.add(resetAll);                          // Ajout du bouton resetAll au panneau paneldroit
            paneldroit.add(resetOne);                         // Ajout du bouton resetOne au panneau paneldroit
            /*GESTION DE LA SUPPRESSION DES ELEMENTS DE (LA/LES) JLIST list1 et list2
              ---->ASSOCIATION DU DÉTECTEUR D'ÉVÉNEMENT (ÉCOUTEUR)AU BOUTON resetOne */
            resetOne.addActionListener(new ActionListener() {
                //ce que l'on fait quand on détecte un événement :
                public void actionPerformed(ActionEvent e) {        
                    //JList list1 et JList list 2
                    if(list1.getSelectedIndices().length > 0||list2.getSelectedIndices().length > 0) 
                    {   
                        int[] selectedIndices1 = list1.getSelectedIndices();//Creation d'un array d'entier pour la récupération de tableau avec tous les indices sélectionnés, dans l'ordre croissant
                        int[] selectedIndices2 = list2.getSelectedIndices();//Creation d'un array d'entier pour la récupération de tableau avec tous les indices sélectionnés, dans l'ordre croissant   
                        //Dans le cas où...
                        for (int i = selectedIndices1.length-1; i >=0; i--) 
                        {
                            listModel1.removeElementAt(selectedIndices1[i]);//Supprimer l'élément sélectionné du (modèle de la Jlist N° 1) dont l'index est i
                            //Si list2 n'a aucun element selectioné
                            if(list2.isSelectionEmpty())
                            {
                            int j=selectedIndices1.length-1;
                            listModel2.removeElementAt(selectedIndices1[j]);//Supprimer l'élément sélectionné du (modèle de la Jlist N° 2) dont l'index est j
                            }
                        }
                        //Dans le cas où...
                        for (int z = selectedIndices2.length-1; z >=0; z--) 
                        {

                            listModel2.removeElementAt(selectedIndices2[z]);//Supprimer l'élément sélectionné du (modèle de la Jlist N° 2) dont l'index est z
                            //Si list2 n'a aucun element selectioné
                            if(list1.isSelectionEmpty())
                            {
                            int r=selectedIndices2.length-1;
                            listModel1.removeElementAt(selectedIndices2[r]);//Supprimer l'élément sélectionné du (modèle de la Jlist N° 1) dont l'index est r
                            }
                        }       
                    }        
                    /*
                        + Si tous les éléments d'une ou des JList list1 ou list2 sont supprimer 1 à 1 et qu'il ne reste plus aucun éléments dans les JList *
                        + getSize():recupère la taille (du/des) modèle(s)de Jlist...
                    */ 
                    if((listModel1.getSize() == 0)||(listModel2.getSize() == 0))
                        count=0;//réinitialise le compeur de la JList list1 et list2 à zéro
                   }

                });

            listModel1 = new DefaultListModel();                         //instanciation du modèle listModel1
            listModel2 = new DefaultListModel();                        //instanciation du modèle listModel2
            list1 = new JList(listModel1);                             //instanciation de la JList list1
            list2 = new JList(listModel2);                            //instanciation de la JList list2
            JScrollPane scrollpanedroite1 = new JScrollPane(list1);  //instanciation du JScrollPane scrollpanedroite1
            JScrollPane scrollpanedroite2 = new JScrollPane(list2); //instanciation du JScrollPane scrollpanedroite2
            scrollpanedroite1.setVisible(false);                   //Rendre invisible le JScrollPane scrollpanedroite1 contenant les messages
            paneldroit.add(scrollpanedroite1, BorderLayout.NORTH);
            paneldroit.add(scrollpanedroite2, BorderLayout.WEST);


            /*GESTION DU VIDAGE COMPLET OU DE LA SUPPRESSION DE TOUS LES ELEMENTS DE (LA/LES) JLIST list1 et list2
            ---->ASSOCIATION DU DÉTECTEUR D'ÉVÉNEMENT (ÉCOUTEUR)AU BOUTON resetAll */
            resetAll.addActionListener(new ActionListener() {
             //ce que l'on fait quand on détecte un événement :    
             public void actionPerformed(ActionEvent e) {
                //Effacer tout le contenu des  Jlist list1 et list2
                     if (listModel1.getSize() > 0)
                     {   
                         listModel1.clear();
                     }    
                     if (listModel2.getSize() > 0)
                     {   
                         listModel2.clear();//
                     } 
                     count=0;//réinitialise le compeur de la JList list1 et list2 à zéro
                 }
             });
            //CREATION DE NOTRE TIMER
            Timer timer = new Timer(1000, new HorlogeListener());//Nouveau thread (timer) va se creer
            timer.start();//Lancer l'affichage du timer
            
        }

   /*
   GESTION DU TIMER ET DU CONTENU DES JLIST QUAND VALEUR DE L'HEURE OU DATE DEPASSE OU OBSOLETE
   -->ASSOCIATION D'UNE CLASSE DÉTECTEUR D'ÉVÉNEMENT (ÉCOUTEUR)AU TIMER
   */
    class HorlogeListener implements ActionListener {
        //ce que l'on fait quand on détecte un événement : 
        public void actionPerformed(ActionEvent e) {
          //MON FORMAT AFFICHAGE DATE ET HEURE DANS HORLOGE
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            labeldateheure1.setText(df.format(Calendar.getInstance().getTime()));
            //
            for(int u = 0; u < listModel1.getSize(); u++) 
            {   
                    int t=u;//
                    String labelDH=labeldateheure1.getText();//récupère le contenu du label "labeldateheure1" contenant la date et l'heure réelle au niveau de l'horloge
                    String listM2Ele=listModel2.getElementAt(u).toString();//récupère le contenu du modèle "listModel2" à la position u et le converti en chaîne de caractères
                /*
                    //Comparaison des valeurs de dateheure de l'horlorge à celle entré par l'utilisateur   
                      Respectivement dans les chaînes de caractères labelDH et listM2Ele
                */    
                if(labelDH.compareTo(listM2Ele)>=0)
                {   
                    if(listModel2.getElementAt(u).equals(labeldateheure1.getText()))
                    {
                    //ce que l'on fait 
                    Toolkit.getDefaultToolkit().beep();//Emission de bruit de beep par défaut   
                    //Affichage de l'alarme avec son N° ,son message dans un popup avec une icon ! attention triangle jaune 
                    JOptionPane.showMessageDialog(frame, listModel1.getElementAt(t).toString(), "Alarm N°"+count, JOptionPane.WARNING_MESSAGE);                  
                    }  
                    else 
                    {
                    //ce que l'on fait sinon 
                    listModel2.removeElementAt(u); //Suppression de l'élément à la position u   
                    listModel1.removeElementAt(t); //Suppression de l'élément à la position t
                    } 
                }
            }        
        }
   } 
   
}   
