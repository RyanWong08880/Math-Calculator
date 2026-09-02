package com.mathcalc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for marking methods as CLI commands.
 * Provides metadata about command parameters, aliases, and descriptions.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    /**
     * Number of expected parameters.
     * Use negative values to indicate "at least N" (e.g., -2 means "at least 2")
     */
    int numExpectedParams() default 0;

    /**
     * Alternative command names/aliases
     */
    String[] aliases() default {};

    /**
     * Description of what this command does
     */
    String desc() default "???";
}
