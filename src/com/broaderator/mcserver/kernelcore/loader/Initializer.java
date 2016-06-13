package com.broaderator.mcserver.kernelcore.loader;

import com.broaderator.mcserver.kernelcore.$;

public class Initializer {

    public static void initializeKernel() {
        $.globalVolNS.put("KernelInitialized", false);
    }
}
