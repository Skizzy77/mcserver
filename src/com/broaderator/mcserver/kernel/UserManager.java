package com.broaderator.mcserver.kernel;

import com.broaderator.mcserver.kernel.yaml.YAMLManager;
import com.broaderator.mcserver.kernelbase.Namespace;
import com.broaderator.mcserver.kernelcore.KMI;
import com.broaderator.mcserver.kernelcore.ModuleAgent;
import com.broaderator.mcserver.kernelcore.ModuleResources;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
            return Collections.emptyList();
        }

        @Override
        public boolean useVariables() {
            return true;
        }

        @Override
        public boolean useEvents() {
            return true;
        }

    };
    private static ModuleResources Resources = KMI.registerModule()

    public static User getUser(OfflinePlayer op) {
        for (User u : users) {
            if (u.asPlayer().getUniqueId().equals(op.getUniqueId())) {
                return u;
            }
        }
        // Create new user
        Logger.info(userMan, "Creating new user: " + op.getName());
        User u = new User(op,
                new Namespace(op.getName() + ".NS", true),
                new Namespace(op.getName() + ".NSVolatile", false,
                        (HashMap<String, Object>) nsHome.get("NSVolatilePrototype")));
        users.add(u);
        return u;
    }
}
