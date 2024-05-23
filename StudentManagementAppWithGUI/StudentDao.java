package com.manage_student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTextArea;

public class StudentDao {

    public static boolean insertStudentToDb(Student st) {
        boolean f = false;
        try {
            Connection con = Connection_provider.create_connection();
            String q = "INSERT INTO students(sname, sphone, scity) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setString(1, st.getStudentName());
            pstmt.setString(2, st.getStudentPhone());
            pstmt.setString(3, st.getStudentCity());
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
            Connection con = Connection_provider.create_connection();
            String q = "DELETE FROM students WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(q);
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void displayStudents(JTextArea displayArea) {
        try {
            Connection con = Connection_provider.create_connection();
            String q = "SELECT * FROM students";
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(q);
            while (set.next()) {
                int id = set.getInt(1);
                String name = set.getString(2);
                String phone = set.getString(3);
                String city = set.getString(4);
                displayArea.append("ID: " + id + ", Name: " + name + ", Phone: " + phone + ", City: " + city + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
