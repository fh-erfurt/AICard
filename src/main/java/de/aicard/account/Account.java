package de.aicard.account;

import de.aicard.learnset.LearnSet;
import de.aicard.enums.Faculty;

import java.util.ArrayList;

public class Account
{
    
    
    //Attribute
    private String email;
    private String password;
    private String name;
    private String description;
    private int semester;
    private Faculty faculty;
    private ArrayList<LearnSet> ownedLearningSets;
    private ArrayList<LearnSet> favoriteSets;
    
    
    //Constructor CreateAccount
    public Account(String _email, String _password, String _name, String _description, int _semester, Faculty _faculty)
    {
        this.email = _email;
        this.password = _password;
        this.name = _name;
        this.description = _description;
        this.semester = _semester;
        this.faculty = _faculty;
        this.ownedLearningSets = new ArrayList<LearnSet>();
        this.favoriteSets = new ArrayList<LearnSet>();
        
    }
    
    //Setter + Getter
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public int getSemester()
    {
        return semester;
    }
    
    public void setSemester(int semester)
    {
        this.semester = semester;
    }
    
    public Faculty getFaculty()
    {
        return faculty;
    }
    
    public void setFaculty(Faculty faculty)
    {
        this.faculty = faculty;
    }
    
    public ArrayList<LearnSet> getOwnedLearningSets()
    {
        return this.ownedLearningSets;
    }
    
    public ArrayList<LearnSet> getFavoriteSets()
    {
        return this.favoriteSets;
    }
    
    /**
     * Setter for ArrayLists are not Required
     */
    
    /*
     * public void setOwnedLearningSets(ArrayList<LearnSet> _NewLearningSets)
     * {
     *   this.ownedLearningSets = _NewLearningSets;
     * }
     *
     *
     * public void setFavoriteSets (ArrayList<LearnSet> (_NewFavoriteSets)
     * {
     *   this.favoriteSets = _NewFavoriteSets;
     * }
     *
     * */
    
    //Advanced Getter, Setter and Delete for ArrayLists
    public LearnSet getOwnedLearningSetByPosition(int _Position)
    {
        return ownedLearningSets.get(_Position);
    }
    
    public void createNewOwnedLearningSetsLearnSet(String _title, String _description)
    {
        ownedLearningSets.add(new LearnSet(_title, _description, this.faculty));
    }
    
    public void deleteFromOwnedLearningSetsByIndex(int _Index)
    {
        this.ownedLearningSets.remove(_Index);
    }
    
    public void deleteFromOwnedLearningSetsLastElement()
    {
        this.ownedLearningSets.remove(this.ownedLearningSets.size() - 1);
    }
    
    public void deleteAllFromOwnedLearningSets()
    {
        this.ownedLearningSets.clear();
    }
    
    
    public LearnSet getFavoriteSetByPosition(int _Position)
    {
        return this.favoriteSets.get(_Position);
    }
    
    public void addNewFavoriteSetsLearnSet(LearnSet favoriteSets)
    {
        this.favoriteSets.add(favoriteSets);
    }
    
    public void deleteFromFavoriteSetsByIndex(int _Index)
    {
        this.favoriteSets.remove(_Index);
    }
    
    public void deleteFromFavoriteSetsLastElement()
    {
        this.favoriteSets.remove(this.favoriteSets.size() - 1);
    }
    
    public void deleteAllFromFavoriteSets()
    {
        this.favoriteSets.clear();
    }
    
    //Methods
    
    
}
