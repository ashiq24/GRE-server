package newlogin;

import java.io.*;
import java.net.Socket;

import Loadingserver.Server;


/**
 * Created by Ashiq on 5/26/2016.
 */
public class Validation implements  Runnable{
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    user User;
    Thread t;
    Validation(Socket so) throws IOException {
        s=so;
        din=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        t=new Thread(this);
        t.start();
    }


    @Override
    public void run() {
        try {
            String s=din.readUTF();
            String [] s1=s.split("#");
            String name=s1[0];
            String pass=s1[1];
            User=new user(name,pass);
            if(server.username.contains(name))
            {
                dout.writeUTF("not");

            }
            else
            {
                dout.writeUTF("success");
                server.luser.add(User);
                Server.luser.add(User);
                Server.username.add(name);

                Server.password.put(name,pass);
                server.username.add(name);
                System.out.println(name+" "+pass);
                File f=new File(name.trim()+".txt");
                PrintWriter p=new PrintWriter(f);
                p.close();
                server.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*finally {

            try {
                din.close();
                dout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }
}
