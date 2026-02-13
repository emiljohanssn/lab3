import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    // A list of cars, modify if needed
    ArrayList<Cars> cars = new ArrayList<>();
    Garage<Volvo240> volvoGarage = new Garage<>(10);

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : cars) {
                car.move();
                int x = (int) Math.round(car.getXPosition());
                int y = (int) Math.round(car.getYPosition());
                if (x >= (frame.getX() - 100) || x < 0) {
                    car.turnLeft();
                    car.turnLeft();
                } else if (car instanceof Volvo240) {
                    if (((x + 100) >= frame.drawPanel.volvoWorkshopPoint.getX()) && (x <= (frame.drawPanel.volvoWorkshopPoint.getX() + 101)) &&
                            ((y + 60) >= frame.drawPanel.volvoWorkshopPoint.getY()) && (y <= (frame.drawPanel.volvoWorkshopPoint.getY() + 96) &&
                            (!volvoGarage.getCarGarage().contains(car)))) {
                        volvoGarage.load((Volvo240) car);
                        car.stopEngine();
                        //cars.remove(car);
                    }
                }
            }
            frame.drawPanel.repaint();
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Cars car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Cars car : cars) {
            car.brake(brake);
        }
    }

    void startEngine() {
        for (Cars car : cars) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (Cars car : cars) {
            car.stopEngine();
        }
    }

    void turboOn() {
        for (Cars car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Cars car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void lowerBed() {
        for (Cars car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).lowerFlatbed(10);
            }
        }
    }

    void raiseBed() {
        for (Cars car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).raiseFlatbed(10);
            }
        }
    }
}