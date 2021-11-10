package org.wit.pitchlocate.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.wit.pitchlocate.R
//import org.wit.pitchlocate.showImagePicker
import org.wit.pitchlocate.main.MainApp
import org.wit.pitchlocate.models.Location
import org.wit.pitchlocate.models.PitchlocateModel
import android.net.Uri
import org.wit.pitchlocate.databinding.ActivityPitchlocateBinding
import org.wit.pitchlocate.helpers.showImagePicker
import timber.log.Timber.i

class PitchlocateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPitchlocateBinding
    var pitchlocate = PitchlocateModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher: ActivityResultLauncher<Intent>
    private lateinit var mapIntentLauncher: ActivityResultLauncher<Intent>
   var location = Location(52.245696, -7.139102, 16f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var edit = false



        binding = ActivityPitchlocateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)


        app = application as MainApp

        i("Pitchlocate Activity started...")

        if (intent.hasExtra("pitchlocate_edit")) {
            edit = true
            pitchlocate = intent.extras?.getParcelable("pitchlocate_edit")!!
            binding.pitchlocateTitle.setText(pitchlocate.title)
            // binding.description.setText(pitchlocate.description)
            binding.btnAdd.setText(R.string.save_pitchlocate)
            Picasso.get()
                .load(pitchlocate.image)
                .into(binding.pitchlocateImage)
            if (pitchlocate.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_pitchlocate_image)
            }
        }

        binding.btnAdd.setOnClickListener() {
            pitchlocate.title = binding.pitchlocateTitle.text.toString()
            // placemark.description = binding.description.text.toString()
            if (pitchlocate.title.isEmpty()) {
                Snackbar
                    .make(it,"enter_pitchlocate_title", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.pitchlocates.update(pitchlocate.copy())
                } else {
                    app.pitchlocates.create(pitchlocate.copy())
                }
            }
            i("add Button Pressed: $pitchlocate")
            setResult(RESULT_OK)
            finish()
        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher)
        }
        binding.pitchlocateLocation.setOnClickListener {
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
        }
        registerImagePickerCallback()
        registerMapCallback()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_pitchlocate, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            pitchlocate.image = result.data!!.data!!
                            Picasso.get()
                                .load(pitchlocate.image)
                                .into(binding.pitchlocateImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }


    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            location = result.data!!.extras?.getParcelable("location")!!
                            i("Location == $location")
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }

            }
    }
}