package test.attentionframework.Lobes.FrontalLobe;

import java.rmi.RemoteException;
import test.attentionframework.Client.client;
import test.attentionframework.Server.WorkingMemory;
import test.attentionframework.spike.Spike;

public class VLPFC {
    
    private WorkingMemory workingMemory;
    private String
            target = "F";
    public VLPFC(WorkingMemory workingMemory) {
        this.workingMemory = workingMemory;
        workingMemory.setTarget(target);
    }
    
    public void maintenceOfTheTargetInformation(Spike s) throws RemoteException {
        workingMemory.setTarget(target);
        s.changeOriginAreaandDestination(s, "VLPFC", "STS");
        client cliente = new client();
        cliente.enviarSike(s, "SpecificArea", 1099);
        
        
    }

}
