package com.dezzy.springbootmodulithcourse.eventaction.action;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomEventMarker {

    Action eventAction();
}
