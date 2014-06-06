
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class CoolClass_Stub implements MyCoolClass {

    Socket socket;
    ObjectOutputStream o;
    ObjectInputStream i;

    public String getDevice(String name) throws Exception {
        
        socket = new Socket("localhost", 9000);
        o = new ObjectOutputStream(socket.getOutputStream());
        o.writeObject("get");
        o.flush();
        o.writeObject(name);
        o.flush();

        i = new ObjectInputStream(socket.getInputStream());
        String ret = (String) (i.readObject());
        socket.close();
        return ret;
    }

    @Override
    public void setDevice(String name, String maker) throws Exception {
        socket = new Socket("localhost", 9000);
        o = new ObjectOutputStream(socket.getOutputStream());
        o.writeObject("set");
        o.flush();
        o.writeObject(name);
        o.flush();
        o.writeObject(maker);
        o.flush();
    }
}
