/**
 * classse controle Fornecedor
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import dao.CidadeDAO;
import dao.FornecedorDAO;
import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Cidade;
import model.Contato;
import model.Endereco;
import model.Estado;
import model.Fornecedor;
import model.PessoaJuridica;
import util.Mensagem;
import util.Util;
import util.Valida;
import view.FornecedorView;

/**
 *
 * @author curitiba01
 */
public class FornecedorController {

    // atributo para manipular tela
    private FornecedorView tela;
    // lista de professor para preencher tabela, alterar e excluir
    private ArrayList<Fornecedor> listaFornecedor;
    @SuppressWarnings("FieldMayBeFinal")

    private ArrayList<Estado> listaEstado;
    private ArrayList<Cidade> listaCidades;
    private ArrayList<Fornecedor> listaFornecedores;

    private Fornecedor fornecedor;
    private PessoaJuridica pessoa;
    private Endereco endereco;
    private Contato contato;

    private boolean alterar;

    // construtor vazio
    public FornecedorController() {
        this.alterar = false;
    }

    // construtor para valorizar o objeto tela
    public FornecedorController(FornecedorView tela) {
        this.alterar = false;
        this.tela = tela;
    }

    /*
     * método para controlar ação do botão NOVO
     */
    public void botaoNovo() {

        alterar = false;

        // desabilitando os botões
        tela.getBtNovo().setEnabled(false);
        tela.getBtAlterar().setEnabled(false);
        tela.getBtExcluir().setEnabled(false);
        tela.getBtSair().setEnabled(false);
        tela.getBtCancelar().setEnabled(!false);

        // habilitando os textField
        tela.getBtSalvarFornecedor().setEnabled(!false);
        tela.getJtCnpj().setEditable(!false);
        tela.getJtRazaoSocial().setEditable(!false);
        tela.getJtEnd().setEditable(!false);
        tela.getjComboBox1().setEnabled(!false);
        tela.getJtCep().setEnabled(!false);
        tela.getJtCelular().setEnabled(!false);
        tela.getJtContato().setEnabled(!false);
        tela.getJtInscricaoEstadual().setEnabled(!false);
        tela.getJtDataFundacao().setEnabled(!false);
        tela.getJtNumero().setEnabled(!false);
        tela.getJtBairro().setEnabled(!false);
        tela.getJtCidade().setEnabled(!false);
        tela.getJtTelefone().setEnabled(!false);
        tela.getJtEmail().setEnabled(!false);
        tela.getJtComplemento().setEnabled(!false);

    }//fim metodo botão novo

    public void acaoBotaoAlterar() {

        alterar = true;
        if (tela.getjTable2().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(tela, Mensagem.selecione_fornecedor, Mensagem.cadastro_fornecedor, 0);

        } else {

            fornecedor = listaFornecedores.get(tela.getjTable2().getSelectedRow());

            bloqueioAlterar();
            carregarTela();

        }
    }

    public void acaoBotaoExcluir() {

        if (tela.getjTable2().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(tela, Mensagem.selecione_fornecedor, Mensagem.cadastro_fornecedor, 0);

        } else {

            int opcao = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir ", "Cadastro Fornecedor", 2);
            if (opcao == JOptionPane.YES_OPTION) {

                fornecedor = listaFornecedores.get(tela.getjTable2().getSelectedRow());

                try {
                    new FornecedorDAO().excluir(fornecedor);
                } catch (Exception ex) {
                    Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
                }

                new ContatoController().excluir(fornecedor.getContatoIdContato());
                new EnderecoController().excluir(fornecedor.getEnderecoIdEndereco());
                new PessoaJuridiaController().excluir(fornecedor.getPessoaJuridicaIdPessoaJuridica());

                JOptionPane.showMessageDialog(tela, "fornecedor Excluido com sucesso");

            }
        }

    }

