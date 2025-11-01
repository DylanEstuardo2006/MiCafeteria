package com.example.micafeteria

import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.micafeteria.Contract.ContratoCafeteria
import com.example.micafeteria.Model.VariedadCafe
import com.example.micafeteria.Presenter.presenterMain
import kotlin.reflect.KClass



class MainActivity : AppCompatActivity(), ContratoCafeteria.Vista {

    private lateinit var spnCafes: Spinner;
    private lateinit var btnVerDetalles: Button;
    private lateinit var presentador: ContratoCafeteria.Presentador;

     override fun onCreate(savedInstanceState: Bundle?)
     {
         super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

         spnCafes = findViewById(R.id.spnVariedadCafe)
         btnVerDetalles = findViewById(R.id.btnVerDetalles)

         presentador = presenterMain(this)
         presentador.cargarCafes()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }

    override fun showCafes(nombres: List<String>) {
        val adaptador = ArrayAdapter(this, android.R.layout.simple_gallery_item, nombres);
        adaptador.setDropDownViewResource(android.R.layout.simple_gallery_item);
        spnCafes.adapter = adaptador;
    }

    override fun browseDetails(idCafeteria: Int) {
        val intent = Intent(this, DetalleViewActivity::class.java)
        intent.putExtra("idCafe", idCafeteria)
        startActivity(intent);
    }

}