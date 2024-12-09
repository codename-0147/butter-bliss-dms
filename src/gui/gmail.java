package gui;


import javax.mail.Authenticator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class gmail {

    public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send Email");

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "djanith11@gmail.com";
        String password = "200332412873@JanithDasanayaka";

        Session session = Session.getInstance(properties, new Authenticator() {
        
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }

        });

        Message message = PrepareMessage(session, myAccountEmail,recepient);
        
        Transport.send(message);
        System.out.println("Message Sent successfully!");
        
        

    }


    private static Message PrepareMessage(Session session, String myAccountEmail , String recepient ) {
       try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Distibutor Payment Invoice");
            message.setText("Hey There Look My emial");
            return message;
            
        } catch (Exception e) {
            
            Logger.getLogger(gmail.class.getName()).log(Level.SEVERE, null, e);
            
            
        }
        return null;
    }

}