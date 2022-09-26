/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*; 
import java.net.*; 
class chatserver 
{    
public static void main(String args[]) throws Exception 
{ 
ServerSocket ss=new ServerSocket(2000); 
Socket sk=ss.accept(); 
BufferedReader cin= new BufferedReader(new InputStreamReader(sk.getInputStream())); 
PrintStream cout=new PrintStream(sk.getOutputStream()); 
BufferedReader stdin =new BufferedReader(new InputStreamReader(System.in)); 
String s; 
while (true) 
{ 
s=cin.readLine(); 
if(s.equalsIgnoreCase("END")) 
{  
cout.println("BYE"); 
break; 
} 
System.out.println("client :"+s+"\n"); 
System.out.println("Server :"); 
s=stdin.readLine(); 
cout.println(s); 
} 
ss.close(); 
sk.close(); 
cin.close(); 
stdin.close(); 
}}
