    package test.attentionframework.Lobes.ParietalLobe;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import test.attentionframework.Client.client;
import test.attentionframework.spike.Spike;
import test.attentionframework.Lobes.TPJ;
import test.attentionframework.Server.WorkingMemory;

public class IPS {

    private WorkingMemory workingMemory;

    public IPS(WorkingMemory workingMemory) {
        this.workingMemory = workingMemory;
    }
    
    public void directingEyeMovements(Spike s) throws RemoteException {
        int relevantPosition = 0;
        System.out.println("---------------------------------------");
        // Obtener el valor de relevantPosition
        try {
            int position = workingMemory.getRelevantPosition();
            System.out.println("RelevantPosition");
            System.out.println(position);
            relevantPosition = position;
        } catch (Exception e) {
            
        }
        
        try {
            int num = workingMemory.getNumStage();
            workingMemory.setNumStage(num+1);
        } catch (Exception e) {
            workingMemory.setNumStage(1);
        }
 
        
        client cliente = new client();
        if (relevantPosition == 0) {
            
            s.changeOriginAreaandDestination(s, "IPS", "FEF");
            
            cliente.enviarSike(s, "SpecificArea", 1099);
            
        }else{
            s.changeOriginAreaandDestination(s, "IPS", "TPJ");
            cliente.enviarSike(s, "Lobe", 1099);
        }
 
    }
        
}
