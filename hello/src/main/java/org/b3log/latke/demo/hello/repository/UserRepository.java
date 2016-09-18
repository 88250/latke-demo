package org.b3log.latke.demo.hello.repository;

import org.b3log.latke.repository.AbstractRepository;
import org.b3log.latke.repository.annotation.Repository;
import org.b3log.latke.repository.jdbc.util.JdbcRepositories;

/**
 * User repository.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.1, Oct 11, 2013
 */
@Repository
public class UserRepository extends AbstractRepository {

    public UserRepository() {
        super("user");

        // Generates database tables
        JdbcRepositories.initAllTables();
    }
}
