package com.basgroup.turboservicebarcode.data.dbmodels

data class ArticleSelectItem (
        var articleId:Int,
        var code:String,
        var producer:String,
        var barcode:String,
        var name:String,
        var count:Int?,
        var place:String?,
        var reportedCount:Int?
        )
