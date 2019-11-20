package application;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote{

	//Getters
	public int getPlayerOneX() throws RemoteException;
	public int getPlayerOneY() throws RemoteException;
	
	public int getPlayerTwoX() throws RemoteException;
	public int getPlayerTwoY() throws RemoteException;
	
	public int getBallX() throws RemoteException;
	public int getBallY() throws RemoteException;
	
	public int getBallSpeedX() throws RemoteException;
	public int getBallSpeedY() throws RemoteException;
	
	public boolean getGameStarted() throws RemoteException;
	
	public int getScoreOne() throws RemoteException;
	public int getScoreTwo() throws RemoteException;
	
	//Setters
	public void setPlayerOneY(int playerOneY) throws RemoteException;
	
	public void setPlayerTwoY(int playerTwoY) throws RemoteException;
	
	public void setBallX(int ballX) throws RemoteException;
	public void setBallY(int ballY) throws RemoteException;
	
	public void setBallSpeedX(int ballSpeedX) throws RemoteException;
	public void setBallSpeedY(int ballSpeedY) throws RemoteException;
	
	public void setGameStarted(boolean gameStarted) throws RemoteException;
	
	public void setScoreOne(int scoreOne) throws RemoteException;
	public void setScoreTwo(int scoreTwo) throws RemoteException;
	
}
