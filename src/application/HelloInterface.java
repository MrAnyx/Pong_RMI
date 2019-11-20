package application;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote{

	//Getters
	public double getPlayerOneX() throws RemoteException;
	public double getPlayerOneY() throws RemoteException;
	
	public double getPlayerTwoX() throws RemoteException;
	public double getPlayerTwoY() throws RemoteException;
	
	public double getBallX() throws RemoteException;
	public double getBallY() throws RemoteException;
	
	public int getBallSpeedX() throws RemoteException;
	public int getBallSpeedY() throws RemoteException;
	
	public boolean getGameStarted() throws RemoteException;
	
	public int getScoreOne() throws RemoteException;
	public int getScoreTwo() throws RemoteException;
	
	//Setters
	public void setPlayerOneY(double playerOneY) throws RemoteException;
	
	public void setPlayerTwoY(double playerTwoY) throws RemoteException;
	
	public void setBallX(double ballX) throws RemoteException;
	public void setBallY(double ballY) throws RemoteException;
	
	public void setBallSpeedX(int ballSpeedX) throws RemoteException;
	public void setBallSpeedY(int ballSpeedY) throws RemoteException;
	
	public void setGameStarted(boolean gameStarted) throws RemoteException;
	
	public void setScoreOne(int scoreOne) throws RemoteException;
	public void setScoreTwo(int scoreTwo) throws RemoteException;
	
}
