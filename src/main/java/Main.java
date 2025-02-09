import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private final String filePath = "data/sirtalksalot.txt";
    private final SirTalksALot sirTalksALot = new SirTalksALot(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setMinHeight(220);
            stage.setMinWidth(800);
            stage.setTitle("Sir Talks-A-Lot");

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSirTalksALot(sirTalksALot);  // inject the SirTalksALot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
