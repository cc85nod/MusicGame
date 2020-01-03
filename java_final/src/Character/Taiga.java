package Character;

import application.Collection;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Taiga {
	AnchorPane pane;
	public Taiga(Collection coll) {
		pane = new AnchorPane();
		pane.setMinWidth(1024);
		Image X = new Image(getClass().getResourceAsStream("X.png"), 55, 55, true, true);
		ImageView X_iv = new ImageView(X);
		X_iv.setLayoutX(110);
		X_iv.setLayoutY(170);
		X_iv.setId("Character_close");
		X_iv.setOnMouseClicked(e-> {
			coll.getPane().getChildren().remove(getPane());
		} );
		
		Image c = new Image(getClass().getResourceAsStream("char/lend_1.jpg"), 800, 450, false, true);
		ImageView c_iv = new ImageView(c);
		c_iv.setLayoutX(110);
		c_iv.setLayoutY(170);
		
		Label name = new Label("Name: Aisaka Taiga");
		name.setMinHeight(36);
		name.setId("Character_label");
		name.setLayoutX(183);
		name.setLayoutY(217);
		
		Label height = new Label("Height: 143.6 cm");
		height.setMinHeight(36);
		height.setId("Character_label");
		height.setLayoutX(183);
		height.setLayoutY(297);
		
		Label birthday = new Label("Birthday: 12/15");
		birthday.setMinHeight(36);
		birthday.setId("Character_label");
		birthday.setLayoutX(183);
		birthday.setLayoutY(377);
		
		Label CV = new Label("CV: Kugimiya Rie");
		CV.setMinHeight(36);
		CV.setId("Character_label");
		CV.setLayoutX(183);
		CV.setLayoutY(457);
		
		Label type = new Label("Type: legend");
		type.setMinHeight(36);
		type.setId("Character_label");
		type.setLayoutX(183);
		type.setLayoutY(537);
		
		Rectangle bg = new Rectangle();
		bg.setId("Character_bg_rec");
		bg.setLayoutX(164);
		bg.setLayoutY(188);
		bg.setWidth(305);
		bg.setHeight(418);
		
		pane.getChildren().addAll(c_iv, bg, name, height, birthday, CV, type, X_iv);
	}
	public AnchorPane getPane() {
		return pane;
	}
}
