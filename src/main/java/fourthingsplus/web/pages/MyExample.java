package fourthingsplus.web.pages;

import fourthingsplus.api.FourThingsPlus;
import fourthingsplus.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
