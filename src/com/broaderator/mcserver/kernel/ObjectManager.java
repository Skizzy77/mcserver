package com.broaderator.mcserver.kernel;

import com.broaderator.mcserver.kernelbase.Namespace;

public class ObjectManager {
    public static Namespace globalNamespace = new Namespace("Global");
    public static Namespace globalProperties = new Namespace("GlobalVolatile");
}
