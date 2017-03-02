/*
  Write a class called Book that contains the following information:
      1. Instance variables for the title (String), author (String) and price (float) of the book.
      2. A three-argument constructor to set each of the instance variable above.  If the title or author is null or 
      the price is less than 0, throw an IllegalArg
       3. Get and Set methods for each variable

 */
public class Book {
   private String title;
   private String author;
   private float price;
   
   public Book (String t, String a, float p) {
      if (t==null || a == null || p<0.0f)
         throw new IllegalArgumentException("Bad parameter to constructor");
      title = t;
      author = a;
      price = p;
   }
   public String getTitle() {
      return title;
   }
   public String getAuthor() {
      return author;
   }
   public float getPrice() {
      return price;
   }
   public void setTitle(String t) {
      if (t==null)
         throw new IllegalArgumentException("Bad title");
      title = t;
   }
   public void setAuthor(String a) {
      if (a == null)
         throw new IllegalArgumentException("Bad author");
      author = a;
   }
   public void setPrice(float p) {
      if (p<0.0f)
         throw new IllegalArgumentException("Bad price");
      price = p;
   }

}
