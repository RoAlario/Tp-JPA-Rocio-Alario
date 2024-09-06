package org.example;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Builder
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="categoria")


public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdCategoria;

    @Column(name= "Descripcion")
    private String denominacionCat;

    @ManyToMany(mappedBy = "categorias")
    @Builder.Default
    private List<Articulo> articulos = new ArrayList<Articulo>();



}
