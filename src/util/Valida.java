
package util;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.ie.IESaoPauloValidator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Classe responsavel por armazenar os metodos de validação do sistema
 * @author Renato Dias
 */
public class Valida {
    
    /**
     * Metodo para verificar se o CNPJ foi preenchido
     */
   public static boolean isCnpjVazio(String args){
       
     return args.equals(Mascara.MASCARA_CNPJ);
   }
   
   /**
     * Metodo para verificar se o CNPJ e Valido
     */
   public static boolean isCnpjInvalido(String args){
       
       CNPJValidator validador = new CNPJValidator();
       
       try {
           validador.assertValid(args);
           return false;
       } catch (Exception e) {
           
           return true;
       }
   }//fim metodo
   
   /**
     * Metodo para verificar se o CPF foi preenchido
     */
   public static boolean isCpfVazio(String args){
       
     return args.equals(Mascara.MASCARA_CPF);
   }//fim metodo
   
   /**
     * Metodo para verificar se o RG foi preenchido
     */
   public static boolean isRgVazio(String args){
       
     return args.equals(Mascara.MASCARA_RG);
   }
       

    
    /**
     * Metodo para verificar se o CPF e Valido
     * @param args
     * @return
     */
    public static boolean isCpfInvalido(String args){
       
       CPFValidator validador = new CPFValidator();
       
       try {
           validador.assertValid(args);
           return false;
       } catch (Exception e) {
           
           return true;
       }
       
   }//fim metodo
   
   /**
     * Metodo para verificar se a inscrição estadua e Preenchido
     */
   public static boolean isInscricaoEstadualVazio(String args){
       
        return args.equals(Mascara.MASCARA_IE);
   }
   
   /**
     * Metodo para verificar se  a Inscrição Estadual  e Valido
     */
   public static boolean isInscricaoEstadualInvalido(String args){
       
       IESaoPauloValidator validador = new IESaoPauloValidator();
               
       
       try {
           validador.assertValid(args);
           return false;
       } catch (Exception e) {
           
           return true;
       }
       
   }//fim metodo
   
   /**
    * 
    * metodo para verificar string ou null
    */
   
   public static boolean isEmptyOrNull(String args){
      return (args.trim().equals("") || args == null);
   }//fim metodo
   
   /**
     * Metodo para verificar se o Data foi preenchido
     */
   public static boolean isDataVazio(String args){
       
     return args.equals(Mascara.MASCARA_DATA);
   }//fim metodo
   
   /**
    * Metodo para validar Data
    */
   public static boolean isDataInvalida(String args){
       
       String formato = "dd/MM/uuuu";
       
       DateTimeFormatter dataTimeFormate = DateTimeFormatter.ofPattern(formato).withResolverStyle(ResolverStyle.STRICT);
               
       try {
           LocalDate date = LocalDate.parse(args,dataTimeFormate);
           return false;
       } catch (Exception e) {
           return true;
       }
          
   }//fim metodo
   
   /**
    * metodo para verificar se é inteiro
    */
   public static boolean isInterger(String args){
       
       try {
           Integer.parseInt(args);
           return true;
       } catch (Exception e) {
           return false;
       }
   }//fim metodo
   
   /**
    * Metodo para validar seleção combo box
    */
   public static boolean isComboInvalida(int index){
       
       return index == 0;
   }
   
   /**
     * Metodo para verificar se o CEP foi preenchido
     */
   public static boolean isCepVazio(String args){
       
     return args.equals(Mascara.MASCARA_CEP);
   }
   
   /**
     * Metodo para verificar se o celularfoi preenchido
     */
   public static boolean isCelularVazio(String args){
       
     return args.equals(Mascara.MASCARA_CELULAR);
   }
   
}
