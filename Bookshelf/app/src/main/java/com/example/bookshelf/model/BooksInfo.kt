package com.example.bookshelf.model

import kotlinx.serialization.Serializable

//https://www.googleapis.com/books/v1/volumes?q=cicero
//val json = Json { ignoreUnknownKeys = true }
//https://github.com/lixoten/codelab-bookshelf-app-android-kotlin-compose/blob/end/app/src/main/java/com/example/bookshelf/network/BookshelfApiService.kt

@Serializable
data class QueryResponse(
    val items: List<Book>?,
    val totalItems: Int,
    val kind: String,
)

//@Serializable
//data class Book(
//    val id: String,
//    val description: String,
//    val volumeInfo: VolumeInfo,
//    val saleInfo: SaleInfo
//) {
//    // Notes: This works too...
//    fun getPrice() : String {
//        if (saleInfo.listPrice == null) {
//            return ""
//        }
//        return "${saleInfo.listPrice.amount} ${saleInfo.listPrice.currency}"
//    }
//
//}
//
//@Serializable
//data class VolumeInfo(
//    val title: String,
//    val subtitle: String,
//    val description: String,
//    val imageLinks: ImageLinks? = null,
//    val authors: List<String>,
//    val publisher: String,
//    val publishedDate: String,
//) {
//    val allAuthorsx: String
//        get() = allAuthors()
//
//    fun allAuthors() : String {
//        var x= ""
//        for (author in authors) {
//            x += "$author, "
//        }
//        return x.trimEnd(',', ' ')
//    }
//}
//
//@Serializable
//data class ImageLinks(
//    val smallThumbnail: String,
//    val thumbnail: String,
//) {
//    val httpsThumbnail : String
//        get() = thumbnail.replace("http", "https")
//}
//
//
//@Serializable
//data class SaleInfo(
//    val country: String,
//    val isEbook: Boolean,
//    val listPrice: ListPrice?
//) {
//    // Notes: This works...
//    val getPrice2 : String
//        get() = "${listPrice?.amount ?: "N/A"} ${listPrice?.currency ?: "N/A"}"
//
//}
//
//@Serializable
//data class ListPrice(
//    val amount: Float?,
//    val currency: String? = ""
//)

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo,
)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val publisher: String,
    val industryIdentifiers: List<IndustryIdentifiers>,
    val categories: List<String>,
    val imageLinks: ImageLinks
)

@Serializable
data class IndustryIdentifiers(
    val type: String,
    val identifier: String
)

@Serializable
data class ImageLinks(
    val thumbnail: String
)