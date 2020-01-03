package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class About {
	
	private AnchorPane pane;
	private Text about;
	private Text maker;
	private Text ncu;
	private Text cs;
	private Rectangle background_rec;
	private Image background;
	private Image back;
	private ImageView background_iv;
	private ImageView back_iv;
	
	public About(Main main) {
		
		//pane settings
		pane = new AnchorPane();
		pane.setPrefSize(1024, 768);
		//text_background settings
		background_rec = new Rectangle();
		background_rec.setWidth(658);
		background_rec.setHeight(499);
		background_rec.setLayoutX(183);
		background_rec.setLayoutY(115);
		background_rec.setId("About_background");
		//text setting
		about = new Text(496, 227, "ABOUT");
		about.setId("About_about");
		about.setScaleX(10.8);
		about.setScaleY(9.9);
		maker = new Text(470, 364, "Maker¡GJerry");
		maker.setId("About_maker");
		maker.setScaleX(2.95);
		maker.setScaleY(2.57);
		ncu = new Text(441, 453, "National Central University");
		ncu.setId("About_ncu");
		ncu.setScaleX(2.61);
		ncu.setScaleY(2.66);
		cs = new Text(460, 550, "Computer Science");
		cs.setId("About_cs");
		cs.setScaleX(2);
		cs.setScaleY(3.4);
		//background settings
		background = new Image(getClass().getResourceAsStream("umi.jpg"), 1024, 768, true, true);
		background_iv = new ImageView(background);
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
		pane.getChildren().add(about);
		pane.getChildren().add(maker);
		pane.getChildren().add(ncu);
		pane.getChildren().add(cs);
		pane.getChildren().add(back_iv);
	}
	public AnchorPane getPane() {
		return pane;
	}
}
