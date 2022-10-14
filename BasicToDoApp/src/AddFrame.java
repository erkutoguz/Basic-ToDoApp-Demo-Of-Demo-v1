import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class AddFrame extends JFrame implements ActionListener {
	public static boolean isAddedSuccesfullyOnDb = false;
	DbManager dbManager = new DbManager();
	Genre genreList[] = new Genre[] { Genre.FAMILY, Genre.FRIEND, Genre.HOBBY, Genre.SCHOOL, Genre.SPORT, Genre.WORK };
	JLabel toDoName = new JLabel("Name");
	JTextField toDoNameTF = new JTextField();
	JLabel toDoGenre = new JLabel("Genre");
	JComboBox toDoGenreCB = new JComboBox(genreList);
	JLabel toDoDetail = new JLabel("Detail");
	JTextArea toDoDetailsTA = new JTextArea();
	JButton addBtn = new JButton("Add");

	public AddFrame() {
		isAddedSuccesfullyOnDb = false;
		toDoName.setBounds(30, 30, 100, 30);
		toDoNameTF.setBounds(100, 30, 300, 30);
		toDoNameTF.setBackground(new Color(168, 164, 206));
		toDoNameTF.setForeground(Color.white);
		toDoNameTF.setFont(new Font("Arial", Font.BOLD, 12));
		toDoGenre.setBounds(30, 90, 100, 30);
		toDoGenreCB.setBounds(100, 90, 100, 30);
		toDoDetail.setBounds(30, 150, 100, 30);
		toDoDetailsTA.setBounds(100, 150, 300, 100);
		toDoDetailsTA.setBackground(new Color(168, 164, 206));
		toDoDetailsTA.setForeground(Color.white);
		toDoDetailsTA.setFont(new Font("Arial", Font.BOLD, 12));
		addBtn.setBounds(135, 320, 150, 30);
		addBtn.setBackground(new Color(210, 218, 255));
		addBtn.setForeground(new Color(6, 17, 60));
		this.add(toDoName);
		this.add(toDoNameTF);
		this.add(toDoGenre);
		this.add(toDoGenreCB);
		this.add(toDoDetail);
		this.add(toDoDetailsTA);
		this.add(addBtn);
		addBtn.addActionListener(this);
		this.setTitle("Add ToDo");
		this.getContentPane().setBackground(Color.white);
		this.setSize(new Dimension(420, 420));
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public ToDo createToDo(String name) {
		return new ToDo(name, Genre.HOBBY, "Details");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = null;
		Genre genre = null;
		String detail = null;
		ToDo todo = null;

		if (e.getSource() == addBtn) {
			name = toDoNameTF.getText();
			genre = Genre.getGenre(toDoGenreCB.getSelectedItem().toString());
			detail = toDoDetailsTA.getText();

			if (toDoNameTF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Name can not be empty!");
			} else if (toDoDetailsTA.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Details can not be empty!");
			} else {
				todo = new ToDo(name, genre, detail);
				try {
					dbManager.add(todo);
//					isAddedSuccesfullyOnDb = true;
					JOptionPane.showMessageDialog(null, "Successfully added.");
				} catch (MySQLIntegrityConstraintViolationException exception) {
					JOptionPane.showMessageDialog(null, "You have a ToDo as same name as " + name + ".");

				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			}

		}

	}
}
