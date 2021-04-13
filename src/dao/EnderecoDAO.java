package dao;

import java.util.ArrayList;
import model.Endereco;
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
public class EnderecoDAO extends GenericDAO {
    
    /*
    * método para consultar os Endereco gravados na tabela
    */
    public ArrayList<Endereco> buscarTodos() throws Exception {
        // lista auxiliar para retorno no método
        @SuppressWarnings("UnusedAssignment")
        ArrayList<Endereco> retorno = new ArrayList<>();
        // classe auxiliar para armazenar a sessão com o banco de dados
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        // classe auxiliar para consultar o banco de dados
        Criteria criteria = sessao.createCriteria(Endereco.class);
        // adicionando a ordenação da pesquisa
        criteria.addOrder(Order.asc("idEndereco"));
        // valorizando o objeto de retorno do método com os registros da tabela
        retorno = (ArrayList<Endereco>) criteria.list();
        // encerrando a conexão com o banco de dados
        sessao.close();
        //retornanado a lista preenchida
        return retorno;
    }

}
