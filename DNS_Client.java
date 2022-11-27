/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package network;
import java.io.*;
import java.util.*;
import java.net.*;


/**
 *
 * @author Sanjay T
 */
public class DNS_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try 
        {
            DatagramSocket client=new DatagramSocket();
          InetAddress addr=InetAddress.getByName("127.0.0.1");
            byte[]sendbyte=new byte[1024];
byte[]receivebyte=new byte[1024];
BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter DOMAIN NAME OR IP address");
String str=in.readLine();
sendbyte=str.getBytes();
DatagramPacket sender=new DatagramPacket(sendbyte,sendbyte.length,addr,1309);
client.send(sender);
DatagramPacket receiver= new DatagramPacket(receivebyte,receivebyte.length);
client.receive(receiver);
String s=new String(receiver.getData());
System.out.println("IP adddress or DOMAIN NAME :"+s.trim());
client.close();
}
catch(Exception e)
{
System.out.println(e);
}
}
}
