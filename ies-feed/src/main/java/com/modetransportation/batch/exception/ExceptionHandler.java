package com.modetransportation.batch.exception;

import com.modetransportation.batch.util.ModeConstants;
import com.modetransportation.batch.logging.Logger;
import com.modetransportation.batch.util.AlarmManager;
import com.modetransportation.batch.util.ResourceManager;

public class ExceptionHandler {

    private static ExceptionHandler instance = null;

    private DecoratorInterface decorator = null;
    private AlarmManager alarmManager = null;

    /**
     * The Exception Handler's constructor. This constructor constructs an instance of
     * ExceptionDecorator and AlarmManager.
     */
    private ExceptionHandler()
    {
        decorator = new ExceptionDecorator();
        alarmManager =  AlarmManager.getInstance(decorator);
    }

    /**
     * Handles the exception based on its severity.
     *
     * The exception trace is written to the log file.
     * An email and/or page notification is sent depending on how an
     * exception severity is configured with respective to sending notifications.
     *
     * A runtime exception is thrown if this method is called before initializing this component.
     * @param hubEx The HubException object containing all the exception related information
     */
    public static void handleException( HubException hubex )
    {
        if ( !isInitialized() )
        {
            init();
        }
        instance.logException( hubex );

		boolean send_email = false, send_page = false;
		switch ( hubex.getSeverity() )
		{
			case ModeConstants.SEVERITY_CRITICAL:
				send_email = sendAlarm( ModeConstants.EXCEPTION_SEVERITY_CRITICAL_SENDMAIL );
				send_page = sendAlarm( ModeConstants.EXCEPTION_SEVERITY_CRITICAL_SENDPAGE );
				break;

			case ModeConstants.SEVERITY_HIGH:
				send_email = sendAlarm( ModeConstants.EXCEPTION_SEVERITY_HIGH_SENDMAIL );
				send_page = sendAlarm( ModeConstants.EXCEPTION_SEVERITY_HIGH_SENDPAGE );
				break;

			case ModeConstants.SEVERITY_MEDIUM:
				send_email = sendAlarm( ModeConstants.EXCEPTION_SEVERITY_MEDIUM_SENDMAIL );
				send_page = sendAlarm( ModeConstants.EXCEPTION_SEVERITY_MEDIUM_SENDPAGE );
				break;

			case ModeConstants.SEVERITY_LOW:
				send_email = sendAlarm( ModeConstants.EXCEPTION_SEVERITY_LOW_SENDMAIL );
				send_page = sendAlarm( ModeConstants.EXCEPTION_SEVERITY_LOW_SENDPAGE );
				break;

			case ModeConstants.SEVERITY_WARNING:
				send_email = sendAlarm( ModeConstants.EXCEPTION_SEVERITY_WARNING_SENDMAIL );
				send_page = sendAlarm( ModeConstants.EXCEPTION_SEVERITY_WARNING_SENDPAGE );
				break;

			default:
				//send an email for exceptions with unknown severity
				send_email = true;
				break;
		}

		// send email for the exception
        try
        {
            if ( send_email )
            {
                instance.alarmManager.raiseEmailAlarm( hubex );
            }
        }
        catch ( Exception e )
        {
            Logger.log("Unable to raise email alarm. Caught Exception " + e);
        }

		// send page for the exception
        try
        {
            if ( send_page )
            {
                instance.alarmManager.raisePageAlarm( hubex );
            }
        }
        catch ( Exception e )
        {
            Logger.log("Unable to raise page alarm. Caught Exception " + e);
        }
    }

    /**
     * Handles any uncaught Throwable exception. Typically, the action will be
     * to log the exception, raise an alarm and to terminate the application.
     *
     * @param  th The exception that was not handled by the application.
     */
    public static void handleException( Throwable th )
    {
        HubException hubex = new HubException( ModeConstants.MSG_CRITICAL_ERROR,
        											ModeConstants.SEVERITY_CRITICAL, th );
        handleException( hubex );
    }

    /**
     * Constructs the singleton exception handler component.
     */
    public static void init()
    {
        if ( instance == null )
        {
            instance = new ExceptionHandler();
        }
    }

    /**
     * Tests to see if exception handler is initialised.
	 *
	 * @return true if this component is intialized, else false.
     */
    public static boolean isInitialized()
    {
        return ( instance != null );
    }

    /**
     * Logs the given exception.
     *
     * @param exceptionToLog The exception that needs to be logged
     */
    private void logException( HubException exceptionToLog )
    {
        String message = ( String ) decorator.decorate( exceptionToLog );
        Logger.log( message );
    }

	/**
	 * This returns the value for the given property from the resource
	 * manager, if the resource manager does not contain the property
	 * then return false.
	 *
	 * @param	name	property name whose value needs to be obtained
	 * @return value of the property
	 */
	private static boolean sendAlarm( String name )
	{
		boolean send_alarm = false;
		try
		{
			send_alarm = ResourceManager.getBoolean( name );
		}
		catch ( Exception e )
		{
			send_alarm = false;
		}
		return send_alarm;
	}

    /**
     * Method to test this component.
     * This method expects one command line argument and that is the absolute path of the
     * directory from where properties are to be read.
     *
     * The properties file must contain following keys with values set:
     *
     * exception.severity.critical.sendpage    \
     * exception.severity.critical.sendmail     \
     * exception.severity.high.sendpage          \
     * exception.severity.high.sendmail           \
     * exception.severity.medium.sendpage          \__ Set to true or false to test various scenarios
     * exception.severity.medium.sendmail          /
     * exception.severity.low.sendpage            /
     * exception.severity.low.sendmail           /
     * exception.severity.warning.sendpage      /
     * exception.severity.warning.sendmail     /
     *
     * mail.host
     * mail.transport.protocol
     * email.distribution.list
     * page.distribution.list
     * email.from
     * email.subject
     * email.message
     * email.attachments (optional)
     *
     * @param args array of command line arguments.
     */
    public static void main( String[] args )
    {
        if ( args.length != 1 )
        {
            System.out.println( "Usage: java ExceptionHandler <<properties directory>>" );
            System.exit( 0 );
        }

        java.io.File file = new java.io.File( args[ 0 ] );
        if ( !file.exists() || !file.isDirectory() )
        {
            System.out.println(
				"Passed argument either does not exists or is not a valid directory name: " +
				args[ 0 ] );
        }

        // Initialize other any other components which exception handler component is
        // depenedent on.
        try
        {
        	ResourceManager.init( args[ 0 ] );
        	Logger.init();
            ExceptionHandler.init();

            //  Test HubException
            HubException hubex = new HubException( "Test exception.",
											ModeConstants.SEVERITY_HIGH,
											new NullPointerException( "test base exception" ) );
            hubex.setProgramName( "ExceptionHandler Test" );

            ExceptionHandler.handleException( hubex );

            System.exit( 0 );

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
	
}
