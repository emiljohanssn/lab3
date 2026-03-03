import java.util.Random;

public class CarFactory {

    public Volvo240 createVolvo240() {
        return new Volvo240();
    }

    public Saab95 createSaab95() {
        return new Saab95();
    }

    public Scania createScania() {
        return new Scania();
    }

    public Cars createRandomCar() {
        Random rnd = new Random();
        int r = rnd.nextInt(3);

        return switch (r) {
            case 0 -> new Volvo240();
            case 1 -> new Saab95();
            case 2 -> new Scania();
            default -> throw new IllegalArgumentException("Unexpected Value" + r);
        };
    }
}






