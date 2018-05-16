package sample;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import org.jetbrains.annotations.Contract;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.nio.file.spi.FileTypeDetector;

public final class ImageSelector extends Application {

    private Desktop desktop = Desktop.getDesktop();
    //private Images
    String imagePath="";
    String imagePath2="";
    String extension;


    @Override
    public void start(final Stage stage) {
        stage.setTitle("File Chooser Sample");

        final FileChooser fileChooser = new FileChooser();

        final Button openButton = new Button("Choose a Picture...");
        final Button originalImageButton = new Button("Open original Picture...");
        final Button changedImageButton = new Button("Save and Open changed Picture...");

        //final Button openMultipleButton = new Button("Open Pictures...");

        ImageView originalImageView = new ImageView();
        originalImageView.setFitWidth(500);
        originalImageView.setFitHeight(500);
        originalImageView.setPreserveRatio(true);

        ImageView changedImageView = new ImageView();
        changedImageView.setFitWidth(500);
        changedImageView.setFitHeight(500);
        changedImageView.setPreserveRatio(true);

        final Image[] originalImage = {new Image("file:C:\\Users\\dell\\IdeaProjects\\tech_mik2\\src\\sample\\flower.JPG")};


      //  BufferedImage bufferedOriginalImage = null;
       BufferedImage bufferedOriginalImage = new BufferedImage(100, 100,
              BufferedImage.TYPE_INT_RGB);

        //do something to populate the image
        //such as
        Color myWhite = new Color(255, 255, 255); // Color white
        int rgb = myWhite.getRGB();

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    bufferedOriginalImage.setRGB(i, j, rgb);
                }
            }
       // bufferedOriginalImage.setRGB(10,10,2560); //set your own pixels color

        final BufferedImage[] bufferedChangedImage = {new BufferedImage(100, 100,
                BufferedImage.TYPE_INT_RGB)};
       // bufferedChangedImage[0].setRGB(10,10,1); //set your own pixels color
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                bufferedChangedImage[0].setRGB(i, j, rgb);
            }
        }


        bufferedOriginalImage = javafx.embed.swing.SwingFXUtils.fromFXImage(originalImage[0], bufferedOriginalImage );
        RGBtoBit rgBtoBit = new RGBtoBit();

        final int[][] filter = {{1, 2, 1, 2, 4, 2, 1, 2, 1}};
        final int[] filterWidth = {3};
        /*
        final int[] filter1 = {1, 2, 1, 2, 4, 2, 1, 2, 1};
        final int[] filter2 = {2,4,2, 4,8,4, 8,16,8, 4,8,4 , 2,4,2};
        final int[] filter3 = {2,4,2, 4,8,4, 8,16,8,  16,32,16, 8,16,8, 4,8,4 , 2,4,2};
        final int filterWidth1 = 3;
        final int filterWidth2 = 5;
        final int filterWidth3 = 7;
        final ChoiceBox<String> box = new ChoiceBox<String>();
        box.getItems().add("3");
        box.getItems().add("5");
        box.getItems().add("7");

        box.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String number, String number2) {
                switch (number2){
                    case "3":
                             filter[0] = filter1;
                             filterWidth[0][0] = filterWidth1;
                        break;
                    case "5":
                        filter[0] = filter2;
                        filterWidth[0][0] = filterWidth2;
                        break;
                    case "7":
                        filter[0] = filter3;
                        filterWidth[0][] = filterWidth3;
                        break;
            }
        }});
                                                                  }
*/
        final int[] currentNumber = {0};
        //final ChoiceBox<String> maskBox = new ChoiceBox<String>();
        final ChoiceBox<String> box = new ChoiceBox<String>();

        box.getItems().add("1");
        box.getItems().add("2");
        box.getItems().add("3");
        box.getItems().add("4");
        box.getItems().add("5");
        //maskBox.getItems().add("Gauss");
       // maskBox.getItems().add("Laplace");
        //maskBox.getItems().add("3");


        final int currentNumberInt[] = {3,5,7,9,11};

        final Image changedImage[] = {new Image("file:C:\\Users\\dell\\IdeaProjects\\tech_mik2\\src\\sample\\flower.JPG")};
