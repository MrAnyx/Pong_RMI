package application.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import application.HelloInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CtrlClient {
	
	@FXML TextField tbIP;
	@FXML TextField tbPort;
	@FXML TextField tbNom;
	
	@FXML TextArea taInfo;
	
	Registry registry = null;
	
	private String addressIP;
	private int port;
	private String nom;
	
	@FXML public void initialize() {
		tbPort.setText("19000");
		tbNom.setText("Pong_Serveur");
	}
	
	@FXML public void actionClic(ActionEvent evt) {
		
		addressIP = tbIP.getText();
		port = Integer.parseInt(tbPort.getText());
		nom = tbNom.getText();
		
		try {
			registry = LocateRegistry.getRegistry(addressIP, port);
			HelloInterface stub = (HelloInterface) registry.lookup(nom);
			
			MainClient.fenJeu.showAndWait();
			
		} catch (NumberFormatException | RemoteException e) {
			System.out.println(e.getMessage());
		} catch (NotBoundException e) {
			System.out.println(e.getMessage());
		}
	}
	

}
