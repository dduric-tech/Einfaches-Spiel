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

        // button erst nach dem ersten zug erlauben
        this.view.setButtonNochEinmalAktiv(false);

        // events registrieren
        this.view.addSpielerZahlListener(new RundeSpielenListener());
        this.view.addNochEinmalListener(new NochEinmalListener());
    }

    // reagiert auf enter im eingabefeld
    private class RundeSpielenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String eingabe = view.getSpielerEingabe();
            int zahl;

            // ungueltige eingaben und buchstaben abfangen
            try {
                zahl = Integer.parseInt(eingabe);
                if (zahl < 1 || zahl > 9) {
                    JOptionPane.showMessageDialog(view, "Zahl muss zwischen 1 und 9 liegen!", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Bitte eine Zahl eingeben!", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // runde rechnen
            model.berechneComputerZahl();
            model.berechneRunde(zahl);

            // gui werte updaten
            view.setComputerZahlText(String.valueOf(model.getComputerZahl()));
            view.setPunkteText("Punkte: " + model.getGesamtPunkte());
            view.setErgebnisText("Runde: " + (model.getRundenErgebnis() > 0 ? "+" : "") + model.getRundenErgebnis());

            // textfeld sperren, button freischalten
            view.setEingabeAktiv(false);
            view.setButtonNochEinmalAktiv(true);

            // farbfeedback: gruen bei plus, rot bei minus
            if (model.getRundenErgebnis() > 0 || model.hatGewonnen()) {
                view.setLabelFarben(Color.GREEN);
            } else if (model.getRundenErgebnis() < 0 || model.hatVerloren()) {
                view.setLabelFarben(Color.RED);
            } else {
                view.setLabelFarben(Color.WHITE);
            }

            // popups bei spielende
            if (model.hatGewonnen()) {
                JOptionPane.showMessageDialog(view, "Gewonnen! Du hast mindestens 100 Punkte erreicht.", "Sieg", JOptionPane.INFORMATION_MESSAGE);
            } else if (model.hatVerloren()) {
                JOptionPane.showMessageDialog(view, "Verloren! Punkte sind auf 0 gefallen.", "Niederlage", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // reagiert auf "noch einmal" button
    private class NochEinmalListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.felderZuruecksetzen();
            // alles wieder auf anfang setzen
            view.setEingabeAktiv(true);
            view.setButtonNochEinmalAktiv(false);
            view.setLabelFarben(Color.WHITE);
        }
    }
}