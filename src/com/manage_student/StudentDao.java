package com.manage_student;

import java.sql.*;

public class StudentDao {

    public static boolean insertStudentToDb(Student st) {

        boolean f = false;

        try {

            // JDBC code...
            Connection con = Connection_provider.create_connection();
            String q = "insert into students(sname, sphone, scity) value(?,?,?)";
            // Prepared Statement...
            PreparedStatement pstmt = con.prepareStatement(q);
            // Set the values of parameters....
            pstmt.setString(1, st.getStudentName());
            pstmt.setString(2, st.getStudentPhone());
            pstmt.setString(3, st.getStudentCity());

            // Execute...
            pstmt.executeUpdate();

            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;

    }

    public static boolean deleteStudent(int userId) {
        boolean flag = false;

        try {

            // JDBC code...
            Connection con = Connection_provider.create_connection();
            String q = "delete from students where id = ?";

            // Prepared Statement...
            PreparedStatement pstmt = con.prepareStatement(q);
            // Set the values of parameters....
            pstmt.setInt(1, userId);

            // Execute...
            pstmt.executeUpdate();

            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    public static void displayStudents() {

        try {

            // JDBC code...
            Connection con = Connection_provider.create_connection();
            String q = "select * from students";
            Statement stmt = con.createStatement();

            // Execute...
            ResultSet set = stmt.executeQuery(q);

            while (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                String phone = set.getString(3);
                String city = set.getString(4);

                System.out.println("ID : " + id);
                System.out.println("NAME : " + name);
                System.out.println("PHONE : " + phone);
                System.out.println("CITY : " + city);
                System.out.println("------------------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
