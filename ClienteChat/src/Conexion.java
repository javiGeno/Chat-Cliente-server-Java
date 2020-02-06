
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;




public class Conexion extends javax.swing.JPanel {

    private VentanaPrincipal_1 venP;
    private PersonaChat nuevoChateador;
    static final String HOST = "localhost";
    static final int PUERTO = 6000;
    private Socket personaServidor;
    private Conversacion conversacion;
    private ObjectOutputStream flujoSalidaServidor;
    
    public Conexion(VentanaPrincipal_1 venP) {
        initComponents();
        
        this.venP=venP;
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelInicioSesion = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabelUsuario = new javax.swing.JLabel();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(800, 500));

        jLabelInicioSesion.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelInicioSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelInicioSesion.setText("INICIAR CHAT");

        jTextFieldUsuario.setText("postgres");

        jLabelUsuario.setText("Usuario");

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelInicioSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCancelar)
                        .addGap(28, 28, 28)
                        .addComponent(jButtonAceptar)))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabelInicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUsuario))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonAceptar))
                .addContainerGap(183, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /*Cuando pulsa el boton aceptar se genera el objeto persona, el hilo que mantendra actualizado el text area de la conversación*/
    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        
        String usuario=jTextFieldUsuario.getText();
        
        if(!usuario.trim().equals(""))
        {
            venP.cambioDePanel(venP.getjPanelChat());
            //indicamos que esta conectado
            venP.setjMenuConexion(Color.green);
            reset();
             try {
                personaServidor= new Socket(HOST, PUERTO);
                nuevoChateador=new PersonaChat(usuario, null);
                flujoSalidaServidor=new ObjectOutputStream(personaServidor.getOutputStream());
                flujoSalidaServidor.writeObject(nuevoChateador);
                conversacion=new Conversacion(personaServidor, nuevoChateador, venP);
                conversacion.start();
            } catch (IOException ex) {
                System.err.println("No ha podido realizarse la conexión con el servidor");
            }
            
           
         
        }
        else
        {
            JOptionPane.showMessageDialog(venP,"El nombre del usuario debe tener caracteres", "ERROR" , JOptionPane.ERROR_MESSAGE);
        }
       
        
        
        
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
         
               
        reset();
    }//GEN-LAST:event_jButtonCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabelInicioSesion;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables

     public void reset()
    {
        
        jTextFieldUsuario.setText("");
    }

    public VentanaPrincipal_1 getVenP() {
        return venP;
    }

    public PersonaChat getNuevoChateador() {
        return nuevoChateador;
    }

    public static String getHOST() {
        return HOST;
    }

    public static int getPUERTO() {
        return PUERTO;
    }

    public Socket getPersonaServidor() {
        return personaServidor;
    }

    public JButton getjButtonAceptar() {
        return jButtonAceptar;
    }

    public JButton getjButtonCancelar() {
        return jButtonCancelar;
    }

    public JLabel getjLabelInicioSesion() {
        return jLabelInicioSesion;
    }

    public JLabel getjLabelUsuario() {
        return jLabelUsuario;
    }

    public JTextField getjTextFieldUsuario() {
        return jTextFieldUsuario;
    }

    public void setVenP(VentanaPrincipal_1 venP) {
        this.venP = venP;
    }

    public void setNuevoChateador(PersonaChat nuevoChateador) {
        this.nuevoChateador = nuevoChateador;
    }

    public void setPersonaServidor(Socket personaServidor) {
        this.personaServidor = personaServidor;
    }

    public void setjButtonAceptar(JButton jButtonAceptar) {
        this.jButtonAceptar = jButtonAceptar;
    }

    public void setjButtonCancelar(JButton jButtonCancelar) {
        this.jButtonCancelar = jButtonCancelar;
    }

    public void setjLabelInicioSesion(JLabel jLabelInicioSesion) {
        this.jLabelInicioSesion = jLabelInicioSesion;
    }

    public void setjLabelUsuario(JLabel jLabelUsuario) {
        this.jLabelUsuario = jLabelUsuario;
    }

    public void setjTextFieldUsuario(JTextField jTextFieldUsuario) {
        this.jTextFieldUsuario = jTextFieldUsuario;
    }

    public void setConversacion(Conversacion conversacion) {
        this.conversacion = conversacion;
    }

     

}
