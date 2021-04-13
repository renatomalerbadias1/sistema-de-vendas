
package controller;


import dao.PessoaJuridicaDAO;
import javax.swing.JOptionPane;
import model.PessoaJuridica;
import util.Mensagem;

/**
 * classse controle Pessoa  Juridica
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
public class PessoaJuridiaController {
    
    /**
     * metodo para salvar um obj no banco de dados
     */
    public void salvar(PessoaJuridica pessoa){
       
        try {
             new PessoaJuridicaDAO().salvar(pessoa);
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,Mensagem.pessoa_juridica_erro,Mensagem.cadastro_pessoa_juridiaca,0);
        }
        
    }//fim metodo
    
    /**
     * metodo para excluir um objeto
     */
    public void excluir(PessoaJuridica pessoa){
        
        try {
             new PessoaJuridicaDAO().excluir(pessoa);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir Pessoa");
        }
    }
    
}
