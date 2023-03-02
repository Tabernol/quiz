package util;

import javax.mail.Session;
import java.util.Properties;




public class SimpleEmail {
    public static void main(String[] args) {

        System.out.println("SimpleEmail Start");

        String smtpHostServer = "localhost";
        String emailID = "mecoseh694@wireps.com";

        Properties props = System.getProperties();

        props.put("mail.smtp.host", smtpHostServer);

        Session session = Session.getInstance(props, null);

        EmailUtil.sendEmail(session, emailID, "SimpleEmail Testing Subject", "SimpleEmail Testing Body");


////        ===================================
//        Properties props = new Properties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.host", "localhost");
//        props.put("mail.smtp.port", 25);
//        Session session = Session.getDefaultInstance(props);
//        session.setDebug(true);
////        ========================
//        EmailUtil.sendEmail(session, emailID,"SimpleEmail Testing Subject", "SimpleEmail Testing Body");
    }
}
