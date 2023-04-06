package com.basgroup.turboservicebarcode.data.networkmodels.user

import com.squareup.moshi.Json

data class UserResult(@Json(name = "Status")
                      val status: Int = 0,
                      @Json(name = "Response")
                      val response: UserResponse?,
                      @Json(name = "Message")
                      val message: String = "")