package ru.hakaton.portvision.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "port")
@Accessors(chain = true)
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String description;
    private Double rating;
    private Double shipsInServe;
    private Double shipsThroughput;
    private List<Double> waterPollution;
    private List<Double> airPollution;
    private Integer incidentCount;
    private Double latitude;
    private Double longitude;
    private String imageUrl;
}
