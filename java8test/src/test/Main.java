package test;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		
//		J7TestFunc.test();
		
		J8TestFunc.test();
		
		//last. javaFX
		launch(args);
	}
	
	/**
	 * 補充(magiclen):https://magiclen.org/javafx-hello-javafx/
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		case1(primaryStage);
		
//		case2(primaryStage);
	}
	
	private void case1(Stage primaryStage) {
		//case 1 create new
		Button btn = new Button();
		btn.setText("關閉程式");
		btn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	
//		    	final Stage dialog = new Stage();
//	            dialog.initModality(Modality.APPLICATION_MODAL);
//			      //dialog.initModality(Modality.NONE);
//	            dialog.initOwner(primaryStage);
//		    	
//	            VBox dialogVbox = new VBox(5);
//	            dialogVbox.getChildren().add(new Text("This is a Dialog"));
//	            
//	            GridPane grid = new GridPane();
//	            grid.setHgap(10);
//	            grid.setVgap(12);
//	            grid.add(dialogVbox, 0, 4);
//	            
//	            Scene dialogScene = new Scene(grid, 150, 120);
//	            dialog.setScene(dialogScene);
//	            dialog.show();
		    	
		    	//https://magiclen.org/javafx-official-dialog/
		    	
		    	final Alert confirm = new Alert(AlertType.CONFIRMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
		    	confirm.setTitle("小提示"); //設定對話框視窗的標題列文字
		    	confirm.setHeaderText("關閉程式？"); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
		    	confirm.setContentText("請按下「確定」按鈕關閉程式。"); //設定對話框的訊息文字
		    	final Optional<ButtonType> opt = confirm.showAndWait();
		    	final ButtonType rtn = opt.get(); //可以直接用「alert.getResult()」來取代
		    	System.out.println(rtn);
		    	if (rtn == ButtonType.OK) {
		    	    //若使用者按下「確定」
		    	    Platform.exit(); // 結束程式
		    	} else if(rtn == ButtonType.CANCEL){
		    	    //若使用者按下「取消」，也可直接使用else
		    		final Alert alert = new Alert(AlertType.INFORMATION); // 實體化Alert對話框物件，並直接在建構子設定對話框的訊息類型
		    		alert.setTitle("小提示"); //設定對話框視窗的標題列文字
		    		alert.setHeaderText("很好！！"); //設定對話框視窗裡的標頭文字。若設為空字串，則表示無標頭
		    		alert.setContentText("請按下「確定」按鈕。"); //設定對話框的訊息文字
		    		alert.showAndWait(); //顯示對話框，並等待對話框被關閉時才繼續執行之後的程式
		    	}
		    	
		    	//選擇對話框
//		    	final ChoiceDialog<String> choiceDialog = 
//		    			new ChoiceDialog(String... array);
		    	//輸入對話框
//		    	final TextInputDialog textInputDialog = 
//		    			new TextInputDialog(String str);
		    }
		});
		
		StackPane root = new StackPane();
		root.getChildren().add(btn);
		
		Scene scene = new Scene(root, 300, 250);
		
//		scene.getStylesheets().add(
//				getClass().getResource("Login.css").toExternalForm());
//		btn.setId("btn_exit");
		
		primaryStage.setTitle("Hello JavaFX!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void case2(Stage primaryStage) throws IOException {
		//case 2: load by xml file
		java.net.URL url = getClass().getResource("Login.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		Parent xmlRoot = loader.load();//FXMLLoader.load(url)
		
//		FXController controller = (FXController)loader.getController();

		//用讀進來FXML的作為Scene的root node
		Scene xmlScene = new Scene(xmlRoot, 300, 275);
		primaryStage.setTitle("Hello JavaFX!");
		primaryStage.setScene(xmlScene);
		primaryStage.show();
	}
	

//	private static String bytesToHexString(final byte[] bytes) {
//        final StringBuilder sb = new StringBuilder();
//        for (final byte b : bytes) {
//            if ((b & 0xF0) == 0) {
//                sb.append("0");
//            }
//            sb.append(Integer.toHexString(b & 0x00FF));
//        }
//        return sb.toString().toLowerCase();
//    }
// 
//    public static void main(final String[] args) throws Exception {
// 
//        final Scanner in = new Scanner(System.in);
//        final String S = in.next();
// 
//        final MessageDigest md = MessageDigest.getInstance("SHA-256");
//        final byte[] sha256 = md.digest(S.getBytes("UTF-8"));
//        System.out.println(bytesToHexString(sha256));
//    }
}
