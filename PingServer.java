/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;
 
import java.io.*;
import java.net.*;
import java.util.*;

public class PingServer
{
public static void main(String[] args)
{
try
{
ServerSocket ss=new ServerSocket(2156);
Socket s=ss.accept();
if(s.isConnected())
System.out.println("Connected ...");
System.out.println("Listening ...");
DataOutputStream dos;
    try (DataInputStream dis = new DataInputStream(s.getInputStream())) {
        dos = new DataOutputStream(s.getOutputStream());
        int no=0;
        String ip="";
        if((dis.readUTF()).equals("P"))
        {
            System.out.println("Getting No. Of Packets ...");
            no=dis.readInt();
        }       if((dis.readUTF()).equals("A"))
        {
            System.out.println("Getting the Address ...");
            ip=dis.readUTF();
        }       Process p=Runtime.getRuntime().exec("ping -n "+no+" "+ip);
        System.out.println("Running  ping -n "+no+" "+ip);
        BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
        String ipline=br.readLine();
        while(ipline != null )
        {
            dos.writeUTF(ipline);
            ipline=br.readLine();
        }   }
dos.close();
}
catch(Exception x)
{
x.printStackTrace();
}
}
}
