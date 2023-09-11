package com.app.demo.domain.articles.mapper

import com.app.demo.data.articles.model.Article
import com.app.demo.data.articles.model.ArticleDetails
import com.app.demo.domain.articles.model.ArticleDetailsModel
import com.app.demo.domain.articles.model.ArticleModel

fun Article.transformArticleModel(formattedPublishedDate: String): ArticleModel {
    return ArticleModel(
        id = id,
        title = title,
        image = image,
        publishedDate = formattedPublishedDate
    )
}

fun ArticleDetails.transformArticleDetailsModel(formattedPublishedDate: String): ArticleDetailsModel {
    return ArticleDetailsModel(
        title = title,
        description = description,
        image = image,
        region = region,
        publishedDate = formattedPublishedDate,
        imageCaption = imageCaption,
        imageCopyright = imageCopyright,
        readMoreLink = readMoreLink
    )
}