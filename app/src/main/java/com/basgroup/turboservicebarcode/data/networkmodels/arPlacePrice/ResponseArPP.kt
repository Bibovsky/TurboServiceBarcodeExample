package com.basgroup.turboservicebarcode.data.networkmodels.arPlacePrice

import com.squareup.moshi.Json

data class ResponseArPP(@Json(name = "ArticlePlaceAndPrice")
                        val articlePlaceAndPrice: ArticlePlaceAndPrice)