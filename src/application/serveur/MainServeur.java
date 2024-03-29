package application.serveur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainServeur extends Application{

	public static Parent root;
	public static Scene scene;
	

	public void start(Stage primaryStage) throws Exception{
		try {
			root = FXMLLoader.load(getClass().getResource("VueServeur.fxml"));
			scene = new Scene(root);
			primaryStage.setTitle("VUE SERVEUR");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
