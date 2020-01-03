package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class Game_hookup_event {
	
	private ArrayList<ImageView> arrow_iv;
	private ArrayList<Boolean> arrow_index;
	private ArrayList<KeyCode> arrow_type;
	private ImageView fish_type_iv;
	private GridPane arrowLayout;
	private int discont;
	private int type=0;
	
	public Game_hookup_event(int species) {
		
		arrow_iv = new ArrayList<ImageView>();
		arrow_index = new ArrayList<Boolean>();
		arrow_type = new ArrayList<KeyCode>();
		//product arrow by species
		discont=0;
		if(species<=80) {
			//normal
			fish_type_iv = setFish("normal");
			for(int i=0; i<6; ++i) {
				int RD = (int)(Math.random()*4+1);
				while(discont==RD) {
					RD = (int)(Math.random()*4+1);
				}
				KeyCode type;
				arrow_iv.add(getRandomArrow_iv(RD));
				switch(RD){
					case 1: type=KeyCode.UP;break;
					case 2: type=KeyCode.DOWN;break;
					case 3: type=KeyCode.RIGHT;break;
					case 4: type=KeyCode.LEFT;break;
					default: type=null;
				}
				discont = RD;
				arrow_type.add(type);
			}
		} else if(species>80 && species<=94) {
			//rare
			fish_type_iv = setFish("rare");
			for(int i=0; i<8; ++i) {
				int RD = (int)(Math.random()*4+1);
				while(discont==RD) {
					RD = (int)(Math.random()*4+1);
				}
				KeyCode type;
				arrow_iv.add(getRandomArrow_iv(RD));
				switch(RD){
					case 1: type=KeyCode.UP;break;
					case 2: type=KeyCode.DOWN;break;
					case 3: type=KeyCode.RIGHT;break;
					case 4: type=KeyCode.LEFT;break;
					default: type=null;
				}
				discont = RD;
				arrow_type.add(type);
			}
		} else if(species>94 && species<=99) {
			//legend
			fish_type_iv = setFish("lend");
			for(int i=0; i<10; ++i) {
				int RD = (int)(Math.random()*4+1);
				while(discont==RD) {
					RD = (int)(Math.random()*4+1);
				}
				KeyCode type;
				arrow_iv.add(getRandomArrow_iv(RD));
				switch(RD){
					case 1: type=KeyCode.UP;break;
					case 2: type=KeyCode.DOWN;break;
					case 3: type=KeyCode.RIGHT;break;
					case 4: type=KeyCode.LEFT;break;
					default: type=null;
				}
				discont = RD;
				arrow_type.add(type);
			}
		} else {
			//epic
			fish_type_iv = setFish("epic");
			for(int i=0; i<15; ++i) {
				int RD = (int)(Math.random()*4+1);
				while(discont==RD) {
					RD = (int)(Math.random()*4+1);
				}
				KeyCode type;
				arrow_iv.add(getRandomArrow_iv(RD));
					switch(RD){
					case 1: type=KeyCode.UP;break;
					case 2: type=KeyCode.DOWN;break;
					case 3: type=KeyCode.RIGHT;break;
					case 4: type=KeyCode.LEFT;break;
					default: type=null;
				}
				discont = RD;
				arrow_type.add(type);
			}
		}
		
		//arrowLayout settings
		arrowLayout = new GridPane();
		
		
		for(int i=0; i<arrow_iv.size(); ++i) {
			arrowLayout.add(arrow_iv.get(i), i+1, 1);
			arrow_index.add(false);
		}
		//Last space
		arrow_index.add(false);
		arrow_iv.add(new ImageView(new Image(getClass().getResourceAsStream("transparent.png"))));
		arrow_type.add(KeyCode.SPACE);
		//First
		arrow_index.set(0, true);
		
		arrowLayout.setLayoutY(490);
		arrowLayout.setMinWidth(1024);
		arrowLayout.setMaxWidth(1024);
		arrowLayout.setHgap(13);
		arrowLayout.setAlignment(Pos.CENTER);
		
	}
	
	
	public ImageView getRandomArrow_iv(int number) {
		Image up = new Image(getClass().getResourceAsStream("Up.png"), 64, 64, false, true);
		Image down = new Image(getClass().getResourceAsStream("Down.png"), 64, 64, false, true);
		Image right = new Image(getClass().getResourceAsStream("Right.png"), 64, 64, false, true);
		Image left = new Image(getClass().getResourceAsStream("Left.png"), 64, 64, false, true);
		ImageView up_iv = new ImageView(up);
		ImageView down_iv = new ImageView(down);
		ImageView right_iv = new ImageView(right);
		ImageView left_iv = new ImageView(left);
		switch(number) {
		case 1:return up_iv;
		case 2:return down_iv;
		case 3:return right_iv;
		case 4:return left_iv;
		default :return null;
		}
	}
	
	
	//getter
	public GridPane getArrowLayout() {
		return arrowLayout;
	}
	public ArrayList getArrow_index() {
		return arrow_index;
	}
	public ArrayList getArrow_type() {
		return arrow_type;
	}
	public ArrayList getArrow_iv() {
		return arrow_iv;
	}
	public ImageView setFish(String fish_type) {
		ImageView epic_1 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/epic_1_Q.png")));
		ImageView epic_2 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/epic_2_Q.png")));
		ImageView epic_3 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/epic_3_Q.png")));
		ImageView lend_1 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/lend_1_Q.png")));
		ImageView lend_2 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/lend_2_Q.png")));
		ImageView lend_3 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/lend_3_Q.png")));
		ImageView normal_1 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/normal_1_Q.png")));
		ImageView normal_2 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/normal_2_Q.png")));
		ImageView normal_3 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/normal_3_Q.png")));
		ImageView normal_4 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/normal_4_Q.png")));
		ImageView normal_5 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/normal_5_Q.png")));
		ImageView rare_1 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/rare_1_Q.png")));
		ImageView rare_2 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/rare_2_Q.png")));
		ImageView rare_3 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/rare_3_Q.png")));
		ImageView rare_4 = new ImageView(new Image(getClass().getResourceAsStream("char_Q/rare_4_Q.PNG")));
		
		if(fish_type.equals("normal")) {
			type = (int)(Math.random()*5);//type=0~4
			switch((type)+1) {
				case 1:return normal_1;
				case 2:return normal_2;
				case 3:return normal_3;
				case 4:return normal_4;
				case 5:return normal_5;
				default:return null;
			}
		} else if(fish_type.equals("rare")) {
			type = (int)(Math.random()*4+5);//5~9
			switch((int)(Math.random()*4+1)) {
				case 1:return rare_1;
				case 2:return rare_2;
				case 3:return rare_3;
				case 4:return rare_4;
				default:return null;
			}
		} else if(fish_type.equals("lend")) {
			type = (int)(Math.random()*3+10);//10~12
			switch((int)(Math.random()*3+1)) {
				case 1:return lend_1;
				case 2:return lend_2;
				case 3:return lend_3;
				default:return null;
			}
		} else if(fish_type.equals("epic")) {
			type = (int)(Math.random()*3+13);//13~15
			switch((int)(Math.random()*3+1)) {
				case 1:return epic_1;
				case 2:return epic_2;
				case 3:return epic_3;
				default:return null;
			}
		} else {
			return null;
		}
		
	}
	public int getType() {
		return type;
	}
	public ImageView getFish_type_iv() {
		fish_type_iv.setFitWidth(120);
		fish_type_iv.setFitHeight(150);
		fish_type_iv.setLayoutX(240);
		fish_type_iv.setLayoutY(203);
		return fish_type_iv;
	}
}
