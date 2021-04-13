/**
 * classse controle Funcionario
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import dao.CidadeDAO;

import dao.FuncionarioDAO;
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
import model.Funcionario;
import model.Estado;
import model.PessoaFisica;
import util.Mensagem;
import util.Util;
import util.Valida;
import view.FuncionarioView;

/**
 * Classe para controlar o Funcionario
 *
 * @author curitiba01
 */
public class FuncionarioController {

    // declarando o controlador dos radioButto de Sexo
    private ButtonGroup grpRadioSexo;

    // atributo para manipular tela
    private FuncionarioView tela;
    // lista de professor para preencher tabela, alterar e excluir
    private ArrayList<Funcionario> listaFuncionario;
    // objeto aluno para incluir ou alterar
    private Funcionario professor;
    // flag para controlar a opção de incluir ou alterar
    private boolean alterar = false;

    private ArrayList<Estado> listaEstado;
    private ArrayList<Cidade> listaCidades;

    private ArrayList<Funcionario> listaFuncionarios;

    private Funcionario funcionario;
    private PessoaFisica pessoa;
    private Endereco endereco;
    private Contato contato;

    // construtor vazio
    public FuncionarioController() {
    }

    // construtor para valorizar o objeto tela
    public FuncionarioController(FuncionarioView tela) {
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
        tela.getBtSalvarFuncionario().setEnabled(!false);
        tela.getBtCancelar().setEnabled(!false);

        // habilitando os textField
        tela.getTfNome().setEditable(!false);
        tela.getJtCpf().setEditable(!false);
        tela.getTfEnd().setEditable(!false);

        tela.getjComboBox1().setEnabled(!false);
        tela.getJcBoxCidade().setEnabled(!false);

        tela.getJtCep().setEnabled(!false);
        tela.getTfComplemento().setEnabled(!false);
        tela.getJtCelular().setEnabled(!false);
        tela.getJtNumero().setEnabled(!false);
        tela.getJtBairro().setEnabled(!false);
        tela.getJtRg().setEnabled(!false);
        tela.getJtTelefone().setEnabled(!false);
        tela.getJtEmail().setEnabled(!false);
        tela.getJtDataNascimento().setEnabled(!false);
        tela.getJtSenha().setEnabled(!false);
        tela.getJtLogin().setEnabled(!false);

    }//fim metodo bt novo

    /*
     * método para bloqueio inicial dos objetos
     */
    public void bloqueioInicial() {

        // desabilitando os botões
        tela.getBtNovo().setEnabled(!false);
        tela.getBtAlterar().setEnabled(!false);
        tela.getBtExcluir().setEnabled(!false);
        tela.getBtSair().setEnabled(!false);
        tela.getBtSalvarFuncionario().setEnabled(false);
        tela.getBtCancelar().setEnabled(false);

        // habilitando os textField
        tela.getTfNome().setEditable(false);
        tela.getJtCpf().setEditable(false);
        tela.getTfEnd().setEditable(false);

        tela.getjComboBox1().setEnabled(false);
        tela.getJcBoxCidade().setEnabled(false);

        tela.getJtCep().setEnabled(false);
        tela.getTfComplemento().setEnabled(false);
        tela.getJtCelular().setEnabled(false);
        tela.getJtNumero().setEnabled(false);
        tela.getJtBairro().setEnabled(false);
        tela.getJtRg().setEnabled(false);
        tela.getJtTelefone().setEnabled(false);
        tela.getJtEmail().setEnabled(false);
        tela.getJtDataNascimento().setEnabled(false);
        tela.getJtSenha().setEnabled(false);
        tela.getJtLogin().setEnabled(false);
    }
    /*
     * método para controlar ação do botão CANCELAR
     */

    public void botaoCancelar() {
        // Abilita os botões
        tela.getBtNovo().setEnabled(!false);
        tela.getBtAlterar().setEnabled(!false);
        tela.getBtExcluir().setEnabled(!false);
        tela.getBtSair().setEnabled(!false);
        tela.getjComboBox1().setSelectedIndex(0);
        tela.getJcBoxCidade().setSelectedIndex(0);
        // limpando os campos tela
        limpaTela();
        bloqueioInicial();

    }// botao Cancelar

    public void botaoSair() {

        MenuControle.desbloquearMenu();
        tela.dispose();

    }//fim bt cancelar

    // botoa salvar
    public void botaoSalvar() {

        if (validarDados()) {
            if (alterar) {
                
                pessoa = funcionario.getPessoaFisicaIdPessoaFisica();
                endereco = funcionario.getEnderecoIdEndereco();
                contato = funcionario.getContatoIdContato();
                

            } else {
                funcionario = new Funcionario();
                pessoa = new PessoaFisica();
                endereco = new Endereco();
                contato = new Contato();
            }
            pessoa = getPessoaFisica();
            endereco = getEndereco();
            contato = getContato();

            new PessoaFisicaController().salvar(pessoa);
            new EnderecoController().salvar(endereco);
            new ContatoController().salvar(contato);

            funcionario.setPessoaFisicaIdPessoaFisica(pessoa);
            funcionario.setEnderecoIdEndereco(endereco);
            funcionario.setContatoIdContato(contato);
            funcionario.setSenha(this.tela.getJtSenha().getText());
            funcionario.setLogin(this.tela.getJtLogin().getText());

            try {

                new FuncionarioDAO().salvar(funcionario);
                JOptionPane.showMessageDialog(null, "Funcionario Salvo", "Cadastro de Funcionario", 1);
                limpaTela();
                bloqueioInicial();
                carragarTabela();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao Salvar Funcionario", "Cadastro de Funcionario", 1);
            }

        }

    }//fim metodo salvar

