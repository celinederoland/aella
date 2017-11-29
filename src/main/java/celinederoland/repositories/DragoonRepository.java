package celinederoland.repositories;

import celinederoland.driver.Sql;
import celinederoland.models.Dragoon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DragoonRepository implements Repository<Dragoon> {

    public Dragoon findOne(int id) {

        try {
            PreparedStatement statement = Sql.connect().prepareStatement("SELECT * FROM dragoon.Names WHERE id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            return new Dragoon(result.getInt("id"), result.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Dragoon> findAll() {

        List<Dragoon> dragoons = new ArrayList<Dragoon>();

        try {

            ResultSet result = Sql.connect().createStatement().executeQuery("SELECT * FROM dragoon.Names");
            while (result.next()) {
                dragoons.add(new Dragoon(result.getInt("id"), result.getString("name")));
            }
            result.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dragoons;
    }

    public Dragoon save(Dragoon object) {

        return (object.id() == 0) ? this.insert(object) : this.update(object);
    }

    public void delete(Dragoon object) {

        try {

            PreparedStatement statement = Sql.connect().prepareStatement(
                    "DELETE FROM dragoon.Names WHERE id = ?"
            );
            statement.setInt(1, object.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dragoon insert(Dragoon object) {

        try {

            PreparedStatement statement = Sql.connect().prepareStatement(
                    "INSERT INTO dragoon.Names VALUES (NULL, ?)", Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, object.name());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            result.next();
            object.id(result.getInt(1));

            statement.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return object;
    }

    private Dragoon update(Dragoon object) {

        try {

            PreparedStatement statement = Sql.connect().prepareStatement("UPDATE dragoon.Names SET name = ? WHERE id = ?");
            statement.setString(1, object.name());
            statement.setInt(2, object.id());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return object;
    }
}
