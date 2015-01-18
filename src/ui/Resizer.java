package ui;

public class Resizer {

    private final int width, height;

    public Resizer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Size resize(int width, int height) {
        double wRatio = 1.0 * this.width / width;
        double hRatio = 1.0 * this.height / height;
        return hRatio < wRatio
                ? new Size((int) (width * hRatio), (int) (height * hRatio))
                : new Size((int) (width * wRatio), (int) (height * wRatio));
    }

    public class Size {

        private int width, height;

        private Size(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

    }

}