    // botao Alterar
    public void botaoAlterar() {

        alterar = true;
        if (tela.getjTable2().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(tela, "Selecione Funcionario", "Cadastro de Funcionario", 0);

        } else {

            funcionario = listaFuncionarios.get(tela.getjTable2().getSelectedRow());

            bloqueioAlterar();
            carregarTela();

        }

    }//fim metodo

    // botao Escluir
    public void botaoExcluir() {
        
        
        if (tela.getjTable2().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(tela, "Selecione Funcionario", "Cadastro de funcionario", 0);

        } else {

            int opcao = JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir ", "Cadastro de funcionario", 2);
            if (opcao == JOptionPane.YES_OPTION) {

                funcionario = listaFuncionarios.get(tela.getjTable2().getSelectedRow());

                try {
                    new FuncionarioDAO().excluir(funcionario);
                } catch (Exception ex) {
                    Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
                }

                new ContatoController().excluir(funcionario.getContatoIdContato());
                new EnderecoController().excluir(funcionario.getEnderecoIdEndereco());
                new PessoaFisicaController().excluir(funcionario.getPessoaFisicaIdPessoaFisica());

                JOptionPane.showMessageDialog(tela, "fornecedor Excluido com sucesso");

            }
        }

    }
        

    

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

    }//fim limpar tela

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

            this.tela.getJcBoxCidade().removeAllItems();
            this.tela.getJcBoxCidade().addItem("-Selecione Cidade-");

            for (Cidade cidade : listaCidades) {
                this.tela.getJcBoxCidade().addItem(cidade.getNome());

            }
            this.tela.getJcBoxCidade().setEditable(false);
        }
    }

    /**
     * Metodo para validar dados
     */
    private boolean validarDados() {

        //Validando o cpf
        if (Valida.isCpfVazio(this.tela.getJtCpf().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.cpfVazio, Mensagem.cadastro_cliente, 0);
            return false;
        } else if (Valida.isCpfInvalido(this.tela.getJtCpf().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.cpfInvalido, Mensagem.cadastro_cliente, 0);
            return false;
        }

        //Valida Rg
        if (Valida.isRgVazio(this.tela.getJtRg().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.rgVazio, Mensagem.cadastro_cliente, 0);
            return false;
        }
        //Valida Nome
        if (Valida.isEmptyOrNull(this.tela.getTfNome().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.nomeVazio, Mensagem.cadastro_cliente, 0);
            return false;
        }

        //validando a Data Nascimento
        if (Valida.isDataVazio(this.tela.getJtDataNascimento().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.dataNascimaentoVazio, Mensagem.cadastro_cliente, 0);
            return false;
        } else if (Valida.isDataInvalida(this.tela.getJtDataNascimento().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.dataInvalido, Mensagem.cadastro_cliente, 0);
            return false;
        }

        //Valida endereço
        if (Valida.isEmptyOrNull(this.tela.getTfEnd().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.enderecoVazio, Mensagem.cadastro_cliente, 0);
            return false;
        }

        //validando o numero
        if (Valida.isEmptyOrNull(this.tela.getJtNumero().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.numeroVazio, Mensagem.cadastro_cliente, 0);
            return false;
        }
        // validando o bairro
        if (Valida.isEmptyOrNull(this.tela.getJtBairro().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.bairroVazio, Mensagem.cadastro_cliente, 0);
            return false;
        }
        // validando o estado
        if (Valida.isComboInvalida(this.tela.getjComboBox1().getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, Mensagem.estadoVazio, Mensagem.cadastro_cliente, 0);
            return false;
        }
        // validando o cidade
        if (Valida.isComboInvalida(this.tela.getJcBoxCidade().getSelectedIndex())) {
            JOptionPane.showMessageDialog(null, Mensagem.cidadeVazio, Mensagem.cadastro_cliente, 0);
            return false;
        }
        // validando o cep
        if (Valida.isCepVazio(this.tela.getJtCep().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.cepVazio, Mensagem.cadastro_cliente, 0);
            return false;
        }
        // validando o celular
        if (Valida.isCelularVazio(this.tela.getJtCelular().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.celularVazio, Mensagem.cadastro_cliente, 0);
            return false;
        }
        // validando o email
        if (Valida.isEmptyOrNull(this.tela.getJtEmail().getText())) {
            JOptionPane.showMessageDialog(null, Mensagem.emailVazio, Mensagem.cadastro_cliente, 0);
            return false;
        }

        return true;
    }//fim metodo validaincluir

    /**
     * metodo para retornar um Objeto
     */
    private PessoaFisica getPessoaFisica() {

        pessoa.setCpf(this.tela.getJtCpf().getText());
        pessoa.setNome(this.tela.getTfNome().getText());
        pessoa.setRg(this.tela.getJtRg().getText());
        pessoa.setDataNascimento(this.tela.getJtDataNascimento().getText());

        return pessoa;
    }//fim metodo getPessoaFisica

    /**
     * metodopara retornar um novo objeto
     */
    private Endereco getEndereco() {

        endereco.setNome(this.tela.getTfEnd().getText());
        endereco.setNumero(Util.getInterger(this.tela.getJtNumero().getText()));
        endereco.setComplemento(this.tela.getTfComplemento().getText());
        endereco.setBairro(this.tela.getJtBairro().getText());
        endereco.setCep(this.tela.getJtCep().getText());
        endereco.setCidadeIdCidade(listaCidades.get(this.tela.getJcBoxCidade().getSelectedIndex() - 1));

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
     * Metodo responsavel por carregar os funcionarios cadastrados
     */
   public ArrayList<Funcionario> buscarTodos() {

        try {
            return listaFuncionarios = new FuncionarioDAO().buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(tela, "Erro ao consultar funcionario", "Cadastro Funcionario", 1);

        }
        return null;
    }//fim metodo

    /**
     * Metodo para carregar tabela
     *
     */
    public void carragarTabela() {

        listaFuncionario = buscarTodos();

        DefaultTableModel modelo = (DefaultTableModel) tela.getjTable2().getModel();

        //limpar tabela
        modelo.setRowCount(0);

        //carregar tabela
        for (Funcionario funcionario : listaFuncionario) {

            modelo.addRow(new String[]{funcionario.getPessoaFisicaIdPessoaFisica().getNome(),
                funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getNome(),
                funcionario.getContatoIdContato().getTelefone(),
                funcionario.getContatoIdContato().getCelular()});

        }

    }//fim metodo

    /**
     * Metodo para carregar a tela com dados do funcionario
     *
     */
    private void carregarTela() {

        tela.getJtCpf().setText(funcionario.getPessoaFisicaIdPessoaFisica().getCpf());
        tela.getJtRg().setText(funcionario.getPessoaFisicaIdPessoaFisica().getRg());
        tela.getTfNome().setText(funcionario.getPessoaFisicaIdPessoaFisica().getNome());
        tela.getJtDataNascimento().setText(funcionario.getPessoaFisicaIdPessoaFisica().getDataNascimento());
        tela.getTfEnd().setText(funcionario.getEnderecoIdEndereco().getNome());
        tela.getJtNumero().setText(funcionario.getEnderecoIdEndereco().getNumero() + "");
        tela.getTfComplemento().setText(funcionario.getEnderecoIdEndereco().getComplemento());
        tela.getJtBairro().setText(funcionario.getEnderecoIdEndereco().getBairro());
        tela.getjComboBox1().setSelectedItem(funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());
        tela.getJcBoxCidade().setSelectedItem(funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
        tela.getJtCep().setText(funcionario.getEnderecoIdEndereco().getCep());
        tela.getJtTelefone().setText(funcionario.getContatoIdContato().getTelefone());
        tela.getJtCelular().setText(funcionario.getContatoIdContato().getCelular());
        tela.getJtEmail().setText(funcionario.getContatoIdContato().getEmail());
        tela.getJtLogin().setText(funcionario.getLogin());
        tela.getJtSenha().setText(funcionario.getSenha());

    }//fim metodo

    /**
     * metodo que bloqueio os campos na ação alterar
     */
    public void bloqueioAlterar() {
        tela.getBtNovo().setEnabled(false);
        tela.getBtAlterar().setEnabled(false);
        tela.getBtExcluir().setEnabled(false);
        tela.getBtSair().setEnabled(false);
        tela.getBtCancelar().setEnabled(!false);
        tela.getBtSalvarFuncionario().setEnabled(!false);

        // habilitando os textField
        tela.getTfNome().setEditable(false);
        tela.getJtCpf().setEditable(false);
        tela.getTfEnd().setEditable(!false);

        tela.getjComboBox1().setEnabled(!false);
        tela.getJcBoxCidade().setEnabled(!false);

        tela.getJtCep().setEnabled(!false);
        tela.getTfComplemento().setEnabled(!false);
        tela.getJtCelular().setEnabled(!false);
        tela.getJtNumero().setEnabled(!false);
        tela.getJtBairro().setEnabled(!false);
        tela.getJtRg().setEnabled(false);
        tela.getJtTelefone().setEnabled(!false);
        tela.getJtEmail().setEnabled(!false);
        tela.getJtDataNascimento().setEnabled(false);
        tela.getJtSenha().setEnabled(!false);
        tela.getJtLogin().setEnabled(!false);
    }//fim metodo
    
    /*
    *Metodo para retornar lista login
    */
    
   public ArrayList<Funcionario> buscarPorLogin(String login){
       
       try {
           return new FuncionarioDAO().buscarPorLogin(login);
       } catch (Exception e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(tela, "Erro ao consultar funcionario");
       }
       
       return null;
   }
    
    
    

}
