package Peticiones.APAC3;

import AD.APAC3.HibernateUtil;
import AD.APAC3.Utilidades;
import Model.Facciones;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class FaccionesConnect implements DBObjects{

    public static Session laSessio;

    public static void showObjectsFromFaccionesCascade() {
        try {
            laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
            laSessio.beginTransaction();

            Query q = laSessio.createQuery("FROM Facciones");

            List<Facciones> lasFacciones = q.list();
            Utilidades.espacioVacio(20);
            System.out.println("Tabla de facciones");
            for (Facciones fac : lasFacciones) {
                System.out.println("Faccion:");
                System.out.print(fac.getNomFaccion() + "\n");

                System.out.println("Lider: ");
                System.out.println(fac.getLiderFaccion());
                Utilidades.liniadoble(20);
                Utilidades.espacioVacio(1);
            }

        } catch (Exception e) {

        } finally {
            laSessio.getTransaction().commit();
        }
    }


    public static void showObjectsFromFacciones() {
        try {
            laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
            laSessio.beginTransaction();
            Query q = laSessio.createQuery("FROM Facciones");

            List<Facciones> lasFacciones = q.list();
            Utilidades.espacioVacio(20);
            System.out.println("Tabla Facciones");
            System.out.println(String.format("%-10s%-40s%-40s", "ID", "Nombre de la faccion", "Lider de la faccion"));
            for (Facciones fac : lasFacciones) {
                System.out.println(String.format("%-10s%-40s%-40s", fac.getIdFaccion(), fac.getNomFaccion(), fac.getLiderFaccion()));
            }

        } catch (Exception e) {

        } finally {
            laSessio.getTransaction().commit();
        }
    }

    public static void createObjectsFromTable() {
        String nomF;
        String nomL;

        try {
            Utilidades.espacioVacio(20);
            nomF = Utilidades.leerTexto("Dime el nombre de la nueva faccion: ");

            nomL = Utilidades.leerTexto("Dime el nombre del lider: ");

            Facciones f = new Facciones(nomF, nomL);

            Guardar.guardarObj(f);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateObjectsFromFacciones() {
        showObjectsFromFacciones();
        try {
            laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
            laSessio.beginTransaction();

            long idFaccion = Utilidades.leerLong("Dime la id de la faccion.");
            Facciones f = laSessio.get(Facciones.class, idFaccion);//elegimos la faccion
            laSessio.getTransaction().commit();

            Utilidades.espacioVacio(3);
            String nomF = Utilidades.leerTexto("Dime el nuevo nombre de la faccion.");//nombre de la faccion
            if (nomF.isEmpty()){
            }else {
                f.setNomFaccion(nomF);
            }
            String nomL = Utilidades.leerTexto("Dime el nuevo nombre del lider.");//Nombre del lider
            if (nomL.isEmpty()){
            }else {
                f.setLiderFaccion(nomF);
            }
            Guardar.guardarObj(f);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteObjectsFromFacciones() {
        showObjectsFromFacciones();
        laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        Long idFaccion = Utilidades.leerLong("Dime la Id de la faccion que quieras borrar: ");
        Facciones f = laSessio.get(Facciones.class, idFaccion);

        System.out.println("Borrando faccion...");
        laSessio.delete(f);
        laSessio.getTransaction().commit();
        Utilidades.espacioVacio(10);
        System.out.println("Faccion borrada.");
    }

}
