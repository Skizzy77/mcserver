package com.broaderator.mcserver.kernelcore.bukkit;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.moduleBase.Module;
import com.broaderator.mcserver.kernelcore.moduleBase.ModuleUtils;

import java.util.HashMap;
import java.util.List;

// Purpose: Provide universal chat formatting and chat events
// Uses Resources.ChatFormats (List<String>) as format
public class ChatManager extends Module {
    private ChatManager This = this;
    public String name = "ChatManager";
    public Function<Boolean> init = new Function<Boolean>() {
        @Override
        public Boolean run(Object... unused) {
            if (!ModuleUtils.registerEvents(This, "ChatMessagePreprocess", "ChatMessageSend")) return false;
            if (!ModuleUtils.registerKernelCall(This, new Function<HashMap<String, Object>>() {
                public HashMap<String, Object> run(Object... args) {
                    // args: String profileName
                    assert args.length == 1;
                    for (HashMap<String, Object> profile : (List<HashMap<String, Object>>) ModuleUtils.getOption(This, "StyleProfiles")) {
                        if (profile.get("name") == args[0]) {
                            Logger.debug(This, "_GetStyleProfile: queried profile found: " + args[0], $.DL_DETAILS);
                            return profile;
                        }
                    }
                    Logger.warn(This, "_GetStyleProfile: unknown style profile queried: " + args[0]);
                }
            }, "_GetStyleProfile")) return false;
            if (!ModuleUtils.registerKernelCall(This, new Function<String>() {
                public String run(Object... args) {
                    return null;
                }
            }, "_ChatPostprocessReplace")) return false;
            return ModuleUtils.registerKernelCall(This, new Function<Boolean>() {
                public Boolean run(Object... args) {
                    // args: CommandSender target, String prefix, String message, optional int type
                    Integer type = args.length == 4 ? (Integer) args[3] : 0;
                    // TODO
                }
            }, "Chat");
        }
    };
    public Function<Boolean> exit = new Function<Boolean>() {
        @Override
        public Boolean run(Object... unused) {
            return true;
        }
    };

}
