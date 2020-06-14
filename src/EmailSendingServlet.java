import java.io.IOException;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties; 
/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
    }
 
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	
    	String ReceiverAddress=request.getParameter("recipient");
	
    	final String username = "shubhamiit91@gmail.com";
        final String password = "ksfalgrggojuxkuh";

        Properties prop = new Properties();
 		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
         
         Session session = Session.getInstance(prop,
                 new javax.mail.Authenticator() {
                     protected PasswordAuthentication getPasswordAuthentication() {
                         return new PasswordAuthentication(username, password);
                     }
                 });

         try {

             Message message = new MimeMessage(session);
             message.setFrom(new InternetAddress(username));
             message.setRecipients(
                     Message.RecipientType.TO,
                     InternetAddress.parse(ReceiverAddress)
             );
             message.setSubject("Mail By My Bank");
             message.setText("Hii...You created new Account in our Bank");

             Transport.send(message);

             System.out.println("Done");

         } catch (MessagingException e) {
             e.printStackTrace();
         }
    }
}