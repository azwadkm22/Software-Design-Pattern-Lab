public class HighDetailedUIElementFactory003_036 implements UIElementFactory003_036 {
    @Override
    public HighDetailedButton003_036 createButton(String value, int X, int Y) {
        return new HighDetailedButton003_036(value, X, Y);
    }

    @Override
    public HighDetailedITextBox003_036 createTextBox(String value, int X, int Y) {
        return new HighDetailedITextBox003_036(value, X, Y);
    }

    @Override
    public HighDetailedIEditBox003_036 createEditBox(String value, int X, int Y) {
        return  new HighDetailedIEditBox003_036(value, X, Y);
    }
}
