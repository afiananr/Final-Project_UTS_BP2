package surabaya.kriya.gallery;

public class cLogin {
    private final cUser user_registered[] = new cUser[3];
    private String loggedin_username;
    private String loggedin_password;
    
    cLogin() {
        this.user_registered[0] = new cUser("kasir", "123456"); 
        this.user_registered[1] = new cUser("alin", "founder123"); 
        this.user_registered[2] = new cUser("nopal", "sby24"); 
        this.loggedin_username = null;
        this.loggedin_password = null;
    }

    public String getLoggedin_username() {
        return loggedin_username;
    }

    public String getLoggedin_password() {
        return loggedin_password;
    }
    
    public boolean login(String username, String password) {
        for (cUser user : user_registered) {
            if (user != null && username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                this.loggedin_username = username;
                this.loggedin_password = password;
                return true;
            }
        }
        
        return false;
    }
}
