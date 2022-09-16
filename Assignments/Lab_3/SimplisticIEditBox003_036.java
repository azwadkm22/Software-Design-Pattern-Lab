import javafx.scene.control.TextField;

public class SimplisticIEditBox003_036 extends TextField implements IEditBox003_036 {
    public SimplisticIEditBox003_036(String value, int X, int Y)
    {
        super(value);
        this.setId("simplistic-editbox");
        this.changeColor();
        this.setLayoutX(X);
        this.setLayoutY(Y);
    }

    @Override
    public void changeColor() {
        this.setStyle(String.format("-fx-background-color: %s;", ConfigManager003_036.getInstance().getCurrentConfig().getColor()));
    }
}
