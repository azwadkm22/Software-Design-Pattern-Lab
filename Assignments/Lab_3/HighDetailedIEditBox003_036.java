import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class HighDetailedIEditBox003_036 extends TextField implements IEditBox003_036 {
    public HighDetailedIEditBox003_036(String value, int X, int Y)
    {
        super(value);
        this.setId("high-detailed-editbox");
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
