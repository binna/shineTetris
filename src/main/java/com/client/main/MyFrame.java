package com.client.main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
 
class MyFrame implements ListSelectionListener, ItemListener
{
	public static void main(String[] args) {
		MyFrame my = new MyFrame();
	}
    private JFrame frm;
    private JButton btn;
    private JLabel showFontLabel;
    private JList<String> fontList;
    private JPanel panel;
    private JCheckBox boldCheck;
    private JCheckBox italicCheck;
    private JList<Integer> sizeList;
    
    MyFrame() 
    {
    	//멤버 생성
        frm = new JFrame();
        btn = new JButton("얼굴 변환");
        showFontLabel = new JLabel("abc ABC 가나다 English 안녕하세요 Hi~", SwingConstants.HORIZONTAL);
        fontList = new JList<String>();
        panel = new JPanel();
        boldCheck = new JCheckBox("굵게");
        italicCheck = new JCheckBox("기울게");
        sizeList = new JList<Integer>();
        
      //폰트사이즈 리스트 설정
        Integer[] font_size = new Integer[100];
        for(int i = 0 ; i < 100 ; ++i)
        {
            font_size[i] = i+1;
        }
        sizeList.setListData(font_size);
        sizeList.addListSelectionListener(this);
        
        //시스템내 존재하는 폰트 목록 리스트 설정
        GraphicsEnvironment g;
        g = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] font_list = g.getAvailableFontFamilyNames();
        fontList.setListData(font_list);
        fontList.addListSelectionListener(this);
        
      //체크박스 이벤트 핸들러 장착
        boldCheck.addItemListener(this);
        italicCheck.addItemListener(this);
        
      //패널에 컴포넌트 장착
        panel.add(new JScrollPane(fontList));
        panel.add(new JScrollPane(sizeList));
        panel.add(boldCheck);
        panel.add(italicCheck);
        
        //프레임에 패널 장착
        frm.add(showFontLabel, BorderLayout.CENTER);
        frm.add(panel, BorderLayout.SOUTH);
        
      //프레임 기본설정
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setBounds(120, 120, 410, 300);
        frm.setVisible(true);
    }
 
    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        if(e.getSource() == fontList)
        {
            String fontName = fontList.getSelectedValue();
            String styleStr;
            int size = showFontLabel.getFont().getSize();
            int styleNum = showFontLabel.getFont().getStyle();
            
            if((Font.BOLD | Font.ITALIC)  == styleNum)
            {
                styleStr = "BOLDITALIC";
            }
            else if(Font.BOLD == styleNum)
            {
                styleStr = "BOLD";
            }
            else if(Font.ITALIC == styleNum)
            {
                styleStr = "ITALIC";
            }
            else if(Font.PLAIN == styleNum)
            {
                styleStr = "PLAIN";
            }
            else
            {
                styleStr = "PLAIN";
            }
           
            String font_style_size = fontName + "-" + styleStr + "-" + size;
            Font font = Font.decode(font_style_size);
            showFontLabel.setFont(font);
        }
        else if(e.getSource() == sizeList)
        {
            Integer fontSize = sizeList.getSelectedValue();
            Font font = showFontLabel.getFont().deriveFont((float)fontSize);
            showFontLabel.setFont(font);    
        }
    }
 
    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        if((e.getSource() == boldCheck) || (e.getSource() == italicCheck))
        {
        	//둘다 체크되어 있을 때
            if(boldCheck.isSelected() && italicCheck.isSelected()) 
            {
                Font font = showFontLabel.getFont().deriveFont(Font.BOLD | Font.ITALIC);
                showFontLabel.setFont(font);
                return;
            }
          //굴게에만 체크 되어 있을 때
            else if(boldCheck.isSelected() == true)
            {
                Font font = showFontLabel.getFont().deriveFont(Font.BOLD);
                showFontLabel.setFont(font);
            }
          //기울게에만 체크 되어 있을 때
            else if(italicCheck.isSelected() == true)
            {
                Font font = showFontLabel.getFont().deriveFont(Font.ITALIC);
                showFontLabel.setFont(font);
            }
            //둘다 체크 안되어 있을 때
            else
            {
                Font font = showFontLabel.getFont().deriveFont(Font.PLAIN);
                showFontLabel.setFont(font);
            }
        }
    }
}