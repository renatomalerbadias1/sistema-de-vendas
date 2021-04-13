/**
 * classse controle Estados
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import dao.EstadoDAO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Estado;

/**
 * classe responsavel por controlar os metodos de acesso a base de dados
 * @author 
 */
public class EstadoController {
    
    public ArrayList<Estado> buscarTodos(){
        //Lista auxiliar para retornar no metodo
        ArrayList<Estado> retorno = null;
                
        try {
            retorno = new EstadoDAO().buscarTodos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Erro ao consultar Dados");
            Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return retorno;
    }
    
}
