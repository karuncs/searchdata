package mail;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import sesame.IConstant;


public class Sender {

  /** The message recipient. */
  protected String message_recip = "fred@cs.aau.dk";

  /* What's it all about, Alfie? */
  protected String message_subject = "Your Delicious account was created!";

  /** The message CC recipient. */
  protected String message_cc = "fred@cs.aau.dk";

  /** The message body */
  protected String message_body = "I am unable to attend to your message, as I am busy sunning"
      + "myself on the beach in Maui, where it is warm and peaceful."
      + "Perhaps when I return I'll get around to reading your mail."
      + "Or perhaps not.";

  /** The JavaMail session object */
  protected Session session;

  /** The JavaMail message object */
  protected Message mesg;

  /** Do the work: send the mail to the SMTP server. */
  public void doSend() {

    // We need to pass info to the mail server as a Properties, since
    // JavaMail (wisely) allows room for LOTS of properties...
    Properties props = new Properties();

    // Your LAN must define the local SMTP server as "mailhost"
    // for this simple-minded version to be able to send mail...
    props.put("mail.smtp.host", IConstant.AAU_SMTP);

    // Create the Session object
    session = Session.getDefaultInstance(props, null);
    session.setDebug(false); // Verbose!

    try {
      // create a message
      mesg = new MimeMessage(session);

      // From Address - this should come from a Properties...
      mesg.setFrom(new InternetAddress("fred@cs.aau.dk"));

      // TO Address
      InternetAddress toAddress = new InternetAddress(message_recip);
      mesg.addRecipient(Message.RecipientType.TO, toAddress);

      // CC Address
//      InternetAddress ccAddress = new InternetAddress(message_cc);
//      mesg.addRecipient(Message.RecipientType.CC, ccAddress);

      // The Subject
      mesg.setSubject(message_subject);

      // Now the message body.
      mesg.setText(message_body);
      // XXX I18N: use setText(msgText.getText(), charset)

      // Finally, send the message!
      Transport.send(mesg);

    } catch (MessagingException ex) {
      while ((ex = (MessagingException) ex.getNextException()) != null) {
        ex.printStackTrace();
      }
    }
  }
  
  public Sender() {
  }

  /** Simple test case driver */
  public static void main(String[] av) {
    Sender sm = new Sender();
    sm.doSend();
  }

public String getMessage_subject() {
	return message_subject;
}

public void setMessage_subject(String message_subject) {
	this.message_subject = message_subject;
}

public String getMessage_body() {
	return message_body;
}

public void setMessage_body(String message_body) {
	this.message_body = message_body;
}

public Sender(String message_recip, String message_subject, String message_body) {
	super();
	this.message_recip = message_recip;
	this.message_subject = message_subject;
	this.message_body = message_body;
}
}
