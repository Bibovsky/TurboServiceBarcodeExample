package com.basgroup.turboservicebarcode.data.networkmodels.warehouseVirtuals

import com.squareup.moshi.Json

data class ResultWVs(@Json(name = "Status")
                     val status: Int = 0,
                     @Json(name = "Response")
                     val response: ResponseWVs?,
                     @Json(name = "Message")
                     val message: String = "")