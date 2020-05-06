/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author migu_
 */
public class frmMain extends javax.swing.JFrame {
    static controladorMonitor contMon = new controladorMonitor();
    static controladorProductor contProd = new controladorProductor();
    static controladorConsumidor contJ1 = new controladorConsumidor();
    static controladorConsumidor contJ2 = new controladorConsumidor();
    //private final Productor generador = new Productor();
    static int numero = 0;
    static JLabel lnum;
    
    /**
     * Creates new form frmMain
     */
    public frmMain() {
        initComponents();
        this.setLocationRelativeTo(null); // para centrar el frame
        
        contJ1.estado = lblEstado1;
        contJ1.contador = lblContador1;
        contJ2.estado = lblEstado2;
        contJ2.contador = lblContador2;
        lnum = lblNumero;
        contProd.start();
        contJ1.start();
        contJ2.start();
//        generador.start();
//        Consumidor jugador1, jugador2;
//        jugador1 = new Consumidor();
//        jugador1.estado = lblEstado1;
//        jugador1.contador = lblContador1;
//        jugador2 = new Consumidor();
//        jugador2.estado = lblEstado2;
//        jugador2.contador = lblContador2;
//        jugador1.start();
//        jugador2.start();
    }
//    
//    private class Productor extends Thread{
//        @Override
//        public void run(){
//            while(true){
//                while(numero!=0)
//                    System.out.println("Esperando...");
//                try {
//                    Thread.sleep(2000); // 2 segundos para generar un número
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                numero = 1;
//                lblNumero.setText(String.valueOf(numero));
//                System.out.println("Acabo de producir: " + numero);
//            }
//        }
//    }
//    
//    private class Consumidor extends Thread{
//        private int limite=0, numeroAtrapado=0;
//        private JLabel estado, contador;
//        
//        @Override
//        public void run(){
//            while(true){
//                while(numero==0)
//                    System.out.println("Esperando...");
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                numeroAtrapado = numero;
//                numero = 0;
//                lblNumero.setText("null");
//                aumentar();
//            }
//        }
//        
//        private void aumentar(){
//            limite+= numeroAtrapado;
//            contador.setText(String.valueOf(limite));
//            numeroAtrapado = 0;
//        }
//    }
    static class controladorProductor extends Thread{
        public void run(){
            while(true){
                contMon.producirNumero();
            }
        }
    }
    static class controladorConsumidor extends Thread{
        public JLabel estado, contador;
        public int limite1=0, numeroAtrapado1=0;
        public void run(){
            while(true){
                contMon.consumirNumero(this, estado, contador);
            }
        }
    }
    static class controladorMonitor extends Thread{
        public synchronized void producirNumero(){
            if(numero == 0){
                try {
                    Thread.sleep(2000); // 2 segundos para generar un número
                } catch (InterruptedException ex) {
                    Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
                }
                numero = 1;
                lnum.setText(String.valueOf(numero));
                System.out.println("Acabo de producir: " + numero);
                notifyAll();
            }else { desactivar(); System.out.println("Esperando...");}
        }
        public synchronized void consumirNumero(controladorConsumidor yo, JLabel estado, JLabel contador){
            while(yo.limite1==15){
                estado.setText("Duermiendo");
            }
            if(numero==0){
                    System.out.println("Esperando...");
                    estado.setText("Esperando");
                    notify();
                    desactivar();}
            else{
                estado.setText("Jugando");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(numero);
                System.out.println(numero);
                yo.numeroAtrapado1 = numero;
                numero = 0;
                System.out.println(yo.numeroAtrapado1);
                lnum.setText("null");

                yo.limite1+= yo.numeroAtrapado1;
                contador.setText(String.valueOf(yo.limite1));
                yo.numeroAtrapado1 = 0;
                if(yo.limite1 == 15){
                    estado.setText("Duermiendo");
                }else{
                estado.setText("Esperando");}
               
                desactivar();
                notify();}
        }
        private void desactivar()
        {
            try{
                Thread.sleep(100);
                wait(); // Duerme al proceso en turno
            }
            catch(InterruptedException exc){};
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblContador1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblContador2 = new javax.swing.JLabel();
        lblEstado1 = new javax.swing.JLabel();
        lblEstado2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Evaluación final - Sistemas Operativos");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Generador:");

        lblNumero.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblNumero.setText("null");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Jugador 1");

        lblContador1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblContador1.setText("0");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Jugador 2");

        lblContador2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblContador2.setText("0");

        lblEstado1.setText("Esperando");

        lblEstado2.setText("Esperando");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(lblContador1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblContador2)
                        .addGap(93, 93, 93))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(lblNumero)))))
                .addContainerGap(125, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(lblEstado1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEstado2)
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumero)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblContador1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblContador2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstado1)
                    .addComponent(lblEstado2))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new frmMain().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblContador1;
    private javax.swing.JLabel lblContador2;
    private javax.swing.JLabel lblEstado1;
    private javax.swing.JLabel lblEstado2;
    private javax.swing.JLabel lblNumero;
    // End of variables declaration//GEN-END:variables
}
