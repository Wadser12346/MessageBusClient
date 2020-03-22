package MainApplication.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewerController {
    @FXML
    ImageView imageViewBox;

    public void displayImage(Image image){
        imageViewBox.setImage(image);
        imageViewBox.setFitWidth(600);
        imageViewBox.setFitHeight(600);
        imageViewBox.setPreserveRatio(true);
        imageViewBox.setSmooth(true);
        imageViewBox.setCache(true);
    }
}
