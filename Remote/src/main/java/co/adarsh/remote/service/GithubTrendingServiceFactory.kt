package co.adarsh.remote.service

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Factory class to get build retrofit and create github retrofit service
 */
object GithubTrendingServiceFactory {

    /**
     * method to create {@link GithubTrendingService}
     *
     * @param Boolean : isDebug flag to set log level for {@link HttpLoggingInterceptor}
     *
     * @return {@link GithubTrendingService}
     */
    open fun makeGithubTrendingService(isDebug: Boolean): GithubTrendingService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor((isDebug)))
        return makeGithubTrendingService(okHttpClient, Gson())
    }

    /**
     * method to create {@link GithubTrendingService}
     *
     * @param OkHttpClient : OkHttpClient to configure retrofit library for http calls
     * @param Gson : To map Json to Object
     *
     * @return {GithubTrendingService
     */
    private fun makeGithubTrendingService(okHttpClient: OkHttpClient, gson: Gson): GithubTrendingService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(GithubTrendingService::class.java)
    }

    /**
     * method to create {@link OkHttpClient}
     *
     * @param HttpLoggingInterceptor : log interceptor to log the http calls
     *
     * @return {@link OkHttpClient}
     */
    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    /**
     * method to create {@link HttpLoggingInterceptor}.
     *
     * @param Boolean : isDebug flag to set log level for {@link HttpLoggingInterceptor}
     *
     * @return {@link HttpLoggingInterceptor} : Logging is done only if build is of debug type
     */
    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

}