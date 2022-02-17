package Peticiones.APAC3;

import AD.APAC3.HibernateUtil;
import AD.APAC3.Utilidades;
import Model.Facciones;
import Model.Jugador;
import Model.Razas;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Objects;

public class JugadoresConnect implements DBObjects{

    public static Session laSessio;

    public static void showObjectsFromJugadores() {


        laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        try {
            Query q = laSessio.createQuery("FROM Jugador");

            List<Jugador> losJugadores = q.list();
            Utilidades.espacioVacio(20);
            System.out.println("Lista de Jugadores");
            System.out.println(String.format("%-5s%-20s%-10s%-30s%-30s", "ID","FechaCreacion","Nombre", "Faccion", "Raza"));
            for (Jugador jug : losJugadores) {

                //System.out.println(jug);

                jug.getLasRazas().setNomRazas(jug.getLasRazas().getNomRazas().isEmpty() ? "No info" : jug.getLasRazas().getNomRazas());
                System.out.println(String.format("%-5s%-20s%-10s%-30s%-30s", jug.getIdJugador(), jug.getFechaCreacion(), jug.getNomJugador(), jug.getLasFacciones().getNomFaccion(), jug.getLasRazas().getNomRazas()));
            }
            laSessio.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e);
        }


    }

    public static void createObjectsFromTable() {
        String fecha;//mmmm-nn-nn
        String nom;
        String nomFac;
        String nomLider;
        Jugador j = new Jugador();
        Facciones f;
        Razas r;
        String nomRaza;
        String nomVentaja;
        Long idRazaExists;

        //Pedimos fecha y nombre al jugador
        fecha = Utilidades.leerTexto("Dime la fecha de la creacion del personaje(mmmm-nn-nn): ");

        j.setFechaCreacion(fecha);
        nom = Utilidades.leerTexto("Nombre del jugador: ");

        j.setNomJugador(nom);

        //Preguntamos si queremos que el jugador pertencezca a una Faccion
        String res = Utilidades.leerTexto("Quieres añadir al jugador a una faccion existente? si/no: ");
        if (Objects.equals(res, "si")) {
            System.out.println("Facciones actuales");
            FaccionesConnect.showObjectsFromFacciones();
            Long idFaccExist = Utilidades.leerLong("Dime al id de la faccion");
            laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
            laSessio.beginTransaction();
            f = laSessio.get(Facciones.class, idFaccExist);

            j.setLasFacciones(f);
            laSessio.getTransaction().commit();


        } else {
            nomFac = Utilidades.leerTexto("Dime el nombre de la facción: ");
            nomLider = Utilidades.leerTexto("Dime el nombre del lider: ");
            //Añadimos la nueva faccion a Facciones
            f = new Facciones(nomFac, nomLider);

            j.setLasFacciones(f);
            laSessio.getTransaction().commit();
        }

        //Le preguntamos por la raza que quiere crear(esta parte es obligatoria en la creacion)
        res = Utilidades.leerTexto("Quieres elegir una raza existente? si/no");
        if (res.equals("si")) {
            do {
                System.out.println("Razas Actuales");
                RazasConnect.showObjectsRazasTable();
                idRazaExists = Utilidades.leerLong("Dime la id de la raza: ");
                laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
                laSessio.beginTransaction();
                r = laSessio.get(Razas.class, idRazaExists);
                laSessio.getTransaction().commit();

                j.setLasRazas(r);
            } while (idRazaExists == null);
        } else {
            nomRaza = Utilidades.leerTexto("Dime el nombre de la raza: ");
            nomVentaja = Utilidades.leerTexto("Dime la ventaja de la raza: ");
            //Añadimos la nueva raza a Razas
            r = new Razas(nomRaza, nomVentaja);

            j.setLasRazas(r);
            laSessio.getTransaction().commit();
        }
        // Y ponemos lo anterior en el nuevo jugador
        j = new Jugador(fecha, nom, f, r);

        System.out.println("Creando jugador...");

        Guardar.guardarObj(j);

        System.out.println("Jugador se ha creado de forma satisfactoria.");

        //INSERT INTO `skyrimdb`.`Jugador` (`fechaCreacion`, `nomJugador`, `Facciones`, `idRazas`) VALUES ('2222-02-22', 'Test', '1', '2');

    }

    public static void updateObjectsFromJugadors() {

        showObjectsFromJugadores();
        Razas r = new Razas();
        Jugador j;
        Facciones f = new Facciones();
        String nomFac, nomLider;

        //Preguntamos por la id del jugador para poder buscarlo en la lista
        Long idJug = Utilidades.leerLong("Dime la id del jugador");
        laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        j = laSessio.get(Jugador.class, idJug);//elegimos el Jugador

        //A partir de aqui actualizamos la informacion de ese jugador.
        String nomJug = Utilidades.leerTexto("Nombre del jugador: ");

        //Preguntamos si queremos que el jugador pertencezca a una Faccion
        String res = Utilidades.leerTexto("Quieres añadir al jugador a una faccion existente? si/no: ");
        if (res.equals("si")) {
            System.out.println("Facciones");
            FaccionesConnect.showObjectsFromFacciones();
            Long idFaccExist = Utilidades.leerLong("Dime al id de la faccion");

            f = laSessio.get(Facciones.class, idFaccExist);

            //Añadimos al jugador a su nueva faccion
            j.setLasFacciones(f);
        } else {
            nomFac = Utilidades.leerTexto("Dime el nombre de la facción: ");
            nomLider = Utilidades.leerTexto("Dime el nombre del lider: ");
            //Añadimos la nueva faccion a Facciones y su respectivo Lider
            f.setNomFaccion(nomFac);
            f.setLiderFaccion(nomLider);
        }


        System.out.println("Razas Actuales");
        RazasConnect.showObjectsRazasTable();

        //Raza
        res = Utilidades.leerTexto("Quieres elegir una raza?");
        if (res.equals("si")) {
            String nomRaza = Utilidades.leerTexto("Dime el nombre de la raza: ");
            String nomVentaja = Utilidades.leerTexto("Dime su ventaja: ");

            r.setNomRazas(nomRaza);
            r.setVentaja(nomVentaja);
        } else {
        }

        Jugador jug = new Jugador(j.getFechaCreacion(), nomJug, f, r);

        System.out.println("Modificando al jugador...");
        Guardar.guardarObj(jug);
        System.out.println("El jugador se ha modificado de forma correcta.");

        laSessio.getTransaction().commit();
    }

    public static void deleteObjectsFromJugadors() {
        //DELETE FROM `skyrimdb`.`Jugador` WHERE (`idJugador` = '?');
        JugadoresConnect.showObjectsFromJugadores();
        laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();

        Long idJugador = Utilidades.leerLong("Dime la Id del jugador al que quieras borrar: ");
        Jugador j = laSessio.get(Jugador.class, idJugador);

        System.out.println("Borrando jugador...");
        laSessio.delete(j);
        laSessio.getTransaction().commit();
        System.out.println("Jugador borrado.");
    }
}
