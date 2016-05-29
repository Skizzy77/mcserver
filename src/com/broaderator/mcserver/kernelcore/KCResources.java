package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernelbase.KernelObject;

public class KCResources {
    static KernelObject Object = new KernelObject() {
        @Override
        public String getComponentName() {
            return "KernelCore";
        }
    };
}
