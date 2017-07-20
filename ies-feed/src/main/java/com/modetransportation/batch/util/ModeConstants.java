package com.modetransportation.batch.util;

public final class ModeConstants {

	public final static String LINE_BREAK = "\n";

	/**
	 * BOOLEAN VALUES
	 * 
	 */
	public final static String TRUE = "true";
	public final static String FALSE = "false";
	public final static String YES = "Y";
	public final static String NO = "N";

	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public final static String TRACE = "TRACE: ";
	public final static String LOG = "LOG: ";
	public final static String SYSTEM_ERROR = "System.err";
	public final static String DEFAULT_ENVIRONMENT = "Unknown Env";

	// Static variables
	public static final int SEVERITY_CRITICAL = 0;
	public static final int SEVERITY_HIGH = 1;
	public static final int SEVERITY_MEDIUM = 2;
	public static final int SEVERITY_LOW = 3;
	public static final int SEVERITY_WARNING = 4;

	public static final String[] SEVERITIES = { "Critical", "High", "Medium",
			"Low", "Warning" };

	/**
	 * ALARM MANAGER
	 * 
	 * alarm manager normally uses these, any application trying to use alarm
	 * manager need to ensure appropriate property key/value has been provided
	 */
	public final static String MSG_PAGE_NOT_RAISED = "No Page Alarm raised due to lack of Distribution List";
	public final static String MSG_EMAIL_NOT_RAISED = "Mail Alarm not raised due to lack of Distribution List";

	public final static String DEFAULT_PAGE_SUBJECT = "Comtrak MQ Java Exception: ";
	public final static String ALARM_EMAIL_TO_LIST = "alarm.email.to.list";
	public final static String ALARM_PAGE_TO_LIST = "alarm.page.to.list";
	public final static String ALARM_EMAIL_FROM = "alarm.email.from";
	public final static String DEFAULT_FROM_ADDRESS = "WebSupport@hubgroup.com";

	/**
	 * LOGGER
	 * 
	 * logger normally uses these, any application trying to use logger need to
	 * ensure appropriate property key/value has been provided
	 */
	public final static String LOG_FILENAME = "log.filename";
	public final static String LOG_USE_DATEBASED = "log.use.datebased";
	public final static String LOG_DATEFORMAT = "log.dateformat";
	public final static String LOG_TRACE_ENABLED = "log.trace.enabled";

	/**
	 * EMAIL MANAGER
	 * 
	 * email manager normally uses these, any application trying to use email
	 * manager need to ensure appropriate property key/value has been provided
	 */
	public final static String DEFAULT_EMAIL_SUBJECT = "Comtrak MQ Notification";
	public final static String DEFAULT_EMAIL_FROM = "webgroupsupport@hubgroup.com";
	public final static String DEFAULT_EMAIL_TO = "webgrouptest@hubgroup.com";
	public final static String DEFAULT_EMAIL_MESSAGE = "Message missing, contact sender.";
	public final static String DEFAULT_MAIL_HOST = "mailhost";
	public final static String DEFAULT_MAIL_TRANSPORT_PROTOCOL = "smtp";

	public final static String MAIL_HOST = "mail.host";
	public final static String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	public final static String EMAIL_TO_LIST = "email.to.list";
	public final static String EMAIL_CC_LIST = "email.cc.list";
	public final static String EMAIL_BCC_LIST = "email.bcc.list";
	public final static String EMAIL_FROM = "email.from";
	public final static String EMAIL_SUBJECT = "email.subject";
	public final static String EMAIL_MESSAGE = "email.message";
	public final static String EMAIL_ATTACHMENTS = "email.attachments";
	public final static String EMAIL_FILE_DATASOURCE = "email.file.datasource";

	public final static String ERROR_EMAIL_FROM = "error.email.from";
	public final static String ERROR_EMAIL_TO_LIST = "error.email.to.list";
	public final static String ERROR_EMAIL_SUBJECT = "error.email.subject";

	public static final String MSG_CRITICAL_ERROR = "A Critical Application Error occurred.";
	public static final String EXCEPTION_SEVERITY_CRITICAL_SENDPAGE = "exception.severity.critical.sendpage";
	public static final String EXCEPTION_SEVERITY_CRITICAL_SENDMAIL = "exception.severity.critical.sendmail";
	public static final String EXCEPTION_SEVERITY_HIGH_SENDPAGE = "exception.severity.high.sendpage";
	public static final String EXCEPTION_SEVERITY_HIGH_SENDMAIL = "exception.severity.high.sendmail";
	public static final String EXCEPTION_SEVERITY_MEDIUM_SENDPAGE = "exception.severity.medium.sendpage";
	public static final String EXCEPTION_SEVERITY_MEDIUM_SENDMAIL = "exception.severity.medium.sendmail";
	public static final String EXCEPTION_SEVERITY_LOW_SENDPAGE = "exception.severity.low.sendpage";
	public static final String EXCEPTION_SEVERITY_LOW_SENDMAIL = "exception.severity.low.sendmail";
	public static final String EXCEPTION_SEVERITY_WARNING_SENDPAGE = "exception.severity.warning.sendpage";
	public static final String EXCEPTION_SEVERITY_WARNING_SENDMAIL = "exception.severity.warning.sendmail";

}
