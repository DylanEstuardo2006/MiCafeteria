package com.example.micafeteria.Contract

import com.example.micafeteria.Model.VariedadCafe

interface ContratoCafeteria {

      interface Vista
      {
          fun showCafes(nombres: List<String>);
          fun browseDetails(idCafeteria: Int);
      }
      interface Presentador
      {
          fun cargarCafes();
          fun verDetallesSeleccionados(posicion: Int);
      }
}