import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/* JADX INFO: loaded from: Main.class */
public class Main extends JFrame implements ActionListener {
    private JLabel lblFile;
    private JTextField txtFile;
    private JButton btnOpen;
    private JLabel lblResult;

    public Main() {
        super("Java CrackMe #4 by vhly[FR]");
        setDefaultCloseOperation(3);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        this.lblFile = new JLabel("License File:");
        this.txtFile = new JTextField(20);
        this.btnOpen = new JButton("...");
        this.btnOpen.addActionListener(this);
        jPanel.add(this.lblFile);
        jPanel.add(this.txtFile);
        jPanel.add(this.btnOpen);
        contentPane.add(jPanel, "North");
        this.lblResult = new JLabel("                 UnRegister!");
        contentPane.add(this.lblResult, "South");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        pack();
        setLocation((width / 2) - (((int) getSize().getWidth()) / 2), (height / 2) - (((int) getSize().getHeight()) / 2));
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        JFileChooser jFileChooser = new JFileChooser(new File("."));
        if (jFileChooser.showOpenDialog(this) == 0) {
            this.txtFile.setText(jFileChooser.getSelectedFile().getPath());
            try {
                FileInputStream fileInputStream = new FileInputStream(jFileChooser.getSelectedFile().getPath());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                KeyFile keyFile = (KeyFile) objectInputStream.readObject();
                if (keyFile.isVal()) {
                    this.lblResult.setText(new StringBuffer().append("                 Registed to ").append(keyFile.getName()).toString());
                } else {
                    this.lblResult.setText("                 UnRegisted!");
                }
                objectInputStream.close();
                fileInputStream.close();
            } catch (Exception e) {
                this.lblResult.setText("                 UnRegisted!");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] strArr) throws Exception {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable() { // from class: com.vhly.crackmes.cm4.Main.1
            @Override // java.lang.Runnable
            public void run() {
                new Main();
            }
        });
    }
}