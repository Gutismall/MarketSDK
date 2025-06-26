package org.gutismall.marketapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Users")
@NoArgsConstructor
public class UserEntity {
    private String email;
    @Id
    private String appId;
}
