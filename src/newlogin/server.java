package newlogin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ashiq on 5/26/2016.
 */

public class server {
    ServerSocket socket;

    public static List<user> luser= Collections.synchronizedList(new ArrayList<user>()) ;

    public static List<String> username= Collections.synchronizedList(new ArrayList<String>()) ;

    server(int port ) throws IOException, InterruptedException {
       File f=new File("D:\\GRE server\\users.txt");
        Scanner s=new Scanner(f);
        while(s.hasNext())
        {
            String n=s.nextLine();
            String p=s.nextLine();
            username.add(n);
            luser.add(new user(n,p));
        }
        socket=new ServerSocket(port);
        new Acceptor(socket).t.join();
    }

    public void close() throws FileNotFoundException {

        PrintWriter p=new PrintWriter("D:\\GRE server\\users.txt");
        for( int n=0;n<luser.size();n++)
        {
            p.println(luser.get(n).name);
            p.println(luser.get(n).pass);
        }
        p.flush();
    }
}
