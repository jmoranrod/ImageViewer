package mock;

import java.io.File;
import model.Bitmap;
import model.Image;

public class MockImage implements Image {

    private final File file;
    private Image nextImage;
    private Image prevImage;

    public MockImage(File file) {
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
        return null;
    }
    
}
