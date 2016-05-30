package ejb.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "student_group")
@NamedQuery(name = "Group.asList", query = "SELECT group FROM Group group")
public class Group {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private @Getter int groupId;

    @Column(name = "name_group")
    private @Getter String nameGroup;

    @Column(name = "curator")
    private @Getter String curator;

    @Column(name = "speciality")
    private @Getter String speciality;
}