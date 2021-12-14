package ru.grobikon.swaggerdemo.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import ru.grobikon.swaggerdemo.model.Book
import ru.grobikon.swaggerdemo.service.BookService

@RestController
@RequestMapping("/book")
@Api(description = "Контроллер для иллюстрации работы Swagger")
class BookController(
    val bookService: BookService
) {

    @PostMapping("/save")
    @ApiOperation(value = "Сохранение книги")
    fun saveBook(@RequestBody book: Book): String {
        return bookService.saveBook(book)
    }

    @GetMapping("/searchBook/{bookId}")
    @ApiOperation(value = "Поиск книги по Id")
    fun getBook(@PathVariable bookId: Int): Book{
        return bookService.getBook(bookId)
    }

    @DeleteMapping("/deleteBook/{bookId}")
    @ApiOperation(value = "Удаление книги")
    fun deleteBook(@PathVariable bookId: Int): List<Book>{
        bookService.deleteBook(bookId)
        return bookService.getBooks()
    }

    @GetMapping("/getAllBooks")
    @ApiOperation(value = "Передаём все книги")
    fun getAllBooks(): List<Book> {
        return bookService.getBooks()
    }
}