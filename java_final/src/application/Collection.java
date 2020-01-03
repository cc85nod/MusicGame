package application;

import java.util.ArrayList;
import java.util.Arrays;

import Character.*;
import javafx.geometry.HPos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Collection {
	
	private AnchorPane pane;
	private Rectangle background_rec;
	private Image background;
	private Image back;
	private Image unknown;
	private Image right;
	private Image left;
	private ImageView right_iv;
	private ImageView left_iv;
	private ImageView background_iv;
	private ImageView back_iv;
	private ArrayList<ImageView> fish;
	private GridPane fish_page1;
	private GridPane fish_page2;
	private Image[] fish_Image;
	private int option_index=1;
	private ArrayList<AnchorPane> panelist;
	
	public Collection(Main main) {
		//panelist settings
		panelist = new ArrayList<AnchorPane>();
		panelist.addAll(
				Arrays.asList(new Chitoge(this).getPane(),
					new Yoshino(this).getPane(),
					new Yui(this).getPane(),
					new Rin(this).getPane(),
					new Schwi(this).getPane(),
					new Rem(this).getPane(),
					new Shiro(this).getPane(),
					new Manaca(this).getPane(),
					new Emilia(this).getPane(),
					new Taiga(this).getPane(),
					new Nayuta(this).getPane(),
					new Rikka(this).getPane(),
					new Mashiro(this).getPane(),
					new Megumin(this).getPane(),
					new Inori(this).getPane()));
		//fish settings
		fish = new ArrayList<ImageView>();
		fish_page1 = new GridPane();
		fish_page1.setLayoutX(65);
		fish_page1.setLayoutY(119);
		fish_page1.setPrefWidth(917);
		fish_page1.setPrefHeight(597);
		fish_page1.setHgap(40);
		fish_page2 = new GridPane();
		fish_page2.setLayoutX(65);
		fish_page2.setLayoutY(119);
		fish_page2.setPrefWidth(917);
		fish_page2.setPrefHeight(597);
		fish_page2.setHgap(40);
		unknown = new Image(getClass().getResourceAsStream("unknown.png"));
		//page1
		for(int i=0; i<4; ++i) {
			for(int j=0; j<2; ++j) {
				ImageView unknown_iv = new ImageView(unknown);
				unknown_iv.setFitHeight(290);
				unknown_iv.setFitWidth(190);
				fish.add(unknown_iv);
				fish_page1.add(unknown_iv, i, j);
				GridPane.setHalignment(unknown_iv, HPos.CENTER);
			}
		}
		for(int i=0; i<8; ++i) {
			final int ii = i;
			fish.get(i).setOnMouseClicked(e->{
				if(fish.get(ii).getImage().equals(fish_Image[ii])) {
					
				}
			});
		}
		//page2
		for(int i=0; i<4; ++i) {
			for(int j=0; j<2; ++j) {
				ImageView unknown_iv = new ImageView(unknown);
				unknown_iv.setFitHeight(290);
				unknown_iv.setFitWidth(190);
				fish.add(unknown_iv);
				fish_page2.add(unknown_iv, i, j);
				GridPane.setHalignment(unknown_iv, HPos.CENTER);
			}
		}
		//right and left settings
		right = new Image(getClass().getResourceAsStream("coll_r.png"));
		right_iv = new ImageView(right);
		right_iv.setId("Collection_right");
		right_iv.setFitWidth(78);
		right_iv.setFitHeight(59);
		right_iv.setLayoutX(946);
		right_iv.setLayoutY(366);
		right_iv.setOnMouseClicked(e->{
			option_index++;
			switch(option_index%2) {
				case 0:{
					pane.getChildren().remove(fish_page1);
					pane.getChildren().add(fish_page2);
					break;
				}
				case 1:{
					pane.getChildren().remove(fish_page2);
					pane.getChildren().add(fish_page1);					
					break;
				}
			}
		});
		left = new Image(getClass().getResourceAsStream("coll_l.png"));
		left_iv = new ImageView(left);
		left_iv.setId("Collection_left");
		left_iv.setFitWidth(78);
		left_iv.setFitHeight(59);
		left_iv.setLayoutX(0);
		left_iv.setLayoutY(366);
		left_iv.setOnMouseClicked(e->{
			option_index--;
			if(option_index<0) option_index+=2;
			switch(option_index%2) {
			case 0:{
				pane.getChildren().remove(fish_page1);
				pane.getChildren().add(fish_page2);
				break;
			}
			case 1:{
				pane.getChildren().remove(fish_page2);
				pane.getChildren().add(fish_page1);
				break;
			}
		}
			
		});
		//characters Q settings
		Image fish_Image [] = new Image[] {
			new Image(getClass().getResourceAsStream("char_Q/normal_1_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/normal_2_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/normal_3_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/normal_4_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/normal_5_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/rare_1_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/rare_2_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/rare_3_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/rare_4_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/lend_1_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/lend_2_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/lend_3_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/epic_1_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/epic_2_Q.png"), 600, 290, false, true)
			,new Image(getClass().getResourceAsStream("char_Q/epic_3_Q.png"), 600, 290, false, true)
		};
		this.fish_Image=fish_Image;
		
		//pane settings
		pane = new AnchorPane();
		pane.setPrefSize(1024, 768);
		//text_background settings
		background_rec = new Rectangle();
		background_rec.setWidth(917);
		background_rec.setHeight(663);
		background_rec.setLayoutX(54);
		background_rec.setLayoutY(53);
		background_rec.setId("Collection_background");
		//background settings
		background = new Image(getClass().getResourceAsStream("collection_bg.jpg"), 1024, 768, true, true);
		background_iv = new ImageView(background);
		//back settings
		back = new Image(getClass().getResourceAsStream("back.png"), 55, 55, false, true);
		back_iv = new ImageView(back);
		back_iv.setId("Back");
		back_iv.setLayoutX(64);
		back_iv.setLayoutY(64);
		back_iv.setOnMouseClicked(e -> {
			main.setMenu();
		});
		back_iv.setFocusTraversable(true);
		pane.getChildren().add(background_iv);		
		pane.getChildren().add(background_rec);
		pane.getChildren().add(fish_page1);
		pane.getChildren().add(right_iv);
		pane.getChildren().add(left_iv);
		pane.getChildren().add(back_iv);
	}
	public void winFish(int type) {
		fish.get(type).setImage(fish_Image[type]);
		fish.get(type).setCursor(Cursor.HAND);
		fish.get(type).setOnMouseClicked(e->{
			pane.getChildren().add(panelist.get(type));
		});
		
	}
	public AnchorPane getPane() {
		return pane;
	}
}