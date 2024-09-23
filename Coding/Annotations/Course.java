package Coding.Annotations;

// Student entity
@Entity
class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Many students can be enrolled in many courses
    @ManyToMany
    @JoinTable(
        name = "student_course", // Join table
        joinColumns = @JoinColumn(name = "student_id"), // Foreign key in join table for Student
        inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key in join table for Course
    )
    private Set<Course> courses = new HashSet<>();

    // Getters and setters
}

// Course entity
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Many courses can have many students
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    // Getters and setters
}