import View.HealthcareView;
import Controller.HealthcareController;

import javax.swing.SwingUtilities;

public class HealthcareApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HealthcareView view = new HealthcareView();
                new HealthcareController(view);
                view.setVisible(true);
            }
        });
    }
}

