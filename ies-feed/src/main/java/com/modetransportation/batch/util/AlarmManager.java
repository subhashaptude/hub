package com.modetransportation.batch.util;

import java.util.*;
import java.net.*;

import com.modetransportation.batch.util.ModeConstants;
import com.modetransportation.batch.exception.DecoratorInterface;
import com.modetransportation.batch.exception.HubException;
import com.modetransportation.batch.logging.Logger;

/**
 * This class is used in the ExceptionHandler component to raise alarms in the
 * event of any exceptions.
 *
 * This class encapsulates the logic to raise alarms (sending PAGE and email messages).
 * Alarms are mostly raised due to exceptional situations. It uses the
 * EmailManager class to send emails.
 * Page messages are sent based on properties defined for different kinds of errors.
 * The logic for sending the Page is handled by AlarmGroupManager.
 *
 * The mailer details such as email host, protocol and the email sender/receipient details
 * are assumed to be available in the properties file. Refer to EmailManager documentation
 * for full details.
 *
 * @author
 * @version
 */
public class AlarmManager
{
	// System property Key(s)
	private static 	final String 	ALARM_GROUP_MANAGER_ENABLED = "alarm.group.manager.enabled",
									EMAIL						= "Email",
									PAGE						= "Page",
									ENVIRONMENT 				= "process.env";

	private boolean alarmGroupManagerEnabled_ = true;

	private String 	sendEmailTo_ 		= ModeConstants.DEFAULT_EMAIL_TO,
					emailMsgFrom_		= ModeConstants.DEFAULT_FROM_ADDRESS,
					hostName_			= "",
					sendPageTo_			= ModeConstants.DEFAULT_EMAIL_TO,
					environment_		= ModeConstants.DEFAULT_ENVIRONMENT;

	private static 	AlarmManager 		instance_;

	private 		DecoratorInterface 	decorator_;

	private static final String emailSeparator = "\n********************************************";

	/**
	 * The AlarmManager's constructor uses the  decorator object to decorate HubExceptions.
	 * It uses simple decoration for Page and detailed decoration for Emails.
	 * @param decor The class implementing DecoratorInterface.
	 */
    private AlarmManager(DecoratorInterface  decor)
    {
		decorator_ 			= decor;
		initProps();
	}

    /**
     * Constructs the singleton Alarm Manager.
     */
    public static AlarmManager getInstance(DecoratorInterface  decor)
    {
        if (instance_ == null)
            instance_ = new AlarmManager(decor);

        return instance_;
    }
	/**
	 * Raises an Email alarms.
	 * The Email alarm will be sent to those identified by the property
	 * email.distribution.list
	 *
	 * @param ArrayList of hubExs The exceptions ArrayList.
	 */
	public void raiseEmailAlarms(ArrayList hubExs) throws Exception
	{
		if(hubExs == null || hubExs.size() == 0)
		{
			return;
		}
		else
		{
			raiseAlarms(hubExs, EMAIL);
		}
	}
	/**
	 * Raises an Email alarm.
	 * The Email alarm will be sent to those identified by the property
	 * email.distribution.list
	 *
	 * @param hubEx The exception which is the reason to raise this page.
	 */
	public void raiseEmailAlarm(HubException hubEx) throws Exception
	{
		raiseAlarm(hubEx, EMAIL);
	}
	/**
	 * This method can be used in two modes, Email and Page.
	 * Error Message and Recepients differ for these two cases.
	 * For Emails the message is more detailed than for Page.
	 * Raises an Email alarm. It uses the EmailManager class to raise the alarm.
	 *
	 * @param ArrayList of hubExs The exceptions ArrayList.
	 */
	private void raiseAlarms(ArrayList hubExs, String mode) throws Exception
	{
		if(hubExs == null || hubExs.size() == 0)
		{
			return;
		}

		String 			message 	= null;
		HubException hubEx0 = (HubException)hubExs.get(0);//assuming all the alarms related to the same program.
        String 			programName = hubEx0.getProgramName();
		String 			subject 	= ModeConstants.DEFAULT_PAGE_SUBJECT;
		String			mailTo		= null;
		StringBuffer 	sbuf 		= new StringBuffer();

        sbuf.append("Host: ").append(hostName_).append(":\n");

        if( mode.equals(EMAIL) )
        {
        	for(int i = 0; i < hubExs.size(); i++)
			{
				HubException hubEx = (HubException)hubExs.get(i);
				sbuf.append( (String) decorator_.decorateDetailed(hubEx) );
				sbuf.append(emailSeparator);
			}
        	mailTo = sendEmailTo_;
		}
        else if ( mode.equals(PAGE) )
        {
        	for(int i = 0; i < hubExs.size(); i++)
			{
				HubException hubEx = (HubException)hubExs.get(i);
				sbuf.append( (String) decorator_.decorateSimple(hubEx) );
				sbuf.append(emailSeparator);
			}
        	mailTo = sendPageTo_;
		}

        message = sbuf.toString();

		if ( !StringUtils.isStringEmpty(programName) )
			subject = programName;

		/**
		 * Try to get the current environment from the system property.
		 * The Environment could be localdev, dev, qa or prod
		 */
		String environment = ResourceManager.getString(ENVIRONMENT, "qa");
		if ( !StringUtils.isStringEmpty(environment) )
			subject = environment + ":" + subject;

		// Send Email, if it fails then log the error
		try
		{
			EmailManager.sendMail(emailMsgFrom_, mailTo, subject, message);
		}
		catch ( Exception e )
		{
             // Email was not sent so log the message
			Logger.log( ModeConstants.MSG_CRITICAL_ERROR +
						"\n\t:Email not sent " + "=subject=" + subject +
						"\n\t=message=" + message + "=\n\t" + e.toString() );
            throw new HubException(e);
		}
	}

