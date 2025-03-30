package dev.jerome.baret.eipdemo.entities;

import jakarta.persistence.*;

@Entity
public class ResultData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_id_gen")
    @SequenceGenerator(name = "result_id_gen", sequenceName = "result_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "input_id")
    private InputData input;

    @Column
    private String initials;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InputData getInput() {
        return input;
    }

    public void setInput(InputData input) {
        this.input = input;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
}
