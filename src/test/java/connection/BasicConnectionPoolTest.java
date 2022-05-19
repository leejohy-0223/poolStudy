package connection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicConnectionPoolTest {

    BasicConnectionPool cp;

    @BeforeEach
    void setUp() throws SQLException {
        cp = BasicConnectionPool.create("jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

    @Test
    void whenCalledgetConnection_thenCollect() throws SQLException {
        assertTrue(cp.getConnection().isValid(1));
    }

    @Test
    void count() throws SQLException {
        assertEquals(cp.getConnectionPool().size(), 10);
    }

    @Test
    void exception_occur() {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                // 스레드에게 작업 시킬 내용
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executorService;

                Connection connection = cp.getConnection();
                // System.out.println( + " is get connection :  " + connection);
            };

            executorService.execute(runnable);
        }
    }
}
