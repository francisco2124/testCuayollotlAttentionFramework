/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.attentionframework;

import java.util.List;
import test.attentionframework.Server.WorkingMemory;
import test.attentionframework.spike.Spike;

/**
 *
 * @author vasit
 */
public class DecisionMaking {
    private WorkingMemory workingMemory;

    public DecisionMaking(WorkingMemory workingMemory) {
        this.workingMemory = workingMemory;
    }
    public void decisionMaking(Spike s) {
        List<Object> information = s.getInformation();
        List<String> infoTarget = (List<String>) information.get(2);
        String target = infoTarget.get(0);
        
        if(target.equals("FD")){
            System.out.println("Derecha");
        }else if(target.equals("FI")){
            System.out.println("Izquierda");
        }else{
            System.out.println("No se reconocio el target");
        }
        workingMemory.setRelevantPosition(0);
        System.out.println(workingMemory.getRelevantPosition());
        System.out.println("**************************************");
        
    }
}
