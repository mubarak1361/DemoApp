package com.app.demo.feature.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.app.demo.domain.articles.model.ArticleDetailsModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(articleId: Int,readMoreClick:(String)-> Unit, onBackPressed: () -> Unit, viewModel: ArticleDetailViewModel = hiltViewModel()) {
    viewModel.getArticleDetailsById(articleId)
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Scaffold(topBar = {
        Surface(shadowElevation = 3.dp) {
            TopAppBar(
                title = { Text("Details") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    }){
        Surface(
            modifier = Modifier
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            when(uiState){
                is ArticleDetailsUiState.Loading -> LoadingContainer()
                is ArticleDetailsUiState.Error -> Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error: " + uiState.errorMessage)
                }
                is ArticleDetailsUiState.Data -> ArticleDetails(uiState.articleDetails,readMoreClick)
            }
        }
    }
}

@Composable
fun ArticleDetails(articleDetailsModel: ArticleDetailsModel,readMoreClick:(String) -> Unit) {
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        with(articleDetailsModel) {
            Text(text = title, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(24.dp))
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                imageUrl = image,
                contentDescription = title,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = imageCaption, color = Color.Gray,fontWeight = FontWeight.Normal, fontSize = 12.sp,lineHeight = 16.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Region: $region",color = Color.Black,fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = description,color = Color.Black,fontWeight = FontWeight.Normal, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = author,color = Color.Gray,fontWeight = FontWeight.Normal, fontSize = 14.sp)
            Text(text = "Published on $publishedDate",color = Color.Gray,fontWeight = FontWeight.Normal, fontSize = 14.sp)
            Column(verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Copyright of image: $imageCopyright",
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))
                    Button(modifier = Modifier.align(Alignment.End), onClick = {
                        val encodedUrl = URLEncoder.encode(readMoreLink, StandardCharsets.UTF_8.toString())
                        readMoreClick(encodedUrl) }) {
                        Text(text = "Read More")
                }
            }

        }
    }
}