package com.broaderator.mcserver.kernelcore.event;

public interface EventProcessor<E> {
    E run(E vararg);
}