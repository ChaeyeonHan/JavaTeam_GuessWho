package Final;

import java.io.File; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.swing.ImageIcon; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel;

public class music_con { 

		public static void audio() { 
			try { 
				File file = new File("src//music//test.wav"); 
				Clip clip = AudioSystem.getClip(); 
				clip.open(AudioSystem.getAudioInputStream(file)); 
				clip.loop(3); //이거는 지금 3번 반복되는거
				//clip.loop(Clip.LOOP_CONTINUOUSLY); 창 닫기전까지 계속 재생
				clip.start(); 
			} catch (Exception e) {
				System.err.println("Put the music.wav file in the sound folder if you want to play background music, only optional!"); 
				} 
			} 
		
		private static String arg; 
		public static void main(String[] args){ 
		
			arg = "background.gif"; 
			JFrame f = new JFrame(); 
			JPanel p = new JPanel(); 
			JLabel l = new JLabel(); 
			ImageIcon icon = new ImageIcon(arg); 
			f.setSize(480, 360); 
			f.setVisible(true); 
			l.setIcon(icon); 
			p.add(l); 
			f.getContentPane().add(p); 
			f.setLocationRelativeTo(null); 
			f.setResizable(false); 
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			audio();
			}
		}


		
		
		
		
		
		
		
		
		
		
		
		