package test.attentionframework.Lobes.ParietalLobe;

import java.rmi.RemoteException;
import test.attentionframework.Client.client;
import test.attentionframework.spike.Spike;

public class IPL {

    public void saccadicEyeMovements(Spike s) throws RemoteException {

        s.changeOriginAreaandDestination(s, "IPL", "TPJ");
        
        client cliente = new client();

        cliente.enviarSike(s, "Lobe", 1099);
    }

}
