package com.excilys.computer_database.dao;

import java.sql.SQLException;
import java.util.List;

import com.excilys.computer_database.model.User;

/**
 * Interface of the User DAO.
 * @author rlarroque
 *
 */
public interface UserDAO {
	
	 /**
     * Get the list of all the existing users on the db.
     * @return the List of User
     */
    List<User> getAll();

    /**
     * Returns a user according to the id passed.
     * @param id name of the user to retrieve
     * @return the retrieved user itself
     */
    User get(String username);

    /**
     * Method used to create a new user. A user has to passed as an argument so all its parameters can be added to the db.
     * @param u a user previously created
     * @return the id of the created user
     */
    long create(User u);

    /**
     * Method used to update an existing user. A user has to passed as an argument so all its parameters can be modified in the db.
     * @param u a user with the id of the one we want to modify but possibly with different parameters
     */
    void update(User u);

    /**
     * Delete a user according to the username passed.
     * @param username username of the user you want to delete.
     * @throws SQLException thrown in case of SQL issues
     */
    void delete(String username);
}
