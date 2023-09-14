package com.app.demo.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            arguments = listOf(navArgument(RouteArgument.articleId) { type = NavType.IntType }),
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }){ backStackEntry ->
            backStackEntry.arguments?.getInt(RouteArgument.articleId)?.let {
                ArticleDetailScreen(it, readMoreClick = { url -> navController.navigate(route = "${RouteName.appWebView}/$url") }, onBackPressed = {navController.popBackStack()})
            }
        }
        composable("${RouteName.appWebView}/{${RouteArgument.url}}",
            arguments = listOf(navArgument(RouteArgument.url) { type = NavType.StringType })
            ){ backStackEntry ->
            backStackEntry.arguments?.getString(RouteArgument.url)?.let {url ->
                AppAndroidWebView(url = url)
            }
        }
    }
}