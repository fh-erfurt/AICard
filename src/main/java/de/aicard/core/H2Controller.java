package de.aicard.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

/**
 * handles the h2 database
 *
 * @author Clemens Berger
 */
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class H2Controller
{
    
    private static H2Controller instance;
    
    private final EntityManagerFactory factory;
    
    public static H2Controller getManager()
    {
        if (Objects.isNull(instance))
        {
            instance = new H2Controller(Persistence.createEntityManagerFactory("de.aicard.jpa"));
        }
        return instance;
    }
    
    public EntityManager getEntityManager()
    {
        return factory.createEntityManager();
    }
    
}
