package lms;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import db.User_Table;

public class Librarian {

	public String L_ID,Name,Email,Phone_No,Password;
	public String Book_Name,Book_Author,Category;
	public int ISBN,No_of_Books;
	Scanner scan=new Scanner(System.in);
	
	public void Register() throws SQLException {


		System.out.print("\nLIBRARIAN REGISTRATION\n\n");
		System.out.print("Enter Librarian_ID: ");
		L_ID=scan.next();
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
		
		java.sql.PreparedStatement ps = conn.prepareStatement("INSERT INTO Librarian VALUES (?,?,?,?,?)");
		

		ps.setString(1,L_ID);
		ps.setString(2,Name);
		ps.setString(3,Email);
		ps.setString(4,Phone_No);
		ps.setString(5,Password);
		ps.executeUpdate();
		
		System.out.println("....Registration Success....");	
	}
	
	public void Login() throws SQLException {
		
		System.out.print("Enter Email: ");
		Email=scan.next();
		System.out.print("Enter Password: ");
		Password=scan.next();
		
		int flag=0;
		
		User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
		Statement stmt = (Statement) conn.createStatement();
		String sql2 = "SELECT Email, Password FROM Librarian where Email=Email and Password=Password";
		ResultSet rs = stmt.executeQuery(sql2);
		
		boolean bool=true;
		while(rs.next())
		{
			String first = rs.getString("Email");
			String last = rs.getString("Password");
        
			if(Email.equals(first) && Password.equals(last))
			{
				Librarian L=new Librarian();
				System.out.println("\n...........................LOGIN SUCCESSFULL.............................");
				
				while(bool)
				{
					System.out.println("1.Search  2.Add_Book  3.Delete_Book  4.Update_Book  5.Delete_User  6.Reports  7.Back");
					int choice=scan.nextInt();
				
					switch(choice)
					{
					case 1:
						L.Search();
						bool=true;
						break;
					case 2:
						L.Add_Book();
						bool=true;
						break;
					case 3:
						L.Delete_Book();
						bool=true;
						break;
					case 4:
						L.Update_Book();
						bool=true;
						break;
					case 5:
						L.Delete_User();
						bool=true;
						break;
                                        case 6:
						L.Reports();
						bool=true;
						break;
					case 7:
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
			System.out.println("\nInvalid Email or Password. Please try again...!!");
			Login();
		}
	}

	public void Search() throws SQLException {

		System.out.print("Enter Book Name: ");
		String Book_Name = scan.nextLine();
		
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

	public void Add_Book() throws SQLException {
		
		System.out.print("Enter ISBN No: ");
		ISBN=Integer.valueOf(scan.nextLine());
		System.out.print("Enter Book Name: ");
		Book_Name=scan.nextLine();
		System.out.print("Enter Book_Author: ");
		Book_Author=scan.nextLine();
		System.out.print("Enter Book_Category: ");
		Category=scan.nextLine();
		System.out.print("Enter No_of_Books: ");
		No_of_Books=Integer.valueOf(scan.nextLine());
		
		User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
		
		java.sql.PreparedStatement ps = conn.prepareStatement("INSERT INTO Books VALUES (?,?,?,?,?)");
		

		ps.setInt(1,ISBN);
		ps.setString(2,Book_Name);
		ps.setString(3,Book_Author);
		ps.setString(4,Category);
		ps.setInt(5,No_of_Books);
		ps.executeUpdate();
		
		System.out.println("....Book added Successfully....");	
		
	}

	public void Delete_Book() throws SQLException {
		
		System.out.print("Enter Book_Name: ");
		String Book_Name = scan.nextLine();
		
		User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
				
		PreparedStatement statement = (PreparedStatement) conn.prepareStatement("delete from Books where Book_Name=?");    
		statement.setString(1, Book_Name);
		int resultSet = statement.executeUpdate();
		
		if(resultSet==1)
			System.out.println("\n.....Book Deleted Successfully.....\n");
		else
			System.out.println("Book not Found......!!");
		
	}

	public void Update_Book() throws SQLException {
            
                System.out.print("Enter Book_Name: ");
		String Book_Name = scan.nextLine();
                System.out.print("Enter No of Books: ");
		int No_of_Books = scan.nextInt();
		
		User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
				
		PreparedStatement statement = (PreparedStatement) conn.prepareStatement("update Books set No_of_Books=? where Book_Name=?");
		statement.setString(2, Book_Name);
                statement.setInt(1, No_of_Books);
		int resultSet = statement.executeUpdate();
		
		if(resultSet==1)
			System.out.println("\n.....Book updated Successfully.....\n");
		else
			System.out.println("Book not Found......!!");
	}

	public void Delete_User() throws SQLException {
		
		System.out.print("Enter User Roll_No: ");
		String Roll_No = scan.nextLine();
		
		User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
				
		PreparedStatement statement = (PreparedStatement) conn.prepareStatement("delete from User where Roll_No=?");    
		statement.setString(1, Roll_No);
		int resultSet = statement.executeUpdate();
		
		if(resultSet==1)
			System.out.println("\n\n.....User Deleted Successfully.....\n");
		else
			System.out.println("User not Found......!!");	
	}

        public void Reports() throws SQLException {
            
                User_Table C=new User_Table();
		java.sql.Connection conn = C.getConnection();
				
		PreparedStatement statement = (PreparedStatement) conn.prepareStatement("select sum(No_of_Books) from Books");    
		ResultSet resultSet = statement.executeQuery();
                
                PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("select count(*) from User");    
		ResultSet res = stmt.executeQuery();
		
		System.out.println("\n\n................Reports..............\n");
		while(resultSet.next())
		{
                    System.out.println("Total Number of Books in the Library: "+resultSet.getInt("sum(No_of_Books)"));
		}
                while(res.next())
		{
                    System.out.println("Total Number of Users in the Library: "+res.getInt("count(*)")+"\n");   
		}
    }
}