Language: PT-BR

Data de criação: 22/05/2025
Data de publicação: 26/05/2025

Atualizações: 28/05/2025 - 10:34

===================Sobre o Projeto===========================

Esse projeto surgiu como "sugestão" dada em um clube de programação
no qual participo durante meu período de faculdade. O intuito era que
fosse algo simples, mesmo cheio de erros, porém, por conta de certas
"AMIZADES", fui induzido a corrigir boa parte dos erros de entrada.

PS: Importante mencionar que no momento de criação deste projeto, estou
no meu 1° período/semestre de faculdade em Análise e Desenvolvimento de Sistemas.

É importante ressaltar que o projeto ainda pode ter alguns bugs,
não trabalha com números (mas é possível permitir que funcione) e também
conta com possíveis otimizações a serem feitas. Mas ele já opera bem para usuários
minimamente espertos...

================================================================
===================Para utilização de números===================

Atualmente, nas linhas 141 e 217, temos um tratamento de erros que
impede a utilização de números nas palavras a serem descobertas. Para
poder usar, remover os dois try/catch já deve ser o suficiente.

===============================================================
=======================Lista de palavras=======================

Para poder criar a lista de palavras, utilizei de um array de String, ou seja,
só é possível utilizar um tema por vez. Caso queira evitar fadiga, peça ajuda para
IAs para poder criar novas listas de palavras com diversos temas. 

Caso queira que seja sorteado mais de um tema no mesmo código, aqui vai uma sugestão:
- Crie uma array bidimencional(matriz) no lugar do array unidimencional(vetor) e preencha
com sua lista de palavras (preferencialmente, todas as listas com o mesmo tamanho).
- Gere um novo número aleatório para poder referenciar qual das listas você vai acessar.
- Com esse novo número aleatório gerado, faça um switch case para selecionar qual vai ser o tema
que deve aprecer no prompt.
- Faça as alterações necessárias para que o código funcione sem erros.

===============================================================
==========================Atualizações=========================

28/05/2025 - Corrigi um potencial erro quanto ao inserir uma entrada com letras maiúsculas. Na 
linha 215 inseri o comando "entrada = entrada.toLowerCase();" para transformar em minúsculo, 
pra só então começar as comparações.

===============================================================
