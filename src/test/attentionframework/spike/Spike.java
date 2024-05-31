package test.attentionframework.spike;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

import java.util.HashMap;

import java.util.HashSet;

import java.util.Map;

import java.util.Set;

public class Spike implements Serializable {
    private List<String> modality = new ArrayList<>();
    private List<Object> information = new ArrayList<>();
    private int temporality = 0;

    public List<String> getModality() {
        return modality;
    }

    public void setModality(List<String> modality) {
        this.modality = modality;
    }

    public List<Object> getInformation() {
        return information;
    }

    public void setInformation(List<Object> information) {
        this.information = information;
    }

    public int getTemporality() {
        return temporality;
    }

    public void setTemporality(int temporality) {
        this.temporality = temporality;
    }

    // Método para validar el destino
    public static boolean validateDestination(String origin, String destination, Map<String, Set<String>> validDestinations) {
        return validDestinations.containsKey(origin) && validDestinations.get(origin).contains(destination);
    }

    // Definir el Map con los datos proporcionados
    public static Map<String, Set<String>> createValidDestinationsMap() {
        Map<String, Set<String>> validDestinations = new HashMap<>();
        validDestinations.put("SFG", new HashSet<>(Arrays.asList("SFG", "MFG", "IFG", "OrG", "PrG", "INS", "CG", "STG", "FuG", "SPL", "IPL", "PCun", "PoG", "MVOcC", "LOcC", "Amyg", "Hipp", "BG", "Tha", "PhG")));
        validDestinations.put("MFG", new HashSet<>(Arrays.asList("SFG", "MFG", "IFG", "OrG", "PrG", "PCL", "INS", "CG", "STG", "MTG", "ITG", "FuG", "PhG", "pSTS", "SPL", "IPL", "PCun", "PoG", "MVOcC", "LOcC", "Amyg", "Hipp", "BG", "Tha")));
        validDestinations.put("IFG", new HashSet<>(Arrays.asList("SFG", "MFG", "IFG", "OrG", "PrG", "PCL", "INS", "CG", "STG", "MTG", "ITG", "FuG", "PhG", "pSTS", "SPL", "IPL", "PCun", "PoG", "MVOcC", "LOcC", "Amyg", "Hipp", "BG", "Tha")));
        validDestinations.put("ORG", new HashSet<>(Arrays.asList("SFG", "MFG", "IFG", "OrG", "PrG", "PCL", "INS", "CG", "STG", "MTG", "ITG", "FuG", "PhG", "pSTS", "SPL", "IPL", "PCun", "PoG", "MVOcC", "LOcC", "Amyg", "Hipp", "BG", "Tha")));
        validDestinations.put("PrG", new HashSet<>(Arrays.asList("SFG", "MFG", "IFG", "OrG", "PrG", "PCL", "INS", "CG", "STG", "MTG", "ITG", "FuG", "PhG", "SPL", "IPL", "PCun", "PoG", "Amyg", "Hipp", "BG", "Tha")));
        validDestinations.put("PCL", new HashSet<>(Arrays.asList("SFG", "MFG", "OrG", "PrG", "PCL", "INS", "CG", "STG", "SPL", "IPL", "PCun", "PoG", "Amyg", "Hipp")));
        validDestinations.put("IPL", new HashSet<>(Arrays.asList("SFG", "MFG", "IFG", "OrG", "PrG", "PCL", "INS", "CG", "STG", "MTG", "ITG", "FuG", "PhG", "pSTS", "SPL", "IPL", "IPS", "PCun", "PoG", "MVOcC", "LOcC", "Amyg", "Hipp", "BG", "Tha", "FEF")));
        validDestinations.put("INPUT", new HashSet<>(Arrays.asList("IPS")));
        validDestinations.put("IPS", new HashSet<>(Arrays.asList("FEF","TPJ")));
        validDestinations.put("FEF", new HashSet<>(Arrays.asList("IPL")));
        validDestinations.put("IPL", new HashSet<>(Arrays.asList("TPJ")));
        validDestinations.put("TPJ", new HashSet<>(Arrays.asList("IPS", "STS", "IFG", "fO")));
        validDestinations.put("STS", new HashSet<>(Arrays.asList("STG", "VLPFC")));
        validDestinations.put("VLPFC", new HashSet<>(Arrays.asList("STS", "DLPFC")));
        validDestinations.put("STG", new HashSet<>(Arrays.asList("TPJ")));
        validDestinations.put("IFG", new HashSet<>(Arrays.asList("DecisionMaking")));
        validDestinations.put("fO", new HashSet<>(Arrays.asList("DecisionMaking")));
        return validDestinations;
    }

    public static boolean validateConnection(Spike spike) {
        // Obtener la información del objeto Spike
        List<Object> information = spike.getInformation();
        // Validar la información antes de asignar y enviar
        String origin = ((List<String>)information.get(0)).get(0);
        String destination = ((List<String>)information.get(0)).get(1);
        // Crear el mapa de destinos válidos
        Map<String, Set<String>> validDestinations = Spike.createValidDestinationsMap();
        // Validar la conexión
        return Spike.validateDestination(origin, destination, validDestinations);
    }
    
    
    public Spike changeOriginAreaandDestination( Spike s, String Origin, String Destination){
        // Verificar si el primer elemento de la información es una lista de strings
        if (information.get(0) instanceof List) {
            List<?> firstList = (List<?>) information.get(0);

            // Verificar si se puede castear a una lista de strings
            if (firstList.get(0) instanceof String) {
                // Convertir la lista inmutable a una lista mutable
                List<String> elements = new ArrayList<>((List<String>) firstList);

                // Modificar el primer y segundo elemento de la lista
                elements.set(0, Origin);
                elements.set(1, Destination);

                // Crear una nueva lista de información para reemplazar la original
                List<Object> newInformation = new ArrayList<>(information);
                newInformation.set(0, elements);

                // Actualizar la información del Spike con la lista mutable
                s.setInformation(newInformation);
                
                
            } else {
                System.out.println("El primer elemento de la lista no es un string.");
            }
        } else {
            System.out.println("El primer elemento de la información no es una lista.");
        }
        
        return s;
    }
    
    
}
