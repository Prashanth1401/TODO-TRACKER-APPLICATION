package com.todo.repository;

import com.todo.model.Task;
import com.todo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends MongoRepository<User,String> {
   // public  boolean findById(User userId, Task task);
}
