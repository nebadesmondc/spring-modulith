package com.dezzy.springbootmodulithcourse.eventaction;

import com.dezzy.springbootmodulithcourse.eventaction.action.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventActionRepository extends CrudRepository<EventAction, Long> {
    Optional<EventAction> getEventActionByAction(Action action);
}
