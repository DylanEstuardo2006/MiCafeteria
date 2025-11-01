package com.example.micafeteria.Presenter

import com.example.micafeteria.Contract.ContratoCafeteria
import com.example.micafeteria.MainActivity
import com.example.micafeteria.Model.ModeloCafeteria
import com.example.micafeteria.Model.VariedadCafe

class presenterMain (private val vista: ContratoCafeteria.Vista):  ContratoCafeteria.Presentador{
   private val modelo = ModeloCafeteria();
    private var listaCafes = emptyList<VariedadCafe>();
    override fun cargarCafes() {
        listaCafes = modelo.obtenerVariedadesDeCafe();
        val nombres = listaCafes.map {it.nombre};
        vista.showCafes(nombres);
    }

    override fun verDetallesSeleccionados(posicion: Int)
    {
        if(posicion in listaCafes.indices)
        {
            val cafeSeleccionado = listaCafes[posicion];
            vista.browseDetails(cafeSeleccionado.id);
        }
    }
}