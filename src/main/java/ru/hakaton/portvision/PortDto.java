package ru.hakaton.portvision;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PortDto implements Serializable {
    private Port port;
    private List<Comment> comments;
}