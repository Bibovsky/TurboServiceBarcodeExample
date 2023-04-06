package com.basgroup.turboservicebarcode.data.network

import com.basgroup.turboservicebarcode.data.networkmodels.arPlacePrice.ArPlacePriceRoot
import com.basgroup.turboservicebarcode.data.networkmodels.articles.ArticlesRoot
import com.basgroup.turboservicebarcode.data.networkmodels.restCurrent.RestCurrentRoot
import com.basgroup.turboservicebarcode.data.networkmodels.user.UserRoot
import com.basgroup.turboservicebarcode.data.networkmodels.warehouseVirtuals.WarehouseVirtualsRoot
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitService {


    @GET("/api-v2/auth/login")
    fun auth(@Header("Authorization") Credentials:String) : Single<UserRoot>

    @GET("/api-v2/Core/Directories/WarehouseVirtuals")
    fun getWarehouses(@Query("SESSIONID") sessionid: String) : Single<WarehouseVirtualsRoot>

    @GET("/api-v2/Core/Directories/Articles")
    //fun getItemByBarcode(@Query("SESSIONID") sessionid: String, @Query("FilterString") FilterString: String, @Query("WithObjects[]") a:String = "ArticleType",@Query("WithObjects[]") b:String = "Producer" ) : Single<ArticlesRoot>
    fun getItemByBarcode(@Query("SESSIONID") sessionid: String, @Query("FilterString") FilterString: String = "(SCAN_CODE like ?) or (CATALOG_CODE like ?)",@Query("FilterParam[0]") p0:String,@Query("FilterParam[1]") p1:String, @Query("WithObjects[]") a:String = "ArticleType",@Query("WithObjects[]") b:String = "Producer" ) : Single<ArticlesRoot>

    @GET("/api-v2/Core/Directories/ArticleRestCurrent/{ArticleId}/{VWId}")
    fun getRestCurrent(@Path("ArticleId") articleId: Int,@Path("VWId") vwId: Int, @Query("SESSIONID") sessionid: String) : Single<RestCurrentRoot>

    @GET("/api-v2/Core/Directories/ArticlePlaceAndPrice/{ArticleId}/{WId}")
    fun getPlace(@Path("ArticleId") articleId: Int,@Path("WId") wId: Int, @Query("SESSIONID") sessionid: String ) : Single<ArPlacePriceRoot>

}