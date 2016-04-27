package logging;

import java.io.IOException;
import java.io.Writer;

public class LoggerWriter extends Writer {
	
	public LoggerWriter(final StringBuilder builder, final String header) {
		_builder = builder;
		_header = header;
		
	}
	@Override
	public void close() throws IOException {
		// Do nothing
	}

	@Override
	public void flush() throws IOException {
		// Do nothing
	}

	@Override
	public void write(final char[] cbuf, final int off, final int len) throws IOException {
		if (len > 2) {
			_builder.append(_header);
			_builder.append('\t');
		}
		_builder.append(cbuf, off, len);
	}

	final StringBuilder _builder; 
	final String _header;
}
