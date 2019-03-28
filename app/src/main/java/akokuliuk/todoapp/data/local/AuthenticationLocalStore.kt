package akokuliuk.todoapp.data.local

import akokuliuk.todoapp.TodoApp
import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton


//TODO: Should be exposed with interface
@Singleton
class AuthenticationLocalStore @Inject constructor(todoApp: TodoApp) {
    private val preferences = todoApp.getSharedPreferences("api_token_store", Context.MODE_PRIVATE)
    private var cachedToken: String? = null

    fun saveToken(token: String) {
        cachedToken = token
        preferences.edit()
            .putString("API_KEY", token)
            .apply()
    }

    fun getToken(): String? {
        return if (cachedToken != null) {
            cachedToken
        } else {
            cachedToken = preferences.getString("API_KEY", null)
            cachedToken
        }
    }

}
