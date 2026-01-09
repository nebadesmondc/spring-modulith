package com.dezzy.springbootmodulithcourse.eventaction;

import com.dezzy.springbootmodulithcourse.eventaction.action.Action;
import com.dezzy.springbootmodulithcourse.eventaction.action.CustomEventMarker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class Setup implements ApplicationRunner {
    private final EventActionRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);

        provider.addIncludeFilter(new AnnotationTypeFilter(CustomEventMarker.class));

        Set<BeanDefinition> beanDefs = provider.findCandidateComponents("com.dezzy.springbootmodulithcourse");

        Map<String, String> eventActionMap = new HashMap<>();

        for(BeanDefinition beanDef : beanDefs){
            if (beanDef instanceof AnnotatedBeanDefinition) {
                Map<String, Object> attributes = ((AnnotatedBeanDefinition) beanDef).getMetadata()
                        .getAnnotationAttributes(CustomEventMarker.class.getCanonicalName());

                eventActionMap.put(attributes.get("eventAction").toString(),
                        beanDef.getBeanClassName());
            }
        }

        List<EventAction> eventActionList = new ArrayList<>(eventActionMap.size());

        eventActionMap.forEach((k, v) -> {
            EventAction eventAction = new EventAction();
            Action action = Action.getCodeByName(k);
            eventAction.setAction(action);
            eventAction.setCanonicalName(v);

            if (repository.getEventActionByAction(action).isEmpty()) {
                eventActionList.add(eventAction);
            }
        });

        if (!eventActionList.isEmpty()) {
            repository.saveAll(eventActionList);
        }
    }
}
