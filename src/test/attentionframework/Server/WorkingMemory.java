/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.attentionframework.Server;

/**
 *
 * @author vasit
 */
public class WorkingMemory {

   private int relevantPosition = 0;
   private int numStage = 0;
   private String target = "";
   private String cueType = "";

    public void setRelevantPosition(int relevantPosition) {
        this.relevantPosition = relevantPosition;
    }

    public int getRelevantPosition() {
        return relevantPosition;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getNumStage() {
        return numStage;
    }

    public void setNumStage(int numStage) {
        this.numStage = numStage;
    }

    public String getCueType() {
        return cueType;
    }

    public void setCueType(String cueType) {
        this.cueType = cueType;
    }
    
    
    
    
}
