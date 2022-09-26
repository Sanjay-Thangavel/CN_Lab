/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.io.*;
import java.net.*;

public class EchoClientTCP {
        public static void main (String args[]) throws IOException {
            Socket s= new Socket("127.0.0.1",8080);
            BufferedReader r=new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter w=new PrintWriter(s.getOutputStream(),true);
            BufferedReader con=new BufferedReader(new InputStreamReader(System.in));
            
            String line;
            do{
                line=r.readLine();
                if(line!=null)
                    System.out.println(line);
                line=con.readLine();
                w.println(line);
            }
            while(!line.trim().equals("Bye"));
        }
}