    /*
     * método para controlar ação do botão CANCELAR
     */
    public void botaoCancelar() {
        // Abilita os botões
        tela.getBtNovo().setEnabled(!false);
        tela.getBtAlterar().setEnabled(false);
        tela.getBtExcluir().setEnabled(false);
        tela.getBtSair().setEnabled(!false);
        tela.getjComboBox1().setSelectedIndex(0);
        tela.getJtCidade().setSelectedIndex(0);
        // limpando os campos tela
        limpaTela();
        bloqueioInicial();

    }// botao Cancelar

    public void acaoBotaoSalvar() {

        if (validarDados()) {

            if (alterar) {
                pessoa = fornecedor.getPessoaJuridicaIdPessoaJuridica();
                endereco = fornecedor.getEnderecoIdEndereco();
                contato = fornecedor.getContatoIdContato();

            } else {

                fornecedor = new Fornecedor();
                pessoa = new PessoaJuridica();
                endereco = new Endereco();
                contato = new Contato();
            }
            pessoa = getPessoaJuridica();
            endereco = getEndereco();
            contato = getContato();

            new PessoaJuridiaController().salvar(pessoa);
            new EnderecoController().salvar(endereco);
            new ContatoController().salvar(contato);

            fornecedor.setContato(this.tela.getJtContato().getText());
            fornecedor.setPessoaJuridicaIdPessoaJuridica(pessoa);
            fornecedor.setEnderecoIdEndereco(endereco);
            fornecedor.setContatoIdContato(contato);

            try {
                new FornecedorDAO().salvar(fornecedor);
                JOptionPane.showMessageDialog(null, Mensagem.fornecedor_salvo, Mensagem.cadastro_fornecedor, 1);
                limpaTela();
                bloqueioInicial();
                carragarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, Mensagem.fornecedor_erro, Mensagem.cadastro_fornecedor, 1);
            }

        }
    }//fim metodo

    /*
     * método para controlar ação do botão SAIR
     */
    public void botaoSair() {

        MenuControle.desbloquearMenu();
        tela.dispose();
    }

    /*
     * método para bloqueio inicial dos objetos
     */
    public void bloqueioInicial() {

        // desabilitando os botões
        tela.getBtNovo().setEnabled(!false);
        tela.getBtAlterar().setEnabled(!false);
        tela.getBtExcluir().setEnabled(!false);
        tela.getBtSair().setEnabled(!false);
        tela.getBtCancelar().setEnabled(false);

        // habilitando os textField
        tela.getJtCnpj().setEditable(false);
        tela.getJtRazaoSocial().setEditable(false);
        tela.getJtEnd().setEditable(false);
        tela.getjComboBox1().setEnabled(false);
        tela.getJtCep().setEnabled(false);
        tela.getJtCelular().setEnabled(false);
        tela.getJtContato().setEnabled(false);
        tela.getJtInscricaoEstadual().setEnabled(false);
        tela.getJtDataFundacao().setEnabled(false);
        tela.getJtNumero().setEnabled(false);
        tela.getJtBairro().setEnabled(false);
        tela.getJtCidade().setEnabled(false);
        tela.getJtTelefone().setEnabled(false);
        tela.getJtEmail().setEnabled(false);
        tela.getJtComplemento().setEnabled(false);

        tela.getBtSalvarFornecedor().setEnabled(false);

    }// metodo bloqueioicial

    /**
     * metodo para carregar a combo de estado
     */
    public void carregarComboEstados() {
        
        listaEstado = new EstadoController().buscarTodos();
        this.tela.getjComboBox1().addItem("-Selecione Estado-");
        for (Estado estado : listaEstado) {
            this.tela.getjComboBox1().addItem(estado.getNome());
        }

    }//fim metodo carregar combo box

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

    }//fim limpar tla

    /**
     * metodo carregar combo cidade
     */
    public void carregarComboCidade() {

        int indice = this.tela.getjComboBox1().getSelectedIndex() - 1;
        if (indice >= 0) {
            try {
                listaCidades = new CidadeDAO().buscarPorEstado(listaEstado.get(indice));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao consultar cidade");
                Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.tela.getJtCidade().removeAllItems();
            this.tela.getJtCidade().addItem("-Selecione Cidade-");

            for (Cidade cidade : listaCidades) {
                this.tela.getJtCidade().addItem(cidade.getNome());

            }
            this.tela.getJtCidade().setEditable(false);
        }
    }//fim metodo

    /**
     * Metodo para validar dados
     */
    private boolean validarDados() {

        //Validando o cnpj
        if (Valida.isCnpjVazio(this.tela.getJtCnpj().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.cnpjVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        } else if (Valida.isCnpjInvalido(this.tela.getJtCnpj().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.cnpjInvalido, Mensagem.cadastro_fornecedor, 0);
            return false;
        }

        //validando a inscrição estadual
        if (Valida.isInscricaoEstadualVazio(this.tela.getJtInscricaoEstadual().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.inscricaoEstadualVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        } else if (Valida.isInscricaoEstadualInvalido(this.tela.getJtInscricaoEstadual().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.inscricaoEstadualInvalido, Mensagem.cadastro_fornecedor, 0);
            return false;
        }
        //validando a razao social
        if (Valida.isEmptyOrNull(this.tela.getJtRazaoSocial().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.razaoSocialVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        }

        //validando a Data Fundação
        if (Valida.isDataVazio(this.tela.getJtDataFundacao().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.dataFundacaoVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        } else if (Valida.isDataInvalida(this.tela.getJtDataFundacao().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.dataInvalido, Mensagem.cadastro_fornecedor, 0);
            return false;
        }

        //validando o Endereço
        if (Valida.isEmptyOrNull(this.tela.getJtEnd().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.enderecoVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        }

        //validando o numero
        if (Valida.isEmptyOrNull(this.tela.getJtNumero().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.numeroVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        }

        // validando o bairro
        if (Valida.isEmptyOrNull(this.tela.getJtBairro().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.bairroVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        }

        // validando o estado
        if (Valida.isComboInvalida(this.tela.getjComboBox1().getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, Mensagem.estadoVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        }
        // validando o cidade
        if (Valida.isComboInvalida(this.tela.getJtCidade().getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, Mensagem.cidadeVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        }

        // validando o cep
        if (Valida.isCepVazio(this.tela.getJtCep().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.cepVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        }
        // validando o celular
        if (Valida.isCelularVazio(this.tela.getJtCelular().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.celularVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        }

        // validando o email
        if (Valida.isEmptyOrNull(this.tela.getJtEmail().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.emailVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        }
        // validando o contato
        if (Valida.isEmptyOrNull(this.tela.getJtContato().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.contatoVazio, Mensagem.cadastro_fornecedor, 0);
            return false;
        }
        return true;
    }//fim metodo validaincluir

    /**
     * metodopara retornar um novo objeto
     */
    private PessoaJuridica getPessoaJuridica() {

        pessoa.setCnpj(this.tela.getJtCnpj().getText());
        pessoa.setInscricaoEstadual(this.tela.getJtInscricaoEstadual().getText());
        pessoa.setRazaoSocial(this.tela.getJtRazaoSocial().getText());
        pessoa.setDataFundacao(this.tela.getJtDataFundacao().getText());
        return pessoa;
    }//fim metodo

    /**
     * metodopara retornar um novo objeto
     */
    private Endereco getEndereco() {

        endereco.setNome(this.tela.getJtEnd().getText());
        endereco.setNumero(Util.getInterger(this.tela.getJtNumero().getText()));
        endereco.setComplemento(this.tela.getJtComplemento().getText());
        endereco.setBairro(this.tela.getJtBairro().getText());
        endereco.setCep(this.tela.getJtCep().getText());
        endereco.setCidadeIdCidade(listaCidades.get(this.tela.getJtCidade().getSelectedIndex() - 1));

        return endereco;
    }//fim metodo

    /**
     * metodopara retornar um novo objeto
     */
    private Contato getContato() {

        contato.setTelefone(this.tela.getJtTelefone().getText());
        contato.setCelular(this.tela.getJtCelular().getText());
        contato.setEmail(this.tela.getJtEmail().getText());

        return contato;
    }//fim metodo

    /**
     * Metodo responsavel para carregar fornecedores cadastrodos no Banco de
     * Dados
     *
     * @return
     */
   public ArrayList<Fornecedor> buscarTodos() {

        try {
            return listaFornecedores = new FornecedorDAO().buscarTodos();
        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(tela, Mensagem.fornecedor_erro_consulta, Mensagem.cadastro_fornecedor, 0);

        }
        return null;
    }//fim metodo

    /**
     * 3metodo para carregar jtable de fornecedor
     */
    public void carragarTabela() {

        buscarTodos();

        DefaultTableModel modelo = (DefaultTableModel) tela.getjTable2().getModel();

        //limpar tabela
        modelo.setRowCount(0);

        for (Fornecedor fornecedor : listaFornecedores) {

            modelo.addRow(new String[]{fornecedor.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial(),
                fornecedor.getContatoIdContato().getTelefone(),
                fornecedor.getContato(),
                fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getNome()});
        }

    }//fim metodo

    /**
     * Metodo para carregar a tela para alterar um fornecedor
     */
    private void carregarTela() {

        tela.getJtCnpj().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getCnpj());
        tela.getJtInscricaoEstadual().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getInscricaoEstadual());
        tela.getJtRazaoSocial().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
        tela.getJtDataFundacao().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getDataFundacao());
        tela.getJtEnd().setText(fornecedor.getEnderecoIdEndereco().getNome());
        tela.getJtNumero().setText(fornecedor.getEnderecoIdEndereco().getNumero() + "");
        tela.getJtComplemento().setText(fornecedor.getEnderecoIdEndereco().getComplemento());
        tela.getJtBairro().setText(fornecedor.getEnderecoIdEndereco().getBairro());
        tela.getjComboBox1().setSelectedItem(fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());
        tela.getJtCidade().setSelectedItem(fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
        tela.getJtCep().setText(fornecedor.getEnderecoIdEndereco().getCep());
        tela.getJtTelefone().setText(fornecedor.getContatoIdContato().getTelefone());
        tela.getJtCelular().setText(fornecedor.getContatoIdContato().getCelular());
        tela.getJtEmail().setText(fornecedor.getContatoIdContato().getEmail());
        tela.getJtContato().setText(fornecedor.getContato());

    }//fim metodo

    /**
     * metodo que bloqueio os campos na ação alterar
     */
    public void bloqueioAlterar() {

        // desabilitando os botões
        tela.getBtNovo().setEnabled(false);
        tela.getBtAlterar().setEnabled(false);
        tela.getBtExcluir().setEnabled(false);
        tela.getBtSair().setEnabled(false);
        tela.getBtCancelar().setEnabled(!false);
        tela.getBtSalvarFornecedor().setEnabled(!false);

        tela.getJtCnpj().setEditable(false);
        tela.getJtInscricaoEstadual().setEnabled(false);
        tela.getJtDataFundacao().setEnabled(false);

        tela.getJtRazaoSocial().setEditable(!false);
        tela.getJtEnd().setEditable(!false);
        tela.getjComboBox1().setEnabled(!false);
        tela.getJtCep().setEnabled(!false);
        tela.getJtCelular().setEnabled(!false);
        tela.getJtContato().setEnabled(!false);
        tela.getJtNumero().setEnabled(!false);
        tela.getJtBairro().setEnabled(!false);
        tela.getJtCidade().setEnabled(!false);
        tela.getJtTelefone().setEnabled(!false);
        tela.getJtEmail().setEnabled(!false);
        tela.getJtComplemento().setEnabled(!false);

    }

}
