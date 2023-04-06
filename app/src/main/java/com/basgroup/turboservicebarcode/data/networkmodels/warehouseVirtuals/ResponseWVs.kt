package com.basgroup.turboservicebarcode.data.networkmodels.warehouseVirtuals

import com.squareup.moshi.Json

data class ResponseWVs(@Json(name = "WarehouseVirtuals")
                       val warehouseVirtuals: WarehouseVirtuals)