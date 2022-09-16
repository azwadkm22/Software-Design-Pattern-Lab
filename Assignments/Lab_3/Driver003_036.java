import javafx.application.Platform;

import java.util.Scanner;

public class Driver003_036 {
    // user will have to input the design style and config file name before the code can proceed to all else
    // if xml then xml to config adapter will create the config that is to be passed to config manager
    // if regular config file then
    static Scanner scanner = new Scanner(System.in);
    static WindowManager003_036 windowManager003_036 = new WindowManager003_036("Simplistic");
    static ConfigManager003_036 configManager003_036 = ConfigManager003_036.getInstance();

    public static void main(String[] args) {
        int style = chooseDesignStyle();
        String st = "";

        if(style == 1)
        {
            WindowManager003_036.uiElementFactory003_036 = new SimplisticUIElementFactory003_036();
            st = "Simplistic";
        }
        else
        {
            WindowManager003_036.uiElementFactory003_036 = new HighDetailedUIElementFactory003_036();
            st = "HighDetailed";
        }


        Integer fileType = chooseConfigFileType();
        switch (fileType) {
            case 1:
                XMLParser003_036 xmlParser003_036 = new XMLParser003_036();
                XMLToConfigAdapter003_036 xmlToConfigAdapter = new XMLToConfigAdapter003_036(xmlParser003_036);
                configManager003_036.changeConfig(xmlToConfigAdapter.createConfig());
                ConfigManager003_036.setDesignStyle(st);
                break;
            case 2:
                System.out.println("Terminal Input Selected.");
                configManager003_036.changeConfig(textInput());
                break;
            default:
                break;
        }

        while(true) {
            System.out.println("\t1- Create Window");
            System.out.println("\t2- No new Window");
            int s = scanner.nextInt();
            if(s==2)
            {
                Platform.exit();
                break;
            }

            System.out.println("Change window design?");
            System.out.println("\t1- Change");
            System.out.println("\tElse- No");
            int x = scanner.nextInt();
            if(x==1)
            {
                int k = chooseDesignStyle();
                if(k==1)
                {
                    WindowManager003_036.uiElementFactory003_036 = new SimplisticUIElementFactory003_036();
                    ConfigManager003_036.setDesignStyle("Simplistic");
                }
                else
                {
                    WindowManager003_036.uiElementFactory003_036 = new HighDetailedUIElementFactory003_036();
                    ConfigManager003_036.setDesignStyle("HighDetailed");
                }
            }


            System.out.println("Change window color?");
            System.out.println("\t1- Yes");
            System.out.println("\tElse- No");
            x = scanner.nextInt();
            if(x == 1)
            {
                System.out.print("\tColor(in hex format #FFFFFF) : ");
                String col = scanner.next();
                configManager003_036.getCurrentConfig().setColor(col);
            }
            System.out.println(configManager003_036.getCurrentConfig().getDesignStyle());
            if(configManager003_036.getCurrentConfig().getDesignStyle() == "HighDetailed")
            {
                System.out.println("Change font size?");
                System.out.println("\t1- Yes");
                System.out.println("\tElse- No");
                x = scanner.nextInt();
                if(x == 1)
                {
                    System.out.print("\tFont Size : ");
                    int sz = scanner.nextInt();
                    configManager003_036.getCurrentConfig().setFontSize(sz);
                }
            }
            System.out.println(s);
            if (s == 1) {
                configManager003_036.resetIndex();
                WindowManager003_036.loadUI(configManager003_036);
            }

            System.out.println("\t1- Exit");
            System.out.println("\t2- Continue");
            s = scanner.nextInt();
            if(s == 1)
            {
                Platform.exit();
                break;
            }
            else {
                continue;
            }
        }
    }

    private static int chooseDesignStyle() {
        System.out.println("Which design style would you like to create the window of?");
        System.out.println("\t1 - Simplistic");
        System.out.println("\t2 - High Detailed");
        int x = scanner.nextInt();
        return x;
    }

    private static Integer chooseConfigFileType() {
        System.out.println("Where to get the config file?");
        System.out.println("\t1 - XML");
        System.out.println("\t2 - Terminal");
        Integer fileType = scanner.nextInt();
        return fileType;
    }

    private static Config003_036 textInput(){
        Config003_036 cfg = new Config003_036();
        System.out.println("How many buttons?");
        int numButtons = scanner.nextInt();
        for(int i=1; i<=numButtons; i++)
        {
            System.out.println("Button " + i + " Value: ");
            String value = scanner.next();

            System.out.println("Button " + i + " X: ");
            int x = scanner.nextInt();

            System.out.println("Button " + i + " Y: ");
            int y = scanner.nextInt();

            cfg.uiElementDescription003_036DescList.add(new UIElementDescription003_036("Button",value, x, y));
        }

        System.out.println("How many TextBoxes?");
        int numTextBoxes = scanner.nextInt();
        for(int i=1; i<=numTextBoxes; i++)
        {
            System.out.println("TextBox " + i + " Value: ");
            String value = scanner.next();

            System.out.println("TextBox " + i + " X: ");
            int x = scanner.nextInt();

            System.out.println("TextBox " + i + " Y: ");
            int y = scanner.nextInt();

            cfg.uiElementDescription003_036DescList.add(new UIElementDescription003_036("TextBox",value, x, y));
        }

        System.out.println("How many EditBoxes?");
        int numEditBoxes = scanner.nextInt();
        for(int i=1; i<=numEditBoxes; i++)
        {
            System.out.println("EditBox " + i + " Value: ");
            String value = scanner.next();

            System.out.println("EditBox " + i + " X: ");
            int x = scanner.nextInt();

            System.out.println("EditBox " + i + " Y: ");
            int y = scanner.nextInt();

            cfg.uiElementDescription003_036DescList.add(new UIElementDescription003_036("EditBox",value, x, y));
        }
        return cfg;
    }


}