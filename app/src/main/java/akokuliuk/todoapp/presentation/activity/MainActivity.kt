package akokuliuk.todoapp.presentation.activity

import akokuliuk.todoapp.R
import akokuliuk.todoapp.presentation.my_list.MyListFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_frame, MyListFragment())
                .commit()
        }
    }

    companion object {

        /**
         * Dirty hack to allow instrumentation tests to change fragment.
         * Such stuff should be handled by some navigation manager
         */
        const val FragmentFrameId = R.id.fragment_frame
    }
}
