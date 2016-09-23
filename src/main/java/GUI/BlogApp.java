package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Components.Blog;

public class BlogApp extends JFrame implements ActionListener{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private JPanel topPanel = new JPanel ();
	private JPanel center = new JPanel ();
	private JLabel lbTitle = new JLabel ("Title");
	private JTextField Title = new JTextField(50);
	private JButton setTitle =  new JButton("Set Title");
	private JLabel lbStyle = new JLabel ("Panel Style");
	private JTextField style = new JTextField (50);
	private JLabel mainStylelb = new  JLabel("Main page style");
	private JTextField mainStyle = new  JTextField(50);
	private JButton mainStyleBT = new  JButton("Add  Style to main body");
	private JButton addStyleBT = new JButton("addStyle");
	private JLabel blogStylelb = new  JLabel("Blog style");
	private JTextField blogStyle = new  JTextField(50);
	private JButton blogStyleBT = new  JButton("Add  Style to blogs");
	private JLabel html = new JLabel ("HTML");
	private JTextArea HTML = new JTextArea();
	private JButton publish = new JButton("Publish");
	private String title;
	private ArrayList <String> styles = new ArrayList <String>();
	private ArrayList <String> indexStyle = new ArrayList<String> ();
	private ArrayList <String> blogStyles = new ArrayList<String>();

	
	
	public BlogApp (){
		setSize(800,800);
		topPanel.setLayout(new GridLayout(4,3));
		topPanel.add(lbTitle);
		topPanel.add(Title);
		setTitle.addActionListener(this);
		topPanel.add(setTitle);
		topPanel.add(lbStyle);
		topPanel.add(style);
		addStyleBT.addActionListener(this);
		topPanel.add(addStyleBT);
		topPanel.add( mainStylelb);
		topPanel.add(mainStyle);
		mainStyleBT.addActionListener(this);
		topPanel.add(mainStyleBT);
		topPanel.add(blogStylelb);
		topPanel.add(blogStyle);
		blogStyleBT.addActionListener(this);
		topPanel.add(blogStyleBT);
		center.setLayout(new BorderLayout());
		center.add(html, BorderLayout.NORTH);
		center.add(new JScrollPane(HTML), BorderLayout.CENTER);
		container.setLayout(new GridLayout(3,3));
		container.add(topPanel);
		container.add(center);
		publish.addActionListener(this);
		container.add(publish);
		add(container);
		setTitle("Blog Creator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new BlogApp();
			
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==setTitle){
			title = Title.getText();
		}
		if(e.getSource()==addStyleBT){
			styles.add(style.getText());
			JOptionPane.showMessageDialog(this, "added style: "+style.getText());	
			style.setText("");
		
		}
		if(e.getSource()==mainStyleBT){
			indexStyle.add(mainStyle.getText());
			JOptionPane.showMessageDialog(this, "added style: "+ mainStyle.getText());	
			mainStyle.setText("");
		
		}
		if(e.getSource()==blogStyleBT){
			blogStyles.add(blogStyle.getText());
			JOptionPane.showMessageDialog(this, "added style: "+ blogStyle.getText());	
			blogStyle.setText("");
		
		}
		
		if(e.getSource()==publish){
			Blog  blog = new Blog(title ,HTML.getText());
			blog.addCSSProperties(styles);
			JOptionPane.showMessageDialog(this, blog.publish(indexStyle, blogStyles));	
		}
	}

}
