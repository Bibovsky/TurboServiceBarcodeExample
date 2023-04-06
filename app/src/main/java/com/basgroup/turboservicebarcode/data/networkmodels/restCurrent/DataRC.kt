package com.basgroup.turboservicebarcode.data.networkmodels.restCurrent

import com.squareup.moshi.Json

data class DataRC(@Json(name = "WHVIRTUAL_ID")
                  val whvirtualId: Int = 0,
                  @Json(name = "REST")
                  val rest: Int = 0,
                  @Json(name = "ARTICLE_ID")
                  val articleId: Int = 0)