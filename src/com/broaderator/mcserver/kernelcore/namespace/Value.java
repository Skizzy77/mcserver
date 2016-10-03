package com.broaderator.mcserver.kernelcore.namespace;

import com.broaderator.mcserver.kernelcore.KernelObject;
import com.broaderator.mcserver.kernelcore.Serializable;
import com.broaderator.mcserver.kernelcore._;
import com.broaderator.mcserver.kernelcore.util.StringFormat;

import java.util.HashMap;

public class Value implements NamespaceElement, KernelObject, Serializable {
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
        return this.id;
    }

    @Override
    public String getComponentName() {
        return StringFormat.f("NamespaceValue#{0}:{1}", this.hashCode(), this.id);
    }

    public String toString() {
        return getComponentName();
    }

    @Override
    public HashMap<String, Object> represent() {
        return _.createHashmap(
                "type", "NamespaceValue",
                "value", this.value
        );
    }

    @Override
    public String getType() {
        return "NamespaceValue";
    }
}
