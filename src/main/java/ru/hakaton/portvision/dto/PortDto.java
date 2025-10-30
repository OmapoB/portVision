package ru.hakaton.portvision.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PortDto implements Serializable {
    private Port port;
    private List<Comment> comments;
    private List<Comment> files;
}