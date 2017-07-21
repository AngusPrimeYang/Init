package test;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * �ɥR(magiclen):https://magiclen.org/javafx-animation-effect/
 * @author Administrator
 *
 */
public class FXController {
	
	//�����j�p
    public static final int width = 800;
    public static final int height = 600;
 
    //�ʵe�ɶ�
    public static final long duration = 30000;
 
    //��μƶq
    public static final int count = 30;

    @FXML
    private Text actiontarget;
    
    @FXML
    private GridPane gp;

    @FXML
    protected void submit(ActionEvent event) {
    	actiontarget.setText("�n�J���\");
    	
    	final Stage dialog = new Stage();
    	dialog.initModality(Modality.APPLICATION_MODAL);
    	
    	Group root = new Group();
    	Scene scene = new Scene(root, width, height, Color.BLACK);
    	
    	//�إ��C��x��colors�A�j�p�����]�w�A�C�⬰�q���U��k�W�����h
    	Rectangle colors = new Rectangle(0, 0,
    		new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new Stop[]{
    		    new Stop(0, Color.web("#f8bd55")),
    		    new Stop(0.14, Color.web("#c0fe56")),
    		    new Stop(0.28, Color.web("#5dfbc1")),
    		    new Stop(0.43, Color.web("#64c2f8")),
    		    new Stop(0.57, Color.web("#be4af7")),
    		    new Stop(0.71, Color.web("#ed5fc2")),
    		    new Stop(0.85, Color.web("#ef504c")),
    		    new Stop(1, Color.web("#f2660f")),}));
    	
    	//��colors�j�p�H��Scene�����e���ܤ�(�p�G�����]�wcolors���j�p�A���򥦱N�u���b��j���ɭԤ~�|���i)
    	colors.widthProperty().bind(scene.widthProperty());
    	colors.heightProperty().bind(scene.heightProperty());
    	
    	//ø�s���
    	Group circles = new Group();
    	for (int i = 0; i < count; i++) {
    	    Circle circle = new Circle(Math.random() * 50 + 75, Color.web("white", 0.15)); //�إ߶�ΡA�b�|��75~125�A�C�⬰�զ�A���z����15%
    	    circle.setStrokeType(StrokeType.OUTSIDE); //�]�w�������
//    	    circle.setStroke(Color.web("white", 0.2)); //�]�w����C�⬰�զ�A���z����20%
    	    circle.setStrokeWidth(4); //�]�w��زʲ�
    	    circles.setEffect(new BoxBlur(10, 10, 3)); //�]�w�ҽk
    	    circles.getChildren().add(circle); //�N�s�إߪ���Υ[�J�s��
    	}
    	
    	//�s�@�ʵe
    	Timeline timeline = new Timeline(); //�ʵe�ɶ��b
    	circles.getChildren().forEach((circle) -> { //�N�Ҧ�����γ��]�ʵe
    	    //�s�@�_�l�B�פ����v��
    	    KeyFrame start = new KeyFrame(Duration.ZERO, new KeyValue(circle.translateXProperty(), Math.random() * scene.getWidth()), new KeyValue(circle.translateYProperty(), Math.random() * scene.getHeight()));
    	    KeyFrame end = new KeyFrame(new Duration(duration), new KeyValue(circle.translateXProperty(), Math.random() * scene.getWidth()), new KeyValue(circle.translateYProperty(), Math.random() * scene.getHeight()));
     
    	    //�[�J����v��
    	    timeline.getKeyFrames().addAll(start, end);
    	});
    	timeline.play(); //����ɶ��b�ʵe
    	
//    	colors.setBlendMode(BlendMode.SCREEN);
    	colors.setBlendMode(BlendMode.OVERLAY);
        
        root.getChildren().add(circles);
    	root.getChildren().add(colors);
    	
    	dialog.setScene(scene);
        dialog.show();
    }
}
