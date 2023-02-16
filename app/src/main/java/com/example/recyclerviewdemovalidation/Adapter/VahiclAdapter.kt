package com.example.recyclerviewdemovalidation.Adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemovalidation.R
import com.example.recyclerviewdemovalidation.activity.MainActivity.Companion.vahicalarryList
import com.example.recyclerviewdemovalidation.model.Vahiclemodel
import com.google.android.material.textfield.TextInputLayout


class VahiclAdapter(
    var context: Context,
    var model: ArrayList<Vahiclemodel>,
    var callbacks: (Vahiclemodel, Position: Int, View) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.vehicle_list_layout, parent, false)
        return MyViewHolder(view)
    }


    @SuppressLint("CutPasteId", "ResourceAsColor")
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        if (vahicalarryList[position].nameedit.isEmpty()) {

            holder.itemView.findViewById<TextInputLayout>(R.id.text1).error =
                "Please Enter Name"
        } else {

            holder.itemView.findViewById<TextInputLayout>(R.id.text1).error =
                ""
        }

        if (vahicalarryList[position].modeledit.isEmpty()) {
            holder.itemView.findViewById<TextInputLayout>(R.id.text2).error =
                "Please Enter Model"

        } else {
            holder.itemView.findViewById<TextInputLayout>(R.id.text2).error =
                ""
        }
        if (vahicalarryList[position].companyedit.isEmpty()) {
            holder.itemView.findViewById<TextInputLayout>(R.id.text3).error =
                "Please Enter Company"
        } else {
            holder.itemView.findViewById<TextInputLayout>(R.id.text3).error =
                ""
        }

        if (vahicalarryList[position].nameedit.isEmpty() || vahicalarryList[position].modeledit.isEmpty() || vahicalarryList[position].companyedit.isEmpty()) {
           // Toast.makeText(context, "000000000000000000000", Toast.LENGTH_SHORT).show()
            holder.itemView.findViewById<AppCompatTextView>(R.id.textcolourchange)
                .setTextColor(context.getColor(R.color.green))

        } else {
            holder.itemView.findViewById<AppCompatTextView>(R.id.textcolourchange)
                .setTextColor(context.getColor(R.color.black))
            // Toast.makeText(context, "111111111111111111111111", Toast.LENGTH_SHORT).show()
        }


        /*______________________________________for first edit Text_____________________________________________________________*/

        holder.itemView.findViewById<AppCompatEditText>(R.id.vahicleedittext)
            .addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                    Log.e(TAG, "afterTextChanged000000000000000000:$s")
                    (vahicalarryList[position].nameedit) = s.toString()
                }
            })

        /*______________________________________for 2nd edit Text_____________________________________________________________*/

        holder.itemView.findViewById<AppCompatEditText>(R.id.vahicleModelEdittext)
            .addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(
                    p: CharSequence, start: Int,
                    before: Int, count: Int
                ) {

                }

                override fun beforeTextChanged(
                    p: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                    Log.e(
                        TAG,
                        "beforeTextChanged++++++++++++++++++++++++++++++++++++++++++++++++++++++++:$p "
                    )
                }

                override fun afterTextChanged(p: Editable) {

                    Log.e(TAG, "afterTextChanged000000000000000000:$p")
                    (vahicalarryList[position].modeledit) = p.toString()
                }
            })

        /*______________________________________for 3nd edit Text_____________________________________________________________*/
        holder.itemView.findViewById<AppCompatEditText>(R.id.vahicleCompanyEdittext)
            .addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(
                    q: CharSequence, start: Int,
                    before: Int, count: Int
                ) {

                }

                override fun beforeTextChanged(
                    q: CharSequence, start: Int,
                    count: Int, after: Int
                ) {
                }

                override fun afterTextChanged(q: Editable) {
                    (vahicalarryList[position].companyedit) = q.toString()
                }
            })
        holder.itemView.findViewById<AppCompatEditText>(R.id.vahicleedittext)
            .setText(model[position].nameedit)
        holder.itemView.findViewById<AppCompatEditText>(R.id.vahicleModelEdittext)
            .setText(model[position].modeledit)
        holder.itemView.findViewById<AppCompatEditText>(R.id.vahicleCompanyEdittext)
            .setText(model[position].companyedit)


        val UserViewModel = model[position]
        holder.itemView.findViewById<AppCompatTextView>(R.id.vahiclename).text =
            model[position].name
        holder.itemView.findViewById<AppCompatTextView>(R.id.vahicleModel).text =
            model[position].model
        holder.itemView.findViewById<AppCompatTextView>(R.id.vahiclecompany).text =
            model[position].company
        holder.itemView.findViewById<AppCompatTextView>(R.id.postionid).text = position.toString()

        holder.itemView.findViewById<Button>(R.id.addBtn).setOnClickListener {
            callbacks.invoke(UserViewModel, position, holder.itemView.findViewById(R.id.addBtn))
            holder.itemView.setVisibility(View.VISIBLE);
            Log.e(TAG, "onBindViewHolder:$position ")
        }

        holder.itemView.findViewById<AppCompatEditText>(R.id.vahicleedittext).setOnClickListener {
            callbacks.invoke(
                UserViewModel,
                position,
                holder.itemView.findViewById(R.id.vahicleedittext)
            )
        }

        holder.itemView.findViewById<AppCompatEditText>(R.id.vahicleModelEdittext)
            .setOnClickListener {
                callbacks.invoke(
                    UserViewModel,
                    position,
                    holder.itemView.findViewById(R.id.vahicleModelEdittext)
                )
            }

        holder.itemView.findViewById<AppCompatEditText>(R.id.vahicleCompanyEdittext)
            .setOnClickListener {
                callbacks.invoke(
                    UserViewModel,
                    position,
                    holder.itemView.findViewById(R.id.vahicleCompanyEdittext)
                )
            }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int = model.size
}
