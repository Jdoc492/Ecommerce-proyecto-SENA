package com.example.algroup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class carrito : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito2)

        val carritoTextView = findViewById<TextView>(R.id.carritoTextView)

        if (Principal_activity.instance?.carritoDeCompras?.isNotEmpty() == true) {
            // Si hay productos en el carrito, crea una cadena para mostrar los productos y cantidades
            val carritoString = buildCarritoString(Principal_activity.instance?.carritoDeCompras ?: emptyList())
            carritoTextView.text = carritoString
        } else {
            // Si el carrito está vacío, muestra un mensaje
            carritoTextView.text = "El carrito está vacío"
        }
    }

    // Mueve esta función fuera del método onCreate
    private fun buildCarritoString(productos: List<Principal_activity.Producto>): String {
        val stringBuilder = StringBuilder()
        var total = 0.0 // Cambia la declaración de total a Double

        for (producto in productos) {
            stringBuilder.append("${producto.nombre}:\n Cantidad: ${producto.cantidad} x valor = ${producto.precio} COP\n-------------------------------------------------------\n")

            // Calcula el subtotal para este producto y suma al total
            val subtotal = producto.cantidad * producto.precio
            total += subtotal.toDouble() // Convierte subtotal a Double antes de sumar
        }

        // Agrega una línea para mostrar el total
        stringBuilder.append("\nTotal a pagar: $total COP")

        return stringBuilder.toString()
    }
}

