package com.example.comandera.features.mesas.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.comandera.databinding.ItemMesaBinding
import com.example.comandera.features.mesas.model.Mesa

class MesasAdapter : ListAdapter<Mesa, MesasAdapter.MesaVH>(Diff) {

    object Diff : DiffUtil.ItemCallback<Mesa>() {
        override fun areItemsTheSame(oldItem: Mesa, newItem: Mesa) = oldItem.idMesa == newItem.idMesa
        override fun areContentsTheSame(oldItem: Mesa, newItem: Mesa) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MesaVH {
        val binding = ItemMesaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MesaVH(binding)
    }

    override fun onBindViewHolder(holder: MesaVH, position: Int) {
        holder.bind(getItem(position))
    }

    class MesaVH(private val binding: ItemMesaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mesa: Mesa) {
            binding.txtNombreMesa.text = mesa.nombre
            binding.txtZona.text = mesa.zona ?: "-"
            binding.txtEstado.text = mesa.estadoMesa
        }
    }
}
