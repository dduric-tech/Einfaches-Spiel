package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GewinnView extends JFrame {
    private JLabel lblPunkte;
    private JLabel lblErgebnis;
    private JTextField txtSpielerZahl;
    private JTextField txtComputerZahl;
    private JButton btnNochEinmal;

    public GewinnView() {
        setTitle("Zahlen-Gewinnspiel");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Obere Anzeigen
        JPanel pnlOben = new JPanel(new GridLayout(1, 2, 10, 0));
        lblPunkte = new JLabel("Punkte: 30", SwingConstants.CENTER);
        lblErgebnis = new JLabel("Runde: -", SwingConstants.CENTER);

        lblPunkte.setOpaque(true);
        lblErgebnis.setOpaque(true);
        lblPunkte.setBackground(Color.WHITE);
        lblErgebnis.setBackground(Color.WHITE);
        lblPunkte.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblErgebnis.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        pnlOben.add(lblPunkte);
        pnlOben.add(lblErgebnis);
        add(pnlOben, BorderLayout.NORTH);

        // 2. Mitte: Eingabe und Ausgabe
        JPanel pnlMitte = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlMitte.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        pnlMitte.add(new JLabel("Deine Zahl (1-9):"));
        txtSpielerZahl = new JTextField();
        pnlMitte.add(txtSpielerZahl);

        pnlMitte.add(new JLabel("Computer-Zahl:"));
        txtComputerZahl = new JTextField();
        txtComputerZahl.setEditable(false);
        pnlMitte.add(txtComputerZahl);

        add(pnlMitte, BorderLayout.CENTER);

        // 3. Unten: Reset-Button
        btnNochEinmal = new JButton("Noch einmal!");
        add(btnNochEinmal, BorderLayout.SOUTH);
    }

    public void addSpielerZahlListener(ActionListener l) { txtSpielerZahl.addActionListener(l); }
    public void addNochEinmalListener(ActionListener l) { btnNochEinmal.addActionListener(l); }
    public String getSpielerEingabe() { return txtSpielerZahl.getText().trim(); }
    public void setComputerZahlText(String t) { txtComputerZahl.setText(t); }
    public void setPunkteText(String t) { lblPunkte.setText(t); }
    public void setErgebnisText(String t) { lblErgebnis.setText(t); }
    public void setEingabeAktiv(boolean b) { txtSpielerZahl.setEnabled(b); }
    public void setButtonNochEinmalAktiv(boolean b) { btnNochEinmal.setEnabled(b); }
    public void setLabelFarben(Color farbe) {
        lblPunkte.setBackground(farbe);
        lblErgebnis.setBackground(farbe);
    }

    public void felderZuruecksetzen() {
        txtSpielerZahl.setText("");
        txtComputerZahl.setText("");
        lblErgebnis.setText("Runde: -");
        txtSpielerZahl.requestFocus();
    }
}