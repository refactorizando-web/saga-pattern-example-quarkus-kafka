package com.refactorizando.sample.saga.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String type;

    private Integer column;

    private Integer row;

    private String ref;

    private String status;

    @OneToOne
    private User user;

    @OneToOne
    private Payment payment;
}
