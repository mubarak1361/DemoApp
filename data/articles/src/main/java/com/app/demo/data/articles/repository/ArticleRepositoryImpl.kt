package com.app.demo.data.articles.repository

import android.util.Log
import com.app.demo.core.local.dao.ArticleDao
import com.app.demo.core.remote.RemoteDataSource
import com.app.demo.data.articles.mapper.transformArticle
import com.app.demo.data.articles.mapper.transformArticleDetails
import com.app.demo.data.articles.mapper.transformArticleEntities
import com.app.demo.data.articles.model.Article
import com.app.demo.data.articles.model.ArticleDetails
import java.io.IOException
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val remoteDataSource: RemoteDataSource): ArticlesRepository {
    override suspend fun getArticles(): List<Article> {
        try {
            val articles = remoteDataSource.getTopStoriesByWorld()
            articleDao.deleteAll()
            articleDao.insertAll(articles.results.transformArticleEntities())
        }catch (e: IOException){
            Log.d("IOException",e.message.toString())
        }
        return articleDao.getAll().filter { it.type == "Article" }.map { it.transformArticle() }
    }

    override suspend fun getArticleDetails(id:Int): ArticleDetails {
       return articleDao.findById(id).transformArticleDetails()
    }
}