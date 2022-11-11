
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Scanner;
import javax.activation.*;

public class SendMail {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String SenderUser = "sanjayt.ifortis";
        String SenderMail = "sanjayt.ifortis@gmail.com";
        String SenderPassword = "aeomqwfmhpjhyzoa";
        System.out.print("Enter Destination Address:");
        String ToMail = in.nextLine();
        String ToHost = "smtp.gmail.com";
        Properties SessionProperties = new Properties();
        SessionProperties.put("mail.smtp.auth","true");
        SessionProperties.put("mail.smtp.starttls.enable","true");
        SessionProperties.put("mail.smtp.host",ToHost);
        SessionProperties.put("mail.smtp.port",587);
        Session CurrentSession = Session.getInstance(SessionProperties,
            new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(SenderMail, SenderPassword);
                }
            });
        try{
            Message ThisMessage = new MimeMessage(CurrentSession);
            
            ThisMessage.setFrom(new InternetAddress(SenderMail));
            ThisMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ToMail));
            System.out.print("Enter Subject for mail:");
            String Subject = in.nextLine();
            System.out.println("Enter Body of the mail:");
            String Body = in.nextLine();
            ThisMessage.setSubject(Subject);
            ThisMessage.setContent(Body,"text/html");
            Transport.send(ThisMessage);
            System.out.println("The Message was sent successfully...");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        in.close();
    }
}