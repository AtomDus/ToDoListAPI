package be.bdus.entities;

import be.bdus.entities.bases.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter
@ToString@EqualsAndHashCode(callSuper = false)
public class Task extends BaseEntity<Long> {

    @Setter
    @Column
    private String title;

    @Setter
    @Column
    private String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Task() {}

    public Task(long l, String oldTitle, String oldDescription) {
        this.title = oldTitle;
        this.description = oldDescription;
    }
}
