package com.broaderator.mcserver.kernelcore.security;

import com.broaderator.mcserver.kernelcore.KernelObject;

public class SecuredSubstance implements KernelObject {
    public final int SecurityLevel = -1;

    public String getComponentName() {
        return "GenericSecuredSubstance@" + this.hashCode();
    }
}
