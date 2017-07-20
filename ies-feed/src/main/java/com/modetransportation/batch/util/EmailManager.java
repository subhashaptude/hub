package com.modetransportation.batch.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.modetransportation.batch.util.ModeConstants;
import com.modetransportation.batch.exception.HubException;

public class EmailManager {
    // The addresses and attachment file names can be delimited using the following chars.
    private static final String DELIMITERS = ", ;";
    private static final String FALSE = "false";

    /** Arraylist to hold messages */
    private static ArrayList messageList_ = new ArrayList();

    /**
     * Parses the given delimited address list into separate strings and returns
     * an array
     *
     * @param toAddresses delimited (comma, semicolon or space) list of email addresses.
     * @return list of addresses.
     */
    private static String[] parseAddressList( String toAddresses )
    {
        Vector list = new Vector();

        StringTokenizer tokenizer = new StringTokenizer( toAddresses, DELIMITERS );
        while( tokenizer.hasMoreTokens() )
        {
            String token = tokenizer.nextToken();
            list.addElement( token );
        }

        String[] toList = new String[ list.size() ];
        list.copyInto( toList );

        return toList;
    }

    /**
     * Populate a properties object with given parameters
     *
     * @param from The from address
     * @param to comma-separated list of "to" addresses
     * @param cc comma-separated list of "cc" addresses
     * @param bcc comma-separated list of "bcc" addresses
     * @param subject The subject for this message
     * @param attachments The attachments for this mail
     * @return A populated Properties object
     */
    public static Properties fillProperties(String from, String to, String cc, String bcc,
                String subject, String attachments)
    {
        return fillProperties(from, to, cc, bcc, subject, null, attachments);
    }

    /**
     * Populate a properties object with given parameters
     *
     * @param from The from address
     * @param to comma-separated list of "to" addresses
     * @param cc comma-separated list of "cc" addresses
     * @param bcc comma-separated list of "bcc" addresses
     * @param subject The subject for this message
     * @param msg The message text
     * @param attachments The attachments for this mail
     * @return A populated Properties object
     */
    public static Properties fillProperties(String from, String to, String cc, String bcc,
                String subject, String msg, String attachments)
    {
        Properties mailProps = new Properties();

        mailProps.put( ModeConstants.EMAIL_FROM,
                            (from != null ? from : ModeConstants.DEFAULT_EMAIL_FROM ));
        mailProps.put( ModeConstants.EMAIL_TO_LIST,
                            (to != null ? to : ModeConstants.DEFAULT_EMAIL_TO ));
        if ( cc != null )
            mailProps.put( ModeConstants.EMAIL_CC_LIST,cc);
        if ( bcc != null )
            mailProps.put( ModeConstants.EMAIL_BCC_LIST,bcc);

        mailProps.put( ModeConstants.EMAIL_SUBJECT,
                            (subject != null ? subject : ModeConstants.DEFAULT_EMAIL_SUBJECT ));
        if ( attachments != null )
            mailProps.put( ModeConstants.EMAIL_ATTACHMENTS, attachments);
        if ( msg != null )
            mailProps.put( ModeConstants.EMAIL_MESSAGE, msg);

        return mailProps;
    }

    /**
     * This method sends the given message to default recipients using a default
     * from address. One should call this method only if "from" and "to" addresses are
     * are not available in application properties due to any reason.
     *
     * @param subject The subject for this message
     * @param msg The body text for this message
     */
    public static void sendMail( String subject, String msg )
        throws Exception
    {
        sendMail( ModeConstants.DEFAULT_EMAIL_FROM, ModeConstants.DEFAULT_EMAIL_TO, subject, msg);
    }

    /**
     * Sends an email message using the given parameters.
     *
     * @param from The from address
     * @param to comma-separated list of "to" addresses
     * @param cc comma-separated list of "cc" addresses
     * @param bcc comma-separated list of "bcc" addresses
     * @param subject The subject for this message
     * @param msg The body text for this message
     */
    public static void sendMail( String from, String to, String cc, String bcc,
                String subject, String msg )
        throws Exception
    {
        Properties mailProps = fillProperties(from, to, cc, bcc, subject, null);
        mailProps.put( ModeConstants.EMAIL_MESSAGE,
                            (msg != null ? msg : ModeConstants.DEFAULT_EMAIL_MESSAGE ));
        sendMail( mailProps );
    }


    /**
     * Sends an email message using the given parameters.
     *
     * @param from The from address
     * @param to comma-separated list of "to" addresses
     * @param subject The subject for this message
     * @param msg The body text for this message
     */
    public static void sendMail( String from, String to, String subject, String msg )
        throws Exception
    {
        sendMail(from, to, null, null, subject, msg);
    }

    /**
     * Sends an email message using the given parameters.
     *
     * @param from The from address
     * @param to comma-separated list of "to" addresses
     * @param cc comma-separated list of "cc" addresses
     * @param subject The subject for this message
     * @param msg The body text for this message
     */
    public static void sendMail( String from, String to, String cc, String subject, String msg )
        throws Exception
    {
        sendMail(from, to, cc, null, subject, msg);
    }

