package test.attentionframework.Lobes.FrontalLobe.folderSuperiorFrontalGirusSFG;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import test.attentionframework.Client.client;
import test.attentionframework.Server.WorkingMemory;
import test.attentionframework.spike.Spike;

public class FEF {
    
    private WorkingMemory workingMemory;

    public FEF(WorkingMemory workingMemory) {
        this.workingMemory = workingMemory;
    }

    public void cotrolOfVsualAttention(Spike s) throws RemoteException {
        int position = 0;
        // Buscar la posición de 'C'
        List<Object> information = s.getInformation();
        int positionC = findPosition("C", information);
        if (positionC != -1) {
            System.out.println("La posicion de 'C' es: " + positionC);
            position = positionC;
            
            if(position == 24){
                workingMemory.setCueType("CenterCue");
            }else{
                workingMemory.setCueType("SpatialCue");
            }
        } else {
            // Si 'C' no se encuentra, buscar la posición de 'R'
            int positionR = findPosition("R", information);
            if (positionR != -1) {
                System.out.println("No hay Cue... ");
                System.out.println("La posicion de 'R' es: " + positionR);
                position = positionR;
                workingMemory.setCueType("NoCue");
                
            } else {
                System.out.println("'C' ni 'R' fueron encontrados en el vetor.");
            }
        }

        // Crear una nueva lista de información para reemplazar la original
        List<Object> newInformation = new ArrayList<>(information);

        // Actualizar la información del Spike con la lista mutable
        s.setInformation(newInformation);

         // Añadir una nueva lista con enteros
        List<Integer> newIntegerList = new ArrayList<>();
        newIntegerList.add(position);
        newInformation.add(newIntegerList);

        // Actualizar la información del Spike con la lista mutable
        s.setInformation(newInformation);
        
        
        s = s.changeOriginAreaandDestination(s, "FEF", "IPL");
        
        
        //Enviar Spike
        client cliente = new client();

        cliente.enviarSike(s, "Gyro", 1099);
    }
    
    public int findPosition(String target, List<Object> information) {
        // Asumimos que la lista de strings está en la segunda posición de 'information'
        @SuppressWarnings("unchecked")
        // Obtener el array de strings del segundo elemento
        String[] array = (String[]) information.get(1);

        // Convertir el array de strings en una lista de strings
        List<String> elements = Arrays.asList(array);

        // Buscar la posición de 'target' en 'elements'
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).equals(target)) {
                return i;
            }
        }
        return -1; // Retorna -1 si no se encuentra el elemento
    }
}
