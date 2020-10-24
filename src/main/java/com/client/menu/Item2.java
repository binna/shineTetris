package com.client.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.client.socket.SocketConnect;
import com.client.common.Block;
import com.client.common.Data;
import com.client.common.GameFrame;

public class Item2 extends JMenuItem{
	public Item2() {
		super("Save");
		setMnemonic('s');
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Data.maxScore>0) {
					SocketConnect.getInstance().InsertRank(Data.maxScore);
				}else {
					JOptionPane.showMessageDialog(getParent(),"������ �����ϼ���!");
				}
				GameFrame.bgp.repaint();
			}
		});
	}
}
