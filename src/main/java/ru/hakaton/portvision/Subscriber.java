package ru.hakaton.portvision;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@ToString
@Table(name = "subscriber")
@Accessors(chain = true)
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String eventType;
    private String mail;
    private Integer targetId;
}
