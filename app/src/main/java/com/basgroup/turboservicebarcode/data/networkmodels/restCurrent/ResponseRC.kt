package com.basgroup.turboservicebarcode.data.networkmodels.restCurrent

import com.squareup.moshi.Json

data class ResponseRC(@Json(name = "ArticleRestCurrent")
                      val articleRestCurrent: ArticleRestCurrent)