package application.client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainClient extends Application {


	public static Stage fenJeu;
	public static CtrlJeu ctrlJeu;
	
	public void start(Stage primaryStage) throws Exception{
		try {
			//fenetre de jeu
			FXMLLoader loadJeu = new FXMLLoader(getClass().getResource("VueJeu.fxml"));
			Parent rootJeu = loadJeu.load();
			ctrlJeu = loadJeu.getController();
			Scene sceneJeu = new Scene(rootJeu);
			fenJeu = new Stage();
			fenJeu.initModality(Modality.APPLICATION_MODAL);
			fenJeu.setScene(sceneJeu);
			fenJeu.setTitle("Jeu");
			
			// fenetre principale
			Parent root = FXMLLoader.load(getClass().getResource("VueClient.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("VUE CLIENT");
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
