package BusinessModel;

public class UsersManager {

  private Map<Integer, User> users;

  public UsersManager() {
    this.users = new HashMap<>();
		for (User user : (new UserDAO()).getAll()) {
			this.users.put(user.getId(), user);
		}
  }

  public int getUserID(String email) {
    int id = -1;
    for (User u : this.users.values()) {
      if (u.getEmail().equals(email)) {
        id = u.getId();
      }
    }
    return id;
  }

  public User getUser(int userID) {
    return this.users.get(userID);
  }

  public
}
