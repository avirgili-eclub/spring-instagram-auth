package virtech.com.polacore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import virtech.com.polacore.model.domain.entity.InstagramAuthUser;

@Repository
public interface InstagramRepository extends JpaRepository<InstagramAuthUser, Long>,
        JpaSpecificationExecutor<InstagramAuthUser> {

}
