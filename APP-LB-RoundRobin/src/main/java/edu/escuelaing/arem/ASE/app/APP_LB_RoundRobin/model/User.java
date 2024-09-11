package edu.escuelaing.arem.ASE.app.APP_LB_RoundRobin.model;

public class User {
    private String id;
    private String nickname;
    private String mail;
    private String role;

    public User(String id, String nickname, String mail, String role) {
        this.id = id;
        this.nickname = nickname;
        this.mail = mail;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
