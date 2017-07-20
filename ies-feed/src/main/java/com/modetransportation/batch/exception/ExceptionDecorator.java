package com.modetransportation.batch.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import com.modetransportation.batch.exception.HubException;


public class ExceptionDecorator implements DecoratorInterface
{
	/**
	 * Static variables
	 */
	private static final String MSG_TERMINATED = " Terminated due to critical errors:";
	//private static MQErrorMessages mqMsgs = new MQErrorMessages();

	/**
	 * Instance Variables
	 */
	private boolean useSimpleDecoration = false;

	/**
	 * The default constructor.
	 */
	public ExceptionDecorator()
	{
	}

	/**
	 * The ExceptionLogDecorator can be used in either a simple or detailed
	 * decoration mode.
	 *
	 * @param useSimple - set to true if you want simple decoration, false for detailed.
	 */
	public ExceptionDecorator( boolean useSimple )
	{
		useSimpleDecoration = useSimple;
	}

	/**
	 * Returns a decorated version of the given object.
	 *
	 * @param obj  The object to be decorated.
	 * @return Object  A String object containing the decorated object.
	 */
	public Object decorate( Object obj )
	{
		if ( useSimpleDecoration )
			return decorateSimple( obj );
		else
			return decorateDetailed( obj );
	}

	/**
	 * Performs a detailed decoration (includes stack trace) of the given object.
	 * If the given object is not a derived class of Throwable, it returns the toString()
	 * version of that object.
	 *
	 * @param obj  The object to be decorated.
	 * @return Object  A String object containing the decorated object.
	 */
	public Object decorateDetailed( Object obj )
	{
		if ( obj != null )
		{
			if ( Throwable.class.isInstance( obj ) )
			{
				return decorateException( (Throwable)obj, 0 );
			}
			else
				return obj.toString();
		}

		throw new NullPointerException( "object to decorate was null" );
	}

	/**
	 * Decorates the given exception in a detailed manner
	 *
	 * This method is recursive.
	 *
	 * Since exceptions can be nested, we use the same method to decorate nested exceptions
	 * Whenever a nested exception is encountered, we increase the indent level before
	 * calling this method, this is to ensure that all nested exceptions appear under the
	 * same root.
	 *
	 * @param exception The object to be decorated.
	 * @return Stirng containing the resultant object.
	 */
	private String decorateException( Throwable exception, int indentLevel  )
	{
		String decoratedMessage = "";

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new IndentingWriter( indentLevel,stringWriter );

		/**
		 * First check to see if this is a HubException
		 */
		if ( exception instanceof HubException )
		{
			HubException hubEx = (HubException) exception;

			String programName = hubEx.getProgramName();

			if ( hubEx.getSeverity() == HubException.SEVERITY_CRITICAL )
			{
				String message = "";

				/**
				 * If the programName is unavailable, then just say unknown program
				 */
				if ( programName == null )
					programName = "Unknown Transaction:";

				message += programName + MSG_TERMINATED;
				printWriter.println( message );
			}

			printWriter.println( hubEx.getMessage() );
			printWriter.println( "***** Stack Trace *****" );
			hubEx.printStackTrace( printWriter );

			/**
			 * Get the exception scope and print it.
			 */
			Object scope = hubEx.getExceptionScope();
			if ( scope != null )
			{
				printWriter.println("--------------------------------------------------------" );
				printWriter.println( scope.toString() );
				printWriter.println("--------------------------------------------------------" );
			}

			/**
			 * If this contains a base exception, increase the indent level
			 * and call the decorateException again on that exception.
			 */
			if ( hubEx.getBaseException() != null )
			{
				indentLevel++;
				String decoratedBaseExceptionMessage = decorateException( hubEx.getBaseException(), indentLevel );
				printWriter.println( decoratedBaseExceptionMessage );
				indentLevel--;
			}
		}
		else
		if ( exception instanceof SQLException )
		{
			SQLException sqlEx = (SQLException) exception;
			String sqlState = sqlEx.getSQLState();
			int errorCode = sqlEx.getErrorCode();
			String message = sqlEx.getMessage();

			printWriter.println( "SQLException:" + message );

			if ( sqlState != null )
				printWriter.println( "SQL State:" + sqlState );
			printWriter.println( "Error Code:" + errorCode );

			sqlEx.printStackTrace( printWriter );

			/**
			 * SQL Exceptions could be nested too. This is when recursion happens
			 */
			if ( sqlEx.getNextException() != null )
			{
				indentLevel++;
				String decoratedBaseMessage = decorateException( sqlEx.getNextException(), indentLevel );
				printWriter.println( decoratedBaseMessage );
				indentLevel--;
			}
		}
		/*else
		if ( exception instanceof MQException )
		{
			MQException mqEx = (MQException) exception;
			printWriter.println( mqEx.toString() );
			printWriter.println( "Reason Code:" + mqMsgs.getMessageForReasonCode( mqEx.reasonCode ) );
			if ( mqEx.exceptionSource != null )
			{
				printWriter.println( mqEx.exceptionSource.toString() );
			}
			mqEx.printStackTrace( printWriter );
		}*/
		else
		{
			String className = exception.getClass().getName();
			printWriter.println( className + ":" + exception.toString() );
			printWriter.println( "***** Stack Trace *****" );
			exception.printStackTrace( printWriter );
		}

		decoratedMessage = stringWriter.toString();
		return decoratedMessage;
	}


	/**
	 * Performs a simple decoration (no StackTrace) of the given object. If the given
	 * object is not a derived class of Throwable, it returns the toString() version
	 * of that object. A simple decoration is useful when decorating an exception for
	 * paging.
	 *
	 * @param obj  The object to be decorated.
	 * @return Object  A String object containing the decorated object.
	 */
	public Object decorateSimple( Object obj )
	{
		if ( obj != null )
		{
			if ( HubException.class.isInstance( obj ) )
			{
				String decoratedMessage = "";
				HubException hubEx = (HubException) obj;

				String programName = hubEx.getProgramName();

				/**
				 * Note: The assumption is that if a severity is CRITICAL, then
				 * the process will be terminated. Therefore,we will put the terminated
				 * message.
				 */
				if ( hubEx.getSeverity() == HubException.SEVERITY_CRITICAL )
				{
					/**
					 * If the programName is unavailable, then just say unknown program
					 */
					if ( programName == null )
						programName = "Unknown Transaction:";

					decoratedMessage += programName + MSG_TERMINATED;
				}

				decoratedMessage += hubEx.getMessage();

				return decoratedMessage;
			}
			else
				return obj.toString();
		}

		throw new NullPointerException( "object to decorate was null" );
	}

	/**
	 * Returns the String version of this object
	 *
	 * @return String The string version of this object.
	 */
	public String toString()
	{
		String message = "ExceptionDecorator:";

		if ( useSimpleDecoration )
			message += " simpleDecoration";
		else
			message += " detailedDecoration";

		return message;
	}
}
