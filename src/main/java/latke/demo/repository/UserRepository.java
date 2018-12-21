package latke.demo.repository;

import org.b3log.latke.repository.*;
import org.b3log.latke.repository.annotation.Repository;
import org.json.JSONObject;

import java.util.List;

/**
 * User repository.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.0.0.2, Dec 21, 2018
 */
@Repository
public class UserRepository extends AbstractRepository {

    public UserRepository() {
        super("user");
    }

    public JSONObject getByName(final String name) throws RepositoryException {
        final List<JSONObject> records = getList(new Query().
                setFilter(new PropertyFilter("name", FilterOperator.EQUAL, name)));
        if (records.isEmpty()) {
            return null;
        }

        return records.get(0);
    }
}
