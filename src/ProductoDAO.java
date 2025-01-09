/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author roscr
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public boolean insertarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            //ps.setInt(5, producto.getId());
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getCantidad());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getCantidad());
            ps.setInt(5, producto.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }
    }

   public List<Producto> getProductos() {
       String sql = "SELECT * FROM productos";
       List<Producto> lista = new ArrayList<>();
       try (Connection con = ConexionBD.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

           while (rs.next()) {
               Producto producto = new Producto(
                       rs.getString("nombre"),        // Nombre del producto
                       rs.getString("descripcion"),  //
                       rs.getDouble("precio"),       // Precio del producto
                       rs.getInt("cantidad")         // Cantidad del producto
               );
               producto.setId(rs.getInt("id")); // Establecer el ID del producto
               lista.add(producto);            // Agregar a la
           }
       } catch (SQLException e) {
           System.out.println("Error al listar productos: " + e.getMessage());
       }
       return lista;
   }

    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    public List<Producto> listarProductos() {
       String sql = "SELECT * FROM productos";
    List<Producto> lista = new ArrayList<>();
    try (Connection con = ConexionBD.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            // Crear el objeto Producto con los datos obtenidos del ResultSet
            Producto producto = new Producto(
                rs.getString("nombre"),        // Nombre del producto
                rs.getString("descripcion"),  // Descripción del producto
                rs.getDouble("precio"),       // Precio del producto
                rs.getInt("cantidad")         // Cantidad del producto
            );
            producto.setId(rs.getInt("id")); // Establecer el ID del producto
            lista.add(producto);            // Agregar a la lista
        }
    } catch (SQLException e) {
        System.out.println("Error al listar productos: " + e.getMessage());
    }
    return lista;
}
}