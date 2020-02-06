
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;



public class VentanaPrincipal_1 extends javax.swing.JFrame {

    
    private  Conexion jPanelConexion;
    private JPanelChat PanelChat;
    
    public VentanaPrincipal_1() {
        initComponents();
        
        jPanelConexion=new Conexion(this);
        PanelChat=new JPanelChat(this);
    
        reset();
    
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuConexion = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jMenuConexion.setText("Conexion ");
        jMenuConexion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuConexionMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuConexion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 763, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuConexionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuConexionMouseClicked
            
        if(jMenuConexion.getForeground()==Color.GREEN)
        {
            int yes=JOptionPane.showConfirmDialog(this, "¿Desea desconectarse?");
            if(yes==0)
            {
                PersonaChat objetoPersona=jPanelConexion.getNuevoChateador();
                Socket perServ=jPanelConexion.getPersonaServidor();
                objetoPersona.setConectado(false);
                ObjectOutputStream salida;
                try {
                    salida = new ObjectOutputStream(perServ.getOutputStream());
                    salida.writeObject(objetoPersona);
                    System.out.println("Persona desconectada enviada a servidor");
                    salida.close();
                } catch (IOException ex) {
                    System.err.println("Ha ocurrido un error a enviar la persona desconectada al servidor");
                }
                
                reset();
         
                
            }
        }
       
    }//GEN-LAST:event_jMenuConexionMouseClicked

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal_1().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuConexion;
    // End of variables declaration//GEN-END:variables

    
    
    public void cambioDePanel(JPanel nuevo)
    {
        PanelChat.setVisible(false);
        jPanelConexion.setVisible(false);
       
        setContentPane(nuevo);
        nuevo.setVisible(true);
        this.pack();
        
    }

    public JPanelChat getjPanelChat() {
        return PanelChat;
    }
    


    public Conexion getjPanelConexion() {
        return jPanelConexion;
    }
    

    public JMenu getjMenuConexion() {
        return jMenuConexion;
    }

    public void setjMenuConexion(Color color) {
        this.jMenuConexion.setForeground(color);
    }

    public void reset()
    {
        jMenuConexion.setForeground(Color.RED);
        this.cambioDePanel(jPanelConexion);
        jPanelConexion.reset();
        
    }

    
    
}
