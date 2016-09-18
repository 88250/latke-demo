package org.b3log.latke.demo.hello.service;

import javax.inject.Inject;
import org.b3log.latke.demo.hello.repository.UserRepository;
import org.b3log.latke.logging.Level;
import org.b3log.latke.logging.Logger;
import org.b3log.latke.repository.RepositoryException;
import org.b3log.latke.repository.annotation.Transactional;
import org.b3log.latke.service.annotation.Service;
import org.json.JSONObject;

/**
 * User service.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.0, Jul 2, 2013
 */
@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Inject
    private UserRepository userRepository;

    @Transactional
    public void saveUser(final String name, final int age) {
        final JSONObject user = new JSONObject();
        user.put("name", name);
        user.put("age", age);

        String userId;

        try {
            userId = userRepository.add(user);
        } catch (final RepositoryException e) {
            LOGGER.log(Level.ERROR, "Saves user failed", e);

            // Throws an exception to rollback transaction
            throw new IllegalStateException("Saves user failed");
        }

        LOGGER.log(Level.INFO, "Saves a user successfully [userId={0}]", userId);
    }
}
