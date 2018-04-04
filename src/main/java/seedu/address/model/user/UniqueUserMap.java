package seedu.address.model.user;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Contains the users of the PrisonBook
 */
public class UniqueUserMap {

    private final ObservableList<User> internalList = FXCollections.observableArrayList();

    private HashMap<String, User> userMap;

    private final User defaultUser1 = new User("prisonguard", "password1", 1);
    private final User defaultUser2 = new User("prisonleader", "password2", 2);
    private final User defaultUser3 = new User("prisonwarden", "password3", 3);

    public UniqueUserMap() {
        resetData();
    }

    /**
     * Resets the existing data of this {@code userMap}.
     */
    public void resetData() {
        userMap = new HashMap<String, User>();
        addUser(defaultUser1);
        addUser(defaultUser2);
        addUser(defaultUser3);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<User> asObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Returns user's securityLevel if username and matching password is found in database, else return -1
     * @param username
     * @param password
     * @return
     */
    public int verify(String username, String password) {
        if (contains(username)) {
            return userMap.get(username).checkPassword(password);
        } else {
            return -1;
        }
    }

    public boolean contains(String username) {
        return userMap.containsKey(username);
    }

    /**
     * For storage purposes
     */
    public ObservableList<User> getUserList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    /**
     * Adds user to the userMap and the internalList
     * @param user must be valid User
     * @return true if added successfully and false if failed to add
     */
    public boolean addUser(User user) {
        if (!contains(user.getUsername())) {
            userMap.put(user.getUsername(), user);
            internalList.add(user);
            return true;
        } else {
            return false;
        }
    }

    public void setUsers(ObservableList<User> users) {
        for (User u: users) {
            addUser(u);
        }
        internalList.clear();
        internalList.setAll(users);
    }

    /**
     * Checks if two UniqueUserMaps are equal
     * @param obj any object
     * @return return true if the userMap and internalList are equal
     */
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof UniqueUserMap)) {
            return false;
        }

        // state check
        UniqueUserMap other = (UniqueUserMap) obj;
        return userMap.equals(other.userMap) && internalList.equals(other.internalList);
    }
}
