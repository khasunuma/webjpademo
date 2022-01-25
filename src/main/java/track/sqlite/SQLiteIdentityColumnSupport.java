package track.sqlite;

import org.hibernate.MappingException;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

/**
 * Override Hibernate's identity column support for adaption to SQLite.
 *
 * <p>This class is based on the following pages. However, both page are partially incorrect.</p>
 * <ul>
 *     <li>https://www.baeldung.com/spring-boot-sqlite</li>
 *     <li>https://fullstackdeveloper.guru/2020/05/01/how-to-integrate-sqlite-database-with-spring-boot/</li>
 * </ul>
 */
public class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public String getIdentitySelectString(String table, String column, int type) throws MappingException {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(int type) throws MappingException {
        return "integer";
    }

}
