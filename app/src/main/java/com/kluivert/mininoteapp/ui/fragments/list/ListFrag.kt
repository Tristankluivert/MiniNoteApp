package com.kluivert.mininoteapp.ui.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kluivert.mininoteapp.R
import com.kluivert.mininoteapp.adapter.NoteAdapter
import com.kluivert.mininoteapp.data.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFrag : Fragment(R.layout.fragment_list) {


    lateinit var noteViewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        setHasOptionsMenu(true)


        val adapter = NoteAdapter()
        view.noteRecycler.adapter = adapter
        view.noteRecycler.layoutManager = LinearLayoutManager(requireContext())

        noteViewModel.readNote.observe(viewLifecycleOwner, Observer {noteModel ->
            adapter.setData(noteModel)
        })

        view.fabAdd.setOnClickListener {

            findNavController().navigate(R.id.action_listFrag_to_addNoteFrag)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.miDelete ->{
                noteDialog()
            }
        }

        return super.onOptionsItemSelected(item)


    }


    fun noteDialog(){

        var dialog = AlertDialog.Builder(requireContext())
            .setTitle("Delete everything?")
            .setMessage("This will delete all saved notes")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Yes"){_, _ ->
                noteViewModel.deleteAllNote()

            }
            .setNegativeButton("No"){_,_ ->

            }.create()
        dialog.show()

    }


}