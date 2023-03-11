package net.codejava.hibernate;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
 
/**
 * UserDAOTest.java
 * Copyright by CodeJava.net
 */
public class UserDAOTest {
 
    public static void main(String[] args) throws SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {

         
    	//Add new user
    	
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
         
        User newUser2 = new User();
        newUser2.setEmail("billjoy2@gmail.com");
        newUser2.setFullname("bill Joy2");
        newUser2.setPassword("billi2");
         
        entityManager.persist(newUser2);
        entityManager.getTransaction().commit();
         
        
        //Update existing user
        User existingUser = new User();
        existingUser.setId(25);
        existingUser.setEmail("updatedbill.joy@gmail.com");
        existingUser.setFullname("Bill Joy");
        existingUser.setPassword("billionaire");
         
        entityManager.getTransaction().begin();
        entityManager.merge(existingUser);
        entityManager.getTransaction().commit();
        
        
        
        //get user by ID
        Integer primaryKey = 25;
        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, primaryKey);
         
        System.out.println(user.getEmail());
        System.out.println(user.getFullname());
        System.out.println(user.getPassword());
        
        
        entityManager.getTransaction().commit();
        
        
        
        //Custom SQL or rather we say JPQL
        entityManager.getTransaction().begin();
        String sql = "SELECT u from User u where u.email = 'updatedbill.joy@gmail.com'";
        Query query = entityManager.createQuery(sql);
        User user3 = (User) query.getSingleResult();
         
        System.out.println(user3.getEmail());
        System.out.println(user3.getFullname());
        System.out.println(user3.getPassword());
        entityManager.getTransaction().commit();
        
        //cleanup
        
        
        entityManager.close();
        factory.close();


    }
}
