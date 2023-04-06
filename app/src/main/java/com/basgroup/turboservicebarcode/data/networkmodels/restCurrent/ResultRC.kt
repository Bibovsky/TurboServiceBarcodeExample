package com.basgroup.turboservicebarcode.data.networkmodels.restCurrent

import com.squareup.moshi.Json

data class ResultRC(@Json(name = "Status")
                    val status: Int = 0,
                    @Json(name = "Response")
                    val response: ResponseRC,
                    @Json(name = "Message")
                    val message: String = "")