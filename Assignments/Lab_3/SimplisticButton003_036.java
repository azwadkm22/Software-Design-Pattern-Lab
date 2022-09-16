import javafx.scene.control.Button;
public class SimplisticButton003_036 extends Button implements IButton003_036 {
    public SimplisticButton003_036(String value, int X, int Y)
    {
        super(value);
        this.setId("simplistic-button");
        this.changeColor();
        this.setLayoutX(X);
        this.setLayoutY(Y);
    }

    @Override
    public void changeColor() {
        this.setStyle(String.format("-fx-background-color: %s;", ConfigManager003_036.getInstance().getCurrentConfig().getColor()));
    }
}
