package me.evgem.android.auratask

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import me.evgem.android.auratask.domain.model.BootRecord
import me.evgem.android.auratask.domain.repository.BootRecordRepository
import me.evgem.android.auratask.domain.repository.NotificationRepository
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            // don't care
        }

    private val notificationRepository: NotificationRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        // TODO use view model
        displayRecordsData()

        findViewById<EditText>(R.id.mainTotalDismissalsAllowed).let { editText ->
            editText.setText(notificationRepository.totalDismissalsAllowed.toString())
            editText.addTextChangedListener { text ->
                text?.toString()?.toIntOrNull()?.let {
                    notificationRepository.totalDismissalsAllowed = it
                }
            }
        }
        findViewById<EditText>(R.id.mainIntervalBetweenDismissals).let { editText ->
            editText.setText(notificationRepository.intervalBetweenDismissalsMinutes.toString())
            editText.addTextChangedListener { text ->
                text?.toString()?.toIntOrNull()?.let {
                    notificationRepository.intervalBetweenDismissalsMinutes = it
                }
            }
        }
    }

    private fun displayRecordsData() {
        lifecycleScope.launch {
            val records = get<BootRecordRepository>().getAllRecords()
            setRecordsText(records)
        }
    }

    private fun setRecordsText(records: List<BootRecord>) {
        val contentView = findViewById<TextView>(R.id.mainContent)
        if (records.isEmpty()) {
            contentView.text = getString(R.string.main_records_empty)
            return
        }
        contentView.text = records.groupBy { it.timestamp.toLocalDate() }
            .entries.joinToString { (date, records) ->
                val formatted = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date)
                "$formatted - ${records.size}"
            }
    }
}
