package admin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdminController {
	@FXML
	private Label exit;
	@FXML
    private void handleButtonAction(MouseEvent event) {
       
            System.exit(0);////
        int error;
	}
	
       
		@FXML
		public void signout(MouseEvent event) throws IOException {
			// TODO Autogenerated
			Parent homePage = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
	        Scene homepageScene = new Scene(homePage);
	        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        appStage.setScene(homepageScene);
	        homepageScene.setFill(Color.TRANSPARENT);
	        appStage.show();
		
	}
	@FXML
    private void compte(ActionEvent event) throws IOException {
       

	    Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        
        stage.close();

        Scene scene;
		
			scene = new Scene(FXMLLoader.load(getClass().getResource("/com_adminsettings/AdminSettings.fxml")));
		
        stage.setScene(scene);
       // stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
       
       stage.show();
        
	}
	@FXML
    private void vendeurs(ActionEvent event) throws IOException  {
       
		    Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            
            stage.close();

            Scene scene;
			
				scene = new Scene(FXMLLoader.load(getClass().getResource("/comvendeur/VenDeur.fxml")));
			
            stage.setScene(scene);
           // stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
           
           stage.show();
	}

}
