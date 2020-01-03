package application;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Entrance_Animation{
	
	private AnchorPane pane;
	private Image background;
	private Image seagull;
	private Image loading;
	private ImageView background_iv;
	private ImageView seagull_iv;
	private ImageView loading_iv;
	private FadeTransition animation_background;
	private Rectangle loading_rec;
	private Button stbtn;
	private Text title;
	private Media background_music;
	private MediaPlayer background_music_pr;
	private PathTransition sl_move_pathpt;
	
	public Entrance_Animation(Main main) {
		
		//Pane settings
		pane = new AnchorPane();
		pane.setPrefSize(1024, 768);
		//Background settings
		background = new Image(getClass().getResourceAsStream("umi.jpg"), 1024, 768, false, true);
		background_iv = new ImageView(background);
		//Animation settings
		animation_background = new FadeTransition(Duration.millis(6000), background_iv);
		animation_background.setCycleCount(1);
		animation_background.setFromValue(0.1);
		animation_background.setToValue(0.7);
		
		//stbtn settings
		stbtn = new Button("START");
		stbtn.setId("EA_stbtn");
		stbtn.setLayoutX(408);
		stbtn.setLayoutY(510);
		stbtn.setPrefWidth(208);
		stbtn.setPrefHeight(109);
		
		loading_rec = new Rectangle();
		loading_rec.setWidth(24);
		loading_rec.setHeight(94);
		loading_rec.setLayoutX(166);
		loading_rec.setLayoutY(440);
		loading_rec.setId("EA_loading_rec");
		loading_rec.setVisible(false);
		
		loading = new Image(getClass().getResourceAsStream("loading.png"), 1024, 768, false, true);
		loading_iv = new ImageView(loading);
		loading_iv.setVisible(false);
		
		stbtn.setOnMouseClicked(e -> {
			stbtn.setMouseTransparent(true);
			Timeline timeline_fade_out = new Timeline();
			Timeline timeline_loading = new Timeline();		
			KeyFrame key_fade_out = new KeyFrame(Duration.millis(3000),
							new KeyValue(main.getStage().getScene().getRoot().opacityProperty(), 0.2));
			KeyFrame key_loading = new KeyFrame(Duration.millis(30), ae -> {
				if(loading_rec.getWidth()<=694) {
					loading_rec.setWidth(loading_rec.getWidth() + 4);
				}
			});
			timeline_fade_out.getKeyFrames().add(key_fade_out);
			timeline_loading.getKeyFrames().add(key_loading);
			timeline_loading.setCycleCount(200);			
			timeline_fade_out.setOnFinished( ae ->{
				main.getStage().getScene().getRoot().setOpacity(1);
				loading_iv.setVisible(true);
				loading_rec.setVisible(true);
				timeline_loading.play();
			});
			timeline_loading.setOnFinished(ae -> {
				main.setMenu();
			});
			timeline_fade_out.play();
			
		});
		//Title settings
		title = new Text(" The Fisher of the Deep Sea ");
		title.setId("EA_title");
		title.setLayoutX(66);
		title.setLayoutY(260);
		//Background_music settings
		background_music = new Media(getClass().getResource("background_music.mp3").toString());
		background_music_pr = new MediaPlayer(background_music);
		background_music_pr.setVolume(0.5);
		background_music_pr.setOnEndOfMedia(()->{
			background_music_pr.seek(Duration.seconds(5));
		});
		background_music_pr.setCycleCount(-1);
		//seagull.gif settings
		seagull = new Image(getClass().getResourceAsStream("seagull.gif"), 50, 30 ,true, true);
		seagull_iv = new ImageView(seagull);
		Line line = new Line();
		line.setStartX(0);
		line.setStartY(150);
		line.setEndX(1050);
		line.setEndY(150);
		sl_move_pathpt = new PathTransition();
		sl_move_pathpt.setDuration(Duration.millis(8000));
		sl_move_pathpt.setPath(line);
		sl_move_pathpt.setNode(seagull_iv);
		
		pane.getChildren().add(background_iv);
		pane.getChildren().add(seagull_iv);
		pane.getChildren().add(title);
		pane.getChildren().add(stbtn);
		pane.getChildren().add(loading_iv);
		pane.getChildren().add(loading_rec);
	}
	public void animation_start() {
		animation_background.play();
		sl_move_pathpt.play();
	}
	public AnchorPane getPane() {
		return pane;
	}
	public void music() {
		background_music_pr.play();
	}
	public MediaPlayer volume_control() {
		return background_music_pr;
	}
}
