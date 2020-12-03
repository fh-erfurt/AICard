package de.aicard;

import java.util.List;

public class Account
{
    
    
    //Attribute
    private String email;
    private String password;
    private String name;
    private String description;
    private int semester;
    private Enums.Faculty faculty;
    private List<LearnSet> ownedLearningSets;
    private List<LearnSet> favoriteSets;
    
    //Constructor CreateAccount
    public Account(String _email, String _password, String _name, String _description, int _semester, Enums.Faculty _faculty)
    {
        this.email = _email;
        this.password = _password;
        this.name = _name;
        this.description = _description;
        this.semester = _semester;
        this.faculty = _faculty;
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
    
    public Enums.Faculty getFaculty()
    {
        return faculty;
    }
    
    public void setFaculty(Enums.Faculty faculty)
    {
        this.faculty = faculty;
    }
    
    public List<LearnSet> getOwnedLearningSets()
    {
        return ownedLearningSets;
    }
    
    public void setOwnedLearningSets(List<LearnSet> ownedLearningSets)
    {
        this.ownedLearningSets = ownedLearningSets;
    }
    
    public List<LearnSet> getFavoriteSets()
    {
        return favoriteSets;
    }
    
    public void setFavoriteSets(List<LearnSet> favoriteSets)
    {
        this.favoriteSets = favoriteSets;
    }
    

    //Methods
    public void createLearnSet(String _title, String _description /*enum _status,*/)
    {
        ownedLearningSets.add(new LearnSet(_title, _description, /*enum _status,*/ this.faculty));
    }


}
