package Loadingserver;

import newlogin.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;
import newlogin.server;

/**
 * Created by Ashiq on 5/27/2016.
 */
public class Server {
    ServerSocket socket;

    public static List<user> luser= Collections.synchronizedList(new ArrayList<user>()) ;
    public static List<String> username= Collections.synchronizedList(new ArrayList<String>());
    public static Map<String,String> password=Collections.synchronizedMap(new HashMap<>());
    public static void load() throws FileNotFoundException {
        luser.clear();
        username.clear();
        password.clear();
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
    }
    public Server( int n1) throws IOException, InterruptedException {

        socket=new ServerSocket(n1);
        new Acceptor(socket);

    }
    public void close() throws IOException {
        socket.close();
    }
    public static void main(String[] args) throws IOException, InterruptedException {

        new Server(9000);
        String s="e";
        s="s";
        System.out.println(s);

    }
}
