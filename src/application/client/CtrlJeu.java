package application.client;

import java.rmi.RemoteException;
import java.util.Random;

import application.serveur.CtrlServeur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CtrlJeu {

	@FXML Canvas canvas;

	public void start() throws Exception {
		GraphicsContext gc = canvas.getGraphicsContext2D();


		Timeline tm = new Timeline(new KeyFrame(Duration.millis(10), e -> {
			try {
				run(gc);
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
		}));
		tm.setCycleCount(Timeline.INDEFINITE);
		canvas.setOnMouseMoved(e ->  {
			try {
				CtrlClient.stub.setPlayerOneY(e.getY());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});
		canvas.setOnKeyReleased(e ->  {
			try {
				if(e.getCode() == KeyCode.R) {
					CtrlClient.stub.setScoreOne(0);
					CtrlClient.stub.setScoreTwo(0);
					CtrlClient.stub.setBallX(800 / 2);
					CtrlClient.stub.setBallY(600 / 2);
					CtrlClient.stub.setGameStarted(false);
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});
		canvas.setOnMouseClicked(e ->  {
			try {
				CtrlClient.stub.setGameStarted(true);
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
		if(CtrlClient.stub.getGameStarted()) {
			CtrlClient.stub.setBallX(CtrlClient.stub.getBallX()+CtrlClient.stub.getBallSpeedX());
			CtrlClient.stub.setBallY(CtrlClient.stub.getBallY()+CtrlClient.stub.getBallSpeedY());
			gc.fillOval(CtrlClient.stub.getBallX(), CtrlClient.stub.getBallY(), CtrlServeur.BALL_R, CtrlServeur.BALL_R);
		} else {
			gc.setStroke(Color.YELLOW);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.strokeText("Click to Start", 800 / 2, 600 / 2);
			CtrlClient.stub.setBallX(800 / 2);
			CtrlClient.stub.setBallY(600 / 2);
			CtrlClient.stub.setBallSpeedX(new Random().nextInt(2) == 0 ? 1: -1);
			CtrlClient.stub.setBallSpeedY(new Random().nextInt(2) == 0 ? 1: -1);
		}
		if(CtrlClient.stub.getBallY() > 600 || CtrlClient.stub.getBallY() < 0) CtrlClient.stub.setBallSpeedY(CtrlClient.stub.getBallSpeedY()*-1);
		if(CtrlClient.stub.getBallX() < CtrlClient.stub.getPlayerOneX() - CtrlServeur.PLAYER_WIDTH) {
			CtrlClient.stub.setScoreTwo(CtrlClient.stub.getScoreTwo()+1);
			CtrlClient.stub.setGameStarted(false);
		}
		if(CtrlClient.stub.getBallX() > CtrlClient.stub.getPlayerTwoX() + CtrlServeur.PLAYER_WIDTH) {  
			CtrlClient.stub.setScoreOne(CtrlClient.stub.getScoreOne()+1);
			CtrlClient.stub.setGameStarted(false);
		}
		if( ((CtrlClient.stub.getBallX() + CtrlServeur.BALL_R > CtrlClient.stub.getPlayerTwoX()) && CtrlClient.stub.getBallY() >= CtrlClient.stub.getPlayerTwoY() && CtrlClient.stub.getBallY() <= CtrlClient.stub.getPlayerTwoY() + CtrlServeur.PLAYER_HEIGHT) || 
				((CtrlClient.stub.getBallX() < CtrlClient.stub.getPlayerOneX() + CtrlServeur.PLAYER_WIDTH) && CtrlClient.stub.getBallY() >= CtrlClient.stub.getPlayerOneY() && CtrlClient.stub.getBallY() <= CtrlClient.stub.getPlayerOneY() + CtrlServeur.PLAYER_HEIGHT)) {

			
			if(CtrlClient.stub.getBallSpeedX() > 0) {
				CtrlClient.stub.setBallSpeedX(CtrlClient.stub.getBallSpeedX()+1);
				
			}else {
				CtrlClient.stub.setBallSpeedX(CtrlClient.stub.getBallSpeedX()-1);
			}
			if(CtrlClient.stub.getBallSpeedY() > 0) {
				CtrlClient.stub.setBallSpeedY(CtrlClient.stub.getBallSpeedY()+1);
			}else {
				CtrlClient.stub.setBallSpeedY(CtrlClient.stub.getBallSpeedY()-1);
			}
			
			CtrlClient.stub.setBallSpeedX(CtrlClient.stub.getBallSpeedX()*-1);
			
			//CtrlClient.stub.setBallSpeedY(CtrlClient.stub.getBallSpeedY()*-1);
		}
		gc.fillText(CtrlClient.stub.getScoreOne() + "\t\t\t\t\t\t\t\t" + CtrlClient.stub.getScoreTwo(), 800 / 2, 100);
		gc.fillRect(CtrlClient.stub.getPlayerTwoX(), CtrlClient.stub.getPlayerTwoY(), CtrlServeur.PLAYER_WIDTH, CtrlServeur.PLAYER_HEIGHT);
		gc.fillRect(CtrlClient.stub.getPlayerOneX(), CtrlClient.stub.getPlayerOneY(), CtrlServeur.PLAYER_WIDTH, CtrlServeur.PLAYER_HEIGHT);
	}





}
