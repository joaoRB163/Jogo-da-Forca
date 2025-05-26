import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * Jogo da Forca em Java
 * 
 * Este é um jogo "simples" de forca, onde o usuário deve adivinhar uma palavra relacionada ao tema que desejar,
 * e para testes, utilizei o tema "Informática". (Se precisar de uma lista rápida, peça ajuda para as IAs).
 * LEMBRE-SE DE ALTERAR O INTERVALO DO NÚMERO ALEATÓRIO (ln: 65) PARA O TAMANHO DO ARRAY DE PALAVRAS.
 * 
 * Para testar palavras específicas, você pode descomentar a linha 66 e definir o número aleatório manualmente.
 * 
 * 
 * O jogo permite que o usuário jogue várias vezes, digitando letras ou tentando adivinhar a palavra completa.
 * 
 * Se encontrar algum erro ou quiser sugerir melhorias, sinta-se à vontade para contribuir.
 */


public class Main {
    public static void main(String[] args) {

        // array de palavras para selecionar
        String[] palavras = {
                "servidor", "conexao", "rede", "teclado", "digital",
                "placa-mae", "backup", "monitor", "sistema", "mouse",
                "aplicativo", "internet", "seguranca", "banco-de-dados", "dados",
                "celular", "circuito", "git-hub", "download", "memoria",
                "navegador", "nuvem", "processador", "tablet", "usuario",
                "programa", "website", "upload", "arquivo", "senha",
                "driver", "algoritmo", "variavel", "funcao", "compilar",
                "script", "comando", "codigos", "pixel", "layout",
                "hardware", "software", "interface", "desenvolver", "metodo",
                "logica", "desktop", "laco", "online", "dev",
                "desespero", "problema"
        };

        // array de caracteres especiais
        char[] caracteresEspeciais = {
            '-', '@', '#', '$', '%', '^', '&', '*', '(', ')', 
            '_', '+', '=', '{', '}', '[', ']', '|', '\\', ':', 
            ';', '"', '\'', '<', '>', ',', '.', '?', '/', '~',
            '!', '`', '¨', '´', '£', '¢', '¥', '§', '°', '¬', 
            'ª', 'º', 'ç', 'Ç', 'à', 'á', 'â', 'ã', 'ä',
            'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ò',
            'ó', 'ô', 'õ', 'ö', 'ù', 'ú', 'û', 'ü', 'ÿ'

        };
        
        // leitor
        Scanner ler = new Scanner(System.in);

        // controle para repetir o programa
        int opcao = 0;

        // laço para começar um novo jogo
        do {

            // gerador de números aleatórios
            Random r = new Random();

            // armazena o número aleatório de 0 - X (X = quantidade de palavras)
            // contador de vidas
            int numeroAleatorio = r.nextInt(52), vidas = 6, c = 0, tentativas = 0;
            // numeroAleatorio = 17; /*descomente para testes*/

            // verificadores
            // verificador => verifica se pode finalizar o jogo (acertou a palavra inteira)
            // verificador2 => verifica se o vetor de letras acertadas já está completo
            // k => verifica se a letra digitada está na palavra
            boolean verificador = false, verificador2 = false, k = false;

            // transforma a string em um vetor de letras
            char[] vetLetras = palavras[numeroAleatorio].toCharArray();

            // vetor de letras acertadas
            char[] letrasAcertadas = new char[vetLetras.length];
            
            // vetor do bonequinho
            char[] bonequinho = new char[6];

            // preenche o vetor de letras acertadas com '_'
            for (int i = 0; i < letrasAcertadas.length; i++) {
                // só preenche o bonequinho com espaços vazios
                if (i < 6){
                    bonequinho[i] = ' ';
                }
                letrasAcertadas[i] = '_';
                if (vetLetras[i] == '-') {
                    letrasAcertadas[i] = '-';
                    // conta quantos espaços tem na palavra para descontar no total de letras
                    c++;
                }
            }

            // variável de entrada de letra
            String entrada;

            // array para armazenar letras/respostas digitadas erradas
            ArrayList<String> respostasErradas = new ArrayList<>();

            // apresentar o programa
            System.out.println("\n===================================================================");
            System.out.println("Bem-vindo ao jogo da forca!\nBoa sorte!\n");
            System.out.println("O tema é: INFORMÁTICA");
            System.out.println("A palavra possui " + (vetLetras.length - c) + " letras!\n\nEscreva uma letra:");

            // laço para poder repetir o jogo
            do {
                // função para imprimir o bonequinho
                printarBonequinho(bonequinho);
                // tratamento para evitar erros da variável entrada
                try {
                    System.out.print("| ");
                    // printa o vetor de letras acertadas
                    for (int i = 0; i < letrasAcertadas.length; i++) {
                        System.out.print(letrasAcertadas[i] + " ");
                    }
                    System.out.print("\nRespota: ");

                    // entrada do usuário
                    entrada = ler.nextLine();

                    // transforma a entrada em minúscula (evita erro com letras digitadas
                    // maiúsculas)
                    entrada.toLowerCase();

                    // transforma a entrada em um array de char
                    char[] verEntrada = entrada.toCharArray();

                    // cria um array para armazenar a entrada sem espaços
                    // veja o resto do código para entender como vai funcionar
                    StringBuilder entradaSemEspacos = new StringBuilder(verEntrada.length);

                    // verificador para letras erradas e contador de espaços
                    int cont = 0, letrasDigitadas = 0;

                    // verifica se foi digitado mais de uma letra
                    if (verEntrada.length > 1) {
                        try{
                            // verifica se é um número
                            Integer.parseInt(entrada);
                            System.out.println("\nVocê digitou um número! Tente novamente.");
                        } catch (Exception e) {
                            // laço para verificar se a palavra digitada está correta
                            for (int i = 0, j = 0; j < verEntrada.length; i++, j++) {
    
                                // tratamento para não dar erro
                                try {
                                    // verifica se algo foi digitado
                                    if (vetLetras[i] != verEntrada[j] && verEntrada[j] != ' ') {
                                        verificador = false;
                                        // verifica se tem letra na entrada
                                        letrasDigitadas++;
                                        cont = 1;
                                        // verifica se é um caractere especial
                                        if (containsChar(caracteresEspeciais, verEntrada[j])) {
                                            System.out.println("\nVocê digitou um caractere especial! Tente novamente.");
                                            cont = 2;
                                            break;
                                        }
                                        // verifica se o espaço digitado está no lugar certo (onde tem um hífen na palavra)
                                    } else if(verEntrada[j] == ' ' && vetLetras[i] == '-'){
                                        cont = 0;
                                        // só verifica se onde foi digitado espaço no começo da resposta
                                    } else if (verEntrada[j] == ' ' && letrasDigitadas == 0) {
                                        // seta cont para evitar problemas
                                        cont = 2;
                                        // retrocede um índice para não pular a letra 
                                        // (espaços no começo não valem para a resposta)
                                        i--;
                                    } else if (verEntrada.length < vetLetras.length){ // entrada menor que a palavra
                                        cont = 1;
                                        verificador = false;
                                    }
                                } catch (Exception E) {
                                    // se o número de letras digitadas for maior que a palavra
                                    System.out.println("\nVocê digitou mais letras do que a palavra possui!");
                                    // não desconta vidas
                                    cont = 2;
                                    break;
                                }
                            }
                            // se foi digitado somente espaços
                            if (cont == 2 && letrasDigitadas == 0) {
                                System.out.println("\nVocê digitou somente espaços! Tente novamente.");
                            }

                            // essa parte vai ficar sem comentários...
                            // se quiser entender, faça teste de mesa e raciocine com calma
                            // mas basicamente, é para remover os espaços extras
                            int contador = 0;
                            for (int i = 0; i < verEntrada.length; i++) {
                                if (verEntrada[i] == ' '){
                                    contador++;
                                }
                                if (verEntrada[i] != ' ') {
                                    entradaSemEspacos.append(verEntrada[i]);
                                    contador = 0;
                                } else if (verEntrada[i] == ' ' && contador < 2){
                                    entradaSemEspacos.append(verEntrada[i]);
                                }
                            }
                            entrada = entradaSemEspacos.toString();
                            // verifica se já foi digitado anteriormente
                            cont = mesmaResposta(respostasErradas, entrada, cont);
                        }

                        // se foi digitado somente uma letra
                    } else {
                        // verifica se a letra digitada é um caractere especial
                        // true se for, false se não for
                        boolean caractereEspecial = containsChar(caracteresEspeciais, verEntrada[0]);

                        try{
                            // verifica se é um número
                            Integer.parseInt(entrada);
                            System.out.println("\nVocê digitou um número! Tente novamente.");
                        } catch (Exception e) {
                            // verifica se a letra digitada já foi digitada anteriormente
                            for (int i = 0; i < letrasAcertadas.length; i++) {
                                if (letrasAcertadas[i] == verEntrada[0] && verEntrada[0] != '-') {
                                    // se a letra já foi digitada, adiciona 2 ao contador
                                    // 2 é para não ter conflito com outras verificações
                                    // (preguiça de criar um novo contador só pra isso)
                                    cont = 2;
                                }
                            }
                            if (cont == 2) {
                                System.out.println("\nVocê digitou essa letra!");
                            } else {
                                // laço para verificar se a letra está correta e em que posição está
                                for (int i = 0; i < vetLetras.length; i++) {
                                    if (verEntrada[0] == vetLetras[i] && !caractereEspecial && verEntrada[0] != ' ') {
                                        // insere a letra de acordo com a posição na palavra original
                                        letrasAcertadas[i] = verEntrada[0];
                                        // verifica se a letra está na palavra
                                        // 2 é para não ter conflito com outras verificações
                                        // (preguiça de criar um novo contador só pra isso)
                                        cont = 2;
                                    } else  if (verEntrada[0] == ' '){
                                        System.out.println("\nVocê digitou um espaço! Tente novamente.");
                                        break;
                                    } else if (caractereEspecial) {
                                        System.out.println("\nVocê digitou um caractere especial! Tente novamente.");
                                        cont = 2;
                                        break;
                                    }
                                }
                                // se a letra não estiver na palavra, adiciona 1 ao contador
                                // VER AQUI DEPOIS
                                if (cont == 0 && verEntrada[0] != '-' && verEntrada[0] != ' ') {
                                    cont = 1;
                                    // verifica se já foi digitado anteriormente
                                    cont = mesmaResposta(respostasErradas, entrada, cont);
                                }
                            }
                        }
                    }

                    // verifica se as letras que foram acertadas já completaram a palavra
                    for (int i = 0; i < letrasAcertadas.length; i++) {
                        if (letrasAcertadas[i] != vetLetras[i]) {
                            k = true;
                        }
                    }

                    // se as letras foram todas acertadas, o verificador2 fica true
                    if (!k) {
                        verificador2 = true;
                    } else { // se não, seta o verificador k para false
                        k = false;
                    }

                    // conta quantas tentativas foram feitas
                    tentativas++;

                    if (cont == 3) {
                        System.out.println("\nVocê já enviou essa resposta antes! Tente novamente.");
                    }

                    // se não estiver correto, diminui uma vida
                    // "monta" o bonequinho de acordo com a quantidade de vidas
                    if (cont == 1) {
                        switch (vidas) {
                            case 6:
                                bonequinho[0] = 'O';
                                break;
                            case 5:
                                bonequinho[1] = '|';
                                break;
                            case 4:
                                bonequinho[2] = '/';
                                break;
                            case 3:
                                bonequinho[3] = '\\';
                                break;
                            case 2:
                                bonequinho[4] = '/';
                                break;
                            case 1:
                                bonequinho[5] = '\\';
                                break;
                            }
                            vidas--;
                            printar(respostasErradas, entrada);
                        // se a vida zerar, exibe uma mensagem de derrota e termina o jogo
                        if (vidas == 0) {
                            System.out.println("");
                            printarBonequinho(bonequinho);
                            System.out.println("\nVocê perdeu!\nA palavra era: " + palavras[numeroAleatorio]);
                        }

                        // se a palavra for completada ou for digitada corretamente, exibe uma mensagem de vitória
                        // se a palavra difigitada for do mesmo tamanho que a original e o contador for 0 
                        // ou se o verificador2 for true finaliza o jogo
                    } else if (verEntrada.length == vetLetras.length && cont == 0 || verificador2) {
                        System.out.println("\nParabéns! Você acertou em " + tentativas + " tentativas!" +
                                "\nA palavra era: " + palavras[numeroAleatorio]);
                        verificador = true;
                    }
                    // exibe uma mensagem de erro se a entrada não for válida
                } catch (Exception e) {
                    System.out.println("\nEntrada inválida! Você não perdeu nenhuma vida.\nTente novamente.");
                }

            } while (!verificador && vidas != 0);
            System.out.println("\n===================================================================\nObrigado por jogar!\n");
            
            // laço pra confirmar que a escolha não vai dar erro
            do {
                System.out.println("Você deseja começar outro jogo?\n1 - SIM\t\t2 - NÃO");

                // String para receber a entrada e depois tentar converter em um int
                String entrada2 = ler.nextLine();                

                // tratamento de erro
                try {
                    //conversão da String para inteiro
                    opcao = Integer.parseInt(entrada2);
                } catch (Exception e) {
                    System.out.println("\nOpção inválida! Digite novamente.\n");
                    opcao = 0;
                }
            } while (opcao != 2 && opcao != 1);
            
        } while (opcao != 2);
        
        System.out.println("\n===================================================================\nFinalizando o programa!\n");
    }

