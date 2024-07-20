package test.attentionframework.Lobes.FrontalLobe;

import java.rmi.RemoteException;
import java.util.List;
import test.attentionframework.Client.client;
import test.attentionframework.spike.Spike;

public class MFG {

    public void circuitBreaker(Spike s) throws RemoteException {
        
        List<Object> information = s.getInformation();
        List<String> elements = (List<String>) information.get(0);
        client cliente = new client();
        System.out.println(information);
            
        List<String> infoTarget = (List<String>) information.get(2);
        String targetType = infoTarget.get(1);
        if ("Congruente".equals(targetType)) {
            s.changeOriginAreaandDestination(s, "MFG", "IFG");
            cliente.enviarSike(s, "Gyro", 1099);
        }else{
            s.changeOriginAreaandDestination(s, "MFG", "fO");
            cliente.enviarSike(s, "SpecificArea", 1099);
        }
    }

}
