package org.gutismall.marketapi.repository;

import org.gutismall.marketapi.entity.*;
import org.springframework.data.jpa.repository.*;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findUserEntityByAppIdAndEmailIs(String appId, String email);
}
