

import java.sql.*;
import java.util.Scanner;

/**
Main class
*/

public class Main {
	
	public static void main (String[] args) {

		
		Scanner sc = new Scanner(System.in);
		int counter = 0;
		
  
		String questionno = null;
		String question = null;
		String answer = null;
		
		 int[] qn = new int [3];
		 String dbAnswer []= new String[3] ;
		 String userAnswer [] = new String [3];
		 String ques[] = new String[3];
		

		try {
				DBConnection db = new DBConnection();
				db.createTable();
				//db.insert();
				//db.retrive();
				
				Statement stmt = db.getConnected().createStatement();
				ResultSet rs = stmt.executeQuery("select * from examination");


				int i = 0;
				while (rs.next()) {
					questionno = rs.getString("questionno");
					qn[i] =  Integer.parseInt(questionno);
							
					question = rs.getString("question");
					ques[i] = question;
					
					answer = rs.getString("answer");
					dbAnswer[i] =  answer;
					
					System.out.println(question + " ?");
					String userAns = sc.next();
					userAnswer[i] = userAns;
					
					if(userAns.equalsIgnoreCase(answer)) {
						counter++;	
					}
					i++;
				}

				
		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			System.out.println("You got "+ counter + " correct");
		
		}

		System.out.println("\nReview following quesiton ");
		for(int j= 0; j<3; j++) {
			if(!dbAnswer[j].equalsIgnoreCase(userAnswer[j])) {
				
			  System.out.println(qn[j] +". "+ ques[j] +" ?");
			  System.out.println("Your answer: "+ userAnswer[j]);
			  System.out.println("Correct answer: " + dbAnswer[j]);
			  }
		}

 }
}
	
	
