package test.attentionframework.Lobes.FrontalLobe.folderInferiorFrontalGirusIFG;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import test.attentionframework.Client.client;
import test.attentionframework.Server.WorkingMemory;
import test.attentionframework.spike.Spike;

public class fO {
    private WorkingMemory workingMemory;

    public fO(WorkingMemory workingMemory) {
        this.workingMemory = workingMemory;
    }
    public void responseInibition(Spike s) throws RemoteException {
        System.out.println("Llego a Fo");
        List<Object> information = s.getInformation();
        // Obtener el array de strings del segundo elemento
        String[] array = (String[]) information.get(1);
        // Convertir el array de strings en una lista de strings
        List<String> elements = Arrays.asList(array);
        
        
        // Lista para almacenar los elementos que contienen "F"
        List<String> elementosConF = new ArrayList<>();
        // Iterar sobre la lista original y encontrar elementos que contienen "F"
        String target = workingMemory.getTarget();
        for (String elemento : elements) {
            if (elemento.contains(target)) {
                elementosConF.add(elemento);
            }
        }
        
        // Verificar si hay al menos 5 elementos con "F"
        if (elementosConF.size() >= 5) {
            // Obtener los primeros 5 elementos
            List<String> primerosCinco = elementosConF.subList(0, 5);
            // El elemento en el medio de estos 5 elementos
            String elementoMedio = primerosCinco.get(2);
            // enviar a toma de desiciones
            s.changeOriginAreaandDestination(s, "fO", "DecisionMaking");
            client cliente = new client();
            cliente.enviarSike(s, "Lobe", 1099);
        } else {
            System.out.println("No se encontro el target");
        }
    }

}
