package ksolve.virtualclassroom.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(
        name = "user_classes",
        joinColumns = @JoinColumn(name = "class_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> enrolledStudents = new ArrayList<>();

    // Constructors, getters, and setters

    public Classroom() {
    }

	public Classroom(Long id, String name, String description, List<Session> sessions, List<User> enrolledStudents) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.sessions = sessions;
		this.enrolledStudents = enrolledStudents;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<User> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(List<User> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
    
    
}
