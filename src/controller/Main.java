/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import javax.swing.UIManager;
import view.LoginView;

/**
 *
 * @author curitiba01
 */
public class Main {

      public static void main(String args[]) {

        try {
            AcrylLookAndFeel.setTheme("Default", "aaaaaaa", "EPIC TECHNOLOGY");
            
            /*
            * moldelos de ambiente
            */
            // UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
           //  UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
               UIManager.setLookAndFeel("com.formdev.flatlaf.FlatIntelliJLaf");
           //  UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
           // UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new LoginView().setVisible(true);

            }
        });
    }

   
    
    
}
