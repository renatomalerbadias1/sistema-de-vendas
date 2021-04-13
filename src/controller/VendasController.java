
package controller;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTextField;
import model.Cliente;
import model.Funcionario;
import util.Pessoa;
import view.VendaView;

/**
 * classse controle Vendas
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
public class VendasController {
    
   
    // atributo para manipular tela
    private VendaView tela;
    
    // flag para controlar a opção de incluir ou alterar
    private boolean alterar = false;
     
    private ArrayList<Funcionario> listaFuncionarios;
    private ArrayList<Cliente> listaCliente;
    

    public VendasController() {
    }
    
    // construtor para valorizar o objeto tela
    public VendasController(VendaView tela) {
        this.tela = tela;
    }
    
     
    
    /*
     * método para bloqueio inicial dos objetos
     */
    public void bloqueioInicial() {
        
        tela.getBtExcluirProduto().setEnabled(false);
        tela.getBtIncluir().setEnabled(false);
        tela.getBtExcluir().setEnabled(false);
        tela.getBtSalvarProduto().setEnabled(false);
        tela.getBtCancelar().setEnabled(false);
        tela.getBtIniciarVenda().setEnabled(false);
        tela.getJcSelecioneCliente().setEnabled(false);
        tela.getJcSelecioneFuncionario().setEnabled(false);
        tela.getjComboBox3().setEnabled(false);
        tela.getjComboBox4().setEnabled(false);
        tela.getJtQuantidade().setEnabled(false);
        tela.getJtDesconto().setEnabled(false);
        tela.getjTextField3().setEnabled(false);
        
        
        
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
       
    }//fim metodo
    
     /**
     * metodo para carregar a combo de funcionario
     */
    public void carregarComboFuncionarios() {

        listaFuncionarios = new FuncionarioController().buscarTodos();

        this.tela.getJcSelecioneFuncionario().addItem("-Selecione Funcionario-");

        for (Funcionario funcionario : listaFuncionarios) {

            this.tela.getJcSelecioneFuncionario().addItem(funcionario.getPessoaFisicaIdPessoaFisica().getNome());
        }

    }//fim metodo carregar combo box
    
     /**
     * metodo para carregar a combo de cliente
     */
    public void carregarComboCliente() {

        listaCliente= new ClienteControler().buscarTodos();

        this.tela.getJcSelecioneCliente().addItem("-Selecione Cliente-");

        for (Cliente cliente : listaCliente) {

            this.tela.getJcSelecioneCliente().addItem(Pessoa.FISICO.getTipo());

    }//fim metodo carregar combo box
    
    }
    
}
