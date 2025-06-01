package com.example.usuarios;

public class User {
    private String name;
    private String email;
    private String phone;
    private String city;
    private String company;

    public User(String name, String email, String phone, String city, String company) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.company = company;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getCity() { return city; }
    public String getCompany() { return company; }
}
