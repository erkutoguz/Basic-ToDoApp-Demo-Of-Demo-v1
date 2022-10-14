import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ShowToDo extends JFrame implements ActionListener {
	JButton exitBtn = new JButton("Close");
	JLabel name;
	JLabel genre;
	JTextArea details;

	public ShowToDo(ToDo todo) {
		exitBtn.setBounds(135, 320, 150, 30);
		exitBtn.setBackground(new Color(210, 218, 255));
		exitBtn.setForeground(new Color(6, 17, 60));
		exitBtn.setFocusable(false);
		exitBtn.addActionListener(this);
		name = new JLabel("Title of ToDo => " + todo.getTitle());
		name.setFont(new Font("Arial", Font.BOLD, 12));
		name.setForeground(new Color(6, 17, 60));
		name.setBounds(30, 30, 300, 30);
		this.add(name);

		genre = new JLabel("Genre of ToDo => " + todo.getGenre());
		genre.setFont(new Font("Arial", Font.BOLD, 12));
		genre.setForeground(new Color(6, 17, 60));
		genre.setBounds(30, 90, 300, 30);
		this.add(genre);

		details = new JTextArea("Details about ToDo => " + todo.getDetail());
		details.setEditable(false);
		details.setLineWrap(true);
		details.setWrapStyleWord(true);
		details.setFont(new Font("Arial", Font.BOLD, 12));
		details.setLayout(new FlowLayout());
		details.setForeground(new Color(6, 17, 60));
		details.setBounds(30, 150, 300, 100);

		this.add(details);

		this.getContentPane().setBackground(Color.white);
		this.setSize(420, 420);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(exitBtn);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitBtn) {
			this.dispose();
		}

	}
}
