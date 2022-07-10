package com.ferran.Clustag.repositories;

import com.ferran.Clustag.models.TaskModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Repository
public interface TaskRepository extends CrudRepository<TaskModel, Long> {
    @Query("select t from TaskModel t where t.createdAt >= :twoMinutes")
    ArrayList<TaskModel> getByDate(@Param("twoMinutes") Date twoMinutes);
}
