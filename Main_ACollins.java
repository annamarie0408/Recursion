//JavaFX project -- Recursion -- Anna Collins

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main_ACollins extends Application {

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Recursion"); //Sets title of GUI
        GridPane gridpane = new GridPane(); //Creates the GridPane

        //Creating the five buttons for the program
        Button dec2BinBtn = new Button("Decimal to Binary");
        Button dec2HexBtn = new Button("Decimal to Hex");
        Button bin2DecBtn = new Button("Binary to Decimal");
        Button hex2DecBtn = new Button("Hex to Decimal");
        Button clearBtn = new Button("CLEAR");

        //creating the textfield and label
        TextField textfield = new TextField();
        Label label1 = new Label();

        //Defining the location of the buttons, textfield, and label.
        gridpane.add(dec2BinBtn, 0, 0);
        gridpane.add(dec2HexBtn, 0, 1);
        gridpane.add(bin2DecBtn, 0, 2);
        gridpane.add(hex2DecBtn, 0, 3);
        gridpane.add(clearBtn, 0, 4);
        gridpane.add(textfield, 1,0);
        gridpane.add(label1, 1, 1);

        dec2BinBtn.setOnAction(new EventHandler<ActionEvent>() {
            //The Decimal to Binary button will convert Decimal to Binary.
            //If the user does not enter a number, it will give them an error in the Label
            @Override
            public void handle(ActionEvent event) {
                try{
                    int i = Integer.parseInt(textfield.getText());
                    int answer;
                    answer = decimal2Binary(i);
                    System.out.println(answer);
                    label1.setText("The binary form of " + String.valueOf(i) + " is " + String.valueOf(answer));
                }
                catch (NumberFormatException e){
                    label1.setText("ERROR: Please enter a numeric number!");
                }
                }
        });

        dec2HexBtn.setOnAction(new EventHandler<ActionEvent>() {
            //The Decimal to Hex button will convert Decimal to Hex
            //If the user does not enter a number, it will give them an error in the label
            @Override
            public void handle(ActionEvent event) {
                try{
                int i = Integer.parseInt(textfield.getText());
                String hexString = decimal2Hex(i);
                label1.setText("The hex version of " + String.valueOf(i) + " is " + hexString);
            }
                catch (NumberFormatException e){
                label1.setText("ERROR: Please enter a numeric number!");
            }
        }
        });

        bin2DecBtn.setOnAction(new EventHandler<ActionEvent>() {
            //No exception needed as this does not convert a number
            @Override
            public void handle(ActionEvent event) {
                String input = textfield.getText();
                int i = 0;
                int answer;
                answer = binary2Decimal(input, i);
                label1.setText("The binary value of \n" + input + " is " + String.valueOf(answer));
            }
        });

        hex2DecBtn.setOnAction(new EventHandler<ActionEvent>() {
            //The Hex to Dec button will convert Hex to Decimal
            //If the user does not enter a number, it will give them an error in the label
            @Override
            public void handle(ActionEvent event) {
                try {
                String input = textfield.getText();
                int decimalValue = hex2Decimal(input);
                label1.setText("The decimal value of \n" + input + " is " + String.valueOf(decimalValue));
            }
                catch (NumberFormatException e){
                    label1.setText("ERROR: Please enter a numeric number!");
                }
            }
        });


        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            //This is the clear button. It will clear the current textfield and Label
            @Override
            public void handle(ActionEvent event) {
                textfield.setText("");
                label1.setText("");
            }
        });


        Scene scene = new Scene(gridpane, 350, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static int decimal2Binary(int decimal_number)
    {
        //Decimal to Binary Recursion method
        if (decimal_number == 0)
            return 0;
        else
            return (decimal_number % 2 + 10 *
                    decimal2Binary(decimal_number / 2));
    }

    static int binary2Decimal(String input, int i)
    {
        //Binary to Decimal Recursion method
        int x = input.length();
        if (i == x-1)
            return input.charAt(i) - '0';

        // Add current tern and recur for
        // remaining terms
        return ((input.charAt(i) - '0') << (x-i-1)) +
                binary2Decimal(input, i+1);
    }

    private static int hex2Decimal(String input) {
        //Hex to Decimal Recursion method
        int decimal = 0;
        String hexCode = "0123456789ABCDEF";
        input = input.toUpperCase();
        int length = input.length();
        if (length > 0) {
            char ch = input.charAt(0);
            int digit = hexCode.indexOf(ch);
            String substring = input.substring(1);
            decimal = digit * (int) Math.pow(16, length - 1) + hex2Decimal(substring);
        }
        return decimal;
    }

    private static String decimal2Hex(int input) {
        //Decimal to Hex Recursion method
        StringBuilder builder = new StringBuilder();
        if (input > 0) {
            String hexNumber = decimal2Hex(input / 16);
            String hexCode = "0123456789ABCDEF";
            int hexDigit = input % 16;
            builder.append(hexNumber + hexCode.charAt(hexDigit));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
