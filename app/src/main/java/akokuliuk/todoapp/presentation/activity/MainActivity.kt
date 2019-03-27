package akokuliuk.todoapp.presentation.activity

import akokuliuk.todoapp.R
import akokuliuk.todoapp.utils.android.ViewModelFactory
import akokuliuk.todoapp.utils.rx.AppSchedulers
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelFactory.provideViewModel(this, MainActivityViewModel::class.java) {
            MainActivityViewModel()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.bindState {
            it.observeOn(AppSchedulers.main)
                .subscribe {
                    findViewById<TextView>(R.id.text).text = it.title
                }
        }
    }
}
