import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainFrame extends JFrame implements ActionListener {
	DbManager dbManager = new DbManager();
	ArrayList<JButton> todoBtnList = new ArrayList<>();
	JPanel optionPanel;
	JPanel todoPanel;
	JButton addBtn;
	JButton delBtn;
	Timer timer;
	boolean deleteMode = false;

	public MainFrame() {
		optionPanel = new JPanel();
		todoPanel = new JPanel();

		addBtn = new JButton("Add ToDo");
		delBtn = new JButton("Delete ToDo");
		addBtn.setForeground(new Color(6, 17, 60));
		addBtn.setBackground(new Color(210, 218, 255));
		addBtn.setFocusable(false);
		delBtn.setFocusable(false);
		delBtn.setForeground(new Color(6, 17, 60));
		delBtn.setBackground(new Color(210, 218, 255));
		addBtn.addActionListener(this);
		delBtn.addActionListener(this);
		addBtn.setPreferredSize(new Dimension(140, 40));
		delBtn.setPreferredSize(new Dimension(140, 40));

		todoPanel.setPreferredSize(new Dimension(1280, 0));
		todoPanel.setBackground(Color.white);
//		todoPanel.setBackground(new Color(191, 172, 224));
		todoPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		optionPanel.add(addBtn);
		optionPanel.add(delBtn);
		optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 140, 30));
		optionPanel.setPreferredSize(new Dimension(1280, 100));
		optionPanel.setBackground(new Color(147, 125, 194));

		addTodoBtnOnPanel();

		this.setLayout(new BorderLayout());
		this.add(todoPanel);
		this.add(optionPanel, BorderLayout.SOUTH);
		this.getContentPane().setBackground(new Color(147, 125, 194));

		this.setTitle("Erkut's ToDo App");
		this.setSize(1280, 720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		timer = new Timer(1000, this);
		timer.start();
	}

	private void addTodoBtnOnPanel() {
		try {
			ArrayList<ToDo> arr = dbManager.get();
			for (ToDo todo : arr) {
				JButton button = new JButton(todo.getTitle());
				todoBtnList.add(button);
				button.setFocusable(false);
				button.setForeground(new Color(6, 17, 60));
				button.setBackground(new Color(210, 218, 255));
				button.addActionListener(this);
				button.setPreferredSize(new Dimension(150, 50));
				todoPanel.add(button);
			}
		} catch (SQLException e) {

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		todoPanel.removeAll();
		addTodoBtnOnPanel();
		todoPanel.revalidate();
		this.repaint();
		this.revalidate();
		if (e.getSource() == addBtn) {
			new AddFrame();
		}
		if (e.getSource() == delBtn) {
			if (deleteMode) {
				deleteMode = false;
				delBtn.setForeground(new Color(6, 17, 60));
				delBtn.setBackground(new Color(210, 218, 255));
			} else {
				deleteMode = true;
				delBtn.setForeground(new Color(242, 242, 242));
				delBtn.setBackground(new Color(243, 36, 36));
			}
		}
		for (int i = 0; i < todoBtnList.size(); i++) {
			if (todoBtnList.get(i) == e.getSource()) {
				
				if (deleteMode) {
					try {
						ToDo entity = dbManager.select(todoBtnList.get(i).getText());
						dbManager.delete(entity);
					} catch (SQLException e1) {
					}
				} else {
					try {
						new ShowToDo(dbManager.select(todoBtnList.get(i).getText()));
						System.out.println("asdasdasd");
					} catch (SQLException exception) {
					}
				}
			}
		}
	}
}



