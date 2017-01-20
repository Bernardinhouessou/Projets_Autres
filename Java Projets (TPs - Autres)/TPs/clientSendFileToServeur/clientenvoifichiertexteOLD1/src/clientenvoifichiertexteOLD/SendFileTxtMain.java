/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientenvoifichiertexteOLD;

import javax.swing.JFrame;

/**
 * MAIN CLASS
 * 
 * @author Bernardin Houessou
 */
public class SendFileTxtMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {      
        
        // Création et initialisation de la fenêtre client       
        JFrame fenetre=new FenetreClient ();      // instantiation de la fenetre(construction) ...
        // Affichage de la fenêtre 
        fenetre.setVisible(true);               // rendre visisble ou active la fenetre au lancement          
        fenetre.pack();                        // fenetre propre
        fenetre.setLocationRelativeTo(null);  //  localistion au centre du bureau        
    //----------------PARTIE SERVEUR EXECUTION 
        // Pour tester un protocole simple de communication
        // Bien entendu il faut que serveur et client soient compatibles
            // initialisation du serveur
                    MonServeur  ms;
                    if (args.length >0)
                            ms = new MonServeur(Integer.parseInt(args[0]));
                    else
                            ms =new MonServeur(0);
                    System.out.println("On communique");
            // Envoie un message d'accueil
                    ms.ecrireClient("Bienvenue sur MonServeur - Pour quitter : taper \"fin\" ");

            // Ecoute du client
                    boolean continuer =true;
                    String ligne;
                    while(continuer && ms.clientOK()) {
                            ligne = ms.lireClient();
                            if (ligne.equalsIgnoreCase("fin")) {//peu importe la casse
                                    continuer = false;
                                    ms.ecrireClient("Au revoir");
                            }
                            System.out.println("Client : "+ligne);
                            ms.ecrireClient("recu");
                    }
                    System.out.println("On termine");
                    ms.fermer();
    //----------------FIN PARTIE SERVEUR EXECUTION   
    }
    
}
