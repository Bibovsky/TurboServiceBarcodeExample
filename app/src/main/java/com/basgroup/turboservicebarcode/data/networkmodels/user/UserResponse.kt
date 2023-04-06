package com.basgroup.turboservicebarcode.data.networkmodels.user

import com.squareup.moshi.Json

data class UserResponse(@Json(name = "FullName")
                        val fullName: String = "",
                        @Json(name = "IS_SECOND_NAME_REQUIRED")
                        val isSecondNameRequired: String = "")