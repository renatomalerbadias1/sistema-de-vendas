/**
 * classse controle cliente
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import dao.CidadeDAO;
import dao.ClienteDAO;
import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Cidade;
import model.Cliente;
import model.Contato;
import model.Endereco;
import model.Estado;
import model.PessoaFisica;
import model.PessoaJuridica;
import util.Mensagem;
import util.Pessoa;
import util.Util;
import util.Valida;
import view.ClienteView;

/**
 * Classe para controlar o Cliente
 *
 * @author curitiba01
 */
public class ClienteControler {

    // declarando o controlador dos radioButto de Sexo
    private ButtonGroup grpRadio;

    // atributo para manipular tela
    private ClienteView tela;
    // lista para preencher tabela, alterar e excluir
    private ArrayList<Cliente> listaCliente;

    // flag para controlar a opção de incluir ou alterar
    private boolean alterar = false;

    private ArrayList<Estado> listaEstado;
    private ArrayList<Cidade> listaCidades;
    String tipoCliente;

    // construtor vazio
    public ClienteControler() {
    }

    // construtor para valorizar o objeto tela
    public ClienteControler(ClienteView tela) {
        this.tela = tela;
    }

    private Cliente cliente;
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;
    private Endereco endereco;
    private Contato contato;

    //selecionando o tipo de cliente pessoa fisica ou juridica
    public void botaoNovo() {

        liberarCampos();
        
        if (tela.getjRadioButton1().isSelected()) {
            liberarCampos();
            selecaoDeClientePessoaFisica();
            
        }else{
            liberarCampos();
            selecaoDeClientePessoaJuridica();
        }

    }

    /*
     * método para controlar ação do botão NOVO liberando campos
     */
    public void liberarCampos() {

        alterar = false;

        // desabilitando os botões
        tela.getBtNovo().setEnabled(!false);
        tela.getBtAlterar().setEnabled(false);
        tela.getBtExcluir().setEnabled(false);
        tela.getBtSair().setEnabled(false);
        // habilitando 
        tela.getBtCancelar().setEnabled(!false);
        tela.getBtSalvarCliente().setEnabled(!false);

        // habilitando os textField e jcombo
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

        tela.getjRadioButton1().setEnabled(!false);

        tela.getjRadioButton2().setEnabled(!false);

    }//fim metodo 

    /*
     * método para bloqueio inicial dos objetos
     */
    public void bloqueioInicial() {

        // desabilitando os botões
        
        tela.getjRadioButton1().setSelected(true);
        
        tela.getBtNovo().setEnabled(!false);
        tela.getBtAlterar().setEnabled(!false);
        tela.getBtExcluir().setEnabled(!false);
        tela.getBtSair().setEnabled(!false);
        tela.getBtSalvarCliente().setEnabled(false);
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

        tela.getjRadioButton1().setEnabled(false);
        tela.getjRadioButton2().setEnabled(false);

        tela.getjLabel8().setVisible(false);
        tela.getjLCnpj().setVisible(false);
        tela.getjFormattedTextFieldCNPJ().setVisible(false);
        tela.getjTinscricaoEstadual().setVisible(false);
        tela.getjLIs().setVisible(false);

        tela.getjLDataFundacao().setVisible(false);
        tela.getjTrasaoSocial().setVisible(false);

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
        tela.getButtonGroup1().clearSelection();
        tela.getjComboBox1().setSelectedIndex(0);
        tela.getJcBoxCidade().setSelectedIndex(0);
        // limpando os campos tela
        limpaTela();
        bloqueioInicial();

    }//fim metodo

    //botao Sair
    public void botaoSair() {

        MenuControle.desbloquearMenu();
        tela.dispose();

    }
    // botoa salvar

