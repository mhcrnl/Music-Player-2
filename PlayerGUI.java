package display;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.io.FileNotFoundException;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;

public class PlayerGUI {

	private JFrame frame;
	private JTextField pathField;
	private File songFile;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					PlayerGUI window = new PlayerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public PlayerGUI() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("MP3 ANONYMOUS235");
		frame.setBounds(100, 100, 290, 174);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JButton startBtn = new JButton("START\r\n");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Play Audio
				try
				{
					Player p = new Player(new FileInputStream(songFile));
					p.play();
				}catch (Exception e3)
				{
					JOptionPane.showMessageDialog(null,  "No File is selected !","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		startBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		startBtn.setBounds(10, 44, 254, 77);
		frame.getContentPane().add(startBtn);
		
		pathField = new JTextField();
		pathField.setEditable(false);
		pathField.setText("Song Path\r\n");
		pathField.setBounds(10, 11, 185, 20);
		frame.getContentPane().add(pathField);
		pathField.setColumns(10);
		
		JButton openBtn = new JButton("Open");
		openBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
				
			}
		});
		openBtn.setBounds(205, 10, 69, 23);
		frame.getContentPane().add(openBtn);
	}
	private void open() {
	try {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Choose Song to Load !");
		chooser.showOpenDialog(null);
		songFile=chooser.getSelectedFile();
		pathField.setText(songFile.getAbsolutePath());
		
		if(! songFile.getName().endsWith(".mp3")) {
			JOptionPane.showMessageDialog(null,  "Invalid Type Selected !","ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}catch (Exception e1) {
		e1.printStackTrace();
	}
}
}
