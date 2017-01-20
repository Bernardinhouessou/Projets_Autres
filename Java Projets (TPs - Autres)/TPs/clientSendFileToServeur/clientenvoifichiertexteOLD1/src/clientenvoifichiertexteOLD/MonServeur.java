/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientenvoifichiertexteOLD;

import java.io.*;
import java.net.*;

/**
 * SERVEUR CONSOLE CLASS
 * 
 * @author Bernardin Houessou
 */
public class MonServeur {

	private ServerSocket serveur = null;
	private Socket client = null;
	private BufferedReader entree;
	private PrintWriter sortie;

	/** Contructeur qui specifie le port ecoute devrait etre au dessus de 1024
	  * @param port le numero du port
	  */
	public MonServeur(int port) {
		if (port < 1023) {// ports reserves
			System.err.println("erreur de choix du port ! 2222 par defaut");
			port =2222;
		}
		try{
		// on cree un serveur sur le port specifie
			serveur = new ServerSocket(port);
		}
		catch(IOException e){
			System.err.println("Impossible d'écouter le port "+port);
            		System.exit(1);
        		}
		System.out.println("J'ecoute le port: "+port+"...");
		try{
			serveur.setSoTimeout(10000); // attend au max 10s
		// s'il y a une requete sur le port
		// on cree un Socket pour communiquer avec le client
		// On attend jusqu'a ce qu'il y ait une requete
			client = serveur.accept(); //"client" est le Socket
		}
		catch (SocketTimeoutException e){
			System.err.println("On quitte : TimeOut");
			System.exit(1);
		}
		catch (IOException e) {
				System.err.println("Client refusé !.");
				System.exit(1);
        		}
		System.out.println("client accepté!");
		try {
		// on recupere les canaux de communication 
		// avec des filtres de lecture ecriture de donnees texte
			entree = new BufferedReader(new
			 InputStreamReader(client.getInputStream() ) );
			sortie = new PrintWriter(client.getOutputStream() );
		}
		catch (IOException e) {
            		System.err.println("PB creation des streams");
           		System.exit(1);
        	}
		if (entree == null)
			System.out.println("pas d'entree !!!");
		if (sortie == null)
			System.out.println("pas de sortie !!!");


	}

	/** lit les caracteres envoyŽs par le client.
		* @return un objet String qui contient l'ensemble des caractres lus
		*/
	public String lireClient(){
		String ligne=null;
		try{
		 	ligne = entree.readLine();
		}
		catch (IOException e) {
            		System.err.println("rien a lire");
		}
		return ligne;
	}

	/** Envoie des donnŽes au client.
		* @param ligne les caractres ˆ envoyer
		*/
	public void ecrireClient(String ligne){
		if (sortie == null)
			System.out.println("pas de sortie !!! : ecrire ?");
		else{
			sortie.println(ligne);
			sortie.flush();
		}
	}
	
	/** teste la connexion.
		*@return un boolŽen notifiant l'Žtat de la connexion
		*/
	public boolean clientOK(){
		return client.isConnected();
	}

	/** Fermeture du socket.
		*/
	public void fermer(){
	// il faut fermer "proprement" les stream avant les Sockets
		try{
			entree.close();
			sortie.close();
			if (client != null)
				client.close();
			if (serveur != null)
				serveur.close();
			System.out.println ("Fermeture ok");
		}
		catch(IOException e){
			System.err.println("Erreur ˆ la fermeture des flux!");
		}
	}

	protected void finalize(){
	// mŽthode executee par le ramasse miettes avant de liberer la memoire
	// pb : on ne sait jamais trop quand !!!
		fermer();
	}

	

	public static void main(String[] args){
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
	}

}
