package com.example.micafeteria.Contract

import com.example.micafeteria.Model.VariedadCafe

interface ContratoDetalles
{
    interface Vista
    {
        fun showDetalles(cafe: VariedadCafe);
        fun showTotal(total: String);
        fun showError(mensaje: String);
        fun closError();
    }
    interface Presentador
    {
        fun cargarCafe(id: Int);
        fun calcularTotal(cantidad: Int );
    }
}