/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 * classe Util para manipular tipo de pessoa
 * @author Renato Dias
 */
public enum Pessoa {
   
    FISICO("F","Pessoa Fisica"),
    JURIDICO("J","Pessoa Juridica");
    
    private String tipo;
    private String descricao;
    
    Pessoa(String tipo , String descricao){
        
        this.tipo = tipo;
        
        this.descricao = descricao;
        
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
