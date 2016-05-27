package newlogin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Ashiq on 5/26/2016.
 */
public class Acceptor implements Runnable{
    ServerSocket socket;
    Thread t;
    Acceptor(ServerSocket s)
    {
        socket=s;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        while(true)
        {
            try {
                Socket s=socket.accept();
                new Validation(s).t.join();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
