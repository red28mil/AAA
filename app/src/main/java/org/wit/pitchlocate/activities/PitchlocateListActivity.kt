package org.wit.pitchlocate.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import org.wit.pitchlocate.R
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.pitchlocate.databinding.ActivityPitchlocateList2Binding
import org.wit.pitchlocate.main.MainApp
import org.wit.pitchlocate.models.PitchlocateAdapter
import org.wit.pitchlocate.models.PitchlocateListener
import org.wit.pitchlocate.models.PitchlocateModel

class PitchlocateListActivity : AppCompatActivity(), PitchlocateListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityPitchlocateList2Binding
    private lateinit var refreshIntentLauncher : ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPitchlocateList2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.adapter = PitchlocateAdapter(app.pitchlocates.findAll(),this)
    registerRefreshCallback()
     }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
       menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
       }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, PitchlocateActivity::class.java)
                refreshIntentLauncher.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPitchlocateClick(pitchlocate: PitchlocateModel) {
        val launcherIntent = Intent(this, PitchlocateActivity::class.java)
        launcherIntent.putExtra("pitchlocate_edit", pitchlocate)
        refreshIntentLauncher.launch(launcherIntent)
    }

    //override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    //    binding.recyclerView.adapter?.notifyDataSetChanged()
    //    super.onActivityResult(requestCode, resultCode, data)
   // }
    private fun registerRefreshCallback() {
        refreshIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { binding.recyclerView.adapter?.notifyDataSetChanged() }
    }



}






