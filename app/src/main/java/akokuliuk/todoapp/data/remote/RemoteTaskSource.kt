package akokuliuk.todoapp.data.remote

import akokuliuk.todoapp.BuildConfig
import akokuliuk.todoapp.data.local.AuthenticationLocalStore
import akokuliuk.todoapp.domain.models.Task
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.OkHttpClient
import schema.AllTasksQuery
import javax.inject.Inject
import kotlin.coroutines.resumeWithException


//TODO: Must be exposed with interface
class RemoteTaskSource @Inject constructor(
    private val authenticationLocalStore: AuthenticationLocalStore,
    private val authenticationSource: AuthenticationSource
) {
    //TODO: Must be exposed through DI
    private val apolloClient = ApolloClient.builder()
        .okHttpClient(
            OkHttpClient.Builder()
                .addInterceptor {
                    if (authenticationLocalStore.getToken() == null) {
                        runBlocking {
                            authenticationLocalStore.saveToken(
                                authenticationSource.authenticate()
                            )
                        }
                    }
                    it.proceed(
                        it.request().newBuilder().addHeader(
                            "Authorization",
                            authenticationLocalStore.getToken()
                        ).build()
                    )
                }.build()
        )
        .serverUrl(BuildConfig.API_URL)
        .build()

    suspend fun loadTasks(): List<Task> {
        return suspendCancellableCoroutine {
            apolloClient.query(
                AllTasksQuery.builder().build()
            ).enqueue(object : ApolloCall.Callback<AllTasksQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    it.resumeWithException(e)
                }

                override fun onResponse(response: Response<AllTasksQuery.Data>) {
                    response.data()?.allTasks()?.map { item -> Task(item.id(), item.name(), item.note(), item.isDone) }
                }

            })
        }
    }
}