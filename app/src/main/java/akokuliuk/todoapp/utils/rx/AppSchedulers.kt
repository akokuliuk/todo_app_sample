package akokuliuk.todoapp.utils.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.annotations.TestOnly


object AppSchedulers{
    var main = AndroidSchedulers.mainThread()!!
        @TestOnly set
}