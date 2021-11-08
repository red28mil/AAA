package org.wit.pitchlocate.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.pitchlocate.databinding.CardPitchlocateBinding


interface PitchlocateListener {
    fun onPitchlocateClick(pitchlocate: PitchlocateModel)
}

class PitchlocateAdapter constructor(private var pitchlocates: List<PitchlocateModel>,
                                   private val listener: PitchlocateListener) :
    RecyclerView.Adapter<PitchlocateAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardPitchlocateBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val pitchlocate = pitchlocates[holder.adapterPosition]
        holder.bind(pitchlocate, listener)
    }

    override fun getItemCount(): Int = pitchlocates.size

    class MainHolder(private val binding : CardPitchlocateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pitchlocate: PitchlocateModel, listener: PitchlocateListener) {
            binding.pitchlocateTitle.text = pitchlocate.title
            binding.description.text = pitchlocate.description
            //Picasso.get().load(pitchlocate.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onPitchlocateClick(pitchlocate) }
        }
    }
}