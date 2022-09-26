/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*; 
import java.net.*; 
public class  Chatclient 
{ 
public static void main(String args[]) throws Exception 
{ 
Socket sk=new Socket("localhost",2000); 
BufferedReader sin=new BufferedReader(new InputStreamReader(sk.getInputStream())); 
PrintStream sout=new PrintStream(sk.getOutputStream()); 
BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in)); 
String s; 
while(true) 
{ 
System.out.print("client  :"); 
s=stdin.readLine(); 
sout.println(s); 
s=sin.readLine(); 
System.out.println("SERVER  :"+s+"\n"); 
if(s.equalsIgnoreCase("BYE")) 
break; 
} 
sk.close(); 
sin.close(); 
sout.close(); 
stdin.close(); 
} 
} 

