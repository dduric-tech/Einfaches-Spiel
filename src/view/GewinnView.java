package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Grafische Benutzeroberflaeche fuer das Spiel.
 *
 * @author Dario Duric
 */
public class GewinnView extends JFrame {

    private final JLabel lblPunkte;
    private final JLabel lblErgebnis;
    private final JTextField txtSpielerZahl;
    private final JTextField txtComputerZahl;
    private final JButton btnNochEinmal;

    public GewinnView() {
        // fenster aufbauen
        setTitle("Zahlen-Gewinnspiel");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // oberer bereich fuer punkte und rundenanzeige
        JPanel pnlOben = new JPanel(new GridLayout(1, 2, 10, 0));
        lblPunkte = new JLabel("Punkte: 30", SwingConstants.CENTER);
        lblErgebnis = new JLabel("Runde: -", SwingConstants.CENTER);

        // weisser hintergrund standardmaessig
        lblPunkte.setOpaque(true);
        lblErgebnis.setOpaque(true);
        lblPunkte.setBackground(Color.WHITE);
        lblErgebnis.setBackground(Color.WHITE);
        lblPunkte.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblErgebnis.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        pnlOben.add(lblPunkte);
        pnlOben.add(lblErgebnis);
        add(pnlOben, BorderLayout.NORTH);

        // felder fuer die zahlen
        JPanel pnlMitte = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlMitte.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        pnlMitte.add(new JLabel("Deine Zahl (1-9):"));
        txtSpielerZahl = new JTextField();
        pnlMitte.add(txtSpielerZahl);

        pnlMitte.add(new JLabel("Computer-Zahl:"));
        txtComputerZahl = new JTextField();
        txtComputerZahl.setEditable(false); // computerfeld sperren
        pnlMitte.add(txtComputerZahl);

        add(pnlMitte, BorderLayout.CENTER);

        // button unten
        btnNochEinmal = new JButton("Noch einmal!");
        add(btnNochEinmal, BorderLayout.SOUTH);
    }

    // listener fuer den controller uebergeben
    public void addSpielerZahlListener(ActionListener l) {
        txtSpielerZahl.addActionListener(l);
    }

    public void addNochEinmalListener(ActionListener l) {
        btnNochEinmal.addActionListener(l);
    }

    public String getSpielerEingabe() {
        return txtSpielerZahl.getText().trim();
    }

    public void setComputerZahlText(String t) {
        txtComputerZahl.setText(t);
    }

    public void setPunkteText(String t) {
        lblPunkte.setText(t);
    }

    public void setErgebnisText(String t) {
        lblErgebnis.setText(t);
    }

    // aktiv / inaktiv schalten fuer versionserweiterung
    public void setEingabeAktiv(boolean b) {
        txtSpielerZahl.setEnabled(b);
    }

    public void setButtonNochEinmalAktiv(boolean b) {
        btnNochEinmal.setEnabled(b);
    }

    public void setLabelFarben(Color farbe) {
        lblPunkte.setBackground(farbe);
        lblErgebnis.setBackground(farbe);
    }

    // leert felder fuer die naechste runde
    public void felderZuruecksetzen() {
        txtSpielerZahl.setText("");
        txtComputerZahl.setText("");
        lblErgebnis.setText("Runde: -");
        txtSpielerZahl.requestFocus();
    }
}