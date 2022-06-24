package com.example.asmspringboot.entity;

import com.example.asmspringboot.entity.enums.RoadStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roads")
public class Road {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="district_id", nullable = false)
    private District district;
    @Column(updatable = false, insertable = false)
    private Integer district_id;
    private String description;
    private LocalDate roadEstablishmentDate;
    @Enumerated(EnumType.ORDINAL)
    private RoadStatus status;
}
