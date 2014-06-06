
import java.io.IOException;
import android.app.Activity;
import android.os.Bundle;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    
        try
{
HttpClient hc = new DefaultHttpClient();
        HttpPost post = new HttpPost("www.yahoo.com.hk");

        HttpResponse rp = hc.execute(post);

        if (rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String str = EntityUtils.toString(rp.getEntity());
        }
    }
    catch(IOException e

    
    

    ){

}

@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(str);
//setContentView(R.layout.main);
    }
}