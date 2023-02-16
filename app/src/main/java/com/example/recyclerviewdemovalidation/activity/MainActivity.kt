package com.example.recyclerviewdemovalidation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewdemovalidation.Adapter.VahiclAdapter
import com.example.recyclerviewdemovalidation.R
import com.example.recyclerviewdemovalidation.databinding.ActivityMainBinding
import com.example.recyclerviewdemovalidation.model.Vahiclemodel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: VahiclAdapter

    companion object {
        val vahicalarryList: ArrayList<Vahiclemodel> = ArrayList<Vahiclemodel>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        recyclerView()
        binding.submitbtn.setOnClickListener {
            validation()
        }
    }


    @SuppressLint("ShowToast", "NotifyDataSetChanged")
    private fun validation() {
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerView() {

        vahicalarryList.add(Vahiclemodel("Name", "Model", "Company", "yamahaRx", "2020", "Yamaha"))
        binding.recylerviewmain.layoutManager = LinearLayoutManager(this)

        adapter = VahiclAdapter(this, vahicalarryList) { UserViewModel, Postion, view ->
            when (view.id) {

                R.id.vahicleedittext -> {
                    UserViewModel.nameedit
                    Toast.makeText(this, "$Postion", Toast.LENGTH_SHORT).show()

                }
                R.id.vahicleModelEdittext -> {
                    UserViewModel.modeledit
                }
                R.id.vahicleCompanyEdittext -> {
                    UserViewModel.companyedit

                }
                R.id.addBtn -> {
                    vahicalarryList.add(Vahiclemodel("Name", "Model", "Company", "", "", ""))
                    view.visibility=View.GONE
                    UserViewModel.nameedit
                    UserViewModel.modeledit
                    UserViewModel.companyedit
                    adapter.notifyDataSetChanged()
                }
            }
        }
        binding.recylerviewmain.adapter = adapter
    }
}


