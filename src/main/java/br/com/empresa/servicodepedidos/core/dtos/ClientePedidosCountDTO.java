import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Transient;
import jakarta.persistence.Query;
import java.util.List;

@Entity
@SqlResultSetMapping(
        name = "ClientePedidosCountMapping",
        entities = @EntityResult(entityClass = ClientePedidosCountDTO.class, fields = {
                @FieldResult(name = "clienteId", column = "cliente_id"),
                @FieldResult(name = "clienteNome", column = "cliente_nome"),
                @FieldResult(name = "quantidadePedidos", column = "quantidade_pedidos")
        })
)
public class ClientePedidosCountDTO {

    private Long clienteId;
    private String clienteNome;
    private Long quantidadePedidos;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public Long getQuantidadePedidos() {
        return quantidadePedidos;
    }

    public void setQuantidadePedidos(Long quantidadePedidos) {
        this.quantidadePedidos = quantidadePedidos;
    }
}