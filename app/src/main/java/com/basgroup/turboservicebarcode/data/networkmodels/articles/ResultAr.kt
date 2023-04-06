package com.basgroup.turboservicebarcode.data.networkmodels.articles

import com.squareup.moshi.Json

data class ResultAr(@Json(name = "Status")
                    val status: Int = 0,
                    @Json(name = "Response")
                    val response: ResponseAr?,
                    @Json(name = "Message")
                    val message: String = "")