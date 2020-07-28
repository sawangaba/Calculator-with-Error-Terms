
package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.math.BigDecimal;
import java.math.MathContext;
import uNumberLibrary.*;

import calculator.BusinessLogic;

/**
 * <p> Title: UserInterface Class. </p>
 *
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.Also this class 
 * Holds some various calculations that are processed in the result.
 * </p>
 *
 * <p> Copyright: Lynn Robert Carter and Sawan © 2018 </p>
 *
 * @BaselineAuthor Lynn Robert Carter
 * @AdditionAuthor Sawan 
 *
 * @version 9.00	2018-04-28 Measured values and error terms are enhanced
 * @version 10.00	2018-10-06 Calculator is enhanced by addition of UNumber Library i.e. result is more precise now
 */

public class UserInterface {

	/**********************************************************************************************

	Attributes

	**********************************************************************************************/

	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;

	// These are the application values required by the user interface
	private Label label_DoubleCalculator = new Label("UNumber with Square Root");
	private Label label_Operand1 = new Label("First operand ");
	private Label label_ErrorOperand1 = new Label("Error Term");
	private Label label_Error1 = new Label("");
	private Label label_Error2 = new Label("");
	private TextField text_Operand1 = new TextField();
	private TextField error_text_Operand1 = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private Label label_ErrorOperand2 = new Label("Error Term");
	private Label label1 = new Label("\u00B1");
	private Label label2 = new Label("\u00B1");
	private Label label3 = new Label("\u00B1");
	private TextField text_Operand2 = new TextField();
	private TextField error_text_Operand2 = new TextField();
	private Label label_Result = new Label("Result");
	private Label label_ErrorResult = new Label("Error Term");
	private TextField text_Result = new TextField();
	private TextField error_text_Result = new TextField();
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand2 = new Label("");
	private Label label_errResult = new Label("");
	private Button button_sqrt = new Button("\u221A");
	 private Text errMVPart1 = new Text();                // All errMV parts are associated with text Flow
	    private Text errMVPart2 = new Text();
	    private Text errMVPart3 = new Text();
	    private Text errMVPart4 = new Text();
	    private Text errETPart1 = new Text();
	    private Text errETPart2 = new Text();
	    private Text errETPart3 = new Text();
	    private Text errETPart4 = new Text();
	    private TextFlow errMeasuredValue;
	    private TextFlow errMeasuredValue2;
	    private TextFlow errErrorTerm1;
	    private TextFlow errErrorTerm2;
	// If the multiplication and/or division symbols do not display properly, replace the
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used.

	private double buttonSpace;		// This is the white space between the operator buttons.

	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();


