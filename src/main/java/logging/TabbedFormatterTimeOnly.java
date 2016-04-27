package logging;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * A tabbed formatter, short form.<br>
 * Format the record with the following attribute, each separated by a tab character:
 * <ol>
 * <li>Sequence number (4 digits with leading zero's).
 * <li>Time (hours:minutes:seconds.milliseconds).
 * <li>Log level.
 * <li>Source class name.
 * <li>Source method name.
 * <li>Message.
 * </ol>
 * @see java.util.logging.Level
 */
@SuppressWarnings("nls")
public class TabbedFormatterTimeOnly extends TabbedFormatterISO8601 {

	/**
	 * Constructor
	 */
	public TabbedFormatterTimeOnly() {
		super(new DecimalFormat("0000"), DATE_FORMAT);
	}

	/** Time format */
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
	{
		DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
}
