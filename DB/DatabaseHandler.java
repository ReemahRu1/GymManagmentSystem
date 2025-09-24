/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagementsystem.DB;

import gymmanagementsystem.GymClasses.Admin;
import gymmanagementsystem.GymClasses.Classes;
import gymmanagementsystem.GymClasses.Feedback;
import gymmanagementsystem.GymClasses.Member;
import gymmanagementsystem.GymClasses.Service;
import gymmanagementsystem.GymClasses.Trainer;
import gymmanagementsystem.GymClasses.User;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import oracle.jdbc.clio.annotations.Debug.Level;

public class DatabaseHandler {

    // Method to add a new book to the database
    public static boolean insertMember(Member member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement memberStmt = null;

        String sql = "INSERT INTO users(name, phone, username, password, email,role) VALUES (?, ?, ?, ?, ?,?)";
        String getIdSql = "SELECT id FROM users WHERE username = ?";

        try {
            conn = connect.getInstance().getConnection();

            // Insert into the users table
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPhone());
            pstmt.setString(3, member.getUsername());
            pstmt.setString(4, member.getPassword());
            pstmt.setString(5, member.getEmail());
            pstmt.setString(6, "member");

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            // Retrieve the generated user ID by querying using the unique username
            int userId;
            try (PreparedStatement getIdStmt = conn.prepareStatement(getIdSql)) {
                getIdStmt.setString(1, member.getUsername());
                try ( var rs = getIdStmt.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getInt("id");
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
            // Insert into the member table with the generated user_id
            String memberSql = "INSERT INTO member (user_id, membership) VALUES (?, ?)";
            memberStmt = conn.prepareStatement(memberSql);
            memberStmt.setInt(1, userId);
            memberStmt.setString(2, member.getMembership());

            memberStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (memberStmt != null) {
                    memberStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean insertTrainer(Trainer trainer) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement trainerStmt = null;

        String sql = "INSERT INTO users(name, phone, username, password, email,role) VALUES (?, ?, ?, ?, ?,?)";
        String getIdSql = "SELECT id FROM users WHERE username = ?";

        try {
            conn = connect.getInstance().getConnection();

            // Insert into the users table
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, trainer.getName());
            pstmt.setString(2, trainer.getPhone());
            pstmt.setString(3, trainer.getUsername());
            pstmt.setString(4, trainer.getPassword());
            pstmt.setString(5, trainer.getEmail());
            pstmt.setString(6, "trainer");

            pstmt.executeUpdate();


            // Retrieve the generated user ID by querying using the unique username
            int userId= -1;
            PreparedStatement getIdStmt = conn.prepareStatement(getIdSql);
                getIdStmt.setString(1, trainer.getUsername());
               var rs = getIdStmt.executeQuery();
                    if (rs.next()) {
                        userId = rs.getInt("id");
                    
                }
            

            // Insert into the trainer table with the generated user_id
            String trainerSql = "INSERT INTO trainer (user_id, trainershift) VALUES (?, ?)";
            trainerStmt = conn.prepareStatement(trainerSql);
            trainerStmt.setInt(1, userId);
            trainerStmt.setString(2, trainer.getTrainershift());

            trainerStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (trainerStmt != null) {
                    trainerStmt.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean insertAdmin(Admin admin) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement adminStmt = null;

        String sql = "INSERT INTO users(name, phone, username, password, email,role) VALUES (?,?, ?, ?, ?, ?)";
        String getIdSql = "SELECT id FROM users WHERE username = ?";

        try {
            conn = connect.getInstance().getConnection();

            // Insert into the users table
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getPhone());
            pstmt.setString(3, admin.getUsername());
            pstmt.setString(4, admin.getPassword());
            pstmt.setString(5, admin.getEmail());
            pstmt.setString(6, "admin");

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            // Retrieve the generated user ID by querying using the unique username
            int userId;
            try (PreparedStatement getIdStmt = conn.prepareStatement(getIdSql)) {
                getIdStmt.setString(1, admin.getUsername());
                try ( var rs = getIdStmt.executeQuery()) {
                    if (rs.next()) {
                        userId = rs.getInt("id");
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }

            // Insert into the "admin" table with the generated user_id
            String adminSql = "INSERT INTO admins (user_id) VALUES (?)";
            adminStmt = conn.prepareStatement(adminSql);
            adminStmt.setInt(1, userId);

            adminStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (adminStmt != null) {
                    adminStmt.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean insertClass(Classes classes) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "INSERT INTO classes( name,trainer_id,day, time, description,room,capacity) VALUES (?, ?, ?, ?, ?,?  ,?)";

        try {
            conn = connect.getInstance().getConnection();

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, classes.getName());
            pstmt.setInt(2, classes.getTrainer().getId());
            pstmt.setString(3, classes.getDay());
            pstmt.setString(4, classes.getTime());
            pstmt.setString(5, classes.getDescription());
            pstmt.setString(6, classes.getRoom());
            pstmt.setInt(7, classes.getCapacity());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean insertService(Service service) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "INSERT INTO service( name,amount) VALUES (?, ?)";

        try {
            conn = connect.getInstance().getConnection();

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, service.getName());
            pstmt.setDouble(2, service.getAmount());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean insertMemberClasses(int memberId, int classesId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = """
                 INSERT INTO member_classes (member_id, classes_id)
                 VALUES (?, ?)
                 """;

        try {
            // Establish connection
            conn = connect.getInstance().getConnection();

            // Prepare the SQL statement
            pstmt = conn.prepareStatement(sql);

            // Set the parameters for the prepared statement
            pstmt.setInt(1, memberId);
            pstmt.setInt(2, classesId);

            // Execute the update
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> List<T> loadAll(String type) {
        List<T> records = new ArrayList<>();
        if (type.equals("Member") || type.equals("Trainer")) {
            String sql = getSqlQuery(type);

            if (sql != null) {
                try (
                        Connection conn = connect.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        // Get the actual `id` of the member or trainer from their respective tables
                        int id = rs.getInt(type.equals("Member") ? "member_id" : "trainer_id");  // Use correct ID based on type
                        String name = rs.getString("name");
                        String phone = rs.getString("phone");
                        String username = rs.getString("username");
                        String pass = rs.getString("password");
                        String email = rs.getString("email");

                        if (type.equals("Member")) {
                            String membership = rs.getString("membership");
                            Member m = new Member(id, name, phone, username, pass, email, membership);
                            records.add((T) m);
                        } else if (type.equals("Trainer")) {
                            String trainershift = rs.getString("trainershift");
                            Trainer t = new Trainer(id, name, phone, username, pass, email, trainershift);
                            records.add((T) t);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return records;
    }

// Helper method to get the SQL query based on type
    private static String getSqlQuery(String type) {
        if (type.equals("Member")) {
            return """
               SELECT u.id AS user_id, m.id AS member_id, u.name, u.phone, u.username, u.password, u.email,
                      m.membership
               FROM users u
               JOIN member m ON u.id = m.user_id
               """;
        } else if (type.equals("Trainer")) {
            return """
               SELECT u.id AS user_id, t.id AS trainer_id, u.name, u.phone, u.username, u.password, u.email,
                      t.trainershift
               FROM users u
               JOIN trainer t ON u.id = t.user_id
               """;
        }
        // Add more conditions for other types as needed
        return null; // Unsupported type
    }

    public static List<Classes> loadClassesByTrainer(int trainerId) {
        List<Classes> classesList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // SQL query to load all classes for a specific trainer
        String sql = """
                 SELECT c.id AS class_id, c.name, c.day, c.time, c.capacity, c.Description, c.room ,c.trainer_id
                 FROM classes c
                 WHERE c.trainer_id = ?
                 """;

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, trainerId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int classId = rs.getInt("class_id");
                String className = rs.getString("name");
                String day = rs.getString("day");
                String time = rs.getString("time");
                int capacity = rs.getInt("capacity");
                String description = rs.getString("Description");
                String room = rs.getString("room");

                // Get the Trainer object using the trainerId 
                Trainer trainer = getTrainerById(trainerId);

                Classes c = new Classes(classId, day, time, className, description, capacity, trainer, room);

                classesList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return classesList;
    }

    public static List<Service> loadAllServices() {
        List<Service> services = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT id, name, amount FROM service";

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");

                Service service = new Service(id, name, amount);
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return services;  // Return the list of services
    }

    // Method to delete a service by its ID
    public static boolean deleteServiceById(int serviceId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "DELETE FROM service WHERE id = ?";

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, serviceId);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false if there was an error
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to book a service for a member by their username
    public static boolean bookServiceByUsername(String username, int serviceId) {
        Connection conn = null;
        PreparedStatement pstmtMemberId = null;
        PreparedStatement pstmtBookService = null;

        String getMemberIdSql = """
                SELECT m.id AS member_id
                FROM users u
                JOIN member m ON u.id = m.user_id
                WHERE u.username = ?
                """;

        String bookServiceSql = """
                INSERT INTO member_service (member_id, service_id)
                VALUES (?, ?)
                """;

        try {
            conn = connect.getInstance().getConnection();
            //Get the member_id by username
            pstmtMemberId = conn.prepareStatement(getMemberIdSql);
            pstmtMemberId.setString(1, username);
            ResultSet rs = pstmtMemberId.executeQuery();

            if (rs.next()) {
                int memberId = rs.getInt("member_id");
                // Book the service by inserting into the member_service table
                pstmtBookService = conn.prepareStatement(bookServiceSql);
                pstmtBookService.setInt(1, memberId);
                pstmtBookService.setInt(2, serviceId);
                pstmtBookService.executeUpdate();
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false in case of an error
        } finally {
            try {
                if (pstmtMemberId != null) {
                    pstmtMemberId.close();
                }
                if (pstmtBookService != null) {
                    pstmtBookService.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to get all service IDs
    public static List<Integer> getAllServiceIds() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> serviceIds = new ArrayList<>();

        String sql = "SELECT id FROM service";  // SQL query to get all service IDs

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int serviceId = rs.getInt("id");
                serviceIds.add(serviceId);  // Add service ID to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return serviceIds;  // Return the list of service IDs
    }

    public static List<String> loadMemberUsernamesByRole() {
        List<String> usernames = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // SQL query to load all usernames of users who have the role 'member'
        String sql = """
                 SELECT u.username
                 FROM users u
                 WHERE u.role = 'member'
                 """;

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                usernames.add(username);  // Add each username to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usernames;
    }

    public static int countMemberClassesByUsername(String username) {
        int classCount = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // SQL query to count the number of member_classes for a specific member by username
        String sql = """
                 SELECT COUNT(*) AS class_count
                 FROM member_classes mc
                 JOIN member m ON mc.member_id = m.id
                 JOIN users u ON m.user_id = u.id
                 WHERE u.username = ?
                 """;

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                classCount = rs.getInt("class_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return classCount;  // Return the count of member_classes
    }

    public static List<String> getClassNamesByUsername(String username) {
        List<String> classNames = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // SQL query to get the class names for a specific member by username
        String sql = """
                 SELECT c.name AS class_name
                 FROM member_classes mc
                 JOIN member m ON mc.member_id = m.id
                 JOIN users u ON m.user_id = u.id
                 JOIN classes c ON mc.classes_id = c.id
                 WHERE u.username = ?
                 """;

        try {
            conn = connect.getInstance().getConnection();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String className = rs.getString("class_name");
                classNames.add(className);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return classNames;  // Return the list of class names
    }

    public static boolean insertFeedback(String username, int trainerId, String feedbackMessage) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // SQL query to get the member_id based on the username
        String getMemberIdSql = "SELECT m.id FROM member m JOIN users u ON m.user_id = u.id WHERE u.username = ?";
        // SQL query to insert feedback into the feedback table
        String insertFeedbackSql = "INSERT INTO feedback (member_id, trainer_id, feedbackMessage) VALUES (?, ?, ?)";

        try {
            conn = connect.getInstance().getConnection();

            pstmt = conn.prepareStatement(getMemberIdSql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int memberId = rs.getInt("id");

                pstmt.close();

                pstmt = conn.prepareStatement(insertFeedbackSql);
                pstmt.setInt(1, memberId);
                pstmt.setInt(2, trainerId);
                pstmt.setString(3, feedbackMessage);
                pstmt.executeUpdate();

            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Classes> loadClassesByMemberId(int memberId) {
        List<Classes> classesList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // SQL query to get all the classes register by the member
        String sql = """
                 SELECT c.id AS class_id, c.name AS class_name, c.day, c.time, c.capacity, c.Description, c.room,
                        t.id AS trainer_id, u.name AS trainer_name, u.phone AS trainer_phone, u.username AS trainer_username, 
                        u.password AS trainer_password, u.email AS trainer_email, t.trainershift
                 FROM classes c
                 JOIN member_classes mc ON c.id = mc.classes_id
                 JOIN trainer t ON c.trainer_id = t.id
                 JOIN users u ON t.user_id = u.id
                 WHERE mc.member_id = ?
                 """;

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int classId = rs.getInt("class_id");
                String className = rs.getString("class_name");
                String day = rs.getString("day");
                String time = rs.getString("time");
                int capacity = rs.getInt("capacity");
                String description = rs.getString("Description");
                String room = rs.getString("room");

                int trainerId = rs.getInt("trainer_id");
                String trainerName = rs.getString("trainer_name");
                String trainerPhone = rs.getString("trainer_phone");
                String trainerUsername = rs.getString("trainer_username");
                String trainerPassword = rs.getString("trainer_password");
                String trainerEmail = rs.getString("trainer_email");
                String trainerShift = rs.getString("trainershift");

                // Create trainer object 
                Trainer trainer = new Trainer(trainerId, trainerName, trainerPhone, trainerUsername,
                        trainerPassword, trainerEmail, trainerShift);

                // Create Classes object
                Classes c = new Classes(classId, day, time, className, description, capacity, trainer, room);
                // Add to the list
                classesList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return classesList;
    }

    // Method to get the amount of a service by its ID
    public static double getServiceAmountById(int serviceId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        double amount = -1; // indicate service not found

        String sql = "SELECT amount FROM service WHERE id = ?";  // SQL query to get the service amount by ID

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, serviceId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                amount = rs.getDouble("amount");  // Retrieve the amount for the given service ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return amount;  // Return the service amount (or -1 if not found)
    }

    public static int getTrainerIdByUserId(int userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // SQL query to get the trainer_id based on the user_id
        String sql = "SELECT id FROM trainer WHERE user_id = ?";

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                System.out.println("No trainer found for user_id: " + userId);
                return -1;  // Return -1 if no trainer is found for the given user_id
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;  // Return -1 if an error occurs during the query
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static int countClassesForMember(int memberId) {
        int classCount = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // SQL query to count the number of classes for the given member
        String sql = """
                 SELECT COUNT(*) AS class_count
                 FROM member_classes mc
                 JOIN classes c ON mc.classes_id = c.id
                 WHERE mc.member_id = ?
                 """;

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                classCount = rs.getInt("class_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return classCount;
    }

    public static User authenticateUser(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        User user = null;
        String sql = """
                 SELECT u.id AS user_id, u.name, u.phone, u.username, u.password, u.email, u.role,
                      d.id AS admin_id  , m.id AS member_id, m.membership,t.id AS trainer_id, t.trainershift 
                 FROM users u
                 LEFT JOIN member m ON u.id = m.user_id
                 LEFT JOIN trainer t ON u.id = t.user_id
                     LEFT JOIN admins d ON u.id = d.user_id
                 WHERE u.username = ? AND u.password = ?
                 """;

        try {
            conn = connect.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String usernameDb = rs.getString("username");
                String pass = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");

                if ("member".equals(role)) {
                    String membership = rs.getString("membership");
                    int id = rs.getInt("member_id");
                    user = new Member(id, name, phone, usernameDb, pass, email, membership);
                } else if ("trainer".equals(role)) {
                    String trainershift = rs.getString("trainershift");
                    int id = rs.getInt("trainer_id");
                    user = new Trainer(id, name, phone, usernameDb, pass, email, trainershift);
                } else if ("admin".equals(role)) {
                    int id = rs.getInt("admin_id");
                    user = new Admin(id, name, phone, usernameDb, pass, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return user;
    }

    public static boolean deleteMember(int memberId) {
        Connection conn = null;
        PreparedStatement memberStmt = null;
        PreparedStatement userStmt = null;
        PreparedStatement userIdStmt = null;
        ResultSet rs = null;

        // SQL queries
        String getUserIdSql = "SELECT user_id FROM member WHERE id = ?";
        String memberSql = "DELETE FROM member WHERE id = ?";
        String userSql = "DELETE FROM users WHERE id = ?";

        try {
            conn = connect.getInstance().getConnection();

            // Get the user_id associated with the memberId
            userIdStmt = conn.prepareStatement(getUserIdSql);
            userIdStmt.setInt(1, memberId);
            rs = userIdStmt.executeQuery();

            int userId = -1;
            if (rs.next()) {
                userId = rs.getInt("user_id");
            } else {
                // Member not found with the given ID
                return false;
            }

            // Delete from the member table 
            memberStmt = conn.prepareStatement(memberSql);
            memberStmt.setInt(1, memberId);
            memberStmt.executeUpdate();

            //Delete from the users table
            userStmt = conn.prepareStatement(userSql);
            userStmt.setInt(1, userId);
            userStmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (memberStmt != null) {
                    memberStmt.close();
                }
                if (userStmt != null) {
                    userStmt.close();
                }
                if (userIdStmt != null) {
                    userIdStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

public static boolean deleteTrainer(int trainerId) {
    Connection conn = null;
    PreparedStatement userIdStmt = null;
    PreparedStatement trainerStmt = null;
    PreparedStatement userStmt = null;

    // SQL queries
    String userIdSql = "SELECT user_id FROM trainer WHERE id = ?";
    String trainerSql = "DELETE FROM trainer WHERE id = ?";
    String userSql = "DELETE FROM users WHERE id = ?";

    try {
        conn = connect.getInstance().getConnection();

        // Retrieve user_id 
        userIdStmt = conn.prepareStatement(userIdSql);
        userIdStmt.setInt(1, trainerId);
        ResultSet rs = userIdStmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("No user_id found for the given trainer id.");
        }
        int userId = rs.getInt("user_id");

        // Delete from the trainer table
        trainerStmt = conn.prepareStatement(trainerSql);
        trainerStmt.setInt(1, trainerId);
        int trainerRowsAffected = trainerStmt.executeUpdate();
        if (trainerRowsAffected == 0) {
            throw new SQLException("Deleting from trainer failed, no rows affected.");
        }

        // Delete from the users table
        userStmt = conn.prepareStatement(userSql);
        userStmt.setInt(1, userId);
        userStmt.executeUpdate();

        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (userIdStmt != null) {
                userIdStmt.close();
            }
            if (trainerStmt != null) {
                trainerStmt.close();
            }
            if (userStmt != null) {
                userStmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    public static List<Classes> loadClasses() {
        List<Classes> classes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = """
                 SELECT c.id AS class_id, c.name AS class_name, c.day, c.time, c.capacity, c.Description ,c.room ,
                        c.trainer_id
                 FROM classes c
                 """;

        try {
            conn = connect.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int classId = rs.getInt("class_id");
                String className = rs.getString("class_name");
                String day = rs.getString("day");
                String time = rs.getString("time");
                int capacity = rs.getInt("capacity");
                String description = rs.getString("Description");
                String room = rs.getString("room");

                int trainerId = rs.getInt("trainer_id");

                // Get the Trainer object by trainer_id using the getTrainerById method
                Trainer trainer = getTrainerById(trainerId);
                if (trainer != null) {
                    Classes c = new Classes(classId, day, time, className, description, capacity, trainer, room);
                    classes.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return classes;
    }

    public static boolean updateAdmin(Admin admin) {
        Connection conn = null;
        PreparedStatement stmt = null;

        // SQL Queries
        String getUserIdSql = "SELECT user_id FROM admins WHERE id = ?";
        String updateUserSql = "UPDATE users SET name = ?, phone = ?, email = ?, username = ?, password = ? WHERE id = ?";

        try {
            conn = connect.getInstance().getConnection();

            // Get the user_id from the admins table using the admin_id
            PreparedStatement getUserIdStmt = conn.prepareStatement(getUserIdSql);
            getUserIdStmt.setInt(1, admin.getId());
            ResultSet rs = getUserIdStmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");

                // Update the users table with the new details
                PreparedStatement updateUserStmt = conn.prepareStatement(updateUserSql);
                updateUserStmt.setString(1, admin.getName());
                updateUserStmt.setString(2, admin.getPhone());
                updateUserStmt.setString(3, admin.getEmail());
                updateUserStmt.setString(4, admin.getUsername());
                updateUserStmt.setString(5, admin.getPassword());
                updateUserStmt.setInt(6, userId);
                updateUserStmt.executeUpdate();

                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to update user and member info based on member_id
    public static boolean updateMember(Member member) {
        Connection conn = null;
        PreparedStatement memberStmt = null;

        String getUserIdSql = "SELECT user_id FROM member WHERE id = ?";
        String updateUserSql = "UPDATE users SET name = ?, phone = ?, email = ?, username = ? , password = ? WHERE id = ?";
        String updateMemberSql = "UPDATE member SET membership = ? WHERE id = ?";

        try {
            conn = connect.getInstance().getConnection();
            // Get the user_id from the member table using the member_id
            PreparedStatement getUserIdStmt = conn.prepareStatement(getUserIdSql);
            getUserIdStmt.setInt(1, member.getId());
            ResultSet rs = getUserIdStmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");

                // Update the users table with the new details
                PreparedStatement updateUserStmt = conn.prepareStatement(updateUserSql);
                updateUserStmt.setString(1, member.getName());
                updateUserStmt.setString(2, member.getPhone());
                updateUserStmt.setString(3, member.getEmail());
                updateUserStmt.setString(4, member.getUsername());
                updateUserStmt.setString(5, member.getPassword());
                updateUserStmt.setInt(6, userId);
                updateUserStmt.executeUpdate();

                //Update the membership in the member table
                PreparedStatement updateMemberStmt = conn.prepareStatement(updateMemberSql);
                updateMemberStmt.setString(1, member.getMembership());
                updateMemberStmt.setInt(2, member.getId());
                updateMemberStmt.executeUpdate();

                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateTrainer(Trainer trainer) {
        String getUserIdSql = "SELECT user_id FROM trainer WHERE id = ?";
        String updateUserSql = "UPDATE users SET name = ?, phone = ?, email = ?, username = ?, password = ? WHERE id = ?";
        String updateTrainerSql = "UPDATE trainer SET trainershift = ? WHERE id = ?";

        try (Connection conn = connect.getInstance().getConnection(); PreparedStatement getUserIdStmt = conn.prepareStatement(getUserIdSql); PreparedStatement updateUserStmt = conn.prepareStatement(updateUserSql); PreparedStatement updateTrainerStmt = conn.prepareStatement(updateTrainerSql)) {

            // Get the user_id from the trainer table using the trainer_id
            getUserIdStmt.setInt(1, trainer.getId());
            try (ResultSet rs = getUserIdStmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");

                    // Update the users table with the new details
                    updateUserStmt.setString(1, trainer.getName());
                    updateUserStmt.setString(2, trainer.getPhone());
                    updateUserStmt.setString(3, trainer.getEmail());
                    updateUserStmt.setString(4, trainer.getUsername());
                    updateUserStmt.setString(5, trainer.getPassword());
                    updateUserStmt.setInt(6, userId);
                    updateUserStmt.executeUpdate();

                    // Update the trainer shift in the trainer table
                    updateTrainerStmt.setString(1, trainer.getTrainershift());
                    updateTrainerStmt.setInt(2, trainer.getId());
                    updateTrainerStmt.executeUpdate();

                    return true;
                } else {

                    return false; // Trainer ID not found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Feedback> loadFeedbackByMemberId(int memberId) {
        List<Feedback> feedbackList = new ArrayList<>();

        String sql = "SELECT id, member_id, trainer_id, feedbackMessage FROM feedback WHERE member_id = ?";

        try (Connection conn = connect.getInstance().getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            Member member = getMemberById(memberId);
            if (member == null) {
                System.out.println("No member found with ID: " + memberId);
                return feedbackList; // Return empty list if member is not found
            }
            while (rs.next()) {
                int feedbackId = rs.getInt("id");
                int trainerId = rs.getInt("trainer_id");
                String feedbackMessage = rs.getString("feedbackMessage");

                Trainer trainer = getTrainerById(trainerId);

                Feedback feedback = new Feedback(feedbackId, member, trainer, feedbackMessage);
                feedbackList.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

    public static List<String> getFeedbackForMemberById(int memberId) {
        List<String> feedbackMessages = new ArrayList<>();
        String query = "SELECT f.feedbackMessage, u.name AS trainer_name "
                + "FROM feedback f "
                + "JOIN member m ON f.member_id = m.id "
                + "JOIN trainer t ON f.trainer_id = t.id "
                + "JOIN users u ON t.user_id = u.id "
                + "WHERE m.id = ?";

        try {
            Connection conn = connect.getInstance().getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, memberId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String feedbackMessage = resultSet.getString("feedbackMessage");
                String trainerName = resultSet.getString("trainer_name");
                feedbackMessages.add(" Trainer: " + trainerName + " - Feedback message : '" + feedbackMessage + "'");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackMessages;
    }

    public static Member getMemberById(int memberId) {
        String sql = """
                 SELECT m.id AS member_id, u.id AS user_id, u.name, u.phone, 
                        u.username, u.password, u.email, m.membership
                 FROM member m
                 JOIN users u ON m.user_id = u.id
                 WHERE m.id = ?
                 """;

        try {
            Connection conn = connect.getInstance().getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String membership = rs.getString("membership");
                return new Member(userId, name, phone, username, password, email, membership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Trainer getTrainerById(int trainerId) {
        String sql = """
                 SELECT u.id AS user_id, u.name, u.phone, 
                        u.username, u.password, u.email, t.trainershift
                 FROM trainer t
                 JOIN users u ON u.id = t.user_id
                 WHERE t.id = ?
                 """;

        try {
            Connection conn = connect.getInstance().getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, trainerId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String username = rs.getString("username");
                String password = rs.getString("password");

                String email = rs.getString("email");
                String trainerShift = rs.getString("trainershift");
                return new Trainer(userId, name, phone, username, password, email, trainerShift);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to calculate the number of members
    public static int getMemberCount() {
        int count = 0;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM member";

        try {
            conn = connect.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public static int getTrainerCount() {
        int count = 0;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM trainer";

        try {
            conn = connect.getInstance().getConnection();

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public static int getClassCount() {
        int count = 0;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM classes";  // SQL query to count classes

        try {
            conn = connect.getInstance().getConnection();

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public static Admin getAdminById(int adminId) {
        String sql = """ 
                 SELECT a.id AS admin_id, u.id AS user_id, u.name, u.phone,  
                        u.username, u.password, u.email, u.role 
                 FROM admins a 
                 JOIN users u ON a.user_id = u.id 
                 WHERE a.id = ? 
                 """;

        try {
            Connection conn = connect.getInstance().getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, adminId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                return new Admin(userId, name, phone, username, password, email);
            } else {
                System.out.println("No admin found with ID: " + adminId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
