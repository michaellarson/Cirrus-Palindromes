import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Palindrome extends Application {
	
	//Determines if string length is even or odd
	public static boolean isEven(String word){
		if(word.length() % 2 == 0)
			return true;
		return false;
	}
	
	//Returns the front half of a string including the middle char if the legnth is odd
	public static String getFrontHalf(String num){
		if(isEven(num))
			return num.substring(0, num.length() /2);
		return num.substring(0, (num.length() /2)+1);
	}
	
	//Finds highest palindrome number between two given numbers
	public static String findPalindrome(String highNum, String lowNum){
		highNum = highNum.replace(",", "");
		lowNum = lowNum.replace(",", "");
		if(Integer.parseInt(highNum) < Integer.parseInt(lowNum))
			return "ERROR. High Number can't be lower than Low Number.";
		if(highNum.indexOf('-') >= 0 || Integer.parseInt(highNum) == 0)
			return "ERROR. Negative numbers can never be palindromes.";
		if(Integer.parseInt(highNum) <= 10 && Integer.parseInt(highNum) - 1 > Integer.parseInt(lowNum))
			return Integer.toString(Integer.parseInt(highNum) - 1);
		String frontHalf = getFrontHalf(highNum);
		String palindrome = "";
		if(isEven(highNum)){
			palindrome = frontHalf + new StringBuilder(frontHalf).reverse().toString();
			if(Integer.parseInt(palindrome) <= Integer.parseInt(lowNum))
				return "No palindrome found.";
			else if(Integer.parseInt(palindrome) >= Integer.parseInt(highNum)){
				frontHalf = Integer.toString(Integer.parseInt(frontHalf) - 1);
				palindrome = frontHalf + new StringBuilder(frontHalf).reverse().toString();
				if(Integer.parseInt(palindrome) <= Integer.parseInt(lowNum))
					return "No palindrome found.";
			}
		}
		else{
			palindrome = frontHalf + new StringBuilder(frontHalf.substring(0, frontHalf.length()-1)).reverse().toString();
			if(Integer.parseInt(palindrome) <= Integer.parseInt(lowNum))
				return "No palindrome found.";
			else if(Integer.parseInt(palindrome) >= Integer.parseInt(highNum)){
				frontHalf = Integer.toString(Integer.parseInt(frontHalf) - 1);
				palindrome = frontHalf + new StringBuilder(frontHalf.substring(0, frontHalf.length()-1)).reverse().toString();
				if(Integer.parseInt(palindrome) <= Integer.parseInt(lowNum))
					return "No palindrome found.";
			}
		}
			
		return palindrome;
	}
	
	public static void main(String[] args){
        launch(args);
    }
	
	//UI Creation
	public void start(Stage primaryStage){
        primaryStage.setTitle("Palindrome Finder");
        GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text title = new Text("Palindrome Finder");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		title.setUnderline(true);
		grid.add(title, 0, 0, 2, 1);
		Label highLabel = new Label("High Number");
		grid.add(highLabel, 0, 1);
        TextField highNum = new TextField();
        highNum.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        grid.add(highNum, 1, 1);
        Label lowLabel = new Label("Low Number");
		grid.add(lowLabel, 0, 2);
        TextField lowNum = new TextField();
        lowNum.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        grid.add(lowNum, 1, 2);
        
        Button search = new Button("Find Palindrome");
        search.setMinWidth(100);
        grid.add(search, 0, 3, 2, 1);
        
        Label outLabel = new Label("Palindrome:");
        grid.add(outLabel, 0, 4);
        Text output = new Text();
        output.setWrappingWidth(200);
        grid.add(output, 1, 4);
        
        search.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event) {
                output.setText(findPalindrome(highNum.getText(),lowNum.getText()));
            }
        });
        
        Scene scene = new Scene(grid, 450, 240);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
