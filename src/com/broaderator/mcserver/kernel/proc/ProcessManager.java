package com.broaderator.mcserver.kernel.proc;

import com.broaderator.mcserver.kernelcore.ModuleAgent;

import java.util.List;

public class ProcessManager {
    public static ModuleAgent Ma = new ModuleAgent() {
        @Override
        public String getComponentName() {
            return null;
        }

        @Override
        public int init() {
            return 0;
        }

        @Override
        public int exit() {
            return 0;
        }

        @Override
        public List<ModuleAgent> getDependencies() {
            return null;
        }

        @Override
        public boolean useVariables() {
            return false;
        }

        @Override
        public boolean useEvents() {
            return false;
        }
    };

    static int _register(Process p) {
        return -1;
    }
}
