package dao;

import java.util.ArrayList;
import model.Funcionario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 * Classa para armazenar os métodos para acesso ao bando de dados
 * @author Renato Dias
 * @since Mar 17, 2021
 * @version 1.0
 */
public class FuncionarioDAO extends GenericDAO {
    
     /*
    * método para consultar os funcionários por login
    */
    public ArrayList<Funcionario> buscarPorLogin(String login) throws Exception{
        // lista auxiliar para retornar no método
        ArrayList<Funcionario> retorno = new ArrayList<>();
        // classe auxiliar para armazenar a sessão com o banco de dados
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        // classe auxiliar para consultar o banco de dados
        Criteria criteria = sessao.createCriteria(Funcionario.class);
        // adicionando a restrição de consulta = WHERE
        criteria.add(Restrictions.eq("login", login));
        // adicionando a ordenação da pesquisa
        criteria.addOrder(Order.asc("idFuncionario"));
        // valorizando o objeto de retorno do método com os registros da tabela
        retorno = (ArrayList<Funcionario>) criteria.list();
        // encerrando a conexão com o banco de dados
        sessao.close();
        // retornando a lista preenchida
        return retorno;
    }// fim do método buscarPorLogin
    
    
    
    
    
    /*
    * método para consultar os Funcionario gravados na tabela
    */
    public ArrayList<Funcionario> buscarTodos() throws Exception {
        // lista auxiliar para retorno no método
        ArrayList<Funcionario> retorno = new ArrayList<>();
        // classe auxiliar para armazenar a sessão com o banco de dados
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        // classe auxiliar para consultar o banco de dados
        Criteria criteria = sessao.createCriteria(Funcionario.class);
        // adicionando a ordenação da pesquisa
        criteria.addOrder(Order.asc("idFuncionario"));
        // valorizando o objeto de retorno do método com os registros da tabela
        retorno = (ArrayList<Funcionario>) criteria.list();
        // encerrando a conexão com o banco de dados
        sessao.close();
        //retornanado a lista preenchida
        return retorno;
    }

}
