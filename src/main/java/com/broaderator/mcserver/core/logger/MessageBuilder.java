package com.broaderator.mcserver.core.logger;

import java.util.Vector;

// advanced message formatting, similar to piping
// cmb.in(Format.Blue).add(Format(optional), "Hi console reader").add(Format.Yellow, "WARN").out().toString()
public class MessageBuilder {
	private Vector<Format> fmtStack = new Vector<>();
	private String msg = "";

	// call this when a format is deleted from the stack; otherwise just add the format
	private void rerenderFormats() {
		msg += Format._Reset.toSequence();
		for (Format fmt : this.fmtStack) {
			msg += fmt.toSequence();
		}
	}

	// enter a color
	public MessageBuilder in(Format fmt) {
		if (!fmtStack.contains(fmt))
			fmtStack.add(fmt);
		msg += fmt.toSequence();
		return this;
	}

	// add a message to the buffer, maybe with formats
	public MessageBuilder add(String snippet, Format... fmts) {

		for (Format fmt : fmts) {
			msg += fmt.toSequence();
		}
		msg += snippet;
		rerenderFormats();
		return this;
	}

	public MessageBuilder out() {
		fmtStack.removeElementAt(fmtStack.size() - 1);
		rerenderFormats();
		return this;
	}

	@Override
	public String toString() {
		return msg + Format._Reset.toSequence();
	}
}
