package de.mth.game.menu;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameButton extends Button implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GameButton() {
		this.addActionListener(this);
		
	}
	
	public void paint(Graphics g) {
		
		g.drawString("Bla", 10, 10);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("GameButton.actionPerformed()");
		
	}

}
