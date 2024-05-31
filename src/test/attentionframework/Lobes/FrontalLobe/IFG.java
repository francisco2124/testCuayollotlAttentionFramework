package test.attentionframework.Lobes.FrontalLobe;

import java.rmi.RemoteException;
import test.attentionframework.spike.Spike;
import test.attentionframework.Client.client;

public class IFG {

    public void reorientingOfAttention(Spike s) throws RemoteException {
        System.out.println("Llego a IFG");
        s.changeOriginAreaandDestination(s, "IFG", "DecisionMaking");
        client cliente = new client();
        cliente.enviarSike(s, "Lobe", 1099);
    }

}
