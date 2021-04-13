package dao;

import java.util.ArrayList;
import model.Estado;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import util.HibernateUtil;

/**
 * Classa para armazenar os métodos para acesso ao bando de dados
 * @author Renato Dias
 * @since Mar 17, 2021
 * @version 1.0
 */
public class EstadoDAO extends GenericDAO {
    
    /*
    * método para consultar os Estado gravados na tabela
    */
    public ArrayList<Estado> buscarTodos() throws Exception {
        // lista auxiliar para retorno no método
        ArrayList<Estado> retorno = new ArrayList<>();
        // classe auxiliar para armazenar a sessão com o banco de dados
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        // classe auxiliar para consultar o banco de dados
        Criteria criteria = sessao.createCriteria(Estado.class);
        // adicionando a ordenação da pesquisa
        criteria.addOrder(Order.asc("idEstado"));
        // valorizando o objeto de retorno do método com os registros da tabela
        retorno = (ArrayList<Estado>) criteria.list();
        // encerrando a conexão com o banco de dados
        sessao.close();
        //retornanado a lista preenchida
        return retorno;
    }

}
