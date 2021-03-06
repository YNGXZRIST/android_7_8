package ru.bfu.malenkov.eventreminder.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.bfu.malenkov.eventreminder.data.db.AppDatabase
import ru.bfu.malenkov.eventreminder.data.db.model.EventReminderDb
import java.util.*

class InitialDataDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val list = listOf(
            EventReminderDb(1, "TITLE 1", "desc 1", Date()),
            EventReminderDb(2, "TITLE 2", "desc 2 ", Date()),
            EventReminderDb(3, "TITLE 3", "desc 3", Date()),
            EventReminderDb(4, "TITLE 4", "desc 4", Date()),
            EventReminderDb(5, "TITLE 5", "desc 5", Date()),
            EventReminderDb(6, "TITLE 6", "desc 6", Date()),
            EventReminderDb(7, "TITLE 7", "desc 7", Date()),
            EventReminderDb(8, "TITLE 8", "desc 8", Date()),
        )

        try {
            val database = AppDatabase.getInstance(applicationContext)
            database.eventReminderDao().insertAll(list)
            Result.success()
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}