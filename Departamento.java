import java.util.*;
public class Departamento {
    private final String nome; private final List<String> cargos;
    public Departamento(String nome, String... cargos){this.nome=nome;this.cargos=Arrays.asList(cargos);}
    public String getNome(){return nome;} public List<String> getCargos(){return cargos;}
    public static List<Departamento> padroes(){return Arrays.asList(
        new Departamento("Administração e Direção","Presidente","Gestor Administrativo","Assistente Administrativo"),
        new Departamento("Recursos Humanos","Responsável de RH","Técnico de RH"),
        new Departamento("Financeiro e Contabilidade","Contabilista","Técnico Financeiro"),
        new Departamento("Compras e Aprovisionamento","Responsável de Compras","Técnico de Aprovisionamento"),
        new Departamento("Armazém e Stock","Chefe de Armazém","Fiel de Armazém","Operador de Stock"),
        new Departamento("Vendas e Caixa","Supervisor de Vendas","Vendedor","Caixa"),
        new Departamento("Logística e Transporte","Supervisor de Logística","Motorista","Ajudante de Logística"),
        new Departamento("Operações / Obras","Encarregado de Obras","Pedreiro","Servente","Carpinteiro","Eletricista","Canalizador","Pintor","Armador de Ferro"),
        new Departamento("Segurança e HST","Técnico de Segurança","Agente de HST"),
        new Departamento("TI / Sistemas","Técnico de TI","Administrador do Sistema")
    );}
    public static String[] nomes(){return padroes().stream().map(Departamento::getNome).toArray(String[]::new);}
    public static String[] cargosDe(String nome){for(Departamento d:padroes())if(d.nome.equals(nome))return d.cargos.toArray(new String[0]);return new String[0];}
}
