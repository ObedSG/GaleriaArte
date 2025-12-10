package org.ipn.mx.galeriaarte.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "ColeccionObra")
public class ColeccionObra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idColeccionObra")
    private Integer idColeccionObra;

    @Column(name = "idObraDigital", nullable = false)
    private Integer idObraDigital;

    @Column(name = "idColeccion", nullable = false)
    private Integer idColeccion;

    @ManyToOne
    @JoinColumn(name = "idObraDigital", insertable = false, updatable = false)
    private ObraDigital obraDigital;

    @ManyToOne
    @JoinColumn(name = "idColeccion", insertable = false, updatable = false)
    private Coleccion coleccion;

    public ColeccionObra() {}

    public ColeccionObra(Integer idObraDigital, Integer idColeccion) {
        this.idObraDigital = idObraDigital;
        this.idColeccion = idColeccion;
    }

    public Integer getIdColeccionObra() { return idColeccionObra; }
    public void setIdColeccionObra(Integer idColeccionObra) { this.idColeccionObra = idColeccionObra; }

    public Integer getIdObraDigital() { return idObraDigital; }
    public void setIdObraDigital(Integer idObraDigital) { this.idObraDigital = idObraDigital; }

    public Integer getIdColeccion() { return idColeccion; }
    public void setIdColeccion(Integer idColeccion) { this.idColeccion = idColeccion; }

    public ObraDigital getObraDigital() { return obraDigital; }
    public void setObraDigital(ObraDigital obraDigital) { this.obraDigital = obraDigital; }

    public Coleccion getColeccion() { return coleccion; }
    public void setColeccion(Coleccion coleccion) { this.coleccion = coleccion; }
}