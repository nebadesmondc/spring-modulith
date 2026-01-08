package com.dezzy.springbootmodulithcourse.eventpublication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
class CustomEventPublicationIntegrationTest {

    @Autowired
    CustomEventPublicationService service;

    @Test
    void getUncompletedEventPublications() {
        List<CustomEventPublication> uncompletedEventPublications = service.getUncompletedEventPublications();

        uncompletedEventPublications
                .forEach(ev -> {
                    assertThat(ev).isInstanceOf(CustomEventPublication.class);
                    assertThat(ev.getCompletionDate()).isNull();
                });
    }

    @Test
    void getCompletedEventPublications() {
        List<CustomEventPublication> completedEventPublications = service.getCompletedEventPublications();

        completedEventPublications
                .forEach(ev -> {
                    assertThat(ev).isInstanceOf(CustomEventPublication.class);
                    assertThat(ev.getCompletionDate()).isNotNull();
                });
    }

}