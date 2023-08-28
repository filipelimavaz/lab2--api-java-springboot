package br.com.ufpb.lab2.repositories;

import br.com.ufpb.lab2.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTagListName(String tagName);
}
