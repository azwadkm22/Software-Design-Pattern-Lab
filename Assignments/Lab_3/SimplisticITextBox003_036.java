import javafx.scene.control.Label;

public class SimplisticITextBox003_036 extends Label implements ITextBox003_036 {
    public SimplisticITextBox003_036(String value, int X, int Y)
    {
        super(value);
        this.setId("simplistic-textbox");
        this.changeColor();
        this.setLayoutX(X);
        this.setLayoutY(Y);
    }

    @Override
    public void changeColor() {
        this.setStyle(String.format("-fx-color: %s;", ConfigManager003_036.getInstance().getCurrentConfig().getColor()));
    }
}
