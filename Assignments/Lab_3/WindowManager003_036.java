import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Builder;

public class WindowManager003_036 extends Application {
    public static UIElementFactory003_036 uiElementFactory003_036;
    private static ConfigManager003_036 cfgManager;
    private static volatile boolean javaFxLaunched = false;

    @Override
    public void start(Stage stage) throws Exception {
        initStage(stage);
    }

    public static void initStage(Stage stage) {
        cfgManager = ConfigManager003_036.getInstance();
        Region sceneRoot = new ScreenBuilder().build();
        Scene scene = new Scene(sceneRoot);

        stage.setWidth(800);
        stage.setHeight(600);
        stage.setTitle("WindowManager003_036");

        stage.setScene(scene);
        stage.show();
    }

    public WindowManager003_036() {

    }

    public WindowManager003_036(String designStyle) {
        if(designStyle.equals("Simplistic"))
            uiElementFactory003_036 = new SimplisticUIElementFactory003_036();
        else
            uiElementFactory003_036 = new HighDetailedUIElementFactory003_036();
    }

    public static void loadUI(ConfigManager003_036 config) {
        cfgManager = config;
        myLaunch(WindowManager003_036.class);
    }



    public static void myLaunch(Class<? extends Application> applicationClass) {
        if (!javaFxLaunched) { // First time
            Platform.setImplicitExit(false);
            new Thread(()->Application.launch(applicationClass)).start();
            javaFxLaunched = true;
        } else { // Next times
            Platform.runLater(()->{
                try {
                    Application application = applicationClass.newInstance();
                    Stage primaryStage = new Stage();
                    application.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    static class ScreenBuilder implements Builder<Region> {
        @Override
        public Region build() {
            Pane displayItems = new Pane();
            //if config element == button, call createbutton and so on...

            while(cfgManager.hasMoreItems()) {
                UIElementDescription003_036 uiElementDescription003_036 = cfgManager.nextItem();

                if( uiElementDescription003_036.type.equals("Button") ) {
                    displayItems.getChildren().add((Node) uiElementFactory003_036.createButton(uiElementDescription003_036.value, uiElementDescription003_036.X, uiElementDescription003_036.Y));
                } else if(uiElementDescription003_036.type.equals("TextBox")) {
                    displayItems.getChildren().add((Node) uiElementFactory003_036.createTextBox(uiElementDescription003_036.value, uiElementDescription003_036.X, uiElementDescription003_036.Y));
                } else {
                    displayItems.getChildren().add((Node) uiElementFactory003_036.createEditBox(uiElementDescription003_036.value, uiElementDescription003_036.X, uiElementDescription003_036.Y));
                }
            }

            return displayItems;
        }
    }

}