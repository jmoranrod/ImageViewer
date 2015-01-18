package control;

import ui.ImageDisplay;

public class PrevImageOperation implements Command {

    private final ImageDisplay display;

    public PrevImageOperation(ImageDisplay display) {
        this.display = display;
    }
    
    @Override
    public void execute() {
        display.setImage(display.getImage().getPrev());
    }

    @Override
    public boolean isEnabled() {
        return display.getImage().getPrev() != null;
    }
    
}
