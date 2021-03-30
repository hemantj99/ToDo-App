package com.hemant.todoapp.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.hemant.todoapp.db.TodoTask;
import com.hemant.todoapp.db.TodoTaskDAO;
import com.hemant.todoapp.db.TodoTaskRoomDatabase;

import java.util.List;

public class TodoRepository{
    private TodoTaskDAO todoTaskDao;
    private LiveData<List<TodoTask>> allTasks;

    public TodoRepository(Application application){
        TodoTaskRoomDatabase db = TodoTaskRoomDatabase.getDatabase(application);
        todoTaskDao = db.todoTaskDAO();
        allTasks = todoTaskDao.getAllTasks();
    }
    public LiveData<List<TodoTask>> getAllTasks(){
        return allTasks;
    }
    public void insert(final TodoTask todoTask) {
        TodoTaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            todoTaskDao.insertTask(todoTask);
        });
    }
    public void delete(final TodoTask todoTask) {
        TodoTaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            todoTaskDao.deleteTask(todoTask);
        });
    }
    public void update(final TodoTask todoTask) {
        TodoTaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            todoTaskDao.updateTask(todoTask);
        });
    }

}
