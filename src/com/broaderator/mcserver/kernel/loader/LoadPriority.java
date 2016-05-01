package com.broaderator.mcserver.kernel.loader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoadPriority {
    LoadPriorityLevel level() default LoadPriorityLevel.LOW;
}
