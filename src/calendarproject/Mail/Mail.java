import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Mail {
	public static void sendMail(String[] to, String from, String password, String subject, String message) {
		Properties props = new Properties();
	    props.put("mail.smtp.host" , "smtp.gmail.com");
	    props.put("mail.stmp.user" , from);
	    props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", 
            "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props, 
        	    new javax.mail.Authenticator(){
        	        protected PasswordAuthentication getPasswordAuthentication() {
        	            return new PasswordAuthentication(
        	            		from, password);
        	        }
        	});
        Message msg = new MimeMessage(session);
        for (int i = 0; i < to.length; i++) {
	        	
        	try {
        		msg.setFrom(new InternetAddress(from));
        		msg.setRecipient(Message.RecipientType.TO, 
        				new InternetAddress(to[i]));
        		msg.setSubject(subject);
        		msg.setText(message);
        		Transport transport = session.getTransport("smtp");
        		transport.connect("smtp.gmail.com" , 465 , from, password);
            	Transport.send(msg);
            	System.out.println("fine!!");
        	}
        	catch(Exception exc) {
        		System.out.println(exc);
        	}
        }
	}
}
