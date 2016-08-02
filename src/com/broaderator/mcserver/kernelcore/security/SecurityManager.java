package com.broaderator.mcserver.kernelcore.security;


import com.broaderator.mcserver.kernelcore.moduleBase.Function;
import com.broaderator.mcserver.kernelcore.moduleBase.Module;
import com.broaderator.mcserver.kernelcore.moduleBase.ModuleUtils;
import com.broaderator.mcserver.kernelcore.user.User;

public class SecurityManager extends Module {
    private final SecurityManager This = this;
    public final String name = "SecurityManager";
    public final Function<Boolean> init = new Function<Boolean>() {
        @Override
        public Boolean run(Object... args) {
            ModuleUtils.registerKernelCall(This, new Function<Boolean>() {
                @Override
                public Boolean run(Object... args) {
                    return null;
                }
            }, "_GetCommandPermission");
            ModuleUtils.registerKernelCall(This, new Function<Permission>() {
                public Permission run(Object... args) {
                    assert args[0] instanceof User;
                    return (Permission) ((User) args[0]).get("Permission");
                }
            }, "_GetUserPermission");
            ModuleUtils.registerKernelCall(This, new Function<Permission>() {
                @Override
                public Permission run(Object... args) {
                    assert args[0] instanceof User && args[1] instanceof Permission;
                }
            }, "_SetUserPermission");
            return true;
        }
    };
    public final Function<Boolean> exit = new Function<Boolean>() {
        @Override
        public Boolean run(Object... args) {
            return null;
        }
    };
    public final String[] dependencies = {};
}
