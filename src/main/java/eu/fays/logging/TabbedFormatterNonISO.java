package eu.fays.logging;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * The tabbed formatter in an non ISO 8601 fashion Format the record with the following attribute, each separated by a tab character:
 * <ol>
 * <li>Sequence number (6 digits with leading zero's).
 * <li>Date and Time (day/month/year hours:minutes:seconds.milliseconds).
 * <li>Log level.
 * <li>Source class name.
 * <li>Source method name.
 * <li>Message.
 * </ol>
 * @see java.util.logging.Level
 */
@SuppressWarnings("nls")
public class TabbedFormatterNonISO extends TabbedFormatterISO8601 {

	/**
	 * Constructor
	 */
	public TabbedFormatterNonISO() {
		super(new DecimalFormat("000000"), DATE_FORMAT);
	}

	/** Date format */
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
	{
		DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
}