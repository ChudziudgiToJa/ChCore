package pl.chudziudgi.core.api.command.interfaces;

import java.lang.annotation.*;

@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {

    String name() default "";

    boolean player() default false;

    String usage() default "";

    String perm() default "";

    String[] aliases() default "";

}
