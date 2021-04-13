/**
 * classse controle compra prazo
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import view.ConfirmaCompraPrazoView;
import view.ContasPagarView;

/**
 *
 * @author curitiba01
 */
public class ConfirmarCompraPrazoController {
    
    private ConfirmaCompraPrazoView tela;

    public ConfirmarCompraPrazoController() {
    }
    
    // construtor para valorizar o objeto tela
    public ConfirmarCompraPrazoController(ConfirmaCompraPrazoView tela) {
        this.tela = tela;
    }
    
    
     public void btConfirma(){
        
        tela.dispose();
    }
    
    
}
