package co.adarsh.remote.service

import co.adarsh.remote.model.ProjectsResponseModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubTrendingService {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String,
                           @Query("sort") sortBy: String,
                           @Query("order") order: String)
    : Flowable<ProjectsResponseModel>

}