* @Entity: Mark a class as JPA entity. And Spring Data JPA uses this annotation to map the class to the corresponding table in the database.
* @Table: Specifites the anem of the table int the database to which this entity will be mapped. It is used when the entity calss doesn't match the table name (e.g.@Table(name = "{table_name}"))
* @Id: Marks a field as the primary key of the entity. It is required in every JPA entity to identify the primary key field.
* @GeneratedValue: It defines the strategy for how the primary key should be generated.
* @Column: Customizes the column mapping for a specific field in an entity like specify column details like name, length, or whether the column is nullable.
* @ManyToOne, @OneToMany, @OneToOne, @ManyToMany: Specifies how entities relate to each other in the database
* @JoinColumn: Specifies the column that joins two tables in a foreign key relationship.Used in combination with annotations like @ManyToOne or @OneToOne to specify the foreign key.
* @Repository: Marks a class as a repository. It indicates that the class provides mechanisms for data access, often used in data access objects (DAO).
* @Query: Used to define custom JPQL or SQL queries for specific methods in a repository. Allows custom database queries when standard method names in JpaRepository aren't sufficient. 
* @Modifying: Marks a query method that modifies data in the database (like an UPDATE or DELETE query).Used in combination with @Query to specify that the query performs an update or delete operation.
* @Transactional: It ensures that a sequence of operations is handled as a single transaction. Used for manages transactions at the method or class level.
* @EntityListeners: Specifies a class that listens for entity lifecycle events (e.g., pre-persist, post-persist).
* @PrePersist, @PostPersist, @PreUpdate, @PostUpdate: They handel tasks like auditing, logging, or validation before or after a particular lifecycle event. They are used to trigger events during the lifecycle of an entity
* @Version: Marks a field as a version field
* @Lob: Marks a field for storing large objects, such as binary data (BLOB) or text (CLOB).
