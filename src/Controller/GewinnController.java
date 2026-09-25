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

        this.view.addSpielerZahlListener(new RundeSpielenListener());
        this.view.addNochEinmalListener(new NochEinmalListener());
    }

    private class RundeSpielenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String eingabe = view.getSpielerEingabe();
            int zahl;

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

            model.berechneComputerZahl();
            model.berechneRunde(zahl);

            view.setComputerZahlText(String.valueOf(model.getComputerZahl()));
            view.setPunkteText("Punkte: " + model.getGesamtPunkte());
            view.setErgebnisText("Runde: " + (model.getRundenErgebnis() > 0 ? "+" : "") + model.getRundenErgebnis());

            // Optik: Grün bei Gewinn, Rot bei Verlust
            if (model.getRundenErgebnis() > 0 || model.hatGewonnen()) {
                view.setLabelFarben(Color.GREEN);
            } else if (model.getRundenErgebnis() < 0 || model.hatVerloren()) {
                view.setLabelFarben(Color.RED);
            } else {
                view.setLabelFarben(Color.WHITE);
            }

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
            // Optik: Farben beim Reset wieder auf Weiß
            view.setLabelFarben(Color.WHITE);
        }
    }
}