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
    @ApiModelProperty(value = "автоматическая генерация id")
    var id: Int? = null,
    @ApiModelProperty(value = "Название книги")
    var nameBook: String,
    @ApiModelProperty(value = "Цена книги")
    var price: Double
): Serializable