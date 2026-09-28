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

    private final JLabel lblRundenergebnis;
    private final JLabel lblGesamtpunkte;
    private final JTextField txtSpielerZahl;
    private final JTextField txtComputerZahl;
    private final JButton btnNochEinmal;

    /**
     * Erstellt das Spielfenster und positioniert alle Komponenten.
     */
    public GewinnView() {
        setTitle("Zahlen-Gewinnspiel (v1.0)");
        setSize(480, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        Font labelFont = new Font("SansSerif", Font.PLAIN, 12);
        Font statusFont = new Font("SansSerif", Font.BOLD, 14);
        Font bigNumberFont = new Font("SansSerif", Font.PLAIN, 28);

        JPanel pnlOben = new JPanel(new GridLayout(2, 2, 10, 2));

        JLabel titleRunde = new JLabel("Rundenergebnis:", SwingConstants.CENTER);
        JLabel titlePunkte = new JLabel("Gesamtpunkte:", SwingConstants.CENTER);
        titleRunde.setFont(labelFont);
        titlePunkte.setFont(labelFont);

        lblRundenergebnis = new JLabel("Tippe eine Zahl von 1 bis 9", SwingConstants.CENTER);
        lblGesamtpunkte = new JLabel("Gesamtpunkte: 30", SwingConstants.CENTER);
        lblRundenergebnis.setFont(statusFont);
        lblGesamtpunkte.setFont(statusFont);

        lblRundenergebnis.setOpaque(true);
        lblGesamtpunkte.setOpaque(true);
        lblRundenergebnis.setBackground(Color.WHITE);
        lblGesamtpunkte.setBackground(Color.WHITE);
        lblRundenergebnis.setPreferredSize(new Dimension(200, 26));
        lblGesamtpunkte.setPreferredSize(new Dimension(200, 26));

        pnlOben.add(titleRunde);
        pnlOben.add(titlePunkte);
        pnlOben.add(lblRundenergebnis);
        pnlOben.add(lblGesamtpunkte);

        JPanel pnlMitte = new JPanel(new GridLayout(2, 2, 10, 2));
        pnlMitte.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        JLabel lblDeineZahl = new JLabel("Deine Zahl:", SwingConstants.CENTER);
        JLabel lblCompZahl = new JLabel("Computer:", SwingConstants.CENTER);
        lblDeineZahl.setFont(labelFont);
        lblCompZahl.setFont(labelFont);

        txtSpielerZahl = new JTextField();
        txtComputerZahl = new JTextField();

        txtSpielerZahl.setFont(bigNumberFont);
        txtComputerZahl.setFont(bigNumberFont);
        txtSpielerZahl.setHorizontalAlignment(JTextField.CENTER);
        txtComputerZahl.setHorizontalAlignment(JTextField.CENTER);
        txtComputerZahl.setEditable(false);
        txtComputerZahl.setBackground(Color.WHITE);

        pnlMitte.add(lblDeineZahl);
        pnlMitte.add(lblCompZahl);
        pnlMitte.add(txtSpielerZahl);
        pnlMitte.add(txtComputerZahl);

        JPanel pnlUnten = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnNochEinmal = new JButton("Noch einmal!");
        pnlUnten.add(btnNochEinmal);

        mainPanel.add(pnlOben);
        mainPanel.add(pnlMitte);
        mainPanel.add(pnlUnten);
        add(mainPanel);
    }

    /**
     * Registriert einen Listener fuer das Eingabefeld.
     */
    public void addSpielerZahlListener(ActionListener l) {
        txtSpielerZahl.addActionListener(l);
    }

    /**
     * Registriert einen Listener fuer den Reset-Button.
     */
    public void addNochEinmalListener(ActionListener l) {
        btnNochEinmal.addActionListener(l);
    }

    /**
     * Liest die Benutzereingabe aus.
     */
    public String getSpielerEingabe() {
        return txtSpielerZahl.getText().trim();
    }

    /**
     * Schreibt die Computerzahl in das vorgesehene Textfeld.
     */
    public void setComputerZahlText(String t) {
        txtComputerZahl.setText(t);
    }

    /**
     * Aktualisiert den Text der Rundenanzeige.
     */
    public void setErgebnisText(String t) {
        lblRundenergebnis.setText(t);
    }

    /**
     * Aktualisiert den Text der Punkteanzeige.
     */
    public void setPunkteText(String t) {
        lblGesamtpunkte.setText(t);
    }

    /**
     * Aktiviert oder deaktiviert das Spieler-Eingabefeld.
     */
    public void setEingabeAktiv(boolean b) {
        txtSpielerZahl.setEnabled(b);
    }

    /**
     * Aktiviert oder deaktiviert den Button Noch einmal.
     */
    public void setButtonNochEinmalAktiv(boolean b) {
        btnNochEinmal.setEnabled(b);
    }

    /**
     * Setzt die Hintergrundfarbe der oberen Info-Labels.
     */
    public void setLabelFarben(Color farbe) {
        lblRundenergebnis.setBackground(farbe);
        lblGesamtpunkte.setBackground(farbe);
    }

    /**
     * Leert die Textfelder und setzt den Standardtext fuer die naechste Runde.
     */
    public void felderZuruecksetzen() {
        txtSpielerZahl.setText("");
        txtComputerZahl.setText("");
        lblRundenergebnis.setText("Tippe eine Zahl von 1 bis 9");
        txtSpielerZahl.requestFocus();
    }
}