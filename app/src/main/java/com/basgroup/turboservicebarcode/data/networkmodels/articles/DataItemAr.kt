package com.basgroup.turboservicebarcode.data.networkmodels.articles

import com.squareup.moshi.Json

data class DataItemAr(@Json(name = "UNIT_ID")
                      val unitId: Int = 0,
                      @Json(name = "CURRENCY_ID")
                      val currencyId: Int = 0,
                      @Json(name = "CATALOG_CODE")
                      val catalogCode: String = "",
                      @Json(name = "ARTICLE_NAME")
                      val articleName: String = "",
                      @Json(name = "ARTICLE_TYPE_ID")
                      val articleTypeId: Int = 0,
                      @Json(name = "DESCRIPTION")
                      val description: String = "",
                      @Json(name = "ARTICLE_ID")
                      val articleId: Int = 0,
                      @Json(name = "ARTICLE_NAME_ORIGINAL")
                      val articleNameOriginal: String = "",
                      @Json(name = "SCAN_CODE")
                      val scanCode: String = "",
                      @Json(name = "ARTICLE_CODE")
                      val articleCode: String = "",
                      @Json(name = "D1")
                      val d1: Double = 0.0,
                      @Json(name = "D2")
                      val d2: Double = 0.0,
                      @Json(name = "GROUP_ID")
                      val groupId: Int = 0,
                      @Json(name = "PRICE_OUT")
                      val priceOut: Int = 0,
                      @Json(name = "D3")
                      val d3: Double = 0.0,
                      @Json(name = "D4")
                      val d4: Double = 0.0,
                      @Json(name = "_relations")
                      val Relations: RelationsAr,
                      @Json(name = "GTD_COUNTRY_CODE")
                      val gtdCountryCode: String = "",
                      @Json(name = "IS_FIXED_PRICE_IN")
                      val isFixedPriceIn: Boolean = false,
                      @Json(name = "IS_ORDER_DENIED_TS")
                      val isOrderDeniedTs: Boolean = false)