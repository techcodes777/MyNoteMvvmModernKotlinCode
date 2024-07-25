package com.eclatsol.mynotemvvmmodernkotlincode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eclatsol.mynotemvvmmodernkotlincode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var rvAdapter: RVAdapter

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
//        viewModel.allNoteData.observe(this) { list ->
//            rvAdapter.submitList(list)
//        }

        viewModel.allNoteData.observe(this, object : Observer<List<Note?>?> {
            override fun onChanged(value: List<Note?>?) {
                rvAdapter.submitList(value)
            }
        })



        binding.floatingActionButton.setOnClickListener {
            var intent = Intent(this, DataInsertActivity::class.java)
            intent.putExtra("type", "addNote")
            insertActivityResultLauncher.launch(intent)
        }


        binding.RV.layoutManager = LinearLayoutManager(this)
        binding.RV.setHasFixedSize(true)
        rvAdapter = RVAdapter()
        binding.RV.adapter = rvAdapter

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.RIGHT) {
                    viewModel.delete(rvAdapter.getNote(viewHolder.adapterPosition))
                    Toast.makeText(this@MainActivity, "Note delete", Toast.LENGTH_SHORT).show()
                } else {
                    var intent = Intent(this@MainActivity, DataInsertActivity::class.java)
                    intent.putExtra("type", "update")
                    intent.putExtra("title", rvAdapter.getNote(viewHolder.adapterPosition).title)
                    intent.putExtra(
                        "disc",
                        rvAdapter.getNote(viewHolder.adapterPosition).description
                    )
                    intent.putExtra("id", rvAdapter.getNote(viewHolder.adapterPosition).id)
                    updateResultLauncher.launch(intent)
                    Toast.makeText(this@MainActivity, "Note update", Toast.LENGTH_SHORT).show()
                }
            }

        }).attachToRecyclerView(binding.RV)
    }


    var insertActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.e("InsertData", "RESULT_OK ")

        val intent = result?.data
        val title = intent?.getStringExtra("title")
        val disc = intent?.getStringExtra("disc")
        val note = Note(title.toString(), disc.toString())
        viewModel.insert(note)
        Log.e("InsertData", "Inserted Data $disc,$note")
    }


    var updateResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val title = intent?.getStringExtra("title")
                val desc = intent?.getStringExtra("disc")
                val note = Note(title.toString(), desc.toString())
                viewModel.update(note)
                Log.e("UpdateData", "Inserted Data $desc,$note")

            }

        }
}