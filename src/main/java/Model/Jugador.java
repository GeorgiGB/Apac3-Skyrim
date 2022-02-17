package Model;

import javax.persistence.*;

@Entity
@Table(name = "Jugador")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idJugador")
    private Long idJugador;

    @Column(name = "nomJugador")
    private String nomJugador;

    @Column(name = "fechaCreacion")
    private String fechaCreacion;
    //nnnn-nn-nn

    //Relacion 1a1 con facciones
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Facciones", referencedColumnName = "id")
    private Facciones lasFacciones;

    //Relacion 1:n con Razas
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRazas")
    private Razas lasRazas;

    public Jugador() {
    }

    public Jugador(String fechaCreacion, String nomJugador, Facciones faccion, Razas razas) {
        this.nomJugador = nomJugador;
        this.fechaCreacion = fechaCreacion;

    }

    public Facciones getLasFacciones() {
        return lasFacciones;
    }

    public void setLasFacciones(Facciones lasFacciones) {
        this.lasFacciones = lasFacciones;
    }

    public Razas getLasRazas() {
        return lasRazas;
    }

    public void setLasRazas(Razas lasRazas) {
        this.lasRazas = lasRazas;
    }
    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    public String getNomJugador() {
        return nomJugador;
    }

    public void setNomJugador(String nomJugador) {
        this.nomJugador = nomJugador;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Razas getLasRazas(Razas r) {
        return lasRazas;
    }




    @Override
    public String toString() {
        return getIdJugador()+ ", " +getNomJugador() + ", " + getFechaCreacion() + ", " + getLasFacciones().getNomFaccion() + ", " + getLasRazas().getNomRazas();
    }


}
