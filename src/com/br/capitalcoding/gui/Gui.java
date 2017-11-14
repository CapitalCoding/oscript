package com.br.capitalcoding.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.br.capitalcoding.gui.stronghold.GuiFloors;
import com.br.capitalcoding.gui.stronghold.SelectableAreas;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Objects;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Button;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.Choice;
import java.awt.Label;

public class Gui {

	private JFrame frame;
	SelectableAreas areas;
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        //UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceRavenLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);				
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(214, 11, 210, 230);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel panelFloor = new JLabel("");
		//panelFloor.setIcon(new ImageIcon("C:\\Users\\Diogo\\Desktop\\Bot\\death.png"));
		panelFloor.setIcon(GuiFloors.WAR.getImg());
		panelFloor.setHorizontalAlignment(SwingConstants.CENTER);
		panelFloor.setBounds(0, 0, 210, 230);
		panel.add(panelFloor);
		
		Choice npcChoice = new Choice();
		npcChoice.setBounds(129, 5, 79, 20);
		npcChoice.setForeground(Color.BLACK);
		frame.getContentPane().add(npcChoice);
		
		Choice floorChoice = new Choice();
		floorChoice.setForeground(Color.BLACK);
		floorChoice.setBackground(Color.WHITE);
		floorChoice.setBounds(46, 5, 79, 20);
		floorChoice.add("");
		floorChoice.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().toString().isEmpty())
					return;
				System.out.println("Item:"+e.getItem());
				npcChoice.removeAll();
				GuiFloors floor = GuiFloors.valueOf(e.getItem().toString());
				panelFloor.setIcon(floor.getImg());
				Arrays.asList(Objects.requireNonNull(floor.npcs())).forEach(i->npcChoice.add(i));
				
			}
		});
		Arrays.asList(GuiFloors.values()).forEach(i->floorChoice.add(i.name()));
		frame.getContentPane().add(floorChoice);
		

		
		
		Label label = new Label("Floor");
		label.setBounds(6, 3, 202, 22);
		frame.getContentPane().add(label);
	}
}
