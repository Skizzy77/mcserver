package com.broaderator.mcserver.kernelcore.security;

import com.broaderator.mcserver.kernelcore.$;

import java.util.LinkedList;

public class Permission {
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
}
