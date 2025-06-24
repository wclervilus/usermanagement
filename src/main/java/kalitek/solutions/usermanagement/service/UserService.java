package kalitek.solutions.usermanagement.service;

import kalitek.solutions.usermanagement.model.Status;
import kalitek.solutions.usermanagement.model.User;
import kalitek.solutions.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, Long> {
    UserRepository repo;
    @Autowired
    public UserService(UserRepository repo) {
        super(repo, "utilisateur");
        this.repo = repo;
    }
    public User disable(User user) {
        user.setStatus(Status.INACTIVE);
        return super.update(user);
    }
}