package comq.common;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;

public class SMTPAuthenticator extends Authenticator {
    public SMTPAuthenticator() {
        super();
    }

    public PasswordAuthentication getPasswordAuthentication() {
        String username = "narujb@gmail.com";
        String password = "dpstltm21231258";
        return new PasswordAuthentication(username, password);
    }
}