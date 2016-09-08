package com.broaderator.mcserver.kernelcore.namespace;

public class NodeAlreadyExistsException extends RuntimeException {
    protected NodeAlreadyExistsException(String why) {
        super(why);
    }
}

