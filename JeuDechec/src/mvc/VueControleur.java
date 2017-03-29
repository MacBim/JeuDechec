
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;


import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author freder
 */
public class VueControleur extends Application {
    
    // modèle : ce qui réalise le calcule de l'expression
    Modele m;
    // affiche la saisie et le résultat
    Text affichage;
    
    @Override
    public void start(Stage primaryStage) {
        
        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele();
        
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        
        
        // cr�ation de la grille
        int compt = 0;
        int ybis = 0;
        for(int x = 0; x < 8; x++){
        	for(int y = 0; y < 8; y++){
        		
        		Group g = new Group();
        		
        		Rectangle rect = new Rectangle();
        		rect.setWidth(80);
        		rect.setHeight(80);
        		if(compt%2 == 0){
        			rect.setFill(Color.WHITE);
        		} else {
        			rect.setFill(Color.BLACK);
        		}
        		compt++;
        		
        		g.getChildren().add(rect);
        		
        		gPane.add(rect, x, y);
        	}
        	if(ybis%2 == 0)
        		compt = 1;
        	else
        		compt = 0;
        	ybis++;
        	
        }
        
        gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);
        
        Scene scene = new Scene(border, Color.WHITE);
        
        primaryStage.setTitle("Calc FX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
