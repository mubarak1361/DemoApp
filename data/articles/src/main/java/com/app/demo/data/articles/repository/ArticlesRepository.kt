package com.app.demo.data.articles.repository

import com.app.demo.data.articles.model.Article
import com.app.demo.data.articles.model.ArticleDetails

interface ArticlesRepository {
    suspend fun getArticles(): List<Article>

    suspend fun getArticleDetails(id: Int): ArticleDetails
}