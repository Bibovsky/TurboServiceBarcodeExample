package com.basgroup.turboservicebarcode.data.networkmodels.articles

import com.squareup.moshi.Json

data class ArticlesAr(@Json(name = "totalRecords")
                      val totalRecords: Int = 0,
                      @Json(name = "data")
                      val data: List<DataItemAr>?)