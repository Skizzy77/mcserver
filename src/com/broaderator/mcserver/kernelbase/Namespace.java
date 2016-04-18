package com.broaderator.mcserver.kernelbase;

import com.broaderator.mcserver.kernel.Logger;

import java.util.Arrays;
import java.util.HashMap;

public class Namespace implements KernelObject{
    public HashMap<String, Object> storage = new HashMap<>();
    private String identifier;

    public Namespace(String ident) {identifier = ident;}

    public boolean createDirs(String direxp){
        return parse(direxp.split("\\."), true) != null;
    }

    public Object get(String direxp){
        String[] idents = direxp.split("\\.");
        HashMap<String, Object> context = parse(Arrays.copyOf(idents, idents.length - 1), false);
        if(context == null) return null;
        return context.get(idents[idents.length-1]);
    }

    private HashMap<String, Object> parse(String[] dirs, boolean createDirs){
        HashMap<String, Object> pointer = storage;
        for(String dir : dirs){
            Object newPointer = pointer.get(dir);
            if(createDirs){
                if(newPointer != null && !(newPointer instanceof HashMap)){
                    Logger.error(this, "Expression parse error, expected null or HashMap, received " +
                            newPointer.getClass().getSimpleName());
                    return null;
                }
                if(newPointer == null) pointer.put(dir, new HashMap<String, Object>());
            }else{
                if(!(newPointer instanceof HashMap)){
                    Logger.error(this, "Expression parse error, expected HashMap, received " +
                            newPointer.getClass().getSimpleName());
                    return null;
                }
            }
            pointer = (HashMap) pointer.get(dir);
        }
        return pointer;
    }

    public boolean put(String direxp, Object newValue){
        String[] idents = direxp.split("\\.");
        HashMap<String, Object> context = parse(Arrays.copyOf(idents, idents.length - 1), false);
        if(context == null) return false;
        context.put(idents[idents.length - 1], newValue);
        return true;
    }

    @Override
    public String getComponentName() {
        return "Namespace::" + identifier;
    }
}
