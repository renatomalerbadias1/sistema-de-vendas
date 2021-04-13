/**
 * classse controle contas a receber
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import view.ContasPagarView;
import view.ContasReceberView;
import view.FuncionarioView;

/**
 *
 * @author curitiba01
 */
public class ContasReceberController {
    
    private ContasReceberView tela;

    public ContasReceberController() {
    }
    
    // construtor para valorizar o objeto tela
    public ContasReceberController(ContasReceberView tela) {
        this.tela = tela;
    }
    
    /*
     * método para bloqueio inicial dos objetos
     */
    public void bloqueioInicial() {
        
        // desabilitando os botões
        tela.getBtAlterar().setEnabled(false);
        tela.getBtCancelar().setEnabled(false);
        tela.getBtSalvar().setEnabled(false);
       
        tela.getjFormattedTextField1().setEnabled(false);
        tela.getjFormattedTextField2().setEnabled(false);
        
        tela.getRbNaoPago().setEnabled(false);
        tela.getRbPago().setEnabled(false);
        
        tela.getjPanel1().setEnabled(false);
    }
    
     public void botaoSair() {
             MenuControle.desbloquearMenu();
            tela.dispose();
        
    }
    
}
