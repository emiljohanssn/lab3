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
    CarModel carC;
    CarView carV;

    public CarController(CarModel cc, CarView cv) {
        this.carC = cc;
        this.carV = cv;
        initButtons();
    }


    public void initButtons() {

            carV.addGasListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    carC.gas(carV.getGasAmount());
                }
            });

            carV.addBrakeListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    carC.brake(carV.getGasAmount());
                }
            });

            carV.addStartListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    carC.startEngine();
                }
            });

            carV.addStopListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    carC.stopEngine();
                }
            });

            carV.addTurboOnListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    carC.turboOn();
                }
            });

            carV.addTurboOffListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    carC.turboOff();
                }
            });

            carV.addRaiseBedListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    carC.raiseBed();
                }
            });

            carV.addLowerBedListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    carC.lowerBed();
                }
            });


        carV.addAddCarListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    carC.addCar();
                }
            });

            carV.addRemoveCarListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {carC.removeCar();
                }
            });
    }
}