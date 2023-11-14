package com.poltarabatko.annotations;

import java.lang.annotation.*;

/**
 * Annotation to mark fields for automatic injection.
 * @author r.poltarabatko
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInjectable {}
