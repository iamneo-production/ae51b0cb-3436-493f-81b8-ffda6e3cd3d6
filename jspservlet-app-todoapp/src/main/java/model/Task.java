package model;

import java.time.LocalDate;

public class Task{
  private String taskName, fromTime, toTime;
  private LocalDate date;
  private int taskId;
public String getTaskName() {
    return taskName;
}
public void setTaskName(String taskName) {
    this.taskName = taskName;
}
public String getFromTime() {
    return fromTime;
}
public void setFromTime(String fromTime) {
    this.fromTime = fromTime;
}
public String getToTime() {
    return toTime;
}
public void setToTime(String toTime) {
    this.toTime = toTime;
}
public LocalDate getDate() {
    return date;
}
public void setDate(LocalDate date) {
    this.date = date;
}
public int getTaskId() {
    return taskId;
}
public void setTaskId(int taskId) {
    this.taskId = taskId;
}
}