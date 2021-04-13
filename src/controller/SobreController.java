
package controller;

import view.SobreView;

/**
 * classse controle Sobre
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
public class SobreController {
    
    private SobreView tela;

    public SobreController() {
    }
    
    // construtor para valorizar o objeto tela
    public SobreController(SobreView tela) {
        this.tela = tela;
    }
    
    public void btSair(){
        
     tela.dispose();
    }
    
}
