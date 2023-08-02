package com.brandpack.Taskmanagement.dao;



import com.brandpack.Taskmanagement.model.TasksModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TasksDao extends CrudRepository<TasksModel, Integer> {
    @Query(value = "SELECT c.`complaint`, c.`userid`, u.`name`, u.`email`, u.`phone`, c.`date` FROM `complaint` AS c JOIN `user` AS u ON c.userid = u.id", nativeQuery = true)
    List<Map<String, String>> GetTasks();

    @Query(value = "SELECT c.`complaint`, c.`date` FROM `complaint` AS c JOIN `user` AS u ON c.userid = u.id WHERE c.userid = :id", nativeQuery = true)
    List<Map<String, String>> GetUserTasks(@Param("id") Integer id);
}
