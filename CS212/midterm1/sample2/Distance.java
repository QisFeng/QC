public class Distance{
	private static int INCHES_IN_A_FOOT = 12;
	private static float FEET_IN_A_METER = 0.3048f;
	private int feet;
	private int inches;
	
	public Distance(){
		feet = 0;
		inches = 0;
	} 
	
	public Distance(int f, int i){
		feet = f;
		if (i>11) throw new IllegalArgumentException("Invalid inches");
		inches = i;
	}
	public void add(Distance w){
		inches = inches+w.getInches();
		feet = feet +w.getFeet()+inches/12;
		inches = inches%12;
	}
	
	public float metricDistance(){
		return (feet/FEET_IN_A_METER+(float)inches*INCHES_IN_A_FOOT/FEET_IN_A_METER);
	}
	
	public int getFeet(){
		return feet;
	}
	public int getInches(){
		return inches;
	}
	
	public void setFeet(int f){
		feet = f;
	}
	public void setInches(int i){
		if (i>11) throw new IllegalArgumntException("Inches cannot be more than 11");
		inches = i;
	}
	
	}
}