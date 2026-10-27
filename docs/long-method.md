# Code Smell: Long Method

**Responsável:** Thais Nicole Freitas de Oliveira
**Classe afetada:** `src/mrbet/MainMrBetSistema.java`
**Método afetado:** `main(String[] args)`
**Refatoração aplicada:** Extract Method

## 1. O que é o Long Method

Long Method é o smell de um método que ficou grande demais e passou a fazer
várias coisas ao mesmo tempo. Quanto mais longo o método, mais difícil é
entender o que ele faz, testá-lo e alterá-lo sem quebrar outra parte. Na
prática, quando sentimos vontade de colocar um comentário para separar
"blocos" dentro de um método, cada bloco costuma ser um método esperando para
ser extraído (Fowler, *Refactoring*).

## 2. Introdução do smell

**Commit:** `Introduz code smell Long Method no main de MainMrBetSistema`

No projeto original, o `main` só lia a opção do usuário e chamava um método
auxiliar para cada item do menu. Para introduzir o smell, o corpo de todos
esses métodos auxiliares foi copiado para dentro do `switch` do `main`, e os
métodos foram removidos.

Com isso, o `main` passou a ser responsável por:

- imprimir o menu principal;
- ler os dados de cada operação pelo `Scanner`;
- tratar os dois submenus (`B`: incluir/verificar e `T`: apostar/status);
- converter texto em número e tratar `NumberFormatException` em dois lugares;
- chamar o `MrBetController` e imprimir o resultado;
- encerrar o programa.

Trecho do método com o smell:

```java
switch (option) {
    case "M": {
        System.out.println("\n--- INCLUIR TIME ---");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        // ...
    }
    case "T": {
        System.out.println("\n--- OPERAÇÕES DE APOSTAS ---");
        String opcao = scanner.nextLine().toUpperCase();
        if (opcao.equals("A")) {
            // leitura de 4 campos + try/catch de conversão
        } else if (opcao.equals("S")) {
            // ...
        }
    }
    // ... mais 5 casos
}
```

**Problemas causados:**

- O método tem **135 linhas** e até **4 níveis de aninhamento**
  (`while` → `switch` → `if` → `try`).
- Não dá para entender uma operação isolada sem ler o método inteiro.
- Nenhuma operação pode ser reaproveitada ou chamada separadamente.
- Uma mudança em uma operação (ex.: pedir mais um campo na aposta) exige
  mexer no mesmo método que controla todo o fluxo do programa.

## 3. Refatoração

**Commit:** `Refatora Long Method do main com Extract Method`

Foi aplicada a refatoração **Extract Method**: cada bloco do `switch` virou
um método privado cujo nome diz o que ele faz.

| Bloco no `main` com smell     | Método extraído              |
|-------------------------------|------------------------------|
| impressão do menu             | `printMenu()`                |
| `case "M"`                    | `incluirTime(...)`           |
| `case "R"`                    | `recuperarTime(...)`         |
| `case "."`                    | `adicionarCampeonato(...)`   |
| `case "B"` (submenu)          | `timeCampeonatoOperations(...)` |
| `case "B"` → `I`              | `incluirTimeCampeonato(...)` |
| `case "B"` → `V`              | `verificarTimeCampeonato(...)` |
| `case "E"`                    | `exibirCampeonatosTime(...)` |
| `case "T"` (submenu)          | `apostasOperations(...)`     |
| `case "T"` → `A`              | `fazerAposta(...)`           |
| `case "T"` → `S`              | `exibirStatusApostas(...)`   |
| `case "!"`                    | `sairPrograma(...)`          |

Passo a passo seguido para cada bloco:

1. Identificar um trecho com uma única responsabilidade.
2. Criar um método privado com nome que descreva essa responsabilidade.
3. Passar como parâmetro o que o trecho usa de fora (`sistema` e `scanner`).
4. Substituir o trecho no `main` pela chamada ao novo método.
5. Rodar o programa com a mesma entrada e conferir que a saída não mudou.

`main` depois da refatoração:

```java
while (true) {
    printMenu();
    String option = scanner.nextLine().toUpperCase();

    switch (option) {
        case "M": incluirTime(sistema, scanner); break;
        case "R": recuperarTime(sistema, scanner); break;
        case ".": adicionarCampeonato(sistema, scanner); break;
        case "B": timeCampeonatoOperations(sistema, scanner); break;
        case "E": exibirCampeonatosTime(sistema, scanner); break;
        case "T": apostasOperations(sistema, scanner); break;
        case "!": sairPrograma(scanner); return;
        default:  System.out.println("OPÇÃO INVÁLIDA!");
    }
}
```

## 4. Resultado

| Métrica                         | Com smell | Refatorado |
|---------------------------------|-----------|------------|
| Linhas do `main`                | 135       | 39         |
| Nível máximo de aninhamento     | 4         | 2          |
| Métodos na classe               | 1         | 12         |
| Maior método auxiliar           | —         | ~20 linhas |

**Verificação do comportamento:** as duas versões foram compiladas e
executadas com a mesma entrada, que passa por todas as opções do menu
(inclusões válidas e repetidas, entradas vazias, números inválidos, submenus e
opções inválidas). As saídas das duas versões foram idênticas, o que confirma
que a refatoração não alterou o comportamento do sistema.

**Benefícios:**

- O `main` agora mostra só o fluxo do programa e cabe em uma tela.
- Cada operação pode ser lida, alterada e entendida isoladamente.
- Os nomes dos métodos documentam o código, sem precisar de comentários
  separando blocos.
