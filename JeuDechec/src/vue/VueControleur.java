
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;


import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modele.ObserveurEchec;
import modele.Partie;
import modele.Plateau;

/**
 *
 * @author lol
 */
public class VueControleur extends Application implements ObserveurEchec{
	
	private Group[][] groups = new Group[8][8];
	private Partie partie;
	
	
    @Override
    public void start(Stage primaryStage) {
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        
        
        for(int x = 0; x < 8; x++){
        	for(int y = 0; y < 8; y++){
        		
        		Rectangle r = new Rectangle(80,80);
        		int positionSum = x + y;
        		if(positionSum%2 == 0){
        			r.setFill(Color.WHITE);
        		} else {
        			r.setFill(Color.LIGHTBLUE);
        		}

        		this.groups[x][y] = new Group();
        		this.groups[x][y].getChildren().add(r);
        		
        		final int col = x;
        		final int lig = y;
        		
        		this.groups[x][y].setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						partie.onClick(col,lig);
					}
        			
				});
        		
        		
        		gPane.add(this.groups[x][y], x, y);
        	}        	
        }
        
        System.out.println("groups");
        
        gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);
        
        Scene scene = new Scene(border, Color.WHITE);
        
        primaryStage.setTitle("Echec");
        primaryStage.setScene(scene);
        primaryStage.show();
        this.partie = new Partie();
        this.partie.subscribe(this);
        this.partie.remplirPlateau();
        
    }

    public void updateVue(){
    	System.out.println("j'update la vuuuuuuuuuuuuuuuuuuue");
    	Plateau p = this.partie.plateau;
    	for(int x = 0; x < 8; x++){
    		for(int y = 0; y < 8; y++){
    			if(p.cases[x][y].piece != null){
    				for(int i = this.groups[x][y].getChildren().size()-1; i > 0; i--){
    					this.groups[x][y].getChildren().remove(i);
    				}
    				
    				ImageView img = new ImageView(p.cases[x][y].piece.imagePath);
    				this.groups[x][y].getChildren().add(img);
    			}
    		}
    	}
    	System.out.println("fin update");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stubs
		updateVue();
		System.out.println("notify");
	}

    
}
