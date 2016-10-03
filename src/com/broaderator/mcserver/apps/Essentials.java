package com.broaderator.mcserver.apps;

import com.broaderator.mcserver.kernelcore.CallManagement;
import com.broaderator.mcserver.kernelcore.api.App;
import com.broaderator.mcserver.kernelcore.api.AppUtils;
import com.broaderator.mcserver.kernelcore.api.Command;
import com.broaderator.mcserver.kernelcore.api.HelpArticle;
import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.security.Permission;
import com.broaderator.mcserver.kernelcore.user.User;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Essentials extends App {
    private Essentials alias = this;
    public String name = "Essentials";
    public List<? extends Command> commands = Arrays.asList(
            new Command(Collections.singletonList("afk"),
                false,
                true, /* TODO: reformat Essentials */
                new Permission("Guest"),
                new HelpArticle("/afk", Collections.singletonList("/afk"), "To toggle your Away From Keyboard status"),
                new Function<Boolean>() {
                    @Override
                    public Boolean run(Object... args) {
                        if (args[0] instanceof ConsoleCommandSender) {
                            AppUtils.setCommandAttribute(alias, "afk", "ConsoleAFK", true);
                        } else if (args[0] instanceof Player) {
                            ((User) CallManagement.Call("GetUser", (Player) args[0]))._put("AFK", true);
                        }
                        //format
                        // todo: not done
                        return null;
                    }
                }
            ),
            new Command(Arrays.asList("tell", "whisper", "w", "m", "msg", "t"),
                    true,
                    true,
                    new Permission("Guest"),
                    new HelpArticle("/tell <selscript> <message..>",
                            Arrays.asList("/tell ExamplePlayer Hello World!", "/msg ExamplePlayer Sup", "/whisper Console Good morning", "/m %all Hello!"),
                            "To communicate in private"),
                    new Function<Boolean>() {
                        @Override
                        public Boolean run(Object... args) {
                            // TODO: 9/8/16
                            return null;
                        }
                    }
            ),
            new Command(
                    Collections.singletonList("warp"),
                    false,
                    false,
                    new Permission("Guest"),
                    new HelpArticle(
                            "/warp [set] <location>",
                            Arrays.asList("/warp World.Spawn", "/warp Event.Anniversary", "/warp Monument", "/warp set MyHouse"),
                            "To teleport yourself to a defined global location"),
                    new Function<Boolean>() {
                        @Override
                        public Boolean run(Object... args) {
                            // TODO: 9/8/16
                            return null;
                        }
                    }
            )  ,
            new Command(
                    Arrays.asList("locate", "l"),
                    false,
                    false,
                    new Permission("Privileged"),
                    new HelpArticle(
                            "/locate [set] <location>",
                            Arrays.asList("/locate home", "/locate EpicBuilding"),
                            "To teleport yourself to a defined personal location (available only to you)"
                    ),
                    new Function<Boolean>() {
                        @Override
                        public Boolean run(Object... args) {
                            // todo: not done
                            return null;
                        }
                    }
            ),
            new Command(
                    Collections.singletonList("home"),
                    false,
                    false,
                    new Permission("Privileged"),
                    new HelpArticle(
                            "/home [set]",
                            Arrays.asList("/home", "/home set"),
                            "To teleport to or set your home location"
                    ),
                    new Function<Boolean>() {
                        @Override
                        public Boolean run(Object... args) {
                            // todo: not done
                            return null;
                        }
                    }
            ),
            new Command(
                    Arrays.asList("gamemode", "gm"),
                    false,
                    false,
                    new Permission("Guest"),
                    new HelpArticle(
                            "/gm <creative/survival/adventure/spectator/c/s/a/p/1/0/2/3> [selScript]",
                            Arrays.asList("/gm c", "/gm 3", "/gm adventure", "/gm creative %all"),
                            "To set your gamemode to your specified choice"
                    ),
                    new Function<Boolean>() {
                        @Override
                        public Boolean run(Object... args) {
                            return null;
                        }
                    }
            ),
            new Command(
                    Collections.singletonList("hat"),
                    false,
                    false,
                    new Permission("Guest"),
                    new HelpArticle(
                            "/hat",
                            Collections.singletonList("/hat"),
                            "To set the current item in your inventory as your hat"
                    ),
                    new Function<Boolean>() {
                        @Override
                        public Boolean run(Object... args) {
                            return null;
                        }
                    }
            ),
            new Command(
                    Collections.singletonList("macro"),
                    false,
                    true,
                    new Permission("Developer"),
                    new HelpArticle(
                            "/macro <name> <command...>",
                            Arrays.asList("/macro WeatherClear weather clear", "/macro SwearToEveryone tell %all You *****es!"),
                            "To define macros for convenience"
                    ),
                    new Function<Boolean>() {
                        @Override
                        public Boolean run(Object... args) {
                            return null;
                        }
                    }
            )
    );
}
