package application;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
public class Setting {
	
	private AnchorPane pane;
	private Rectangle background_rec;
	private Rectangle music_control_bar;
	private Rectangle s_effects_control_bar;
	private Image background;
	private ImageView background_iv;
	private Text music;
	private Text s_effects;
	private Text setting;
	private Button music_zip;
	private Button s_effects_zip;
	private Image back;
	private Image music_vol_low;
	private Image music_vol_high;
	private Image s_effects_vol_low;
	private Image s_effects_vol_high;
	private ImageView music_vol_low_iv;
	private ImageView music_vol_high_iv;
	private ImageView s_effects_vol_low_iv;
	private ImageView s_effects_vol_high_iv;
	private ImageView back_iv;
	
	public Setting(Main main, Entrance_Animation EA) {
		//pane settings
		pane = new AnchorPane();
		pane.setPrefSize(1024, 768);
		//text_background settings
		background_rec = new Rectangle();
		background_rec.setWidth(658);
		background_rec.setHeight(499);
		background_rec.setLayoutX(183);
		background_rec.setLayoutY(115);
		background_rec.setId("Setting_background");
		background = new Image(getClass().getResourceAsStream("umi.jpg"), 1024, 768, false, true);
		background_iv = new ImageView(background);
		//text settings
		setting = new Text(496, 227, "Setting");
		setting.setId("Setting_setting");
		setting.setScaleX(10.8);
		setting.setScaleY(9.9);
		//music settings
		music = new Text(496, 370, "Music");
		music.setId("Setting_music");
		music.setScaleX(3);
		music.setScaleY(2);
		music_vol_low = new Image(getClass().getResourceAsStream("vol_low.png"), 40, 40, true, true);
		music_vol_low_iv = new ImageView(music_vol_low);
		music_vol_low_iv.setLayoutX(340);
		music_vol_low_iv.setLayoutY(387);
		music_vol_high = new Image(getClass().getResourceAsStream("vol_high.png"), 40, 40, true, true);
		music_vol_high_iv = new ImageView(music_vol_high);
		music_vol_high_iv.setLayoutX(663);
		music_vol_high_iv.setLayoutY(391);
		music_control_bar = new Rectangle();
		music_control_bar.setId("Setting_control_bar");
		music_control_bar.setWidth(215);
		music_control_bar.setHeight(14);
		music_control_bar.setLayoutX(414);
		music_control_bar.setLayoutY(401);
		music_zip = new Button("");
		music_zip.setId("Setting_music_zip");
		music_zip.setMinWidth(4);
		music_zip.setPrefWidth(4);
		music_zip.setPrefHeight(42);
		music_zip.setLayoutX(414+107-2);
		music_zip.setLayoutY(386);
		//sound effects settings
		s_effects = new Text(485, 486, "Sound Effects");
		s_effects.setId("Setting_s_effects");
		s_effects.setScaleX(3);
		s_effects.setScaleY(2);
		s_effects_vol_low = new Image(getClass().getResourceAsStream("vol_low.png"), 40, 40, true, true);
		s_effects_vol_low_iv = new ImageView(s_effects_vol_low);
		s_effects_vol_low_iv.setLayoutX(340);
		s_effects_vol_low_iv.setLayoutY(524);
		s_effects_vol_high = new Image(getClass().getResourceAsStream("vol_high.png"), 40, 40, true, true);
		s_effects_vol_high_iv = new ImageView(s_effects_vol_high);
		s_effects_vol_high_iv.setLayoutX(663);
		s_effects_vol_high_iv.setLayoutY(528);
		s_effects_control_bar = new Rectangle();
		s_effects_control_bar.setId("Setting_control_bar");
		s_effects_control_bar.setWidth(215);
		s_effects_control_bar.setHeight(14);
		s_effects_control_bar.setLayoutX(414);
		s_effects_control_bar.setLayoutY(538);
		s_effects_zip = new Button();
		s_effects_zip.setId("Setting_s_effects_zip");
		s_effects_zip.setMinWidth(4);
		s_effects_zip.setPrefWidth(4);
		s_effects_zip.setPrefHeight(43);
		s_effects_zip.setLayoutX(414+107-2);
		s_effects_zip.setLayoutY(523);
		//zip_move settings
		music_zip.setOnMouseDragged(e -> {
			if(music_zip.getLayoutX() + e.getX() > 412 && music_zip.getLayoutX() + e.getX() < 627) {
				music_zip.setLayoutX(music_zip.getLayoutX() + e.getX());
			} else if(music_zip.getLayoutX() + e.getX() <= 412 ) {
				music_zip.setLayoutX(412);
			} else if(music_zip.getLayoutX() + e.getX() >= 627) {
				music_zip.setLayoutX(627);
			}
			EA.volume_control().setVolume( ( music_zip.getLayoutX()-412 ) / 215);
		});
		s_effects_zip.setOnMouseDragged(e -> {
			if(s_effects_zip.getLayoutX() + e.getX() > 412 && s_effects_zip.getLayoutX() + e.getX() < 627) {
				s_effects_zip.setLayoutX(s_effects_zip.getLayoutX() + e.getX());
			} else if(s_effects_zip.getLayoutX() + e.getX() <= 412 ) {
				s_effects_zip.setLayoutX(412);
			} else if(s_effects_zip.getLayoutX() + e.getX() >= 627) {
				s_effects_zip.setLayoutX(627);
			}
			main.getGame().effect_vol_control().setVolume( ( s_effects_zip.getLayoutX()-412 ) / 215);
		});
		//back settings
		back = new Image(getClass().getResourceAsStream("back.png"), 64, 64, false, true);
		back_iv = new ImageView(back);
		back_iv.setId("Back");
		back_iv.setLayoutX(212);
		back_iv.setLayoutY(148);
		back_iv.setOnMouseClicked(e -> {
			main.setMenu();
		});	
		
		pane.getChildren().add(background_iv);
		pane.getChildren().add(background_rec);
		pane.getChildren().add(setting);
		pane.getChildren().add(music);
		pane.getChildren().add(music_vol_low_iv);
		pane.getChildren().add(music_vol_high_iv);
		pane.getChildren().add(music_control_bar);
		pane.getChildren().add(music_zip);
		pane.getChildren().add(s_effects);
		pane.getChildren().add(s_effects_vol_low_iv);
		pane.getChildren().add(s_effects_vol_high_iv);
		pane.getChildren().add(s_effects_control_bar);
		pane.getChildren().add(s_effects_zip);
		pane.getChildren().add(back_iv);
		
	}
	public AnchorPane getPane() {
		return pane;
	}
}
