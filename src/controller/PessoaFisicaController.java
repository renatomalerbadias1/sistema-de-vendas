
package controller;


import dao.PessoaFisicaDAO;
import javax.swing.JOptionPane;
import model.PessoaFisica;

/**
 * classse controle Pessoa Fisica
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
public class PessoaFisicaController {
    
    /**
     * metodo para salvar um obj no banco de dados
     */
    public void salvar(PessoaFisica pessoa){
       
        try {
             new PessoaFisicaDAO().salvar(pessoa);
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"Erro ","Cadastro de cliente",0);
        }
        
    }//fim metodo
    
    /**
     * metodo para excluir um objeto
     */
    public void excluir(PessoaFisica pessoa){
        
        try {
             new PessoaFisicaDAO().excluir(pessoa);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir endereço");
        }
    }//fim metodo
    
    
    
}
