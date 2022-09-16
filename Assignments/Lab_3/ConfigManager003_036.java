public class ConfigManager003_036 {

    private static ConfigManager003_036 instance = null;
    private static Config003_036 currentConfig003_036 = new Config003_036();
    private static int currentIndex = 0;

    public void resetIndex()
    {
        currentIndex=0;
    }


    public static String getDesignStyle() {
        return currentConfig003_036.getDesignStyle();
    }

    public static void setDesignStyle(String designStyle) {
        currentConfig003_036.setDesignStyle(designStyle);
    }


    private ConfigManager003_036(Config003_036 config003_036)
    {
        ConfigManager003_036.currentConfig003_036 = config003_036;
    }

    private ConfigManager003_036() {

    }

    public static ConfigManager003_036 getInstance()
    {
        if (instance == null)
            instance = new ConfigManager003_036();
        return instance;
    }

    public Config003_036 getCurrentConfig() {
        return currentConfig003_036;
    }
    public static void changeConfig(Config003_036 config003_036)
    {
        ConfigManager003_036.currentConfig003_036 = config003_036;
    }
    public UIElementDescription003_036 nextItem()
    {
        return currentConfig003_036.uiElementDescription003_036DescList.get(currentIndex++);
    }
    public Boolean hasMoreItems() {
        return currentIndex < currentConfig003_036.uiElementDescription003_036DescList.size();
    }

}