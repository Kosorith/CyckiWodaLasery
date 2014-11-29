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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Piotr Grzelak
 */
@Entity
@Table(name = "hints")
@NamedQueries({
    @NamedQuery(name = "Hint.findAll", query = "SELECT h FROM Hint h")})
public class Hint implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private int id;
    
    @NotNull
    @Column(name = "text", nullable = false)
    private String text;
    
    @NotNull
    @Column(name = "distance", nullable = false)
    private int distance;

    @Column(name = "photo", nullable = false)
    private String photo;
    
    @JoinColumn(name = "challenge_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Challenge challenge;

    public Hint() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.text);
        hash = 89 * hash + Objects.hashCode(this.distance);
        hash = 89 * hash + Objects.hashCode(this.photo);
        hash = 89 * hash + Objects.hashCode(this.challenge);
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
        final Hint other = (Hint) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.distance, other.distance)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        return Objects.equals(this.challenge, other.challenge);
    }
}
