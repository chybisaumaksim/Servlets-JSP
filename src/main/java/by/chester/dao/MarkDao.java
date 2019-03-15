package by.chester.dao;
import by.chester.entities.Mark;
import by.chester.entities.Student;

import java.util.List;

public interface MarkDao {

    void create(Mark mark) throws PersistException;
    List<Mark> getAll () throws PersistException;
    void update (Mark mark) throws PersistException;
    void delete (Mark mark) throws PersistException;
    void close() throws PersistException;
    Mark getById (int id) throws PersistException;
}
