package Final;

import java.awt.*;
import javax.swing.*;


public class JPanel02 extends JFrame{
    JButton b1,b2,b3,b4,b5,b6;
   
    public JPanel02(){
        super("패널");
        GridBagLayout gridbag = new GridBagLayout();
        getContentPane().setLayout(gridbag);
       
        GridBagConstraints constraint = new GridBagConstraints();
       
        constraint.fill=GridBagConstraints.BOTH;
        constraint.weightx = 1.0;
        constraint.weighty = 1.0;
        constraint.gridwidth = 1;
        constraint.gridheight = 2;
        b1 = new JButton("Button1");
        gridbag.setConstraints(b1, constraint);
        getContentPane().add(b1);
       
      //가로방향 남은여백 모두 활용
        constraint.gridwidth = GridBagConstraints.REMAINDER;
        constraint.gridheight = 1;
        constraint.weighty = 1;
        b5= new JButton("Button5");
        gridbag.setConstraints(b5, constraint);
        getContentPane().add(b5);
        
      //b5와 같은 constraint설정 유지
        b6 = new JButton("Button6");
        gridbag.setConstraints(b6, constraint);
        getContentPane().add(b6);
            
      //남은여백 모두 사용 remainder
        constraint.gridwidth = GridBagConstraints.REMAINDER;
        b2 = new JButton("Button2");
        gridbag.setConstraints(b2, constraint);
        getContentPane().add(b2);
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);
        setVisible(true);
       
    }
    public static void main(String args[]){
        new JPanel02();
    }
}