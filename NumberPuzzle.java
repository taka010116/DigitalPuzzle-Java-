import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class NumberPuzzle extends JPanel{
	ArrayList<Figure> figs = new ArrayList<Figure>();
	ArrayList<Rect> rects = new ArrayList<Rect>();
	Set<Integer> usedNumbers = new HashSet<>();
	
	BufferedImage img7, img6, img9;
	
	Text n1 = new Text( 65, 45, "1", new Font("Arial", Font.BOLD, 15));
	Text n2 = new Text( 25, 85, "1", new Font("Arial", Font.BOLD, 15));
	Text n3 = new Text( 105, 85, "1", new Font("Arial", Font.BOLD, 15));
	Text n4 = new Text( 65, 125, "1", new Font("Arial", Font.BOLD, 15));
	Text n5 = new Text( 25, 165, "1", new Font("Arial", Font.BOLD, 15));
	Text n6 = new Text( 65, 205, "1", new Font("Arial", Font.BOLD, 15));
	Text n7 = new Text( 105, 165, "1", new Font("Arial", Font.BOLD, 15));
	
	Text check = new Text( 30, 300, "Check: ", new Font("Arial", Font.BOLD, 20));
	Text clearText = new Text( 30, 330, "Correct: ", new Font("Arial", Font.BOLD, 20));
	
	JLabel l2 = new JLabel("4つの異なるデジタル数字が重なっています。");
	JLabel l5 = new JLabel("左に表示されている数字は線が重なっている数を表します。");
	JLabel l3 = new JLabel("重なっている4つの数字を当ててください。");
	JLabel l4 = new JLabel("6,7,9はそれぞれ以下の形を使用します。");
	
	JTextField f1 = new JTextField();
	JButton button = new JButton("答える");
	JLabel l1 = new JLabel("答えは四桁の整数値で小さい順に入力してね");
	JButton button2 = new JButton("次の問題");
	int clear = 0;
	int answer1 = 0;
	int[] answer = new int[4];
	int[] number = new int[7];
	
	public NumberPuzzle(){
		setLayout(null);
		add(f1);
		add(button);
		add(l1);
		add(button2);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		f1.setBounds(30, 230, 120, 30);
		button.setBounds(160, 230, 80, 30);
		l1.setBounds(30, 260, 250, 30);
		button2.setBounds(250, 230, 100, 30);
		l2.setBounds(130, 30, 260, 30);
		l3.setBounds(130, 70, 250, 30);
		l4.setBounds(130, 90, 260, 30);
		l5.setBounds(130, 50, 400, 30);
		addText();
		paintNumber1();
		paintNumber2();
		
		setNumber();
		numberZero();
		
		try{
			img7 = ImageIO.read(new File("7.png"));
			img6 = ImageIO.read(new File("6.png"));
			img9 = ImageIO.read(new File("9.png"));
		}catch(Exception ex){}
	
		sort( answer );
		
		/*
		for( int i = 0; i < 4; i++ ){
			System.out.println(answer[i]);
		}
		*/
		
		answer1 = answer[3] + answer[2] * 10 + answer[1] * 100 + answer[0] * 1000;
		//System.out.println(answer1);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed( ActionEvent evt){	
				int n = Integer.parseInt(f1.getText());
				if( n == answer1 ){
					check.setText("Check: YES");
					clear++;
					clearText.setText("Correct: " + clear);
				}else{
					check.setText("Check: NO");
				}
				repaint();
			}
		});
		
		button2.addActionListener(new ActionListener(){
			public void actionPerformed( ActionEvent evt){	
				usedNumbers.clear();
				setNumber();
				numberZero();
				sort(answer);
				answer1 = answer[3] + answer[2] * 10 + answer[1] * 100 + answer[0] * 1000;
        		//System.out.println(answer1);
				textNumber();
				textPrint();
				check.setText("Check: ");
				repaint();
			}
		});
		
		textNumber();
		textPrint();
		
	}
	
	public void numberZero(){
		for( int i = 0; i < 7; i++ ){
			number[i] = 0;
		}
	}
	
	public void textPrint(){
		n1.setText(String.valueOf(number[0]));
		n2.setText(String.valueOf(number[1]));
		n3.setText(String.valueOf(number[2]));
		n4.setText(String.valueOf(number[3]));
		n5.setText(String.valueOf(number[4]));
		n6.setText(String.valueOf(number[5]));
		n7.setText(String.valueOf(number[6]));
	}
	public void textNumber(){
		for( int i = 0; i < 4; i++ ){
			switch( answer[i]){
			case 1:
				number[2]++;
				number[6]++;
				break;
			case 2:
				number[0]++;
				number[2]++;
				number[3]++;
				number[4]++;
				number[5]++;
				break;
			case 3:
				number[0]++;
				number[2]++;
				number[3]++;
				number[5]++;
				number[6]++;
				break;
			case 4:
				number[1]++;
				number[2]++;
				number[3]++;
				number[6]++;
				break;
			case 5:
				number[0]++;
				number[1]++;
				number[3]++;
				number[5]++;
				number[6]++;
				break;
			case 6:
				number[0]++;
				number[1]++;
				number[3]++;
				number[4]++;
				number[5]++;
				number[6]++;
				break;
			case 7:
				number[0]++;
				number[2]++;
				number[6]++;
				break;
			case 8:
				number[0]++;
				number[1]++;
				number[2]++;
				number[3]++;
				number[4]++;
				number[5]++;
				number[6]++;
				break;
			case 9:
				number[0]++;
				number[1]++;
				number[2]++;
				number[3]++;
				number[5]++;
				number[6]++;
				break;
			}
		}
	}
	
	public void setNumber(){
		for( int i = 0; i < 4; i++ ){
			int newNumber;
			do{
				newNumber = (int)(Math.random()*9) + 1;
			}while(usedNumbers.contains(newNumber));
			answer[i] = newNumber;
			usedNumbers.add(newNumber);
		}
	}
	
	public void sort( int[] number ){
		int n = number.length;
		for( int i = 0; i < n-1; i++ ){
			for( int j = 0; j < n-1; j++ ){
				if( number[j] > number[j+1] ){
					int tmp = number[j];
					number[j] = number[j+1];
					number[j+1] = tmp;
				}
			}
		}
	}
	
	public void paintNumber1(){
		for( int i = 0; i < 3; i++ ){
			Rect rect = new Rect(Color.WHITE, 40, 30 + i * 80, 60, 20 );
			rects.add(rect);
			repaint();
		}
	}
	
	public void addText(){
		figs.add(n1);
		figs.add(n2);
		figs.add(n3);
		figs.add(n4);
		figs.add(n5);
		figs.add(n6);
		figs.add(n7);
		figs.add(check);
		figs.add(clearText);
	}
	
	public void paintNumber2(){
		for( int i = 0; i < 2; i++ ){
			for( int j = 0; j < 2; j++ ){	
				Rect rect1 = new Rect(Color.WHITE, 20 + i * 80, 50 + j * 80, 20, 60);
				rects.add(rect1);
				repaint();
			}
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for( Rect r : rects ){
			r.draw(g);
		}
		for( Figure f : figs ){
			f.draw(g);
		}
		
		g.drawImage(img6, 150, 130, null);
		g.drawImage(img7, 200, 130, null);
		g.drawImage(img9, 250, 130, null);
	}
	
	public static void main(String[] args) {
        JFrame app = new JFrame();
        app.add(new NumberPuzzle());
        app.setSize(500, 400);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

	interface Figure {
		public void draw(Graphics g);
	}
	
	static class Text implements Figure {
		int xpos, ypos;
		String txt;
		Font fn;

		public Text(int x, int y, String t, Font f) {
			xpos = x;
			ypos = y;
			txt = t;
			fn = f;
		}

		public void setText(String t) {
			txt = t;
		}

		public void draw(Graphics g) {
			g.setColor(Color.BLACK);
			g.setFont(fn);
			g.drawString(txt, xpos, ypos);
		}
	}
	
    static class Rect implements Figure {
        Color col;
        int xpos, ypos, width, height;

        public Rect(Color c, int x, int y, int w, int h) {
            col = c;
            xpos = x;
            ypos = y;
            width = w;
            height = h;
        }

        public boolean hit(int x, int y) {
            return x >= xpos && x <= xpos + width && y >= ypos && y <= ypos + height;
        }

        public void draw(Graphics g) {
            g.setColor(col);
            g.fillRect(xpos, ypos, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(xpos, ypos, width, height);
        }

        public void setColor(Color c) {
            col = c;
        }

        public int getX() {
            return xpos;
        }

        public int getY() {
            return ypos;
        }
    }
}