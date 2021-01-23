package de.aicard.learnset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LearnSetTest
{
    
    @Test
    public void testingEvaluations()
    {
        //before
        LearnSet testLearnSet = new LearnSet();
        int evaluation1 = 4;
        int evaluation2 = 2;
        int evaluation3 = 3;
        
        //Testing adding Evaluations
        testLearnSet.addEvaluation(evaluation1);
        Assertions.assertEquals(evaluation1, testLearnSet.getEvaluation());
    
        testLearnSet.addEvaluation(evaluation2);
        Assertions.assertEquals((double)(evaluation1 + evaluation2) / 2, testLearnSet.getEvaluation());
        
        testLearnSet.addEvaluation(evaluation3);
        Assertions.assertEquals((double)(evaluation1 + evaluation2 + evaluation3) / 3, testLearnSet.getEvaluation());
        
        //Testing deleting Evaluations
        testLearnSet.deleteEvaluation(evaluation1);
        Assertions.assertEquals((double)(evaluation2 + evaluation3) / 2, testLearnSet.getEvaluation());
        
    }
    
    @Test
    public void testingAdminList()
    {
    
    }
    
    @Test
    public void testingCommentList()
    {
    
    }


}
