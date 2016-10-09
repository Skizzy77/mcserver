package com.broaderator.mcserver.core.logger;

public enum Format {
	_Reset(0),
	Bold(1),
	Italic(3),
	Underline(4),
	Inverse(7),
	Strikethrough(9),
	Black(30),
	Red(31),
	Green(32),
	Yellow(33),
	Blue(34),
	Purple(35),
	Cyan(36),
	White(37);

	public final int escapeCode;

	Format(int escapeCode) {
		this.escapeCode = escapeCode;
	}

	// use the escape code
	public String toSequence() {
		return String.format("\u001B[%dm", this.escapeCode);
	}
}
