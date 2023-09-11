package com.app.demo.data.articles.mapper

import com.app.demo.core.local.model.ArticleEntity
import com.app.demo.core.remote.model.RemoteArticle

private fun RemoteArticle.transformArticleEntity(): ArticleEntity {
    val media = multimedia.first { it.format == "Super Jumbo" }
    return ArticleEntity(
        title = title,
        description = description,
        type = itemType,
        image = media.url,
        publishedDate = publishedDate,
        imageCopyright = media.copyright,
        imageCaption = media.caption,
        readMoreLink = url,
        createdDate = createdDate,
        updatedDate = updatedDate,
        region = subSection,
        byline = byline
    )
}

fun List<RemoteArticle>.transformArticleEntities():List<ArticleEntity>{
    return this.map { it.transformArticleEntity() }
}