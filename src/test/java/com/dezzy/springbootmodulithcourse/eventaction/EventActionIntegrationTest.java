package com.dezzy.springbootmodulithcourse.eventaction;

import com.dezzy.springbootmodulithcourse.eventaction.action.Action;
import com.dezzy.springbootmodulithcourse.eventaction.action.RepublishUncompletedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

@ApplicationModuleTest
class EventActionIntegrationTest {

    @Autowired
    RepublishUncompletedEvent republishUncompletedEvent;

    @Test
    void verifyModule() {

    }

    @Test
    void testUncompletedEvent() {
        republishUncompletedEvent.republish(Action.PAYMENT);
    }
}