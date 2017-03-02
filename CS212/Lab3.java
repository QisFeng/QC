
public class Lab3 {
	public static void main(String[] args){
		float fahrenheit;
		float centigrade;
		fahrenheit = 98.6f;
		centigrade = (5f/9)*(fahrenheit-32);
		System.out.print("Fahrenheit ="+fahrenheit+"\t");
		System.out.println("Centigrade ="+centigrade);
		
		System.out.println("\nFor loop Conversion");
		for(float i=0f;i<=40;i+=5){
			centigrade = (5f/9)*(i-32);
			System.out.println("Fahrenheit is: "+i+"\t"+"centigrade is: "+centigrade);
		}
		
		System.out.println("\nWhile loop Conversion");
		float i = 0.0f;
		while(i<=40){
			centigrade = (5f/9)*(i-32);
			System.out.println("Fahrenheit is: "+i+"\t"+"centigrade is: "+centigrade);
			i+=5;
		}
		
		System.out.println("\nFor loop using method convert");
		for(float j=0f;j<=40;j+=5){
			System.out.println("Fahrenheit is: "+j+"\t"+"centigrade is: "+convert(j));
		}
	}
	public static float convert(float f){
		return ((5f/9)*(f-32));
	}
}
