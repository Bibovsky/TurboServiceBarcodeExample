package com.basgroup.turboservicebarcode.data.networkmodels.articles

import com.squareup.moshi.Json

data class RelationsAr(@Json(name = "Producer")
                       val producer: Producer,
                       @Json(name = "ArticleType")
                       val articleType: ArticleType)