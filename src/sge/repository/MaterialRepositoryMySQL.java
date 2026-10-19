
package sge.repository;

import sge.model.Material;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialRepositoryMySQL {

    private final String utilizador;
    private final String senha;

    public MaterialRepositoryMySQL(String utilizador, String senha) {
        this.utilizador = utilizador;
        this.senha = senha;
    }

    private Connection conectar() throws SQLException {
        return ConexaoMySQL.conectar(utilizador, senha);
    }

    // CREATE - Registar material
    public void adicionar(Material material) throws SQLException {
        String sql = """
            INSERT INTO materiais
            (nome, quantidade, preco, total_vendido, imagem,
             categoria, fornecedor, disponivel_caixa)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(
                 sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, material.getNome());
            ps.setInt(2, material.getQuantidade());
            ps.setDouble(3, material.getPreco());
            ps.setInt(4, material.getTotalVendido());
            ps.setString(5, material.getImagem());
            ps.setString(6, material.getCategoria());
            ps.setString(7, material.getFornecedor());
            ps.setBoolean(8, material.isDisponivelCaixa());

            ps.executeUpdate();

            // Recuperar o ID criado pelo MySQL
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    material.setId(rs.getInt(1));
                }
            }
        }
    }

    // READ - Listar materiais
    public List<Material> listar() throws SQLException {
        List<Material> lista = new ArrayList<>();

        String sql = """
            SELECT id, nome, quantidade, preco, total_vendido,
                   imagem, categoria, fornecedor,
                   disponivel_caixa, data_registo
            FROM materiais
            ORDER BY nome
            """;

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Material material = new Material(
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco"),
                    rs.getString("imagem"),
                    rs.getString("categoria"),
                    rs.getString("fornecedor")
                );

                material.setId(rs.getInt("id"));
                material.setTotalVendido(rs.getInt("total_vendido"));
                material.setDisponivelCaixa(
                    rs.getBoolean("disponivel_caixa")
                );

                Timestamp data = rs.getTimestamp("data_registo");
                if (data != null) {
                    material.setDataRegisto(data.toString());
                }

                lista.add(material);
            }
        }

        return lista;
    }

    // UPDATE - Actualizar material pelo ID
    public boolean actualizar(int id, Material material)
            throws SQLException {

        String sql = """
            UPDATE materiais
            SET nome = ?, quantidade = ?, preco = ?,
                total_vendido = ?, imagem = ?, categoria = ?,
                fornecedor = ?, disponivel_caixa = ?
            WHERE id = ?
            """;

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, material.getNome());
            ps.setInt(2, material.getQuantidade());
            ps.setDouble(3, material.getPreco());
            ps.setInt(4, material.getTotalVendido());
            ps.setString(5, material.getImagem());
            ps.setString(6, material.getCategoria());
            ps.setString(7, material.getFornecedor());
            ps.setBoolean(8, material.isDisponivelCaixa());
            ps.setInt(9, id);

            return ps.executeUpdate() > 0;
        }
    }

    // UPDATE - Actualizar usando o ID do próprio material
    public boolean actualizar(Material material)
            throws SQLException {

        if (material.getId() <= 0) {
            throw new SQLException(
                "O material ainda não possui um ID válido no MySQL."
            );
        }

        return actualizar(material.getId(), material);
    }

    // DELETE - Eliminar material pelo ID
    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM materiais WHERE id = ?";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
