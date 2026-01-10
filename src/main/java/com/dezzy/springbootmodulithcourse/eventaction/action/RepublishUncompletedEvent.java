package com.dezzy.springbootmodulithcourse.eventaction.action;

import com.dezzy.springbootmodulithcourse.eventaction.EventAction;
import com.dezzy.springbootmodulithcourse.eventaction.EventActionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.modulith.events.CompletedEventPublications;
import org.springframework.modulith.events.IncompleteEventPublications;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class    RepublishUncompletedEvent {

    private final EventActionRepository eventActionRepository;
    private final CompletedEventPublications completedEventPublications;
    private final IncompleteEventPublications incompleteEventPublications;
    private final Environment env;

    public void republish(Action action) {
        Optional<EventAction> optEventAction = eventActionRepository.getEventActionByAction(action);

        if (optEventAction.isEmpty()) {
            log.info("No event action found for action {}", action);
        }

        if (optEventAction.isPresent()) {
            log.info("Republish uncompleted events for action {}", action);
            EventAction eventAction = optEventAction.get();

            try {
                final Class<?> actionClass = Class.forName(eventAction.getCanonicalName());
                incompleteEventPublications.resubmitIncompletePublications((ep -> ep.getEvent().getClass() == actionClass));
//                completedEventPublications.deletePublicationsOlderThan(Duration.ofHours(env.getProperty("delete.event.action", Long.class, 100L)));
            } catch(ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void republish(List<Action> actions) {
        actions.forEach(this::republish);
    }

    @Scheduled(fixedRate = 3L, timeUnit = TimeUnit.HOURS)
    public void republish() {
        log.info("Republish uncompleted events");
        for (Action action : Action.values()) {
            republish(action);
        }
    }
}