    // método para imprimir as respostas erradas
    // PS: achei que ia usar mais de uma vez e acabei não usando, então deixei por
    // preguiça de tirar
    public static void printar(ArrayList<String> respostasErradas, String entrada) {
        // adiciona a resposta errada no array de letras erradas
        respostasErradas.add(entrada);

        // exibe as respostas erradas
        System.out.print("Respostas erradas: ");

        for (int i = 0; i < respostasErradas.size(); i++) {
            if (i == 0) {
                System.out.print(respostasErradas.get(i));
            } else {
                System.out.print(" - " + respostasErradas.get(i));
            }
        }

        System.out.println("");
    }

    // método para verificar se a letra digitada já foi tentada para não perder
    // vidas
    public static int mesmaResposta(ArrayList<String> respostasErradas, String entrada, int cont) {
        int c = cont;
        // verifica se a letra digitada já foi tentada
        for (int i = 0; i < respostasErradas.size(); i++) {
            if (respostasErradas.get(i).equals(entrada)) {
                return 3;
            }
        }
        return c;
    }

    // método para imprimir o bonequinho da forca
    public static void printarBonequinho(char[] bonequinho) {
        System.out.println("");
                System.out.println("___________________");
                System.out.println("|                 |");
                System.out.println("|                 " + bonequinho[0]);
                System.out.println("|                " + bonequinho[2] + bonequinho[1] + bonequinho[3]);
                System.out.println("|                 " + bonequinho[1]);
                System.out.println("|                " + bonequinho[4] + " " + bonequinho[5]);
                System.out.println("|");
    }
    // método para verificar se um array de caracteres contém um caractere especial
    public static boolean containsChar(char[] array, char targetChar) {
        for (char c : array) {
            if (c == targetChar) {
                return true; // Encontrou o caractere
            }
        }
        return false; // Não encontrou o caractere
    }
}
