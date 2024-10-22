import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class tic implements ActionListener
{
	int flag=0;
	char turn1='X';
	char[] state=new char[9];
	static JPanel score=new JPanel();
	static JLabel turn=new JLabel("X's turn");
	static JButton[] buttons=new JButton[9];
	static JButton reset=new JButton("RESET");
	static JButton ai = new JButton("AI OFF");
	static AI obj = new AI();
	
	void init()
	{
	for(int i=0;i<9;i++)
	{
		buttons[i]=new JButton();
		buttons[i].setText(null);
		buttons[i].addActionListener(this);
	}
	reset.addActionListener(this);
	ai.addActionListener(this);
	}

	public static void main(String[] args) 
	{
		new tic().init();
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,510);
		frame.setTitle("Tic Tac Toe");
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		Font font=new Font("Camp",Font.PLAIN,50);
		
		JPanel game=new JPanel();
		game.setLayout(new GridLayout(3,3,10,10));
		game.setBounds(10,100,388,363);
		game.setBackground(Color.LIGHT_GRAY);
		
		
		score.setBounds(10,0,388,100);
		score.setBackground(Color.LIGHT_GRAY);
		score.setLayout(null);
		
		turn.setFont(new Font(null,Font.PLAIN,30));
		turn.setBounds(10,25,200,100);
		
		
		reset.setBounds(260,10,120,50);
		reset.setFont(new Font("",Font.PLAIN,24));
		reset.setForeground(Color.RED);
		reset.setBackground(Color.lightGray);
		reset.setBorder(new LineBorder(Color.darkGray));
		reset.setFocusable(false);

		ai.setBounds(9,10,120,50);
		ai.setFont(new Font("",Font.PLAIN,24));
		ai.setForeground(Color.RED);
		ai.setBackground(Color.lightGray);
		ai.setBorder(new LineBorder(Color.darkGray));
		ai.setFocusable(false);
		
		for(int i=0;i<9;i++)
		{
			buttons[i].setFocusable(false);
			buttons[i].setFont(font);
			buttons[i].setForeground(Color.black);
			buttons[i].setBackground(Color.gray);
			buttons[i].setBorder(new LineBorder(Color.darkGray));
			game.add(buttons[i]);
		}

		score.add(ai);
		score.add(reset);
		score.add(turn);
		frame.add(game);
		frame.add(score);
		frame.setVisible(true);
	}

	public void Win()
	{
		if(state[0]==state[1]&&state[1]==state[2]&&state[2]!='\u0000' || state[3]==state[4]&&state[4]==state[5]&&state[5]!='\u0000' || state[6]==state[7]&&state[7]==state[8]&&state[8]!='\u0000' || state[0]==state[3]&&state[3]==state[6]&&state[6]!='\u0000' || state[1]==state[4]&&state[4]==state[7]&&state[7]!='\u0000' || state[2]==state[5]&&state[5]==state[8]&&state[2]!='\u0000'  || state[2]==state[4]&&state[4]==state[6]&&state[2]!='\u0000' || state[0]==state[4]&&state[4]==state[8]&&state[8]!='\u0000' )
		{
			if(turn1=='X')
				turn.setText("O wins!");
			else
				turn.setText("X wins!");
			flag=1;
		}
	}

	public void AI()
	{
		int move = obj.move(state);
		if(move == -1)
			return;
		buttons[move].setText("O");
		turn1='X';
		turn.setText("X's Turn");
		state[move]='O';
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i=0;i<9;i++)
		{
			if(e.getSource()==buttons[i] && turn1=='X' && buttons[i].getText()==null && flag==0)
			{
				buttons[i].setText("X");
				turn1='O';
				turn.setText("O's Turn");
				state[i]='X';
				Win();
				if(ai.getBackground()==Color.GREEN && flag!=1)
					AI();
			}
			
			else if(e.getSource()==buttons[i] && turn1=='O' && buttons[i].getText()==null && flag==0)
			{
				buttons[i].setText("O");
				turn1='X';
				turn.setText("X's Turn");
				state[i]='O';
			}
		}
		int count=0;
		for(int i=0;i<9;i++)
		{

			if(state[i]=='X' || state[i]=='O')
				count++;
			if(count==9)
				turn.setText("Draw!");
		}
		Win();

		
		if(e.getSource()==reset)
		{
			turn.setText("X's turn");
			flag=0;
			turn1='X';
			for(int i=0;i<9;i++)
			{
				state[i]='\u0000';
				buttons[i].setText(null);
				buttons[i].setEnabled(true);
			}
		}

		if(e.getSource()==ai)
		{
			if (ai.getBackground() != Color.GREEN)
			{
				ai.setText("AI ON");
				ai.setBackground(Color.green);
			}
			else
			{
				ai.setText("AI OFF");
				ai.setBackground(Color.LIGHT_GRAY);
			}
		}
	}

}

