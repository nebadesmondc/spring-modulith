package com.dezzy.springbootmodulithcourse.eventaction;

import com.dezzy.springbootmodulithcourse.eventaction.action.Action;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class EventAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(length = 2, nullable = false)
    private Action action;
    @Column(nullable = false)
    private String canonicalName;
}
