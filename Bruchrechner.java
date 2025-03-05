import javax.swing.*;

public class Bruchrechner {
    private JTextField zaehler1;
    private JTextField zaehler2;
    private JTextField nenner1;
    private JTextField nenner2;
    private JComboBox comboBox1;
    private JLabel equalsymbol;
    private JTextField zaehlererg;
    private JTextField nennererg;
    private JPanel mainpanel;
    private JLabel filler;
    private JTextArea ______________________TextArea;
    private JTextArea ______________________________TextArea1;
    private JTextArea ______________________________TextArea;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bruchrechner Binder - BB");
        frame.setContentPane(new Bruchrechner().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Fenster zentrieren
        frame.setVisible(true);
    }
}