	/**********************************************************************************************

	Constructors

	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {

		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;
		errMVPart1.setFill(Color.BLACK);
	    errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMVPart2.setFill(Color.RED);
	    errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMVPart3.setFill(Color.BLACK);
	    errMVPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errMVPart4.setFill(Color.RED);
	    errMVPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errMeasuredValue = new TextFlow(errMVPart1, errMVPart2);
		errMeasuredValue.setMinWidth(Calculator.WINDOW_WIDTH-10);
		errMeasuredValue.setLayoutX(22.5);
		errMeasuredValue.setLayoutY(112.5);
		errMeasuredValue2 = new TextFlow(errMVPart3, errMVPart4);
		errMeasuredValue2.setMinWidth(Calculator.WINDOW_WIDTH-10);
		errMeasuredValue2.setLayoutX(22.5);
		errMeasuredValue2.setLayoutY(207.5);
		errETPart1.setFill(Color.BLACK);
	    errETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errETPart2.setFill(Color.RED);
	    errETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errETPart3.setFill(Color.BLACK);
	    errETPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
	    errETPart4.setFill(Color.RED);
	    errETPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
	    errErrorTerm1 = new TextFlow(errETPart1, errETPart2);
		errErrorTerm1.setMinWidth(Calculator.WINDOW_WIDTH-10);
		errErrorTerm1.setLayoutX(810);
		errErrorTerm1.setLayoutY(112.5);
        errErrorTerm2 = new TextFlow(errETPart3, errETPart4); // Establish an error message for the second operand just above it with, left aligned
		errErrorTerm2.setMinWidth(Calculator.WINDOW_WIDTH-10);
		errErrorTerm2.setLayoutX(810);
		errErrorTerm2.setLayoutY(212.5);
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_DoubleCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 50);
		setupLabelUI(label_ErrorOperand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 800, 50);
		setupLabelUI(label1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 730, 80);
		setupLabelUI(label2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 730, 180);
		setupLabelUI(label3, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 730, 280);
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18,666, Pos.BASELINE_LEFT, 10, 80, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		// Move focus to the second operand when the user presses the enter (return) key

			text_Operand1.setOnAction((event) -> { error_text_Operand1.requestFocus();
			if(text_Operand1.getText().isEmpty()){  // check if operand 1 is empty 
				text_Operand1.setText("0.0");       // display 0
			}
			Double a= Double.parseDouble(text_Operand1.getText()); // store operand2 value in double
			if(a>1e7){
				// check if value is greater than 1e7
				
				text_Operand1.setText(Double.toString(a)); // display double form of value
			}
			if(a<1e7){
				text_Operand1.setText(Double.toString(a));
			}
			}
			);

		setupTextUI(error_text_Operand1, "Arial", 18,700, Pos.BASELINE_LEFT, 800, 80, true);
		error_text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1ErrorTerm(); });
		// Move focus to the second operand when the user presses the enter (return) key


				error_text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus();
				if(error_text_Operand1.getText().isEmpty()){
					error_text_Operand1.setText("5");
				}
				
				});


		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 137.5);
		label_errOperand1.setTextFill(Color.RED);

		setupLabelUI(label_Error1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 800, 137.5);

		label_Error1.setTextFill(Color.RED);

		setupLabelUI(label_Error2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 800, 237.5);

		label_Error2.setTextFill(Color.RED);

		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 155);
		setupLabelUI(label_ErrorOperand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 800, 155);
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, 666, Pos.BASELINE_LEFT, 10, 180, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		// Move the focus to the result when the user presses the enter (return) key


				text_Operand2.setOnAction((event) -> { error_text_Operand2.requestFocus();
				if(text_Operand2.getText().isEmpty()){
					text_Operand2.setText("0.0");
				}
				Double a= Double.parseDouble(text_Operand2.getText()); // store operand2 value in double
				if(a>1e7){
					                             // check if value is greater than 1e7
					
					text_Operand2.setText(Double.toString(a));  // display double form of value
				}
				if(a<1e7){                       // check if value is smaller than 1e7
					text_Operand2.setText(Double.toString(a));     // display double form of value
				}
				});


		setupTextUI(error_text_Operand2, "Arial", 18, 700, Pos.BASELINE_LEFT, 800, 180, true);
		error_text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2ErrorTerm();});
		// Move focus to the second operand when the user presses the enter (return) key
		error_text_Operand2.setOnAction((event) -> {
		if(error_text_Operand2.getText().isEmpty()){
			error_text_Operand2.setText("5");
		}
		});
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 237.5);
		label_errOperand2.setTextFill(Color.RED);

		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 255);
		setupLabelUI(label_ErrorResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 800, 255);
		// Establish the result output field.  It is not editable, so the text can be selected and copied,
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 18,666, Pos.BASELINE_LEFT, 10, 280, false);
		setupTextUI(error_text_Result, "Arial", 18, 700, Pos.BASELINE_LEFT, 800, 280, false);

		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 647.5, 215);
		label_errResult.setTextFill(Color.RED);

		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 400);
		button_Add.setOnAction((event) -> {addOperands(); });

		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 400);
		button_Sub.setOnAction((event) -> { subOperands(); });

		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 400);
		button_Mpy.setOnAction((event) -> { mpyOperands(); });

		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 400);
		button_Div.setOnAction((event) -> { divOperands(); });

		setupButtonUI(button_sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 400);
		button_sqrt.setOnAction((event) -> { 
			text_Operand2.setText("");
			error_text_Operand2.setText("");
			sqrtOperands(); });

		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_DoubleCalculator,label1,label2,
				label3, label_Operand1,label_ErrorOperand1,label_ErrorOperand2, text_Operand1
				,error_text_Operand1, label_errOperand1,
				label_Operand2, text_Operand2,error_text_Operand2,
				label_errOperand2, label_Result,label_ErrorResult,
				text_Result,error_text_Result, label_errResult,
				button_Add, button_Sub, button_Mpy, button_Div, button_sqrt,
				errMeasuredValue, errMeasuredValue2,errErrorTerm1,errErrorTerm2,label_Error1, label_Error2 );

	}

	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);
	}

	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);
		t.setEditable(e);
	}

	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);
	}


	/**********************************************************************************************

	User Interface Actions

	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */

