/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.attentionframework.Server;

/**
 *
 * @author vasit
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;
import test.attentionframework.DecisionMaking;
import test.attentionframework.Lobes.FrontalLobe.IFG;
import test.attentionframework.Lobes.FrontalLobe.MFG;
import test.attentionframework.Lobes.FrontalLobe.VLPFC;
import test.attentionframework.Lobes.FrontalLobe.folderInferiorFrontalGirusIFG.fO;
import test.attentionframework.spike.Spike;
import test.attentionframework.Lobes.ParietalLobe.IPS;
import test.attentionframework.Lobes.ParietalLobe.IPL;
import test.attentionframework.Lobes.TPJ;
import test.attentionframework.Lobes.FrontalLobe.folderSuperiorFrontalGirusSFG.FEF;
import test.attentionframework.Lobes.TemporalLobe.STG;
import test.attentionframework.Lobes.TemporalLobe.folderSuperiorTemporalGirusSTG.STS;
import test.attentionframework.Server.WorkingMemory;
public class hub  extends UnicastRemoteObject implements receiveData {
    // Crear una instancia de WorkingMemory
    WorkingMemory workingMemory = new WorkingMemory();

    public hub() throws RemoteException {
        super();
    }

   
    
    @Override
    public void receiveDataLobes(Spike s) throws RemoteException {
        
        // Obtener la modalidad y la información del Spike
        List<String> modality = s.getModality();
        List<Object> information = s.getInformation();

        // Verificar si el primer elemento de la información es una lista de strings
        if (information.get(0) instanceof List) {
            List<String> elements = (List<String>) information.get(0);


            // Verificar si el segundo elemento de la lista es "IPS"
            if (elements.size() > 1 && "TPJ".equals(elements.get(1))) {

                TPJ area = new TPJ(workingMemory);
                area.reorientingOfAttention(s);
            
            }else if (elements.size() > 1 && "DecisionMaking".equals(elements.get(1))) {

                DecisionMaking area = new DecisionMaking(workingMemory);
                area.decisionMaking(s);
            
            }else{
                System.out.println("No se encontro el area de destino");
            }
        } else {
            System.out.println("El primer elemento de la información no es una lista de strings.");
        }
    }
        

    @Override
    public void receiveDataGyros(Spike s) throws RemoteException {
        
        // Obtener la modalidad y la información del Spike
        List<String> modality = s.getModality();
        List<Object> information = s.getInformation();

        // Verificar si el primer elemento de la información es una lista de strings
        if (information.get(0) instanceof List) {
            List<String> elements = (List<String>) information.get(0);


            // Verificar si el segundo elemento de la lista es "IPS"
            if (elements.size() > 1 && "IPS".equals(elements.get(1))) {
                
                IPS area = new IPS(workingMemory);
                area.directingEyeMovements(s);
            
            }else if (elements.size() > 1 && "IPL".equals(elements.get(1))) {

                IPL area = new IPL();
                area.saccadicEyeMovements(s);
            
            }else if (elements.size() > 1 && "VLPFC".equals(elements.get(1))) {

                VLPFC area = new VLPFC(workingMemory);
                area.maintenceOfTheTargetInformation(s);
            
            }else if (elements.size() > 1 && "STG".equals(elements.get(1))) {

                STG area = new STG(workingMemory);
                area.visuispatialProcessing(s);
            
            }else if (elements.size() > 1 && "IFG".equals(elements.get(1))) {

                IFG area = new IFG();
                area.reorientingOfAttention(s);
            
            }else if (elements.size() > 1 && "MFG".equals(elements.get(1))) {

                MFG area = new MFG();
                area.circuitBreaker(s);
            
            }else{
                System.out.println("No se encontro el area de destino");
            }
        } else {
            System.out.println("El primer elemento de la información no es una lista de strings.");
        }
 
    }

    @Override
    public void receiveDataSpecificAreas(Spike s) throws RemoteException {
        
        // Obtener la modalidad y la información del Spike
        List<String> modality = s.getModality();
        List<Object> information = s.getInformation();
        
        if (information.get(0) instanceof List) {
            List<String> elements = (List<String>) information.get(0);
            if(elements.size() > 1 && "FEF".equals(elements.get(1))){

                    FEF area = new FEF(workingMemory);
                    area.cotrolOfVsualAttention(s);
            }else if(elements.size() > 1 && "STS".equals(elements.get(1))){
            STS area = new STS();
                    area.spatialOrientation(s);
            }else if(elements.size() > 1 && "fO".equals(elements.get(1))){
                    fO area = new fO(workingMemory);
                    area.responseInibition(s);
            }else{
                    System.out.println("No se encontro el area de destino");
                }
        } else {
            System.out.println("El primer elemento de la información no es una lista de strings.");
        }
    }
    
}
