package test.attentionframework.Server;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
public class server {
    public static void main(String[] args) {

        try {
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            //distributed system. Configure the IP and port
                                r.rebind("receiveData", new hub());
                System.out.println("Running server :D");
                JOptionPane.showMessageDialog(null, "Conectado al Servidor ;D ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se conecto al servidor :c "+e);
        }
    }
}
