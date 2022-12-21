import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class TextScanner extends JFrame {

    static int width = 250, height = 90;
    static String str = "";
    static PrintWriter pw;

    public TextScanner() throws FileNotFoundException, UnsupportedEncodingException {
        super("TextScanner");
        createGUI();
    }

    public void createGUI() throws FileNotFoundException, UnsupportedEncodingException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setFocusable(true);
        pw = new PrintWriter("out.txt", "UTF-8");
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                str += e.getKeyChar();
                pw.write(str);
            }
        });
        setPreferredSize(new Dimension(width, height));
        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                TextScanner frame = null;
                try {
                    frame = new TextScanner();
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                            pw.close();
                        }
                    });
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
