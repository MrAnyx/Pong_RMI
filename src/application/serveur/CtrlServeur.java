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

	private static final long serialVersionUID = 1L;


	public CtrlServeur() throws RemoteException {
		super();
	}

	@FXML TextField tbIP;
	@FXML TextField tbPort;
	@FXML TextField tbNom;
	
	@FXML TextArea taInfo;
	
	@FXML TextField tbStatus;
	
	private int port;
	private String addressIP;
	private String nom;
	
	private Registry refRegistry;
	
	
	
	
	
	// variables jeu
	private static final int width = 800;
	private static final int height = 600;
	
	public static final int PLAYER_HEIGHT = 100;
	public static final int PLAYER_WIDTH = 15;
	public static final double BALL_R = 15;
	
	private int ballYSpeed = 1;
	private int ballXSpeed = 1;
	
	private double playerOneYPos = height / 2;
	private double playerTwoYPos = height / 2;
	
	private double playerOneXPos = 0;
	private double playerTwoXPos = width - PLAYER_WIDTH;
	
	private double ballXPos = width / 2;
	private double ballYPos = height / 2;
	
	private int scoreP1 = 0;
	private int scoreP2 = 0;
	
	private boolean gameStarted;
	
	
	@FXML private void initialize() {
		taInfo.appendText("Lancement de la fenêtre\n");
		afficheInfoReseau();
		tbStatus.setStyle("-fx-background-color:#ff0000");
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

			tbStatus.setStyle("-fx-background-color:#00ff00");
		}else {
			stopperServeur();

			tbStatus.setStyle("-fx-background-color:#ff0000");
		}
		
	}

	@Override
	public double getPlayerOneX() throws RemoteException {
		return this.playerOneXPos;
	}

	@Override
	public double getPlayerOneY() throws RemoteException {
		return this.playerOneYPos;
	}

	@Override
	public double getPlayerTwoX() throws RemoteException {
		return this.playerTwoXPos;
	}

	@Override
	public double getPlayerTwoY() throws RemoteException {
		return this.playerTwoYPos;
	}

	@Override
	public double getBallX() throws RemoteException {
		return this.ballXPos;
	}

	@Override
	public double getBallY() throws RemoteException {
		return this.ballYPos;
	}

	@Override
	public int getBallSpeedX() throws RemoteException {
		return this.ballXSpeed;
	}

	@Override
	public int getBallSpeedY() throws RemoteException {
		return this.ballYSpeed;
	}

	@Override
	public boolean getGameStarted() throws RemoteException {
		return this.gameStarted;
	}

	@Override
	public int getScoreOne() throws RemoteException {
		return this.scoreP1;
	}

	@Override
	public int getScoreTwo() throws RemoteException {
		return this.scoreP2;
	}

	@Override
	public void setPlayerOneY(double playerOneY) throws RemoteException {
		this.playerOneYPos = playerOneY;
	}

	@Override
	public void setPlayerTwoY(double playerTwoY) throws RemoteException {
		this.playerTwoYPos = playerTwoY;
	}

	@Override
	public void setBallX(double ballX) throws RemoteException {
		this.ballXPos = ballX;
	}

	@Override
	public void setBallY(double ballY) throws RemoteException {
		this.ballYPos = ballY;
	}

	@Override
	public void setBallSpeedX(int ballSpeedX) throws RemoteException {
		this.ballXSpeed = ballSpeedX;
	}

	@Override
	public void setBallSpeedY(int ballSpeedY) throws RemoteException {
		this.ballYSpeed = ballSpeedY;
	}

	@Override
	public void setGameStarted(boolean gameStarted) throws RemoteException {
		this.gameStarted = gameStarted;
	}

	@Override
	public void setScoreOne(int scoreOne) throws RemoteException {
		this.scoreP1 = scoreOne;
	}

	@Override
	public void setScoreTwo(int scoreTwo) throws RemoteException {
		this.scoreP2 = scoreTwo;
	}
	
	
	
	
	
}
