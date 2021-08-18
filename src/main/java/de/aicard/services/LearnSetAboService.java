package de.aicard.services;

import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.storages.LearnSetAboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * handles learnsetabo repository requests
 *
 * @author Daniel Michel,Martin Kühlborn,Clemens Berger
 */
@Service
public class LearnSetAboService {
    final LearnSetAboRepository learnSetAboRepository;


    @Autowired
    public LearnSetAboService(LearnSetAboRepository learnSetAboRepository) {

        this.learnSetAboRepository = learnSetAboRepository;
    }

    /**
     * finds a LearnsetAbo by its id
     *
     * @param learnSetAboId /
     * @return Optional\<LearnSetAbo\>
     */
    public Optional<LearnSetAbo> getLearnSetAbo(Long learnSetAboId) {
        return learnSetAboRepository.findById(learnSetAboId);
    }

    /**
     * saves a learnSetAbo
     *
     * @param learnSetAbo /
     */
    public void save(LearnSetAbo learnSetAbo) {
        learnSetAboRepository.save(learnSetAbo);
    }

    /**
     * deletes a learnSetAbo
     *
     * @param learnSetAbo /
     */
    public void delete(LearnSetAbo learnSetAbo) {
        learnSetAboRepository.delete(learnSetAbo);
    }


}
