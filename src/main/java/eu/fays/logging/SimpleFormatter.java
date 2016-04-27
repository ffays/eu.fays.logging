package eu.fays.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * A formatter to output the message only.
 */
public class SimpleFormatter extends Formatter {

	/**
	 * Outputs the message only.
	 */
	@Override
	public String format(final LogRecord record) {
		return record.getMessage() + System.lineSeparator(); 
	}

}
