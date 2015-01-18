package control;

public interface Command {

    public void execute();
    public boolean isEnabled();
    
}
