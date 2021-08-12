package de.aicard.services;

import de.aicard.domains.card.Card;
import de.aicard.domains.card.CardStatus;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.domains.learnset.LearnSetAbo;
import de.aicard.domains.learnset.LearningSession;
import de.aicard.storages.CardListRepository;
import de.aicard.storages.CardStatusRepository;
import de.aicard.storages.LearnSetAboRepository;
import de.aicard.storages.LearningSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO : jemand muss am ENDe alle TODOs und sout löschen pls *_*
@Service
public class LearningSessionService
{
    // TODO : befülle mich pls
    @Autowired
    LearnSetAboRepository learnSetAboRepository;

    @Autowired
    CardStatusRepository cardStatusRepository;

    @Autowired
    LearningSessionRepository learningSessionRepository;

    public void deleteLearningSessionsByLearnSet(LearnSet learnSet){
        List<LearnSetAbo> learnSetAbos = learnSetAboRepository.findAllByLearnSetId(learnSet.getId());
        for (LearnSetAbo learnSetAbo : learnSetAbos) {
            LearningSession learningSession = learnSetAbo.getLearningSession();
            System.out.println("learningSession1: "+learningSession);
            learnSetAbo.setLearningSession(null);
            System.out.println("learningSession2: "+learningSession);
            learnSetAboRepository.save(learnSetAbo);
            System.out.println("learningSession3: "+learningSession);
            if(learningSession != null){
                learningSession.setCardStatusList(null);
                learningSessionRepository.delete(learningSession);
                System.out.println("learningSession4: "+learningSession);
            }

        }
    }
}
