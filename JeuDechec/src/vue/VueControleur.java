package vue;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
public class VueControleur extends Application implements ObserveurEchec {

	private Group[][] groups = new Group[8][8];
	private Partie partie;

	@Override
	public void start(Stage primaryStage) {
		// gestion du placement (permet de palcer le champ Text affichage en
		// haut, et GridPane gPane au centre)
		BorderPane border = new BorderPane();

		// permet de placer les diffrents boutons dans une grille
		GridPane gPane = new GridPane();
		gPane.setAlignment(Pos.CENTER);

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {

				Rectangle r = new Rectangle(80, 80);
				int positionSum = x + y;
				if (positionSum % 2 == 0) {
					r.setFill(Color.BLANCHEDALMOND);
				} else {
					r.setFill(Color.BROWN);
				}

				this.groups[x][y] = new Group();
				this.groups[x][y].getChildren().add(r);

				final int col = x;
				final int lig = y;

				this.groups[x][y].setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						partie.onClickPiece(col, lig);
					}

				});

				gPane.add(this.groups[x][y], x, y);
			}
		}
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
	
	private void colorCase(Rectangle colorRect, Plateau p, int x, int y){
		if (p.cases[x][y].isLit) {
			if(p.cases[x][y].piece != null && p.cases[x][y].piece.couleur == !this.partie.getLastPieceClicked().couleur)
				colorRect.setFill(Color.RED);
			else
				colorRect.setFill(Color.GREENYELLOW);
		} else {
			if((x+y)%2 == 0)
				colorRect.setFill(Color.BLANCHEDALMOND);
			else
				colorRect.setFill(Color.BROWN);
		}
	}
	
	private void affichePieceCase(Plateau p, int x, int y){
		if (p.cases[x][y].piece != null) {
			ImageView img = new ImageView(p.cases[x][y].piece.imagePath);
			this.groups[x][y].getChildren().add(img);
		}
	}
	

	public void updateVue() {
		Plateau p = this.partie.plateau;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Rectangle colorRect = ((Rectangle) this.groups[x][y].getChildren().get(0));
				
				for (int i = this.groups[x][y].getChildren().size() - 1; i > 0; i--) {
					this.groups[x][y].getChildren().remove(i);
				}
				// Affichage de l'image de la piï¿½ce
				affichePieceCase(p, x, y);

				// Coloration des cases
				colorCase(colorRect, p, x, y);
			}
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stubs
		int gameStatus = this.partie.getGameStatus();
		if(gameStatus == 0){
			updateVue();
			return;
		}
		displayEndGamePopup(gameStatus);
		
	}
	
	private void displayEndGamePopup(int gameStatus){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Jeu d'échec");
		alert.setHeaderText("La partie est terminée.");
		if(gameStatus == 1) // blanc
			alert.setContentText("Les blancs ont gagnés !");
		else
			alert.setContentText("Les noirs ont gagnés !");

		alert.showAndWait();
	}

}
