package com.app.demo.core.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteArticle(
    @SerialName("abstract")
    val description: String,
    val byline: String,
    @SerialName("created_date")
    val createdDate: String,
    @SerialName("des_facet")
    val desFacet: List<String>,
    @SerialName("geo_facet")
    val geoFacet: List<String>,
    @SerialName("item_type")
    val itemType: String,
    val kicker: String,
    @SerialName("material_type_facet")
    val materialTypeFacet: String,
    val multimedia: List<RemoteMultimedia>,
    @SerialName("org_facet")
    val orgFacet: List<String>,
    @SerialName("per_facet")
    val perFacet: List<String>,
    @SerialName("published_date")
    val publishedDate: String,
    val section: String,
    @SerialName("short_url")
    val shortUrl: String,
    @SerialName("subsection")
    val subSection: String,
    val title: String,
    @SerialName("updated_date")
    val updatedDate: String,
    val uri: String,
    val url: String
)