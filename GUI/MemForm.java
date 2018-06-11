package GUI;
import java.time.LocalDateTime;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import javax.swing.Box;

import DAO.*;
import Ctrl.*;
import Actor.User;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MemForm extends JFrame {
	
	/**
	 * 
	 */
	private User user;
	private static final long serialVersionUID = -3036858692602713273L;
	private static int MaximumPageNum = 99;
	private Box vbox;
	private JPanel JPanelNorth_first_row, JPanelNorth_second_row, JPanelSouth, JPanelCentre;
	private JButton AddStudent, DeleteStudent, QueryStudent, EditInfo;
	private JButton NextPage, PrePage, FirstPage, LastPage;
	private JLabel time_label, curr_page_label, username_label;
	public static JTable information_table;
	private LocalDateTime localdatetime;
	// private DefaultTableModel MyTableModel;
	private JScrollPane scroll_pane;
	public static int CurrPageNum = 1;
	public static String[] column = {"student_number", "name", "gender", "academy", "major", "native_place", "phone_number", "email"};
	public static String[][] result;
	public MemForm() {
		init();
	}
	public MemForm(String username) {
		user = AuthenticalCtrl.get_ability_user(username);
		init();
	}
	private void init() {
		setTitle("Student Information System");
		localdatetime = LocalDateTime.now();
		username_label = new JLabel("Welcome! User: " + user.Get_Username());
		time_label = new JLabel("Login Time: " + localdatetime.toString());
		curr_page_label = new JLabel("1" + "th Page");
		curr_page_label.setHorizontalAlignment(JLabel.CENTER);
		/* North Part of MemForm:
		 *     1. four Buttons, refers to four usercase
		 *     2. time_label
		 */
		/*  Action of AddStudent button
		 *  Create an AddStudentCtrl Class, and present an AddStuent UI.
		 */
		AddStudent = new JButton("Add Student");
		AddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/* 1. Check Authentication,
				 * 2. Create AddStudentCtrl Class,
				 * 3. present an AddStudent UI.
				 * 4. Update MemForm
				 */
				new AddStudentCtrl();
				// Update JTable Information
			}
		});
		DeleteStudent = new JButton("Delete Student");
		DeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/* 1. Check Authentication,
				 * 2. Create DeleteStudentCtrl class,
				 * 3. present an Delete Student UI.
				 * 4. Update MemForm
				 */
				new DeleteStudentCtrl();
			}
		});
		QueryStudent = new JButton("Query Student");
		QueryStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/* 1. Check Authentication
				 * 2. Create QueryStudentCtrl class,
				 * 3. present an Query Student UI.
				 * 4. Update MemForm
				 */
				new QueryStudentCtrl();
			}
		});
		EditInfo = new JButton("Edit");
		EditInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/* 1. Check Authentication,
				 * 2. Create EditStudentCtrl class,
				 * 3. present an Edit Info UI.
				 * 4. Update MemForm
				 */
				new EditInfoCtrl();
			}
		});
		vbox = Box.createVerticalBox();
		JPanelNorth_first_row = new JPanel();
		JPanelNorth_first_row.add(username_label);
		JPanelNorth_first_row.add(time_label);
		JPanelNorth_second_row = new JPanel();
		JPanelNorth_second_row.add(AddStudent);
		JPanelNorth_second_row.add(DeleteStudent);
		JPanelNorth_second_row.add(QueryStudent);
		JPanelNorth_second_row.add(EditInfo);
		vbox.add(JPanelNorth_first_row);
		vbox.add(JPanelNorth_second_row);
		/* JPanelCentre showing some items of Student Information data in database.
		 * JPanelNorth showing Current Time and log in time. 
		 */
		// Center JPanel
		JPanelCentre = new JPanel();
		JPanelCentre.setLayout(new GridLayout(1, 1));
		// Use StudentDAO to get student information data list from database.
		result = ((StudentDAO) BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
		// MyTableModel = new DefaultTableModel(result, column);
		information_table = new JTable();
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(JLabel.CENTER);
		information_table.setDefaultRenderer(Object.class, cr);
		/* initJTabel(), not written yet
		 * Put the result into JTable
		 */
		initJTable(information_table, result);
		scroll_pane = new JScrollPane(information_table);
		JPanelCentre.add(scroll_pane);
		
		// South JPanel
		JPanelSouth = new JPanel();
		JPanelSouth.setLayout(new GridLayout(1, 5));
		FirstPage = new JButton("First Page");
		FirstPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CurrPageNum = 1;
				result = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
				initJTable(information_table, result);
				curr_page_label.setText(CurrPageNum + "th Page");
			}
		});
		PrePage = new JButton("Pre Page");
		PrePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (CurrPageNum > 1) CurrPageNum = CurrPageNum - 1;
				result = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
				initJTable(information_table, result);
				curr_page_label.setText(CurrPageNum + "th Page");
			}
		});
		NextPage = new JButton("Next Page");
		NextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (CurrPageNum < MaximumPageNum) {
					CurrPageNum = CurrPageNum + 1;
				}
				result = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
				initJTable(information_table, result);
				curr_page_label.setText(CurrPageNum + "th Page");
			}
		});
		LastPage = new JButton("Last Page");
		LastPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CurrPageNum = MaximumPageNum;
				result = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
				initJTable(information_table, result);
				curr_page_label.setText(CurrPageNum + "th Page");
			}
		});
		JPanelSouth.add(FirstPage);
		JPanelSouth.add(PrePage);
		JPanelSouth.add(curr_page_label);
		JPanelSouth.add(NextPage);
		JPanelSouth.add(LastPage);
		this.add(vbox, BorderLayout.NORTH);
		this.add(JPanelCentre, BorderLayout.CENTER);
		this.add(JPanelSouth, BorderLayout.SOUTH);
		this.setBounds(400, 200, 1090, 400);
		this.setResizable(false);
		this.setVisible(true);
		/*
		 * haven't set DefaultClose yet,
		 * Once the system is closed,
		 * the login state of the user should be canceled.
		 */
	}
	public static void initJTable(JTable _table, String[][] _content) {
		((DefaultTableModel)_table.getModel()).setDataVector(_content, column);
		_table.setRowHeight(20);
		// student_number column
		TableColumn firstColumn = _table.getColumnModel().getColumn(0);
		firstColumn.setPreferredWidth(120);
		firstColumn.setMaxWidth(120);
		firstColumn.setMinWidth(120);
		// name column
		TableColumn secondColumn = _table.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(120);
		secondColumn.setMaxWidth(120);
		secondColumn.setMinWidth(120);
		// gender column
		TableColumn thirdColumn = _table.getColumnModel().getColumn(2);
		thirdColumn.setPreferredWidth(60);
		thirdColumn.setMaxWidth(60);
		thirdColumn.setMinWidth(60);
		// academy column
		TableColumn forthColumn = _table.getColumnModel().getColumn(3);
		forthColumn.setPreferredWidth(200);
		forthColumn.setMaxWidth(200);
		forthColumn.setMinWidth(200);
		// major column
		TableColumn fifthColumn = _table.getColumnModel().getColumn(4);
		fifthColumn.setPreferredWidth(150);
		fifthColumn.setMaxWidth(150);
		fifthColumn.setMinWidth(150);
		// native_place column
		TableColumn sixthColumn = _table.getColumnModel().getColumn(5);
		sixthColumn.setPreferredWidth(120);
		sixthColumn.setMaxWidth(120);
		sixthColumn.setMinWidth(120);
		// phone_number column
		TableColumn seventhColumn = _table.getColumnModel().getColumn(6);
		seventhColumn.setPreferredWidth(120);
		seventhColumn.setMaxWidth(120);
		seventhColumn.setMinWidth(120);
		// email column
		TableColumn eighthColumn = _table.getColumnModel().getColumn(7);
		eighthColumn.setPreferredWidth(200);
		eighthColumn.setMaxWidth(200);
		eighthColumn.setMinWidth(200);
	}
	public static void Update() {
		CurrPageNum = 1;
		result = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
		initJTable(information_table, result);
	}
	public static void main(String[] args) {
		new MemForm();
	}
}
