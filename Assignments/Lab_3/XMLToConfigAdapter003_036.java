import java.util.List;

public class XMLToConfigAdapter003_036 extends Config003_036 {
    private XMLParser003_036 xmlParser003_036;

    XMLToConfigAdapter003_036(XMLParser003_036 xmlParser003_036)
    {
        this.xmlParser003_036 = xmlParser003_036;
    }

    public Config003_036 createConfig()
    {
        List<String> commands = this.xmlParser003_036.parseXML();
        for (String command: commands) {
            String[] arrOfCmd = command.split(",");

            if(arrOfCmd[0].equals("Button"))
            {
                uiElementDescription003_036DescList.add(new UIElementDescription003_036("Button",arrOfCmd[1], Integer.parseInt(arrOfCmd[2]), Integer.parseInt(arrOfCmd[3])));
            }
            else if(arrOfCmd[0].equals("EditBox"))
            {
                uiElementDescription003_036DescList.add(new UIElementDescription003_036("EditBox",arrOfCmd[1], Integer.parseInt(arrOfCmd[2]), Integer.parseInt(arrOfCmd[3])));
            }
            else if(arrOfCmd[0].equals("TextBox"))
            {
                uiElementDescription003_036DescList.add(new UIElementDescription003_036("TextBox",arrOfCmd[1], Integer.parseInt(arrOfCmd[2]), Integer.parseInt(arrOfCmd[3])));
            }
        }
        return this;
    }
}
