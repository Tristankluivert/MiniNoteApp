package com.kluivert.mininoteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kluivert.mininoteapp.R
import com.kluivert.mininoteapp.data.entities.NoteModel
import com.kluivert.mininoteapp.ui.fragments.list.ListFragDirections
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(
    var noteList : List<NoteModel> = emptyList()
): RecyclerView.Adapter<NoteAdapter.NoteAdapterViewHolder>(){

    inner class NoteAdapterViewHolder(itenView : View) : RecyclerView.ViewHolder(itenView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteAdapterViewHolder, position: Int) {
        holder.itemView.apply {
            val current = noteList[position]
            tvtextTitle.text = current.title
            tvID.text = current.id.toString()
            tvnoteContent.text = current.textcon


            note_item_list.setOnClickListener {
                val action = ListFragDirections.actionListFragToUpdateFrag(current)
                findNavController().navigate(action)

            }


        }
    }

    fun setData(noteModel : List<NoteModel>){
        this.noteList = noteModel
        notifyDataSetChanged()
    }

}