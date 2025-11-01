package com.example.micafeteria.Presenter

import com.example.micafeteria.Contract.ContratoDetalles
import com.example.micafeteria.Model.ModeloCafeteria
import com.example.micafeteria.Model.VariedadCafe

class presenterDetalles (private val vista: ContratoDetalles.Vista): ContratoDetalles.Presentador {
    private val modelo = ModeloCafeteria();
    private var cafe: VariedadCafe? = null;

    override fun cargarCafe(id: Int)
    {
       cafe = modelo.obtenerCafeId(id);
       cafe?.let{
         vista.showDetalles(it);
       }
    }

    override fun calcularTotal(cantidad: Int) {
        cafe?.let{
            if(cantidad > it.stock)
            {
                vista.showError("Stock insuficiente. Máximo: ${it.stock}");
                vista.showTotal("$0.00");
            }
            else
            {
                vista.closError();
                val total = it.precio * cantidad;
                vista.showTotal(String.format("$%.2f",total));
            }
        }
    }
}