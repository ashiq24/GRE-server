package newlogin;

import java.io.IOException;

/**
 * Created by Ashiq on 5/26/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        server s=new server(8000);
        s.close();
    }
}
