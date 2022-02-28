package service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import model.*;
import utility.ConnectionManager;
import utility.ExceptionPrinter;

public class TaskDao {

    private static final String INSERT_TODO_QUERY = "INSERT INTO Task(name, starttime, endtime, taskdate) VALUES (?, ?, ?, ?);";
    private static final String UPDATE_TODO_QUERY = "UPDATE Task SET name=?, starttime=?, endtime=?, taskdate=? WHERE taskId=?;" ;
    private static final String DELETE_TODO_QUERY = "DELETE FROM Task WHERE taskId=?;" ;
    private static final String SELECT_TODO_QUERY = "SELECT * FROM Task WHERE taskId=? ;" ;
    private static final String SELECT_ALL_TODO_QUERY = "SELECT * FROM Task order by taskdate,starttime,endtime DESC;" ;
    
    public void addTask(Task task){

        try(Connection con = ConnectionManager.getConnection();
          PreparedStatement ps = con.prepareStatement(INSERT_TODO_QUERY);
        ){
            ps.setString(1, task.getTaskName());
            ps.setString(2, task.getFromTime());
            ps.setString(3, task.getToTime());
            ps.setDate(4, java.sql.Date.valueOf(task.getDate()));
            ps.executeUpdate();
        }catch(SQLException ex){
            ExceptionPrinter.printSqlException(ex);
        }

    }
    public boolean updateTask(Task task){
        boolean rowUpdated=false;
        try(Connection con = ConnectionManager.getConnection();
          PreparedStatement ps = con.prepareStatement(UPDATE_TODO_QUERY);
        ){
            ps.setString(1, task.getTaskName());
            ps.setString(2, task.getFromTime());
            ps.setString(3, task.getToTime());
            ps.setDate(4, java.sql.Date.valueOf(task.getDate()));
            ps.setInt(5, task.getTaskId());
           
            rowUpdated =  ps.executeUpdate()>0;

        }catch(SQLException ex){
            ExceptionPrinter.printSqlException(ex);
        }
        return rowUpdated;

    }
    public Task viewTaskById(int taskId){
        Task task = null;
        try(Connection con = ConnectionManager.getConnection();
          PreparedStatement ps = con.prepareStatement(SELECT_TODO_QUERY);
        ){
            ps.setInt(1, taskId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setTaskName(rs.getString("name"));
                task.setFromTime(rs.getString("endtime"));
                task.setToTime(rs.getString("starttime"));
                task.setDate(rs.getDate("taskdate").toLocalDate());                
            }

        }catch(SQLException ex){
            ExceptionPrinter.printSqlException(ex);
        }
       return task;
     
    }

    public List<Task> getAllTask(){
        List<Task> tasks = new ArrayList<Task>();

        try(Connection con = ConnectionManager.getConnection();
          PreparedStatement ps = con.prepareStatement(SELECT_ALL_TODO_QUERY);
        ){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Task task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setTaskName(rs.getString("name"));
                task.setFromTime(rs.getString("starttime"));
                task.setToTime(rs.getString("endtime"));
                task.setDate(rs.getDate("taskdate").toLocalDate());
                tasks.add(task);                
            }
        }catch(SQLException ex){
            ExceptionPrinter.printSqlException(ex);
        }
       return tasks;
     
    }

    public boolean deleteTask(int taskId){
        boolean rowDeleted=false;
        try(Connection con = ConnectionManager.getConnection();
          PreparedStatement ps = con.prepareStatement(DELETE_TODO_QUERY);
        ){
            ps.setInt(1, taskId);
            rowDeleted = ps.executeUpdate()>0;

        }catch(SQLException ex){
            ExceptionPrinter.printSqlException(ex);
        }
     return rowDeleted;

    }
}
