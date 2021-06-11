package Final;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GuessWho extends JFrame {

   private static final long serialVersionUID = 1L;

   private Person secretPerson; // 비밀사람 선정
   private final int SIZE = 20; // array의 크기 설정
   private JButton[] pictures = new JButton[SIZE]; // button과 그 위 사진들 만들기 위한 array
   private Person[] people = new Person[SIZE]; // 사람 객체 갖고 있는 array
   private ImageIcon[] images = new ImageIcon[SIZE]; // 이미지들 갖고 있는 array
   private JPanel mainPanel = new JPanel(); // main panel
   private JPanel sidePanel = new JPanel(); // side menu panel
   private JList attribBox;
   private JList nameBox;
   private JButton guessAttrib;
   private JButton guessPerson; 
   private JPanel numGuesses;
   private JTextField guessesMade; // text field holding the number of guesses made
   private int guesses; // holds number of guesses made
   private ImageIcon cardBack = new ImageIcon("src//image//cardback.jpg"); // image for card back used when turning
   private JPanel peopleList;
   private JPanel attribList;
   private JPanel sPersonPane;
   private ImageIcon sPersonImage = new ImageIcon("src//image//qmark.png");
   //private JPanel logoPane = new JPanel(); // 김눈송이 누구야
   private JPanel scorePane = new JPanel(); //점수판
   private JButton sPersonButton; //to hold sPersonImage
   
   private JScrollPane spPeople;
   private JScrollPane spAttrib;
   private Clip clip;

   // attributes to choose from
   private String[] attributes = { "glasses", "glasses X", "dye", "dye X", "blush", "blush X", "ribbon", "ribbon X",
         "shoes", "shoes X", "bag", "bag X" };
   // names to choose from
   private String[] names = { "눈송1", "눈송2", "눈송3", "눈송4", "눈송5", "눈송6", "눈송7", "눈송8", "눈송9",
	         "눈송10", "눈송11", "눈송12", "눈송13", "눈송14", "눈송15", "눈송16", "눈송17", "눈송18", "눈송19", "눈송20"};

   // the constructor sets up the game
   public GuessWho() throws IOException {

      buildPersonArray(); // build person array from the information within the attribute text file
      buildMainPanel(); // build the main panel that contains the cards/images
      buildSidePanel(); // build the side panel that contains attribute buttons (if pressed it
                     // automatically functions)

      pack(); //// JFrame의 내용물에 알맞게 윈도우 크기 조절해주는 메소드
      setVisible(true); // set form visible
      setExtendedState(JFrame.MAXIMIZED_BOTH);
      setResizable(false);

      getSecretPerson(); // obtaining the secret person

   }
   
   public void Init() {
         playSound("src//audio//test.wav",true);
      }
      
      
      public void playSound(String pathName, boolean isLoop) {
        
            try {
               clip = AudioSystem.getClip();
               File audioFile = new File("src//audio//test.wav");
               AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
               clip.open(audioStream);
               clip.start();
               if(isLoop)
                  clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch(Exception e){
            System.err.println("Put the music.wav file in the sound folder if you want to play background music, only optional!"); 
            } 
      
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
               inputFile.nextLine().toString(), // storing glasses
               inputFile.nextLine().toString(), // storing dye
               inputFile.nextLine().toString(), // storing blush
               inputFile.nextLine().toString(), // storing ribbon
               inputFile.nextLine().toString(), // storing shoes
               inputFile.nextLine().toString()); // storing bag
         inputFile.nextLine(); // skip past # separation character for next person to be stored
         
      }

      // close file
      inputFile.close();
   }

   /**
    * buildMainPanel builds the main panel that stores the cards/pictures
    */
   public void buildMainPanel() {
      setTitle("김눈송이 누구야"); // set title
      setLayout(new BorderLayout()); // set layout

      mainPanel.setLayout(new GridLayout(4,5,60,20)); // set panel layout
      
      // assigning buttons
      for (int i = 0; i < SIZE; i++) {
         images[i] = new ImageIcon(people[i].getPicURL());
         pictures[i] = new JButton(images[i]);
         mainPanel.add(pictures[i]);
      }
      
      Color b = new Color(237,248,252);
      mainPanel.setBackground(b);
      add(mainPanel, BorderLayout.CENTER); // add main panel to center of frame
      mainPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,100));
   }

   /**
    * buildSidePanel builds the side panel containing attribute lists and guess
    * buttons
    */
   public void buildSidePanel() {
     buildAttrib();
     buildPerson();
      //buildImage(); // 김눈송이 누구야
      buildNumGuesses(); // build panel to hold num of guesses made
      //buildAttribPane(); // 특징 질문판 있는 Panel을 SidePanel에 붙이기
       buildSecretPerson();
       
       sidePanel.setLayout(new GridLayout(2,2));
       
       sidePanel.add(sPersonPane);
       sidePanel.add(attribList);
       sidePanel.add(numGuesses);
       sidePanel.add(peopleList);

      sidePanel.setBackground(Color.white);
      add(sidePanel, BorderLayout.EAST); // add side panel to right side of frame

   }

   /**
    * 김눈송이 누구야 로고 붙이기
    */
   /*public void buildImage() {
      logoPane = new JPanel();
      ImageIcon nunlogo = new ImageIcon("src//image//nunlogo.jpg");
      Image image = nunlogo.getImage();
      Image newlogo = image.getScaledInstance(120, 90, java.awt.Image.SCALE_SMOOTH);
      nunlogo = new ImageIcon(newlogo);
      JLabel nunlabel = new JLabel(nunlogo);
      logoPane.setBackground(Color.white);
      logoPane.add(nunlabel);
      sidePanel.add(logoPane);
   }*/
   
   public void buildSecretPerson()
   { 
      sPersonPane = new JPanel();
      
      JLabel spLabel = new JLabel("Secret Person: ");
      sPersonButton = new JButton(sPersonImage);
      
      sPersonPane.setLayout(new BoxLayout(sPersonPane, BoxLayout.PAGE_AXIS));
      sPersonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
      spLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      sPersonPane.add(spLabel);
      sPersonPane.add(sPersonButton);
      
   }
   /**
    * buildNumGuesses builds a panel holding the number of guesses made
    */
   
   public void buildAttrib()
   {
      attribList = new JPanel();
      
      attribBox = new JList(attributes); //creating list box holding attributes to choose from
      guessAttrib = new JButton("Guess Attribute");
      guessAttrib.addActionListener(new guessAttribListener()); 
      
      //setting layout and centering components
      attribList.setLayout(new BorderLayout());
      attribList.add(attribBox,"Center");
      attribList.add(guessAttrib,"South");
   }
   
   
   /**
    * buildPerson builds a panel holding the person list and person guess button
    */
   public void buildPerson()
   {
     
      peopleList = new JPanel();
      
      spPeople = new JScrollPane();
      nameBox = new JList(names); //creating list box holding names to choose from
      spPeople.setViewportView(nameBox);
      //spPeople = new JScrollPane(nameBox);
      //spPeople.setViewportView(nameBox);
      //nameBox.setLayoutOrientation(JList.VERTICAL);
      
      peopleList.add(spPeople);
      
      guessPerson = new JButton("Guess Person");
      guessPerson.addActionListener(new guessPersonListener());
      
      //setting layout and centering components
      peopleList.setLayout(new BorderLayout());
      peopleList.add(nameBox,"Center");
      peopleList.add(guessPerson,"South");

   }
   
   public void buildNumGuesses() {
      numGuesses = new JPanel(); // create panel

      JLabel label = new JLabel("Guesses Made: "); // create label

      guessesMade = new JTextField(4); // create text field
      guessesMade.setEditable(false); // set to uneditable

      guessesMade.setText(Integer.toString(guesses));

      numGuesses.setBackground(Color.white);
      numGuesses.add(label); // add label to panel
      numGuesses.add(guessesMade); // add text field to panel

   }

   /*public void buildAttribPane() {
      attribPane = new JPanel();
      for (int i = 0; i < 12; i++) {
         bt[i] = new JButton(String.valueOf(i));
         attribPane.add(bt[i]);
         bt[i].addActionListener(new attribButtonListener());
      }
      attribPane.setBackground(Color.white);
      attribPane.setLayout(new GridLayout(3, 4)); // set panel layout
      sidePanel.add(attribPane);
   } */

   /**
    * getSecretPerson gets a random number between 0 and 19 and assigns the person
    * to a value stored at the corresponding subscript in the people array.
    */
   public void getSecretPerson() {
      int random = (int) (Math.random() * (20)); // creating random number
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
            if (selection.matches(secretPerson.getGlasses()) || selection.matches(secretPerson.getDye())
                  || selection.matches(secretPerson.getBlush())
                  || selection.matches(secretPerson.getRibbon())
                  || selection.matches(secretPerson.getShoes())
                  || selection.matches(secretPerson.getBag())) {
               guesses++; // increment guesses
               guessesMade.setText(Integer.toString(guesses)); // display guesses in txtfield

               // finding card to turn over
               for (int i = 0; i < SIZE; i++) {
                  if (!selection.matches(people[i].getGlasses()) && !selection.matches(people[i].getDye())
                        && !selection.matches(people[i].getBlush())
                        && !selection.matches(people[i].getRibbon())
                        && !selection.matches(people[i].getShoes())
                        && !selection.matches(people[i].getBag())) {
                     pictures[i].setIcon(cardBack);
                  }
               }
            } else // selection doesn't match any attributes
            {
               guesses++; // increment guesses
               guessesMade.setText(Integer.toString(guesses)); // display guesses in txtfield

               // finding card to turn over
               for (int i = 0; i < SIZE; i++) {
                  if (selection.matches(people[i].getGlasses()) || selection.matches(people[i].getDye())
                        || selection.matches(people[i].getBlush())
                        || selection.matches(people[i].getRibbon())
                        || selection.matches(people[i].getShoes())
                        || selection.matches(people[i].getBag())) {
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

               /*// changing question mark card icon to show secret person
               ImageIcon SP = new ImageIcon(secretPerson.getPicURL());
               sPersonButton.setIcon(SP); */
             //changing question mark card icon to show secret person
               
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
   
   
	public static void main(String[] args) throws IOException 
	{
		new GuessWho();
	}

}