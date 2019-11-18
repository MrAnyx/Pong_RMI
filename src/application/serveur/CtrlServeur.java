package application.serveur;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import application.HelloInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CtrlServeur extends UnicastRemoteObject implements HelloInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public CtrlServeur() throws RemoteException {
		super();
	}

	@FXML TextField tbIP;
	@FXML TextField tbPort;
	@FXML TextField tbNom;
	
	@FXML TextArea taInfo;
	
	private int port;
	private String addressIP;
	private String nom;
	
	private Registry refRegistry;
	
	
	@FXML private void initialize() {
		taInfo.appendText("Lancement de la fenêtre\n");
		afficheInfoReseau();
	}
	
	private void afficheInfoReseau() {
		
		try {
			InetAddress inetadr = InetAddress.getLocalHost();
			addressIP = inetadr.getHostAddress();
			
			port = 19000;
			nom = "Pong_Serveur";
			
			tbIP.setText(addressIP);
			tbPort.setText(String.valueOf(port));
			tbNom.setText(nom);

			taInfo.appendText("Affichage du réseau\n");
			
		} catch (UnknownHostException e) {

			taInfo.appendText("Erreur affichage réseau\n");
			System.out.println(e.getMessage());
		}
	}
	
	public boolean lancerAnnuaire() {
		try {

			taInfo.appendText("Lancement annuaire ...\n");
			refRegistry = java.rmi.registry.LocateRegistry.createRegistry(port);
			taInfo.appendText("Annuaire OK\n");
			return true;
		} catch (Exception e) {

			taInfo.appendText("Erreur annuaire\n");
			return false;
		}
	}
	
	
	public boolean lancerServeur() {
		try {
			taInfo.appendText("Lancement du serveur ...\n");
			refRegistry.rebind(nom, (Remote) this);
			taInfo.appendText("Serveur OK\n");
			return true;
		} catch (AccessException e) {
			taInfo.appendText("Erreur lors du lancement du serveur\n");
			System.out.println(e.getMessage());
			return false;
		} catch (RemoteException e) {
			taInfo.appendText("Erreur lors du lancement du serveur\n");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	private boolean stopperServeur() {
		try {
			taInfo.appendText("Arrete du serveur ...\n");
			refRegistry.unbind(nom);
			UnicastRemoteObject.unexportObject((Remote) this, true);
			UnicastRemoteObject.unexportObject(refRegistry, true);
			refRegistry = null;

			taInfo.appendText("Serveur arrêté\n");
			return true;
		} catch (NoSuchObjectException e) {

			taInfo.appendText("Erreur lors de l'arret du serveur\n");
			return false;
		} catch (AccessException e) {
			taInfo.appendText("Erreur lors de l'arret du serveur\n");
			return false;
		} catch (RemoteException e) {
			taInfo.appendText("Erreur lors de l'arret du serveur\n");
			return false;
		} catch (NotBoundException e) {
			taInfo.appendText("Erreur lors de l'arret du serveur\n");
			return false;
		}
	}
	
	@FXML private void actionClic(ActionEvent evt) {
		if(refRegistry == null) {
			lancerAnnuaire();
			lancerServeur();
		}else {
			stopperServeur();
		}
		
	}

	@Override
	public int foisDeux(int valeur) throws RemoteException {
		return valeur*2;
	}
	
	
	
	
	
}
