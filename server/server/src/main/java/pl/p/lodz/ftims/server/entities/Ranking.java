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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piotr Grzelak
 */
@Entity
@Table(name = "ranking")
@NamedQueries({
    @NamedQuery(name = "Ranking.findAll", query = "SELECT r FROM Ranking r")})
public class Ranking implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private int id;
    
    @NotNull
    @Column(name = "points", nullable = false)
    private int points;
    
    @NotNull
    @Column(name = "completed_challenges_num", nullable = false)
    private int completedChallengesNum;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    @OneToOne(optional = false)
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCompletedChallengesNum() {
        return completedChallengesNum;
    }

    public void setCompletedChallengesNum(int completedChallengesNum) {
        this.completedChallengesNum = completedChallengesNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.points;
        hash = 17 * hash + this.completedChallengesNum;
        hash = 17 * hash + Objects.hashCode(this.user);
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
        final Ranking other = (Ranking) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.points != other.points) {
            return false;
        }
        if (this.completedChallengesNum != other.completedChallengesNum) {
            return false;
        }
        return Objects.equals(this.user, other.user);
    }
}
