package com.broaderator.mcserver.kernelcore.api;

import java.util.List;

public class HelpArticle {
    public final String usage;
    public final List<String> examples;
    public final String purpose;

    public HelpArticle(String usage, List<String> examples, String purpose) {
        this.usage = usage;
        this.examples = examples;
        this.purpose = purpose;
    }
}
