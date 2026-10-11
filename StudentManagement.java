import java.util.Scanner;
import java.sql.*;

public class StudentManagement{
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        String url="jdbc:mysql://localhost:3306/saran_1st";
        String username="root";
        String password = System.getenv("DB_PASSWORD");

        int choice;
        do{
        System.out.println("STUDENT MANAGEMENT");
        System.out.println("1.Insert Operation");
        System.out.println("2.Select Operation");
        System.out.println("3.Update Operation");
        System.out.println("4.Delete Operation");
        System.out.println("5.Exit Operation");

        System.out.print("Enter your choice:");
        choice=scan.nextInt();

        switch (choice) {
            case 1:

                 System.out.println("Insert Students");
                 String sql="insert into students(name,age,department) values(?,?,?)";
                 try(
                    Connection con = DriverManager.getConnection(url,username,password);
                    PreparedStatement ps = con.prepareStatement(sql);
                 ){
                  System.out.print("Enter Student name:");
                   scan.nextLine();
                  String name=scan.nextLine();
                   System.out.print("Enter student age:");
                   int age=scan.nextInt();
                   scan.nextLine();
                   System.out.print("Enter student department:");
                    String department=scan.nextLine();
  
                    ps.setString(1,name);
                    ps.setInt(2, age);
                    ps.setString(3, department);

                    int rows = ps.executeUpdate();
                    if(rows>0){
                         System.out.println("Students successfully inserted");
                    } 
                    
                    }catch(SQLException e){
                        e.printStackTrace();
                }

                break;
            case 2:
                 System.out.println("Select Students");
                 String sqls = "select *from students";
                 try(
                    Connection con = DriverManager.getConnection(url,username,password);
                    Statement sm = con.createStatement();
                    ResultSet rs = sm.executeQuery(sqls);
                 ){
                    while (rs.next()) {
                        
                    
                    String name=rs.getString("name");
                    int age = rs.getInt("age");
                    String department = rs.getString("department");
                    System.out.println();
                    System.out.println("Name:"+name);
                    System.out.println("Age:"+age);
                    System.out.println("Department:"+department);
                    }

                 }catch(SQLException e){
                    e.printStackTrace();
                 }
                 
                 break;
            case 3:
                System.out.println("Update Students");
                String sqlc ="update students set age=? where name=?";
                 try(
                    Connection con = DriverManager.getConnection(url,username,password);
                    PreparedStatement ps = con.prepareStatement(sqlc);
                 ){
                     System.out.println("Enter update student name:");
                     scan.nextLine();
                     String name=scan.nextLine();

                      System.out.println("Enter new age:");
                      int age=scan.nextInt();

                      ps.setInt(1, age);
                      ps.setString(2, name);

                      int rows=ps.executeUpdate();
                      if(rows>0){
                         System.out.println("Student updete successfully");
                      }
                      else{
                         System.out.println("student not found");
                      }
                      
                      }catch(SQLException e){
                        e.printStackTrace();

                       

                 }

                break;
            case 4:
                 System.out.println("Delete Students");
                 String sq="delete from students where name=?";
                  try(
                    Connection con = DriverManager.getConnection(url,username,password);
                    PreparedStatement ps = con.prepareStatement(sq);
                 ){
                    System.out.println("Enter the delete student:");
                     scan.nextLine();
                    String name=scan.nextLine();
                   

                    ps.setString(1, name);

                    int rows=ps.executeUpdate();
                    if(rows>0){
                         System.out.println("students deleted");
                    }
                    else{
                         System.out.println("Students not found");
                    }
                 }catch(SQLException e){
                    e.printStackTrace();
                 }

                 break;
            case 5:
                System.out.println("Exit");
                break;       
            default:
                System.out.println("Invalid choice");
                break;
        }



        }while(choice!=5);
        
    }
}