package pl.rybak.dawid.springtest;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object var2) throws HibernateException {
        Connection connection = session.connection();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select nextval('book_sequence')");
            if (rs.next()) {
                long id = rs.getLong(1);
                return BookId.of(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new IllegalStateException("Could not fetch new bookId");
    }

}