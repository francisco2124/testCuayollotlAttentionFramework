package test.attentionframework.Lobes;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import javax.swing.text.Position;
import test.attentionframework.Client.client;
import test.attentionframework.Server.WorkingMemory;
import test.attentionframework.spike.Spike;

public class TPJ {

    private WorkingMemory workingMemory;

    public TPJ(WorkingMemory workingMemory) {
        this.workingMemory = workingMemory;
    }
    int relevantPosition = 0;
    
    public void reorientingOfAttention(Spike s) throws RemoteException {
        
        List<Object> information = s.getInformation();
        List<String> elements = (List<String>) information.get(0);
        client cliente = new client();
        
        // Verificar el area de origen
        if (elements.size() > 0 && "IPL".equals(elements.get(0))) {
            information = s.getInformation();
            List<Object>  thirdList = (List<Object>) information.get(2);
            relevantPosition = (int) thirdList.get(0);
            workingMemory.setRelevantPosition(relevantPosition);
            
        }else if(elements.size() > 0 && "IPS".equals(elements.get(0))){
            s.changeOriginAreaandDestination(s, "TPJ", "STS");
            cliente.enviarSike(s, "SpecificArea", 1099);
            
        }else if(elements.size() > 0 && "STG".equals(elements.get(0))){
            //System.out.println("*******Information**********");
            //System.out.println(information);
            s.changeOriginAreaandDestination(s, "TPJ", "DecisionMaking");
            cliente.enviarSike(s, "Lobe", 1099);
            /*
            List<String> infoTarget = (List<String>) information.get(2);
            String targetType = infoTarget.get(1);
            if ("Congruente".equals(targetType)) {
                s.changeOriginAreaandDestination(s, "TPJ", "IFG");
                cliente.enviarSike(s, "Gyro", 1099);
            }else{
                s.changeOriginAreaandDestination(s, "TPJ", "fO");
                cliente.enviarSike(s, "SpecificArea", 1099);
            }*/
        }else{
            System.out.println(elements.get(0));
            System.out.println("No se tiene una funcionalidad para el area de origen");
        }
    }
    
}
