package Peticiones.APAC3;

import AD.APAC3.HibernateUtil;
import AD.APAC3.Utilidades;
import Model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RazasConnect implements DBObjects {

    public static Session laSessio;
    static List<Razas> lasRazas;

    public static void showObjectsFromRazasCascade() {
        laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        try {


            Query q = laSessio.createQuery("FROM Razas");

            lasRazas = q.list();

            Utilidades.espacioVacio(10);
            System.out.println(String.format("%-20s%-20s%-50s%", "ID", "Nombre", "Ventaja"));
            for (Razas razas : lasRazas) {
                System.out.println(String.format("%-20s%-20s%-50s%", razas.getIdRazas(),razas.getNomRazas(),razas.getVentaja()));
                System.out.println("Pueden ser elegidos por: ");
                //razas.mostrarClases();
            }


        } catch (Exception e) {

        } finally {
            laSessio.getTransaction().commit();
        }
    }

    public static void showObjectsRazasTable() {
        try {
            laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
            laSessio.beginTransaction();
            Query q = laSessio.createQuery("FROM Razas");
            lasRazas = q.list();
            System.out.println(String.format("%-20s%-20s%-20s", "ID", "Nombre ", "Ventaja"));
            for (Razas r : lasRazas)
                System.out.println(String.format("%-20s%-20s%-20s",r.getIdRazas(),r.getNomRazas(),r.getVentaja()));

        } catch (Exception e) {

        } finally {
            laSessio.getTransaction().commit();
        }
    }

    public static void createObjectsFromTable(){
        showObjectsRazasTable();
        Razas r = new Razas();

        try {
            laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
            laSessio.beginTransaction();

            String nomR = Utilidades.leerTexto("Dime el nombre de la raza. ");//nombre de la faccion
            if (nomR.isEmpty()) {
            } else {
                r.setNomRazas(nomR);
            }

            Utilidades.espacioVacio(2);
            String ventaja = Utilidades.leerTexto("Dime la ventaja de este. ");//Nombre del lider
            if (ventaja.isEmpty()) {
            } else {
                r.setVentaja(ventaja);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            laSessio.getTransaction().commit();
        }
    }

    public static void updateObjectsFromRazas(){

    }

    public static void deleteObjectsFromRazas() {
        JugadoresConnect.showObjectsFromJugadores();
        laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        Long idJRazas = Utilidades.leerLong("Dime la Id de la raza a la que quieras borrar: ");
        Razas r = laSessio.get(Razas.class, idJRazas);

        System.out.println("Borrando raza...");
        laSessio.delete(r);
        laSessio.getTransaction().commit();
        System.out.println("Raza borrada.");
    }




    /*
    INSERT INTO `skyrimdb`.`Razas` (`nomRazas`, `ventaja`) VALUES ('Altmer', '+50 reserva de magia');
    insertar a nuevas razas
     */
}