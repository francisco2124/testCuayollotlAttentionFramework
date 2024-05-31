package test.attentionframework.Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import test.attentionframework.spike.Spike;

public interface receiveData extends Remote {
    void receiveDataLobes(Spike s) throws RemoteException;
    void receiveDataGyros(Spike s) throws RemoteException;
    void receiveDataSpecificAreas(Spike s) throws RemoteException;
}
