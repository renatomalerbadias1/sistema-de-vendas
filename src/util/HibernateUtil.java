package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


/**
 * Classa para efetuar a conexão com o banco de dados 
 * @author Renato Dias
 * @since Mar 24, 2021
 * @version 1.0
 */
public class HibernateUtil {
    
    // atributo para armazenar a seção de comunição
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    /*
    * método para criar um seção de conexão com o banco de dados
    */
    private static SessionFactory buildSessionFactory(){
        return new AnnotationConfiguration().configure().buildSessionFactory();
    }
    
    // método para acessar o atributo de conexão e seção com o banco de dados
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
