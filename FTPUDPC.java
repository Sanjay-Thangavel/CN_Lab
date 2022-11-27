/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nwtwork_tes;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FTPUDPC
{
    public static void main(String[] args)
    {
                DatagramSocket socket = null;
                DatagramPacket inPacket = null;
                DatagramPacket outPacket = null;
                byte[] inBuf, outBuf;
                final int PORT = 50000;
                String msg = null;
                Scanner src=new Scanner(System.in);

                try
                {
                        InetAddress address = InetAddress.getByName("127.0.0.1");
                        socket = new DatagramSocket();

                        msg = "";
                        outBuf = msg.getBytes();
                        outPacket = new DatagramPacket(outBuf, 0, outBuf.length, address, PORT);
                        socket.send(outPacket);

                        inBuf = new byte[65535];
                        inPacket = new DatagramPacket(inBuf, inBuf.length);
                        socket.receive(inPacket);

                        String data = new String(inPacket.getData(), 0, inPacket.getLength());
                        //Print file list
                        System.out.println(data);

                        //Send file name
                        String filename=src.nextLine();             
                        outBuf = filename.getBytes();
                        outPacket = new DatagramPacket(outBuf, 0, outBuf.length,address, PORT);
                        socket.send(outPacket);

                        //Receive file
                        inBuf = new byte[100000];
                        inPacket = new DatagramPacket(inBuf, inBuf.length);
                        socket.receive(inPacket);

                        data = new String(inPacket.getData(), 0, inPacket.getLength());
                        if(data.endsWith("ERROR"))
                        {
                                System.out.println("File doesn't exist.\n");
                                socket.close();
                        }
                        else
                        {
                                try
                                {
                                        BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
                                        pw.write(data);
                                        //Force write buffer to File
                                        pw.close();

                                        System.out.println("File Write Successful. Closing Socket.");
                                        socket.close();
                                }
                                       
                                catch(IOException ioe)
                                {
                                        System.out.println("File Error\n");
                                        socket.close();
                                }
                        }
                }
                catch (Exception e)
                {
                        System.out.println("\nNetwork error. Please try again.\n");
                }
    }
}