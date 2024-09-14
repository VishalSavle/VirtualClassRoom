package ksolve.virtualclassroom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "unit_id")  // Foreign key column in sessions table
    private Unit unit;  // Property name matches the 'mappedBy' in Unit entity

    @ManyToOne
    @JoinColumn(name = "class_id")  // Foreign key column in sessions table
    private Classroom classroom;  // Property name matches the 'mappedBy' in Classroom entity

    // Constructors, getters, and setters

    public Session() {
    }

    public Session(String title, Unit unit, Classroom classroom) {
        this.title = title;
        this.unit = unit;
        this.classroom = classroom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
