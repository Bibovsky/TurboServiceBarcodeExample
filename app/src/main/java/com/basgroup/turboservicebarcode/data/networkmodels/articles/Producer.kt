package com.basgroup.turboservicebarcode.data.networkmodels.articles

import com.squareup.moshi.Json

data class Producer(
                    @Json(name = "IS_IMPROPER")
                    val isImproper: Boolean = false,
                    @Json(name = "PRODUCER_NAME")
                    val producerName: String = "",
                    @Json(name = "IS_PRIORITY_PRODUCER")
                    val isPriorityProducer: Boolean = false,
                    @Json(name = "PRODUCER_NAME_ENG")
                    val producerNameEng: String = "")