/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GET_POST;
import java.io.*;
import java.net.*;

public class HTTPGetClient {
    public static void main(String[] args) throws Exception {
        BufferedReader ifu = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream ots = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader ifs = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.print("GET URL: ");
        String sentence = ifu.readLine();
        ots.writeBytes(sentence + '\n');
        String ms = ifs.readLine();
        System.out.println("Resoponse:\n" + ms);
        clientSocket.close();
    }
}
