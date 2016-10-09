package com.broaderator.mcserver.core.logger;

import com.broaderator.mcserver.core.GlobalConstants;
import com.broaderator.mcserver.core.Module;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

// dynamic formatting wanted
public class Logger implements Module {
	private static final String logFormat = "%s %s %s: %s";
	public static Logger self = new Logger();
	private static PrintWriter fileStream;

	public static void log(Object source, Level level, String msg) {

		String output = new Event(level, source.toString(), msg).getMessage();

		// for now, console and file.
		System.out.println(output);
		fileStream.println(output);
	}

	public void start() {
		try {
			fileStream = new PrintWriter(
				new BufferedWriter(
					new FileWriter(
						Paths.get(GlobalConstants.HomeFolder, "log.log").toFile(), true)));
		} catch (IOException exp) {
			exp.printStackTrace();
			throw new RuntimeException("IOException");
		}
	}

	public void stop() {
		fileStream.println(new String(new char[10]).replace('\0', '-'));
		fileStream.close();
	}

	public enum Level {
		Disaster(Format.Inverse, Format.Red),
		Error(Format.Red),
		Warning(Format.Yellow),
		Info(),
		Nice(Format.Green),
		Great(Format.Inverse, Format.Green);

		Collection<Format> formats;

		Level(Format... fmts) {
			formats = Arrays.asList(fmts);
		}

		// returns format sequences
		public String toLog() {
			StringBuilder sb = new StringBuilder();
			sb.append(Format._Reset.toSequence());
			formats.forEach(format -> sb.append(format.toSequence()));

			sb.append("(");
			sb.append(this.name().toUpperCase());
			sb.append(")");

			return sb.toString();
		}
	}

	// Every log line is a representation of an event
	public static class Event {
		protected DateTime time;
		protected Level level;
		protected String source;
		protected String msg;

		Event(Level level, String source, String msg) {
			this.time = DateTime.now();
			this.level = level;
			this.source = source;
			this.msg = msg;
		}

		public String getMessage() {
			return String.format(logFormat,
				time.toString(DateTimeFormat.forPattern("[HH:mm:ss dd.MM.yyyy]")),
				source,
				level.toLog(),
				msg) + Format._Reset;
		}
	}

}