	private void setOperand1() {



		errMVPart1.setText("");
		errMVPart2.setText("");
		performGo();
		text_Result.setText("");
		error_text_Result.setText("");// Any change of an operand probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.setOperand1(text_Operand1.getText())) {	// Set the operand and see if there was an error
			label_errOperand1.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			label_errOperand1.setText(perform.getOperand1ErrorMessage());	// the error message that was generated

	}
	private void setOperand1ErrorTerm() {

		errETPart1.setText("");
		errETPart2.setText("");
		if (perform.setOperand1(error_text_Operand1.getText())) {
			errETPart1.setText("");
			errETPart2.setText("");
			text_Result.setText("");
			error_text_Result.setText(""); // Set the operand and see if there was an error
			label_Error1.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			label_Error1.setText(perform.getOperand1ErrorMessage());
		performGo();
		}
		private void performGo() {
			String errMessage = CalculatorValue.checkMeasureValue(text_Operand1.getText());
		if (errMessage != "") {

			label_errOperand1.setText(CalculatorValue.errorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			errMVPart1.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			errMVPart2.setText("\u21EB");
		}


			/* This is the code for implementing ErrorTerm recognition
			 */
			errMessage = CalculatorValue.checkErrorTerm(error_text_Operand1.getText());
			if (errMessage != "") {

				label_Error1.setText(CalculatorValue.errorTermErrorMessage);
				String input = CalculatorValue.errorTermInput;
				if (CalculatorValue.errorTermIndexofError <= -1) return;
				errETPart1.setText(input.substring(0, CalculatorValue.errorTermIndexofError));
				errETPart2.setText("\u21EB");
			}}


	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		errMVPart3.setText("");
		errMVPart4.setText("");
		performGo1();
		text_Result.setText("");
		error_text_Result.setText("");                      // Any change of an operand probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.setOperand2(text_Operand2.getText())) {	// Set the operand and see if there was an error
			label_errOperand2.setText("");
			errMVPart4.setText("");
			errMVPart3.setText("");
			// If no error, clear this operands error
			if (text_Operand2.getLength() == 0){
				label_errOperand2.setText("");

				                                            // If the other operand is empty, clear its error
			}

			                                                // as well.
		}
		else 												                // If there's a problem with the operand, display
			label_errOperand2.setText(perform.getOperand2ErrorMessage());	// the error message that was generated

	}

	private void setOperand2ErrorTerm() {

		errETPart3.setText("");
		errETPart4.setText("");
		if (perform.setOperand1(error_text_Operand2.getText())) {
			errETPart3.setText("");
			errETPart4.setText("");
			text_Result.setText("");
			error_text_Result.setText("");              // Set the operand and see if there was an error
			label_Error2.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			label_Error2.setText(perform.getOperand2ErrorMessage());
		performGo1();
		}

	private void performGo1() {
	String errMessage = CalculatorValue.checkMeasureValue(text_Operand2.getText());
	if (errMessage != "") {

	label_errOperand2.setText(CalculatorValue.errorMessage);
	if (CalculatorValue.measuredValueIndexofError <= -1) return;
	String input = CalculatorValue.measuredValueInput;
	errMVPart3.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
	errMVPart4.setText("\u21EB");
}



	errMessage = CalculatorValue.checkErrorTerm(error_text_Operand2.getText());
	if (errMessage != "") {

		label_Error2.setText(CalculatorValue.errorTermErrorMessage);
		String input = CalculatorValue.errorTermInput;
		if (CalculatorValue.errorTermIndexofError <= -1) return;
		errETPart3.setText(input.substring(0, CalculatorValue.errorTermIndexofError));
		errETPart4.setText("\u21EB");
	}}
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there are issues
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 *
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
				label_errOperand2.setText(errorMessage2);		// and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
			label_errOperand2.setText(errorMessage2);			// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}


		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the second. Both
			label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}

