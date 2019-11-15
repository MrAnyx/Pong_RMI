package application.serveur;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.registry.Registry;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CtrlServeur {

	@FXML TextField tbIP;
	@FXML TextField tbPort;
	@FXML TextField tbNom;
	
	@FXML TextArea taInfo;
	
	private int port;
	private String addressIP;
	private String nom;
	
	private void afficheInfoReseau() {
		
		try {
			InetAddress inetadr = InetAddress.getLocalHost();
			addressIP = inetadr.getHostAddress();
			
			port = 19000;
			nom = "Pong_Serveur";
			
			tbIP.setText(addressIP);
			tbPort.setText(String.valueOf(port));
			tbNom.setText(nom);
			
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean lancerAnnuaire() {
		try {
			Registry refRegistry = java.rmi.registry.LocateRegistry.createRegistry(port);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public boolean lancerServeur() {
		return false;
	}
	
	
	
}
