package com.example.algroup
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Principal_activity : AppCompatActivity() {

    data class Producto(val nombre: String, val cantidad: Int, val precio: Double){
    }


    // Lista de productos seleccionados en el carrito
    val carritoDeCompras = mutableListOf<Producto>()

    companion object {
        var instance: Principal_activity? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        instance = this

        // Obtener una referencia al botón btn_carrito
        val btnCarrito = findViewById<Button>(R.id.btn_carrito)

        // Configurar un OnClickListener para el botón btn_carrito
        btnCarrito.setOnClickListener {
            // Iniciar la actividad carrito
            val intent = Intent(this, carrito::class.java)
            startActivity(intent)
        }
    }


    // Esta función se llamará cuando se presione uno de los botones de productos
    fun onProductButtonClick(view: View) {
        val buttonId = view.id

        // Determinar cuál de los botones de productos se ha presionado y realizar acciones en consecuencia
        when (buttonId) {
            R.id.button1 -> {
                showQuantityDialog("Fuel Filters", "fuel_filters")
            }
            R.id.button2 -> {
                showQuantityDialog("Oil Filters", "oil_filters")
            }
            R.id.button3 -> {
                showQuantityDialog("Air Filters", "air_filters")
            }
            R.id.button4 -> {
                showQuantityDialog("Cabin Filters", "cabin_filters")
            }
            R.id.button5 -> {
                showQuantityDialog("Other Filters", "other_filters")
            }
            // Agrega casos para otros botones de productos según sea necesario
        }
    }

    // Mostrar un cuadro de diálogo para ingresar la cantidad de productos
    private fun showQuantityDialog(productName: String, productKey: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Agregar productos al carrito")
        builder.setMessage("¿Cuántos productos de $productName desea agregar al carrito?")

        val inputCantidad = EditText(this)

        // Establecer el tipo de entrada para el campo de cantidad
        inputCantidad.inputType = InputType.TYPE_CLASS_NUMBER

        // Establecer el diseño del campo de entrada
        inputCantidad.hint = "Cantidad"

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.addView(inputCantidad)

        builder.setView(layout)

        builder.setPositiveButton("Agregar") { dialog, _ ->
            val cantidadProductos = inputCantidad.text.toString().toIntOrNull() ?: 0

            if (cantidadProductos > 0) {
                // Establecer un precio fijo para el producto
                val precioProducto = 10.0 // Precio fijo de 10.0 para todos los productos

                // Calcular el precio total del producto
                val precioTotal = cantidadProductos * precioProducto

                // Agregar el producto con cantidad y precio al carrito de compras
                carritoDeCompras.add(Producto(productName, cantidadProductos, precioTotal))

                // Actualizar el total del carrito
                // Puedes hacerlo aquí o en la función onCreate
                // dependiendo de cómo desees mostrar el total en tu aplicación
                dialog.dismiss()
            } else {
                // Mostrar un mensaje de error si la cantidad ingresada es inválida
                Toast.makeText(this, "Ingrese una cantidad válida", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancelar") { dialog, _ ->
            dialog.cancel()
        }

        val dialog = builder.create()
        dialog.show()
    }


}


