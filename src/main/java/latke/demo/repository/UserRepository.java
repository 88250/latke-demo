package latke.demo.repository;

import org.b3log.latke.repository.*;
import org.b3log.latke.repository.annotation.Repository;
import org.json.JSONObject;

/**
 * User repository.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.3, Mar 8, 2019
 */
@Repository
public class UserRepository extends AbstractRepository {

    public UserRepository() {
        super("user");
    }

    public JSONObject getByName(final String name) throws RepositoryException {
        return getFirst(new Query().setFilter(new PropertyFilter("name", FilterOperator.EQUAL, name)));
    }
}
