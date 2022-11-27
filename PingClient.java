/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class PingClient
{
public static void main(String[] args)
{
try
{
Socket s=new Socket("localhost",2156);
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));	
if(s.isConnected())
System.out.println("Connected !!");
Scanner in=new Scanner(System.in);
DataInputStream is=new DataInputStream(s.getInputStream());
DataOutputStream os=new DataOutputStream(s.getOutputStream());
System.out.println("How many Packets You want to send ? ");	
int no=in.nextInt();
System.out.println("Address to be pinged :");
String ip=br.readLine();
os.writeUTF("P");
os.writeInt(no);
os.writeUTF("A");
os.writeUTF(ip);
String pingline=is.readUTF();
while(pingline != null )
{
System.out.println(pingline);
pingline=is.readUTF();
}
os.flush();
os.close();	
is.close();
}catch(Exception x)	
{
}	
}
}
