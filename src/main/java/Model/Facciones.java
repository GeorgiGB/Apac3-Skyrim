package Model;

import javax.persistence.*;

@Entity
@Table(name = "Facciones")
public class Facciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idFaccion;

    @Column(name = "nombre")
    private String nomFaccion;

    @Column(name = "lider")
    private String liderFaccion;

    @OneToOne(mappedBy = "lasFacciones")
    private Jugador elJugador;

    public Facciones() {
    }

    public Facciones( String nomFaccion, String liderFaccion) {

        this.nomFaccion = nomFaccion;
        this.liderFaccion = liderFaccion;
    }

    public Jugador getElJugador() {
        return elJugador;
    }

    public void setElJugador(Jugador elJugador) {
        this.elJugador = elJugador;
    }

    public Long getIdFaccion() {
        return idFaccion;
    }

    public void setIdFaccion(Long idFaccion) {
        this.idFaccion = idFaccion;
    }

    public String getNomFaccion() {

        return this.nomFaccion.isBlank()? "No info":this.nomFaccion;
    }

    public void setNomFaccion(String nomFaccion) {
        this.nomFaccion = nomFaccion;
    }

    public String getLiderFaccion() {
        return liderFaccion;
    }

    public void setLiderFaccion(String liderFaccion) {
        this.liderFaccion = liderFaccion;
    }

    @Override
    public String toString() {
        return getIdFaccion() +","+ getNomFaccion()+","+ getLiderFaccion();
    }
}
