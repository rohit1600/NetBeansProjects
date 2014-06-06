
import edu.cmu.andrew.rohitraj.MyCoolClass;


public class MyCoolClass_Servant implements MyCoolClass {

    private String n[] = {"printer","stereo","TV","ipod","pda"};

    private String a[] = {"HP200XT","Kenwood200","Panasonic","Apple","Palm"};

    public String getDevice(String name) {

       for(int i = 0; i < n.length; i++) {
            if(n[i].equals(name)) return a[i];
       }
       return "No device";
    }

    @Override
    public void setDevice(String name, String maker) {
        for(int i = 0; i < n.length; i++) {
            if(n[i].equals(name)) return;
       }
        String nn[]=new String[n.length+1];
        String aa[]=new String[a.length+1];
        for(int i=0;i<n.length;i++){
            nn[i]=n[i];
            aa[i]=a[i];
        }
        
        nn[n.length]=name;
        aa[a.length]=maker;
        n=nn;
        a=aa;
        
    }
}