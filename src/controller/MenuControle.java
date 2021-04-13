/**
 * classse controle Menu
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import javax.swing.JOptionPane;
import view.ClienteView;
import view.CompraView;
import view.ContasPagarView;
import view.ContasReceberView;
import view.EstoqueView;
import view.FornecedorView;
import view.FuncionarioView;
import view.MenuView;
import view.ProdutoView;
import view.VendaView;


public  class MenuControle { 
    
    public static MenuView menu;
    
    // construtor vazio
    public MenuControle() {
    }
    
    @SuppressWarnings("static-access")
    public MenuControle (MenuView menu){
        this.menu = menu;
    }
    
    public void btSair(){
        
        
        
         int opcao = JOptionPane.showConfirmDialog(null, "Encerrar o sitema?", "Atenção", 0, 2);

        if (opcao == 0) {
            System.exit(0);
        }
    }//fim btSair
    
    public void btCliente(){
        
        bloquearMenu();
        new ClienteView().setVisible(true);
        
    }
    
    public void btFornecedor(){
        
        bloquearMenu(); 
        new FornecedorView().setVisible(true);
    }
    
    public void btFuncionario(){
        
        bloquearMenu();
        new FuncionarioView().setVisible(true);
        
    }
    
    public void btProduto(){
        
        bloquearMenu();
        new ProdutoView().setVisible(true);
        
    }
    public void btVendas(){
        
        bloquearMenu();
        new VendaView().setVisible(true);
        
    }
    public void btCompras(){
        
        bloquearMenu();
        new CompraView().setVisible(true);
        
    }
    
    public void btContasPagar(){
        
        bloquearMenu();
       new ContasPagarView().setVisible(true);
        
    }
    
    public void btContasReceber(){
        
        bloquearMenu();
       new ContasReceberView().setVisible(true);
        
    }
    public void btEstoque(){
        
        bloquearMenu(); 
        new EstoqueView().setVisible(true);
       
        
    }
    
    
    
    /**
     * metodod para bloquear o menu principal
     */
    
    public static void bloquearMenu(){
        
        menu.getjMenu1().setEnabled(!true);
        menu.getjMenu2().setEnabled(!true);
        menu.getjMenu3().setEnabled(!true);
        menu.getjMenu4().setEnabled(!true);
        menu.getjMenu5().setEnabled(!true);
        menu.getjMenu6().setEnabled(!true);
       
        
        menu.getBtCliente().setEnabled(!true);
        menu.getBtCliente1().setEnabled(!true);
        menu.getBtFuncionario().setEnabled(!true);
        menu.getBtProduto().setEnabled(!true);
        menu.getBtCompras().setEnabled(!true);
        menu.getBtVendas().setEnabled(!true);
        menu.getBtSair().setEnabled(!true);
       
        
    } 
    /**
     * metodod para bloquear o menu principal
     */
    
    public static void desbloquearMenu(){
        
        menu.getjMenu1().setEnabled(true);
        menu.getjMenu2().setEnabled(true);
        menu.getjMenu3().setEnabled(true);
        menu.getjMenu4().setEnabled(true);
        menu.getjMenu5().setEnabled(true);
        menu.getjMenu6().setEnabled(true);
         
        
        menu.getBtCliente().setEnabled(true);
        menu.getBtCliente1().setEnabled(true);
        menu.getBtFuncionario().setEnabled(true);
        menu.getBtProduto().setEnabled(true);
        menu.getBtCompras().setEnabled(true);
        menu.getBtVendas().setEnabled(true);
        menu.getBtSair().setEnabled(true);
        
    } 
    
    /**
     * Metodo para carregar usuario logado
     */
    
    public void carregarUsuario(){
      menu.getjLabelUsuario().setText(LoginController.nomeFuncionario);
    }
}
