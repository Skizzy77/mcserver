package com.broaderator.mcserver.kernel.yaml;

import com.broaderator.mcserver.kernel.$;
import com.broaderator.mcserver.kernel.Logger;
import com.broaderator.mcserver.kernelbase.Manager;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class YAMLManager {
    private static final Path moduleDir = Paths.get((String) $.globalNS.get("Resources.WorkingDirectory"),
            (String) $.globalNS.get("Manager.YAML.ModuleFolder"));
    private static List<YAMLModule> modules = new ArrayList<>();
    public static final Manager yamlMan = new Manager() {
        @Override
        public boolean spawn() {
            Logger.info(this, "Loading external modules");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(moduleDir)) {
                for (Path file : stream) {
                    if (file.getFileName().endsWith(".mod")) {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.toFile()));
                        Object mod = ois.readObject();
                        if (!(mod instanceof YAMLModule)) {
                            Logger.warn(this, "Invalid module: " + file.getFileName() + ", type not YAMLModule");
                        } else {
                            modules.add((YAMLModule) mod);
                            Logger.debug(this, "Module added: " + file.getFileName(), $.DL_DETAILS);
                        }
                        ois.close();
                    } else {
                        Logger.info(this, "Unknown file: " + file.getFileName() + ", ignoring");
                    }
                }
                Logger.debug(this, "Module count: " + modules.size(), $.DL_INFO);
                Logger.info(this, "External module load complete");
                return true;
            } catch (Exception e) {
                Logger.error(this, "Directory listing or File I/O failure: " + e);
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean die() {
            modules.clear();
            return true;
        }

        @Override
        public String getComponentName() {
            return "YAMLManager";
        }
    };

    static final List<Class<? extends Serializable>> validClasses = Arrays.asList(
            String.class,
            ArrayList.class,
            HashMap.class,
            Integer.class
    );

    public static boolean isValid(Object obj) {
        for (Class c : validClasses) {
            if (c.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    public static Object toRepresentation(Object input) {
        if (isValid(input)) return input;
        for (YAMLModule ym : modules) {
            try {
                return ym.toYAML(input);
            } catch (Exception e) {
            }
        }
        return null;
    }
}
