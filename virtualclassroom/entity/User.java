	package ksolve.virtualclassroom.entity;
	
	import java.util.ArrayList;
	import java.util.List;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.ManyToMany;
	import jakarta.persistence.Table;
	
	@Entity
	@Table(name = "users")
	
	public class User {
		  @Id
		    @GeneratedValue(strategy = GenerationType.IDENTITY)
		    private Long id;
	
		  private String firstName;
		    private String lastName;
		    private String email;
		    private String password;
		    private String role; // Should be "ADMIN", "STUDENT", or "INSTRUCTOR"
		    private String mobile;
		    
		    @ManyToMany(mappedBy = "enrolledStudents")
		    private List<Classroom> enrolledClasses = new ArrayList<>();
			public User() {
				
			}
			public User(Long id, String firstName, String lastName, String email, String password, String role,
					String mobile, List<Classroom> enrolledClasses) {
				super();
				this.id = id;
				this.firstName = firstName;
				this.lastName = lastName;
				this.email = email;
				this.password = password;
				this.role = role;
				this.mobile = mobile;
				this.enrolledClasses = enrolledClasses;
			}
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getFirstName() {
				return firstName;
			}
			public void setFirstName(String firstName) {
				this.firstName = firstName;
			}
			public String getLastName() {
				return lastName;
			}
			public void setLastName(String lastName) {
				this.lastName = lastName;
			}
			public String getEmail() {
				return email;
			}
			public void setEmail(String email) {
				this.email = email;
			}
			public String getPassword() {
				return password;
			}
			public void setPassword(String password) {
				this.password = password;
			}
			public String getRole() {
				return role;
			}
			public void setRole(String role) {
				this.role = role;
			}
			public String getMobile() {
				return mobile;
			}
			public void setMobile(String mobile) {
				this.mobile = mobile;
			}
			public List<Classroom> getEnrolledClasses() {
				return enrolledClasses;
			}
			public void setEnrolledClasses(List<Classroom> enrolledClasses) {
				this.enrolledClasses = enrolledClasses;
			}
			
	}
