package Loadingserver;

import newlogin.*;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

/**
 * Created by Ashiq on 5/27/2016.
 */
public class Server {
    ServerSocket socket;

    public static List<user> luser= Collections.synchronizedList(new ArrayList<user>()) ;
    public static List<String> username= Collections.synchronizedList(new ArrayList<String>());
    public static Map<String,String> password=new HashMap<>();
    Server( ) throws IOException, InterruptedException {
        File f=new File("D:\\GRE server\\users.txt");
        Scanner s=new Scanner(f);
        while(s.hasNext())
        {
            String n=s.nextLine();
            String p=s.nextLine();
            username.add(n);
            luser.add(new newlogin.user(n,p));
            password.put(n,p);
        }
        socket=new ServerSocket(9000);
        new Acceptor(socket);

    }

    public static void main(String[] args) throws IOException, InterruptedException {

      new Server();
        String s="e";
        s="s";
        System.out.println(s);

    }
}
