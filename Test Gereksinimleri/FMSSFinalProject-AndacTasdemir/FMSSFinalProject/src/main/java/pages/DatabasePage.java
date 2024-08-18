package pages;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabasePage {

    public Connection connection;

    public void connectToDatabase()  {
        String url = "jdbc:mysql://localhost:3306/yourDatabase";
        String username = "root";
        String password = "password";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeDatabaseConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void aNewUserSignsUpWithAUsernameAndPassword(){
        String username = "newUser";
        String password = "password123";

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = md.digest(password.getBytes());

        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setBytes(2, hash);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void hashedValue() {
        String username = "newUser";
        String password = "password123";

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] expectedHash = md.digest(password.getBytes());

        String sql = "SELECT password FROM users WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (resultSet.next()) {
                byte[] storedHash = resultSet.getBytes("password");
                assert java.util.Arrays.equals(expectedHash, storedHash);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void theUsernameShouldBeStoredCorrectlyInTheCorrespondingTable()  {
        String username = "newUser";

        String sql = "SELECT username FROM users WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (!resultSet.next()) throw new AssertionError("Username not found in the database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void theUserAttemptsToLoginWithValidOrInvalidCredentials(){
        String username = "newUser";
        boolean loginSuccessful = false;

        String sql = "INSERT INTO login_attempts (username, attempt_time, successful) VALUES (?, NOW(), ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setBoolean(2, loginSuccessful);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void theLoginAttemptShouldBeRecordedInTheDatabaseWithTheTimestamp(){
        String username = "newUser";

        String sql = "SELECT * FROM login_attempts WHERE username = ? ORDER BY attempt_time DESC LIMIT 1";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (!resultSet.next()) throw new AssertionError("Login attempt not recorded in the database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void theStatusOfTheAttemptSuccessfulFailedShouldBeLogged(){
        String username = "newUser";

        String sql = "SELECT successful FROM login_attempts WHERE username = ? ORDER BY attempt_time DESC LIMIT 1";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (!resultSet.next()) throw new AssertionError("Login attempt not recorded in the database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            boolean isSuccess = resultSet.getBoolean("successful");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void theSessionIsEstablished(){
        String userId = "user123";

        String sql = "INSERT INTO sessions (user_id, start_time) VALUES (?, NOW())";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void theSessionDetailsShouldBeSavedInTheDatabaseWithTheCorrectUserID(){
        String userId = "user123";

        String sql = "SELECT * FROM sessions WHERE user_id = ? ORDER BY start_time DESC LIMIT 1";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (!resultSet.next()) throw new AssertionError("Session details not saved in the database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void whenTheUserLogsOutTheSessionDetailsShouldBeRemovedOrMarkedAsInactive(){
        String userId = "user123";

        String sql = "UPDATE sessions SET end_time = NOW() WHERE user_id = ? ORDER BY start_time DESC LIMIT 1";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void aUserTriesToSignUpWithAnEmailAddressAlreadyInUse(){
        String email = "alreadyInUse@example.com";

        String sql = "INSERT INTO users (email) VALUES (?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Duplicate email detected.");
        }
    }

    public void theDatabaseShouldPreventTheInsertionOfTheDuplicateEmail(){
        String email = "alreadyInUse@example.com";

        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int count = 0;
        try {
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        assert count == 1 : "Duplicate email was inserted into the database.";

    }

    public void theNumberOfFailedLoginAttemptsExceedsTheLimit(){
        String username = "newUser";

        String sql = "UPDATE users SET status = 'locked' WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void theUserSAccountStatusShouldBeUpdatedInTheDatabaseTo(){
        String username = "newUser";

        String sql = "SELECT status FROM users WHERE username = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statement.setString(1, username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String accountStatus = null;
        try {
            accountStatus = resultSet.getString("status");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        assert accountStatus.equals("status") : "Account status was not updated correctly.";
    }






}
