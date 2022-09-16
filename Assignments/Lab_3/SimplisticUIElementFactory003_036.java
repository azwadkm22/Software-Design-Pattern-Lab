public class SimplisticUIElementFactory003_036 implements UIElementFactory003_036 {
    @Override
    public SimplisticButton003_036 createButton(String value, int X, int Y) {
        return new SimplisticButton003_036(value,X, Y);
    }

    @Override
    public SimplisticITextBox003_036 createTextBox(String value, int X, int Y) {
        return new SimplisticITextBox003_036(value, X, Y);
    }

    @Override
    public SimplisticIEditBox003_036 createEditBox(String value, int X, int Y) {
        return new SimplisticIEditBox003_036(value, X, Y);
    }
}
