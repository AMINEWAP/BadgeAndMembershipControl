package edu.miu.cs.badgeandmembershipcontrol.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String description;

    @Enumerated
    @ElementCollection
    @ToString.Include
    private Set<Role> roles = new HashSet<>();

//    @JsonBackReference(value = "location")
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="plan_location", joinColumns = {@JoinColumn(name="plan_id")},inverseJoinColumns = {@JoinColumn(name="location_id")})
    @ToString.Exclude
    private List<Location> locations = new ArrayList<>();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return Objects.equals(id, plan.id) && Objects.equals(name, plan.name) && Objects.equals(description, plan.description) && Objects.equals(roles, plan.roles) && Objects.equals(locations, plan.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    public Plan(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
