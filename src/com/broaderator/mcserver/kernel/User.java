package com.broaderator.mcserver.kernel;

import com.broaderator.mcserver.kernelbase.Namespace;
import org.bukkit.OfflinePlayer;

public class User {
    private OfflinePlayer op;
    private Namespace ns;

    User(OfflinePlayer op, Namespace ns) {
        this.op = op;
        this.ns = ns;
    }

    public Object get(String str) {
        return ns.get(str);
    }

    public OfflinePlayer asPlayer() {
        return op;
    }

    public boolean put(String dir, Object val) {
        return ns.put(dir, val);
    }

    public Namespace getNamespace() {
        return ns;
    }
}
