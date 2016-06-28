package com.broaderator.mcserver.kernelcore.user;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.Namespace;
import com.broaderator.mcserver.kernelcore.Serializer;
import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.moduleBase.Module;
import com.broaderator.mcserver.kernelcore.moduleBase.ModuleUtils;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserManager extends Module {
    private final UserManager thisPointer = this;
    public final String name = "UserManager";
    public static List<User> users = new ArrayList<>();
    public final Function<Boolean> init = new Function<Boolean>() {
        public Boolean run(Object... unused) {
            if (!List.class.isInstance($.globalNS.get("Users"))) {
                Logger.error(thisPointer, "'Users' type invalid in global namespace, UserManager load failure");
                return false;
            }
            Logger.debug(thisPointer, "Loading users", $.DL_INFO);
            users.clear();
            for (HashMap<String, Object> userRep : (List<HashMap<String, Object>>) $.globalNS.get("Users")) {
                User u = (User) Serializer.deserialize(userRep, User.class);
                users.add(u);
                Logger.debug(thisPointer, "Loaded existing user: " + u.asPlayer().getName(), $.DL_DETAILS);
            }
            Logger.debug(thisPointer, "User load complete", $.DL_INFO);
            if (!ModuleUtils.registerKernelCall(thisPointer, new Function<User>() {
                public User run(Object... args) {
                    assert args[0] instanceof OfflinePlayer;
                    final OfflinePlayer op = (OfflinePlayer) args[0];
                    for (User u : users) {
                        if (u.asPlayer().equals(op)) return u;
                    }
                    // Create new user
                    Logger.fine(thisPointer, "Creating new user named " + op.getName());
                    User u = new User(op,
                            new Namespace(op.getName() + ".Namespace", true),
                            new Namespace(op.getName() + ".VolNamespace", false,
                                    new HashMap<>((HashMap<String, Object>) ModuleUtils.getOption(thisPointer, "VolatileNamespacePrototype"))));
                    users.add(u);
                    return u;
                }
            }, "GetUser")) return false;
            if (!ModuleUtils.registerKernelCall(thisPointer, new Function<Boolean>() {
                public Boolean run(Object... args) {
                    assert args[0] instanceof OfflinePlayer;
                    final OfflinePlayer op = (OfflinePlayer) args[0];
                    User pointer = null;
                    for (User u : users) {
                        if (u.asPlayer().equals(op)) {
                            pointer = u;
                            break;
                        }
                    }
                    if (pointer == null) {
                        Logger.warn(thisPointer, "Attempt to call RemoveUser with a nonexistent user");
                        return false;
                    }
                    return users.remove(pointer);
                }
            }, "RemoveUser")) ;
        }
    };
    public final Function<Boolean> exit = new Function<Boolean>() {

        @Override
        public Boolean run(Object... unused) {
            final List<HashMap<String, Object>> hm = (List<HashMap<String, Object>>) $.globalNS.get("Users");
            hm.clear();
            for (User u : users) {
                HashMap<String, Object> serd = (HashMap<String, Object>) Serializer.serialize(u);
                if (serd == null) {
                    Logger.error(thisPointer, "Unable to serialize user " + u.asPlayer().getName());
                    return false;
                }
                hm.add(serd);
                Logger.debug(thisPointer, "Exported user: " + u.asPlayer().getName(), $.DL_PROGRESS);
            }
            return true;
        }
    };
    public final String[] dependencies = {};
}
