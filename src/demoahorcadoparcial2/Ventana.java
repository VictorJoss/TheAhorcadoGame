/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package demoahorcadoparcial2;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class Ventana extends javax.swing.JFrame {

    private static final String[] PALABRAS_FACIL = {"manzana", "perro", "casa", "sol", "gato", "arbol", "flor"};
    private static final String[] PALABRAS_MEDIO = {"computadora", "libro", "avion", "ciudad", "musica", "familia", "escuela"};
    private static final String[] PALABRAS_DIFICIL = {"extraordinario", "independencia", "interesante", "maravilloso", "desarrollo", "experiencia", "complicado"};

    private JuegoAhorcado juego;
    int cantidadPista = 0;
    ObservableIntentos observable = new ObservableIntentos();
    ObservadorIntentos observador = () -> {
        JOptionPane.showMessageDialog(null, "Se ha realizado un nuevo intento. Intentos totales: " + juego.getIntentos());
    };

    /**
     * Creates new form Vista
     */
    public Ventana() {
        initComponents();
        this.setLocationRelativeTo(null);
        jPanel2.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        juego = JuegoAhorcado.getInstancia();
    }

    private void iniciarJuego() {
        Dificultad dificultad = null;
        String selectedDificultad = cmbLevels.getSelectedItem().toString();

        switch (selectedDificultad) {
            case "Facil":
                dificultad = new DificultadFacil();
                break;
            case "Medio":
                dificultad = new DificultadMedio();
                break;
            case "Dificil":
                dificultad = new DificultadDificil();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida. Se establecerá la dificultad fácil por defecto.");
                dificultad = new DificultadFacil();
                break;
        }

        String[] palabrasSecretas = obtenerPalabrasSecretas(dificultad);

        Random random = new Random();
        String palabraSecreta = palabrasSecretas[random.nextInt(palabrasSecretas.length)];

        cantidadPista = dificultad.obtenerCantidadPista();

        juego.iniciarJuego(palabraSecreta, cantidadPista);
        actualizarPalabraActual();
        
        
    

    observable.agregarObservador (observador);
    }

    

    private void realizarIntento() {
        String letraStr = txtLetra.getText().toLowerCase();
        if (letraStr.length() != 1) {
            JOptionPane.showMessageDialog(null, "Ingrese una única letra.");
            return;
        }

        char letra = letraStr.charAt(0);

        boolean acierto = juego.realizarIntento(letra);
        actualizarPalabraActual();
         

        if (juego.juegoTerminado()) {
            finalizarJuego();
        } else if (!acierto) {
            observable.notificarIntentoRealizado();
            JOptionPane.showMessageDialog(null, "Letra incorrecta.");
        }

        txtLetra.setText("");
        txtLetra.requestFocus();
    }

    private void usarPista() {
        if (juego.hayPistasDisponibles()) {
            juego.usarPista();
            actualizarPalabraActual();
            cantidadPista--;
            JOptionPane.showMessageDialog(null, "Pista: " + juego.getPalabraActual() + "\nPistas restantes: " + cantidadPista);

        } else {
            JOptionPane.showMessageDialog(null, "Ya no hay pistas disponibles o no quedan letras por revelar.");
        }
    }

    private void actualizarPalabraActual() {
        lblPalabraActual.setText("Palabra actual: " + juego.getPalabraActual());
    }

    private void finalizarJuego() {
        if (juego.getPalabraActual().equals(juego.getPalabraSecreta())) {
            JOptionPane.showMessageDialog(null, "¡Felicidades! Has ganado.");
        } else {
            JOptionPane.showMessageDialog(null, "¡Has perdido! La palabra era: " + juego.getPalabraSecreta());
        }

        reiniciarJuego();
    }

    private void reiniciarJuego() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas jugar de nuevo?", "Reiniciar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            //iniciarJuego();
            juego.setPistasUtilizadas(0);
            observable.eliminarObservador(observador);
            jPanel3.setVisible(true);
            jPanel2.setVisible(false);
            
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        } else {
            System.exit(0);
        }
    }

    public static String[] obtenerPalabrasSecretas(Dificultad dificultad) {
        String[] palabras;
        if (dificultad instanceof DificultadFacil) {
            palabras = PALABRAS_FACIL;
        } else if (dificultad instanceof DificultadMedio) {
            palabras = PALABRAS_MEDIO;
        } else {
            palabras = PALABRAS_DIFICIL;
        }
        return palabras;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnIntentar = new javax.swing.JButton();
        btnPista = new javax.swing.JButton();
        txtLetra = new javax.swing.JTextField();
        lblPalabraActual = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cmbLevels = new javax.swing.JComboBox<>();
        btnComenzar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setBackground(new java.awt.Color(255, 255, 255));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Ingrese una letra:");

        btnIntentar.setBackground(new java.awt.Color(153, 153, 153));
        btnIntentar.setForeground(new java.awt.Color(0, 0, 0));
        btnIntentar.setText("Intentar");
        btnIntentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIntentarActionPerformed(evt);
            }
        });

        btnPista.setBackground(new java.awt.Color(153, 153, 153));
        btnPista.setForeground(new java.awt.Color(0, 0, 0));
        btnPista.setText("Pista");
        btnPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPistaActionPerformed(evt);
            }
        });

        lblPalabraActual.setForeground(new java.awt.Color(0, 0, 0));
        lblPalabraActual.setText("Palabra actual:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(lblPalabraActual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLetra, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIntentar)
                .addGap(18, 18, 18)
                .addComponent(btnPista)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPalabraActual)
                    .addComponent(jLabel3)
                    .addComponent(btnIntentar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPista, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLetra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 580, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        cmbLevels.setBackground(new java.awt.Color(204, 204, 204));
        cmbLevels.setForeground(new java.awt.Color(0, 0, 0));
        cmbLevels.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Facil", "Medio", "Dificil" }));

        btnComenzar.setBackground(new java.awt.Color(153, 255, 204));
        btnComenzar.setForeground(new java.awt.Color(0, 0, 0));
        btnComenzar.setText("COMENZAR");
        btnComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComenzarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(cmbLevels, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btnComenzar, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLevels, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComenzar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        panel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Bienvenido(a) a THE AHORCADO GAME");
        panel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Para continuar, seleccione una dificultad:");
        panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Intenta adivinar la palabra secreta");
        panel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("NOTA: Pierdes si no aciertas 6 veces.  ");
        panel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIntentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIntentarActionPerformed
        realizarIntento();
    }//GEN-LAST:event_btnIntentarActionPerformed

    private void btnPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPistaActionPerformed
        usarPista();
    }//GEN-LAST:event_btnPistaActionPerformed

    private void btnComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComenzarActionPerformed
        iniciarJuego();
        jPanel3.setVisible(false);
        jPanel2.setVisible(true);
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);

    }//GEN-LAST:event_btnComenzarActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComenzar;
    private javax.swing.JButton btnIntentar;
    private javax.swing.JButton btnPista;
    private javax.swing.JComboBox<String> cmbLevels;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblPalabraActual;
    private javax.swing.JPanel panel1;
    private javax.swing.JTextField txtLetra;
    // End of variables declaration//GEN-END:variables
}