    public void botaoSalvar() {

        if (validarDados()) {

            if (alterar) {
                //procedimento alterara
                if (tela.getjRadioButton1().isSelected()) {

                    pessoaFisica = cliente.getPessoaFisicaIdPessoaFisica();
                    endereco = cliente.getEnderecoIdEndereco();
                    contato = cliente.getContatoIdContato();
                    new PessoaFisicaController().salvar(pessoaFisica);

                } else {

                    pessoaJuridica = getPessoaJuridica();
                    endereco = cliente.getEnderecoIdEndereco();
                    contato = cliente.getContatoIdContato();
                    new PessoaJuridiaController().salvar(pessoaJuridica);
                }

                endereco = getEndereco();
                contato = getContato();
                cliente = getCliente();

                new EnderecoController().salvar(endereco);
                new ContatoController().salvar(contato);

                try {
                    new ClienteDAO().salvar(cliente);
                    JOptionPane.showMessageDialog(tela, Mensagem.clienteSalvo, Mensagem.cadastro_cliente, 1);
                    botaoCancelar();
                    carragarTabela();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(tela, Mensagem.errocadastroCliente, Mensagem.cadastro_cliente, 0);
                }
// -----------------------------------------------------------------------------------------

            } else {
                //procedimento inclusao

                cliente = new Cliente();
                endereco = new Endereco();
                contato = new Contato();

                if (tela.getjRadioButton1().isSelected()) {

                    pessoaFisica = new PessoaFisica();
                    pessoaFisica = getPessoaFisica();

                } else {

                    pessoaJuridica = new PessoaJuridica();
                    pessoaJuridica = getPessoaJuridica();
                }

                endereco = getEndereco();
                contato = getContato();
                cliente = getCliente();

                if (tela.getjRadioButton1().isSelected()) {
                    new PessoaFisicaController().salvar(pessoaFisica);

                } else {

                    new PessoaJuridiaController().salvar(pessoaJuridica);
                }

                new EnderecoController().salvar(endereco);
                new ContatoController().salvar(contato);

                try {
                    new ClienteDAO().salvar(cliente);
                    JOptionPane.showMessageDialog(tela, Mensagem.clienteSalvo, Mensagem.cadastro_cliente, 1);
                    botaoCancelar();
                    carragarTabela();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(tela, Mensagem.errocadastroCliente, Mensagem.cadastro_cliente, 0);
                }

            }

        }

    }//fim metodo

