package com.broaderator.mcserver.kernel.loader;

public enum LoadPriorityLevel {
    EMPTY(0),
    LOW(1),
    MEDIUM_LOW(2),
    MEDIUM(3),
    MEDIUM_HIGH(4),
    HIGH(5);
    private int level;

    LoadPriorityLevel(int l) {
        level = l;
    }

    public int getLevel() {
        return level;
    }
}
