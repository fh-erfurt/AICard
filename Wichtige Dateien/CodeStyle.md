# CodeStyle aiCard
- The gaol of this document is to ensure a consistent CodeStyle throughout the project
- Violations of this CodyStyle are allowed if it enhances readability

## Naming conventions
- All names should be written in English
- Class names must be PascalCase <br>
    ```
    ClassName, CardList
    ```
- Variable names must be camelCase <br>
    ```
    int numberOfCard;   // not: int NumberOfcard;
    String carName;     // not: String Carname;
    ```
- Underscores are banned from all names
    ```
    // INCORRECT
    int number_of_cars;
    String car_Name;
  ```
- Method names must be camelCase
    ```
    getNumberOfCars();          // not: GetNumberOfCars();
    calculateEverageSales();    // not: Calculateeveragesales();
    ``` 
## Files and Folders
- Java file names must be PascalCase
    ```
    Car.java        // not: car.java
    SportsCar.java  // not: sportsCard.java
    ``` 
- Java test files names must be Pascal Case
    ```
    CarTest.java        // not: testcar.java
    SportsCarTest.java  // not: TestsportsCardtesting.java
    ``` 
    - their location must be in the same package structure in the test branch as the implementation
    
- package names must be lower case
    - packages in java and test folder must mirror each other

## Layout
- Block Layout for curly brackets
    ```
    public class TestClass
    {
        if (1 < 0)
        {
            // Do something
        }
    
        for (int index = 0; index < 10 ; index++)
        {
            // Do something
        }
    
    }
    ```

## Comments
- every class must have a comment with its description and its author in JavaDocStyle
- every membervariable must have a comment explaining its use in JavaDocStyle
- complex methods must be commented in JavaDocStyle 
- short comments in methods are appreciated 
- TODO comments are appreciated
    ```
    /**
    * JavaDocStyle comment example
    * 
    * @auther: 
    */
    
    // short comment example
    
    
    // TODO: example
    ```
