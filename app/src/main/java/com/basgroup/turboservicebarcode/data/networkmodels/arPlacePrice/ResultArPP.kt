package com.basgroup.turboservicebarcode.data.networkmodels.arPlacePrice

import com.squareup.moshi.Json

data class ResultArPP(@Json(name = "Status")
                      val status: Int = 0,
                      @Json(name = "Response")
                      val response: ResponseArPP?,
                      @Json(name = "Message")
                      val message: String = "")