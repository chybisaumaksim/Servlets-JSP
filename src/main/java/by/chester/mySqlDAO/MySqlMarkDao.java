package by.chester.mySqlDAO;

import by.chester.dao.MarkDao;
import by.chester.entities.Mark;
import by.chester.dao.PersistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlMarkDao implements MarkDao {

    private Connection connection;
    private PreparedStatement statementCreate;
    private PreparedStatement statementUpdate;
    private PreparedStatement statementDelete;
    private PreparedStatement statementSelectID;

    protected MySqlMarkDao(Connection connection) throws PersistException {
        try {
            this.connection = connection;
            statementCreate = connection.prepareStatement(getCreateQuery(), PreparedStatement.RETURN_GENERATED_KEYS);
            statementUpdate = connection.prepareStatement(getUpdateQuery());
            statementDelete = connection.prepareStatement(getDeleteQuery());
            statementSelectID = connection.prepareStatement(SelectIdQuery());
        } catch (SQLException e) {
            throw new PersistException("Ошибка при создании prepareStatement в классе " + getClass(), e);
        }
    }

    public void create(Mark mark) throws PersistException {
        ResultSet generatedId = null;
        try {
            prepareStatementForInsert(statementCreate, mark);
            statementCreate.executeUpdate();
            generatedId = statementCreate.getGeneratedKeys();
            if (generatedId.next()) {
                int id = generatedId.getInt(1);
                statementSelectID.setInt(1, id);
            }
        } catch (Exception e) {
            throw new PersistException(" Невозможно записать данные в БД", e);
        } finally {
            try {
                if (generatedId != null) {
                    generatedId.close();
                }
            } catch (SQLException e) {
                throw new PersistException("Ошибка закрытия потока", e);
            }
        }
    }

    public List<Mark> getAll() throws PersistException {
        ArrayList list = new ArrayList();
        ResultSet rs = null;
        try {
            PreparedStatement stm = connection.prepareStatement(getSelectAll());
            rs = stm.executeQuery();
            while (rs.next()) {
                Mark m = new Mark();
                m.setId(rs.getInt(1));
                m.setStudentId(rs.getInt(2));
                m.setLessonId(rs.getInt(3));
                m.setMark(rs.getInt(4));
                list.add(m);
            }
        } catch (SQLException e) {
            throw new PersistException("Ошибка Sql запроса", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new PersistException("Ошибка закрытия потока", e);
            }
        }
        return list;
    }

    public void update(Mark mark) throws PersistException {
        try {
            prepareStatementForUpdate(statementUpdate, mark);
            statementUpdate.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException("Ошибка Sql запроса", e);
        }
    }

    public void delete(Mark mark) throws PersistException {
        try {
            prepareStatementForDelete(statementDelete, mark);
            statementDelete.executeUpdate();
        } catch (SQLException e) {
            throw new PersistException("Ошибка Sql запроса", e);
        }
    }

    public Mark getById(int id) throws PersistException {
        Mark mark = new Mark();
        ResultSet rs = null;
        try {
            PreparedStatement stm = connection.prepareStatement(SelectIdQuery());
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                mark.setId(rs.getInt(1));
                mark.setStudentId(rs.getInt(2));
                mark.setLessonId(rs.getInt(3));
                mark.setMark(rs.getInt(4));
            }
        } catch (SQLException e) {
            throw new PersistException("Ошибка обращения к БД ", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new PersistException("Ошибка закрытия потока", e);
            }
        }
        return mark;
    }

    private String getSelectAll() {
        return "SELECT id, student_Id, lesson_Id, mark FROM MARK ";
    }

    private String getCreateQuery() {
        return "INSERT INTO MARK (student_Id, lesson_Id, mark) VALUES (?, ?, ?) ; ";
    }

    private String getUpdateQuery() {
        return "UPDATE MARK SET MARK = ? WHERE id = ? " ;
    }

    private String getDeleteQuery() {
        return "DELETE FROM MARK WHERE id= ?; ";
    }

    private String SelectIdQuery() {
        return "SELECT id, student_Id, lesson_Id, mark FROM MARK WHERE ID = ? ; ";
    }

    private void prepareStatementForInsert(PreparedStatement statement, Mark object) throws PersistException {
        try {
            statement.setInt(1, object.getStudentId());
            statement.setInt(2, object.getLessonId());
            statement.setInt(3, object.getMark());
        } catch (SQLException e) {
            throw new PersistException("Ошибка получения prepareStatementForInsert", e);
        }
    }

    private void prepareStatementForUpdate(PreparedStatement statement, Mark object) throws PersistException {
        try {
            statement.setInt(2, object.getId());
            statement.setInt(1, object.getMark());
        } catch (Exception e) {
            throw new PersistException("Ошибка получения prepareStatementForUpdate", e);

        }
    }

    private void prepareStatementForDelete(PreparedStatement statement, Mark object) throws PersistException {
        try {
            statement.setInt(1, object.getId());
        } catch (Exception e) {
            throw new PersistException("Ошибка получения prepareStatementForDelete", e);

        }
    }

    public void close() throws PersistException {
        try {
            if (statementDelete != null)
                statementDelete.close();
        } catch (SQLException e) {
            throw new PersistException("Ошибка закрытия statementDelete ", e);
        }
        try {
            if (statementCreate != null)
                statementCreate.close();
        } catch (SQLException e) {
            throw new PersistException("Ошибка закрытия statementCreate ", e);
        }
        try {
            if (statementUpdate != null)
                statementUpdate.close();
        } catch (SQLException e) {
            throw new PersistException("Ошибка закрытия statementUpdate ", e);
        }
        try {
            if (statementSelectID != null)
                statementSelectID.close();
        } catch (SQLException e) {
            throw new PersistException("Ошибка  закрытия statementSelectID ", e);
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new PersistException("Ошибка закрытия Connection ", e);
        }
    }
}

