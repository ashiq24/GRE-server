package Loadingserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Ashiq on 5/27/2016.
 */
public class Acceptor implements Runnable {
    ServerSocket server;
    Thread t;
    Acceptor(ServerSocket s)
    {
        t=new Thread(this);
        server=s;
        t.start();
    }
    @Override
    public void run() {
        while(true)
        {
            try {
                Socket s=server.accept();

                System.out.println(s.toString());
                new Readwrite(s,s.getPort());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
