package de.aicard.services;

import de.aicard.domains.learnset.LearningSession;
import de.aicard.storages.LearningSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LearningSessionService
{
    
    @Autowired
    LearningSessionRepository learningSessionRepository;
    
    @Autowired
    public LearningSessionService(){
        
    }
    
    public void delete(LearningSession learningSession){
        learningSessionRepository.delete(learningSession);
    }
        
}
