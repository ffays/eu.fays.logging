package eu.fays.logging;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Tabbed formatter.<br>
 * Format the record with the following attribute, each separated by a tab character:
 * <ol>
 * <li>Sequence number (6 digits with leading zero's).
 * <li>Time stamp (In <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601</a> expanded format).
 * <li>Log level.
 * <li>Source class name.
 * <li>Source method name.
 * <li>Message.
 * </ol>
 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
 * @see java.util.logging.Level
 */
@SuppressWarnings("nls")
public class TabbedFormatterISO8601 extends Formatter {

	/**
	 * Constructor
	 */
	public TabbedFormatterISO8601() {
		this(new DecimalFormat("000000"), DATE_FORMAT);
	}

	protected TabbedFormatterISO8601(final DecimalFormat sequenceFormat, final SimpleDateFormat timeStampFormat) {
		//
		assert sequenceFormat != null;
		assert timeStampFormat != null;
		//

		_sequenceFormat = sequenceFormat;
		_timeStampFormat = timeStampFormat;
		//
	}

	/**
	 * Format the record with the following attribute, each separated by a tab character:
	 * <ol>
	 * <li>Sequence number (6 digits with leading zero's).
	 * <li>Time (hours,minutes,seconds,milliseconds).
	 * <li>Log level.
	 * <li>Source class name.
	 * <li>Source method name.
	 * <li>Message.
	 * </ol>
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 * @see java.util.logging.Level
	 */
	@Override
	public String format(final LogRecord record) {
		//
		assert record != null;
		//

		final StringBuilder result = new StringBuilder();
		
		// header
		final String header = header(record);
		result.append(header);

		// message
		result.append(record.getMessage());
		result.append(System.lineSeparator());

		final Throwable thrown = record.getThrown();
		if (thrown != null) {
			try (final Writer w = new LoggerWriter(result, header); final PrintWriter writer = new PrintWriter(w)) {
				thrown.printStackTrace(writer);
			} catch (IOException e) {
				// Do nothing
			} 
		}
		return result.toString();
	}

	/**
	 * Format the header of the log line accordingly the class description. The header contains all the fields but the message.
	 * @param record the log record
	 * @return the header.
	 */
	protected String header(final LogRecord record) {
		//
		assert record != null;
		//

		final StringBuilder result = new StringBuilder();

		// sequence
		result.append(_sequenceFormat.format(record.getSequenceNumber()));
		result.append('\t');

		// time stamp
		result.append(_timeStampFormat.format(new Date(record.getMillis())));
		result.append('\t');

		// log level
		result.append(record.getLevel().getName());
		result.append('\t');

		// origin
		result.append(record.getSourceClassName());
		result.append('\t');
		result.append(record.getSourceMethodName());
		result.append('\t');

		return result.toString();
	}

	/** Date format used for the time stamp. */
	private final DateFormat _timeStampFormat;

	/** Decimal format used for the sequence */
	private final DecimalFormat _sequenceFormat;
	
	/** Date format */
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	{
		DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
}