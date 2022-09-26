/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GET_POST;
import java.io.*; 
import java.net.*; 

public class HTTPPostServer {
    private static final String USER_AGENT = "Google Chrome"; 
    static String sendPOST(String POST_URL) throws IOException{ 
        URL obj = new URL(POST_URL); 
        HttpURLConnection con = (HttpURLConnection) obj.openConnection(); 
        con.setRequestMethod("POST"); 
        con.setRequestProperty("User-Agent", USER_AGENT); 
        con.setDoOutput(true); 
        OutputStream os = con.getOutputStream(); 
        int responseCode = con.getResponseCode(); 
        System.out.println("POST Response Code :: " + responseCode); 
        if (responseCode == HttpURLConnection.HTTP_OK) { 
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); 
            String inputLine; 
            StringBuffer response = new StringBuffer(); 
            while ((inputLine = in.readLine()) != null) { 
                response.append(inputLine); 
            } 
            in.close(); 
            System.out.println(response.toString()); 
            return(response.toString()); 
        } 
        else{ 
            System.out.println("POST request not worked"); 
            return(null); 
        }
    } 
    public static void main(String a[]) throws Exception { 
        ServerSocket ss=new ServerSocket(6789); 
        try { 
            while(true) { 
                Socket consoc= ss.accept(); 
                BufferedReader ifc =new BufferedReader(new InputStreamReader(consoc.getInputStream())); 
                DataOutputStream otc =new DataOutputStream(consoc.getOutputStream()); 
                String ps=ifc.readLine()+'\n'; 
                System.out.println("RECEIVED : "+ps); 
                String POST_URL = ps; 
                otc.writeBytes(sendPOST(POST_URL)+'\n'); 
                System.out.println("POST DONE"); 
            }
        } 
        catch(Exception e){ 
            e.printStackTrace(); 
        }
        ss.close();
    } 
}