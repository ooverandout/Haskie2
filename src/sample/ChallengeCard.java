package sample;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.net.URL;
import java.awt.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.io.File;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class ChallengeCard {
    final double sizeX = 0, sizeY = 0;
    String  imgPath, blackImgPath, victorySoundPath, failSoundPath,password;
    VBox card=new VBox();
    Button cardButton = new Button();
    TextField passwordField = new TextField();
    ChallengeCard(){
        card.getChildren().addAll(cardButton,passwordField);
    }
    ChallengeCard(String password,String imgPath, String blackImgPath, String victorySoundPath, String failSoundPath) {
        this.blackImgPath = blackImgPath;
        this.imgPath = imgPath;
        this.victorySoundPath = victorySoundPath;
        this.failSoundPath = failSoundPath;

        //cardButton.setMaxSize(280,780);
        setImage(blackImgPath);
        cardButton.setDisable(true);
        cardButton.setPickOnBounds(true);
        cardButton.setMinWidth(300);
        cardButton.setMinHeight(620);
        cardButton.setOnAction(e->{
            playSound(failSoundPath);
        });
        card.getChildren().addAll(cardButton,passwordField);
         this.password=toHex(password);
         System.out.println(this.password);

         passwordField.setOnKeyReleased(e-> {
            if (isPasswordCorrect() && passwordField.getText().length() == 6) {
                playSound(victorySoundPath);
                setImage(imgPath);
                cardButton.setDisable(false);
                passwordField.setVisible(false);

            }
            else if (passwordField.getText().length() >= 6) {
                playSound(failSoundPath);
                passwordField.setText("");
            }


        });


    }

     VBox createCard() {
        VBox card=new VBox();
        Button cardButton = new Button("lalala");
        TextField password = new TextField();
        password.setOnKeyPressed(e-> {
                    if (isPasswordCorrect() && password.getText().length() == 6) {
                        playSound(victorySoundPath);
                        setImage(imgPath);
                    } else
                        playSound(failSoundPath);
                });

        card.getChildren().addAll(cardButton,password);
        return card;

    }



    public static String toHex(String arg) {
        return String.format("%x", new BigInteger(1, arg.getBytes(Charset.defaultCharset())));
    }
    private void playSound(String path){
        final URL resource = getClass().getResource(path);
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        card.getChildren().add(mediaView);
    }
        private void setImage(String path){
        /*javafx.scene.image.Image playI=new Image(path);
        ImageView iv1=new ImageView(playI);
        iv1.setFitHeight(700);
        iv1.setFitWidth(320);
        cardButton.setGraphic(iv1);8*/



            javafx.scene.image.Image playI=new Image(path);
            BackgroundImage bImage = new BackgroundImage(playI, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(cardButton.getWidth(), cardButton.getHeight(), true, true, true, false));
            ImageView iv1=new ImageView(playI);
            Background backGround = new Background(bImage);
            cardButton.setBackground(backGround);

    }
        boolean isPasswordCorrect() {
        if (passwordField.getText().equals(password))
                return true;
        return false;


    }
    void init(){
       // card.getChildren().addAll(cardButton,password);
    }
}

