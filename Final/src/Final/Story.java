package Final;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import Final.GuessWho;

// Panel에 배경이미지 넣음
class ImagePanel extends JPanel {
	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}

public class Story_Start_Inst{
	JFrame f1;
	Frame f;
	CardLayout card;
	ImagePanel p0, p1, p2, p3, p4, p5, p6;
	Label l0, l1, l2, l3, l4, l5, l6;
	Button startbt;
	String find;

	public Story_Start_Inst() {

		f = new Frame("김눈송이 누구야");
		card = new CardLayout();
		f.setLayout(card);
		//f.setLocationRelativeTo(null);
		f.setLocation(0, 0);
		f.setVisible(true);

		
		startbt = new Button("게임시작");
		startbt.setSize(300, 70);
		startbt.setLocation(250, 600);
		
		l0 = new Label("Start Panel");
		l1 = new Label("First Panel");
		l2 = new Label("Second Panel");
		l3 = new Label("Third Panel");
		l4 = new Label("Fourth Panel");
		l5 = new Label("Fifth Panel");
		l6 = new Label("Sixth Panel");
		
		p0 = new ImagePanel(new ImageIcon("0.jpeg").getImage());
		p1 = new ImagePanel(new ImageIcon("1.jpeg").getImage());
		p2 = new ImagePanel(new ImageIcon("2.jpeg").getImage());
		p3 = new ImagePanel(new ImageIcon("3.jpeg").getImage());
		p4 = new ImagePanel(new ImageIcon("4.jpeg").getImage());
		p5 = new ImagePanel(new ImageIcon("5.jpeg").getImage());
		p6 = new ImagePanel(new ImageIcon("Inst.png").getImage());
		
		MouseHandler handler = new MouseHandler();
		MouseHandler2 handler2 = new MouseHandler2();
		
		p0.add(startbt);
		p0.add(l0); startbt.addMouseListener(handler);
		p1.add(l1); p1.addMouseListener(handler);
		p2.add(l2); p2.addMouseListener(handler);
		p3.add(l3); p3.addMouseListener(handler);
		p4.add(l4); p4.addMouseListener(handler);
		p5.add(l5); p5.addMouseListener(handler);
		p6.add(l6); p6.addMouseListener(handler2);
	
		f.add("Start", p0);
		f.add("First", p1);
		f.add("Second", p2);
		f.add("Third", p3);
		f.add("Fourth", p4);
		f.add("Fifth", p5);
		f.add("Inst", p6);
		//card.show(f, "First");
		f.pack();
		


		
	}


	public class MouseHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			card.next(f);
		}
		public void mousePressed(MouseEvent e) {/*구현생략*/}
	    @Override  //마우스 이벤트  인터페이스 구현 
	    public void mouseReleased(MouseEvent e) {/*구현생략*/}
	    @Override  //마우스 이벤트  인터페이스 구현 
	    public void mouseEntered(MouseEvent e) {/*구현생략*/}    
	    @Override  //마우스 이벤트  인터페이스 구현 
	    public void mouseExited(MouseEvent e) {/*구현생략*/}
	}
	
	public class MouseHandler2 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			f.dispose();
			CreateDialog();
		}
		public void mousePressed(MouseEvent e) {/*구현생략*/}
	    @Override  //마우스 이벤트  인터페이스 구현 
	    public void mouseReleased(MouseEvent e) {/*구현생략*/}
	    @Override  //마우스 이벤트  인터페이스 구현 
	    public void mouseEntered(MouseEvent e) {/*구현생략*/}    
	    @Override  //마우스 이벤트  인터페이스 구현 
	    public void mouseExited(MouseEvent e) {/*구현생략*/}
	}
	
	private void CreateDialog() {
		//팝업창
		final Dialog info = new Dialog(f1, "김눈송이 누구야", true);
	    info.setSize(300,100);
	    info.setLocation(300,400);
	    info.setLayout(new FlowLayout());
	   
	    Label msg = new Label("도대체 김눈송이는 누굴까요? 찾아보러 갈까요?!", Label.CENTER);
	    Button ok = new Button("OK");
	    ok.addActionListener(new ActionListener(){
	           public void actionPerformed(ActionEvent ev){
	        	   new Game();
	        	   f.dispose();
	           }
	    });         
	    info.add(msg);
	    info.add(ok);
	    info.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Story_Start_Inst();
	}
		
}
