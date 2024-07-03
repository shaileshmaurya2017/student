package com.school.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.student.entity.Document;
import com.school.student.entity.Student;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

}