package ru.hakaton.portvision;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Event {

    private String eventType;
    private Integer targetId;
    private String body;
}
