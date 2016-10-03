package com.broaderator.mcserver.kernelcore;

import java.util.HashMap;

public interface Serializable {
    HashMap<String, Object> represent();
    String getType();
}
