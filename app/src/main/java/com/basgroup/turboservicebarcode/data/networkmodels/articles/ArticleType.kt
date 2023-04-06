package com.basgroup.turboservicebarcode.data.networkmodels.articles

import com.squareup.moshi.Json

data class ArticleType(@Json(name = "CHANGE_PRICES")
                       val changePrices: Boolean = false,
                       @Json(name = "ONE_LINE_ID")
                       val oneLineId: Int = 0,
                       @Json(name = "ATG_ID")
                       val atgId: Int = 0,
                       @Json(name = "ARTICLE_TYPE_ID")
                       val articleTypeId: Int = 0,
                       @Json(name = "IS_EXCISABLE")
                       val isExcisable: Boolean = false,

                       @Json(name = "DEFAULT_TYPE")
                       val defaultType: Boolean = false,
                       @Json(name = "PARAM2")
                       val param1: String = "",
                       @Json(name = "PARAM1")
                       val param2: Int = 0,
                       @Json(name = "PARAM4")
                       val param3: String = "",
                       @Json(name = "PARAM3")
                       val param4: String = "",
                       @Json(name = "ATYPE_NAME")
                       val atypeName: String = "",
                       @Json(name = "ATYPE_CODE")
                       val atypeCode: String = "",
                       @Json(name = "IS_PRICE_SELECTION_ENABLED")
                       val isPriceSelectionEnabled: Boolean = false)