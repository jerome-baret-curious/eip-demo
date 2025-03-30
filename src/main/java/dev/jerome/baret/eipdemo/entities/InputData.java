package dev.jerome.baret.eipdemo.entities;

import jakarta.persistence.*;

@Entity
public class InputData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "input_id_gen")
    @SequenceGenerator(name = "input_id_gen", sequenceName="input_seq", allocationSize = 1)
    private Long id;

    @Column(name="last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;

    @Column
    private Boolean done;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