	/**
	 * This method can be used in two modes, Email and Page.
	 * Error Message and Recepients differ for these two cases.
	 * For Emails the message is more detailed than for Page.
	 * Raises an Email alarm. It uses the EmailManager class to raise the alarm.
	 *
	 * @param hubEx The exception which is the reason to raise this page.
	 */
	private void raiseAlarm(HubException hubEx, String mode) throws Exception
	{
		String 			message 	= null;
        String 			programName = hubEx.getProgramName();
		String 			subject 	= ModeConstants.DEFAULT_PAGE_SUBJECT;
		String			mailTo		= null;
		StringBuffer 	sbuf 		= new StringBuffer();

        sbuf.append("Host: ").append(hostName_).append(":\n");

        if( mode.equals(EMAIL) )
        {
        	sbuf.append( (String) decorator_.decorateDetailed(hubEx) );
        	mailTo = sendEmailTo_;
		}
        else if ( mode.equals(PAGE) )
        {
        	sbuf.append( (String) decorator_.decorateSimple(hubEx) );
        	mailTo = sendPageTo_;
		}

        message = sbuf.toString();

		/**
		 * Try to get the current environment from the system property.
		 * The Environment could be localdev, dev, qa or prod
		 */
		String environment = ResourceManager.getString(ENVIRONMENT, "");
		if ( !StringUtils.isStringEmpty(environment) ) {
			environment_ = environment;
			subject = subject + "[" + environment_ + "] ";
		}


		if ( !StringUtils.isStringEmpty(programName) )
			subject = subject + programName;


		// Send Email, if it fails then log the error
		try
		{
			EmailManager.sendMail(emailMsgFrom_, mailTo, subject, message);
		}
		catch ( Exception e )
		{
             // Email was not sent so log the message
			Logger.log( ModeConstants.MSG_CRITICAL_ERROR +
						"\n\t:Email not sent " + "=subject=" + subject +
						"\n\t=message=" + message + "=\n\t" + e.toString() );
            throw new HubException(e);
		}
	}

	/**
	 * Raises a pager alarm. Based on property defined,
	 * uses the AlarmGroupManager or raiseAlarm() in Page mode to send pages.
	 * @param hubEx The exception which is the reason to raise this page.
	 */
	public void raisePageAlarm(HubException hubEx) throws Exception
	{
		raiseAlarm(hubEx, PAGE);
	}

	/**
	 * This method sends the actual page. This is used by AlarmGroupManager/AlarmGroup.
	 * @param		subject		is the subject for the page.
	 * @param		content		is the content for the page.
	 * @exceptio	Exception
	 */
	static void sendPageAlarm(String subject, String content) throws Exception
	{
		String 			message = null;

		StringBuffer 	sbuf 	= new StringBuffer();

        sbuf.append("Host: ").append(instance_.hostName_).append(":\n").append(content);
        message = sbuf.toString();

		// try sending email if it fails then log the error
		try
		{
			EmailManager.sendMail( instance_.emailMsgFrom_, instance_.sendPageTo_, subject, message );
		}
		catch ( Exception e )
		{
             // Email was not sent so log the message
			Logger.log( ModeConstants.MSG_CRITICAL_ERROR +
						"\n\t:Page not sent " + "=subject=" + subject +
						"\n\t=message=" + message + "=\n\t" + e.toString() );
            throw new HubException(e);
		}
	}

	/**
	 * This method reads properties relating to email/page.
	 */
	private void initProps()
	{
		String temp = null;
		try
		{
			temp  = ResourceManager.getString(ModeConstants.ALARM_EMAIL_TO_LIST);
			if( !StringUtils.isStringEmpty(temp) )
				sendEmailTo_ = temp;
		}
		catch ( Exception e )
		{
			Logger.log( ModeConstants.MSG_CRITICAL_ERROR + ModeConstants.LINE_BREAK +
						":To mail address not given " + e.toString() );
		}

		// if resource manager does not have this property then use default
		try
		{
			temp = ResourceManager.getString(ModeConstants.ALARM_EMAIL_FROM);
			if( !StringUtils.isStringEmpty(temp) )
				emailMsgFrom_ = temp;
		}
		catch ( Exception e )
		{
			Logger.log( ModeConstants.ALARM_EMAIL_FROM + ModeConstants.LINE_BREAK +
						":From Address not given " + e.toString() );
		}

		try
		{
			temp = ResourceManager.getString( ModeConstants.ALARM_PAGE_TO_LIST );
			if( !StringUtils.isStringEmpty(temp) )
				sendPageTo_ = temp;
		}
		catch (Exception e)
		{
			Logger.log( ModeConstants.MSG_CRITICAL_ERROR + ModeConstants.LINE_BREAK +
						":To Page address not given " + e.toString() );
		}

		try
		{
			hostName_ = InetAddress.getLocalHost().getHostName();
		}
		catch(Exception e)
		{}

		try
		{
			temp = ResourceManager.getString(ALARM_GROUP_MANAGER_ENABLED);
			if( !StringUtils.isStringEmpty(temp) && temp.equals(ModeConstants.TRUE) )
				alarmGroupManagerEnabled_ = true;
		}
		catch (Exception e)
		{}
	}
}
