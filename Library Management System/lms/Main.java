package lms;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("\nWelcome to Library Management System\n");
		System.out.println("	1. User");
		System.out.println("	2. Librarian");
		System.out.println("	3. Exit");
		System.out.print("\nChoose your Option: ");
		
		@SuppressWarnings("resource")
		Scanner scan=new Scanner(System.in);
		int option=scan.nextInt();
		boolean bool=true;
		
		switch(option)
		{
		case 1:
			while(bool)
			{
				System.out.println("\n.....................WELCOME to USER..................");
				System.out.println("1.Register\t2.Login\t\t3.Main Menu\t4.Exit");
				int choice=scan.nextInt();
				
				Student U=new Student();
				switch(choice)
				{
				case 1:
					U.Register();
					bool=true;
					break;
				case 2:
					U.Login();
					bool=true;
					break;
				case 3:
					bool=false;
					main(args);
					break;
				case 4:
					bool=false;
					System.out.println("\nYou have Successfully Exitted......!!");
					break;
				default:
					System.out.println("\nWrong Option........!!");
				}
			}
			break;
		case 2:
			while(bool)
			{
				System.out.println("\n..................WELCOME to LIBRARY..................");
				System.out.println("1.Register\t2.Login\t\t3.Main Menu\t4.Exit");
				int choice=scan.nextInt();
				
				Librarian L=new Librarian();
				switch(choice)
				{
				case 1:
					L.Register();
					bool=true;
					break;
				case 2:
					L.Login();
					bool=true;
					break;
				case 3:
					bool=false;
					main(args);
					break;
				case 4:
					bool=false;
					System.out.println("\nYou have Successfully Exitted......!!");
					break;
				default:
					System.out.println("\nWrong Option........!!");
				}
			}
			break;
		case 3:
			System.out.println("\nYou have Successfully Exitted......!!");
			break;
		default:
			System.out.println("\nWrong Option........!!");
		}
	}

}