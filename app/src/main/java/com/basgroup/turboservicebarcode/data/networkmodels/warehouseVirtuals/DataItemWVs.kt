package com.basgroup.turboservicebarcode.data.networkmodels.warehouseVirtuals

import com.squareup.moshi.Json

data class DataItemWVs(@Json(name = "WH_ID")
                       val whId: Int = 0,
                       @Json(name = "WHV_CODE")
                       val whvCode: String = "",
                       @Json(name = "PFIRM_ID")
                       val pfirmId: Int = 0,
                       @Json(name = "WHVIRTUAL_ID")
                       val whvirtualId: Int = 0,
                       @Json(name = "WHVIRTUAL_NAME")
                       val whvirtualName: String = "")