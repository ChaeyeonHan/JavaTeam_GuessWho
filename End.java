package Final;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JFrame;

public class End {
	JFrame f2;

	private void TheEnd() {
		//팝업창
		final Dialog end = new Dialog(f2, "김눈송이 누구야", true);
	    end.setSize(300,100);
	    end.setLocation(300,400);
	    end.setLayout(new FlowLayout());
	   
	    Label msg = new Label("눈송이를 찾았습니다", Label.CENTER);
	    Button again = new Button("다시하기");
	    Button bye = new Button("종료");
	    
	    again.addActionListener(new ActionListener(){
	           public void actionPerformed(ActionEvent ev){
	        	   try {
	        		   end.dispose();
	        		   new GuessWho();
	        	   }
	        	   catch(Exception e) {
	        		   System.out.println("김눈송이 누구야");
	        	   }
	           }
	    });    
	    
	    bye.addActionListener(new ActionListener(){
	           public void actionPerformed(ActionEvent ev){
	        	   try {
	        		   end.dispose();
	        		   System.exit(0);
	        	   }
	        	   catch(Exception e) {
	        		   System.out.println("김눈송이 누구야");
	        	   }

	           }
	    });
	    
	    end.add(msg);
	    end.add(again);
	    end.add(bye);
	    end.setVisible(true);
	}
	
}
