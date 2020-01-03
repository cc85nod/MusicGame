package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Menu {
	
	private AnchorPane pane;
	private Rectangle background_rec;
	private Rectangle text_rec;
	private Image background;
	private Image game;
	private Image about;
	private Image collection;
	private Image setting;
	private Image arrow_left;
	private Image arrow_right;
	private ImageView background_iv;
	private ImageView option_icon;
	private ImageView arrow_left_iv;
	private ImageView arrow_right_iv;
	private Label option;
	private int option_index;
	
	public Menu(Main main) {
		//pane settings
		pane = new AnchorPane();
		pane.setPrefSize(1024, 768);
		//text_background settings
		background_rec = new Rectangle();
		background_rec.setWidth(658);
		background_rec.setHeight(499);
		background_rec.setLayoutX(183);
		background_rec.setLayoutY(115);
		background_rec.setId("Menu_background");
		
		text_rec = new Rectangle();
		text_rec.setWidth(644);
		text_rec.setHeight(128);
		text_rec.setLayoutX(190);
		text_rec.setLayoutY(478);
		text_rec.setId("Menu_text_rec");
		//option settings
		option = new Label("GAME");
		option.setId("Menu_option");
		option.setLayoutX(190);
		option.setLayoutY(478);
		option.setPrefWidth(644);
		option.setPrefHeight(121);
		option.setAlignment(Pos.CENTER);
		//icon settings
		game = new Image(getClass().getResourceAsStream("game.png"), 235, 235, false, true);
		about = new Image(getClass().getResourceAsStream("about.png"), 235, 235, false, true);
		collection = new Image(getClass().getResourceAsStream("collection.png"), 235, 235, false, true);
		setting = new Image(getClass().getResourceAsStream("setting.png"), 235, 235, false, true);
		
		option_icon = new ImageView(game);
		option_icon.setId("Menu_option_icon");
		option_icon.setLayoutX(395);
		option_icon.setLayoutY(182);
		option_icon.setOnMouseClicked(e -> {
			switch(option_index%4) {
			
			case 0:{
				if(!main.getGame().getFirst()) {
					main.getGame().play();
				}
				main.setGame();
				break;
			}
			case 1:{
				main.setSetting();
				break;
			}
			case 2:{
				main.setAbout();
				break;
			}
			case 3:{
				main.setCollection();
				break;
			}
		}
		});
		//arrow settings
		arrow_left = new Image(getClass().getResourceAsStream("arrow_left.png"), 140, 221, false, true);
		arrow_left_iv = new ImageView(arrow_left);
		arrow_left_iv.setLayoutX(14);
		arrow_left_iv.setLayoutY(215);
		arrow_left_iv.setId("Menu_arrow_left");
		arrow_right = new Image(getClass().getResourceAsStream("arrow_right.png"), 140, 221, false, true);
		arrow_right_iv = new ImageView(arrow_right);
		arrow_right_iv.setLayoutX(872);
		arrow_right_iv.setLayoutY(215);
		arrow_right_iv.setId("Menu_arrow_right");
		option_index = 0;
		arrow_left_iv.setOnMouseClicked(e->{
			option_index--;
			if(option_index<0) option_index+=4;
			switch(option_index%4) {
				
				case 0:{
					option_icon.setImage(game);
					option.setText("GAME");
					break;
				}
				case 1:{
					option_icon.setImage(setting);
					option.setText("SETTING");
					break;
				}
				case 2:{
					option_icon.setImage(about);
					option.setText("ABOUT");
					break;
				}
				case 3:{
					option_icon.setImage(collection);
					option.setText("COLLECTION");
					break;
				}
			}
		});
		arrow_right_iv.setOnMouseClicked(e->{
			option_index++;
			switch(option_index%4) {
				case 0:{
					option_icon.setImage(game);
					option.setText("GAME");
					break;
			}
				case 1:{
					option_icon.setImage(setting);
					option.setText("SETTING");
					break;
			}
				case 2:{
					option_icon.setImage(about);
					option.setText("ABOUT");
					break;
			}
				case 3:{
					option_icon.setImage(collection);
					option.setText("COLLECTION");
					break;
			}
		}
		});
		//background settings
		background = new Image(getClass().getResourceAsStream("umi.jpg"), 1024, 768, false, true);
		background_iv = new ImageView(background);
		
		pane.getChildren().add(background_iv);
		pane.getChildren().add(background_rec);
		pane.getChildren().add(text_rec);
		pane.getChildren().add(option);
		pane.getChildren().add(option_icon);
		pane.getChildren().add(arrow_left_iv);
		pane.getChildren().add(arrow_right_iv);
	}
	public AnchorPane getPane() {
		return pane;
	}
}
