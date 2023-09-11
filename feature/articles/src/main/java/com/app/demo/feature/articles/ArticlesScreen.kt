package com.app.demo.feature.articles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.demo.core.ui.widgets.CoilImage
import com.app.demo.core.ui.widgets.LoadingContainer
import com.app.demo.domain.articles.model.ArticleModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(viewModel: ArticlesViewModel = hiltViewModel(),onItemClick:(Int) -> Unit) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Scaffold(
        topBar = {
            Surface(shadowElevation = 3.dp) {
                TopAppBar(
                    title = { Text("Articles") },
                )
            }
        }
    ) {
        Surface(
             modifier = Modifier
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            when (uiState) {
                is ArticlesUiState.Loading -> LoadingContainer()
                is ArticlesUiState.Error -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: " + uiState.errorMessage)
                }

                is ArticlesUiState.Data -> ArticleScreenWithData(
                    movies = uiState.articles,
                    onItemClick
                )
            }
        }
    }
}

@Composable
fun ArticleScreenWithData(movies: List<ArticleModel>,onItemClick:(Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(movies.size){
            ArticleCard(article = movies[it],onItemClick)
        }
    }
}

@Composable
fun ArticleCard(article: ArticleModel,onItemClick:(Int) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onItemClick(article.id) }
    ) {
        Box {
            Column {
                with(article) {
                    CoilImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        imageUrl = image,
                        contentDescription = title,
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = publishedDate, fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(text = title, color = Color.Black, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(modifier = Modifier.align(alignment = Alignment.End)) {
                            Text(text = author, color = Color.Black, fontSize = 12.sp)
                        }

                    }
                }
            }
        }

    }
}