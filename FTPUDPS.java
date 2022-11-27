/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nwtwork_tes;
import java.io.*;
import java.net.*;

public class FTPUDPS
{
    public static void main(String[] args)
    {
                DatagramSocket socket=null;
                //Send, Receive packets
                DatagramPacket inPacket=null;
                DatagramPacket outPacket=null;
                byte[] inBuf, outBuf;
                final int PORT=50000;

                try
                {
                        socket=new DatagramSocket(PORT);
                        while(true)
                        {
                                System.out.println("\nRunning...\n");

                                //Receive Client's request
                                inBuf=new byte[100];
                                inPacket=new DatagramPacket(inBuf, inBuf.length);
                                socket.receive(inPacket);

                                //Get Port and IPAddress
                                int source_port=inPacket.getPort();
                                InetAddress source_address=inPacket.getAddress();
                                System.out.println("Client: "+source_address + ":" + source_port);

                                //Send File list
                                String dirname="/home/vaibhav/TextFiles/";
                                File f1=new File(dirname);
                                File fl[]=f1.listFiles();

                                StringBuilder sb=new StringBuilder("\n");
                                int c=0;

                                for(int i=0;i<fl.length;i++)
                                {
                                        if(fl[i].canRead() && (fl[i].toString()).endsWith(".txt"))
                                        c++;
                                }

                                sb.append(c+" .txt files found.\n\n");

                                for(int i=0;i<fl.length;i++)
                                        if((fl[i].toString()).endsWith(".txt"))       
                                                sb.append(fl[i].getName()+" "+fl[i].length()+" Bytes\n");

                                sb.append("\nEnter file name for download: ");
                                outBuf=(sb.toString()).getBytes();
                                outPacket=new DatagramPacket(outBuf, 0, outBuf.length, source_address, source_port);
                                socket.send(outPacket);

                                //Receive Filename request
                                inBuf=new byte[100];
                                inPacket=new DatagramPacket(inBuf, inBuf.length);
                                socket.receive(inPacket);
                                String filename=new String(inPacket.getData(), 0, inPacket.getLength());

                                System.out.println("Requested File: "+filename);

                                boolean flis=false;
                                int index=-1;
                                sb=new StringBuilder("");
                                for(int i=0;i<fl.length;i++)
                                {
                                        if(((fl[i].getName()).toString()).equalsIgnoreCase(filename))
                                        {
                                                index=i;
                                                flis=true;
                                        }

                                }

                                if(!flis)
                                {
                                        System.out.println("ERROR");
                                        sb.append("ERROR");
                                        outBuf=(sb.toString()).getBytes();
                                        outPacket=new DatagramPacket(outBuf, 0, outBuf.length, source_address, source_port);
                                        socket.send(outPacket);
                                }
                                else
                                {
                                        try
                                        {
                                                //File Send Process, Independent
                                                File ff=new File(fl[index].getAbsolutePath());
                                                FileReader fr=new FileReader(ff);
                                                BufferedReader brf=new BufferedReader(fr);      
                                                String s=null;
                                                sb=new StringBuilder();

                                                while((s=brf.readLine())!=null)
                                                {
                                                        sb.append(s);
                                                }

                                                if(brf.readLine()==null)
                                                        System.out.println("File Read Successful. Closing Socket.");
                                                       
                                                outBuf=(sb.toString()).getBytes();
                                                outPacket=new DatagramPacket(outBuf, 0, outBuf.length, source_address, source_port);
                                                socket.send(outPacket);    
                                        }
                                        catch(IOException ioe)
                                        {
                                        System.out.println(ioe);
                                        }
                                }
                        }
                }
               
                catch(Exception e)
                {
                        System.out.println("Error\n");
                }
    }
}