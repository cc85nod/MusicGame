package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Entrance_Animation EA;
	private About AT;
	private Setting SET;
	private Menu MU;
	private Game GE;
	private Collection Coll;
	
	private Scene scene_mu;
	private Scene scene_ea;
	private Scene scene_at;
	private Scene scene_set;
	private Scene scene_coll;
	private Stage stage;
	
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		//Entrance_Animation
		EA = new Entrance_Animation(this); 
		EA.animation_start();
		EA.music();
		//About
		AT = new About(this);
		//Setting( need to control volume )
		SET = new Setting(this, EA);
		//Menu ( need to change options )
		MU = new Menu(this);
		//Game
		GE = new Game(this);
		//Collection
		Coll = new Collection(this);
		
		scene_mu = new Scene(MU.getPane(), 1024, 768);
		scene_ea = new Scene(EA.getPane(), 1024, 768);
		scene_at = new Scene(AT.getPane(), 1024, 768);
		scene_set = new Scene(SET.getPane(), 1024, 768);
		scene_coll = new Scene(Coll.getPane(), 1024, 768);
		
		scene_ea.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		scene_at.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		scene_set.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		scene_mu.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		scene_coll.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		
		GE.getGameScene().getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		stage.setTitle("The Fisher of the Deep Sea");
		stage.setScene(scene_ea);
		stage.setResizable(false);
		stage.show();
//		GE.getGameScene()
		
	}
	
	//GETTING
	public Stage getStage() {
		return stage;
	}
	public Game getGame() {
		return GE;
	}
	public Collection getColl() {
		return Coll;
	}
	//SETTING
	public void setAbout() {
		stage.setScene(scene_at);
	}
	public void setCollection() {
		stage.setScene(scene_coll);
	}
	public void setGame() {
		stage.setScene(GE.getGameScene());
	}
	public void setSetting() {
		stage.setScene(scene_set);
	}
	public void setMenu() {
		stage.setScene(scene_mu);
	}
	public static void main(String[] args) {
		launch(args);
	}
}