package friends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;

public class MainForm extends javax.swing.JFrame {

    public MainForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        txtName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumber = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name");
        jLabel2.setText("Number");

        btnCreate.setText("Create");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRead.setText("Read");
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        // Layout de los componentes en el formulario
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtName)
                            .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRead)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnClear)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnRead)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnClear))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    // Método para crear un contacto
    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String newName = txtName.getText();
            long newNumber = Long.parseLong(txtNumber.getText());
            String nameNumberString;
            String name;
            long number;

            File file = new File("friendsContact.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            // Recorre el archivo para verificar si el contacto ya existe
            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                nameNumberString = newName + "!" + newNumber;
                raf.writeBytes(nameNumberString);
                raf.writeBytes(System.lineSeparator());
                JOptionPane.showMessageDialog(this, "Contact created successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Contact already exists.");
            }
            raf.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // Método para leer un contacto
    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String newName = txtName.getText();
            String nameNumberString;
            String name;
            long number;

            File file = new File("friendsContact.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName)) {
                    found = true;
                    txtName.setText(name);
                    txtNumber.setText(String.valueOf(number));
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "Contact not found.");
            }

            raf.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // Método para actualizar un contacto
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String newName = txtName.getText();
            long newNumber = Long.parseLong(txtNumber.getText());
            String nameNumberString;
            String name;
            long number;

            File file = new File("friendsContact.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            File tmpFile = new File("temp.txt");
            RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName)) {
                    found = true;
                    nameNumberString = newName + "!" + newNumber;
                }

                tmpraf.writeBytes(nameNumberString);
                tmpraf.writeBytes(System.lineSeparator());
            }

            if (found) {
                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    nameNumberString = tmpraf.readLine();
                    raf.writeBytes(nameNumberString);
                    raf.writeBytes(System.lineSeparator());
                }

                raf.setLength(tmpraf.length());
                tmpFile.delete();
                JOptionPane.showMessageDialog(this, "Contact updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Contact not found.");
            }

            raf.close();
            tmpraf.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // Método para eliminar un contacto
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String newName = txtName.getText();
            String nameNumberString;
            String name;
            long number;

            File file = new File("friendsContact.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            File tmpFile = new File("temp.txt");
            RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                // Si el nombre no coincide, se copia la línea en el archivo temporal
                if (!name.equals(newName)) {
                    tmpraf.writeBytes(nameNumberString);
                    tmpraf.writeBytes(System.lineSeparator());
                } else {
                    found = true;
                }
            }

            if (found) {
                // Reescribe el archivo original desde el temporal
                raf.setLength(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    nameNumberString = tmpraf.readLine();
                    raf.writeBytes(nameNumberString);
                    raf.writeBytes(System.lineSeparator());
                }

                JOptionPane.showMessageDialog(this, "Contact deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Contact not found.");
            }

            tmpraf.close();
            raf.close();
            tmpFile.delete();  // Elimina el archivo temporal
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // Método para limpiar los campos de texto
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        txtName.setText("");
        txtNumber.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - no modificar
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnClear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumber;
    // Fin de la declaración de variables
}
            
