package Model;

/**
 * Logik und Datenhaltung für das Zahlen-Gewinnspiel.
 *
 * @author Dario Duric
 */
public class GewinnModel {
    private int gesamtPunkte;
    private int spielerZahl;
    private int computerZahl;
    private int rundenErgebnis;

    /**
     * Startet das Spiel mit 30 Punkten.
     */
    public GewinnModel() {
        this.gesamtPunkte = 30;
    }

    /**
     * Liefert den aktuellen Punktestand.
     */
    public int getGesamtPunkte() {
        return gesamtPunkte;
    }

    /**
     * Liefert die vom Computer gezogene Zahl.
     */
    public int getComputerZahl() {
        return computerZahl;
    }

    /**
     * Liefert die Punkteänderung der letzten Runde.
     */
    public int getRundenErgebnis() {
        return rundenErgebnis;
    }

    /**
     * Liefert die getippte Zahl des Spielers.
     */
    public int getSpielerZahl() {
        return spielerZahl;
    }

    /**
     * Ermittelt eine Zufallszahl von 1 bis 9 für den Computer.
     */
    public void berechneComputerZahl() {
        this.computerZahl = (int) (Math.random() * 9) + 1;
    }

    /**
     * Berechnet das Rundenergebnis basierend auf dem Abstand zur Computerzahl.
     */
    public void berechneRunde(int spielerZahl) {
        this.spielerZahl = spielerZahl;

        if (this.spielerZahl == this.computerZahl) {
            this.rundenErgebnis = 20;
        } else if (this.spielerZahl == this.computerZahl + 1 || this.spielerZahl == this.computerZahl - 1) {
            this.rundenErgebnis = 5;
        } else {
            this.rundenErgebnis = -10;
        }

        this.gesamtPunkte += this.rundenErgebnis;
    }

    /**
     * Prüft, ob mindestens 100 Punkte erreicht wurden.
     */
    public boolean hatGewonnen() {
        return this.gesamtPunkte >= 100;
    }

    /**
     * Prüft, ob die Punkte auf 0 oder weniger gefallen sind.
     */
    public boolean hatVerloren() {
        return this.gesamtPunkte <= 0;
    }
}