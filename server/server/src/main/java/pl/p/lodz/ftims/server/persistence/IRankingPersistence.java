/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.p.lodz.ftims.server.entities.Ranking;
import pl.p.lodz.ftims.server.entities.User;

/**
 *
 * @author Piotr Grzelak
 */
public interface IRankingPersistence extends CrudRepository<Ranking, Integer> {
    
    Ranking findByUser(User user);
}
