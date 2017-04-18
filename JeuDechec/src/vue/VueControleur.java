package vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	private Scene scene;

	@Override
	public void start(Stage primaryStage) {
		// gestion du placement (permet de palcer le champ Text affichage en
		// haut, et GridPane gPane au centre)
		BorderPane border = new BorderPane();

		// permet de placer les diffrents boutons dans une grille
		GridPane boardGridPane = new GridPane();
		boardGridPane.setAlignment(Pos.CENTER);

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

				boardGridPane.add(this.groups[x][y], x, y);
			}
		}
		boardGridPane.setGridLinesVisible(true);
		border.setCenter(boardGridPane);

		// cr�ation du panneau de control
		final Button playVsIAButton = new Button("Jouer contre l'ordinateur");
		this.partie = new Partie();
		final Partie partieTmp = this.partie;
		playVsIAButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				partieTmp.setPlayerTwoAsIA();
				playVsIAButton.setText("Joue contre l'ordinateur");
			}
		});
		Pane buttonPane = new Pane();
		buttonPane.getChildren().add(playVsIAButton);

		border.setBottom(buttonPane);

		this.scene = new Scene(border, Color.WHITE);

		primaryStage.setTitle("Echec");
		primaryStage.setScene(this.scene);
		primaryStage.show();

		this.partie.subscribe(this);
		this.partie.fillCheckBoard();

	}

	private void colorCase(Rectangle colorRect, Plateau p, int x, int y) {
		if (p.cases[x][y].isLit) {
			if (p.cases[x][y].piece != null
					&& p.cases[x][y].piece.couleur == !this.partie.getLastPieceClicked().couleur)
				colorRect.setFill(Color.RED);
			else
				colorRect.setFill(Color.GREENYELLOW);
		} else {
			if ((x + y) % 2 == 0)
				colorRect.setFill(Color.BLANCHEDALMOND);
			else
				colorRect.setFill(Color.BROWN);
		}
	}

	private void displayPieceImage(Plateau p, int x, int y) {
		if (p.cases[x][y].piece != null) {
			ImageView img = new ImageView(p.cases[x][y].piece.imagePath);
			img.setPreserveRatio(true);
			img.setFitWidth(50);
			img.setFitHeight(50);
			img.setLayoutY(15);
			img.setLayoutX(15);
			this.groups[x][y].getChildren().add(img);
		}
	}

	public void updateView() {
		Plateau p = this.partie.plateau;

		boolean currentTurn = this.partie.whitesTurn;
		Stage stage = (Stage) this.scene.getWindow();
		String title = "Echec : Tour des ";
		if (currentTurn) {
			title += "Blancs";
		} else {
			title += "Noir";
		}
		stage.setTitle(title);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Rectangle colorRect = ((Rectangle) this.groups[x][y].getChildren().get(0));

				for (int i = this.groups[x][y].getChildren().size() - 1; i > 0; i--) {
					this.groups[x][y].getChildren().remove(i);
				}
				// Affichage de l'image de la piece
				displayPieceImage(p, x, y);

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
		if (gameStatus == Partie.NOBODY_IN_CHECK) { // personne n'a encore
													// gagner, on continue de
													// jouer
			updateView();
			return;
		}
		displayEndGamePopup(gameStatus);
	}

	private void displayEndGamePopup(int gameStatus) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Jeu d'echec");
		alert.setHeaderText("La partie est terminee.");
		if (gameStatus == Partie.WHITE_IN_CHECK || gameStatus == Partie.WHITE_IN_CHECKMATE) // blanc
			alert.setContentText("Les blancs ont gagnes !");
		else // noir
			alert.setContentText("Les noirs ont gagnes !");
		alert.showAndWait();
	}
	
	private String displayPromotePopUp(){
		List<String> choices = new ArrayList<>();
		choices.add("Reine");
		choices.add("Tour");
		choices.add("Cavalier");
		choices.add("Fou");
		
		ChoiceDialog<String> dialog = new ChoiceDialog<>("Reine", choices);
		dialog.setTitle("Promotion");
		dialog.setHeaderText("Un de vos pions peut être promus!");
		dialog.setContentText("Choisissez en quoi:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    return(result.get());
		}
		else
			return "Reine";

	}

}
