/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.p.lodz.ftims.server.persistence;

import org.springframework.data.repository.CrudRepository;
import pl.p.lodz.ftims.server.entities.Administrator;

/**
 *
 * @author Piotr Grzelak
 */
public interface IAdminPersistence extends CrudRepository<Administrator, Integer> {
    
    /**
     * 
     * @param login
     * @param password
     * @return 
     */
    Administrator findByLoginAndPassword(String login, String password);
}
