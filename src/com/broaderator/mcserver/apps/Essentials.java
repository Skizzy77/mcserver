package com.broaderator.mcserver.apps;

import com.broaderator.mcserver.kernelcore.api.App;
import com.broaderator.mcserver.kernelcore.api.Command;
import com.broaderator.mcserver.kernelcore.api.HelpArticle;
import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.security.Permission;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Essentials extends App {
    public List<? extends Command> commands = Arrays.asList(
            new Command() {
                public List<String> label = Collections.singletonList("afk");
                public boolean requireRawInput = false;
                public boolean allowConsole = true;
                public Permission requiredPerm = new Permission("Guest");
                public HelpArticle help = new HelpArticle("/afk", Collections.singletonList("/afk"), "To toggle your Away From Keyboard status");
                public Function<Boolean> onRun = new Function<Boolean>() {
                    @Override
                    public Boolean run(Object... args) {
                        if (args[0] instanceof ConsoleCommandSender) {

                        }
                    }
                }
            },
            new Command() {
                public List<String> label = Arrays.asList("tell", "whisper", "w", "m", "msg", "t");
                public boolean requireRawInput = true;
                public boolean allowConsole = true;
                public Permission requiredPerm = new Permission("Guest");
                public HelpArticle help = new HelpArticle("/tell <selscript> <message..>",
                        Arrays.asList("/tell ExamplePlayer Hello World!", "/msg ExamplePlayer Sup", "/whisper Console Good morning", "/m %all Hello!"),
                        "To communicate in private");
            },
            new Command() {
                public List<String> label = Collections.singletonList("warp");
                public boolean requireRawInput = false;
                public boolean allowConsole = false;
                public Permission requiredPerm = new Permission("Guest");
                public HelpArticle help = new HelpArticle(
                        "/warp [set] <location>",
                        Arrays.asList("/warp World.Spawn", "/warp Event.Anniversary", "/warp Monument", "/warp set MyHouse"),
                        "To teleport yourself to a defined global location");
            },
            new Command() {
                public List<String> label = Arrays.asList("locate", "l");
                public boolean requireRawInput = false;
                public boolean allowConsole = false;
                public Permission requiredPerm = new Permission("Privileged");
                public HelpArticle help = new HelpArticle(
                        "/locate [set] <location>",
                        Arrays.asList("/locate home", "/locate EpicBuilding"),
                        "To teleport yourself to a defined personal location (available only to you)"
                );
            },
            new Command() {
                public List<String> label = Collections.singletonList("home");
                public boolean requireRawInput = false;
                public boolean allowConsole = false;
                public Permission requiredPerm = new Permission("Privileged");
                public HelpArticle help = new HelpArticle(
                        "/home [set]",
                        Arrays.asList("/home", "/home set"),
                        "To teleport to or set your home location"
                );
            },
            new Command() {
                public List<String> label = Arrays.asList("gamemode", "gm");
                public boolean requireRawInput = false;
                public boolean allowConsole = false;
                public Permission requiredPerm = new Permission("Guest");
                public HelpArticle help = new HelpArticle(
                        "/gm <creative/survival/adventure/spectator/c/s/a/p/1/0/2/3> [selScript]",
                        Arrays.asList("/gm c", "/gm 3", "/gm adventure", "/gm creative %all"),
                        "To set your gamemode to your specified choice"
                );
            },
            new Command() {
                public List<String> label = Collections.singletonList("hat");
                public boolean requireRawInput = false;
                public boolean allowConsole = false;
                public Permission requiredPerm = new Permission("Guest");
                public HelpArticle help = new HelpArticle(
                        "/hat",
                        Collections.singletonList("/hat"),
                        "To set the current item in your inventory as your hat"
                );
            },
            new Command() {
                public List<String> label = Collections.singletonList("macro");
                public boolean requireRawInput = false;
                public boolean allowConsole = true;
                public Permission requiredPerm = new Permission("Developer");
                public HelpArticle help = new HelpArticle(
                        "/macro <name> <command...>",
                        Arrays.asList("/macro WeatherClear weather clear", "/macro SwearToEveryone tell %all You *****es!"),
                        "To define macros for convenience"
                );
            }
    );
}
