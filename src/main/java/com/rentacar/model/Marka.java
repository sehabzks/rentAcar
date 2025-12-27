package com.rentacar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "markalar")
public class Marka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marka_id")
    private Integer id;

    @Column(name = "marka_adi", length = 255)
    private String ad;
}
