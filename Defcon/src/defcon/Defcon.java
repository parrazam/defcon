/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package defcon;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JDialog;

/**
 *
 * @author Parra
 */
public class Defcon {

    private static Vista v;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JDialog.setDefaultLookAndFeelDecorated(false);
        v = new Vista();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    System.err.println(ex.getMessage());
                }


                // Iniciamos el formulario
                v.run();
            }
        });
    }
}
