/**
 * classse controle confirma venda
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import view.ConfirmaCompraPrazoView;
import view.ConfirmaVendaPrazoView;

/**
 *
 * @author curitiba01
 */
public class ConfirmarVendaController {
    
     private ConfirmaVendaPrazoView tela;

    public ConfirmarVendaController() {
    }
    
    // construtor para valorizar o objeto tela
    public ConfirmarVendaController(ConfirmaVendaPrazoView tela) {
        this.tela = tela;
    }
    
    public void btConfirma(){
        
        tela.dispose();
    }
    
}
