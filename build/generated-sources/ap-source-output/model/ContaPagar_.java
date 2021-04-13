package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Compra;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-09T15:49:27")
@StaticMetamodel(ContaPagar.class)
public class ContaPagar_ { 

    public static volatile SingularAttribute<ContaPagar, String> dataPagamento;
    public static volatile SingularAttribute<ContaPagar, Integer> idContaPagar;
    public static volatile SingularAttribute<ContaPagar, Compra> compraIdCompra;
    public static volatile SingularAttribute<ContaPagar, String> dataVencimento;
    public static volatile SingularAttribute<ContaPagar, String> vencida;
    public static volatile SingularAttribute<ContaPagar, String> pagamento;

}