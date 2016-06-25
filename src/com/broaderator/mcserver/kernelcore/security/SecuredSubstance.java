package com.broaderator.mcserver.kernelcore.security;

import com.broaderator.mcserver.kernelcore.KernelObject;

public class SecuredSubstance implements KernelObject {
    public int SecurityLevel;

    public String getComponentName() {
        return "GenericSecuredSubstance@" + this.hashCode();
    }
}
