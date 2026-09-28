import Controller.GewinnController;
import Model.GewinnModel;
import view.GewinnView;

/**
 * Startklasse der Anwendung.
 *
 * @author Dario Duric
 */
public class Main {
    /**
     * Startet das Programm, erzeugt MVC-Instanzen und macht das Fenster sichtbar.
     */
    public static void main(String[] args) {
        GewinnModel model = new GewinnModel();
        GewinnView view = new GewinnView();
        new GewinnController(model, view);
        view.setVisible(true);
        view.felderZuruecksetzen();

    }
}