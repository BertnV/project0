package org.example.dao;


import org.example.model.Loans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LoansDao{
    private final String url;
    private final String dbUser;
    private final String dbPassWord;

    public  LoansDao(){
        this.url = "jdbc:postgresql://localhost:5432/project_0";
        this.dbUser = "postgres";
        this.dbPassWord = "password";



    }

    public Loans addLoan(Loans loan) {
        String sql = "INSERT INTO loans (user_id, loan_type, amount, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassWord);
             // Note: We request generated keys if needed
             // maybe in the future we redirect the user and use this key to get their loans

             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(2, loan.getUserId());
            stmt.setString(3, loan.getLoanType());
            stmt.setInt(4, loan.getAmount());
            stmt.setString(5,loan.getStatus());

            stmt.executeUpdate();

            // Retrieve auto-generated ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // The first column in 'generatedKeys' is the newly inserted ID
                    int newId = generatedKeys.getInt(1);
                    loan.setId(newId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the updated User object (now containing its DB-assigned ID)
        return loan;
    }

    /**
     * Fetch all Loans in the database.
     */
    public List<Loans> getAllLoans() {
        List<Loans> loans = new ArrayList<>();
        String sql = "SELECT * FROM loans";

        try(Connection conn = DriverManager.getConnection(url, dbUser, dbPassWord);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Loans l = new Loans(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("loan_type"),
                        rs.getInt("amount"),
                        rs.getString("status")
                );
                System.out.println(l.toString());
                loans.add(l);
            }
            }catch (SQLException e) {
            e.printStackTrace();
        }
            return loans;
    }
        /**
         * Fetch Loans for a single user.
         */

    public List<Loans> getUserLoans(int userId) {
        List<Loans> loans = new ArrayList<>();
        String sql = "SELECT * FROM loans WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassWord);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    Loans l = new Loans(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("loan_type"),
                            rs.getInt("amount"),
                            rs.getString("status")
                    );
                    loans.add(l);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }
            /*
             * Update an existing To-do’s title/completion status by ID.
             */
            public void UpdateLoan(Loans loan) {
                String sql = "UPDATE loans SET user_id = ?, loan_type = ?, amount = ?, status = ?";

                try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassWord);

                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, loan.getId());
                    stmt.setString(2, loan.getLoanType());
                    stmt.setInt(3, loan.getAmount());
                    stmt.setString(4, loan.getStatus());
                    stmt.executeUpdate();

                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            public  List<Loans> deleteLoan(int userId) {
                String sql = "DELETE FROM loans WHERE id = ?";

                try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassWord);
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

                    stmt.setInt(1, userId);
                    stmt.executeUpdate();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return deleteLoan(userId);
            }


    public void updateLoan() {

    }
}


