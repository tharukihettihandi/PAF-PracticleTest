package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//import com.mysql.cj.xdevapi.Statement;

public class HospitalReg {

	// A common method to connect to the DB

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, user name, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaresystem", "root", "");

			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readHos() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Hospital Registration Number</th><th>Hospital Name</th>"
				 + "<th>Hos Address </th><th>Hos Phone</th><th>Hos Email </th>"
				 + "<th>Hos Departments </th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from hospitamanagement";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String hosID = Integer.toString(rs.getInt("hosID"));
				String hosRegNo = rs.getString("hosRegNo");
				String hosName = rs.getString("hosName");
				String hosAddress = rs.getString("hosAddress");
				String hosPhone = rs.getString("hosPhone");
				String hosEmail = rs.getString("hosEmail");
				String Departments = rs.getString("Departments");

				// Add into the html table
			    output += "<tr><td><input id='hidhosIDUpdate' name='hidhosIDUpdate'"
			    		+ " type='hidden' value='" + hosID +"'>" + hosRegNo + "</td>";
				//output += "<td>" + hosRegNo + "</td>";
				output += "<td>" + hosName + "</td>";
				output += "<td>" + hosAddress + "</td>";
				output += "<td>" + hosPhone + "</td>";
				output += "<td>" + hosEmail + "</td>";
				output += "<td>" + Departments + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button'  value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button'  value='Remove' class='btnRemove btn btn-danger' data-Hosid='"
						+ hosID + "'>" + "</td></tr>";
			}
			con.close();
			
			// Complete the html table
			
			output += "</table>";
		} 
		catch (Exception e) 
		{
			output = "Error while reading Hospitals.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertHos(String regno, String name, String address, String phone, String email, String Departments) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into hospitamanagement (`hosID`,`hosRegNo`,`hosName`,`hosAddress`,`hosPhone`,`hosEmail`,`Departments`)"
					+ " values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, regno);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, Departments);

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			// output = "Inserted successfully";
			
			String newHospital = readHos();
			output = "{\"status\":\"success\", \"data\": \""+ newHospital +"\"}";
		} 
		catch (Exception e) 
		{
			// output = "Error while Adding Hospital.";
			
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Hospital.\"}";
			
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateHos(String hosID, String hosRegNo, String hName, String haddress, String hphone, String hemail,
			String Departments) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE hospitamanagement SET hosRegNo=?,hosName=?,hosAddress=?,hosPhone=?,hosEmail=?,Departments=?WHERE hosID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, hosRegNo);
			preparedStmt.setString(2, hName);
			preparedStmt.setString(3, haddress);
			preparedStmt.setString(4, hphone);
			preparedStmt.setString(5, hemail);
			preparedStmt.setString(6, Departments);
			preparedStmt.setInt(7, Integer.parseInt(hosID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			// output = "Updated successfully";

			String newHospital = readHos();
			output = "{\"status\":\"success\",\"data\": \"" + newHospital + "\"}";

		} 
		catch (Exception e)
		{
			// output = "Error while updating the Hospital.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the Hospital.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHos(String hosID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			
			// create a prepared statement
			String query = "delete from hospitamanagement where hosID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(hosID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			// output = "Deleted successfully";

			String newHospital = readHos();
			output = "{\"status\":\"success\",\"data\": \"" + newHospital + "\"}";
		} 
		catch (Exception e) 
		{
			
			// output = "Error while deleting the Hospital.";
			
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the Hospital.\"}";
			
			//System.err.println(e.getMessage());
		}
		return output;
	}
}
