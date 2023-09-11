package com.app.demo.ui.navigation

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.app.demo.feature.articles.ArticlesScreen
import com.app.demo.feature.details.ArticleDetailScreen
import com.app.demo.ui.route.RouteArgument
import com.app.demo.ui.route.RouteName
import com.app.demo.ui.widgets.AppAndroidWebView

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = RouteName.articles) {
        composable(RouteName.articles){
            ArticlesScreen(onItemClick = { articleId -> navController.navigate(route = "${RouteName.details}/$articleId") })
        }
        composable("${RouteName.details}/{${RouteArgument.articleId}}",
            arguments = listOf(navArgument(RouteArgument.articleId) { type = NavType.IntType })){ backStackEntry ->
            backStackEntry.arguments?.getInt(RouteArgument.articleId)?.let {
                ArticleDetailScreen(it, readMoreClick = { url -> navController.navigate(route = "${RouteName.appWebView}/$url") }, onBackPressed = {navController.popBackStack()})
            }
        }
        composable("${RouteName.appWebView}/{${RouteArgument.url}}",
            arguments = listOf(navArgument(RouteArgument.url) { type = NavType.StringType })){ backStackEntry ->
            backStackEntry.arguments?.getString(RouteArgument.url)?.let {url ->
                AppAndroidWebView(url = url)
            }
        }
    }
}