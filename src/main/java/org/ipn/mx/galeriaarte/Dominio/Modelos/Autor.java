package org.ipn.mx.galeriaarte.Dominio.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "Autor")
public class Autor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAutor;


    @Column(nullable = false, length = 100)
    private String nombreCompleto;

    private String seudonimo;

    private String correoContacto;

    private String avatar;

}
