package com.basgroup.turboservicebarcode.data.networkmodels.user

import com.squareup.moshi.Json

data class UserRoot(@Json(name = "result")
                        val result: UserResult,
                    @Json(name = "SESSIONID")
                        val sessionid: String? = "",
                    @Json(name = "remaining")
                        val remaining: Int? = 0)