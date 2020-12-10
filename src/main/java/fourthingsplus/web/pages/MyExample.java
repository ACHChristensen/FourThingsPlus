package fourthingsplus.web.pages;

import fourthingsplus.api.FourThingsPlus;
import fourthingsplus.web.BaseServlet;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@WebServlet("/example")
public class MyExample extends BaseServlet {

    public static class AssignementManagaer {
        public List<String> assignments = new ArrayList<String>();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public synchronized String popAssignment() {
            if (assignments.isEmpty()) {
                String ass = assignments.get(0);
                assignments.remove(0);
                return ass;
            } else {
                throw new RuntimeException("WAHHAT!");
            }
        }
    }

    AssignementManagaer ass = new AssignementManagaer();
    private final AtomicInteger nextId = new AtomicInteger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ourid = nextId.addAndGet(1);

        LoggerFactory.getLogger(MyExample.class).info("Our id: {}", ourid);
    }
}
