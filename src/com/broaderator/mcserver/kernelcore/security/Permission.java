package com.broaderator.mcserver.kernelcore.security;

import com.broaderator.mcserver.kernelcore.$;
import com.broaderator.mcserver.kernelcore.Serializable;
import com.broaderator.mcserver.kernelcore._;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.HashMap;
import java.util.LinkedList;

public class Permission implements Serializable {
    private int index;

    public Permission(String name) {
        this.index = ((LinkedList<String>) $.globalNS.get("Permissions")).indexOf(name);
    }

    public boolean isAbove(Permission perm) {
        return index > perm.index;
    }

    public boolean isBelow(Permission perm) {
        return index < perm.index;
    }

    @Override
    public boolean equals(Object p) {
        return p instanceof Permission && index == ((Permission) p).index;
    }

    @Override
    public String toString() {
        return StringFormat.f("Permission#{0}:{1}", this.hashCode(), this.index);
    }

    @Override
    public HashMap<String, Object> represent() {
        return _.createHashmap("type", "Permission", "index", index);
    }
}
