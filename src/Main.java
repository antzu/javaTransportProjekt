import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Created by antz on 27/11/2016.
 */
public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start (Stage primaryStage) throws Exception{
        Kasutajaliides kasutajaliides = new Kasutajaliides(); //Generates user interface
    }
}
