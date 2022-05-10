package com.zfl.demo.domain.task;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    boolean existsByGroupNameAndName(String groupName, String name);
}
