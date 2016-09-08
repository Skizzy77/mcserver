package com.broaderator.mcserver.kernelcore.namespace;

import com.broaderator.mcserver.kernelcore.KernelObject;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

public class Value implements NamespaceElement, KernelObject {
    private String id;
    Object value;

    Value(String id, Object value) {
        this.id = id;
        this.value = value;
    }

    public Object get() {
        return value;
    }

    public void set(Object newValue) {
        value = newValue;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public String getComponentName() {
        return StringFormat.f("NamespaceValue#{0}:{1}", this.hashCode(), this.id);
    }

    public String toString() {
        return getComponentName();
    }
}