    /**
     * Sends an email message using the values specified in the properties parameter.
     * The  properties parameter should contain following properties:
     *
     *      mail.host (default localhost)
     *      mail.transport.protocol (default smtp)
     *      email.from
     *      email.to.list
     *      email.cc.list (optional)
     *      email.bcc.list (optional)
     *      email.subject
     *      email.message
     *      email.attachments (optional)
     *      email.send.background (optional)
     *
     * @param mailProps application properties.
     * @exception Thrown by the JavaMail system if the message could not be
     *            sent to some or any of the recipients.
     */
    public static void sendMail( Properties mailProps )
        throws Exception
    {
        String mailHost = null, protocol = null;
        String strFrom = mailProps.getProperty( ModeConstants.EMAIL_FROM );
        String subject = mailProps.getProperty( ModeConstants.EMAIL_SUBJECT );
        String msgText = mailProps.getProperty( ModeConstants.EMAIL_MESSAGE );
        String attachments = mailProps.getProperty( ModeConstants.EMAIL_ATTACHMENTS );
        String dataSourceFile = mailProps.getProperty(ModeConstants.EMAIL_FILE_DATASOURCE);

        if (strFrom == null || strFrom.length() == 0)
        {
            strFrom = ModeConstants.DEFAULT_EMAIL_FROM;
        }

        if (subject == null || subject.length() == 0)
        {
            subject = ModeConstants.DEFAULT_EMAIL_SUBJECT;
        }

        if ( msgText == null && dataSourceFile == null)
        {
            msgText = ModeConstants.DEFAULT_EMAIL_MESSAGE;
        }
        else if ( msgText != null && msgText.length() == 0 )
        {
            msgText = ModeConstants.DEFAULT_EMAIL_MESSAGE;
        }
        else if ( dataSourceFile != null )
        {
            File file = new File(dataSourceFile);
            if ( !file.isFile() )
                throw new Exception("Invalid datasource file name");
        }

        //use default email host and transport protocol if not avaialble in the
        //resource manager.
        try
        {
            mailHost = ResourceManager.getString(ModeConstants.MAIL_HOST);
            if ( ( mailHost == null ) || ( mailHost.length() == 0 ) )
                    mailHost = ModeConstants.DEFAULT_MAIL_HOST;

            protocol = ResourceManager.getString(ModeConstants.MAIL_TRANSPORT_PROTOCOL);
            if ( ( protocol == null ) || ( protocol.length() == 0 ) )
                    protocol = ModeConstants.MAIL_TRANSPORT_PROTOCOL;
        }
        catch ( Exception e )
        {
            mailHost = ModeConstants.DEFAULT_MAIL_HOST;
            protocol = ModeConstants.MAIL_TRANSPORT_PROTOCOL;
        }

        mailProps.put( ModeConstants.MAIL_HOST, mailHost );
        mailProps.put( ModeConstants.MAIL_TRANSPORT_PROTOCOL, protocol );

        Message message = null;
        try
        {
            message = new MimeMessage( Session.getDefaultInstance( mailProps, null ) );
            MimeMultipart mimeMultipart = new MimeMultipart();
            MimeBodyPart mimeBodyPart = null;

            // Set the from address
            InternetAddress fromAddr = new InternetAddress( strFrom );
            message.setFrom( fromAddr );

            // Set the recipients
            String[] toList =
                    parseAddressList( mailProps.getProperty( ModeConstants.EMAIL_TO_LIST ));
            InternetAddress[] toAddresses = new InternetAddress[ toList.length ];
            for ( int i = 0; i < toList.length; i++ )
            {
                toAddresses[i] = new InternetAddress( toList[i] );
            }
            message.addRecipients( Message.RecipientType.TO, toAddresses );

            String strCC = mailProps.getProperty( ModeConstants.EMAIL_CC_LIST );
            if ( strCC != null && strCC.trim().length() > 0 )
            {
                String[] ccList = parseAddressList(strCC);
                InternetAddress[] ccAddresses = new InternetAddress[ ccList.length ];
                for ( int i = 0; i < ccList.length; i++ )
                {
                    ccAddresses[i] = new InternetAddress( ccList[i] );
                }
                message.addRecipients( Message.RecipientType.CC, ccAddresses );
            }

            String strBCC = mailProps.getProperty( ModeConstants.EMAIL_BCC_LIST );
            if ( strBCC != null && strBCC.trim().length() > 0 )
            {
                String[] bccList = parseAddressList(strBCC);
                InternetAddress[] bccAddresses = new InternetAddress[bccList.length];
                for ( int i = 0; i < bccList.length; i++ )
                {
                    bccAddresses[i] = new InternetAddress( bccList[i] );
                }
                message.addRecipients( Message.RecipientType.BCC, bccAddresses );
            }

            // Set the subject, date sent and the message
            message.setSubject( subject );
            message.setSentDate( new java.util.Date() );

            //attach the body to the mulit part
            if ( msgText != null )
            {
                mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setText( msgText );
                mimeMultipart.addBodyPart( mimeBodyPart );
            }
            else if ( dataSourceFile != null )
            {
                DataSource source = new FileDataSource(dataSourceFile);
                //message.setDataHandler(new DataHandler(source));
                //message.saveChanges();
                mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setDataHandler(new DataHandler(source));
                mimeMultipart.addBodyPart( mimeBodyPart );
            }

            //read and set the attachment files
            if ( attachments != null && attachments.length() > 0 )
            {
                StringTokenizer tokenizer = new StringTokenizer( attachments, DELIMITERS );
                while( tokenizer.hasMoreTokens() )
                {
                    FileDataSource fds = new FileDataSource( tokenizer.nextToken() );

                    mimeBodyPart = new MimeBodyPart();
                    mimeBodyPart.setDataHandler( new DataHandler( fds ) );
                    mimeBodyPart.setFileName( fds.getName() );
                    mimeMultipart.addBodyPart( mimeBodyPart );
                }
            }

            //add the multipart to the message
            message.setContent( mimeMultipart );
            Transport.send( message );
        }
        catch ( MessagingException me )
        {
            System.err.println( ModeConstants.MSG_CRITICAL_ERROR + ":Error in sending email " +
                        "\n\t" + message.toString() +
                        "\n\t" + me.toString() );
            throw new HubException(me);
        }
    }

