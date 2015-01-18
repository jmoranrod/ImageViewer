package application;

import control.CloseApplicationOperation;
import control.Command;
import control.NextImageOperation;
import control.PrevImageOperation;
import filesystem.FileImageLoader;
import model.Image;
import swing.SwingFrame;
import ui.ImageDisplay;

public class Application {

    public static void main(String[] args) {
        new Application().start(args[0]);
    }

    private void start(String filename) {
        Image image = new FileImageLoader(filename).load();
        SwingFrame frame = new SwingFrame();
        frame.getDisplay().setImage(image);
        frame.register(createOperations(frame.getDisplay()));
        frame.start();       
    }

    private Command[] createOperations(final ImageDisplay display) {
        Command[] commands = new Command[3];
        commands[SwingFrame.NEXT] = new NextImageOperation(display);
        commands[SwingFrame.PREV] = new PrevImageOperation(display);
        commands[SwingFrame.CLOSE] = new CloseApplicationOperation();
        return commands;
    }
    
}