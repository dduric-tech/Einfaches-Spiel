package Controller;

import Model.GewinnModel;
import view.GewinnView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GewinnController {
    private final GewinnModel model;
    private final GewinnView view;

    public GewinnController(GewinnModel model, GewinnView view) {
        this.model = model;
        this.view = view;

        // Listener an die GUI-Elemente der View binden
        this.view.addSpielerZahlListener(new RundeSpielenListener());
        this.view.addNochEinmalListener(new NochEinmalListener());
    }

    private class RundeSpielenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String eingabe = view.getSpielerEingabe();
            int zahl;

            // Eingabe überprüfen
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
        }
    }
}