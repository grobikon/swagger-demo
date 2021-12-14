package ru.grobikon.swaggerdemo.dao

import org.springframework.data.jpa.repository.JpaRepository
import ru.grobikon.swaggerdemo.model.Book

interface BookRepository: JpaRepository<Book, Int> {
}