		return false;											// Signal there are no issues with the operands
	}

	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, divide and square root) are pressed.
	 */

	/**********
	 * This is the add routine
	 * @return the addition in the result operands
	 *
	 */
	private  void addOperands(){
		if (binaryOperandIssues())                  // If there are issues with the operands, return
			return;
		if(label_errOperand1.getText().isEmpty()==false || label_errOperand2.getText().isEmpty()==false ||
				label_Error1.getText().isEmpty()==false ||label_Error2.getText().isEmpty()==false){
			return;
		}
			/*
			 * above code is when there is error in operands, calculations will not be
			 * performed
			 */

		String y= text_Operand2.getText();
		String v= text_Operand1.getText();
		String x= error_text_Operand2.getText();
		String z= error_text_Operand1.getText();
		if(v.trim().isEmpty()){                      // this type of code is when we will enter nothing
			                                         // operand will automatically gain a value acc to req.

			text_Operand1.setText("0.0");

		}
        if(y.trim().isEmpty()){

			text_Operand2.setText("0.0");

		}
        if(x.trim().isEmpty()){

        	error_text_Operand2.setText("5");

        }
        if(z.trim().isEmpty()){

        	error_text_Operand1.setText("5");

}
       
        
		Double a= Double.parseDouble(text_Operand1.getText());
		Double c= Double.parseDouble(text_Operand2.getText());
		Double b= Double.parseDouble(error_text_Operand1.getText());
		Double d= Double.parseDouble(error_text_Operand2.getText());
		
		/* Below is the UNumberWithGetters object to perform Calculations based on 
		 * UNumbers.
		 * 
		 */
		
		 UNumberWithGetters aa = new UNumberWithGetters(a);
		 UNumberWithGetters aaa = new UNumberWithGetters(a);
		 UNumberWithGetters aaaa = new UNumberWithGetters(a);
		 UNumberWithGetters bb = new UNumberWithGetters(b);
		 UNumberWithGetters cc = new UNumberWithGetters(c);
		 UNumberWithGetters ccc = new UNumberWithGetters(c);
		 UNumberWithGetters dd = new UNumberWithGetters(d);
		 UNumberWithGetters two2 = new UNumberWithGetters(2.0);	
		
		 // this is to store data upto 25 significant digits
		 
		 int numSigDigits= 25;	
		 aa = new UNumberWithGetters(aa, numSigDigits);    
		 bb = new UNumberWithGetters(bb, numSigDigits);  
		 cc = new UNumberWithGetters(cc, numSigDigits);  
		 dd = new UNumberWithGetters(dd, numSigDigits);  
		 
		 // This is the constant 2.0
		 two2 = new UNumberWithGetters(two2, numSigDigits);
		 
/*
 * Below is the Calculation algorithm for addition step by step because 
 * UNumber methods are stored in voids and not return anything that can be stored into Variables. 
 *
 * Basically the below code is working on finding of upper bound and lower bound method
 * 
 * The code is aligned in steps and should be followed as it is.
 * 
 * variable names are big problem here, i have made them like this for my own good.
 * surely you will get  some difficulty seeing them, but you can see above to see what is each variable is.
 * 
 *
 */
		
		aa.add(bb);
		aaaa.add(bb);
		aaa.sub(bb);
		cc.add(dd);
		
		ccc.sub(dd);
		aa.add(cc);
		aaaa.add(cc);
		aaa.add(ccc);
		aa.add(aaa);
		
		aa.div(two2);
		
		aaaa.sub(aaa);
		aaaa.div(two2);
		
		
		
		text_Result.setText(aa.toDecimalString());
		error_text_Result.setText(aaaa.toDecimalString());
		label_Result.setText("Sum");
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned

								// Call the business logic add method
		label_errResult.setText("");
		label_errOperand1.setText("");
		label_errOperand2.setText("");
		label_Error1.setText("");
		label_Error2.setText("");// Reset any result error messages from before

	}

	/**********
	 * This is the subtract routine
	 * same logics will be applied as addOperands()
	 */

	private void subOperands(){
		if (binaryOperandIssues()) 									    // If there are issues with the operands, return
			return;
		String y= text_Operand2.getText();
		String v= text_Operand1.getText();
		String x= error_text_Operand2.getText();
		String z= error_text_Operand1.getText();

		if(v.trim().isEmpty()){

			text_Operand1.setText("0.0");

		}
        if(y.trim().isEmpty()){

			text_Operand2.setText("0.0");

		}
        if(x.trim().isEmpty()){

        	error_text_Operand2.setText("5");

        }
        if(z.trim().isEmpty()){

        	error_text_Operand1.setText("5");

}

		if(label_errOperand1.getText().isEmpty()==false || label_errOperand2.getText().isEmpty()==false ||
				label_Error1.getText().isEmpty()==false ||label_Error2.getText().isEmpty()==false){
			return;
		}// without doing the computation
		Double a= Double.parseDouble(text_Operand1.getText());
		Double c= Double.parseDouble(text_Operand2.getText());
		Double b= Double.parseDouble(error_text_Operand1.getText());
		Double d= Double.parseDouble(error_text_Operand2.getText());
	
		 UNumberWithGetters aa = new UNumberWithGetters(a);
		 UNumberWithGetters bb = new UNumberWithGetters(b);
		 UNumberWithGetters cc = new UNumberWithGetters(c);
		 UNumberWithGetters dd = new UNumberWithGetters(d);
		 
		 int numSigDigits= 25;
		 
		 aa = new UNumberWithGetters(aa, numSigDigits);    
		 bb = new UNumberWithGetters(bb, numSigDigits);  
		 cc = new UNumberWithGetters(cc, numSigDigits);  
		 dd = new UNumberWithGetters(dd, numSigDigits);
		 
		

		aa.sub(cc);
		bb.add(dd);

      
		text_Result.setText(aa.toDecimalString());
		error_text_Result.setText(bb.toDecimalString());
		label_Result.setText("Difference");                                                      	// If the operands are defined and valid, request the business logic method to do the subtraction and return the
		label_errResult.setText("");
		label_errOperand1.setText("");
		label_errOperand2.setText("");
		label_Error1.setText("");
		label_Error2.setText("");

	}

	/**********
	 * This is the multiply routine
	 * same logics will be applied as addOperands()
	 */
	private void mpyOperands(){
		if(label_errOperand1.getText().isEmpty()==false || label_errOperand2.getText().isEmpty()==false ||
				label_Error1.getText().isEmpty()==false ||label_Error2.getText().isEmpty()==false){
			return;
		}
		label_errResult.setText("");
		if (binaryOperandIssues()) 									    // If there are issues with the operands, return
			return;
		String y= text_Operand2.getText();
		String v= text_Operand1.getText();
		String x= error_text_Operand2.getText();
		String z= error_text_Operand1.getText();
		if(v.trim().isEmpty()){

			text_Operand1.setText("0.0");

		}
        if(y.trim().isEmpty()){

			text_Operand2.setText("0.0");

		}
        if(x.trim().isEmpty()){

        	error_text_Operand2.setText("5");

        }
        if(z.trim().isEmpty()){

        	error_text_Operand1.setText("5");

}
		Double a= Double.parseDouble(text_Operand1.getText());
		Double c= Double.parseDouble(text_Operand2.getText());
		Double b= Double.parseDouble(error_text_Operand1.getText());
		Double d= Double.parseDouble(error_text_Operand2.getText());
		
		
		if(a==0 || c==0){
			text_Result.setText("0.0");
			error_text_Result.setText("NaN");// If okay, display it in the result field and
			label_Result.setText("Product");
			label_errOperand2.setText("");
			return;
		}


		 UNumberWithGetters aa = new UNumberWithGetters(a);
		 UNumberWithGetters aaa = new UNumberWithGetters(a);
		 UNumberWithGetters bb = new UNumberWithGetters(b);
		 UNumberWithGetters cc = new UNumberWithGetters(c);
		 UNumberWithGetters dd = new UNumberWithGetters(d);
		 
		 int numSigDigits= 25;
		 
		 aa = new UNumberWithGetters(aa, numSigDigits);  
		 aaa = new UNumberWithGetters(aaa, numSigDigits);  
		 bb = new UNumberWithGetters(bb, numSigDigits);  
		 cc = new UNumberWithGetters(cc, numSigDigits);  
		 dd = new UNumberWithGetters(dd, numSigDigits);

		 /*
		  * Below is the Calculation algorithm for multiplication step by step because 
		  * UNumber methods are stored in voids and not return anything that can be stored into Variables. 
		  *
		  * Basically the below code is working on finding of upper bound and lower bound method
		  * 
		  * The code is aligned in steps and should be followed as it is.
		  * 
		  * variable names are big problem here, i have made them like this for my own good.
		  * surely you will get  some difficulty seeing them, but you can see above to see what is each variable is.
		  * 
		  *
		  */
		 
		aa.abs();
		aaa.abs();
		
		cc.abs();
		
		aa.mpy(cc);
		aa.abs();
		
		aaa.add(bb);
		cc.add(dd);
		aaa.mpy(cc);

		aaa.sub(aa);


		text_Result.setText(aa.toDecimalString());
		error_text_Result.setText(aaa.toDecimalString());
														                

		                                                                
		String theAnswer1 = aa.toDecimalString();                                                               
		label_errResult.setText("");
		label_errOperand1.setText("");
		label_errOperand2.setText("");
		label_Error1.setText("");
		label_Error2.setText("");									// Reset any result error messages from before
		if (theAnswer1.length() > 0) {							     	// Check the returned String to see if it is okay
			text_Result.setText(theAnswer1);							// If okay, display it in the result field and
			label_Result.setText("Product");						// change the title of the field to "Multiplication"
		}
		else {														    // Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.
			label_Result.setText("Result");								// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}


	}
	/**********
	 * This is the division routine
	 * same logics will be applied as addOperands()
	 */
	private void divOperands(){
		if (binaryOperandIssues()) 									    // If there are issues with the operands, return
			return;
		String y= text_Operand2.getText();
		String v= text_Operand1.getText();
		String x= error_text_Operand2.getText();
		String z= error_text_Operand1.getText();

		if(v.trim().isEmpty()){

			text_Operand1.setText("0.0");

		}
        if(y.trim().isEmpty()){

			text_Operand2.setText("0.0");

		}
        if(x.trim().isEmpty()){

        	error_text_Operand2.setText("5");

        }
        if(z.trim().isEmpty()){

        	error_text_Operand1.setText("5");

}

		if(label_errOperand1.getText().isEmpty()==false || label_errOperand2.getText().isEmpty()==false ||
				label_Error1.getText().isEmpty()==false ||label_Error2.getText().isEmpty()==false){
			return;
		}
		Double a= Double.parseDouble(text_Operand1.getText());
		Double c= Double.parseDouble(text_Operand2.getText());
		Double b= Double.parseDouble(error_text_Operand1.getText());
		Double d= Double.parseDouble(error_text_Operand2.getText());
		if(a==0){
			text_Result.setText("0.0");
			error_text_Result.setText("NaN");	// If okay, display it in the result field and
			label_Result.setText("Quotient");
			return;
		}
		if(c==0){
			label_errResult.setText("Divisor is invalid");							// If okay, display it in the result field and
			label_Result.setText("Quotient");
			text_Result.setText("");							// If okay, display it in the result field and
			error_text_Result.setText("");
			return;
		}
		

		 UNumberWithGetters aa = new UNumberWithGetters(a);
		 UNumberWithGetters aaa = new UNumberWithGetters(a);
		 UNumberWithGetters bb = new UNumberWithGetters(b);
		 UNumberWithGetters cc = new UNumberWithGetters(c);
		 UNumberWithGetters dd = new UNumberWithGetters(d);
		 
		 int numSigDigits= 25;
		 
		 aa = new UNumberWithGetters(aa, numSigDigits);  
		 aaa = new UNumberWithGetters(aaa, numSigDigits);  
		 bb = new UNumberWithGetters(bb, numSigDigits);  
		 cc = new UNumberWithGetters(cc, numSigDigits);  
		 dd = new UNumberWithGetters(dd, numSigDigits);
		
		 /*
		  * Below is the Calculation algorithm for addition step by step because 
		  * UNumber methods are stored in voids and not return anything that can be stored into Variables. 
		  *
		  * Basically the below code is working on finding of upper bound and lower bound method
		  * 
		  * The code is aligned in steps and should be followed as it is.
		  * 
		  * variable names are big problem here, i have made them like this for my own good.
		  * surely you will get  some difficulty seeing them, but you can see above to see what is each variable is.
		  * 
		  *
		  */
		 
		    aa.abs();
			aaa.abs();
			cc.abs();
			bb.div(aa);
			dd.div(cc);
			aa.div(cc);
			aa.abs();
			
			bb.add(dd);
			bb.mpy(aa);
	
	/*  Previous method of version 9.00
	 
		positivevalue1= Math.abs(a);
		positivevalue2= Math.abs(c);
		value1error= b/positivevalue1;
		value2error= d/positivevalue2;
		quotient= a/c;
		positivequotient= Math.abs(quotient);
		quetienterror=(value1error+value2error)*positivequotient;
		
		*/
		
		
		text_Result.setText(aa.toDecimalString());
		error_text_Result.setText(bb.toDecimalString());
		
		label_Result.setText("Quotient");
		label_errResult.setText("");
		label_errOperand1.setText("");
		label_errOperand2.setText("");
		label_Error1.setText("");
		label_Error2.setText("");// without doing the computation



	}

	/** you can see here, i have removed the binary issues condition and instead of
	 * taking value from binary issues method, i am defining the statements individually to work
	 * as unary issues. i can define that method too, but chose to ignore it as below code
	 * is easily understandable without any complexity.
	 */
	private void sqrtOperands(){

		if(label_errOperand1.getText().isEmpty()==false || label_errOperand2.getText().isEmpty()==false ||
				label_Error1.getText().isEmpty()==false ||label_Error2.getText().isEmpty()==false){
			return;
		}
		String y= text_Operand2.getText(); // get string value from Operand 2 field
		String v= text_Operand1.getText();
		String x= error_text_Operand2.getText();
		if(v.isEmpty()){
			label_errOperand1.setText("No value found");
			label_errOperand2.setText("");
			return;
		}
		/** above return statement is the fixer for the bug that result is displaying
		 * on entering and deleting the value in operand 1, because value last character deleted was being
		 * stored in the memory and result is producing, but a return statement will fix this bug.
		 */


		if(v.charAt(0)=='-'){
			text_Result.setText("NaN");
			error_text_Result.setText("NaN");
			if(y.isEmpty() || x.isEmpty()){

			}

		}
		String z= error_text_Operand1.getText();

        if(z.trim().isEmpty()){

        	error_text_Operand1.setText("5");

}
        if(v.trim().isEmpty()){

        	text_Operand1.setText("0.0");

}
		
					Double a= Double.parseDouble(text_Operand1.getText());
			if(a<0) {
				label_errOperand1.setText("Square Root of Negetive Number is not possible");
				return;
			}
					Double b= Double.parseDouble(error_text_Operand1.getText());
			if (a==0){
				error_text_Result.setText("0.0");
				text_Result.setText("0.0");
				return;
			} 
		 UNumber aa = new UNumber(a);
		 
		 UNumberWithGetters bb = new UNumberWithGetters(b);
		 UNumberWithGetters sq = new UNumberWithGetters(0.5);
		 
		 int numSigDigits= 25;
		 bb = new UNumberWithGetters(bb, numSigDigits);  
		 aa = new UNumberWithGetters(aa, numSigDigits); 
		 
		 /*
		  * Below is the Calculation algorithm for square root step by step because 
		  * UNumber methods are stored in voids and not return anything that can be stored into Variables. 
		  *
		  * Basically the below code is working on finding of upper bound and lower bound method
		  * 
		  * The code is aligned in steps and should be followed as it is.
		  * 
		  * variable names are big problem here, i have made them like this for my own good.
		  * surely you will get  some difficulty seeing them, but you can see above to see what is each variable is.
		  * 
		  *
		  */
		 
		 aa.abs();
		 bb.div(aa);
		 
		UNumber sd=  aa.sqrt(a);
		text_Result.setText(sd.toDecimalString());
		
		sd.mpy(sq);
		sd.mpy(bb);
		
		error_text_Result.setText(sd.toDecimalString());
		String f = aa.toDecimalString();
		

		if (f.length() > 0) {							     	// Check the returned String to see if it is okay
										// If okay, display it in the result field and
			label_Result.setText("Square Root");
		}
		else {														    // Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.
			label_Result.setText("Result");								// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());
			error_text_Result.setText("");// Display the error message.
			
		}
		label_errResult.setText("");
		label_errResult.setText("");
		label_errOperand1.setText("");
		label_errOperand2.setText("");
		label_Error1.setText("");
		label_Error2.setText("");
		

	}
		
		


	}



