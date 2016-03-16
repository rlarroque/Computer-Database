package com.excilys.computer_database.services;

import java.util.List;

import com.excilys.computer_database.model.User;

public interface UserService {

	   /**
     * Get the list of all the existing users on the db.
     * @return The List of User
     */
    List<User> getAll();

    /**
     * Returns a user according to the name passed.
     * @param username the name of the user
     * @return the retrieved user itself
     */
    User get(String username);

    /**
     * Method used to create a new user. A user has to passed as an argument so all its parameters can be added to the db.
     * @param user a user previously created
     * @return the id of the created user
     */
    long create(User user);

    /**
     * Method used to update an existing user. A user has to passed as an argument so all its parameters can be modified in the db.
     * @param user a user with the id of the one we want to modify but possibly with different parameters
     */
    void update(User user);

    /**
     * Delete a user according to the id passed.
     * @param username name of the user you want to delete.
     */
    void delete(String username);

}
