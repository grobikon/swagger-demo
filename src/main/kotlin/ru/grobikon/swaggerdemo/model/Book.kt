package ru.grobikon.swaggerdemo.model

import io.swagger.annotations.ApiModelProperty
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Book(
    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "автоматическая генерация id", example = "1")
    var id: Int? = null,
    @ApiModelProperty(notes = "Название книги", example = "Test 1")
    var nameBook: String,
    @ApiModelProperty(notes = "Цена книги", example = "25.78")
    var price: Double
): Serializable