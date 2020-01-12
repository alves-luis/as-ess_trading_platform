package BusinessModel.User;

import BusinessModel.Assets.Asset;
import java.util.List;

public class Admin extends User {

	private String sudopass;

	public Admin(int id, String username, String email, String password, String sudopass) {
		super(id, username, email, password);
		this.sudopass = sudopass;
	}
}