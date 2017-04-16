package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import object.Roommate;

public class PayBillActivity extends JFrame implements ItemListener{
	private CardLayout layout;
	private JPanel Cards;
	private Roommate roommate = Roommate.getInstance();
	private int billID[];
	private int rowCount=0;
	private int selectedBill;
	private JComboBox cbBillList;
	private String billTitle[];
	private String billDate[];
	private float billAmount[];
	private JLabel lblBillName,lblTotalBillAmount,lblDate,lblDisplayBillName,lblDisplayTotalBillAmount,lblDisplayDate;
	private JPanel PayBill;
	private Button btnPayment;
	
	public PayBillActivity(JPanel newCards, CardLayout newLayout){
		Cards = newCards;
		layout = newLayout;
	}
	

@SuppressWarnings("resource")
public void startActivity(){
	
	PayBill = new JPanel();
	Cards.add(PayBill, "pay");
	PayBill.setLayout(null);
	
	PreparedStatement prepared = null;


    
	
	lblBillName = new JLabel("Bill Name:");
	lblBillName.setBounds(5, 21, 112, 26);
	PayBill.add(lblBillName);
	
	lblTotalBillAmount = new JLabel("Total Bill Amount:");
	lblTotalBillAmount.setBounds(5, 64, 176, 26);
	PayBill.add(lblTotalBillAmount);
	
	lblDate = new JLabel("Date:");
	lblDate.setBounds(5, 111, 233, 26);
	PayBill.add(lblDate);
	
	lblDisplayBillName = new JLabel("_____");
	lblDisplayBillName.setBounds(126, 21, 112, 26);
	PayBill.add(lblDisplayBillName);
	
	lblDisplayTotalBillAmount = new JLabel("0.0");
	lblDisplayTotalBillAmount.setBounds(126, 64, 176, 26);
	PayBill.add(lblDisplayTotalBillAmount);
	
	lblDisplayDate = new JLabel("____-__-__");
	lblDisplayDate.setBounds(126, 111, 233, 26);
	PayBill.add(lblDisplayDate);
	
	btnPayment = new Button("Pay Bill");
	btnPayment.setBounds(10, 200, 50, 26);
	PayBill.add(btnPayment);
	//btnPayment.setVisible(false);
	
	String conn = "jdbc:sqlserver://cit337.database.windows.net:1433;" +
			"database=CSE337;user=afdanaj@cit337;password=Temp12345;"+
			"encrypt=true;trustServerCertificate=false;" +
			"hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
   layout.show(Cards, "view");
   try {
		Connection connection = DriverManager.getConnection(conn);
		Connection connection2 = DriverManager.getConnection(conn);
		Connection connection3 = DriverManager.getConnection(conn);
		String query="SELECT * FROM Bills WHERE BILL_OWNER_ID = ?";
		 prepared = connection.prepareStatement(query);
		 prepared.setInt(1, roommate.getGID());
	     ResultSet rs=prepared.executeQuery();
	     
		PreparedStatement prepared2 = null;
		 prepared2 = connection2.prepareStatement(query);
		 prepared2.setInt(1, roommate.getGID());
	     ResultSet rsc=prepared2.executeQuery();
	     while(rsc.next()) {
	    	 rowCount++;
	    	}
	     connection2.close();
	     billID=new int[rowCount];
	     billTitle=new String[rowCount];
	     billDate=new String[rowCount];
	     billAmount=new float[rowCount];
	     int count=0;
		while(rs.next())
	   {   
			billID[count]=rs.getInt("BILL_ID");
			billTitle[count]=rs.getString("BILL_NAME");
			billDate[count]=rs.getString("BILL_DUE_DATE");
			billAmount[count]=rs.getFloat("BILL_AMOUNT");
			count++;
	   }
		connection.close();
		
		cbBillList = new JComboBox(billTitle);
		cbBillList.setBounds(10, 300, 233, 26);
		PayBill.add(cbBillList);
		cbBillList.setSelectedIndex(0);
		cbBillList.addItemListener(this);
		
	    lblDisplayBillName.setText(billTitle[0]);
	    lblDisplayTotalBillAmount.setText(Float.toString(billAmount[0]));
	    lblDisplayDate.setText(billDate[0]);
	    selectedBill=0;
	    
		 btnPayment.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String queryPay="INSERT INTO Payments (PAYMENT_BILL_ID, PAYMENT_USER_ID, PAYMENT_PAID)" +
								"VALUES (?, ?, ?)";
							PreparedStatement prepared3 = null;
							 prepared3 = connection3.prepareStatement(queryPay);
						prepared3.setInt(1, billID[selectedBill]);
						prepared3.setInt(2, roommate.getID());
						prepared3.setFloat(3, billAmount[selectedBill]);
					    prepared3.execute();
						JOptionPane.showMessageDialog(null, "Your bill was successfully payed!");
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
			});

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();

}
		
	layout.show(Cards, "pay");
}
public void actionPerformed(ActionEvent e) {
   // JComboBox cbBillList = (JComboBox)e.getSource();

    
}


@Override
public void itemStateChanged(ItemEvent arg0) {
	// TODO Auto-generated method stub
    selectedBill = cbBillList.getSelectedIndex();
    //JOptionPane.showMessageDialog(null, billTitle[selectedBill]);
    lblDisplayBillName.setText(billTitle[selectedBill]);
    lblDisplayTotalBillAmount.setText(Float.toString(billAmount[selectedBill]));
    lblDisplayDate.setText(billDate[selectedBill]);
    btnPayment.setVisible(true);
}
}
