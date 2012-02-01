package models;

import org.apache.commons.codec.digest.DigestUtils;
import play.Play;
import play.db.jpa.Model;
import play.i18n.Messages;
import play.mvc.Scope;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class User extends Model {
    public String username;
    public String passwordHash;

    @OneToOne
    public RiskScope scope;
    
    public static User create(String username,String password) {
        User user = new User();
        user.username = username;
        user.passwordHash = getPasswordHash(password);
        user.scope = RiskScope.create(Messages.get("model.risk-scope.name"));
        return user.save();
    }
    
    public static boolean checkUsernameAvailable(String username) {
        return 0 == User.count("username = ?",username);
    }

    public static User findByUsernameAndPassword(String username, String password) {
        return User.find("username = ? and passwordHash = ?", username, getPasswordHash(password)).first();
    }

    public static User findBySession(Scope.Session session) {
        return findById(Long.valueOf(session.get("user")));
    }

    private static String getPasswordHash(String password) {
        String salt = Play.configuration.getProperty("application.salt");
        return DigestUtils.sha256Hex(salt + password);
    }
}
