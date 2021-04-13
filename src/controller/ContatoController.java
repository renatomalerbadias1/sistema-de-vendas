/**
 * classse controle contatato
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import dao.ContatoDAO;
import javax.swing.JOptionPane;
import model.Contato;

import util.Mensagem;

/**
 * responsavela por armazenar os metodos de base de dados
 * @author curitiba01
 */
public class ContatoController {
    
    /**
     * metodo para salvar um obj no banco de dados
     */
    public void salvar(Contato contato){
       
        try {
             new ContatoDAO().salvar(contato);
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,Mensagem.contato_erro,Mensagem.cadastro_contato,1);
        }
        
    }//fim metodo
    
    /**
     * metodo para excluir um objeto
     */
    public void excluir(Contato contato){
        
        try {
             new ContatoDAO().excluir(contato);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir contato");
        }
    }
    
}
