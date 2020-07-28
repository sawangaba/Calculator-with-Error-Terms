package calculator;

import java.util.Scanner;


/**
 * <p> Title: CalculatorValue Class. </p>
 *
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 *
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 *
 * @BaselineAuthor Lynn Robert Carter
 * @AdditionAuthor Sawan
 *
 * @version 7.00	2018-02-25 Errors are enhanced and more user friendly.
 *
 **/
public class CalculatorValue {

	/**********************************************************************************************

	Attributes

	**********************************************************************************************/

	double measuredValue ;
	static String errorMessage = "";

	/**********************************************************************************************

	Constructors

	**********************************************************************************************/

	/*****
	 * This is the default constructor
	 * @param value
	 */
	public CalculatorValue(String value) {
	}

	/*****
	 * This constructor creates a calculator value based on a double data type. For future calculators, it
	 * is best to avoid using this constructor.
	 */
	public CalculatorValue(double v) {
		measuredValue = v;
	}

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
	}

	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the
	 * routine returns the value with the error message value set to empty or the string
	 * of an error message.
	 */
		// The alternate error message text
	public static String measuredValueInput = null;		// The input being processed
	public static int measuredValueIndexofError = -1;		// The index where the error was located
	private static int state = 0;						// The current state value
	private static int nextState = 0;					// The next state value
	private static boolean finalState = false;			// Is this state a final state
	private static String inputLine = "";				// The input line
	private static char currentChar;						// The current character in the line
	private static int currentCharNdx;					// The index of the current character
	private static boolean running;						// The flag that specifies if it is running

	/**********
	 * This private method display the input line and then on a line under it displays an up arrow
	 * at the point where an error was detected.  This method is designed to be used to display the
	 * error message on the console terminal.
	 *
	 * @param input				The input string
	 * @param currentCharNdx		The location where an error was found
	 * @return					Two lines, the entire input line followed by a line with an up arrow
	 */
	private static String displayInput(String input, int currentCharNdx) {
		// Display the entire input line
		String result = input + "\n";

		// Display a line with enough spaces so the up arrow point to the point of an error
		for (int ndx=0; ndx < currentCharNdx; ndx++) result += " ";

		// Add the up arrow to the end of the second line
		return result + "\u21EB";				// A Unicode up arrow with a base
	}




	private static void moveToNextCharacter() {
		currentCharNdx++;
		if (currentCharNdx < inputLine.length())
			currentChar = inputLine.charAt(currentCharNdx);
		else {
			currentChar = ' ';
			running = false;
		}
	}

	/**********
	 * This method is a mechanical transformation of a Finite State Machine diagram into a Java
	 * method.
	 *
	 * @param input		The input string for the Finite State Machine
	 * @return			An output string that is empty if every things is okay or it will be
	 * 						a string with a help description of the error follow by two lines
	 * 						that shows the input line follow by a line with an up arrow at the
	 *						point where the error was found.
	 */
	public static String checkMeasureValue(String input) {        // FSM recognizer checker method
		if(input.length() <= 0) return "";
		// The following are the local variable used to perform the Finite State Machine simulation
		state = 0;							// This is the FSM state number
		inputLine = input;					// Save the reference to the input line as a global
		currentCharNdx = 0;					// The index of the current character
		currentChar = input.charAt(0);		// The current character from the above indexed position

		// The Finite State Machines continues until the end of the input is reached or at some
		// state the current character does not match any valid transition to a next state

		measuredValueInput = input;			// Set up the alternate result copy of the input
		running = true;						// Start the loop


		// The Finite State Machines continues until the end of the input is reached or at some
		// state the current character does not match any valid transition to a next state
		while (running) {
			// The switch statement takes the execution to the code for the current state, where
			// that code sees whether or not the current character is valid to transition to a
			// next state
			switch (state) {

			case 0:
				// State 0 has three valid transitions.  Each is addressed by an if statement.

				// This is not a final state
				finalState = false;

				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (currentChar >= '0' && currentChar <= '9' ) {
					nextState = 1;
					break;
				}
				// If the current character is a decimal point, it transitions to state 3
				else if (currentChar == '.') {
					nextState = 3;
					break;
				}
				else if (currentChar=='+' || currentChar=='-'){
					nextState = 8;
					break;
				}

				// If it is none of those characters, the FSM halts
				else
					running = false;

				// The execution of this state is finished
				break;

			case 1:
				// State 1 has three valid transitions.  Each is addressed by an if statement.

				// This is a final state
				finalState = true;

				// In state 1, if the character is 0 through 9, it is accepted and we stay in this
				// state
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 1;
					break;
				}

				// If the current character is a decimal point, it transitions to state 2
				else if (currentChar == '.') {
					nextState = 2;
					break;
				}
				// If the current character is an E or an e, it transitions to state 5
				else if (currentChar == 'E' || currentChar == 'e') {
					nextState = 5;
					break;
				}
				// If it is none of those characters, the FSM halts
				else
					running = false;

				// The execution of this state is finished
				break;

			case 2:
				// State 2 has two valid transitions.  Each is addressed by an if statement.

				// This is a final state
				finalState = true;

				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 2;
					break;
				}
				// If the current character is an 'E' or 'e", it transitions to state 5
				else if (currentChar == 'E' || currentChar == 'e') {
					nextState = 5;
					break;
				}

				// If it is none of those characters, the FSM halts
				else
					running = false;

				// The execution of this state is finished
				break;

			case 3:
				// State 3 has only one valid transition.  It is addressed by an if statement.

				// This is not a final state
				finalState = false;

				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 4;
					break;
				}

				// If it is none of those characters, the FSM halts
				else
					running = false;

				// The execution of this state is finished
				break;

			case 4:
				// State 4 has two valid transitions.  Each is addressed by an if statement.

				// This is a final state
				finalState = true;

				// If the current character is in the range from 1 to 9, it transitions to state 4
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 4;
					break;
				}
				// If the current character is an 'E' or 'e", it transitions to state 5
				else if (currentChar == 'E' || currentChar == 'e') {
					nextState = 5;
					break;
				}

				// If it is none of those characters, the FSM halts
				else
					running = false;

				// The execution of this state is finished
				break;

			case 5:
				finalState= false;
				if(currentChar=='+' || currentChar=='-'){
					nextState=6;
					break;// This state has not yet been implemented.  This is a stub!
				}
				else if(currentChar >= '0' && currentChar <= '9'){
					nextState = 7;
				break;
				}
				else
					running = false;

				// The execution of this state is finished
				// The execution of this state is finished
				break;

			case 6:
				finalState=false;
				if(currentChar >= '0' && currentChar <= '9'){
					nextState = 7;
				break;
				}
				else
					running = false;// This state has not yet been implemented.  This is a stub!

				// The execution of this state is finished
				break;

			case 7:
