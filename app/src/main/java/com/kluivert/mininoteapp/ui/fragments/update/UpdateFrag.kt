package com.kluivert.mininoteapp.ui.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kluivert.mininoteapp.R
import com.kluivert.mininoteapp.data.entities.NoteModel
import com.kluivert.mininoteapp.data.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.fragment_update.view.edTitleUpdate


class UpdateFrag : Fragment(R.layout.fragment_update) {

    lateinit var noteViewModel : NoteViewModel
    private val args by navArgs<UpdateFragArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        setHasOptionsMenu(true)
        view.edTitleUpdate.setText(args.noteArgs.title)
        view.edTextContentUpdate.setText(args.noteArgs.textcon)

        view.btnUpdate.setOnClickListener {

            val title = edTitleUpdate.text.toString()
            val content = edTextContentUpdate.text.toString()

            val noteModel = NoteModel(args.noteArgs.id,title,content)

            if (inputChecker(title,content)){
                noteViewModel.updateNote(noteModel)
                Toast.makeText(requireContext(),"Updated successfully",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFrag_to_listFrag)
            }else {
                Toast.makeText(requireContext(),"Make some changes",Toast.LENGTH_SHORT).show()
            }

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

    fun inputChecker(title :String, content : String):Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }

    fun noteDialog(){

        var dialog = AlertDialog.Builder(requireContext())
            .setTitle("Delete ${args.noteArgs.title}?")
            .setMessage("This will delete ${args.noteArgs.title}")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Yes"){_, _ ->
                noteViewModel.deleteNote(args.noteArgs)
                Toast.makeText(requireContext(),"Removed : ${args.noteArgs.title}",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFrag_to_listFrag)
            }
            .setNegativeButton("No"){_,_ ->

            }.create()
        dialog.show()

    }
}