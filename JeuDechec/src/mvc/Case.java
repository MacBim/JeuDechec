package mvc;

import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Case {
	public Position position;
	public Group group;
	public Color couleur;
	
	public Case(Position position){
		this.group = new Group();
		this.position = position;
		int positionSum = position.x + position.y;
		if(positionSum%2 == 0){
			this.couleur = Color.WHITE;
		} else {
			this.couleur = Color.LIGHTBLUE;
		}
		Rectangle rect = new Rectangle();
		rect.setHeight(80);
		rect.setWidth(80);
		rect.setFill(this.couleur);
		
		this.group.getChildren().add(rect);
	}
}
