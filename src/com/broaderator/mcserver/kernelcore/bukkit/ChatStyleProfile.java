package com.broaderator.mcserver.kernelcore.bukkit;

import com.broaderator.mcserver.kernelcore._;
import com.broaderator.mcserver.kernelcore.util.StringFormat;
import org.bukkit.ChatColor;

public class ChatStyleProfile {
    private String name;
    private ChatColor prefixColor;
    private ChatColor textColor;
    private ChatColor nameColor;
    private String format;

    ChatStyleProfile(String name, ChatColor prefixColor, ChatColor textColor, ChatColor nameColor, String format) {
        this.name = name;
        this.prefixColor = prefixColor;
        this.textColor = textColor;
        this.nameColor = nameColor;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return StringFormat.f("<ChatStyleProfile#{0}: {1}>", this.hashCode(), this.name);
    }

    public boolean equals(Object other) {
        if (other instanceof ChatStyleProfile) {
            for (String property : "name;prefixColor;textColor;nameColor;format".split(";")) {
                if (!(_.getAttribute(other, property).equals(_.getAttribute(this, property)))) return false;
            }
            return true;
        } else return false;
    }

    public ChatColor prefixColor() {
        return prefixColor;
    }

    public void prefixColor(ChatColor prefixColor) {
        this.prefixColor = prefixColor;
    }

    public ChatColor textColor() {
        return textColor;
    }

    public void textColor(ChatColor textColor) {
        this.textColor = textColor;
    }

    public ChatColor nameColor() {
        return nameColor;
    }

    public void nameColor(ChatColor nameColor) {
        this.nameColor = nameColor;
    }

    public String format() {
        return format;
    }

    public void format(String format) {
        this.format = format;
    }

}
