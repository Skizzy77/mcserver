package com.broaderator.mcserver.core.modules.bukkit;

import com.broaderator.mcserver.core.$;
import com.broaderator.mcserver.core.Module;
import com.broaderator.mcserver.core.modules.user.User;
import com.broaderator.mcserver.core.modules.user.util.Rank;

import java.util.Collection;
import java.util.HashMap;

// uses multiple color schemes, shortened to "cs" for most cases
public class Chat implements Module {

	public static void writeMsg(User user, String msg, Collection<User> targets) {

		ChatStyle activeCs = ChatStyle.getCsByName($.savedNS.getProperty("Chat.ActiveChatStyle"));

		HashMap<String, String> chatKeyval = new HashMap<>();
		chatKeyval.put("rank", Rank.getRank(user));
		chatKeyval.put("displayName", user.getProperty("displayName"));
		chatKeyval.put("msg", msg);
		String chatOutput = activeCs.chatFormat;

		// if option "Chat.ManualColoring", defer the color formatting until later
		// repetition may need optimization
		if ($.savedNS.getProperty("Chat.ManualColoring").equals("true")) {
			chatOutput = $.replace(chatOutput, chatKeyval);
			chatOutput = Message.format(chatOutput);
		} else {
			chatOutput = Message.format(chatOutput);
			chatOutput = $.replace(chatOutput, chatKeyval);
		}

		for (User receip : targets) {
			if (receip.online()) {
				receip.getOnline().sendMessage(chatOutput);
			}
		}

	}

	public static void writeCmd(Palette colors, String prefix, String msg, Collection<User> targets) {

		ChatStyle activeCs = ChatStyle.getCsByName($.savedNS.getProperty("Chat.ActiveChatStyle"));

		HashMap<String, String> cmdKeyval = new HashMap<>();
		cmdKeyval.put("prefixColor", colors.prefixColor);
		cmdKeyval.put("prefix", prefix);
		cmdKeyval.put("msgColor", colors.msgColor);
		cmdKeyval.put("msg", msg);
		String cmdOutput = activeCs.commandFormat;

		cmdOutput = $.replace(cmdOutput, cmdKeyval);
		cmdOutput = Message.format(cmdOutput);

		for (User target : targets) {
			if (target.online()) {
				target.getOnline().sendMessage(cmdOutput);
			}
		}
	}

	@Override
	public void start() {
		// nothing yet
	}

	@Override
	public void stop() {
		// nothing yet
	}

	public static class Palette {
		// expandable
		public static final Palette Mineplex = new Palette("blue", "gray");

		public final String prefixColor;
		public final String msgColor;

		private Palette(String prefixColor, String msgColor) {
			this.prefixColor = Message.ColorCodes.get(prefixColor);
			this.msgColor = Message.ColorCodes.get(msgColor);
		}
	}
}
