/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dateheure;

import javax.swing.JFrame;

/**
 *
 * @author Bernardin Houessou
 */
public class DateHeureMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                // Création et initialisation de la fenêtre
        JFrame fenetre=new DateHeure ();      // instantiation de la fenetre "DateHeure"(construction) ...
        // Affichage de la fenêtre 
        fenetre.setVisible(true);               // rendre visisble ou active la fenetre au lancement          
        //fenetre.pack();                        // fenetre propre
        fenetre.setLocationRelativeTo(null);  //  localistion au centre du bureau
    }
    
}
