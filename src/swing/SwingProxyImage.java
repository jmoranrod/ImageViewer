package swing;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Bitmap;
import model.Image;

public class SwingProxyImage implements Image{

    private final File file;
    private Bitmap bitmap;
    private Image nextImage;
    private Image prevImage;

    public SwingProxyImage(File file) {
        this.file = file;
    }
    
    @Override
    public Image getNext() {
        return nextImage;
    }

    @Override
    public Image getPrev() {
        return prevImage;
    }

    @Override
    public void setNext(Image nextImage) {
        this.nextImage = nextImage;
    }

    @Override
    public void setPrev(Image prevImage) {
        this.prevImage = prevImage;
    }

    @Override
    public Bitmap getBitmap() {
        if (bitmap == null) bitmap = loadBitmap();
        return bitmap;
    }

    private Bitmap loadBitmap() {
        try {
            return new SwingBitmap(ImageIO.read(file));
        } catch (IOException ex){
            System.out.println("Error loading bitmap");
            return null;
        }
    }
    
}
