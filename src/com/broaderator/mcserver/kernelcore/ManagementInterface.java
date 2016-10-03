package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.moduleBase.Module;
import com.broaderator.mcserver.kernelcore.moduleBase.ModuleUtils;
import com.broaderator.mcserver.kernelcore.user.UserManager;

import java.util.HashMap;

public class ManagementInterface extends Module { // special module that initializes manually after all other modules
    public final String name = "ManagementInterface";
    private final HashMap<String, Function<Object>> modules = new HashMap<String, Function<Object>>() {
        {
        /*put("GetModules", new Function<Object>() {
            @Override
            public Object run(Object... args) {
                return ((HashMap<String, Object>) $.globalVolNS.get("Modules")).keySet();
            }
        });
        put("GetUsers", new Function<Object>() {

            @Override
            public Object run(Object... args) {
                return UserManager.users;
            }
        });
        put("GetCalls", new Function<Object>() {

            @Override
            public Object run(Object... args) {
                return ((HashMap<String, Object>) $.globalVolNS.get("Calls")).keySet();
            }
        });
        put("GetNamespace", new Function<Object>() {

            @Override
            public Object run(Object... args) {
                if (args.length < 1) {
                    return "Argument required";
                }
                if (args[0].equals("Normal")) {
                    return $.globalNS.storage;
                } else if (args[0].equals("Volatile")) {
                    return $.globalVolNS.storage;
                } else {
                    return "Argument invalid: expected 'Normal' or 'Volatile'";
                }
            }
        });
        put("GetCommands", new Function<Object>() {

            @Override
            public Object run(Object... args) {
                // tie into CommandManager
            }
        });
        put("GetApps", new Function<Object>() {
            @Override
            public Object run(Object... args) {
                // tie into AppManager
            }
        });
        put("GetGames", new Function<Object>() {
            @Override
            public Object run(Object... args) {
                // tie into GameManager
            }
        });
        put("GetLogs", new Function<Object>() {
            @Override
            public Object run(Object... args) {
                return Logger.logOfLog;
            }
        });
        put("GetEvents", new Function<Object>() {
            @Override
            public Object run(Object... args) {
                return $.globalVolNS.get("RecentEvents");
            }
        });
        put("SetNamespaceItem", new Function<Object>() {
            @Override
            public Object run(Object... args) {
                if (args.length < 4) {
                    return "Arguments Required: SetNamespaceItem <Normal/Volatile> <Namespace directory> <type> <value>";
                }
                if (args[0].equals("Normal")) {

                } else if (args[0].equals("Volatile")) {

                } else {
                    return "Invalid argument \"" + args[0] + "\"";
                }
            }
        });
        put("BlockCall", new Function<Object>() {
            @Override
            public Object run(Object... args) {
                if (args.length < 1 || !String.class.isInstance(args[0]))
                    return "Arguments Required: BlockCall <Call Name>";
                if (!CallManagement.DisabledCalls.contains(args[0]))
                    CallManagement.DisabledCalls.add((String) args[0]);
                return CallManagement.DisabledCalls.contains(args[0]);
            }
        });
        put("BlockCommand", new Function<Object>() {
            @Override
            public Object run(Object... args) {
                if (args.length < 1 || !String.class.isInstance(args[0]))
                    return "Argument Required: BlockCommand <Command Name>";
                // interact with CommandManager
            }
        });
        put("SetDebugLevel", new Function<Object>() {
            @Override
            public Object run(Object... args) {
            }
        });
        put("SetUserPermission", new Function<Object>() {
            @Override
            public Object run(Object... args) {
            }
        });
        put("SetUserNamespaceItem", new Function<Object>() {
            @Override
            public Object run(Object... args) {
            }
        });
        put("Sandbox-Remove", new Function<Object>() {
            @Override
            public Object run(Object... args) {
            }
        });
        put("Sandbox-Insert", new Function<Object>() {
            @Override
            public Object run(Object... args) {
            }
        });
        put("Sandbox-List", new Function<Object>() {
            @Override
            public Object run(Object... args) {
            }
        });
    }};
    public final Function<Boolean> init = new Function<Boolean>() {
        @Override
        public Boolean run(Object... args) {
            ModuleUtils.registerKernelCall(This, new Function<String>() {
                @Override
                public String run(Object... args) {
                    if (args.length < 1)
                        return "Error: Missing command";
                    *//*
                    Functions of the Kernel Management Interface:
                    - Display stats
                      - Modules loaded (GetModules)
                      - Users loaded (GetUsers)
                      - Kernel calls (GetCalls)
                      - Namespace hierarchy (GetNamespace)
                      - Commands available (GetCommands)
                      - Apps loaded (GetApps)
                      - Games loaded (GetGames)
                      - Recent logs (GetLogs)
                      - Fired events (GetEvents)
                    - Change preferences
                      - Modify namespace item (SetNamespaceItem)
                      - Block kernel calls (BlockCall)
                      - Block commands (BlockCommand)
                      - Set DebugLevel (SetDebugLevel)
                      - Set User SecurityLevel (SetUserPermission)
                      - Modify User namespace item (ranks, flags, etc.) (SetUserNamespaceItem)
                      - Sandbox operations
                        - Remove from sandbox (Sandbox-Remove)
                        - Insert into sandbox (Sandbox-Insert)
                        - List items from sandbox (Sandbox-List)
                    - Experimental features
                      - tbd
                     *//*

                }
            }, "SubmitManagementCommand");
        }*/
        }
    };
}
