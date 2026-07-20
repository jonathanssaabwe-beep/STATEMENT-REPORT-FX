import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // This loads your main dashboard layout
            Parent root = FXMLLoader.load(getClass().getResource("Financialstatement.fxml"));
            
            primaryStage.setTitle("Financial Statement Generator");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            
        } catch(Exception e) {
            System.out.println("Error: Make sure MainApp.java is in the same folder as Financialstatement.fxml");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}