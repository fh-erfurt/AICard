package de.aicard.services;

import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.LearnSetAboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LearnSetAboService
{
    @Autowired
    LearnSetAboRepository learnSetAboRepository;
    
    
    @Autowired
    public LearnSetAboService(){
    
    }
    
    
    public Optional<LearnSetAbo> getLearnSetAbo(Long learnSetAboId){
        return learnSetAboRepository.findById(learnSetAboId);
    }
    
    public void save(LearnSetAbo learnSetAbo){
       learnSetAboRepository.save(learnSetAbo);
    }
    
    public void delete(LearnSetAbo learnSetAbo){
        learnSetAboRepository.delete(learnSetAbo);
    }
    
    
}
