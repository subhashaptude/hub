package com.modetransportation.batch.logging;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.modetransportation.batch.util.ModeConstants;
import com.modetransportation.batch.util.ResourceManager;
import com.modetransportation.batch.util.ModeConstants;

public class Logger {
	private static Logger instance = null;
	private PrintStream writer = null;
	private static String logFileName = null;

	/**
	 * This constrcutor creats the log file if does not exists, and
	 * creates writer to log messages and initializes any other class variables.
	 */
    private Logger()
    {
		logFileName = readLogFileName();
		createLogWriter( logFileName );
	}

	/**
	 * This redirects all the errors to System.err, this occurs when
	 * ResourceManager has not been or cannot be initialized.
	 */
	private void createDefaultWriter()
	{
		writer = System.err;
		logFileName = ModeConstants.SYSTEM_ERROR;
	}

	/**
	 * Creates the log writer object.
	 *
	 * @param	fileName	Name of the file to which logging will be done
	 */
	private void createLogWriter( String fileName )
	{
		// file name is null, need to redirect log to system err
		if ( fileName == null )
		{
			createDefaultWriter();
			return;
		}

		File file = new File( fileName );

		//close the current writer if it is not using system error writer.
		if 	( ( writer != null ) &&
			  ( !getLogFileName().equalsIgnoreCase( ModeConstants.SYSTEM_ERROR ) )
			)
		{
			writer.flush();
			writer.close();
		}
		writer = null;

		try
		{
			if ( !file.exists() )
			{
				file.createNewFile();
			}

			//create the writer in append mode
			writer = new PrintStream( new FileOutputStream( fileName, true ) );
			logFileName = fileName;
		}
		catch ( Exception e )
		{
			System.err.println(e);
			createDefaultWriter();
		}
	}

	/**
	 * Returns the filename being used by the logger.
	 *
	 * @return absolute name of the log file being used by the Logger.
	 */
	private static String getLogFileName()
	{
		return logFileName;
	}

	/**
	 * Initializes the logger.
	 */
	public static void init()
	{
		if ( instance == null )
		{
			instance = new Logger();
		}
	}

	/**
	 * Tests to see if Logger is initialized.
	 *
	 * @return true if this component is intialized and false otherwise
	 */
	public static boolean isInitialized()
	{
	    return ( instance != null );
	}

	/**
	 * Prepends the text "LOG :" and current time to the given message and
	 * writes the resultant message to the log file.
	 *
	 * @param msg message to write.
	 */
	public static void log( String msg )
	{
        StringBuffer buffer = new StringBuffer();
        buffer.append(ModeConstants.LOG);
        buffer.append(new java.util.Date().toString());
        buffer.append(" (" + Thread.currentThread().getThreadGroup().getName() +
                    ":" + Thread.currentThread().getName() + ")");
        buffer.append(":\n\t ");
        buffer.append(msg);
        writeMsg( buffer.toString() );
	}

    /**
	 * Returns the filename for the logger. If a dateBasedLogFileName property is true
	 * it appends a formatted current date to the file name. Otherwise it simply
	 * returns the base file as the log filename.
	 *
	 * @return Absolute log file name;
	 *         null if the required properties are not avaialbe from resource manager.
	 */
	private static String readLogFileName()
	{
		try
		{
            String fileName = ResourceManager.getString( ModeConstants.LOG_FILENAME );
			if ( ResourceManager.getBoolean( ModeConstants.LOG_USE_DATEBASED ) )
			{
				// If no date format could be found in the properties, use default.
				String dateFormat = ResourceManager.getString( ModeConstants.LOG_DATEFORMAT );
				if ( dateFormat == null || dateFormat.length() == 0 )
				{
					dateFormat = ModeConstants.DEFAULT_DATE_FORMAT;
				}

				SimpleDateFormat dateFormatter = new SimpleDateFormat( dateFormat );
				String dateString = dateFormatter.format( new Date() );
				fileName += "." + dateString;
			}
			return fileName;
		}
		catch ( Exception e )
		{
			//System.err.println(e);
            //  Properties were not set. Use defaults
			return null;
		}
	}

	/**
	 * Prepends the text "TRACE :" and current time to the given message and
	 * writes the given message to the log file if property "log.trace.enabled" is set to true.
	 *
	 * @param msg message to trace.
	 */
	public static void trace( String msg )
	{
		if ( isTraceEnabled() )
		{
            StringBuffer buffer = new StringBuffer();
            buffer.append(ModeConstants.TRACE);
            buffer.append(new java.util.Date().toString());
            buffer.append(" (" + Thread.currentThread().getThreadGroup().getName() +
                    ":" + Thread.currentThread().getName() + ")");
            buffer.append(":\n\t ");
            buffer.append(msg);
			writeMsg( buffer.toString() );
		}
	}

