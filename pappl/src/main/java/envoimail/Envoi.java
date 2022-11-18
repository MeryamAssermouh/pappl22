/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envoimail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author 96441
 */
public class Envoi  {

    public Envoi() {
    }
    
    /**
     * Cette méthode permet l'envoi d'un texte par mail à une personne particulière 
     * @param recepient
     * @param subject
     * @param text
     * @throws MessagingException 
     */
    public static void send(String recepient, String subject, String text) throws MessagingException {
        System.out.println("preparing to send mail");
        Properties properties=new Properties();
        properties.put("mail.smtp.host", "smtps.nomade.ec-nantes.fr");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port","587");
        String myAccountEmail="meryam.assermouh@eleves.ec-nantes.fr";
        String password="KIriqinagoxovohoco67";
        Session session;
        session = Session.getInstance(properties, new Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(myAccountEmail, password);
           }
        });
        Message message=prepareMessage(session,myAccountEmail,recepient,subject,text);
        Transport.send(message);
        System.out.println("Message sent !");
    }
    
    /**
     * Cette méthode permet de créer un message pour l'envoyer par mail 
     * @param session
     * @param myAccountEmail
     * @param recepient
     * @param subject
     * @param text
     * @return
     * @throws AddressException
     * @throws MessagingException 
     */
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient,String subject, String text) throws AddressException, MessagingException{
   
            Message message= new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(subject);
            message.setText(text);
            return(message);
    }
}
