package Model;

public class GewinnModel {
    private int gesamtPunkte;
    private int spielerZahl;
    private int computerZahl;
    private int rundenErgebnis;

    // start mit 30 punkten laut angabe
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

    // zufallszahl zwischen 1 und 9
    public void berechneComputerZahl() {
        this.computerZahl = (int) (Math.random() * 9) + 1;
    }

    // regeln aus der angabe: gleich +20, abstand 1 +5, sonst -10
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

    // gewinn ab 100 punkten
    public boolean hatGewonnen() {
        return this.gesamtPunkte >= 100;
    }

    // verloren bei 0 oder weniger
    public boolean hatVerloren() {
        return this.gesamtPunkte <= 0;
    }
}