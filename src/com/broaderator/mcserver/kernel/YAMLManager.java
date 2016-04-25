package com.broaderator.mcserver.kernel;

import com.broaderator.mcserver.kernelbase.KernelObject;

import java.util.concurrent.ConcurrentLinkedQueue;

public class YAMLManager implements KernelObject {
    public String getComponentName() {
        return "YAMLManager";
    }

    static class YAMLRequest {

    }

    private static ConcurrentLinkedQueue<YAMLRequest> queue = new ConcurrentLinkedQueue<>();

}