    /**
     * Sends an email message using the given parameters.
     *
     * @param from The from address
     * @param to comma-separated list of "to" addresses
     * @param cc comma-separated list of "cc" addresses
     * @param bcc comma-separated list of "bcc" addresses
     * @param subject The subject for this message
     * @param file A file containing the text to be sent.
     */
    public static void sendMail(String from, String to, String cc, String bcc,
                String subject, File file)
        throws Exception
    {
        if ( ! file.isFile() )
            throw new Exception("Invalid datasource file name");

        Properties mailProps = fillProperties(from, to, cc, bcc, subject, null);
        mailProps.put( ModeConstants.EMAIL_FILE_DATASOURCE, file.getAbsolutePath());

        sendMail( mailProps );
    }

    /**
     * Sends an email message using the given parameters.
     *
     * @param from The from address
     * @param to comma-separated list of "to" addresses
     * @param cc comma-separated list of "cc" addresses
     * @param subject The subject for this message
     * @param file A file containing the text to be sent.
     */
    public static void sendMail(String from, String to, String cc,
                String subject, File file)
        throws Exception
    {
        sendMail( from, to, cc, null, subject, file);
    }

    /**
     * Sends an email message using the given parameters.
     *
     * @param from The from address
     * @param to comma-separated list of "to" addresses
     * @param subject The subject for this message
     * @param file A file containing the text to be sent.
     */
    public static void sendMail(String from, String to, String subject, File file)
        throws Exception
    {
        sendMail( from, to, null, null, subject, file);
    }

    /**
     * Method to test this component.
     * This method expects one command line argument and that is the absolute path of the
     * directory from where properties are to be read.
     *
     * The given directory must contain property file(s) with necessary properties for
     * ResourceManager and the EmailManager to be intialized properly.
     *
     * Following properties must be avaialble in the properties files in order to
     * test sendMail(Properties) method:
     *
     *      email.from,
     *      email.distribution.list,
     *      email.subject,
     *      email.message, and
     *      email.attachments.
     *
     * @param args[] array of command line arguments.
     */
    public static void main( String[] args )
    {
        if ( args.length != 1 )
        {
            System.out.println( "Usage: java EmailManager <<properties directory>>" );
            System.exit(0);
        }

        java.io.File file = new java.io.File( args[0] );
        if ( !file.exists() || !file.isDirectory() )
        {
            System.out.println(
                "Passed argument either does not exists or is not a valid directory name: " +
                args[0]);
        }

        //Initialize any other components which email manager is depenedent on.
        try
        {
            ResourceManager.init( args[0] );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Properties appProps = new Properties();

        try
        {
            appProps.put( ModeConstants.EMAIL_FROM,
                                    ResourceManager.getString( ModeConstants.EMAIL_FROM ) );
            appProps.put( ModeConstants.EMAIL_TO_LIST,
                                ResourceManager.getString( ModeConstants.EMAIL_TO_LIST ) );
            appProps.put( ModeConstants.EMAIL_SUBJECT,
                                ResourceManager.getString( ModeConstants.EMAIL_SUBJECT ) );
            appProps.put( ModeConstants.EMAIL_MESSAGE,
                                ResourceManager.getString( ModeConstants.EMAIL_MESSAGE ) );
            appProps.put( ModeConstants.EMAIL_ATTACHMENTS,
                                ResourceManager.getString( ModeConstants.EMAIL_ATTACHMENTS ) );

            EmailManager.sendMail( appProps );
            EmailManager.sendMail( ResourceManager.getString( ModeConstants.EMAIL_FROM ),
                        ResourceManager.getString( ModeConstants.EMAIL_TO_LIST ),
                        ResourceManager.getString( ModeConstants.EMAIL_SUBJECT ),
                        ResourceManager.getString( ModeConstants.EMAIL_MESSAGE ));

            System.exit( 0 );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
     }

}
