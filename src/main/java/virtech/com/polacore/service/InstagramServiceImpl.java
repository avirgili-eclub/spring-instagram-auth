package virtech.com.polacore.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import virtech.com.polacore.model.domain.entity.InstagramAuthUser;
import virtech.com.polacore.repository.InstagramRepository;

@Service
@Slf4j
public class InstagramServiceImpl implements InstagramService{
    private final InstagramRepository repository;

    public InstagramServiceImpl(InstagramRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveInstagramCode(String code, String userId) {
        InstagramAuthUser instagramAuthUser = new InstagramAuthUser();
        instagramAuthUser.setInstagramCode(code);
        instagramAuthUser.setUserId(userId);
        this.repository.save(instagramAuthUser);
    }
}
