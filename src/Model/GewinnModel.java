package Model;
public class GewinnModel {
  private int gesamtPunkte = 30;
  private int spielerZahl;
  private int computerZahl;
  private int rundenErgebnis;

    public GewinnModel(int spielerZahl, int gesamtPunkte, int computerZahl, int rundenErgebnis) {
        this.spielerZahl = spielerZahl;
        this.gesamtPunkte = gesamtPunkte;
        this.computerZahl = computerZahl;
        this.rundenErgebnis = rundenErgebnis;
    }

    public int getGesamtPunkte() {
        return gesamtPunkte;
    }

    public int getSpielerZahl() {
        return spielerZahl;
    }

    public int getComputerZahl() {
        return computerZahl;
    }

    public int getRundenErgebnis() {
        return rundenErgebnis;
    }
    public void berechneComputerZahl() {
        this.computerZahl = (int) (Math.random() * 9) + 1;
    }
    public boolean hatGewonnen() {
        return this.gesamtPunkte >= 100;
    }
    public boolean hatVerloren() {
        return this.gesamtPunkte <= 0;
    }
}

