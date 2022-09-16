import java.util.ArrayList;
import java.util.List;

public class Config003_036 {

    private String designStyle;
    private String simpleColor = "#DEDEDE";
    private String hdColor = "#FFCCEE";
    private int fontSize = 14;
    List<UIElementDescription003_036> uiElementDescription003_036DescList;

    Config003_036(List<UIElementDescription003_036> uiElementDescription003_036List)
    {
        this.uiElementDescription003_036DescList = uiElementDescription003_036List;
    }
    Config003_036()
    {
        this.uiElementDescription003_036DescList = new ArrayList<>();
    }

    public String getDesignStyle() {
        return this.designStyle;
    }

    public void setDesignStyle(String designStyle) {
        this.designStyle = designStyle;
    }

    public String getColor()
    {
        if(designStyle == "Simplistic")
        return this.simpleColor;
        else
            return this.hdColor;
    }
    public void setColor(String color)
    {
        if(designStyle == "Simplistic")
        this.simpleColor = color;
        else this.hdColor = color;
    }
    public int getFontSize()
    {
        return this.fontSize;
    }
    public void setFontSize(int fontSize)
    {
        this.fontSize = fontSize;
    }
    
}
