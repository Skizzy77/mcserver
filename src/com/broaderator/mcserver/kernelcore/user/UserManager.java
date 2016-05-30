package com.broaderator.mcserver.kernelcore.user;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.Logger;
import com.broaderator.mcserver.kernelcore.yaml.YAMLManager;
import com.broaderator.mcserver.kernelcore.Namespace;
import com.broaderator.mcserver.kernelcore.KMI;
import com.broaderator.mcserver.kernelcore.ModuleAgent;
import com.broaderator.mcserver.kernelcore.ModuleResources;
import com.broaderator.mcserver.kernelcore.event.Event;
import org.bukkit.OfflinePlayer;

import java.util.*;

public class UserManager {
    private static final HashMap<String, Object> nsHome = (HashMap<String, Object>) $.globalNS.get("Manager.User");
    private static List<User> users = new ArrayList<>();

    public static ModuleAgent Ma = new ModuleAgent() {

        // Requirement: Load YAMLManager before this.
        @Override
        public int init() {
            if (!List.class.isInstance($.globalNS.get("Users"))) {
                Logger.error(this, "'Users' type invalid in global namespace, UserManager load failure");
                return 1;
            }
            Logger.debug(this, "Loading users", $.DL_INFO);
            users.clear();
            for (HashMap<String, Object> userRep : (List<HashMap<String, Object>>) $.globalNS.get("Users")) {
                User u = (User) YAMLManager.fromRepresentation(userRep, User.class);
                users.add(u);
                Logger.debug(this, "Loaded existing user: " + u.asPlayer().getName(), $.DL_DETAILS);
            }
            Logger.debug(this, "User load complete", $.DL_INFO);
            return 0;
        }

        @Override
        public int exit() {
            List<HashMap<String, Object>> hm = (List<HashMap<String, Object>>) $.globalNS.get("Users");
            Logger.debug(this, "Exporting users", $.DL_INFO);
            hm.clear();
            for (User u : users) {
                hm.add((HashMap<String, Object>) YAMLManager.toRepresentation(u));
                Logger.debug(this, "Exported user: " + u.asPlayer().getName(), $.DL_DETAILS);
            }
            Logger.debug(this, "User export complete", $.DL_INFO);
            return 0;
        }


        @Override
        public String getComponentName() {
            return "UserManager";
        }

        @Override
        public List<ModuleAgent> getDependencies() {
            return Collections.singletonList(YAMLManager.Ma);
        }

        @Override
        public boolean useVariables() {
            return true;
        }

        @Override
        public boolean useEvents() {
            return true;
        }

        @Override
        public HashMap<String, Event> getEvents() {
            return new HashMap<String, Event>() {{
                put("AddUser", new Event());
                put("RemoveUser", new Event());
            }};
        }

    };
    private static ModuleResources Resources = KMI.registerModule(Ma);

    public static User getUser(OfflinePlayer op) {
        for (User u : users) {
            if (u.asPlayer().getUniqueId().equals(op.getUniqueId())) {
                return u;
            }
        }
        // Create new user
        Logger.info(Ma, "Creating new user: " + op.getName());
        User u = new User(op,
                new Namespace(op.getName() + ".Attributes", true),
                new Namespace(op.getName() + ".Namespace", false,
                        (HashMap<String, Object>) nsHome.get("NSVolatilePrototype")));
        users.add(u);
        return u;
    }
}
