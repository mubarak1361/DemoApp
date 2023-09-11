package com.app.demo.ui.widgets

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun AppAndroidWebView(url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    })
}