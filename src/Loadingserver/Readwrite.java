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
    Readwrite(Socket s) throws IOException {
        soc=s;
        din=new DataInputStream(soc.getInputStream());
        dout=new DataOutputStream(soc.getOutputStream());
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run()
    {
        try {
            String s=din.readUTF();
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
                    known.add(new Node(s1[0],s1[1],s1[2]));
                    w=din.readUTF();
                }
                File f=new File(name+".txt");
                FileWriter fw=new FileWriter(f,true);
                BufferedWriter bw=new BufferedWriter(fw);
                PrintWriter p=new PrintWriter(bw);
                for( Node x:known)
                {
                    p.println(x.word);
                    p.println(x.mean);
                    p.println(x.senten);
                }
                p.flush();



            }
            else
            {
                String []s1=s.split(" ");
                String name=s1[0];
                String pass=s1[1];
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
                        String t=sc.nextLine()+"#"+sc.nextLine()+"#"+sc.nextLine();
                        dout.writeUTF(t);
                        dout.flush();
                        System.out.println("1..");
                    }
                    dout.writeUTF("stop");
                    dout.flush();
                    System.out.println("2..");

                }
                else
                {
                    dout.writeUTF("nomatch");
                    dout.flush();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {
                din.close();
                dout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
