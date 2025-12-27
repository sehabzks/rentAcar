package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "modeller")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer id;

    @Column(name = "model_adi", length = 255)
    private String ad;

    @ManyToOne
    @JoinColumn(name = "marka_id")
    private Marka marka;
}
