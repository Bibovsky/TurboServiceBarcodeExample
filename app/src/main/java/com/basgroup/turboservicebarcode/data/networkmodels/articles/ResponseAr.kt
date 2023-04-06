package com.basgroup.turboservicebarcode.data.networkmodels.articles

import com.squareup.moshi.Json

data class ResponseAr(@Json(name = "Articles")
                      val articles: ArticlesAr)