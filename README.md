Project packaging structure (model,exception,service,controller,repository)
created employee entity class inside model package 
In side employee class used @Setter, @Getter, @AllArgsConstructor, @NoArgsConstructor, @Builder, @Entity, @Table, @Id, @GeneratedValue
@Data 
- It reduces the amount of repetitive code you need to write for simple Java classes.
- Boilerplate code refers to sections of code that have to be written repeatedly 
- It's particularly useful for DTOs (Data Transfer Objects) and POJOs (Plain Old Java Objects) where you just need to store data and don't need complex logic.
              import lombok.Builder;
              import lombok.Data;
              
              @Data
              @Builder
              public class User {
                  private String username;
                  private String email;
                  private int age;
              }
@Builder
- This provides a concise and readable way to create instances of the User class, especially when dealing with classes with many fields.
   
              User user = User.builder()
                                      .username("john")
                                      .email("john@example.com")
                                      .age(25)
                                      .build();
@Entity annotation marks a class as a persistent Java entity. This means that instances of this class are mapped to corresponding records in a database table.
@GeneratedValue(strategy = GenerationType.IDENTITY) indicates that the primary key values for entities will be automatically generated using an identity column in the databas and unique data.


**************************************************************************************************************************************************************************************************
Steps to write Junit test cases
  //Junit test for
    @DisplayName("unit test for")
    @Test
     public void give_when_then(){
        //give - pre-condition or set up
        //when - action or behaviour of the test case
        //then - verify the output
        
     }

