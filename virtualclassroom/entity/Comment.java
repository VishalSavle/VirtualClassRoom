package ksolve.virtualclassroom.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "lecture_id")  // Foreign key column in comments table
    private Lecture lecture;  // Property name matches the 'mappedBy' in Lecture entity

    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign key column in comments table
    private User user;  // Property name matches the 'mappedBy' in User entity

    // Constructors, getters, and setters

    public Comment() {
    }

    public Comment(String text, Lecture lecture, User user) {
        this.text = text;
        this.lecture = lecture;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
