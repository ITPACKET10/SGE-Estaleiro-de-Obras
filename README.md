# SGE - Sistema de Gestão de Estaleiro de Obras

Projeto semestral em Java Swing para gestão de materiais, stock, vendas e utilizadores.

## Conceitos de POO demonstrados
- Encapsulamento nas classes de domínio.
- Herança: Presidente, Gestor e Funcionario herdam de Usuario.
- Polimorfismo: getPermissoes() e getMenuOpcoes() são sobrescritos por perfil.
- Interface: Autenticavel e Crud<T>.
- CRUD: MaterialCRUD e operações existentes de materiais/utilizadores.

## Executar no IntelliJ IDEA
1. Criar/abrir um projeto Java e usar esta pasta como Sources Root.
2. Confirmar que existe um JDK configurado (17 ou superior recomendado).
3. Executar Main.java.

A imagem `/imagem/construcao.png` é opcional: se não existir, a tela de login usa um fundo em gradiente.

## Atualização v10
- Dashboard próprio do funcionário com departamento, cargo, presença do dia e faltas do mês.
- Geração automática de faltas para dias úteis anteriores do mês.
- Fins de semana e férias/licenças aprovadas não geram falta.
- Folha salarial passa a contar faltas registadas em presenças.txt.
- Justificação aprovada reverte a falta para efeitos do desconto salarial interno.

## Versão 13
- Centro de notificações contextual por perfil/departamento.
- Dashboard do funcionário adaptado ao departamento e às tarefas autorizadas.
- Relatório completo ampliado com Salários, Presenças e Departamentos.
- Alertas de presença, faltas, stock baixo, salários pendentes e justificações pendentes.
- Mantido o controlo de permissões: funcionário comum não processa salários.


## v14 - Clientes
- CRUD completo de clientes com persistência em clientes.txt.
- NUIT, contacto, email e endereço.
- Histórico resumido de compras e total gasto.
- Acesso para Presidente, Gestor e equipa de Vendas/Caixa.
- Operações de clientes registadas na auditoria.

## Novidades v15
- Caixa integrado ao cadastro de clientes: seleção de cliente registado ou venda avulsa.
- Vendas passam a gerar automaticamente movimento de stock `SAÍDA - Venda`.
- Vendas passam a ser registadas na auditoria do sistema.
- Histórico de cliente continua calculado a partir das vendas associadas ao nome do cliente.


## Versão 16
- CRUD completo de fornecedores: registar, consultar, atualizar e remover.
- Histórico de compras preservado mesmo após remoção de fornecedor.
- Fornecedores mostram número de compras e total comprado.
- Compras/Aprovisionamento ganhou indicadores de compras, unidades adquiridas e valor total.
- Operações de fornecedor passam a integrar a auditoria.

## Versão 17
- Recuperação de senha passa a ser feita pelo próprio utilizador no Login, validando email, BI e contacto.
- Removida do menu do Presidente a recuperação administrativa de senha.
- Funcionário de Armazém e Stock passa a ter Entrada de Material e Movimentos de Stock.
- Funcionário de Recursos Humanos passa a ter registo/listagem de trabalhadores, presenças/justificações e férias/licenças.
- Registo de trabalhador passa a incluir método de pagamento, número/conta e titular (M-Pesa, e-Mola, transferência ou dinheiro).
- A opção SEM_ACESSO é respeitada independentemente do cargo: trabalhador sem conta não recebe login.
- Adicionado fundo de construção em imagem/construcao.png, com fallback para execução direta no IntelliJ.

## v18 - Mapa de Presenças
- Mapa completo: trabalhador, departamento, cargo, data, entrada, saída, estado e observação.
- RH/Presidente pode marcar presença manual de trabalhadores sem acesso ao sistema.
- Trabalhadores sem login passam a ser identificados pela chave BI no controlo de presença.
- Geração automática de faltas passa a considerar também trabalhadores sem acesso.
- Ordenação da tabela de presenças e auditoria da marcação manual.

## Novidades v19
- Gestão de Obras/Projectos com estado e responsável.
- Requisições de materiais associadas a uma obra.
- Fluxo: solicitação -> aprovação/rejeição -> entrega pelo armazém.
- A entrega aprovada reduz o stock e cria movimento `SAÍDA - Obra`.
- Permissões por perfil/departamento para solicitar e tratar requisições.


## V22 - RH e Trabalhadores
- Cadastro ampliado com NUIT e contacto de emergência.
- Contratos: indeterminado, prazo certo, prazo incerto e estágio.
- Código interno automático do trabalhador.
- Separação entre trabalhador com acesso e sem acesso ao SGE.
- Métodos de pagamento: M-Pesa, e-Mola, transferência bancária e dinheiro.
- Lista de trabalhadores mostra contrato, pagamento e estado de acesso.
- Correção da leitura do perfil no ficheiro trabalhadores.txt.
- Nova classe Trabalhador para representar dados laborais independentemente da conta de utilizador.

## V24 - Gestão de contratos
- Novo painel de contratos para Presidente e Recursos Humanos.
- Estado automático: ativo, indeterminado, a expirar em até 30 dias ou expirado.
- Indicadores visuais para contratos ativos, próximos do fim, expirados e indeterminados.
- Notificação ao Presidente para contratos que terminam nos próximos 30 dias.
- Nova classe ContratoTrabalho para representar o domínio laboral.
- Correção: documentos de credenciais deixaram de usar extensão .pdf sem serem PDFs reais; agora são gerados corretamente como .txt.
- Projeto recompilado com javac após as alterações.

## V25 - Inventário físico
- Contagem física de materiais no armazém.
- Comparação entre stock do sistema e quantidade realmente contada.
- Registo de diferenças sem alterar automaticamente o stock.
- Opção autorizada para reconciliar o stock com a contagem física.
- Geração de movimento de ajuste e registo de auditoria.

## V26 - Devoluções de Vendas
- Registo de devoluções totais ou parciais a partir de vendas existentes.
- Impede devolver quantidade superior à quantidade efetivamente vendida ainda disponível para devolução.
- Reposição automática do material no stock.
- Criação de movimento `ENTRADA - Devolução`.
- Registo de motivo, responsável, valor devolvido e auditoria.
- Acesso para Presidente, Gestor e funcionários de Vendas/Caixa.
