package com.ivoronline.springboot_db_query_native_named_repository_projection_entities.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivoronline.springboot_db_query_native_named_repository_projection_entities.entities.Author;
import com.ivoronline.springboot_db_query_native_named_repository_projection_entities.entities.Book;
import com.ivoronline.springboot_db_query_native_named_repository_projection_entities.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@RestController
public class MyController {

  @Autowired AuthorRepository authorRepository;
  @PersistenceContext EntityManager entityManager;

  //================================================================
  // SELECT AUTHOR
  //================================================================
  @RequestMapping("SelectAuthor")
  Object[] selectAuthor() throws JsonProcessingException {

    // SELECT AUTHOR USING REPOSITORY THROWS ERROR:
    // "Cannot create TypedQuery for query with more than one return"
    // This is why in this tutorial we are using createNativeQuery() instead
    // Object[] objectArray = (Object[]) query.getSingleResult(); //THROWS ERROR

    // CREATE QUERY
    String select =
      "SELECT author.id AS authorId, name, age, book_id, " +      //Author
      "       book.id   AS bookId, title " +                      //Book
      "FROM   Author " +
      "JOIN   Book ON book_id = book.id " +                       //Relationship
      "WHERE  name = :name ";
    Query  query  = entityManager.createNativeQuery(select, "AuthorBookMapping");
           query.setParameter("name", "John");

    //SELECT AUTHOR
    Object[] objectArray = authorRepository.selectAuthorByName("John");

    //RETURN PERSON
    return objectArray;

  }

}
