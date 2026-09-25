import Controller.GewinnController;
import Model.GewinnModel;
import view.GewinnView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GewinnModel model = new GewinnModel();
            GewinnView view = new GewinnView();
            new GewinnController(model, view);
            view.setVisible(true);
        });
    }
}