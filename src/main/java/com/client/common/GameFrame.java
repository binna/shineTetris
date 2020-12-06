package com.client.common;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.client.panel.BackGroundPanel;
import com.client.panel.CenterPanel;
import com.client.menu.TetrisMenuBar;
import com.client.panel.NextPanel;
import com.client.panel.SavePanel;
import com.client.socket.SocketConnect;

public class GameFrame extends JFrame implements KeyListener,Runnable{
	private static final long serialVersionUID = 1L;
	
	
	public static BackGroundPanel bgp=new BackGroundPanel();
	NextPanel np=new NextPanel();
	SavePanel sp=new SavePanel();
	CenterPanel cp=new CenterPanel();


	
	public GameFrame(){
		super("testris!");
		/* MenuBar */
		TetrisMenuBar menu = new TetrisMenuBar();
		setJMenuBar(menu);
		/* Setting Each Panel */
		bgp.add(sp,"West");
		bgp.add(cp,"Center");
		bgp.add(np,"East");
		
		add(bgp);

		pack();
		cp.requestFocus();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {bgp.repaint();}
			@Override
			public void mousePressed(MouseEvent e) {bgp.repaint();}
			
			@Override
			public void mouseExited(MouseEvent e) {
				Block.getInstance().gameHold();
				bgp.repaint();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {bgp.repaint();}
			
			@Override
			public void mouseClicked(MouseEvent e) {bgp.repaint();}
		});
	}
	
	@Override
	public void run() {
		cp.addKeyListener(this);
		CenterPanel.btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CenterPanel.btn.setText("RESTART");
				CenterPanel.btn.setVisible(false);
				CenterPanel.btnHold.setVisible(false);
				Block.getInstance().startCount();
				bgp.repaint();
				cp.requestFocus();
//				CenterPanel.btn.setBounds(10, 150, 150, 30);
			}
		});
		CenterPanel.btnHold.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CenterPanel.btnHold.setVisible(false);
				Data.status=true;
				cp.requestFocus();
				bgp.repaint();
			}
		});
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(Data.status){
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				Block.getInstance().left();
				break;
			case KeyEvent.VK_RIGHT:
				Block.getInstance().right();
				break;
			case KeyEvent.VK_UP:
				Block.getInstance().spin();
				break;
			case KeyEvent.VK_DOWN:
				Block.getInstance().down();
				break;
			case 32:
				//�����̽��ٷ� �س����� �ȵǼ� ���ڷ� ǥ��
				Data.spacestatus = true;
				int i=0;
				while (Data.spacestatus) {
					if(i>1) {
						Data.tSpin = false;
					}
					Block.getInstance().down();
					i++;
				}
				break;
			case KeyEvent.VK_P:
				Block.getInstance().gameHold();
				break;
			case KeyEvent.VK_Z:
				Block.getInstance().reverseSpin();
				break;
			case KeyEvent.VK_X:
				//also X is spin method;
				Block.getInstance().spin();
				break;
			case KeyEvent.VK_C:
				if (Data.savestatus) Block.getInstance().saveBlock();
				break;
			default:
				System.out.println(e.getKeyCode());
				break;
			}
			bgp.repaint();
	}}
	public void keyReleased(KeyEvent arg0) {}public void keyTyped(KeyEvent arg0) {}
	
	
}
