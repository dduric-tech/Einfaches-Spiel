package Controller;

import Model.GewinnModel;
import view.GewinnView;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GewinnController {
    private final GewinnModel model;
    private final GewinnView view;

    public GewinnController(GewinnModel model, GewinnView view) {
        this.model = model;
        this.view = view;

        // Aus Version 1.1: Reset-Button zu Spielbeginn deaktivieren
        this.view.setButtonNochEinmalAktiv(false);

        this.view.addSpielerZahlListener(new RundeSpielenListener());
        this.view.addNochEinmalListener(new NochEinmalListener());
    }

    private class RundeSpielenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String eingabe = view.getSpielerEingabe();
            int zahl;

            // Absicherung gegen ungültige Eingaben
            try {
                zahl = Integer.parseInt(eingabe);
                if (zahl < 1 || zahl > 9) {
                    JOptionPane.showMessageDialog(view, "Bitte eine Zahl von 1 bis 9 eingeben!", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Ungültige Eingabe! Bitte eine Zahl eingeben.", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Spiellogik aufrufen
            model.berechneComputerZahl();
            model.berechneRunde(zahl);

            // GUI aktualisieren
            view.setComputerZahlText(String.valueOf(model.getComputerZahl()));
            view.setPunkteText("Punkte: " + model.getGesamtPunkte());
            view.setErgebnisText("Runde: " + (model.getRundenErgebnis() > 0 ? "+" : "") + model.getRundenErgebnis());

            // --- AUFLÖSUNG DER BEIDEN BRANCHES ---
            // Aus Version 1.1: Eingabefeld nach Zug sperren, Reset-Button freigeben
            view.setEingabeAktiv(false);
            view.setButtonNochEinmalAktiv(true);

            // Aus Version 2.0: Farbliche Rückmeldung der Labels
            if (model.getRundenErgebnis() > 0 || model.hatGewonnen()) {
                view.setLabelFarben(Color.GREEN);
            } else if (model.getRundenErgebnis() < 0 || model.hatVerloren()) {
                view.setLabelFarben(Color.RED);
            } else {
                view.setLabelFarben(Color.WHITE);
            }

            // Spielende prüfen
            if (model.hatGewonnen()) {
                JOptionPane.showMessageDialog(view, "Herzlichen Glückwunsch! Du hast gewonnen!", "Spiel gewonnen", JOptionPane.INFORMATION_MESSAGE);
            } else if (model.hatVerloren()) {
                JOptionPane.showMessageDialog(view, "Schade! Dein Punktestand ist auf 0 gefallen.", "Spiel verloren", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class NochEinmalListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.felderZuruecksetzen();
            // Beide Features zurücksetzen:
            view.setEingabeAktiv(true);           // Textfeld wieder entsperren
            view.setButtonNochEinmalAktiv(false); // Reset-Button wieder sperren
            view.setLabelFarben(Color.WHITE);     // Farben wieder auf Standard Weiß
        }
    }
}