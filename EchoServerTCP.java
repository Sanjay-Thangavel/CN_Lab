/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.util.*;


public class EchoServerTCP {

    private ServerSocket server;
   
    public static void main(String args[]) throws IOException {
        ServerSocket server = new ServerSocket(8080);
         
        while(true){
                Socket client= server.accept();
                BufferedReader r=new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter w=new PrintWriter(client.getOutputStream(),true);
                w.println("Welcome to Java Echoserver.Tap Bye to close.");
                String line;
                do{
                    line=r.readLine();
                    if(line!=null)
                        System.out.println("Server got "+line+" from client...");
                    w.println("Got : " +line);
                }
                while(!line.trim().equals("Bye"));
                client.close();
            }
        }
}
