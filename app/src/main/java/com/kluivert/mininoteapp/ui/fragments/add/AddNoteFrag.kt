package com.kluivert.mininoteapp.ui.fragments.add

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kluivert.mininoteapp.R
import com.kluivert.mininoteapp.data.entities.NoteModel
import com.kluivert.mininoteapp.data.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_add_note.view.*

class AddNoteFrag : Fragment(R.layout.fragment_add_note) {


    lateinit var noteViewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        setHasOptionsMenu(true)


        view.btnSave.setOnClickListener {

            val title = view.edTitle.text.toString()
            val content = view.edTextContent.text.toString()

            val noteModel = NoteModel(0,title,content)

            if(inputChecker(title,content)){

                noteViewModel.addNote(noteModel)
                Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addNoteFrag_to_listFrag)

            }else{
                Toast.makeText(requireContext(),"Fill all fields",Toast.LENGTH_SHORT).show()
            }

        }

    }

      fun inputChecker(title :String, content : String):Boolean{
          return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
      }


}