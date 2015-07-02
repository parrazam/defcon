/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package defcon;

import dao.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author Parra
 */
class Vista extends JDialog {

    private JLabel defcon;
    private JLabel indicador;
    private JLayeredPane panel;
    private int frameX;
    private int frameY;
    private int indX;
    private int indY;
    private ImageIcon Defcon;
    private ImageIcon Indicador;
    private Estado estado;
    private Vista frame;

    public Vista() {

        super();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/yo_pixel.png")));
        frame = this;
        this.setUndecorated(true);
        this.getRootPane().setOpaque(false);
        this.getContentPane().setBackground(new Color(0, 0, 0, 0));
        this.setBackground(new Color(0, 0, 0, 0));

        DAO dao = new XmlDAO();
        Datos d = (Datos) dao.Cargar();
        int level = 5;
        if (d != null) {
            level = d.getLevel();
            frameX = d.getX();
            frameY = d.getY();
            this.setLocation(frameX, frameY);
        } else {
            setLocationRelativeTo(null);
        }
        Defcon = new ImageIcon(getClass().getResource("/images/DEFCON.png"));
        Indicador = new ImageIcon(getClass().getResource("/images/Indicador.png"));

        defcon = new JLabel();
        defcon.setIcon(Defcon);
        defcon.setSize(Defcon.getIconWidth(), Defcon.getIconHeight());
        indicador = new JLabel();
        indicador.setIcon(Indicador);
        indicador.setSize(Indicador.getIconWidth(), Indicador.getIconHeight());
        indicador.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/yo_pixel.png")));

        panel = new JLayeredPane();
        defcon.setOpaque(false);
        panel.setOpaque(false);
        this.add(panel);
        panel.setSize(156, 400);
        panel.add(defcon, new Integer(1));
        panel.add(indicador, new Integer(2));
        panel.setOpaque(false);
        this.setSize(156, 400);
        this.setResizable(false);
        estado = new Estado(indicador, level);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /**
         * Listener para coger la posición del JDialog
         */
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                frameX = evt.getX();
                frameY = evt.getY();
            }
        });

        /**
         * Listener para registrar dónde se suelta el JDialog
         */
        this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Point point = MouseInfo.getPointerInfo().getLocation();
                setLocation(point.x - frameX, point.y - frameY);
            }
        });

        /**
         * Listener para registrar el cierre de la aplicación
         */
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                int op = evt.getKeyCode();
                switch (op) {
                    case KeyEvent.VK_5:
                        estado.setEstado(5);
                        break;
                    case KeyEvent.VK_4:
                        estado.setEstado(4);
                        break;
                    case KeyEvent.VK_3:
                        estado.setEstado(3);
                        break;
                    case KeyEvent.VK_2:
                        estado.setEstado(2);
                        break;
                    case KeyEvent.VK_1:
                        estado.setEstado(1);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        Datos d = new Datos(estado.getEstado(), frame.getLocation().x, frame.getLocation().y);
                        DAO dao = new XmlDAO();
                        dao.Guardar(d);
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
        });

        indicador.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                indY = estado.getPosicionEstado().y;
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                try {
                    Point point = frame.getMousePosition().getLocation();
                    if (point.y < frame.getHeight()
                            && point.y > indicador.getHeight() / 2 + 22) {
                        estado.setLocation(10, point.y - indicador.getHeight());
                    } else {
                        throw new Exception();
                    }
                } catch (Exception ex) {
                    estado.setEstado(estado.getEstado());
                }
            }
        });
        indicador.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evt) {
                try {
                    Point point = frame.getMousePosition().getLocation();
                    if (point.y < frame.getHeight() - indicador.getHeight() / 2
                            && point.y > indicador.getHeight() + 22) {
                        indicador.setLocation(10, point.y - indicador.getHeight() / 2);
                    }
                } catch (Exception ex) {
                }
            }
        });

    }

    void run() {
        this.setVisible(true);
    }
}
