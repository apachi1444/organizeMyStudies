package android.example.organizestudies

import android.example.organizestudies.databinding.ActivityOnlyOnceWelcomeBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.fragment.app.commit

class OnlyOnceWelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnlyOnceWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_only_once_welcome)

        supportFragmentManager.commit {
            setReorderingAllowed(true).add<FirstWelcomePageFragment>(R.id.fragment_container_view)
        }
    }

}