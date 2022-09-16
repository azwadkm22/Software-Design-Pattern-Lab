import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HighDetailedITextBox003_036 extends Label implements ITextBox003_036 {
    public HighDetailedITextBox003_036(String value, int X, int Y)
    {
        super(value);
        this.setId("high-detailed-textbox-blue");
        this.changeColor();
        this.changeFont();
        this.setLayoutX(X);
        this.setLayoutY(Y);
    }

    @Override
    public void changeColor() {
        this.setStyle(String.format("-fx-color:  %s;", ConfigManager003_036.getInstance().getCurrentConfig().getColor()));
    }

    public void changeFont()
    {
        this.setFont(new Font(ConfigManager003_036.getInstance().getCurrentConfig().getFontSize()));
    }

}
