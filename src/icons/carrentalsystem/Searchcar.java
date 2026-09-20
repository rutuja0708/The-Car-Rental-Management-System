
package carrentalsystem;

import java.awt.BorderLayout;
import java.awt.*;

import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Searchcar extends JFrame {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JPanel contentPane;
	private JTextField txt_Type;
	private JTable table;
        Choice c1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Searchcar frame = new Searchcar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close()
	{
		this.dispose();
	}

	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public Searchcar() throws SQLException {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearchForCar = new JLabel("Search For Car");
		lblSearchForCar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearchForCar.setBounds(250, 11, 186, 31);
		contentPane.add(lblSearchForCar);
		
		JLabel lblCarAvailable = new JLabel("Car type:");
		lblCarAvailable.setBounds(50, 73, 96, 14);
		contentPane.add(lblCarAvailable);
		
                JLabel lblcarname = new JLabel("Car Name");
		lblcarname.setBounds(15, 162, 96, 14);
		contentPane.add(lblcarname);
                
		JLabel lblCarType = new JLabel("Car Number");
		lblCarType.setBounds(120, 162, 96, 14);
		contentPane.add(lblCarType);
		
		JLabel lblCarAvailable_1 = new JLabel("Availability");
		lblCarAvailable_1.setBounds(195, 162, 120, 14);
		contentPane.add(lblCarAvailable_1);
		
		JLabel lblPrice_1 = new JLabel("Price");
		lblPrice_1.setBounds(458, 162, 46, 14);
		contentPane.add(lblPrice_1);
                
                JLabel l1 = new JLabel("Car Type");
		l1.setBounds(580, 162, 96, 14);
		contentPane.add(l1);
		
		JCheckBox checkRoom = new JCheckBox("Only display Available");
		checkRoom.setBounds(400, 69, 205, 23);
                checkRoom.setBackground(Color.WHITE);
		contentPane.add(checkRoom);
		
		
                c1 = new Choice();
                c1.add("Five Sitter");
                c1.add("Seven Sitter");
                c1.setBounds(153, 70, 120, 20);
		contentPane.add(c1);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SQL = "select * from car where car_type = '"+c1.getSelectedItem()+"'";
				String SQL2 = "select * from car where availability = 'Available' AND car_type = '"+c1.getSelectedItem()+"'";
			try{			
                                conn c = new conn();
				rs = c.s.executeQuery(SQL);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				if(checkRoom.isSelected())
				{	
					rs = c.s.executeQuery(SQL2);
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				
				
			}catch (SQLException ss)
			{
				ss.printStackTrace();
			}
			
			}
		});
		btnSearch.setBounds(200, 400, 120, 30);
                btnSearch.setBackground(Color.BLACK);
                btnSearch.setForeground(Color.WHITE);
		contentPane.add(btnSearch);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
                                setVisible(false);
			}
		});
		btnExit.setBounds(380, 400, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
		
		table = new JTable();
		table.setBounds(0, 187, 700, 300);
		contentPane.add(table);
		
		JLabel lblCleanStatus = new JLabel("Clean Status");
		lblCleanStatus.setBounds(306, 162, 96, 14);
		contentPane.add(lblCleanStatus);
                
                getContentPane().setBackground(Color.WHITE);
	}
}