	/**
	 * Prepends the text "TRACE :" and current time to the given message and
	 * writes the given message to the log file if property "log.trace.enabled" is set to true.
	 *
     * @param exception Exception to be logged
     * @param msg A message to be logged with the exception
	 */
	public static void trace(Exception exception,  String msg )
	{
		if ( isTraceEnabled() )
		{
            StringBuffer buffer = new StringBuffer();
            buffer.append(ModeConstants.TRACE);
            buffer.append(new java.util.Date().toString());
            buffer.append(" (Thread: " + Thread.currentThread().getName() + ")");
            buffer.append(":\n\t ");
            buffer.append(msg);
			writeMsg( buffer.toString() );

            exception.printStackTrace(instance.writer);
		}
	}

	/**
	 * This method will simply write the current exception to the log file. This
     * method should be used when it is not necessary to invoke the
     * ExceptionHandler because there is no need for the alarm and email functions
     * of the ExceptionHandler. This method is similar to the trace message with
     * the same signature, but is being added because in most cases it is
     * not optional to output the details of an exception. The trace method is
     * being retained for backward compatibility.
     *
     * @param exception Exception to be logged
     * @param msg A message to be logged with the exception
	 */
	public static void log(Exception exception,  String msg )
	{
        StringBuffer buffer = new StringBuffer();
        buffer.append(ModeConstants.LOG);
        buffer.append(new java.util.Date().toString());
        buffer.append(" (Thread: " + Thread.currentThread().getName() + ")");
        buffer.append(":\n\t ");
        buffer.append(msg);
        writeMsg( buffer.toString() );
        exception.printStackTrace(instance.writer);
	}


	/**
	 * writes the message to the log file.
	 *
	 * @param msg message to write.
	 */
	public static void println( String msg )
	{
        writeMsg( msg );
	}

    /**
     * Check if tracing is enabled for the application. Clients can use this
     * method to check if tracing is enabled before they build non-trivial trace
     * messages. For trivial messages, this check need not be done by the client.
     */
    public static boolean isTraceEnabled()
    {
        boolean traceEnabled = false;
        try
        {
            traceEnabled = ResourceManager.getBoolean( ModeConstants.LOG_TRACE_ENABLED );
        }
        catch ( Exception e )
        {
			System.err.println(e);
            //  Ignore
        }
        return traceEnabled;
    }

	/**
	 * writes the resultant message to the log file.
	 *
	 * @param msg message to write.
	 */
	private static void writeMsg( String msg )
	{
		//attempt to initialize this component if it is not done.
		if ( !isInitialized() )
		{
			init();
		}

		//make sure to log the message to the appropriate file.
		String newFileName = readLogFileName();
		if ( ( newFileName != null ) && ( !getLogFileName().equalsIgnoreCase( newFileName ) ) )
		{
			instance.createLogWriter( newFileName );
		}

		// Given message must be written either to the log writer or to the system
		// error in case of error.
		try
		{
			instance.writer.println( msg );
			instance.writer.flush();
		}
		catch( Exception e )
		{
			//Write the message to the system error.
		    System.err.println( msg );
			e.printStackTrace();
		}
	}
	public static String getStackTraceAsString(Exception e) 
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		PrintWriter writer = new PrintWriter(bytes,true);
		e.printStackTrace(writer);
		return bytes.toString();
	}


    /**
	 * Method to test this component.
	 *
	 * This method expects one command line argument and that is the absolute path of the
	 * directory from where properties are to be read.
	 *
	 * This method initializes the ResourceManager first using the given properties directory.
	 * Writes sample test messages to the log file provided in the properties.
	 * While the program is running change the system data and time to check if the Logger
	 * is able to create new log file for changed date.
	 */
	public static void main( String[] args )
	{
		if ( args.length != 1 )
		{
			System.out.println( "Usage: java Logger <<properties directory>>" );
			System.exit(0);
		}

		File file = new File( args[0] );
		if ( !file.exists() || !file.isDirectory() )
		{
			System.out.println(
				"Passed argument either does not exists or is not a valid directory name: " +
				args[0]);
		}

		//Initialize the resource manager
		try
		{
			ResourceManager.init( args[0] );
			Logger.init();

			while (true)
			{
				Logger.log( "Test message." );
				Logger.trace( "Test trace message." );
				Thread.sleep( 30000 );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			System.exit( 0 );
		}
	}

}
