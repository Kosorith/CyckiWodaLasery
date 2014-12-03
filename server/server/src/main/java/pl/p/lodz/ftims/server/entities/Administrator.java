/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piotr Grzelak
 */
@Entity
@Table(name = "administrators")
@NamedQueries({
    @NamedQuery(name = "Administrator.findByLoginPasswd", 
            query = "SELECT a FROM Administrator a where a.login = :loginParam and a.password = :passwdParam")})
public class Administrator implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private short id;
    
    @NotNull
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.login);
        hash = 61 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrator other = (Administrator) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    } 
}
