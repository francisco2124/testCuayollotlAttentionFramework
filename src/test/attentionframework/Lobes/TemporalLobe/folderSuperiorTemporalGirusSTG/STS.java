package test.attentionframework.Lobes.TemporalLobe.folderSuperiorTemporalGirusSTG;

import java.rmi.RemoteException;
import java.util.List;
import test.attentionframework.Client.client;
import test.attentionframework.spike.Spike;

public class STS {

    public void spatialOrientation(Spike s) throws RemoteException{
        List<Object> information = s.getInformation();
        List<String> elements = (List<String>) information.get(0);
        client cliente = new client();
        // Verificar el area de origen
        if (elements.size() > 0 && "TPJ".equals(elements.get(0))) {
            s.changeOriginAreaandDestination(s, "STS", "VLPFC");
            cliente.enviarSike(s, "Gyro", 1099);
            
        }else if(elements.size() > 0 && "VLPFC".equals(elements.get(0))){
            s.changeOriginAreaandDestination(s, "STS", "STG");
            cliente.enviarSike(s, "Gyro", 1099);
        }else{
            System.out.println(elements.get(0));
            System.out.println("No se tiene una funcionalidad para el area de origen");
        }
    }

}
