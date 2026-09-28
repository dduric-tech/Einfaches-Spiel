package Controller;

import Model.GewinnModel;
import view.GewinnView;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Verbindet View und Model und steuert den Ablauf.
 *
 * @author Dario Duric
 */
public class GewinnController {
    private final GewinnModel model;
    private final GewinnView view;

    /**
     * Initialisiert den Controller und verknuepft die ActionListener.
     */
    public GewinnController(GewinnModel model, GewinnView view) {
        this.model = model;
        this.view = view;

        this.view.setButtonNochEinmalAktiv(false);
        this.view.addSpielerZahlListener(new RundeSpielenListener());
        this.view.addNochEinmalListener(new NochEinmalListener());
    }

    private class RundeSpielenListener implements ActionListener {
        /**
         * Fuehrt die Spielrunde aus, validiert die Eingabe und aktualisiert die View.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String eingabe = view.getSpielerEingabe();
            int zahl;

            try {
                zahl = Integer.parseInt(eingabe);
                if (zahl < 1 || zahl > 9) {
                    JOptionPane.showMessageDialog(view, "Nur Zahlen zwischen 1 und 9 eingeben", "Hinweis", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Das ist keine gueltige Zahl", "Fehler", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.berechneComputerZahl();
            model.berechneRunde(zahl);

            view.setComputerZahlText(String.valueOf(model.getComputerZahl()));
            view.setPunkteText(String.valueOf(model.getGesamtPunkte()));

            if (model.hatVerloren()) {
                view.setErgebnisText("Verloren");
            } else if (model.hatGewonnen()) {
                view.setErgebnisText("Gewonnen!");
            } else {
                int punkte = model.getRundenErgebnis();
                view.setErgebnisText((punkte > 0 ? "+" : "") + punkte);
            }

            view.setEingabeAktiv(false);
            view.setButtonNochEinmalAktiv(true);

            if (model.getRundenErgebnis() > 0 || model.hatGewonnen()) {
                view.setLabelFarben(Color.GREEN);
            } else if (model.getRundenErgebnis() < 0 || model.hatVerloren()) {
                view.setLabelFarben(Color.RED);
            } else {
                view.setLabelFarben(Color.WHITE);
            }

            if (model.hatGewonnen()) {
                JOptionPane.showMessageDialog(view, "Glueckwunsch du hast 100 Punkte erreicht", "Gewonnen", JOptionPane.INFORMATION_MESSAGE);
            } else if (model.hatVerloren()) {
                JOptionPane.showMessageDialog(view, "Du hast keine Punkte mehr", "Verloren", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private class NochEinmalListener implements ActionListener {
        /**
         * Setzt die UI-Felder und Zustaende fuer die naechste Runde zurueck.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            view.felderZuruecksetzen();
            view.setEingabeAktiv(true);
            view.setButtonNochEinmalAktiv(false);
            view.setLabelFarben(Color.WHITE);
        }
    }
}