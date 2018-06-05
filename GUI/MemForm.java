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

import javax.swing.JButton;
import javax.swing.JLabel;

public class MemForm extends JFrame {
	private static final long serialVersionUID = 3L;
	private static int MaximumPageNum = 99;
	private Box vbox;
	private JPanel JPanelNorth_first_row, JPanelNorth_second_row, JPanelSouth, JPanelCentre;
	private JButton AddStudent, DeleteStudent, QueryStudent, EditInfo;
	private JButton NextPage, PrePage, FirstPage, LastPage;
	private JLabel time_label, curr_page_label;
	private JTable information_table;
	private LocalDateTime localdatetime;
	private DefaultTableModel MyTableModel;
	private JScrollPane scroll_pane;
	public static int CurrPageNum = 1;
	public static String[] column = {"student_number", "name", "gender", "academy", "major", "native_place", "phone_number", "email"};
	public MemForm() {
		init();
	}
	private void init() {
		setTitle("Student Information System");
		localdatetime = LocalDateTime.now();
		time_label = new JLabel(localdatetime.toString());
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
				dispose();
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
			}
		});
		vbox = Box.createVerticalBox();
		JPanelNorth_first_row = new JPanel();
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
		String[][] result = ((StudentDAO) BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
		MyTableModel = new DefaultTableModel(result, column);
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
				String[][] result = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
				initJTable(information_table, result);
				curr_page_label.setText(CurrPageNum + "th Page");
			}
		});
		PrePage = new JButton("Pre Page");
		PrePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (CurrPageNum > 1) CurrPageNum = CurrPageNum - 1;
				String[][] result = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
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
				String[][] result = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
				initJTable(information_table, result);
				curr_page_label.setText(CurrPageNum + "th Page");
			}
		});
		LastPage = new JButton("Last Page");
		LastPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				CurrPageNum = MaximumPageNum;
				String[][] result = ((StudentDAO)BaseDAO.get_ability_DAO(DAO.StudentDAO)).list(CurrPageNum);
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
		firstColumn.setPreferredWidth(60);
		firstColumn.setMaxWidth(60);
		firstColumn.setMinWidth(60);
		// name column
		TableColumn secondColumn = _table.getColumnModel().getColumn(1);
		secondColumn.setPreferredWidth(60);
		secondColumn.setMaxWidth(60);
		secondColumn.setMinWidth(60);
		// gender column
		TableColumn thirdColumn = _table.getColumnModel().getColumn(2);
		thirdColumn.setPreferredWidth(30);
		thirdColumn.setMaxWidth(30);
		thirdColumn.setMinWidth(30);
		// academy column
		TableColumn forthColumn = _table.getColumnModel().getColumn(3);
		forthColumn.setPreferredWidth(90);
		forthColumn.setMaxWidth(90);
		forthColumn.setMinWidth(90);
		// phone_number column
		TableColumn fifthColumn = _table.getColumnModel().getColumn(6);
		fifthColumn.setPreferredWidth(60);
		fifthColumn.setMaxWidth(60);
		fifthColumn.setMinWidth(60);
		// email column
		TableColumn sixthColumn = _table.getColumnModel().getColumn(7);
		sixthColumn.setPreferredWidth(90);
		sixthColumn.setMaxWidth(90);
		sixthColumn.setMinWidth(90);
	}
	public static void main(String[] args) {
		new MemForm();
	}
}
