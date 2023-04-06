package com.basgroup.turboservicebarcode.data.networkmodels.arPlacePrice

import com.squareup.moshi.Json

data class DataArPP(@Json(name = "WAREHOUSE_ID")
                    val warehouseId: Int = 0,
                    @Json(name = "RACK")
                    val rack: String = "",
                    @Json(name = "ARTICLE_PLACE_AND_PRICE_ID")
                    val articlePlaceAndPriceId: Int = 0,
                    @Json(name = "SHELF")
                    val shelf: String = "",
                    @Json(name = "ARTICLE_ID")
                    val articleId: Int = 0,
                    @Json(name = "ROW")
                    val row: String = "",
                    @Json(name = "CELL")
                    val cell: String = "",
                    @Json(name = "PLACE")
                    val place: String = "")