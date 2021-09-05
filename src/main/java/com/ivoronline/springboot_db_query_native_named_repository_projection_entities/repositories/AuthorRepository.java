package com.ivoronline.springboot_db_query_native_named_repository_projection_entities.repositories;

import com.ivoronline.springboot_db_query_native_named_repository_projection_entities.entities.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
  @Query(nativeQuery=true)
  Object[] selectAuthorByName(String name); //THROWS ERROR
}
