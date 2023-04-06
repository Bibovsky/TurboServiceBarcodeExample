package com.basgroup.turboservicebarcode.data.networkmodels.warehouseVirtuals

import com.squareup.moshi.Json

data class WarehouseVirtuals(@Json(name = "totalRecords")
                             val totalRecords: Int = 0,
                             @Json(name = "data")
                             val data: List<DataItemWVs>?)