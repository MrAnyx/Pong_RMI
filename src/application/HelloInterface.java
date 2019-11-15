package application;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloInterface extends Remote{

	public int foisDeux(int valeur) throws RemoteException;
	
}
