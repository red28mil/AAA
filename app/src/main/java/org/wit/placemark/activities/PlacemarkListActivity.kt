package org.wit.placemark.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import org.wit.placemark.R
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.placemark.databinding.ActivityPlacemarkList2Binding
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkAdapter
import org.wit.placemark.models.PlacemarkListener
import org.wit.placemark.models.PlacemarkModel

class PlacemarkListActivity : AppCompatActivity(), PlacemarkListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityPlacemarkList2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacemarkList2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        //binding.recyclerView.adapter = PlacemarkAdapter(app.placemarks)
        binding.recyclerView.adapter = PlacemarkAdapter(app.placemarks.findAll(),this)
     }
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
       menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
       }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, PlacemarkActivity::class.java)
                startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPlacemarkClick(placemark: PlacemarkModel) {
        val launcherIntent = Intent(this, PlacemarkActivity::class.java)
        launcherIntent.putExtra("placemark_edit", placemark)
        startActivityForResult(launcherIntent,0)
    }
}


  //  class PlacemarkAdapter constructor(private var placemarks: List<PlacemarkModel>) :
    //    RecyclerView.Adapter<PlacemarkAdapter.MainHolder>() {

      //  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        //    val binding = CardPlacemarkBinding
          //      .inflate(LayoutInflater.from(parent.context), parent, false)

            //return MainHolder(binding)
       // }

      //  override fun onBindViewHolder(holder: MainHolder, position: Int) {
      //      val placemark = placemarks[holder.adapterPosition]
      //      holder.bind(placemark)
      //  }


        //override fun getItemCount(): Int = placemarks.size

       // class MainHolder(private val binding : CardPlacemarkBinding) :
         //   RecyclerView.ViewHolder(binding.root) {

           // fun bind(placemark: PlacemarkModel) {
             //   binding.placemarkTitle.text = placemark.title
             //  binding.description.text = placemark.description
            //}
        //}

    //}


