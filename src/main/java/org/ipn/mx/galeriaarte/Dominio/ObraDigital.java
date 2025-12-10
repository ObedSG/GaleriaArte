package org.ipn.mx.galeriaarte.Dominio;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ObraDigital")
public class ObraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idObraDigital")
    private Integer idObraDigital;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "descripcion", length = 512)
    private String descripcion;

    @Column(name = "fechaPublicacion")
    private LocalDate fechaPublicacion;

    @Column(name = "idAutor")
    private Integer idAutor;

    @Column(name = "idArchivoPrincipal")
    private Integer idArchivoPrincipal;

    @ManyToOne
    @JoinColumn(name = "idAutor", insertable = false, updatable = false)
    private Autor autor;

    @OneToOne
    @JoinColumn(name = "idArchivoPrincipal", insertable = false, updatable = false)
    private ArchivoDigital archivoPrincipal;

    @OneToMany(mappedBy = "obraDigital", cascade = CascadeType.ALL)
    private List<ArchivoDigital> archivos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "ObraCategoria",
            joinColumns = @JoinColumn(name = "idObraDigital"),
            inverseJoinColumns = @JoinColumn(name = "idCategoria")
    )
    private List<Categoria> categorias = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "ColeccionObra",
            joinColumns = @JoinColumn(name = "idObraDigital"),
            inverseJoinColumns = @JoinColumn(name = "idColeccion")
    )
    private List<Coleccion> colecciones = new ArrayList<>();

    public ObraDigital() {}

    public ObraDigital(String titulo, String descripcion, LocalDate fechaPublicacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getIdObraDigital() { return idObraDigital; }
    public void setIdObraDigital(Integer idObraDigital) { this.idObraDigital = idObraDigital; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getIdAutor() { return idAutor; }
    public void setIdAutor(Integer idAutor) { this.idAutor = idAutor; }

    public Integer getIdArchivoPrincipal() { return idArchivoPrincipal; }
    public void setIdArchivoPrincipal(Integer idArchivoPrincipal) {
        this.idArchivoPrincipal = idArchivoPrincipal;
    }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    public ArchivoDigital getArchivoPrincipal() { return archivoPrincipal; }
    public void setArchivoPrincipal(ArchivoDigital archivoPrincipal) {
        this.archivoPrincipal = archivoPrincipal;
    }

    public List<ArchivoDigital> getArchivos() { return archivos; }
    public void setArchivos(List<ArchivoDigital> archivos) { this.archivos = archivos; }

    public List<Categoria> getCategorias() { return categorias; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }

    public List<Coleccion> getColecciones() { return colecciones; }
    public void setColecciones(List<Coleccion> colecciones) { this.colecciones = colecciones; }
}