package com.basgroup.turboservicebarcode.data.networkmodels.articles

import com.squareup.moshi.Json

data class ArticlesRoot(@Json(name = "result")
                        val result: ResultAr)