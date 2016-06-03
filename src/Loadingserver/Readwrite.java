package Loadingserver;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ashiq on 5/27/2016.
 */
public class Readwrite implements Runnable{
    Socket soc;
    DataInputStream din;
    DataOutputStream dout;
    ArrayList<Node> known;
    Thread t;
    int port;
    Readwrite(Socket s,int port) throws IOException {
        soc=s;
        din=new DataInputStream(soc.getInputStream());
        dout=new DataOutputStream(soc.getOutputStream());
        this.port=port;
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run()
    {
        try {
            String s=din.readUTF();
            System.out.println(s);
            if(s.charAt(0)=='#')
            {
                String name=s.replace('#',' ');
                name=name.trim();
                String w="1";
                w=din.readUTF();
                known=new ArrayList<>();
                while(!w.equals("done"))
                {

                    String []s1=w.split("#");
                    System.out.println(w);
                    known.add(new Node(s1[0],s1[1],s1[2]));
                    w=din.readUTF();
                }
                try {

                    din.close();
                    dout.close();
                    soc.close();      //new add

                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedWriter p = new BufferedWriter(new FileWriter(name+".txt",true));

                for( Node x:known)
                {
                    System.out.println(x.word);
                    p.append(x.word+"\n");
                    p.append(x.mean+"\n");
                    p.append(x.senten+"\n");
                }
                p.flush();
                p.close();



            }
            else
            {
                String []s1=s.split(" ");
                String name=s1[0];
                String pass=s1[1];
                Server.load();
                if(Server.username.contains(name)&& Server.password.get(name).equals(pass))
                {
                    dout.writeUTF("Match");
                    dout.flush();
                    String file="D:\\GRE server\\"+name+".txt";
                    System.out.println(file);
                    File f=new File(file);
                    System.out.println("..");
                    Scanner sc=new Scanner(f);
                    while(sc.hasNext())
                    {
                        String t;
                        try {
                            t = sc.nextLine() + "#" + sc.nextLine() + "#" + sc.nextLine();
                        }catch (Exception e)
                        {
                            break;
                        }
                        dout.writeUTF(t);
                        dout.flush();
                        System.out.println("1..");
                    }
                    sc.close();
                    dout.writeUTF("stop");
                    dout.flush();

                    System.out.println("2..");

                }
                else
                {
                    dout.writeUTF("nomatch");
                    dout.flush();

                }
                try {

                    din.close();
                    dout.close();
                    soc.close();      //new add

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e)
        {
            System.out.println(port);
            e.printStackTrace();
            try {

                din.close();
                dout.close();
                soc.close();      //new add

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }
}
