package Peticiones.APAC3;

import AD.APAC3.HibernateUtil;
import org.hibernate.Session;

public class Guardar {
    public static Session laSessio;

    public static void guardarObj(Object e){
        laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();

        laSessio.persist(e);

        laSessio.getTransaction().commit();
    }

}
