package com.broaderator.mcserver.kernelcore.user;

import com.broaderator.mcserver.kernelcore.KernelObject;
import com.broaderator.mcserver.kernelcore.Namespace;
import org.bukkit.OfflinePlayer;

public class User implements KernelObject{
    private OfflinePlayer op;
    private Namespace ns;
    private Namespace nsVolatile;

    public User(OfflinePlayer op, Namespace ns, Namespace nsVolatile) {
        this.op = op;
        this.ns = ns;
        this.nsVolatile = nsVolatile;
    }

    public Object get(String str) {
        return ns.get(str);
    }

    public Object _get(String str) {
        return nsVolatile.get(str);
    }

    public OfflinePlayer asPlayer() {
        return op;
    }

    public boolean put(String dir, Object val) {
        return ns.put(dir, val);
    }

    public boolean _put(String dir, Object val) {
        return nsVolatile.put(dir, val);
    }

    public Namespace getNamespace() {
        return ns;
    }

    public Namespace getVolatileNS() {
        return nsVolatile;
    }

    @Override
    public String getComponentName() {
        return op.getName();
    }
}
