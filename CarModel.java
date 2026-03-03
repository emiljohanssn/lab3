import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CarModel {
    // member fields:
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    protected javax.swing.Timer timer = new Timer(delay, new TimerListener());
    // A list of cars, modify if needed

    ArrayList<Cars> cars = new ArrayList<>();
    Garage<Volvo240> volvoGarage = new Garage<>(10, 300, 50);
    CarFactory factory = new CarFactory();

    // The frame that represents this instance View of the MVC pattern
    //CarView frame;
    Notifier obs;
    Volvo240 toRemove;

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */


    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int i = 0;
            for (Cars car : cars) {
                car.move();
                int x = (int) Math.round(car.getXPosition());
                int y = (int) Math.round(car.getYPosition() + i++*100);
                if (x >= (800 - 100) || x < 0) {
                    car.turnLeft();
                    car.turnLeft();
                } else if (car instanceof Volvo240) {
                    if (((x + 100) >= volvoGarage.getGarageX()) && (x <= (volvoGarage.getGarageX() + 101)) &&
                            ((y + 60) >= volvoGarage.getGarageY()) && (y <= (volvoGarage.getGarageY() + 96) &&
                            (!volvoGarage.getCarGarage().contains(car)))) {
                                    volvoGarage.load((Volvo240) car);
                                    car.stopEngine();
                                    toRemove = (Volvo240) car;
                                    break;
                                }
                }
            }
            cars.remove(toRemove);
            obs.notice();
        }
    }

    public void addObserver(Notifier obs) {
            this.obs = obs;
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
    void addCar() {
        if (cars.size() < 6) {
            cars.add(factory.createRandomCar());
        }
    }

    void removeCar() {
        if (!cars.isEmpty()) {
            cars.removeLast();
        }
    }
}
