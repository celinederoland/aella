package celinederoland.repositories;

import celinederoland.driver.Sql;
import celinederoland.models.Figure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FigureRepository implements Repository<Figure> {

    @Override
    public Figure findOne(Long id) {

        try {
            PreparedStatement statement = Sql.connect().prepareStatement("SELECT * FROM figures.Figures WHERE id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if(result.next())
            return (Figure) (new Figure()).id(result.getLong("id"))
                    .name(result.getString("name"))
                    .description(result.getString("description"))
                    .uri(result.getString("uri"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Figure> findAll() {
        List<Figure> figures = new ArrayList<Figure>();

        try {

            ResultSet result = Sql.connect().createStatement().executeQuery("SELECT * FROM figures.Figures");
            while (result.next()) {
                figures.add(
                        (Figure) (new Figure()).id(result.getLong("id"))
                                .name(result.getString("name"))
                                .description(result.getString("description"))
                                .uri(result.getString("uri"))
                );
            }
            result.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return figures;
    }


    @Override
    public Figure save(Figure object) {

        return (object.id() == 0) ? this.insert(object) : this.update(object);
    }

    private Figure insert(Figure object) {

        try {

            PreparedStatement statement = Sql.connect().prepareStatement(
                    "INSERT INTO figures.Figures VALUES (NULL, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, object.uri());
            statement.setString(2, object.description());
            statement.setString(3, object.name());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            result.next();
            object.id((long) result.getInt(1));
            statement.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return object;
    }

    private Figure update(Figure object) {

        try {

            PreparedStatement statement = Sql.connect().prepareStatement(
                    "UPDATE figures.Figures SET uri = ?, description = ?, name = ? WHERE id = ?");
            statement.setString(1, object.uri());
            statement.setString(2, object.description());
            statement.setString(3, object.name());
            statement.setLong(4, object.id());
            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return object;
    }
    @Override
    public void delete(Figure object) {

        try {

            PreparedStatement statement = Sql.connect().prepareStatement(
                    "DELETE FROM figures.Figures WHERE id = ?"
            );
            statement.setLong(1, object.id());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
