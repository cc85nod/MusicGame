package application;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Game {
	private AnchorPane pane;
	private Image cliff_background;
	private Image fisher;
	private Image marquee_icon;
	private Image success;
	private Image back;
	private ImageView cliff_background_iv;
	private ImageView fisher_iv;
	private ImageView marquee_icon_iv;
	private ImageView success_iv;
	private ImageView fish_type;
	private ImageView back_iv;
	private CubicCurve fishing_line;
	private Scene scene;
	private Rectangle marquee;
	private Rectangle marquee_space;
	private Media pressed;
	private MediaPlayer pressed_pr;
	private boolean[] have;
	
	private KeyFrame S_F_KF;
	private KeyFrame clear_event_KF;
	private KeyFrame hooking_KF;
	private KeyFrame autofailed_KF;
	private KeyFrame s_clear_event_KF;
	private Timeline S_F_TL;
	private Timeline clear_event_TL;
	private Timeline hooking_TL;
	private Timeline autofailed_TL;
	private Timeline s_clear_event_TL;
	
	private Line line;
	private Line line_up;
	private PathTransition move_path;
	private PathTransition move_path_up;
	
	private Main main;
	private Game_hookup_event hook;
	
	private int hooking_cycle;
	private int intro_index = 0;
	private Label result;
	private static boolean first=true;
	private static boolean reverse;
	private static boolean success_TF=false;
	private static boolean failed_TF=false;
	
	public Game(Main main) {
		
		this.main=main;
		//have settings
		boolean have[] = new boolean[15];
		for(int i=0; i<15; ++i) {
			have[i] = false;
		}
		this.have = have;
		//back settings
		back = new Image(getClass().getResourceAsStream("back.png"), 64, 64, false, true);
		back_iv = new ImageView(back);
		back_iv.setId("Back");
		back_iv.setLayoutX(20);
		back_iv.setLayoutY(20);
		back_iv.setOnMouseClicked(e -> {
			stop();
			success_TF=false;
			failed_TF=false;
			main.setMenu();
		});
		//pressed settings
		pressed = new Media(getClass().getResource("pressed.mp3").toString());
		pressed_pr = new MediaPlayer(pressed);
		pressed_pr.setVolume(0.5);
		pressed_pr.setOnEndOfMedia(()-> {
			pressed_pr.stop();
		});
		pressed_pr.setRate(2.0);
		//pane settings
		pane = new AnchorPane();
		pane.setPrefSize(1024, 768);
		//result settings
		result = new Label("Failed");
		result.setId("Game_result");
		result.setMouseTransparent(true);
		result.setVisible(false);
		result.setLayoutY(395);
		AnchorPane.setLeftAnchor(result, 0.0);
		AnchorPane.setRightAnchor(result, 0.0);
		result.setAlignment(Pos.CENTER);
		//marquee settings
		marquee = new Rectangle();
		marquee.setId("Game_marquee");
		marquee.setLayoutY(603);
		marquee.setWidth(1024);
		marquee.setHeight(110);
		marquee.setVisible(false);
		//marquee_space settings
		marquee_space = new Rectangle();
		marquee_space.setId("Game_marquee_space");
		marquee_space.setLayoutX(792);
		marquee_space.setLayoutY(603);
		marquee_space.setWidth(107);
		marquee_space.setHeight(110);
		marquee_space.setVisible(false);
		//marquee_icon settings
		marquee_icon = new Image(getClass().getResourceAsStream("miku.png"), 90, 90, true, true);
		marquee_icon_iv = new ImageView(marquee_icon);
		marquee_icon_iv.setVisible(false);
		marquee_icon_iv.setLayoutX(-90);
		line = new Line();
		line.setStartX(-90);
		line.setStartY(653);
		line.setEndX(1200);
		line.setEndY(653);
		move_path = new PathTransition();
		move_path.setDuration(Duration.millis(3000));
		move_path.setPath(line);
		move_path.setNode(marquee_icon_iv);
		//fisher settings
		fisher = new Image(getClass().getResourceAsStream("fisher.png"), 130, 90, true, true);
		fisher_iv = new ImageView(fisher);
		fisher_iv.setLayoutX(14);
		fisher_iv.setLayoutY(210);
		//cliff_background settings
		cliff_background = new Image(getClass().getResourceAsStream("cliff_background.png"), 1024, 768, false, true);
		cliff_background_iv = new ImageView(cliff_background);
		//fishing_line settings
		fishing_line = new CubicCurve(-152, 11, 45, -176, 278, -284, 436, 409);
		fishing_line.setId("Game_fishing_line");
		fishing_line.setLayoutX(227);
		fishing_line.setLayoutY(203);
		//success settings
		success = new Image(getClass().getResourceAsStream("miku_success.gif"), 400, 250, false, true);
		success_iv = new ImageView(success);
		success_iv.setLayoutX(250);
		success_iv.setLayoutY(220);
		success_iv.setVisible(false);
		//fish_up settings
		line_up = new Line();
		line_up.setStartX(436);
		line_up.setStartY(409);
		line_up.setEndX(436);
		line_up.setEndY(34);
		move_path_up = new PathTransition();
		move_path_up.setDuration(Duration.millis(5000));
		move_path_up.setPath(line_up);
		
		//hooking settings
		reverse = true;
		hooking_cycle=0;
		hooking_TL = new Timeline();
		hooking_TL.setDelay(Duration.seconds(4));
		hooking_TL.setCycleCount(-1);
		hooking_KF = new KeyFrame(Duration.millis(50), e-> {
			if(reverse) {
				fishing_line.setEndX(fishing_line.getEndX()+20);
				reverse = false;
			} else {
				fishing_line.setEndX(fishing_line.getEndX()-20);
				reverse = true;
			}
			hooking_cycle++;
			if(hooking_cycle==10) {
				hookup();
			}
		});
		hooking_TL.getKeyFrames().add(hooking_KF);
		//clear delay
		clear_event_TL = new Timeline();
		clear_event_KF = new KeyFrame(Duration.seconds(3), ae->{
			clear();
		});
		clear_event_TL.getKeyFrames().add(clear_event_KF);
		//success clear delay
		s_clear_event_TL = new Timeline();
		s_clear_event_KF = new KeyFrame(Duration.seconds(6), ae->{
			clear();
		});
		s_clear_event_TL.getKeyFrames().add(s_clear_event_KF);
		//autofailed settings
		autofailed_KF = new KeyFrame(Duration.seconds(3.5), e->{
			failed();
		});
		autofailed_TL = new Timeline();
		autofailed_TL.getKeyFrames().add(autofailed_KF);
		//success fishline setting
		S_F_KF = new KeyFrame(Duration.millis(100), e-> {
			fishing_line.setEndY(fishing_line.getEndY()-8);
		});
		S_F_TL = new Timeline();
		S_F_TL.getKeyFrames().add(S_F_KF);
		S_F_TL.setCycleCount(50);
		
		scene = new Scene(pane, 1024, 768);
		pane.getChildren().add(cliff_background_iv);
		pane.getChildren().add(back_iv);
		pane.getChildren().add(fishing_line);
		pane.getChildren().add(fisher_iv);
		pane.getChildren().add(marquee);
		pane.getChildren().add(marquee_space);
		pane.getChildren().add(marquee_icon_iv);
		pane.getChildren().add(result);
		pane.getChildren().add(success_iv);
		introduction();
	}
	public AnchorPane getPane() {
		return pane;
	}
	public Scene getGameScene() {
		return scene;
	}
	public void hookup() {
		fish_type = null;
		hook = null;
		hook = new Game_hookup_event((int)(Math.random()*100+1));
		ArrayList<ImageView> arrow_iv = hook.getArrow_iv();
		ArrayList<Boolean> arrow_index = hook.getArrow_index();
		ArrayList<KeyCode> arrow_type = hook.getArrow_type();
		
		marqueePlay();
		autofailed_TL.play();
		pane.getChildren().add(hook.getArrowLayout());
		
		scene.setOnKeyPressed(ae->{
			for(int i=0; i<arrow_index.size(); ++i) {
				if(arrow_index.get(i)) {
					if(ae.getCode()==arrow_type.get(i)) {
						arrow_iv.get(i).setImage(new Image(getClass().getResourceAsStream("transparent.png"), 64, 64, false, true));
						if(i!=arrow_index.size()-1) {
							//next arrow can be pressed
							pressed_pr.play();
							arrow_index.set(i+1, true);
						} else {
							//all arrows are pressed
							//plus 90 because of initial layoutX is -90 882~899
							autofailed_TL.stop();
							if(marquee_icon_iv.getTranslateX()>=(870) && marquee_icon_iv.getTranslateX()<=(930)) {
								fish_type = hook.getFish_type_iv();
								have[hook.getType()]=true;
								success();
							} else {
								failed();
							}
						}
					}
				}
			}
		});
		
	}
	public void clear() {
		
		success_TF=false;
		failed_TF=false;
		scene.setOnKeyPressed(null);
		marquee.setVisible(false);
		marquee_space.setVisible(false);
		marquee_icon_iv.setVisible(false);
		result.setText("Failed");
		result.setVisible(false);
		hooking_cycle=0;
		hooking_TL.stop();
		hooking_TL.play();
		move_path.stop();
		fishing_line.setEndY(409);
		success_iv.setVisible(false);
		pane.getChildren().remove(hook.getArrowLayout());
		pane.getChildren().remove(fish_type);
		
	}
	public void no_hookup_back() {
		scene.setOnKeyPressed(null);	
		marquee.setVisible(false);
		marquee_space.setVisible(false);
		marquee_icon_iv.setVisible(false);	
		result.setText("Failed");
		result.setVisible(false);		
		hooking_cycle=0;
		hooking_TL.stop();
		fishing_line.setEndY(409);
		success_iv.setVisible(false);
	}
	public void hookup_back() {
		
		scene.setOnKeyPressed(null);	
		marquee.setVisible(false);
		marquee_space.setVisible(false);
		marquee_icon_iv.setVisible(false);	
		result.setText("Failed");
		result.setVisible(false);	
		hooking_cycle=0;
		hooking_TL.stop();
		fishing_line.setEndY(409);
		success_iv.setVisible(false);
		pane.getChildren().remove(hook.getArrowLayout());
		
		autofailed_TL.stop();
		move_path.stop();
	}
	public void success_back() {
		
		scene.setOnKeyPressed(null);
		marquee.setVisible(false);
		marquee_space.setVisible(false);
		marquee_icon_iv.setVisible(false);
		result.setText("Failed");
		result.setVisible(false);
		hooking_cycle=0;
		hooking_TL.stop();
		autofailed_TL.stop();
		move_path.stop();
		pane.getChildren().remove(hook.getArrowLayout());
		pane.getChildren().remove(fish_type);
		fishing_line.setEndY(409);
		success_iv.setVisible(false);
		
		s_clear_event_TL.stop();
		S_F_TL.stop();
		move_path_up.stop();
				
	}
	public void failed_back() {
		
		scene.setOnKeyPressed(null);
		marquee.setVisible(false);
		marquee_space.setVisible(false);
		marquee_icon_iv.setVisible(false);
		result.setText("Failed");
		result.setVisible(false);
		clear_event_TL.stop();
		hooking_cycle=0;
		hooking_TL.stop();
		pane.getChildren().remove(hook.getArrowLayout());
		fishing_line.setEndY(409);
		success_iv.setVisible(false);	
		
		clear_event_TL.stop();
	}
	public void failed() {
		failed_TF=true;
		scene.setOnKeyPressed(null);
		result.toFront();
		result.setVisible(true);
		clear_event_TL.play();
	}
	public void success() {
		success_TF=true;
		for(int i=0; i<15; ++i) {
			if(have[i]==true) {
				main.getColl().winFish(i);
			}
		}
		move_path_up.setNode(fish_type);
		pane.getChildren().add(fish_type);
		fish_type.toBack();
		fishing_line.toBack();
		cliff_background_iv.toBack();
		result.toFront();
		result.setVisible(true);
		result.setText("Success");
		hooking_TL.stop();
		s_clear_event_TL.play();
		S_F_TL.play();
		move_path_up.play();
		success_iv.setVisible(true);
	}
	public void marqueePlay() {
		move_path.play();
		marquee_icon_iv.setVisible(true);
		marquee.setVisible(true);
		marquee_space.setVisible(true);
	}
	public void play() {
		hooking_TL.play();
	}
	public void stop() {
		if(hooking_cycle<10){
			no_hookup_back();
		} else {
			if(success_TF) {
				success_back();
			} else if(failed_TF) {
				failed_back();
			} else {
				hookup_back();
			}
		}
	}
	public MediaPlayer effect_vol_control() {
		return pressed_pr;
	}
	public void introduction() {
		AnchorPane pane = new AnchorPane();
		pane.setMinWidth(1024);
		pane.setMinHeight(768);
		Image X = new Image(getClass().getResourceAsStream("X.png"), 55, 55, true, true);
		ImageView X_iv = new ImageView(X);
		X_iv.setLayoutX(110);
		X_iv.setLayoutY(170);
		X_iv.setId("Character_close");
		X_iv.setOnMouseClicked(e-> {
			this.getPane().getChildren().remove(pane);
			play();
			first=false;
		} );
		Rectangle bg = new Rectangle();
		bg.setId("Game_introduction_rc");
		bg.setLayoutX(110);
		bg.setLayoutY(170);
		bg.setWidth(800);
		bg.setHeight(450);
		Image i1 = new Image(getClass().getResourceAsStream("i1.png"));
		Image i2 = new Image(getClass().getResourceAsStream("i2.png"));
		Image i3 = new Image(getClass().getResourceAsStream("i3.png"));
		Image i4 = new Image(getClass().getResourceAsStream("i4.png"));
		Image i5 = new Image(getClass().getResourceAsStream("i5.png"));
		ImageView prc = new ImageView(i1);
		prc.setLayoutX(170);
		prc.setLayoutY(230);
		prc.setFitWidth(680);
		prc.setFitHeight(300);
		
		String step1 = "Step1: Waiting for seconds, there will be something heppened.";
		String step2 = "Step2: When the arrows appear, please pressing correspond key.";
		String step3 = "Step3: You need to press space key in black block after have been pressing all arrows.";
		String step4 = "Step4: If you don't miss some arrows or not press space in black block, you will fail game.";
		String step5 = "Step5: You will success if you have done both things, and you will get a cute fish,\nits types will be"
						+ "normal, rare, legend and epic in different condition with more arrows.";
		String end = "Enjoy it!";
		
		Label intro = new Label(step1);
	
		intro.setId("Game_introduction");
		intro.setLayoutX(170);
		intro.setLayoutY(530);
		AnchorPane.setLeftAnchor(intro, 0.0);
		AnchorPane.setRightAnchor(intro, 0.0);
		
		intro.setAlignment(Pos.CENTER);
		ImageView arrow_left_iv = new ImageView(new Image(getClass().getResourceAsStream("arrow_left.png"), 70, 100, false, true));
		arrow_left_iv.setId("Game_arrow_left");
		arrow_left_iv.setLayoutX(100);
		arrow_left_iv.setLayoutY(365);
		arrow_left_iv.setVisible(false);
		ImageView arrow_right_iv = new ImageView(new Image(getClass().getResourceAsStream("arrow_right.png"), 70, 100, false, true));
		arrow_right_iv.setId("Game_arrow_right");
		arrow_right_iv.setLayoutX(850);
		arrow_right_iv.setLayoutY(365);
		
		arrow_left_iv.setOnMouseClicked(e->{
			intro_index--;
			switch(intro_index%5) {
				case 0:{
					arrow_left_iv.setVisible(false);
					prc.setImage(i1);
					intro.setText(step1);
					break;
				}
				case 1:{
					arrow_left_iv.setVisible(true);
					prc.setImage(i2);
					intro.setText(step2);
					break;
				}
				case 2:{
					prc.setImage(i3);
					intro.setText(step3);
					break;
				}
				case 3:{
					prc.setImage(i4);
					intro.setText(step4);
					break;
				}
				case 4:{
					arrow_right_iv.setVisible(true);
					prc.setImage(i5);
					intro.setText(step5);
					intro.setLayoutY(530);
					intro.setScaleX(1);
					intro.setScaleY(1);
					break;
				}
			}
		});
		arrow_right_iv.setOnMouseClicked(e->{
			intro_index++;
			switch(intro_index%6) {
				case 0:{
					arrow_left_iv.setVisible(false);
					prc.setImage(i1);
					intro.setText(step1);
					break;
			}
				case 1:{
					arrow_left_iv.setVisible(true);
					prc.setImage(i2);
					intro.setText(step2);
					break;
			}
				case 2:{
					prc.setImage(i3);
					intro.setText(step3);
					break;
			}
				case 3:{
					prc.setImage(i4);
					intro.setText(step4);
					break;
			}
				case 4:{
					arrow_right_iv.setVisible(true);
					prc.setImage(i5);
					intro.setText(step5);
					intro.setLayoutY(530);
					break;
			}	case 5:{
					arrow_right_iv.setVisible(false);
					prc.setImage(null);
					intro.setLayoutY(400);
					intro.setText(end);
					break;
			}
		}
		});
		pane.getChildren().addAll(bg, X_iv, arrow_left_iv, arrow_right_iv, prc, intro);
		this.getPane().getChildren().add(pane);
	}
	public boolean getFirst() {
		return first;
	}
}