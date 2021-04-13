package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Funcionario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-09T15:49:27")
@StaticMetamodel(LogUsuario.class)
public class LogUsuario_ { 

    public static volatile SingularAttribute<LogUsuario, String> tabela;
    public static volatile SingularAttribute<LogUsuario, String> operacao;
    public static volatile SingularAttribute<LogUsuario, Funcionario> funcionarioIdFuncionario;
    public static volatile SingularAttribute<LogUsuario, String> timestamp;

}