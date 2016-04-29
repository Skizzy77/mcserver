package com.broaderator.mcserver.kernel.yaml;

import java.io.Serializable;
import java.util.HashMap;

public interface YAMLModule<T> extends Serializable {
    HashMap<String, Object> toYAML(T obj);

    T fromYAML(HashMap<String, Object> obj);
}