/*
        maskBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                currentNumber[0] = currentNumberInt[number2.intValue()];
            }
        });
        */
        box.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
               // if (number2.equals("Gauss")) {
                    currentNumber[0] = currentNumberInt[number2.intValue()];
                    switch (currentNumber[0]) {
                        case 3:
                            filterWidth[0] = 3;
                            filter[0] = new int[]{1, 2, 1,
                                    2, 4, 2,
                                    1, 2, 1};

                            BufferedImage tempBuffImg = null;
                            tempBuffImg = javafx.embed.swing.SwingFXUtils.fromFXImage(originalImage[0], tempBuffImg);

                            bufferedChangedImage[0] = rgBtoBit.blur(tempBuffImg, filter[0], filterWidth[0]);
                            changedImage[0] = javafx.embed.swing.SwingFXUtils.toFXImage(bufferedChangedImage[0], null);
                            changedImageView.setImage(changedImage[0]);

                            break;
                        case 5:

                            filterWidth[0] = 5;
                            filter[0] = new int[]{0, 2, 4, 2, 0,
                                    1, 4, 8, 4, 1,
                                    2, 8, 16, 8, 2,
                                    1, 4, 8, 4, 1,
                                    0, 2, 4, 2, 0};

                            tempBuffImg = null;
                            tempBuffImg = javafx.embed.swing.SwingFXUtils.fromFXImage(originalImage[0], tempBuffImg);

                            bufferedChangedImage[0] = rgBtoBit.blur(tempBuffImg, filter[0], filterWidth[0]);
                            changedImage[0] = javafx.embed.swing.SwingFXUtils.toFXImage(bufferedChangedImage[0], null);
                            changedImageView.setImage(changedImage[0]);

                            break;
                        case 7:
                            filterWidth[0] = 7;
                            filter[0] = new int[]{
                                    1, 1, 2, 2, 2, 1, 1,
                                    1, 2, 2, 4, 2, 2, 1,
                                    2, 2, 4, 8, 4, 2, 2,
                                    2, 4, 8, 16, 8, 4, 2,
                                    2, 2, 4, 8, 4, 2, 2,
                                    1, 2, 2, 4, 2, 2, 1,
                                    1, 1, 2, 2, 2, 1, 1};

                            tempBuffImg = null;
                            tempBuffImg = javafx.embed.swing.SwingFXUtils.fromFXImage(originalImage[0], tempBuffImg);

                            bufferedChangedImage[0] = rgBtoBit.blur(tempBuffImg, filter[0], filterWidth[0]);
                            changedImage[0] = javafx.embed.swing.SwingFXUtils.toFXImage(bufferedChangedImage[0], null);
                            changedImageView.setImage(changedImage[0]);

                            break;


                   // if (number2.equals("Laplace")) {
                        //currentNumber[0] = currentNumberInt[number2.intValue()];
                        //switch (currentNumber[0]) {
                        case 9:
                            filterWidth[0] = 3;
                            filter[0] = new int[]{
                                    1,  1,	1,
                                    1,	2,  1,
                                    1,	1,	1};

                            tempBuffImg = null;
                            tempBuffImg = javafx.embed.swing.SwingFXUtils.fromFXImage(originalImage[0], tempBuffImg);

                            bufferedChangedImage[0] = rgBtoBit.blur(tempBuffImg, filter[0], filterWidth[0]);
                            changedImage[0] = javafx.embed.swing.SwingFXUtils.toFXImage(bufferedChangedImage[0], null);
                            changedImageView.setImage(changedImage[0]);

                            break;
                        case 11:
                            filterWidth[0] = 3;
                            filter[0] = new int[]{
                                    0,  -1,	0,
                                    -1,	4,  -1,
                                    0,	-1,	0};

                            tempBuffImg = null;
                            tempBuffImg = javafx.embed.swing.SwingFXUtils.fromFXImage(originalImage[0], tempBuffImg);

                            bufferedChangedImage[0] = rgBtoBit.blur(tempBuffImg, filter[0], filterWidth[0]);
                            changedImage[0] = javafx.embed.swing.SwingFXUtils.toFXImage(bufferedChangedImage[0], null);
                            changedImageView.setImage(changedImage[0]);

                            break;
                    }
                }


        });

        //currentNumberInt = currentNumber.intValue();
        /*public void checkNumber(int[] currentNumber, int filterWidth,int[] filter) {
        switch (currentNumber[0]) {
            case 3:
                filterWidth = 3;
                filter = new int[]{1, 2, 1, 2, 4, 2, 1, 2, 1};


                break;
            case 5:
                filterWidth = 5;
                filter = new int[]{2, 4, 2, 4, 8, 4, 8, 16, 8, 4, 8, 4, 2, 4, 2};
                break;
            case 7:
                filterWidth = 7;
                filter = new int[]{2, 4, 2, 4, 8, 4, 8, 16, 8, 16, 32, 16, 8, 16, 8, 4, 8, 4, 2, 4, 2};
                break;
        }
        //}
        //this.checkNumber(currentNumber,filterWidth,filter);
*/

        bufferedChangedImage[0] = rgBtoBit.blur(bufferedOriginalImage, filter[0], filterWidth[0]);




        changedImage[0] = javafx.embed.swing.SwingFXUtils.toFXImage(bufferedChangedImage[0],  null);

        final File[] file = {null};
        //final Image[] changedImage = {new Image("file:C:\\Users\\dell\\IdeaProjects\\tech_mik2\\src\\sample\\flower.JPG")};
        final File[] changedFile = {null};

        //BufferedImage finalBufferedOriginalImage = bufferedOriginalImage;
        FileTypeDetector fileTypeDetector = new FileTypeDetector() {
            @Override
            public String probeContentType(Path path) throws IOException {
                return null;
            }
        };
        //final String[] fileType = {null};
     //final Path[] path = {FileSystems.getDefault().getPath(imagePath)};



        openButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        file[0] = fileChooser.showOpenDialog(stage);
                        if (file[0] != null ) {
                            //openFile(file);

                            imagePath = "file:"+ file[0].getAbsolutePath();
                            extension = imagePath.substring(imagePath.lastIndexOf(".") + 1);

                            if (extension.equals("jpeg")){
                                extension="jpg";
                            }
                            if (extension.equals("JPG")){
                                extension="jpg";
                            }
                            if (extension.equals("PNG")){
                                extension="png";
                            }


                            if (extension.equals("png")||extension.equals("jpg")) {

                                //imagePath2 = file[0].getAbsolutePath();

                                //path[0] = FileSystems.getDefault().getPath(imagePath2);
                                System.out.println("value of imagePath: " + imagePath);
                                //System.out.println("value of imagePath2: " + path[0]);
/*
                            try {
                                fileType[0] = fileTypeDetector.probeContentType(path[0]);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            */
                                // System.out.println("file type: "+ fileType[0]);
                                originalImage[0] = new Image(imagePath);
                                originalImageView.setImage(originalImage[0]);
                                BufferedImage tempBuffImg = null;
                                tempBuffImg = javafx.embed.swing.SwingFXUtils.fromFXImage(originalImage[0], tempBuffImg);

                                bufferedChangedImage[0] = rgBtoBit.blur(tempBuffImg, filter[0], filterWidth[0]);
                                changedImage[0] = javafx.embed.swing.SwingFXUtils.toFXImage(bufferedChangedImage[0], null);
                                changedImageView.setImage(changedImage[0]);


                            }


                        }
                    }
                });

        originalImageButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        if(file[0]!=null)
                            openFile(file[0]);
                        }
                    }
                );
        changedImageButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                            changedFile[0] = fileChooser.showSaveDialog(stage);
                            if (changedFile[0]!=null &&extension.equals("png")) {
                                try {
                                    System.out.println("extenstion " +extension);
                                    bufferedChangedImage[0] = javafx.embed.swing.SwingFXUtils.fromFXImage(changedImage[0], bufferedChangedImage[0]);
                                    ImageIO.write(bufferedChangedImage[0], extension, changedFile[0]);
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                                openFile(changedFile[0]);
                            } else if (changedFile[0]!=null &&extension.equals("jpg")) {
                                    try {
                                        System.out.println("extenstion " +extension);


                                        final int[] RGB_MASKS = {0xFF0000, 0xFF00, 0xFF};
                                        final ColorModel RGB_OPAQUE =
                                                new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);

                                        // ...


                                        BufferedImage img = bufferedChangedImage[0];

                                        PixelGrabber pg = new PixelGrabber(img, 0, 0, -1, -1, true);
                                        pg.grabPixels();
                                        int width = pg.getWidth(), height = pg.getHeight();

                                        DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
                                        WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
                                        BufferedImage bi = new BufferedImage(RGB_OPAQUE, raster, false, null);

                                        ImageIO.write(bi, "jpg", changedFile[0]);

                                        //bufferedChangedImage[0] = javafx.embed.swing.SwingFXUtils.fromFXImage(changedImage[0], bufferedChangedImage[0]);
                                        //ImageIO.write(bufferedChangedImage[0], extension, changedFile[0]);
                                    } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                    } catch (InterruptedException e1) {
                                        e1.printStackTrace();
                                    }
                                openFile(changedFile[0]);
                            }
                    }

                }
        );

        final GridPane inputGridPane = new GridPane();
        final GridPane outputGridPane = new GridPane();

        originalImageView.setImage(originalImage[0]);
        changedImageView.setImage(changedImage[0]);

        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(box, 1, 0);
        GridPane.setConstraints(originalImageView, 0, 0);
        GridPane.setConstraints(originalImageButton, 0, 1);
        GridPane.setConstraints(changedImageView, 1,0);
        GridPane.setConstraints(changedImageButton, 1, 1);

        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        outputGridPane.setHgap(6);
        outputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton ,box);
        outputGridPane.getChildren().addAll(originalImageView,changedImageView,changedImageButton,originalImageButton);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane,outputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));


        stage.setScene(new Scene(rootGroup));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    ImageSelector.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
    

}