finalState = true;

				// In state 1, if the character is 0 through 9, it is accepted and we stay in this
				// state
				if (currentChar >= '0' && currentChar <= '9') {
					nextState = 7;
					break;
				}// This state has not yet been implemented.  This is a stub!
				else
					running = false;

					// The execution of this state is finished
					break;
				// In state 1, if the character is 0 through 9, it is accepted and we stay in this
				// state


			case 8:
				finalState = false;
				if (currentChar >= '0' && currentChar <= '9'){
					nextState = 1;
					break;
				}
				else if (currentChar == '.'){
					nextState = 2;
					break;
				}
				else
					running = false;

					// The execution of this state is finished
					break;}
			if (running) {

				// When the processing of a state has finished, the FSM proceeds to the next character
				// in the input and if there is one, it fetches that character and updates the
				// currentChar.  If there is no next character the currentChar is set to a blank.
				moveToNextCharacter();

				// Move to the next state
				state = nextState;

			}
			// Should the FSM get here, the loop starts again

		}



		measuredValueIndexofError = currentCharNdx;		// Copy the index of the current character;

		// When the FSM halts, we must determine if the situation is an error or not.  That depends
		// of the current state of the FSM and whether or not the whole string has been consumed.
		// This switch directs the execution to separate code for each of the FSM states.
		switch (state) {
		case 0:
			// State 0 is not a final state, so we can return a very specific error message
			measuredValueIndexofError = currentCharNdx;		// Copy the index of the current character;
			errorMessage = "The first character must be a digit or a decimal point.";
			return "The first character must be a digit or a decimal point.";

		case 1:
			// State 1 is a final state, so we must see if the whole string has been consumed.
			if (currentCharNdx<input.length()) {
				// If not all of the string has been consumed, we point to the current character
				// in the input line and specify what that character must be in order to move
				// forward.
				errorMessage = "This character may only be an \"E\", an \"e\", a digit, "
						+ "a \".\", or it must be the end of the input.\n";
				return errorMessage + displayInput(input, currentCharNdx);
			}
			else {
				measuredValueIndexofError = -1;
				errorMessage = "";
				return errorMessage;

			}

		case 2:
		case 4:
			// States 2 and 4 are the same.  They are both final states with only one possible
			// transition forward, if the next character is an E or an e.
			if (currentCharNdx<input.length()) {
				errorMessage = "This character may only be an \"E\", an \"e\", or it must"
						+ " be the end of the input.\n";
				return errorMessage + displayInput(input, currentCharNdx);
			}
			// If there is no more input, the input was recognized.
			else {
				measuredValueIndexofError = -1;
				errorMessage = "";
				return errorMessage;
			}
		case 3:
		case 6:
			// States 3, and 6 are the same. None of them are final states and in order to
			// move forward, the next character must be a digit.
			errorMessage = "This character may only be a digit.\n";
			return errorMessage + displayInput(input, currentCharNdx);
		case 8:
			// States 3, and 6 are the same. None of them are final states and in order to
			// move forward, the next character must be a digit.
			errorMessage = "This character may only be a digit or a decimal point.\n";
			return errorMessage + displayInput(input, currentCharNdx);
		case 7:
			// States 7 is similar to states 3 and 6, but it is a final state, so it must be
			// processed differently. If the next character is not a digit, the FSM stops with an
			// error.  We must see here if there are no more characters. If there are no more
			// characters, we accept the input, otherwise we return an error
			if (currentCharNdx<input.length()) {
				errorMessage = "This character may only be a digit.\n";
				return errorMessage + displayInput(input, currentCharNdx);
			}
			else {
				measuredValueIndexofError = -1;
				errorMessage = "";
				return errorMessage;
			}

		case 5:
			// State 5 is not a final state.  In order to move forward, the next character must be
			// a digit or a plus or a minus character.
			errorMessage = "This character may only be a digit, a plus, or minus "
					+ "character.\n";
			return errorMessage + displayInput(input, currentCharNdx);
		default:
			return "";
		}
	}
	/**********************************************************************************************

	Getters and Setters

	**********************************************************************************************/

	/*****
	 * This is the start of the getters and setters
	 *
	 * Get the error message
	 */
	public String getErrorMessage(){
		return errorMessage;
	}

	/*****
	 * Set the current value of a calculator value to a specific long integer
	 */
	public void setValue(double v){
		measuredValue = v;
	}

	/*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}

	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
	}

	/**********************************************************************************************

	The toString() Method

	**********************************************************************************************/

	/*****
	 * This is the default toString method
	 *
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String toString() {
		return measuredValue + "";
	}

	/*****
	 * This is the debug toString method
	 *
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String debugToString() {
		return "measuredValue = " + measuredValue + "\nerrorMessage = " + errorMessage + "\n";
	}


	/**********************************************************************************************

	The computation methods

	**********************************************************************************************/


	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business
	 * logic and user interface modules.
	 *
	 * Since this is addition and we do not yet support units, we don't recognize any errors.
	 */

	public void add(CalculatorValue v) {
		measuredValue += v.measuredValue;
		errorMessage = "";
	}

	/** Same as above method add(CalculatorValue v), change the + sign to - to do subtraction **/
	public void sub(CalculatorValue v) {
		measuredValue -= v.measuredValue;
		errorMessage = "";
	}
	/** Same as method add(CalculatorValue v), change the + sign to * to do multiplication **/
	public void mpy(CalculatorValue v) {
		measuredValue *= v.measuredValue;
		errorMessage = "";
	}
	/** Same as method add(CalculatorValue v), change the + sign to / to do division **/
	public void div(CalculatorValue v) {
		measuredValue /= v.measuredValue;
		errorMessage = "";
	}
	/** define a method of square root that will be fetched by business logic **/
	public void sqrt(CalculatorValue v) {
		measuredValue = Math.pow(v.measuredValue, 0.5);
		errorMessage = "";
	}
	public static String errorTermErrorMessage = "Error Term recognition has not been implements";
	public static String errorTermInput = "";			// The input being processed
	public static int errorTermIndexofError = -1;		// The index where the error was located
		// The index where the error was located
	private static int state1 = 0;						// The current state value
	private static int nextState1 = 0;					// The next state value
	private static boolean finalState1 = false;			// Is this state a final state
	private static String inputLine1 = "";				// The input line
	private static char currentChar1;						// The current character in the line
	private static int currentCharNdx1;					// The index of the current character
	private static boolean running1;
	/**********
	 * This method is a mechanical transformation of a Finite State Machine diagram into a Java
	 * method.
	 *
	 * @param input		The input string for the Finite State Machine
	 * @return			An output string that is empty if every things is okay or it will be
	 * 						a string with a help description of the error follow by two lines
	 * 						that shows the input line follow by a line with an up arrow at the
	 *						point where the error was found.
	 */
	private static String displayInput1(String input, int currentCharNdx) {
		// Display the entire input line
		String result = input + "\n";

		// Display a line with enough spaces so the up arrow point to the point of an error
		for (int ndx=0; ndx < currentCharNdx1; ndx++) result += " ";

		// Add the up arrow to the end of the second line
		return result + "\u21EB";				// A Unicode up arrow with a base
	}




	private static void moveToNextCharacter1() {
		currentCharNdx1++;
		if (currentCharNdx1 < inputLine1.length())
			currentChar1 = inputLine1.charAt(currentCharNdx1);
		else {
			currentChar1 = ' ';
			running1 = false;
		}
	}
	public static String checkErrorTerm(String input) {
		if(input.length() <= 0) return "";
		// The following are the local variable used to perform the Finite State Machine simulation
		state1 = 0;							// This is the FSM state number
		inputLine1 = input;					// Save the reference to the input line as a global
		currentCharNdx1 = 0;					// The index of the current character
		currentChar1 = input.charAt(0);		// The current character from the above indexed position

		// The Finite State Machines continues until the end of the input is reached or at some
		// state the current character does not match any valid transition to a next state

		errorTermInput = input;			// Set up the alternate result copy of the input
		running1= true;						// Start the loop


		// The Finite State Machines continues until the end of the input is reached or at some
		// state the current character does not match any valid transition to a next state
		while (running1) {
			// The switch statement takes the execution to the code for the current state, where
			// that code sees whether or not the current character is valid to transition to a
			// next state
			switch (state1) {
			case 0:
				// State 0 has three valid transitions.  Each is addressed by an if statement.

				// This is not a final state
				finalState1 = false;

				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (currentChar1 >= '1' && currentChar1 <= '9') {
					nextState1 = 1;
					break;
				}
				// If the current character is a decimal point, it transitions to state 3
				else if (currentChar1 == '.') {
					nextState1 = 3;
					break;
				}
				else if (currentChar1=='0'){
					nextState1 = 8;
					break;
				}
				// If it is none of those characters, the FSM halts
				else
					running1 = false;

				// The execution of this state is finished
				break;

			case 1:
				// State 1 has three valid transitions.  Each is addressed by an if statement.

				// This is a final state
				finalState1 = true;

				// In state 1, if the character is 0 through 9, it is accepted and we stay in this
				// state
				if (currentChar1 =='0') {
					nextState1 = 1;
					break;
				}

				// If the current character is a decimal point, it transitions to state 2
				else if (currentChar1 == '.') {
					nextState1 = 2;
					break;
				}
				// If the current character is an E or an e, it transitions to state 5
				else if (currentChar1 == 'E' || currentChar1 == 'e') {
					nextState1 = 5;
					break;
				}
				// If it is none of those characters, the FSM halts
				else
					running1 = false;

				// The execution of this state is finished
				break;

			case 2:
				// State 2 has two valid transitions.  Each is addressed by an if statement.

				// This is a final state
				finalState1 = true;

				// If the current character is in the range from 1 to 9, it transitions to state 1

				// If the current character is an 'E' or 'e", it transitions to state 5
				if (currentChar1 == 'E' || currentChar1 == 'e') {
					nextState1 = 5;
					break;
				}

				// If it is none of those characters, the FSM halts
				else
					running1 = false;

				// The execution of this state is finished
				break;

			case 3:
				// State 3 has only one valid transition.  It is addressed by an if statement.

				// This is not a final state
				finalState1 = false;

				// If the current character is in the range from 1 to 9, it transitions to state 1
				if (currentChar1 >= '1' && currentChar1 <= '9') {
					nextState1 = 4;
					break;
				}
				else if(currentChar1=='0'){
					nextState1 = 3;
					break;
				}
				// If it is none of those characters, the FSM halts
				else
					running1 = false;

				// The execution of this state is finished
				break;

			case 4:
				// State 4 has two valid transitions.  Each is addressed by an if statement.

				// This is a final state
				finalState1 = true;

				// If the current character is in the range from 1 to 9, it transitions to state 4

				// If the current character is an 'E' or 'e", it transitions to state 5
				if (currentChar1 == 'E' || currentChar1 == 'e') {
					nextState1 = 5;
					break;
				}

				// If it is none of those characters, the FSM halts
				else
					running1 = false;

				// The execution of this state is finished
				break;

			case 5:
				finalState1= false;
				if(currentChar1=='+' || currentChar1=='-'){
					nextState1=6;
					break;// This state has not yet been implemented.  This is a stub!
				}
				else if(currentChar1 >= '0' && currentChar1 <= '9'){
					nextState1 = 7;
				break;
				}
				else
					running1 = false;

				// The execution of this state is finished
				// The execution of this state is finished
				break;

			case 6:
				finalState1=false;
				if(currentChar1 >= '0' && currentChar1 <= '9'){
					nextState1 = 7;
				break;
				}
				else
					running1 = false;// This state has not yet been implemented.  This is a stub!

				// The execution of this state is finished
				break;

			case 7:
finalState1 = true;
			case 8:
				finalState1= false;
				if (currentChar1=='.') {
					nextState1 = 9;
					break;
				}// This state has not yet been implemented.  This is a stub!
				else
					running1 = false;

					// The execution of this state is finished
					break;
			case 9:
				finalState1= false;
				if (currentChar1 >= '1' && currentChar1 <= '9') {
					nextState1 = 4;
					break;
				}
				else if(currentChar1=='0'){
					nextState1= 9;// This state has not yet been implemented.  This is a stub!
				}
				else
					running1 = false;

					// The execution of this state is finished
					break;
			}

			if (running1) {

				// When the processing of a state has finished, the FSM proceeds to the next character
				// in the input and if there is one, it fetches that character and updates the
				// currentChar.  If there is no next character the currentChar is set to a blank.
				moveToNextCharacter1();

				// Move to the next state
				state1 = nextState1;

			}
			// Should the FSM get here, the loop starts again

		}



		errorTermIndexofError = currentCharNdx1;		// Copy the index of the current character;

		// When the FSM halts, we must determine if the situation is an error or not.  That depends
		// of the current state of the FSM and whether or not the whole string has been consumed.
		// This switch directs the execution to separate code for each of the FSM states.
		switch (state1) {
		case 0:
			// State 0 is not a final state, so we can return a very specific error message
			errorTermIndexofError = currentCharNdx1;		// Copy the index of the current character;
			errorTermErrorMessage = "The first character must be a digit or a decimal point.";
			return "The first character must be a digit or a decimal point.";

		case 1:
			// State 1 is a final state, so we must see if the whole string has been consumed.
			if (currentCharNdx1<input.length()) {
				// If not all of the string has been consumed, we point to the current character
				// in the input line and specify what that character must be in order to move
				// forward.
				errorTermErrorMessage = "This character may only be an \"E\", an \"e\", a digit, "
						+ "a \".\", or it must be the end of the input.\n";
				return errorTermErrorMessage + displayInput1(input, currentCharNdx1);
			}
			else {
				errorTermIndexofError = -1;
				errorTermErrorMessage = "";
				return errorTermErrorMessage+ displayInput1(input, currentCharNdx1);
			}

		case 2:
		case 4:
			// States 2 and 4 are the same.  They are both final states with only one possible
			// transition forward, if the next character is an E or an e.
			if (currentCharNdx1<input.length()) {
				errorTermErrorMessage = "This character may only be an \"E\", an \"e\", a digit, "
						+ "a \".\", or it must be the end of the input.\n";
				return errorTermErrorMessage + displayInput1(input, currentCharNdx1);
			}
			// If there is no more input, the input was recognized.
			else {
				errorTermIndexofError = -1;
				errorTermErrorMessage = "";
				return errorTermErrorMessage;
			}
		case 3:
		case 6:
			// States 3, and 6 are the same. None of them are final states and in order to
			// move forward, the next character must be a digit.
			errorTermErrorMessage = "This character may only be a digit.\n";
			return errorTermErrorMessage + displayInput1(input, currentCharNdx1);

		case 7:
			// States 7 is similar to states 3 and 6, but it is a final state, so it must be
			// processed differently. If the next character is not a digit, the FSM stops with an
			// error.  We must see here if there are no more characters. If there are no more
			// characters, we accept the input, otherwise we return an error
			if (currentCharNdx1<input.length()) {
				errorTermErrorMessage = "Only one significant digit is allowed.\n";
				return errorTermErrorMessage + displayInput1(input, currentCharNdx1);
			}
			else {
				errorTermIndexofError = -1;
				errorTermErrorMessage = "";
				return errorTermErrorMessage;
			}

		case 5:
			// State 5 is not a final state.  In order to move forward, the next character must be
			// a digit or a plus or a minus character.
			errorTermErrorMessage = "This character may only be a digit, a plus, or minus "
					+ "character.\n";// This is a sub for an Error Term recognizer
		return errorTermErrorMessage;
		case 8:
			if (currentCharNdx1<input.length()) {
				// If not all of the string has been consumed, we point to the current character
				// in the input line and specify what that character must be in order to move
				// forward.
				errorTermErrorMessage = "This character may only be "
						+ "a \".\", or it must be the end of the input.\n";
				return errorTermErrorMessage + displayInput1(input, currentCharNdx1);
			}
			else {
				errorTermIndexofError = -1;
				errorTermErrorMessage = "";
				return errorTermErrorMessage+ displayInput1(input, currentCharNdx1);
			}

		case 9:
			// States 3, and 6 are the same. None of them are final states and in order to
			// move forward, the next character must be a digit.
			errorTermErrorMessage = "This character may only be a digit.\n";
			return errorTermErrorMessage + displayInput1(input, currentCharNdx1);
	default:
		return "";
		
		}
}
}
