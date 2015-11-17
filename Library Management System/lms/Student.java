package lms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import db.User_Table;

public class Student {

	public String Roll_No,Name,Email,Phone_No,Password,Book_Name,Borrow_Date,Return_Date;
	Scanner scan=new Scanner(System.in);
	
	public void Register() throws SQLException {
		
		System.out.print("\nNEW USER REGISTRATION\n\n");
		
		System.out.print("Enter Roll_No: ");
		Roll_No=scan.next();
		System.out.print("Enter Name: ");
		Name=scan.next();
		System.out.print("Enter Email: ");
		Email=scan.next();
		System.out.print("Enter Phone_No: ");
		Phone_No=scan.next();
		System.out.print("Enter Password: ");
		Password=scan.next();
		
		User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
		
		java.sql.PreparedStatement ps = conn.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?)");
		

		ps.setString(1,Roll_No);
		ps.setString(2,Name);
		ps.setString(3,Email);
		ps.setString(4,Phone_No);
		ps.setString(5,Password);
		ps.executeUpdate();
		
		System.out.println("....Registration Success....");
	}

	public void Login() throws SQLException {

		System.out.print("Enter Roll_No: ");
		Roll_No=scan.next();
		System.out.print("Enter Password: ");
		Password=scan.next();
		
		int flag=0,choice;
		
		User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
		Statement stmt = (Statement) conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT Roll_No,Password FROM User where Roll_No=Roll_No and Password=Password");
		
		boolean bool=true;
		while(rs.next())
		{
			String first = rs.getString("Roll_No");
			String last = rs.getString("Password");
        
			if(Roll_No.equals(first) && Password.equals(last))
			{
				Student U=new Student();
				System.out.println("\n...................LOGIN SUCCESSFULL..................");
				
				while(bool)
				{
					System.out.println("\n1.Search\t2.Borrow\t3.Return\t4.Back");
					choice=scan.nextInt();
				
					switch(choice)
					{
					case 1:
						U.Search();
						bool=true;
						break;
					case 2:
						U.Borrow();
						bool=true;
						break;
					case 3:
						U.Return();
						bool=true;
						break;
					case 4:
						bool=false;
						break;
					default:
						System.out.println("\nWrong Option........!!");
					}
				}
				flag=1;
				break;
			}
		}
		if(flag==0)
		{
			System.out.println("\nInvalid Roll_No or Password. Please try again...!!");
			Login();
		}
		
	}

	public void Search() throws SQLException {
		
		System.out.print("Enter Book Name: ");
		Book_Name=scan.nextLine();
		
		User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
				
		PreparedStatement statement = (PreparedStatement) conn.prepareStatement("SELECT * FROM Books where Book_Name=?");    
		statement.setString(1, Book_Name);
		ResultSet resultSet = statement.executeQuery();
		
		System.out.println("\n\n...........................Search Results..........................\n");
		System.out.println("ISBN\tBook_Name\tBook_Author\tCategory\tNo of Books");
		
		while(resultSet.next())
		{
			System.out.print(resultSet.getString("ISBN")+"\t");
			System.out.print(resultSet.getString("Book_Name")+"\t");
			System.out.print(resultSet.getString("Book_Author")+"\t");
			System.out.print(resultSet.getString("Category")+"\t");
			System.out.print(resultSet.getString("No_of_Books")+"\n");
		}
	}

	public void Borrow() throws SQLException {
		
		User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
                
                System.out.print("Enter Roll_No: ");
		Roll_No=scan.nextLine();
		System.out.print("Enter Book Name: ");
		Book_Name=scan.nextLine();
		System.out.print("Enter Borrow Date: ");
		Borrow_Date=scan.nextLine();
		System.out.print("Enter Return Date: ");
		Return_Date=scan.nextLine();
		
                PreparedStatement statement = (PreparedStatement) conn.prepareStatement("UPDATE Books SET No_of_Books = No_of_Books - 1 WHERE Book_Name=?");
		statement.setString(1, Book_Name);
		int resultSet = statement.executeUpdate();
                
		if(resultSet==1)
                {
                    java.sql.PreparedStatement ps = conn.prepareStatement("INSERT INTO Transaction VALUES (?,?,?,?)");
                    ps.setString(1,Roll_No);
                    ps.setString(2,Book_Name);
                    ps.setString(3,Borrow_Date);
                    ps.setString(4,Return_Date);
                    ps.executeUpdate();
                    System.out.println("\n.....Book Borrowed Successfully.....\n");
                }
		else
                    System.out.println("Book not Found......!!");
	}
	
	public void Return() throws SQLException {

		System.out.print("Enter Book Name: ");
		Book_Name=scan.nextLine();
		
		User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
		
		PreparedStatement statement = (PreparedStatement) conn.prepareStatement("UPDATE Books SET No_of_Books = No_of_Books + 1 WHERE Book_Name=?");
		statement.setString(1, Book_Name);
		int resultSet = statement.executeUpdate();
		
		if(resultSet==1)
                {
                    PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("delete from Transaction where Book_Name=?");    
                    stmt.setString(1, Book_Name);
                    resultSet = stmt.executeUpdate();
                    System.out.println(".....Book Returned Successfully.....\n");
                }
		else
                    System.out.println("Book not Found......!!");
                
                
	}
}