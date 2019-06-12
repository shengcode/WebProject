package ExcelUpload;

	import java.sql.Statement;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
import java.sql.SQLException;

	public class EmployeeCRUD {
		
		public boolean insertEmployee(Statement st, Employee employee){
                            
                            try {
				System.out.println("Creating new employee. ");
				int rows = st.executeUpdate("INSERT INTO Student_Performance.Employees VALUES ('" + employee.getEmployeeID() + "','" + employee.getEmployeeName() + "','" + employee.getEmployeeEmail() + "','" + employee.getClassID() + "','"+employee.getManagerID()+"')");
				
                                System.out.println(rows + " employee added.");
                                return true;
			} catch (SQLException e) {
				System.out.println("Exception: " +  e.getMessage());
                                return false;
			}
                        
		}
		
		public void readEmployee(Statement st, String employee_id){
			try {
				System.out.println("Reading employee.");
				ResultSet rs = st.executeQuery("SELECT employee_id, name, email, class_id FROM Student_Performance.Employees WHERE employee_id = '" + employee_id + "'");
				while(rs.next())
					System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
			} catch (SQLException e) {
				System.out.println("Exception: " +  e.getMessage());
			}
		}
		
		public void updateEmployee(Statement st, String employee_id, String name, String email){
			try {
				System.out.println("Update employee.");
				int rows = st.executeUpdate("UPDATE Student_Performance.Employees SET name ='" + name + "' ,email ='" + email +
						"'WHERE employee_id ='" + employee_id + "'");
				System.out.println(rows + " employee updated.");
			} catch (SQLException e) {
				System.out.println("Exception: " +  e.getMessage());
			}
		}
		
		public void deleteEmployee(Statement st, String employee_id){
			try {
				System.out.println("Delete employee.");
				int rows = st.executeUpdate("DELETE FROM Student_Performance.Employees WHERE employee_id = '" + employee_id + "'");
				System.out.println(rows + " employee deleted.");
			} catch (SQLException e) {
				System.out.println("Exception: " +  e.getMessage());
			}
		}
                
                public void updateClass(Statement st, Employee employee){
                    try {
                        int rows = st.executeUpdate("UPDATE employees Set class_id = '"+employee.getClassID()+"' where employee_id = '"+employee.getEmployeeID()+"'");
                        System.out.println("Employee Updated");
                    } catch(SQLException e){
                        System.out.println(e.getMessage());
                        
                    }
                    
                }
                

		public static void main(String[] args) {
			EmployeeCRUD ec = new EmployeeCRUD();
			try {
				String empid = "IM505";
				String name = "Icema";
				String email = "IM506@syn.com";
				String classid = "JV123";
				Class.forName("oracle.jdbc.OracleDriver");
                            try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance")) {
                                Statement st = con.createStatement();
                                
                                //ec.insertEmployee(st, empid, name, email, classid);
                                //ec.readEmployee(st, empid);
                                //ec.updateEmployee(st, empid, name, email);
                                //ec.deleteEmployee(st, empid);
                                st.close();
                            }
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Exception " + e.getMessage());
			} 
		}
		
		

	}
