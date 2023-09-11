package com.app.demo.data.articles.mapper

import com.app.demo.core.local.model.ArticleEntity
import com.app.demo.data.articles.model.Article
import com.app.demo.data.articles.model.ArticleDetails

fun ArticleEntity.transformArticle(): Article {
    return Article(
        id = id,
        title = title,
        author = byline,
        image = image,
        publishedDate = publishedDate
    )
}

fun ArticleEntity.transformArticleDetails(): ArticleDetails {
    return ArticleDetails(
        title = title,
        description = description,
        author = byline,
        image = image,
        region = region,
        publishedDate = publishedDate,
        imageCaption = imageCaption,
        imageCopyright = imageCopyright,
        readMoreLink = readMoreLink
    )
}