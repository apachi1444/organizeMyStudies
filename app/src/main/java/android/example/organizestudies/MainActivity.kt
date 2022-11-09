package android.example.organizestudies

import android.content.Context
import android.example.organizestudies.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val my_shared_pref = getSharedPreferences("mine", Context.MODE_PRIVATE)

        println(my_shared_pref)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
                .add<HomeFragment>(R.id.fragment_container_view)
        }

    }

}