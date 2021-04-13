/**
 * classse controle Login
 * Renato Malerba Dias 
 * 06/04/2021 v 1.0
 * 
 * 
 */
package controller;

import javax.swing.JOptionPane;
import model.Funcionario;
import util.Mensagem;
import util.Valida;
import view.LoginView;
import view.MenuView;


public class LoginController {

    private LoginView loginView;
    public static String nomeFuncionario;

    public LoginController() {
    }

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    public void acaoBotaoConfirmar() {
        if (validarDados()) {
            efetuarLogin();
        } 
    }

    public void acaoBotaoCancelar() {
        System.exit(0);
    }

    private boolean validarDados() {

        //Valida 
        if (Valida.isEmptyOrNull(this.loginView.getjTlog().getText())) {
            JOptionPane.showMessageDialog(null,Mensagem.loginVazio,Mensagem.sitemaVendas,1);
            return false;
        }

        if (Valida.isEmptyOrNull(this.loginView.getJtSenha().getText())) {
            JOptionPane.showMessageDialog(null,Mensagem.senhaVazio,Mensagem.sitemaVendas,1);
            loginView.getJtSenha().grabFocus();
            return false;
        }

        return true;
    }

    private void efetuarLogin() {
        String login = loginView.getjTlog().getText();
        String senha = loginView.getJtSenha().getText();

        boolean achouLogin = false;
        boolean achouSenha = false;

        for (Funcionario funcionario : new FuncionarioController().buscarPorLogin(login)) {
            achouLogin = true;
            if (funcionario.getSenha().equals(senha)) {
                nomeFuncionario = funcionario.getPessoaFisicaIdPessoaFisica().getNome();
                loginView.dispose();
                new MenuView().setVisible(true);
                achouSenha = true;
                break;
            } else {
                achouSenha = false;
            }
        }

        if (achouLogin == false || achouSenha == false) {
            JOptionPane.showMessageDialog(null,Mensagem.credenciaInvalida,Mensagem.sitemaVendas,1);
        }
    }

}//fim classe
