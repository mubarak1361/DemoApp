package com.app.demo.domain.articles.interactor

import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject


class FormatArticlePublishedDateUseCase @Inject constructor() {

    operator fun invoke(date: String): String {
        val defaultReturnValue = ""
        return try {
            val sourceSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZ", Locale.ENGLISH)
            val targetSDF = SimpleDateFormat("EEE, dd MMM yyy hh:mm a", Locale.ENGLISH)
            sourceSdf.parse(date)?.let {
                targetSDF.format(it)
            }?:defaultReturnValue
        }catch (_:Exception){
            defaultReturnValue
        }

    }
}