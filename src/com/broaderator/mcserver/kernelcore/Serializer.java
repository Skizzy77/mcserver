package com.broaderator.mcserver.kernelcore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Serializer {
    public interface Module {
        boolean isValid(Object obj);

        Object serialize(HashMap<String, Object> obj);

        HashMap<String, Object> deserialize(Object obj);
    }

    private static List<Module> modules = Arrays.asList(

    )

    public static boolean isSerializable(Object obj) {

    }


}
