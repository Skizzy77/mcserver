package com.broaderator.mcserver.kernel;

import com.broaderator.mcserver.kernelbase.Namespace;

/**
 * Shorthand class for global common items, such as <code>GlobalNamespace</code>.
 */
public class $ {
    public static Namespace globalNS = new Namespace("Global");
    public static Namespace globalVolNS = new Namespace("GlobalVolatile");
}
