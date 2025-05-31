package com.Emailservice.Emailservice;


import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppConfig {

    private String username;
    private String password;
    private String persnalName;

    @PostConstruct
    public void print() {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }


    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPersnalName() { return persnalName; }
    public void setPersnalName(String persnalName) { this.persnalName = persnalName; }
}
