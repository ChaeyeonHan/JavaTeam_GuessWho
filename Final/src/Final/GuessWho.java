package Final;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class GuessWho extends JFrame {

   private static final long serialVersionUID = 1L;

   private Person secretPerson; 

   private final int SIZE = 20; 
   private JButton[] pictures = new JButton[SIZE];
   private JButton bt[] = new JButton[13];
   private Person[] people = new Person[SIZE];
   private ImageIcon[] images = new ImageIcon[SIZE]; 

   private JPanel mainPanel = new JPanel(); 
   private JPanel sidePanel = new JPanel(); 
   private JPanel numGuesses; 
   private JPanel attribPane; 
   private JPanel logoPane; 
   private JPanel scorePane; 

   private JTextField guessesMade;
   private int guesses; 

   private ImageIcon cardBack = new ImageIcon("src//image//cardback.jpg");
                                                        

   // attributes to choose from
   private String[] attributes = { "glasses", "no glasses", "dye", "no dye", "bag", "no bag", "blush", "no blush",
         "shoes", "no shoes", "ribbon", "no ribbon" };
   // names to choose from
   private String[] names = { "Adam", "Brenda", "Charlie", "David", "Denise", "Frank", "Giselle", "Heidi", "Kathy",
         "Kevin", "Lucas", "Matthew", "Petra", "Raymond", "Sophia", "Steven", "Tamsin", "Yvette", "Sarah", "Wendy" };

   // the constructor sets up the game
   public GuessWho() throws IOException {

      buildPersonArray(); // build person array from the information within the attribute text file
      buildMainPanel(); // build the main panel that contains the cards/images
      buildSidePanel(); // build the side panel that contains attribute buttons (if pressed it
                     // automatically functions)

      pack(); 
      setVisible(true); // set form visible
      setSize(850, 650);

      getSecretPerson(); // obtaining the secret person

   }

   /**
    * buildPersonArray reads info from a text file to build the person array
    */
   public void buildPersonArray() {
      String fileName = "src//image//characterFeatures.txt"; // path to file containing person attributes
      File file; // for file input
      Scanner inputFile = null; // for file input

      // attempt to open file
      try {
         file = new File(fileName);
         inputFile = new Scanner(file);
      } catch (IOException e) {
         JOptionPane.showMessageDialog(null, "File not found.");
      }

      // build ArrayList with people's attributes
      for (int i = 0; i < SIZE; i++) {
         people[i] = new Person(inputFile.nextLine().toString(), // storing name
               inputFile.nextLine().toString(), // storing file path
               inputFile.nextLine().toString(), // storing gender
               inputFile.nextLine().toString(), // storing glasses status
               inputFile.nextLine().toString(), // storing hair color
               inputFile.nextLine().toString(), // storing eye color
               inputFile.nextLine().toString(), // storing beard status
               inputFile.nextLine().toString()); // storing mustache status
         inputFile.nextLine(); // skip past # separation character for next person to be stored
         inputFile.nextLine(); // skip past # separation character for next person to be stored
         inputFile.nextLine(); // skip past # separation character for next person to be stored
         inputFile.nextLine(); // skip past # separation character for next person to be stored
         inputFile.nextLine(); // skip past # separation character for next person to be stored
         inputFile.nextLine(); // skip past # separation character for next person to be stored
      }

      // close file
      inputFile.close();
   }

   /**
    * buildMainPanel builds the main panel that stores the cards/pictures
    */
   public void buildMainPanel() {
      setTitle("Guess Who"); // set title
      setLayout(new BorderLayout()); // set layout

      mainPanel.setLayout(new GridLayout(5, 4, 20, 20)); // set panel layout

      // assigning buttons
      for (int i = 0; i < SIZE; i++) {
         images[i] = new ImageIcon(people[i].getPicURL());
         pictures[i] = new JButton(images[i]);
         mainPanel.add(pictures[i]);
      }

      mainPanel.setBackground(Color.WHITE);
      add(mainPanel, BorderLayout.CENTER); // add main panel to center of frame

   }

   /**
    * buildSidePanel builds the side panel containing attribute lists and guess
    * buttons
    */
   public void buildSidePanel() {

      buildImage(); 
      buildNumGuesses(); // build panel to hold num of guesses made
      buildAttribPane(); 

      
       sidePanel.setLayout(new GridLayout(2,2,20,20)); //set panel layout
       
      
       sidePanel.add(logoPane); 
       sidePanel.add(numGuesses); //add numGuesses panel to
       sidePanel.add(attribPane); 
      
       GridBagLayout gridbag = new GridBagLayout(); 
		 sidePanel.setLayout(gridbag);
		 
		 GridBagConstraints constraint = new GridBagConstraints();
		 
		 constraint.fill=GridBagConstraints.BOTH; 
		 constraint.weightx = 1.0;
		 constraint.weighty = 1.0; 
		 constraint.gridwidth = 1; 
		 constraint.gridheight = 2; 
		 gridbag.setConstraints(logoPane, constraint); 
		 sidePanel.add(logoPane);

		 constraint.gridwidth = GridBagConstraints.REMAINDER; 
		 constraint.gridheight = 1; 
		 constraint.weighty = 1; 
		 gridbag.setConstraints(scorePane, constraint);
		 sidePanel.add(scorePane);

		 gridbag.setConstraints(numGuesses, constraint); 
		 sidePanel.add(numGuesses);
		 
		 constraint.gridwidth = GridBagConstraints.REMAINDER;
		 gridbag.setConstraints(attribPane, constraint); 
		 sidePanel.add(attribPane);
		 

		sidePanel.setBackground(Color.white);
		add(sidePanel, BorderLayout.EAST);

      sidePanel.setBackground(Color.white);
      add(sidePanel, BorderLayout.EAST); // add side panel to right side of frame

   }

   /**
    * 김눈송이 누구야 
    */
   public void buildImage() {

      logoPane = new JPanel();
      ImageIcon nunlogo = new ImageIcon("src//image//nunlogo.jpg"); //김눈송이 누구야
      Image image = nunlogo.getImage();
      Image newlogo = image.getScaledInstance(120, 90, java.awt.Image.SCALE_SMOOTH);
      nunlogo = new ImageIcon(newlogo);

      JLabel nunlabel = new JLabel(nunlogo);

      logoPane.setBackground(Color.white);
      logoPane.add(nunlabel);

      sidePanel.add(logoPane);

   }

   /**
    * buildNumGuesses builds a panel holding the number of guesses made
    */
   public void buildNumGuesses() {
      numGuesses = new JPanel(); // create panel

      JLabel label = new JLabel("Score"); // create label
      
      guessesMade = new JTextField(4); // create text field
      guessesMade.setEditable(false); // set to uneditable
      
      guessesMade.setText(Integer.toString(guesses));
      JLabel label2 = new JLabel("/3");

      numGuesses.setBackground(Color.white);
      numGuesses.add(label); // add label to panel
      numGuesses.add(label2);
      numGuesses.add(guessesMade); // add text field to panel

   }

   public void buildAttribPane() {

      attribPane = new JPanel();

      for (int i = 0; i < 12; i++) {
         bt[i] = new JButton();
         attribPane.add(bt[i]);
         bt[i].addActionListener(new attribButtonListener());
      }

      attribPane.setBackground(Color.white);
      attribPane.setLayout(new GridLayout(3, 4)); // set panel layout

      sidePanel.add(attribPane);
   }

   /**
    * getSecretPerson gets a random number between 0 and 17 and assigns the person
    * to a value stored at the corresponding subscript in the people array.
    */
   public void getSecretPerson() {
      int random = (int) (Math.random() * (18)); // creating random number
      secretPerson = people[random]; // assigning secret person based on random number value
   }

   /**
    * guessAttribListener compares the selected attribute to the secret person's
    * attribute and turns over cards that are to be eliminated from consideration.
    *
    */
   public class guessAttribListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         // get selected attribute
         String selection = (String) attribBox.getSelectedValue();

         if (selection == null) {
            JOptionPane.showMessageDialog(null, "No attribute is currently selected.");
         } else {
            // selection matches an attribute of the secret person
            if (selection.matches(secretPerson.getGender()) || selection.matches(secretPerson.getEyeColor())
                  || selection.matches(secretPerson.getHairColor())
                  || selection.matches(secretPerson.getHasGlasses())
                  || selection.matches(secretPerson.getHasBeard())
                  || selection.matches(secretPerson.getHasMustache())) {
               guesses++; // increment guesses
               guessesMade.setText(Integer.toString(guesses)); // display guesses in txtfield

               // finding card to turn over
               for (int i = 0; i < SIZE; i++) {
                  if (!selection.matches(people[i].getGender()) && !selection.matches(people[i].getEyeColor())
                        && !selection.matches(people[i].getHairColor())
                        && !selection.matches(people[i].getHasGlasses())
                        && !selection.matches(people[i].getHasBeard())
                        && !selection.matches(people[i].getHasMustache())) {
                     pictures[i].setIcon(cardBack);
                  }
               }
            } else // selection doesn't match any attributes
            {
               guesses++; // increment guesses
               guessesMade.setText(Integer.toString(guesses)); // display guesses in txtfield

               // finding card to turn over
               for (int i = 0; i < SIZE; i++) {
                  if (selection.matches(people[i].getGender()) || selection.matches(people[i].getEyeColor())
                        || selection.matches(people[i].getHairColor())
                        || selection.matches(people[i].getHasGlasses())
                        || selection.matches(people[i].getHasBeard())
                        || selection.matches(people[i].getHasMustache())) {
                     pictures[i].setIcon(cardBack);
                  }
               }
            }
         }
      }
   }

   /**
    * guessPersonListener compares the selected person to the secret person's name
    * attribute and turns over cards that don't match or ends the game if the
    * correct guess was made.
    */
   public class guessPersonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         // get selected attribute
         String selection = (String) nameBox.getSelectedValue();

         if (selection == null) {
            JOptionPane.showMessageDialog(null, "No name is currently selected.");
         } else {
            // selection matches the name of the secret person
            if (selection.matches(secretPerson.getName())) {
               guesses++; // increment guesses
               guessesMade.setText(Integer.toString(guesses)); // display guesses in txtfield

               // changing question mark card icon to show secret person
               ImageIcon SP = new ImageIcon(secretPerson.getPicURL());
               sPersonButton.setIcon(SP);

               // show dialog box telling the player they won and asking if they want to play
               // again
               int choice = JOptionPane.showConfirmDialog(null,
                     selection + " is the correct guess!  " + "You took " + guesses
                           + " guesses.  Would you like to play again?",
                     "Play again?", JOptionPane.YES_NO_OPTION);

               if (choice == 0) {
                  // start new game if yes is selected
                  try {
                     dispose(); // close previous window
                     new GuessWho();
                  } catch (IOException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                  }
               } else {
                  System.exit(0); // exit if no is selected
               }

            } else // selection doesn't match the name
            {
               guesses++; // increment guesses
               guessesMade.setText(Integer.toString(guesses)); // display guesses in txtfield

               // finding card to turn over
               for (int i = 0; i < SIZE; i++) {
                  if (selection.matches(people[i].getName())) {
                     pictures[i].setIcon(cardBack);
                  }
               }
            }
         }
      }
   }

   public class attribButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {

         JButton btn = (JButton) e.getSource();

      }
   }

}
