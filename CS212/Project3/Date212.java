


public class  Date212 {
	
	private int year;
	private int month;
	private int day;
	
		
	/**
	 * Creating a Date212 constructor to initialize the 
	 * value of year/month/day
	 */
	public Date212(String d){
		 
		if (!isDigit(d))
			throw new IllegalDate212Exception(d);
		if (!isValid(d))
			throw new IllegalDate212Exception(d);
		
		year = Integer.parseInt(d.substring(0,4));
		month = Integer.parseInt(d.substring(4,6));
		day = Integer.parseInt(d.substring(6,8));
		if (month<1||month>12||day>31) 
			throw new IllegalDate212Exception(d);
 		if (month==2&&day>28) 
 			throw new IllegalDate212Exception(d);
 		if (month==1&&month==3&&month==5&&month==7&&month==8&&month==10&&month==12&&day>31) 
			throw new IllegalDate212Exception(d);
		if (month==4&&month==6&&month==9&&month==11&&day>30) 
			throw new IllegalDate212Exception(d);
		
			
	}
	
	public int getMonth(){
		return month;
	}
	public int getDay(){
		return day;
	}
	public int getYear(){
		return year;
	}
	/**
	 * check whether the data is all digits
	 *  @return result
	 */
	public static boolean isDigit(String s) {
		for(int i=0;i<s.length();i++){
			if(! Character.isDigit(s.charAt(i)))
				return false;		
		}
		return true;
	}
	
	/**
	 * check whether the data is valid
	 * @return result
	 * 
	 */
	public static boolean isValid(String s){
		if(s.length()!=8)
			return false;
		return true;
	}
	/**
	 * creating toString method for display in required format
	 */
	public String toString() {
		
		if (getMonth()<10&&getDay()>=10)
		return ("0"+getMonth()+"/"+getDay()+"/"+getYear());
		if (getMonth()>=10&&getDay()<10)
		return (getMonth()+"/"+"0"+getDay()+"/"+getYear());
		if (getMonth()<10&&getDay()<10)
		return ("0"+getMonth()+"/"+"0"+getDay()+"/"+getYear());
		
		else return (getMonth()+"/"+getDay()+"/"+getYear());
	}
	
	/**
	 * compare from year to day
	 * make sure the data will be displayed 
	 * in descending order
	 * 
	 */
	public int compareTo(Date212 d) {
		if (this.year==d.getYear()&&(this.month-d.getMonth())!=0)
			return this.month-d.getMonth();
		else if (this.month==d.getMonth())
			return this.day-d.getDay();
		return this.year-d.getYear();
	}	
	
}

