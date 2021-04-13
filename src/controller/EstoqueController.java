/**
 * classse controle Estoque
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import model.Estoque;
import view.EstoqueView;


/**
 *
 * @author curitiba01
 */
public class EstoqueController {
    
    // declarando o controlador dos radioButto de Sexo
    private ButtonGroup grpRadio;

    // atributo para manipular tela
    private EstoqueView tela;
    // lista para preencher tabela, alterar e excluir
    private ArrayList<Estoque> listaEstoque;
    // objeto aluno para incluir ou alterar
    private Estoque professor;
    // flag para controlar a opção de incluir ou alterar
    private boolean alterar = false;

    public EstoqueController() {
    }
    
    // construtor para valorizar o objeto tela
    public EstoqueController(EstoqueView tela) {
        this.tela = tela;
    }
    
     /*
     * método para controlar ação do botão NOVO
     */
    public void botaoNovo() {
        
        tela.getBtNovo().setEnabled(false);
        tela.getBtAlterar().setEnabled(false);
        tela.getBtExcluir().setEnabled(false);
        tela.getBtSair().setEnabled(false);
        tela.getBtSalvarEstoque().setEnabled(!false);
        tela.getBtCancelar().setEnabled(!false);
        
        
         tela.getjComboBox1().setEnabled(!false);
         tela.getJtQuntidadeEstoque().setEnabled(!false);
         tela.getJtQuantidadeMinima().setEnabled(!false);
        
        
        
         
    }
    /*
     * método para bloqueio inicial dos objetos
     */
    public void bloqueioInicial() {
        
        tela.getBtNovo().setEnabled(!false);
        tela.getBtAlterar().setEnabled(!false);
        tela.getBtExcluir().setEnabled(!false);
        tela.getBtSair().setEnabled(!false);
         tela.getBtSalvarEstoque().setEnabled(false);
        tela.getBtCancelar().setEnabled(false);
        
         tela.getjComboBox1().setEnabled(false);
         tela.getJtQuntidadeEstoque().setEnabled(false);
         tela.getJtQuantidadeMinima().setEnabled(false);
        
        
    }
    // botao Cancelar
     public void botaoSair() {
             MenuControle.desbloquearMenu();
            tela.dispose();
        
    }
     
      /*
     * método para controlar ação do botão CANCELAR
     */
    public void botaoCancelar() {
         tela.getBtNovo().setEnabled(false);
        tela.getBtAlterar().setEnabled(false);
        tela.getBtExcluir().setEnabled(false);
        tela.getBtSair().setEnabled(false);
        limpaTela();
        bloqueioInicial();
    }
     /*
     * método para limpar os campos da tela
     */
    private void limpaTela() {
        // limpando textField
        for (Component component : tela.getjPanel1().getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(null);
            }
        }
       
    }
    
}//fim da classe
    

