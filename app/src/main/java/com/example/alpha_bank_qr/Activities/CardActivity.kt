package com.example.alpha_bank_qr.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alpha_bank_qr.Adapters.DataListAdapter
import com.example.alpha_bank_qr.QRDatabaseHelper
import com.example.alpha_bank_qr.R
import com.example.alpha_bank_qr.Utils.DataUtils
import kotlinx.android.synthetic.main.activity_card.*
import kotlinx.android.synthetic.main.activity_profile.data_list
import kotlinx.android.synthetic.main.activity_profile.profile_name
import kotlinx.android.synthetic.main.activity_profile.profile_photo

class CardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        back.setOnClickListener { finish() }

        val bundle : Bundle? = intent.extras
        val id = bundle!!.getInt("user_id")

        val dbHelper = QRDatabaseHelper(this)
        val cursor = dbHelper.getUser(id)
        if (cursor!!.count != 0) {
            cursor.moveToFirst()
            profile_name.text = DataUtils.setNameAndSurname(cursor)

            profile_photo.setImageDrawable(DataUtils.getImageInDrawable(cursor))

            val data = DataUtils.setUserData(cursor)

            val adapter = DataListAdapter(this, data, R.layout.data_list_item)
            data_list.adapter = adapter
        }
    }
}
