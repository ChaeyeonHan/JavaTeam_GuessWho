package Final;

public class Person 
{
   private String name; 
   private String glasses; 
   private String dye; 
   private String blush; 
   private String ribbon; 
   private String shoes; 
   private String bag; 
   private String picURL; 
   
   /**
    * Constructor
    * @param n person's name
    * @param g whether person has glasses or not
    * @param d whether person has dye or not
    * @param b whether person has blush or not
    * @param r whether person has ribbon or not
    * @param s whether person has shoes or not
    * @param bb whether person has bag or not
    * @param p name/location of picture file associated with the person
    */
   public Person(String n, String p, String g, String d, String b, String r, 
               String s, String bb)
   {
      name = n;
      picURL = p;
      glasses = g;
      dye = d;
      blush = b;
      ribbon = r;
      shoes = s;
      bag = bb;
   }
   
   
   /**
    * Accessor
    * @return the person's name
    */
   public String getName()
   {
      return name;
   }
   
   /**
    * Accessor
    * @return the person's glasses
    */
   public String getGlasses()
   {
      return glasses;
   }
   
   /**
    * Accessor
    * @return the person's dye
    */
   public String getDye()
   {
      return dye;
   }
   
   /**
    * Accessor
    * @return the person's blush
    */
   public String getBlush()
   {
      return blush;
   }
   
   /**
    * Accessor
    * @return whether the person is wearing ribbon or not
    */
   public String getRibbon()
   {
      return ribbon;
   }
   
   /**
    * Accessor
    * @return whether the person has a shoes or not
    */
   public String getShoes()
   {
      return shoes;
   }
   
   /**
    * Accessor
    * @return whether the person has a bag or not
    */
   public String getBag()
   {
      return bag;
   }
   
   /**
    * Accessor
    * @return the name/location of the picture associated with the person
    */
   public String getPicURL()
   {
      return picURL;
   }
   
   
   //following method not used in program....was only used for testing
   /**
    * toString - override method that displays the stored values in each object's variables
    * @return str the variable values stored in a string
    */
   public String toString()
   {
      String str = "Name: " + getName() + "\n" + 
                "Glasses: " + getGlasses() + "\n" + 
                "Dye: " + getDye() + "\n" + 
                "Blush: " + getBlush() + "\n" + 
                "Ribbon: " + getRibbon() + "\n" + 
                "Shoes: " + getShoes() + "\n" + 
                "Bag: " + getBag() + "\n";
      
      return str;
   }
}