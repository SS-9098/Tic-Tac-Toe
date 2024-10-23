import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class tic implements ActionListener
{
	int flag=0;
	char turn1='X', AITurn='-';
	char[] state=new char[9];
	static JPanel score=new JPanel();
	static JLabel turn=new JLabel("X's turn");
	static JButton[] buttons=new JButton[9];
	static JButton reset=new JButton("RESET");
	static JButton ai = new JButton("AI OFF");
	static JButton aiTurn = new JButton("AI is O");
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
	aiTurn.addActionListener(this);
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
		
		
		reset.setBounds(257,10,123,50);
		reset.setFont(new Font("",Font.PLAIN,24));
		reset.setForeground(Color.RED);
		reset.setBackground(Color.lightGray);
		reset.setBorder(new LineBorder(Color.darkGray));
		reset.setFocusable(false);

		ai.setBounds(9,10,123,50);
		ai.setFont(new Font("",Font.PLAIN,24));
		ai.setForeground(Color.RED);
		ai.setBackground(Color.lightGray);
		ai.setBorder(new LineBorder(Color.darkGray));
		ai.setFocusable(false);

		aiTurn.setBounds(133,10,123,50);
		aiTurn.setFont(new Font("",Font.PLAIN,24));
		aiTurn.setForeground(Color.RED);
		aiTurn.setBackground(Color.lightGray);
		aiTurn.setBorder(new LineBorder(Color.darkGray));
		aiTurn.setFocusable(false);
		aiTurn.setVisible(false);
		aiTurn.setEnabled(false);
		
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
		score.add(aiTurn);
		score.add(reset);
		score.add(turn);
		frame.add(game);
		frame.add(score);
		frame.setVisible(true);
	}

	public void Win()
	{
		int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] condition : winConditions) {
            if (state[condition[0]] == state[condition[1]] && state[condition[1]] == state[condition[2]]) {
                if (state[condition[0]] == 'X')
                    turn.setText("X wins!");
                else if (state[condition[0]] == 'O')
                    turn.setText("O wins!");
                flag = 1;
            }
        }
	}

	public void AI()
	{
		int move = obj.move(state, AITurn);
		if(move == -1)
			return;
		if (AITurn == 'O') {
			buttons[move].setText("O");
			turn1 = 'X';
			turn.setText("X's Turn");
			state[move] = 'O';
		} else {
			buttons[move].setText("X");
			turn1 = 'O';
			turn.setText("O's Turn");
			state[move] = 'X';
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		for(int i=0;i<9;i++)
		{
			if(e.getSource()==buttons[i] && turn1=='X' && buttons[i].getText()==null && flag==0)
			{
				if(AITurn != 'X') {
					buttons[i].setText("X");
					turn1 = 'O';
					turn.setText("O's Turn");
					state[i] = 'X';
					Win();
					if(AITurn=='O' && flag!=1)
						AI();
				}
			}
			
			else if(e.getSource()==buttons[i] && turn1=='O' && buttons[i].getText()==null && flag==0)
			{
				if(AITurn != 'O') {
					buttons[i].setText("O");
					turn1 = 'X';
					turn.setText("X's Turn");
					state[i] = 'O';
					if(AITurn=='X' && flag!=1)
						AI();
				}
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
			if (AITurn == 'X')
				AI();
		}

		if(e.getSource()==ai)
		{
			if (ai.getBackground() != Color.GREEN)
			{
				ai.setText("AI ON");
				ai.setBackground(Color.green);
				aiTurn.setVisible(true);
				aiTurn.setEnabled(true);
				AITurn = 'O';
			}
			else
			{
				ai.setText("AI OFF");
				ai.setBackground(Color.LIGHT_GRAY);
				aiTurn.setVisible(false);
				aiTurn.setEnabled(false);
				AITurn = '-';
			}
		}

		if(e.getSource()==aiTurn)
		{
			if(AITurn == 'X') {
				AITurn = 'O';
				aiTurn.setText("AI is O");
			} else if (AITurn == 'O') {
				AITurn = 'X';
				aiTurn.setText("AI is X");
				AI();
			}
		}
	}

}

