package akokuliuk.todoapp.domain.errors

import java.lang.Error


sealed class RemoteError : Error {

    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}

class ConnectivityError(message: String? = null) : RemoteError(message)
