
package controller;


import dao.ProdutoDAO;
import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Fornecedor;
import model.Produto;

import util.Mensagem;
import util.Valida;

import view.ProdutoView;

/**
 * classse controle Produto
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
public class ProdutoController {

   

    // atributo para manipular tela
    private ProdutoView tela;
      
    // flag para controlar a opção de incluir ou alterar
    private boolean alterar = false;

   
    private Produto produto;

    private ArrayList<Fornecedor> listaFornecedores;
    private ArrayList<Produto> listaProdutos;

    public ProdutoController() {
    }

    // construtor para valorizar o objeto tela
    public ProdutoController(ProdutoView tela) {
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
        tela.getBtCancelar().setEnabled(!false);
        tela.getBtSalvarProduto().setEnabled(!false);

        tela.getJtDescricao().setEnabled(!false);
        tela.getJtValorCusto().setEnabled(!false);
        tela.getJtValorVenda().setEnabled(!false);
        tela.getJcFornecedor().setEnabled(!false);
    }
    /*
     * método para bloqueio inicial dos objetos
     */

    public void bloqueioInicial() {

        tela.getBtNovo().setEnabled(!false);
        tela.getBtAlterar().setEnabled(!false);
        tela.getBtExcluir().setEnabled(!false);
        tela.getBtSair().setEnabled(!false);
        tela.getBtCancelar().setEnabled(false);
        tela.getBtSalvarProduto().setEnabled(false);

        tela.getJtDescricao().setEnabled(false);
        tela.getJtValorCusto().setEnabled(false);
        tela.getJtValorVenda().setEnabled(false);
        tela.getJcFornecedor().setEnabled(false);

    }

    //botao Salvar

    public void botaoSalvar() {

        if (validarDados()) {

            if (alterar) {

            } else {
                produto = new Produto();

            }
            produto = getProduto();

            try {
                new ProdutoDAO().salvar(produto);
                JOptionPane.showMessageDialog(null, Mensagem.produtoSalvo, Mensagem.cadastro_produto, 1);
                limpaTela();
                bloqueioInicial();
                carragarTabela();
            } catch (Exception e) {
            }

        }

    }//fim metodo

    //botao Alterar
    public void botaoAlterar() {

        alterar = true;
        if (tela.getjTable1().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(tela, Mensagem.selecione_produto, Mensagem.cadastro_produto, 0);

        } else {

            produto = listaProdutos.get(tela.getjTable1().getSelectedRow());

            bloqueioAlterar();
            carregarTela();

        }

    }//fim metodo

    //botao Excluir
    public void botaoExcluir() {
        
         if (tela.getjTable1().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(tela, Mensagem.selecione_produto, Mensagem.cadastro_produto, 0);

        } else {

            int opcao = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir ", "Cadastro Produto", 2);
            if (opcao == JOptionPane.YES_OPTION) {

                produto = listaProdutos.get(tela.getjTable1().getSelectedRow());

                try {
                    new ProdutoDAO().excluir(produto);
                } catch (Exception ex) {
                    Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
                }


                JOptionPane.showMessageDialog(tela, "fornecedor Excluido com sucesso");

            }
        }

    }//fim metodo

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
     * Metodo para validar dados
     */
    private boolean validarDados() {

        //validando a descrição
        if (Valida.isEmptyOrNull(this.tela.getJtDescricao().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.descricaoVazio, Mensagem.cadastro_produto, 0);
            return false;
        }
        // validando o fornecedor
        if (Valida.isComboInvalida(this.tela.getJcFornecedor().getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, Mensagem.fornecedorVazio, Mensagem.cadastro_produto, 0);
            return false;
        }
        //validando valor custo
        if (Valida.isEmptyOrNull(this.tela.getJtValorCusto().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.valorCustoVazio, Mensagem.cadastro_produto, 0);
            return false;
        }
        //validando valor venda
        if (Valida.isEmptyOrNull(this.tela.getJtValorVenda().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.valorVendaVazio, Mensagem.cadastro_produto, 0);
            return false;
        }

        return true;
    }//fim metodo

    /**
     * metodo para carregar a combo de fornecedores
     */
    public void carregarComboFornecedores() {

        listaFornecedores = new FornecedorController().buscarTodos();

        this.tela.getJcFornecedor().addItem("-Selecione Fornecedor-");

        for (Fornecedor fornecedor : listaFornecedores) {

            this.tela.getJcFornecedor().addItem(fornecedor.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
        }

    }//fim metodo carregar combo box

    /**
     * metodopara retornar um novo objeto
     */
    private Produto getProduto() {

        produto.setDescricao(this.tela.getJtDescricao().getText());

        produto.setValorCusto(Double.parseDouble(this.tela.getJtValorCusto().getText()));

        produto.setValorVenda(Double.parseDouble(this.tela.getJtValorVenda().getText()));

        produto.setFornecedorIdFornecedor(listaFornecedores.get(this.tela.getJcFornecedor().getSelectedIndex() - 1));

        return produto;
    }//fim metodo

    /**
     * Metodo responsavel para carregar fornecedores cadastrodos no Banco de
     * Dados
     *
     * @return
     */
    public ArrayList<Produto> buscarTodos() {

        try {
            return listaProdutos = new ProdutoDAO().buscarTodos();
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(tela, "", Mensagem.cadastro_produto, 0);

        }
        return null;
    }//fim metodo

    /**
     * 3metodo para carregar jtable de fornecedor
     */
    public void carragarTabela() {

        buscarTodos();

        DefaultTableModel modelo = (DefaultTableModel) tela.getjTable1().getModel();

        //limpar tabela
        modelo.setRowCount(0);

        for (Produto produto : listaProdutos) {

            modelo.addRow(new String[]{produto.getDescricao(),
                produto.getFornecedorIdFornecedor().getPessoaJuridicaIdPessoaJuridica().getRazaoSocial(),
                produto.getValorCusto() + "", produto.getValorVenda() + ""});
        }

    }//fim metodo

    /**
     * Metodo para carregar a tela para alterar um fornecedor
     */
    private void carregarTela() {

        tela.getJtDescricao().setText(produto.getDescricao());
        tela.getJcFornecedor().setSelectedItem(produto.getFornecedorIdFornecedor().getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
        tela.getJtValorCusto().setText(produto.getValorCusto() + "");
        tela.getJtValorVenda().setText(produto.getValorVenda() + "");

    }
    
    /**
     * metodo que bloqueio os campos na ação alterar
     */
    public void bloqueioAlterar() {
        
         tela.getBtNovo().setEnabled(false);
        tela.getBtAlterar().setEnabled(false);
        tela.getBtExcluir().setEnabled(false);
        tela.getBtSair().setEnabled(false);
        tela.getBtCancelar().setEnabled(!false);
        tela.getBtSalvarProduto().setEnabled(!false);

        tela.getJtDescricao().setEnabled(!false);
        tela.getJtValorCusto().setEnabled(!false);
        tela.getJtValorVenda().setEnabled(!false);
        tela.getJcFornecedor().setEnabled(!false);
        
        
    }

}//fim da classe
