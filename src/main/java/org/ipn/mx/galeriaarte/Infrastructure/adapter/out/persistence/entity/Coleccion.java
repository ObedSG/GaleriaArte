package org.ipn.mx.galeriaarte.Infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Coleccion")
public class Coleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idColeccion")
    private Integer idColeccion;

    @Column(name = "nombreColeccion", nullable = false, length = 150)
    private String nombreColeccion;

    @Column(name = "descripcionColeccion", length = 512)
    private String descripcionColeccion;

    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "fechaFin")
    private LocalDate fechaFin;

    @ManyToMany(mappedBy = "colecciones")
    private List<ObraDigital> obras = new ArrayList<>();

    public Coleccion() {}

    public Coleccion(String nombreColeccion, String descripcionColeccion,
                     LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombreColeccion = nombreColeccion;
        this.descripcionColeccion = descripcionColeccion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdColeccion() { return idColeccion; }
    public void setIdColeccion(Integer idColeccion) { this.idColeccion = idColeccion; }

    public String getNombreColeccion() { return nombreColeccion; }
    public void setNombreColeccion(String nombreColeccion) { this.nombreColeccion = nombreColeccion; }

    public String getDescripcionColeccion() { return descripcionColeccion; }
    public void setDescripcionColeccion(String descripcionColeccion) {
        this.descripcionColeccion = descripcionColeccion;
    }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public List<ObraDigital> getObras() { return obras; }
    public void setObras(List<ObraDigital> obras) { this.obras = obras; }
}