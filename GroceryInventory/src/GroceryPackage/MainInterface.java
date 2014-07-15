package GroceryPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;

import java.awt.SystemColor;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;



public class MainInterface {

	private JFrame frmGroceryInventoryList;
	//private JTable GroceryTable;
	//private DefaultTableModel model;
	
	Object[][] data = null;
	String[] columnNames = new String[] {"Grocery Item","Price ($)","Expiration Date", "Quantity"};
	private JTable GroceryTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface window = new MainInterface();
					window.frmGroceryInventoryList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		int counter = 0;
		
		frmGroceryInventoryList = new JFrame();
		frmGroceryInventoryList.setTitle("Grocery Inventory List");
		frmGroceryInventoryList.setBounds(100, 100, 550, 300);
		frmGroceryInventoryList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGroceryInventoryList.getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 6, 450, 33);
		frmGroceryInventoryList.getContentPane().add(toolBar);
		
		JButton btnNewButton = new JButton("Add Item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(GroceryTable.getRowCount()==0)
				{
					data = new Object[1][4];
					data[0][0] = JOptionPane.showInputDialog("Enter Item:");
					data[0][1] = JOptionPane.showInputDialog("Enter Price:");
					data[0][2] = JOptionPane.showInputDialog("Enter Expiration Date:");
					data[0][3] = JOptionPane.showInputDialog("Enter Quantity: ");
					GroceryTable.setModel(new DefaultTableModel(data,columnNames));
					
				}else
				{
				
				Object[][] temp = new Object[data.length+1][4];	//create temp object
				//Copy the array to the new temp array
				for(int i=0; i<data.length; i++)
				{
					for(int j=0;j<4;j++)
					{
						temp[i][j] = data[i][j];
					}
				}
				
				temp[data.length][0] = JOptionPane.showInputDialog("Enter Grocery Item");
				temp[data.length][1] = JOptionPane.showInputDialog("Enter Price");
				temp[data.length][2] = JOptionPane.showInputDialog("Enter Expiration Date");
				temp[data.length][3] = JOptionPane.showInputDialog("Enter Quantity");
				data = temp;
				GroceryTable.setModel(new DefaultTableModel(data,columnNames));
			}
			}
		});
		toolBar.add(btnNewButton);
		
		JButton btnEdit = new JButton("Edit");
		toolBar.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel model=(DefaultTableModel) GroceryTable.getModel();
				if(GroceryTable.getSelectedColumn()==-1)
				{
					if(GroceryTable.getRowCount()==0)
					{
						JOptionPane.showMessageDialog(null, "Table is empty");
					}else{
						JOptionPane.showMessageDialog(null, "You must selct a Grocery Item");
					}
					
				}else{
					
					int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete Item", JOptionPane.OK_CANCEL_OPTION);
					//String Item = GroceryTable.getSelectedRow();
					if (answer == JOptionPane.OK_OPTION)
					{
					JOptionPane.showMessageDialog(null, "Item Deleted!");
					model.removeRow(GroceryTable.getSelectedRow());
					}
					else{
						JOptionPane.showMessageDialog(null, "Item Not Deleted.");
					}
	
				}
				
			}
		});
		toolBar.add(btnDelete);
		
		JButton btnNewButton_1 = new JButton("Refresh");
		toolBar.add(btnNewButton_1);
		
		JButton btnGroceryList = new JButton("Grocery List");
		toolBar.add(btnGroceryList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 71, 538, 179);
		frmGroceryInventoryList.getContentPane().add(scrollPane);
		
		GroceryTable = new JTable();
		scrollPane.setViewportView(GroceryTable);
		
		JLabel lblNewLabel = new JLabel("Grocery Inventory List ");
		lblNewLabel.setBounds(205, 51, 142, 16);
		frmGroceryInventoryList.getContentPane().add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		frmGroceryInventoryList.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGroceryInventoryList.setVisible(false);
			}
		});
		
		JMenuItem mntmReadMe = new JMenuItem("Read Me");
		mntmReadMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Create a new Grocery Inventory and save to Excel Spreadsheet/nor import an existing inventory.");
			}
		});
		mnFile.add(mntmReadMe);
		mntmExit.setIcon(new ImageIcon(MainInterface.class.getResource("/Resources/cancel.jpeg")));
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmImportFromExcel = new JMenuItem("Import From Excel");
		mntmImportFromExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTable newtable = new JTable();
				String excelFileName = "Test1.xls";
				File file = new File(excelFileName);
				JProgressBar progressBar = new JProgressBar();
				JTableReadTableModelTask task = new JTableReadTableModelTask(file, null, progressBar, jTable);
			}
		});
		mnEdit.add(mntmImportFromExcel);
		
		JMenuItem mntmExportToExcel = new JMenuItem("Export To Excel");
		mntmExportToExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String FileName = JOptionPane.showInputDialog("Enter File Name:");
				
				try {
					exportTable(GroceryTable, new File("/Users/jonathanlaroco/Desktop/JavaExcelExamples/"+FileName+".xls"));
							//ExcelTest.xls"));
					JOptionPane.showMessageDialog(null, FileName + " was successfully exported to Excel!");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnEdit.add(mntmExportToExcel);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAboutUs = new JMenuItem("About Us");
		mntmAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Developer: Jonathan laroco");
			}
		});
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Use the following formats(below) to enter data into the table \n(Enter Grocery Item)\nPrice Format:(0.55 or 7.56)\nDate Format:(04/17/1991)\nQuantity: (Enter Number)");;
			}
		});
		mnAbout.add(mntmHelp);
		mntmAboutUs.setIcon(new ImageIcon(MainInterface.class.getResource("/Resources/information_16.png")));
		mnAbout.add(mntmAboutUs);
		
	}
	
	public void exportTable(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        FileWriter out = new FileWriter(file);
        for(int i=0; i < model.getColumnCount(); i++) {
    out.write(model.getColumnName(i) + "\t");
        }
        out.write("\n");

        for(int i=0; i< model.getRowCount(); i++) {
    for(int j=0; j < model.getColumnCount(); j++) {
        out.write(model.getValueAt(i,j).toString()+"\t");
        }
        out.write("\n");
    }

    out.close();
    System.out.println("write out to: " + file);
}

}

