package com.educalab.pequeley

import android.app.Application
import com.educalab.pequeley.data.local.PequeLeyDatabase
import com.educalab.pequeley.data.repository.PequeLeyRepository

class PequeLeyApplication : Application() {
    val database: PequeLeyDatabase by lazy { PequeLeyDatabase.getInstance(this) }
    val repository: PequeLeyRepository by lazy { PequeLeyRepository(database) }
}
