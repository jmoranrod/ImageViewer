package swing;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import model.Image;
import ui.ImageDisplay;
import ui.Resizer;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    
    private Image image;

    public SwingImageDisplay() {
    }
    
    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage bufferedImage = getBufferedImage();
        Rectangle rectangle = calculateRectangle(bufferedImage);
        g.drawImage(bufferedImage, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
    }

    private BufferedImage getBufferedImage() {
        return getSwingBitmap().getBufferedImage();
    }

    private Rectangle calculateRectangle(BufferedImage bufferedImage) {
        Resizer.Size size = new Resizer(this.getWidth(), this.getHeight()).resize(bufferedImage.getWidth(), bufferedImage.getHeight());
        return new Rectangle(
                (this.getWidth() - size.getWidth()) / 2,
                (this.getHeight() - size.getHeight()) / 2,
                size.getWidth(),
                size.getHeight()
        );
    }

    private SwingBitmap getSwingBitmap() {
        return (SwingBitmap) this.image.getBitmap();
    }

}
