/**
 * classse controle Endereço Renato Malerba Dias 06/04/2021 v 1.0
 *
 *
 */
package controller;

import dao.EnderecoDAO;
import javax.swing.JOptionPane;
import model.Endereco;
import util.Mensagem;

/**
 * responsavela por armazenar os metodos de base de dados
 *
 * @author curitiba01
 */
public class EnderecoController {

    /**
     * metodo para salvar um obj no banco de dados
     * @param endereco
     */
    public void salvar(Endereco endereco) {

        try {
            new EnderecoDAO().salvar(endereco);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Mensagem.endereco_erro, Mensagem.cadastro_endereco, 1);
        }

    }//fim metodo

    /**
     * metodo para excluir um objeto
     * @param endereco
     */
    public void excluir(Endereco endereco) {

        try {
            new EnderecoDAO().excluir(endereco);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao excluir endereço");
        }
    }

}
