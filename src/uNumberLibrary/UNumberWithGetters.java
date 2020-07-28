package uNumberLibrary;
/**
 * <p> Title: UNumberWithGetters. </p>
 * 
 * <p> Description: a constructor Class to add getters for UNumber Type</p>
 * 
 * <p> Copyright: Lyn Robert Carter © 2018-08-10 </p>
 * 
 * @author Lyn Robert Carter
 * 
 * @version 1.00	Initial baseline
 * 
 */
public class UNumberWithGetters extends UNumber {

	

	public UNumberWithGetters(int v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithGetters(long v) {
		super(v);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithGetters(String str, int dec, boolean sign) {
		super(str, dec, sign);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithGetters(String str, int dec, boolean sign, int size) {
		super(str, dec, sign, size);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithGetters(UNumber that) {
		super(that);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithGetters(UNumber that, int size) {
		super(that, size);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithGetters(UNumber that, UNumber another) {
		super(that, another);
		// TODO Auto-generated constructor stub
	}

	public UNumberWithGetters(double v) {
		super(v);
		// TODO Auto-generated constructor stub
	}
	
	
	/**********
	 * Get the characteristic
	 * 
	 * @return the characteristic
	 */
	public int getCharacteristic () {
		return dP;
	}
	
	/**********
	 * Get the mantissa
	 * 
	 * @return the mantissa
	 */
	public String getMantissa () {
		String result = "";
		for (int ndx=0; ndx < d.length; ndx++) result += d[ndx];
		return result;
	}

}
