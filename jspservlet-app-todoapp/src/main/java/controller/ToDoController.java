package controller;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import model.Task;
import service.TaskDao;


@WebServlet("/")
public class ToDoController extends HttpServlet{
    List<String> sortByTypes = new ArrayList<String>();
    Map<String,Comparator<Task>> comparators = new HashMap<String,Comparator<Task>>();

    public void init() {
        sortByTypes.add("Priority");
        sortByTypes.add("TaskName");
        sortByTypes.add("Date");
        comparators.put("Priority", Comparator.comparing(Task::getDate));
        comparators.put("TaskName", Comparator.comparing(Task::getTaskName));
        comparators.put("Date", Comparator.comparing(Task::getDate));
        
    }

    private TaskDao dao;
    
    public ToDoController(){
        dao = new TaskDao();
    }



    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
     
        String action = req.getServletPath();

        if (action.contains("/updatetask")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Task task = dao.viewTaskById(id);
            RequestDispatcher dispatcher = req.getRequestDispatcher("add_task.jsp");
            req.setAttribute("task", task);
            dispatcher.forward(req, res);
        }else if (action.contains("/add")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("add_task.jsp");
            req.setAttribute("task", null);
            dispatcher.forward(req, res);
        } else if(action.contains("/delete")){
            int id = Integer.parseInt(req.getParameter("id"));      
            dao.deleteTask(id);
            res.sendRedirect("list");
        } else if(action.contains("/sort")) {
            String sortBy = req.getParameter("sortByType");
            List <Task> taskList = dao.getAllTask();
            req.setAttribute("taskList",
            taskList.stream().sorted(comparators.get(sortBy)).collect(Collectors.toList()));
            req.setAttribute("sortByTypes", sortByTypes);
            RequestDispatcher dispatcher = req.getRequestDispatcher("todo_list.jsp");
            dispatcher.forward(req, res);
        } else {
            List <Task> taskList = dao.getAllTask();
            System.out.println(taskList.size());
            req.setAttribute("taskList", taskList);
            req.setAttribute("sortByTypes", sortByTypes);
            RequestDispatcher dispatcher = req.getRequestDispatcher("todo_list.jsp");
            dispatcher.forward(req, res);
        }

        

    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
     
        String action = req.getServletPath();

        if (action.contains("/insert")) {
            Task task = new Task();
            task.setTaskName(req.getParameter("taskName"));
            task.setFromTime(req.getParameter("fromTime"));
            task.setToTime(req.getParameter("toTime"));
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate taskDate = LocalDate.parse(req.getParameter("date"),df);
            task.setDate(taskDate);        
            dao.addTask(task);
            res.sendRedirect("list");
        } else if (action.contains("/update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Task task = dao.viewTaskById(id);
            task.setTaskName(req.getParameter("taskName"));
            task.setFromTime(req.getParameter("fromTime"));
            task.setToTime(req.getParameter("toTime"));
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate taskDate = LocalDate.parse(req.getParameter("date"),df);
            task.setDate(taskDate);        
            dao.updateTask(task);
            res.sendRedirect("list");
        } else {
            System.out.println("Test");
            List <Task> taskList = dao.getAllTask();
            System.out.println(taskList.size());
            req.setAttribute("taskList", taskList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("todo_list.jsp");
            dispatcher.forward(req, res);
        }

        

    }
}