# EVE - EDITOR VETORIAL

# Domínio da Aplicação
O **Eve** é um editor vetorial desenvolvido em Java utilizando Java2D. O programa permite criar, remover e manipular diferentes tipos de figuras geométricas.

---

#### Instalação
``` bash
git clone https://github.com/felipequintiere/uerj-lp2.git
cd ./uerj-lp2/Eve/
javac Eve.java
```
nota: execute o programa com `java Eve`

#### Uso do Programa
```
$ java Eve -h
```
---

## Figuras Disponíveis
`Q` Linha  
`W` Triângulo  
`E` Elipse  
`R` Retângulo  
`T` Texto  

NOTA: cada tecla está associada à criação de uma figura  
NOTA: a nova figura é criada na posição atual do ponteiro do mouse

## Foco e Hover
- **clique esquerdo** sobre uma figura: seleciona a figura e a põe em **foco**
- **clique esquerdo** fora das figuras: remove o foco
- tecla `O`: altera o foco para outra figura

NOTA: a figura em **foco** é destacada em vermelho e possui alças de manipulação  
NOTA: a figura sob o ponteiro do mouse é identificada como **hover** e é destacada em azul  

## Movimentação das Figuras
pressione o **botão esquerdo** sobre a figura em foco e:

1. Mouse:
- arraste o mouse para mover a figura

2. Teclado
- use as teclas de setas (UP, DOWN, LEFT, RIGHT)  
NOTA: cada pressionamento das teclas desloca a figura em 30 pixels  

## Tamanho
A figura pode ser redimensionada ao arrastar as alças exibidas quando uma figura está em foco ou pressionando o **botão direito do mouse** sobre a figura e arrastando

NOTA: o comportamento da alça depende do tipo de figura

## Z-order
Para alterar a posição da figura em foco:

`Ctrl + U`: move a figura para cima na ordem da lista  
`Ctrl + D`: move a figura para baixo na ordem da lista  

## Remoção
Para remover a figura atualmente em foco:  
`D` || `Delete` || `Backspace`

## Cores
A tecla `C` permite alterar as cores da figura em foco. O programa solicita primeiro a **cor de contorno** e depois a **cor de fundo**, quando aplicável.

NOTA: a entrada deve ser feita em hexadecimal, utilizando seis dígitos no formato `RRGGBB`
