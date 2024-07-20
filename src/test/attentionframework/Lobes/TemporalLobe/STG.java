package test.attentionframework.Lobes.TemporalLobe;

import static java.lang.Math.random;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import test.attentionframework.Client.client;
import test.attentionframework.Server.WorkingMemory;
import test.attentionframework.spike.Spike;
import java.util.Random;

public class STG {
    
    private WorkingMemory workingMemory;

    public STG(WorkingMemory workingMemory) {
        this.workingMemory = workingMemory;
    }

    public void visuispatialProcessing(Spike s) throws RemoteException {
        
       int numStage = workingMemory.getNumStage();
       
        if (numStage == 1) {
            System.out.println("Foco atencional Posicionado");
        }else if(numStage == 2){
            System.out.println("Mantener Foco atencional");
        }else if(numStage == 3){
            System.out.println("Detectando estimulo target");
            String cueType = workingMemory.getCueType();
            
            List<Object> information = s.getInformation();
            // Obtener el array de strings del segundo elemento
            String[] array = (String[]) information.get(1);

            // Convertir el array de strings en una lista de strings
            List<String> elements = Arrays.asList(array);
            
            long startTime = System.currentTimeMillis();
            switch (cueType) {
            case "SpatialCue":
                System.out.println("Spatial Cue");
                processSpatialCue(elements, information, s, workingMemory);
                break;

            case "CenterCue":
                System.out.println("Center Cue");
                processCenterCue(elements, information, s, workingMemory);
                break;

            case "NoCue":
                System.out.println("No Cue");
                processNoCue(elements, information, s, workingMemory);
                break;
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Tiempo de ejecucion: " + duration + " ms");

        workingMemory.setNumStage(0);
            
        }else{
            System.out.println("El num de stage no concide :v");
        }
        
    }
    
    private static void processSpatialCue(List<String> elements, List<Object> information, Spike s, WorkingMemory workingMemory) throws RemoteException {
        int index = workingMemory.getRelevantPosition(); // Índice del elemento central
        
        //Random **************
        Random random = new Random();
        
        // Verificar que el índice central esté dentro del rango permitido
        if (index >= 2 && index <= elements.size() - 3) {
            //String centralElement = elements.get(index);
            
            //random***************
            // Elegir aleatoriamente uno de los elementos adyacentes incluyendo el central
            int randomOffset = random.nextInt(5) - 2; // Genera un número entre -2 y 2
            int randomIndex = index + randomOffset;
            String centralElement = elements.get(randomIndex);
            
            

            // Acceder a los elementos adyacentes 
            if (centralElement.equals(elements.get(index - 1)) &&
                centralElement.equals(elements.get(index - 2)) &&
                centralElement.equals(elements.get(index + 1)) &&
                centralElement.equals(elements.get(index + 2))) {

                // Target encontrado y es congruente
                updateInformation(information, s, true, centralElement);
            } else {
                // Target no es congruente
                updateInformation(information, s, false, centralElement);
            }
        } else {
            // Index fuera de rango, no es congruente
            updateInformation(information, s, false, "");
        }
    }

    private static void processCenterCue(List<String> elements, List<Object> information, Spike s, WorkingMemory workingMemory1) throws RemoteException {
        String elementtarget = "";
        boolean foundTarget = false;
        int index = 0 ;
        
        
        //elemntos random***************
        Random random = new Random();
        List<Integer> fIndices = new ArrayList<>();
        
        
        // Búsqueda de secuencias que contienen 'F'
        for (int i = 0; i <= elements.size() - 5; i++) {
            if (elements.get(i).contains("F") &&
                elements.get(i + 1).contains("F") &&
                elements.get(i + 2).contains("F") &&
                elements.get(i + 3).contains("F") &&
                elements.get(i + 4).contains("F")) {
                foundTarget = true;
                index = i + 4;
                
               
            
                elementtarget = elements.get(i);
                
                break;
            }
        }
        
        //Random*******************
        if (!fIndices.isEmpty()) {
            int randomIndex = random.nextInt(fIndices.size());
            index = fIndices.get(randomIndex) + 4;
            elementtarget = elements.get(fIndices.get(randomIndex));
        }

        boolean checkTarget = false;
        
        if (index >= 2 && index <= elements.size() - 3) {
            String centralElement = elements.get(index);

            // Acceder a los elementos adyacentes solo una vez
            if (centralElement.equals(elements.get(index - 1)) &&
                centralElement.equals(elements.get(index - 2)) &&
                centralElement.equals(elements.get(index + 1)) &&
                centralElement.equals(elements.get(index + 2))) {

                // Target encontrado y es congruente
                updateInformation(information, s, true, centralElement);
            } else {
                // Target no es congruente
                updateInformation(information, s, false, centralElement);
            }
        } else {
            // Index fuera de rango, no es congruente
            updateInformation(information, s, false, elementtarget);
        }
    }

    private static void processNoCue(List<String> elements, List<Object> information, Spike s, WorkingMemory workingMemory) throws RemoteException {
        String elementtarget = "";
        boolean foundTarget = false;
        System.out.println(workingMemory.getRelevantPosition());
        int index = 0 ;
        
        //elemntos random***************
        Random random = new Random();
        List<Integer> fIndices = new ArrayList<>();
        
        // Búsqueda de secuencias que contienen 'F'
        for (int i = 0; i <= elements.size() - 5; i++) {
            if (elements.get(i).contains("F") &&
                elements.get(i + 1).contains("F") &&
                elements.get(i + 2).contains("F") &&
                elements.get(i + 3).contains("F") &&
                elements.get(i + 4).contains("F")) {
                foundTarget = true;
                index = i +4;
                
                //agregar indice de secuencia Random******
                fIndices.add(i); // Agrega el índice de la secuencia encontrada
               
                elementtarget = elements.get(i);
                
                break;
            }
        }
        
        /*
        for (int i = 0; i <= elements.size() - 5; i++) {
            if (elements.get(i).contains("F") &&
                elements.get(i + 1).contains("F") &&
                elements.get(i + 2).contains("F") &&
                elements.get(i + 3).contains("F") &&
                elements.get(i + 4).contains("F")) {
                foundTarget = true;
                index = i + 4;
                
                
                elementtarget = elements.get(i);
                
                break;
            }
        }
        */
        
        //random*******************
        // Selecciona aleatoriamente uno de los índices de las secuencias encontradas
        if (!fIndices.isEmpty()) {
            int randomIndex = random.nextInt(fIndices.size());
            index = fIndices.get(randomIndex) + 4;
            elementtarget = elements.get(fIndices.get(randomIndex));
        }
        
        boolean checkTarget = false;
        
        if (index >= 2 && index <= elements.size() - 3) {
            String centralElement = elements.get(index);

            // Acceder a los elementos adyacentes solo una vez
            if (centralElement.equals(elements.get(index - 1)) &&
                centralElement.equals(elements.get(index - 2)) &&
                centralElement.equals(elements.get(index + 1)) &&
                centralElement.equals(elements.get(index + 2))) {

                // Target encontrado y es congruente
                updateInformation(information, s, true, centralElement);
            } else {
                // Target no es congruente
                updateInformation(information, s, false, centralElement);
            }
        } else {
            // Index fuera de rango, no es congruente
            updateInformation(information, s, false, elementtarget);
        }
    }

    private static void updateInformation(List<Object> information, Spike s, boolean checkTarget, String elementtarget) throws RemoteException {
        List<Object> newInformation = new ArrayList<>(information);
        List<String> newIntegerList = new ArrayList<>();
        newIntegerList.add(elementtarget);
        newIntegerList.add(checkTarget ? "Congruente" : "Incongruente");
        newInformation.add(newIntegerList);

        // Actualizar la información del Spike con la lista mutable
        s.setInformation(newInformation);
        s.changeOriginAreaandDestination(s, "STG", "TPJ");
        client cliente = new client();
        cliente.enviarSike(s, "Lobe", 1099);
    }

}
