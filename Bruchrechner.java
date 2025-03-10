import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bruchrechner {
    private JTextField zaehler1;
    private JTextField zaehler2;
    private JTextField nenner1;
    private JTextField nenner2;
    private JComboBox<String> comboBox1;
    private JLabel equalsymbol;
    private JTextField zaehlererg;
    private JTextField nennererg;
    private JPanel mainpanel;
    private JLabel filler;
    private JTextArea ______________________TextArea;
    private JTextArea ______________________________TextArea1;
    private JTextArea ______________________________TextArea;

    public Bruchrechner() {
        // Sobald die ComboBox geändert wird, neu berechnen
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                berechneBruch();
            }
        });

        // DocumentListener für alle Textfelder, damit bei jeder Eingabe neu berechnet wird
        addDocumentListener(zaehler1);
        addDocumentListener(nenner1);
        addDocumentListener(zaehler2);
        addDocumentListener(nenner2);
    }

    /**
     * Fügt einem JTextField einen DocumentListener hinzu,
     * der bei jeder Änderung im Textfeld die Berechnung aufruft.
     */
    private void addDocumentListener(JTextField textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                berechneBruch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                berechneBruch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Wird bei Plain-Text-Feldern meist nicht aufgerufen,
                // aber wir rufen sicherheitshalber ebenfalls berechneBruch() auf.
                berechneBruch();
            }
        });
    }

    /**
     * Liest die Eingaben aus den Textfeldern aus, führt die Berechnung
     * basierend auf der ausgewählten Operation durch und schreibt
     * das Ergebnis in zaehlererg und nennererg.
     */
    private void berechneBruch() {
        // Wenn noch nichts oder Ungültiges eingegeben ist, wollen wir keine Fehlermeldung
        // bei jedem Tastendruck. Stattdessen könnten wir das Ergebnis einfach löschen.
        // Wer lieber direkt eine Fehlermeldung möchte, kann das Handling entsprechend anpassen.
        try {
            int z1 = Integer.parseInt(zaehler1.getText());
            int n1 = Integer.parseInt(nenner1.getText());
            int z2 = Integer.parseInt(zaehler2.getText());
            int n2 = Integer.parseInt(nenner2.getText());

            if (n1 == 0 || n2 == 0) {
                // Kein Ergebnis, da Nenner 0
                zaehlererg.setText("?");
                nennererg.setText("?");
                return;
            }

            String op = (String) comboBox1.getSelectedItem();
            int zErg = 0;
            int nErg = 1;

            switch (op) {
                case "+":
                    zErg = z1 * n2 + z2 * n1;
                    nErg = n1 * n2;
                    break;
                case "-":
                    zErg = z1 * n2 - z2 * n1;
                    nErg = n1 * n2;
                    break;
                case "*":
                    zErg = z1 * z2;
                    nErg = n1 * n2;
                    break;
                case "/":
                    if (z2 == 0) {
                        zaehlererg.setText("?");
                        nennererg.setText("?");
                        return;
                    }
                    zErg = z1 * n2;
                    nErg = n1 * z2;
                    break;
            }

            // Bruch ggf. kürzen
            int gcd = berechneGGT(zErg, nErg);
            zErg /= gcd;
            nErg /= gcd;

            // Minuszeichen ggf. nach vorne ziehen
            if (nErg < 0) {
                nErg = -nErg;
                zErg = -zErg;
            }

            zaehlererg.setText(String.valueOf(zErg));
            nennererg.setText(String.valueOf(nErg));

        } catch (NumberFormatException e) {
            // Falls die Eingabe noch nicht vollständig oder ungültig ist,
            // kann man hier das Ergebnis leeren oder "?" anzeigen:
            zaehlererg.setText("?");
            nennererg.setText("?");
        }
    }

    /**
     * Berechnet den größten gemeinsamen Teiler (GGT) mit dem euklidischen Algorithmus.
     */
    private int berechneGGT(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return (a == 0) ? 1 : a;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bruchrechner Binder - BB");
        frame.setContentPane(new Bruchrechner().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Fenster zentrieren
        frame.setVisible(true);
    }
}
