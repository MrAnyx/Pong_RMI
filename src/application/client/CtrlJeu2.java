package application.client;

import java.rmi.RemoteException;

import application.serveur.CtrlServeur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class CtrlJeu2 {

	@FXML Canvas canvas;


	public void start() throws Exception {
		GraphicsContext gc = canvas.getGraphicsContext2D();


		Timeline tm = new Timeline(new KeyFrame(Duration.millis(10), tmp -> {
			try {
				run(gc);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
		}));
		tm.setCycleCount(Timeline.INDEFINITE);
		canvas.setOnMouseMoved(e ->  {
			try {
				CtrlClient2.stub.setPlayerTwoY(e.getY());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});
		tm.play();
		
	}

	private void run(GraphicsContext gc) throws RemoteException {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 800, 600);
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font(25));
		if(CtrlClient2.stub.getGameStarted()) {
			gc.fillOval(CtrlClient2.stub.getBallX(), CtrlClient2.stub.getBallY(), CtrlServeur.BALL_R, CtrlServeur.BALL_R);
		} else {
			gc.setStroke(Color.YELLOW);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.strokeText("Waiting to Start", 800 / 2, 600 / 2);
		}
		gc.fillText(CtrlClient2.stub.getScoreOne() + "\t\t\t\t\t\t\t\t" + CtrlClient2.stub.getScoreTwo(), 800 / 2, 100);
		gc.fillRect(CtrlClient2.stub.getPlayerTwoX(), CtrlClient2.stub.getPlayerTwoY(), CtrlServeur.PLAYER_WIDTH, CtrlServeur.PLAYER_HEIGHT);
		gc.fillRect(CtrlClient2.stub.getPlayerOneX(), CtrlClient2.stub.getPlayerOneY(), CtrlServeur.PLAYER_WIDTH, CtrlServeur.PLAYER_HEIGHT);
	}





}
