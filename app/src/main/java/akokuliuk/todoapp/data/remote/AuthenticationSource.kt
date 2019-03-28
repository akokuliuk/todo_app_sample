package akokuliuk.todoapp.data.remote

import akokuliuk.todoapp.BuildConfig
import akokuliuk.todoapp.domain.errors.ConnectivityError
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import dagger.Reusable
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import schema.GenerateApiTokenMutation
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


//TODO: Should be exposed with interface
@Reusable
class AuthenticationSource @Inject constructor() {

    private val apolloClient =
        ApolloClient
            .builder()
            .okHttpClient(
                //TODO: Should be removed even in debug builds!
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
            )
            .serverUrl(BuildConfig.API_URL)
            .build()

    suspend fun authenticate() = suspendCancellableCoroutine<String> {
        apolloClient.mutate(
            GenerateApiTokenMutation.builder()
                .apiKey(BuildConfig.API_KEY)
                .userName(UUID.randomUUID().toString())
                .build()
        ).enqueue(object : ApolloCall.Callback<GenerateApiTokenMutation.Data>() {

            override fun onFailure(e: ApolloException) {
                it.resumeWithException(e)
            }

            override fun onResponse(response: Response<GenerateApiTokenMutation.Data>) {
                //TODO: Convert errors here
                if (response.errors().isNotEmpty()) {
                    it.resumeWithException(ConnectivityError(response.errors().toString()))
                }
                it.resume(response.data()?.generateAccessToken()!!)
            }
        })
    }
}
