package com.broaderator.mcserver.kernel;

import com.broaderator.mcserver.kernelbase.Namespace;

/**
 * Shorthand class for global common items, such as <code>GlobalNamespace</code>.
 */
public class $ {
    public static final short DL_MUSTDISPLAY = 0;
    public static final short DL_INFO = 1;
    public static final short DL_PROGRESS = 2;
    public static final short DL_DETAILS = 3;
    public static Namespace globalNS = new Namespace("Global", true);
    public static Namespace globalVolNS = new Namespace("GlobalVolatile", false);
}
