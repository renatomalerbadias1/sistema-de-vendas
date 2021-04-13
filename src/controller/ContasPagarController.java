/**
 * classse controle contas a pagar
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import view.ContasPagarView;
import view.FuncionarioView;

/**
 *
 * @author curitiba01
 */
public class ContasPagarController {
    
    private ContasPagarView tela;

    public ContasPagarController() {
    }
    
    // construtor para valorizar o objeto tela
    public ContasPagarController(ContasPagarView tela) {
        this.tela = tela;
    }
    
    /*
     * método para bloqueio inicial dos objetos
     */
    public void bloqueioInicial() {
        
        // desabilitando os botões
        tela.getjButton1().setEnabled(false);
        tela.getjButton2().setEnabled(false);
        tela.getjButton3().setEnabled(false);
       
        tela.getjFormattedTextField1().setEnabled(false);
        tela.getjFormattedTextField2().setEnabled(false);
        tela.getjRadioButton1().setEnabled(false);
        tela.getjRadioButton2().setEnabled(false);
        tela.getjTable1().setEnabled(false);
    }
    
     public void botaoSair() {
             MenuControle.desbloquearMenu();
            tela.dispose();
        
    }
    
}
