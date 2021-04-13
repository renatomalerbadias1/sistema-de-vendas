/**
 * classse controle compra
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
import model.Compra;
import model.Estoque;
import model.Fornecedor;
import model.Funcionario;
import view.CompraView;
import view.EstoqueView;

/**
 *
 * @author curitiba01
 */
public class CompraController {

    // declarando o controlador dos radioButto de Sexo
    private ButtonGroup grpRadio;

    // atributo para manipular tela
    private CompraView tela;
    // lista para preencher tabela, alterar e excluir
    private ArrayList<Compra> listaCompra;
    // objeto aluno para incluir ou alterar
    private Compra professor;
    // flag para controlar a opção de incluir ou alterar
    private boolean alterar = false;
    
    private ArrayList<Fornecedor> listaFornecedores;
    private ArrayList<Funcionario> listaFuncionarios;

    public CompraController() {
    }

    // construtor para valorizar o objeto tela
    public CompraController(CompraView tela) {
        this.tela = tela;
    }

    /*
     * método para bloqueio inicial dos objetos
     */
    public void bloqueioInicial() {

        tela.getBtSalvarProduto().setEnabled(false);
        tela.getBtExcluirProduto().setEnabled(false);
        tela.getBtIncluirFormaPagamento().setEnabled(false);
        tela.getBtExcluirPagamento().setEnabled(false);
        tela.getBtCancelar().setEnabled(false);
        tela.getBtIniciarCompra().setEnabled(false);

        tela.getjComboBoxFuncionario().setEnabled(false);
        tela.getjComboBoxFornecedor().setEnabled(false);
        tela.getjComboBoxProduto().setEnabled(false);
        tela.getJcFormaPagamento().setEnabled(false);

        tela.getJtQuantidade().setEnabled(false);
        tela.getjTextField1().setEnabled(false);
        tela.getjTable1().setEnabled(false);

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
     * metodo para carregar a combo de fornecedores
     */
    public void carregarComboFornecedores() {

        listaFornecedores = new FornecedorController().buscarTodos();

        this.tela.getjComboBoxFornecedor().addItem("-Selecione Fornecedor-");

        for (Fornecedor fornecedor : listaFornecedores) {

            this.tela.getjComboBoxFornecedor().addItem(fornecedor.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
        }

    }//fim metodo carregar combo box
    
    /**
     * metodo para carregar a combo de fornecedores
     */
    public void carregarComboFuncionarios() {

        listaFuncionarios = new FuncionarioController().buscarTodos();

        this.tela.getjComboBoxFuncionario().addItem("-Selecione Funcionario-");

        for (Funcionario funcionario : listaFuncionarios) {

            this.tela.getjComboBoxFuncionario().addItem(funcionario.getPessoaFisicaIdPessoaFisica().getNome());
        }

    }//fim metodo carregar combo box

}
