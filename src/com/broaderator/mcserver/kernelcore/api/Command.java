package com.broaderator.mcserver.kernelcore.api;

import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.security.Permission;

import java.util.List;

// Dummy class for inheritance
public class Command {

    public List<String> label;
    public boolean requireRawInput;
    public boolean allowConsole;
    public Permission requiredPerm;
    public HelpArticle help;
    public Function<Boolean> onRun; /*(arguments: CommandSender/Player sender (depends on allowConsole), String[] args)*/

    public Command(List<String> label, boolean requireRawInput, boolean allowConsole, Permission requiredPerm, HelpArticle help, Function<Boolean> onRun) {
        this.label = label;
        this.requireRawInput = requireRawInput;
        this.allowConsole = allowConsole;
        this.requiredPerm = requiredPerm;
        this.help = help;
        this.onRun = onRun;
    }
}
