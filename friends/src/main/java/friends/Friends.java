package friends;

import javax.swing.JFrame;

public class Friends {

    public static void main(String[] args) {
        // Crear una instancia de MainForm y hacerla visible
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainForm form = new MainForm();
                form.setVisible(true);
                form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación cuando se cierra el formulario
            }
        });
    }
}
