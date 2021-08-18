package de.aicard.services;

import de.aicard.domains.learnset.LearningSession;
import de.aicard.storages.LearningSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * provides delete method for learningsession
 *
 * @author Clemes Berger, Daniel Michel, Martin Kühhlborn
 */
@Service
public class LearningSessionService {

    final LearningSessionRepository learningSessionRepository;

    @Autowired
    public LearningSessionService(LearningSessionRepository learningSessionRepository) {

        this.learningSessionRepository = learningSessionRepository;
    }

    /**
     * deletes a given learningsession
     *
     * @param learningSession /
     */
    public void delete(LearningSession learningSession) {
        learningSessionRepository.delete(learningSession);
    }

}
