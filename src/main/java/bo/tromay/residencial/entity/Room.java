package bo.tromay.residencial.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="price")
    private String lastName;

    @Column(name="number")
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "status")
    private boolean status;

}
