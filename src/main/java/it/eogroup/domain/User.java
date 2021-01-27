package it.eogroup.domain;

import lombok.*;
import org.springframework.stereotype.Repository;

/**
 * @author glx
 * @date 2021/01/27/15:20
 */
@Repository
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
}
