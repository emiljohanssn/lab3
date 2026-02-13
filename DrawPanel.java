import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    private final List<Cars> cars;
    private final Map<String, BufferedImage> images = new HashMap<>();
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,50); //x och y är övre vänstra hörnet

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            images.put("Volvo240",ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y,null);
        int i = 0;
        for (Cars car : cars) {
            BufferedImage img = images.get(car.getModelName());
            if (img == null) continue;

            int x = (int) Math.round(car.getXPosition());
            int y = (int) Math.round(car.getYPosition()) + i++*100;

            g.drawImage(img, x, y, null);
        }
    }
}
