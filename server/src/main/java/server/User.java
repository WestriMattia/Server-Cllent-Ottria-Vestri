package server;
public class User {
    String ip_address;
    String user_name;

    public User(String user_name, String ip_address) {
        this.ip_address = ip_address;
        this.user_name = user_name;
    }

    public String getIp_address() {
        return this.ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

}
