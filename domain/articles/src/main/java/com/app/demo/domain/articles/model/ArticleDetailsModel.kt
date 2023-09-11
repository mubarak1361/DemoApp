package com.app.demo.domain.articles.model

data class ArticleDetailsModel(val title:String,
                          val description: String,
                          val author: String,
                          val image: String,
                          val region: String,
                          val publishedDate: String,
                          val imageCaption: String,
                          val imageCopyright: String,
                          val readMoreLink: String)
