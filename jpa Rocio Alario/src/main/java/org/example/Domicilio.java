package org.example;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Builder
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="domicilio")

public class Domicilio implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdDomicilio;

    @Column(name = "Calle")
    private String calle;

    @Column(name = "Numerocalle")
    private int numcalle;


    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;
}