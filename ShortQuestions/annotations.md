# List of Annotations

1. **@Entity**  
   Marks a class as a JPA entity.

2. **@Table**  
   Specifies the table name in the database for the entity.

3. **@Id**  
   Indicates the primary key of the entity.

4. **@GeneratedValue**  
   Defines the primary key generation strategy.

5. **@Column**  
   Maps a field to a specific column in the database.

6. **@OneToOne**  
   Defines a one-to-one relationship between entities.

7. **@OneToMany**  
   Defines a one-to-many relationship between entities.

8. **@ManyToOne**  
   Defines a many-to-one relationship between entities.

9. **@ManyToMany**  
   Defines a many-to-many relationship between entities.

10. **@JoinColumn**  
    Specifies the foreign key column for a relationship.

11. **@Transactional**  
    Manages transaction boundaries in service methods.

12. **@Query**  
    Used to define custom queries in Spring Data JPA.

13. **@Param**  
    Binds method parameters to query parameters.

14. **@Fetch**  
    Specifies how the associated entities should be fetched (e.g., `LAZY`, `EAGER`).

15. **@Transient**  
    Marks a field that should not be persisted to the database.
