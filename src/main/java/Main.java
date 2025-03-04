import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class is the entry point for the Sir Talks-A-Lot application.
 * It initializes the JavaFX application and sets up the primary stage (window)
 * with the main user interface loaded from an FXML file.
 */
public class Main extends Application {
    private final String filePath = "data/sirtalksalot.txt";
    private final SirTalksALot sirTalksALot = new SirTalksALot(filePath);

    /**
     * The main entry point for the JavaFX application.
     *
     * @param stage The stage for the application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setMinHeight(220);
            stage.setMinWidth(500);
            stage.setTitle("Sir Talks-A-Lot");

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSirTalksALot(sirTalksALot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
