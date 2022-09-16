import javafx.scene.text.Font;

public class HighDetailedButton003_036 extends javafx.scene.control.Button implements IButton003_036 {
    public HighDetailedButton003_036(String value, int X, int Y)
    {
        super(value);
        this.setId("high-detailed-button");
        this.changeColor();
        this.changeFont();
        this.setLayoutX(X);
        this.setLayoutY(Y);
    }
    @Override
    public void changeColor() {
        this.setStyle(String.format("-fx-background-color: %s;", ConfigManager003_036.getInstance().getCurrentConfig().getColor()));
    }

    public void changeFont()
    {
        this.setFont(new Font(ConfigManager003_036.getInstance().getCurrentConfig().getFontSize()));
    }
}
