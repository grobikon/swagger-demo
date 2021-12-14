package ru.grobikon.swaggerdemo.service

import org.springframework.stereotype.Service
import ru.grobikon.swaggerdemo.dao.BookRepository
import ru.grobikon.swaggerdemo.model.Book

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun saveBook(book: Book): String {
        bookRepository.save(book)
        return "Книга сохранена id: ${book.id}"
    }

    fun getBook(bookId: Int): Book {
        return bookRepository.getById(bookId)
    }

    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }

    fun deleteBook(bookId: Int) {
        bookRepository.deleteById(bookId)
    }
}