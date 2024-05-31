package test.attentionframework.Client;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import test.attentionframework.Server.receiveData;
//Spike Class
import test.attentionframework.spike.Spike;
public class client {
    
    
    public void enviarSike(Spike s, String interfaz, int port) throws RemoteException{
        
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", port);
            String origen = "";
            String destiny = "";
            List<Object> information = s.getInformation();
            List<String> sublist = (List<String>) information.get(0);
            origen = sublist.get(0);
            destiny = sublist.get(1);
            information = s.getInformation();
            receiveData stub = (receiveData) registry.lookup("receiveData");
            boolean res = s.validateConnection(s);
            if (res) {
                if (interfaz=="Lobe") {
                    stub.receiveDataLobes(s);
                }else if(interfaz=="Gyro"){
                    stub.receiveDataGyros(s);
                }else if(interfaz=="SpecificArea"){
                    stub.receiveDataSpecificAreas(s);
                }else{
                    System.out.println("Nivel no reconocido");
                }
              
            } else {
              System.out.println("Area de destino no valida. La conexion del area "+origen+" a la area "+destiny+
                      " no es correcta acorde al atlas de Brainnetome");
            }
                 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
