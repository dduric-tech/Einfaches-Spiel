package Model;

public class GewinnModel {
    private int gesamtPunkte;
    private int spielerZahl;
    private int computerZahl;
    private int rundenErgebnis;

    // Parameterloser Konstruktor: Startet mit 30 Punkten
    public GewinnModel() {
        this.gesamtPunkte = 30;
    }

    public int getGesamtPunkte() {
        return gesamtPunkte;
    }

    public int getComputerZahl() {
        return computerZahl;
    }

    public int getRundenErgebnis() {
        return rundenErgebnis;
    }

    public int getSpielerZahl() {
        return spielerZahl;
    }

    // Zufallszahl von 1 bis 9 für den Computer
    public void berechneComputerZahl() {
        this.computerZahl = (int) (Math.random() * 9) + 1;
    }

    // Rundenberechnung ohne Math.abs
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

    public boolean hatGewonnen() {
        return this.gesamtPunkte >= 100;
    }

    public boolean hatVerloren() {
        return this.gesamtPunkte <= 0;
    }
}