package control;

public class CloseApplicationOperation implements Command {
    
    public CloseApplicationOperation() {
    }

    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
