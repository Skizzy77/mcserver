package com.broaderator.mcserver.kernelbase;

import com.broaderator.mcserver.kernel.Logger;
import com.broaderator.mcserver.kernel.yaml.YAMLManager;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.HashMap;

public class Namespace implements KernelObject{
    public HashMap<String, Object> storage = new HashMap<>();
    private String identifier;
    private boolean tofile;

    public Namespace(String ident, boolean toFile) {
        identifier = ident;
        tofile = toFile;
    }

    public boolean createDirs(String direxp){
        return parse(direxp.split("\\."), true) != null;
    }

    public Object get(String direxp){
        String[] idents = direxp.split("\\.");
        HashMap<String, Object> context = getContext(idents);
        if(context == null) return null;
        return context.get(idents[idents.length-1]);
    }

    private HashMap<String, Object> parse(String[] dirs, boolean createDirs){
        HashMap<String, Object> pointer = storage;
        for(String dir : dirs){
            Object newPointer = pointer.get(dir);
            if(createDirs){
                if(newPointer != null && !(newPointer instanceof HashMap)){
                    Bukkit.getLogger().warning("KernelInternalError::Expression parse error, expected null or HashMap, received " +
                            newPointer.getClass().getSimpleName());
                }
                if(newPointer == null) pointer.put(dir, new HashMap<String, Object>());
            }else{
                if(!(newPointer instanceof HashMap)){
                    Bukkit.getLogger().warning("KernelInternalError::Expression parse error, expected HashMap, received " +
                            newPointer.getClass().getSimpleName());
                    return null;
                }
            }
            pointer = (HashMap) pointer.get(dir);
        }
        return pointer;
    }

    public HashMap<String, Object> getDirectory(String path) {
        return parse(path.split("\\."), false);
    }

    public boolean put(String direxp, Object newValue){
        String[] idents = direxp.split("\\.");
        HashMap<String, Object> context = getContext(idents);
        if (context == null) {
            Logger.warn(this, "Failed to locate context '" + direxp + "'");
            return false;
        }
        Object value = YAMLManager.toRepresentation(newValue);
        if (value == null && tofile) {
            Logger.warn(this, "Yaml incompatible object: " + newValue);
            return false;
        }
        context.put(idents[idents.length - 1], value);
        return true;
    }

    private HashMap<String, Object> getContext(String[] idents) {
        return parse(Arrays.copyOf(idents, idents.length - 1), false);
    }

    public boolean remove(String direxp) {
        String[] idents = direxp.split("\\.");
        HashMap<String, Object> context = getContext(idents);
        if (context == null) return false;
        if (!context.containsKey(idents[idents.length - 1])) return false;
        context.remove(idents[idents.length - 1]);
        return true;
    }

    @Override
    public String getComponentName() {
        return "Namespace." + identifier;
    }
}
