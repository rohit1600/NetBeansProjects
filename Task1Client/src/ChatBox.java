
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;


public interface ChatBox extends Remote{
    public Vector getMessageArchive(String s) throws RemoteException;
    
}
