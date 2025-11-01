package com.example.micafeteria

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.micafeteria.Contract.ContratoDetalles
import com.example.micafeteria.Model.VariedadCafe
import com.example.micafeteria.Presenter.presenterDetalles

class DetalleViewActivity : AppCompatActivity(), ContratoDetalles.Vista {
    private lateinit var presentador: ContratoDetalles.Presentador
    private lateinit var txvNombre: TextView
    private lateinit var txvVariedad: TextView
    private lateinit var txvPrecio: TextView
    private lateinit var txvStock: TextView
    private lateinit var edtCantidadCompra: EditText
    private lateinit var txvTotal: TextView
    private lateinit var txvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_view2)

        txvNombre = findViewById(R.id.txvNombre)
        txvVariedad = findViewById(R.id.txvVariedad)
        txvPrecio = findViewById(R.id.txvPrecio)
        txvStock = findViewById(R.id.txvStock)
        edtCantidadCompra = findViewById(R.id.edtCantidadCompra)
        txvTotal = findViewById(R.id.txvTotal)
        txvError = findViewById(R.id.txvError)

        presentador = presenterDetalles(this);

        val idCafe = intent.getIntExtra("idCafe", -1)
        if (idCafe != -1) {
            presentador.cargarCafe(idCafe)
        }
        edtCantidadCompra.addTextChangedListener { editable ->
            val cantidad = editable.toString().toIntOrNull() ?: 0
            presentador.calcularTotal(cantidad)
        }

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun showDetalles(cafe: VariedadCafe) {
        txvNombre.text = cafe.nombre
        txvVariedad.text = cafe.descripcion
        txvPrecio.text = "Precio: $${cafe.precio}"
        txvStock.text = "Stock disponible: ${cafe.stock}"
    }

    override fun showTotal(total: String) {
        txvTotal.text = "Total a pagar: $total"
    }

    override fun showError(mensaje: String) {
        txvError.text = mensaje
        txvError.visibility = TextView.VISIBLE
    }

    override fun closError() {
        txvError.visibility = TextView.GONE
    }
}