    //botao excluir
    public void botaoExcluir() {

        //verifica se foi selecionado na tabela
        if (tela.getjTable2().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(tela, Mensagem.selecione_fornecedor, Mensagem.cadastro_fornecedor, 0);

        } else {

            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.desejaExcluir, Mensagem.cadastro_cliente, 2);
            if (opcao == JOptionPane.YES_OPTION) {

                cliente = listaCliente.get(tela.getjTable2().getSelectedRow());

                try {
                    new ClienteDAO().excluir(cliente);
                } catch (Exception ex) {
                    Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
                }

                new ContatoController().excluir(cliente.getContatoIdContato());
                new EnderecoController().excluir(cliente.getEnderecoIdEndereco());
                new PessoaJuridiaController().excluir(cliente.getPessoaJuridicaIdPessoaJuridica());
                new PessoaFisicaController().excluir(cliente.getPessoaFisicaIdPessoaFisica());

                JOptionPane.showMessageDialog(tela, "Cliente excluido com sucesso");
                carragarTabela();
            }
        }

    }

    // botao Alterar
    public void botaoAlterar() {

        alterar = true;

        if (tela.getjTable2().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(tela, "Selecione Cliente", Mensagem.cadastro_cliente, 0);

        } else {
            cliente = listaCliente.get(tela.getjTable2().getSelectedRow());

            bloqueioAlterar();
            carregarTela();

        }

    }//fim metodo

    /**
     * metodo para carregar a combo de estado
     */
    public void carregarComboEstados() {
        listaEstado = new EstadoController().buscarTodos();
        this.tela.getjComboBox1().addItem("-Selecione Estado-");
        for (Estado estado : listaEstado) {
            this.tela.getjComboBox1().addItem(estado.getNome());
        }

    }//fim metodo 

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
     * metodo seleção cliente pessoa juridica
     */
    public void selecaoDeClientePessoaJuridica() {

        limpaTela();
        liberarCampos();

        //Nome
        tela.getTfNome().setEditable(false);

        // cpf
        tela.getJtCpf().setVisible(false);
        tela.getjLabel1().setVisible(false);
        // RG
        tela.getjLRG().setVisible(false);
        tela.getJtRg().setVisible(false);
        // CNPJ
        tela.getjLCnpj().setVisible(!false);
        tela.getjFormattedTextFieldCNPJ().setVisible(!false);
        //INSCRIÇÃO ESTADUAL
        tela.getjTinscricaoEstadual().setVisible(!false);
        tela.getjLIs().setVisible(!false);
        //DATA
        tela.getjLDataFundacao().setVisible(!false);
        tela.getjLabel9().setVisible(false);
        // RAZAO SOCIAL
        tela.getjLRazaoSocial().setVisible(!false);
        tela.getjTrasaoSocial().setVisible(!false);
        tela.getjLabel8().setVisible(!false);

    }//fim metodo

    /**
     * metodo seleção cliente pessoa Fisica
     */
    public void selecaoDeClientePessoaFisica() {

        limpaTela();
        liberarCampos();

        
        //Nome
        tela.getTfNome().setEditable(!false);

        tela.getJtCpf().setVisible(!false);
        tela.getjLabel1().setVisible(!false);

        tela.getJtRg().setVisible(!false);
        tela.getjLRG().setVisible(!false);

        tela.getjLCnpj().setVisible(false);
        tela.getjFormattedTextFieldCNPJ().setVisible(false);
        tela.getjTinscricaoEstadual().setVisible(false);

        tela.getjLIs().setVisible(false);

        tela.getjLDataFundacao().setVisible(false);

        tela.getjLabel9().setVisible(!false);

        // RAZAO SOCIAL
        tela.getjLRazaoSocial().setVisible(false);
        tela.getjTrasaoSocial().setVisible(false);
        tela.getjLabel8().setVisible(false);

    }//fim metodo 

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
    }//fim metodo

    /**
     * Metodo para validar dados
     */
    private boolean validarDados() {

        if (tela.getjRadioButton1().isSelected()) {
            // Validando o cpf
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

        } else {

            //Validando o cnpj
            if (Valida.isCnpjVazio(this.tela.getjFormattedTextFieldCNPJ().getText())) {
                JOptionPane.showMessageDialog(null, Mensagem.cnpjVazio, Mensagem.cadastro_cliente, 0);
                return false;
            } else if (Valida.isCnpjInvalido(this.tela.getjFormattedTextFieldCNPJ().getText())) {
                JOptionPane.showMessageDialog(null, Mensagem.cnpjInvalido, Mensagem.cadastro_cliente, 0);
                return false;
            }

            //validando a inscrição estadual
            if (Valida.isInscricaoEstadualVazio(this.tela.getjTinscricaoEstadual().getText())) {
                JOptionPane.showMessageDialog(null, Mensagem.inscricaoEstadualVazio, Mensagem.cadastro_cliente, 0);
                return false;
            } else if (Valida.isInscricaoEstadualInvalido(this.tela.getjTinscricaoEstadual().getText())) {
                JOptionPane.showMessageDialog(null, Mensagem.inscricaoEstadualInvalido, Mensagem.cadastro_cliente, 0);
                return false;
            }
            //validando a razao social
            if (Valida.isEmptyOrNull(this.tela.getjTrasaoSocial().getText())) {
                JOptionPane.showMessageDialog(null, Mensagem.razaoSocialVazio, Mensagem.cadastro_cliente, 0);
                return false;
            }
            //validando a Data Razao
            if (Valida.isDataVazio(this.tela.getJtDataNascimento().getText())) {
                JOptionPane.showMessageDialog(null, Mensagem.dataRazaoSociaVazio, Mensagem.cadastro_cliente, 0);
                return false;
            } else if (Valida.isDataInvalida(this.tela.getJtDataNascimento().getText())) {
                JOptionPane.showMessageDialog(null, Mensagem.dataInvalido, Mensagem.cadastro_cliente, 0);
                return false;
            }

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
    }//fim metodo

    /**
     * metodopara retornar um novo objeto
     */
    private PessoaFisica getPessoaFisica() {

        pessoaFisica.setCpf(this.tela.getJtCpf().getText());
        pessoaFisica.setNome(this.tela.getTfNome().getText());
        pessoaFisica.setRg(this.tela.getJtRg().getText());
        pessoaFisica.setDataNascimento(this.tela.getJtDataNascimento().getText());

        return pessoaFisica;
    }//fim metodo

    /**
     * metodopara retornar um novo objeto
     */
    private PessoaJuridica getPessoaJuridica() {

        pessoaJuridica.setCnpj(this.tela.getjFormattedTextFieldCNPJ().getText());
        pessoaJuridica.setInscricaoEstadual(this.tela.getjTinscricaoEstadual().getText());
        pessoaJuridica.setRazaoSocial(this.tela.getjTrasaoSocial().getText());
        pessoaJuridica.setDataFundacao(this.tela.getJtDataNascimento().getText());

        return pessoaJuridica;
    }//fim metodo

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
     * metodo para retornar um novo objeto
     *
     */
    private Cliente getCliente() {

        if (tela.getjRadioButton1().isSelected()) {

            cliente.setTipoPessoa(Pessoa.FISICO.getTipo());
            cliente.setPessoaFisicaIdPessoaFisica(pessoaFisica);

        } else {

            cliente.setTipoPessoa(Pessoa.JURIDICO.getTipo());
            cliente.setPessoaJuridicaIdPessoaJuridica(pessoaJuridica);
        }

        cliente.setEnderecoIdEndereco(endereco);
        cliente.setContatoIdContato(contato);

        return cliente;
    }//fim metodo

    /**
     * Metodo responsavel por carregar os cliente cadastrados
     */
   public ArrayList<Cliente> buscarTodos() {

        try {
            return listaCliente = new ClienteDAO().buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(tela, "Erro ao consultar Cliente", "Cadastro Cliente", 1);

        }
        return null;
    }//fim metodo

    /**
     * Metodo para carregar tabela com cliente
     *
     */
    public void carragarTabela() {

        listaCliente = buscarTodos();

        DefaultTableModel modelo = (DefaultTableModel) tela.getjTable2().getModel();

        //limpar tabela
        modelo.setRowCount(0);

        //carregar tabela
        for (Cliente cliente : listaCliente) {

            String nome = "";

            if (cliente.getTipoPessoa().equals(Pessoa.FISICO.getTipo())) {

                nome = cliente.getPessoaFisicaIdPessoaFisica().getNome();

            } else {
                nome = cliente.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial();
            }

            modelo.addRow(new String[]{
                nome,
                cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome(),
                cliente.getContatoIdContato().getCelular(),
                cliente.getContatoIdContato().getEmail()
            });
        }

    }//fim metodo

    /**
     * Metodo para carregar a tela para alterar um cliente
     */
    private void carregarTela() {

        //Se selecionado pessoa fisica
        if (cliente.getTipoPessoa().equals(Pessoa.FISICO.getTipo())) {
            
            tela.getjRadioButton1().setSelected(true);
            selecaoDeClientePessoaFisica();
            tela.getTfNome().setText(cliente.getPessoaFisicaIdPessoaFisica().getNome());
            tela.getJtCpf().setText(cliente.getPessoaFisicaIdPessoaFisica().getCpf());
            tela.getJtRg().setText(cliente.getPessoaFisicaIdPessoaFisica().getRg());
            tela.getJtDataNascimento().setText(cliente.getPessoaFisicaIdPessoaFisica().getDataNascimento());

        } else {

            
            tela.getjRadioButton2().setSelected(true);
            selecaoDeClientePessoaJuridica();
            tela.getjFormattedTextFieldCNPJ().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getCnpj());
            tela.getjTinscricaoEstadual().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getInscricaoEstadual());
            tela.getjTrasaoSocial().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
            tela.getJtDataNascimento().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getDataFundacao());

        }

        tela.getTfEnd().setText(cliente.getEnderecoIdEndereco().getNome());
        tela.getJtNumero().setText(cliente.getEnderecoIdEndereco().getNumero() + "");
        tela.getTfComplemento().setText(cliente.getEnderecoIdEndereco().getComplemento());
        tela.getJtBairro().setText(cliente.getEnderecoIdEndereco().getBairro());
        tela.getjComboBox1().setSelectedItem(cliente.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getNome());
        tela.getJcBoxCidade().setSelectedItem(cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
        tela.getJtCep().setText(cliente.getEnderecoIdEndereco().getCep());
        tela.getJtTelefone().setText(cliente.getContatoIdContato().getTelefone());
        tela.getJtCelular().setText(cliente.getContatoIdContato().getCelular());
        tela.getJtEmail().setText(cliente.getContatoIdContato().getEmail());

    }//fim metodo

    /**
     * metodo que bloqueio os campos na ação alterar
     */
    public void bloqueioAlterar() {

        // desabilitando os botões
        tela.getjRadioButton1().setEnabled(!false);
        tela.getjRadioButton2().setEnabled(!false);

        tela.getBtNovo().setEnabled(false);
        tela.getBtAlterar().setEnabled(!false);
        tela.getBtExcluir().setEnabled(false);
        tela.getBtSair().setEnabled(false);
        tela.getBtCancelar().setEnabled(!false);
        tela.getBtSalvarCliente().setEnabled(!false);

        tela.getjFormattedTextFieldCNPJ().setEditable(false);
        tela.getjTinscricaoEstadual().setEditable(false);
        tela.getjTrasaoSocial().setEditable(false);

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

    }//fim metodo

}//fim classe
