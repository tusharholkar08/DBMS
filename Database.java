import java.sql.*;
import java.util.Scanner;

class Database {
    public static void main(String arg[]) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "N#@98wrft45");

        System.out.println("connected to database");
        int count = 1;
        try {
            do {
                System.out.println(
                        "1 for Create table \n 2 for Insert record \n 3 for Delete record \n 4 for Update Record \n 5 for Display \n 6 for Drop Table ");
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter Choice ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        String Query = "create table Employee (ID int(10),Emp_name varchar(20),emp_Address varchar(20))";
                        Statement stmt = con.createStatement();
                        stmt.execute(Query);
                        System.out.println("Table Created");
                        break;
                    case 2:
                        PreparedStatement pstmt = con.prepareStatement("insert into Employee values (?,?,?)");
                        System.out.println("Enter Emp_id:");
                        pstmt.setInt(1, sc.nextInt());
                        System.out.println("Enter Emp_name:");
                        pstmt.setString(2, sc.next());
                        System.out.println("Enter Emp_Address:");
                        pstmt.setString(3, sc.next());
                        pstmt.executeUpdate();
                        System.out.println(" Record has been Inserted");

                        break;

                    case 3:
                        PreparedStatement pstmt1 = con.prepareStatement("Delete from Employee where Id=?");
                        System.out.println("Enter Id to delete record ");
                        pstmt1.setInt(1, sc.nextInt());
                        pstmt1.executeUpdate();
                        System.out.println("Record has been deleted ");
                        break;
                    case 4:
                        PreparedStatement pstmt2 = con.prepareStatement("Update employee set emp_name=? where id =?");
                        System.out.println("Enter Name for updation ");
                        pstmt2.setString(1, sc.next());

                        System.out.println("Enter Id");
                        pstmt2.setInt(2, sc.nextInt());
                        pstmt2.executeUpdate();

                        System.out.println("Record has been updated");

                        break;

                    case 5:

                        String Select_Query = "Select * from employee";
                        Statement stmt1 = con.createStatement();
                        ResultSet rs = stmt1.executeQuery(Select_Query);
                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
                        }
                        break;
                    case 6:

                        String Drop_Query = "drop table employee";
                        Statement stmt2 = con.createStatement();
                        stmt2.execute(Drop_Query);
                        System.out.println("Table has been Droped");

                        break;

                }
                System.out.println("Do you want to continue Press 1 Otherwise press 0");

                count = sc.nextInt();
            } while (count == 1);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
