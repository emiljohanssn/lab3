import javax.swing.Timer;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        CarModel cc = new CarModel();

        CarFactory cf = new CarFactory();

        cc.cars.add(cf.createVolvo240());
        cc.cars.add(cf.createSaab95());
        cc.cars.add(cf.createScania());

        // Start a new view and send a reference of self
        CarView view = new CarView("CarSim 1.0", cc);
        CarController c = new CarController(cc, view);

        cc.addObserver(view);

        // Start the timer
        cc.timer.start();
    }
}
