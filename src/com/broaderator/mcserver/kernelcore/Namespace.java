package com.broaderator.mcserver.kernelcore;

import com.broaderator.mcserver.kernelcore.yaml.YAMLManager;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.HashMap;

public class Namespace implements KernelObject {
    public static String joinPath(String... paths) {
        String output = "";
        for (String path : paths) {
            output += path;
            if (!output.endsWith(".")) output += ".";
        }
        return output.substring(0, output.length() - 1);
    }

    public HashMap<String, Object> storage = new HashMap<>();
    private String identifier;
    private boolean tofile;

    public Namespace(String ident, boolean toFile) {
        identifier = ident;
        tofile = toFile;
    }

    public Namespace(String ident, boolean toFile, HashMap<String, Object> prototype) {
        identifier = ident;
        tofile = toFile;
        storage = prototype;
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
                            (newPointer == null ? "null" : newPointer.getClass().getSimpleName()));
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
            Bukkit.getLogger().warning("KernelInternalError::Failed to locate context '" + direxp + "'");
            return false;
        }
        Object value = YAMLManager.toRepresentation(newValue);
        if (value == null && tofile) {
            Bukkit.getLogger().warning("KernelInternalError::Yaml incompatible object: " + newValue);
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
