<!--
- Para as perguntas a seguir, escolha um editor gráfico vetorial diferente do "Dia".
- Indique a sua escolha nas respostas.
- Não use as mesmas operações, atributos e hierarquia discutidos nos vídeos.

1. Que outras operações gráficas são comuns? Explique o comportamento esperado de pelo menos 3 operações (1 parágrafo para cada).
2. Que atributos são compartilhados entre as figuras geométricas? Indique pelo menos 5 atributos.
3. Que atributos são específicos a certas figuras geométricas. Indique pelo menos 2 atributos específicos de 3 figuras diferentes.
4. Desenhe uma hierarquia de classes de objetos gráficos com pelo menos 2 níveis de altura e 5 de largura.
-->

# SVG-Edit
um editor vetorial web-based escrito em java

https://github.com/svg-edit/svgedit?tab=readme-ov-file

---

# 1.

- agrupar/desagrupar objetos\
agrupar: combinar dois ou mais objetos em uma única unidade, permitindo outras operações sobre o conjunto; desagrupar: reverter um agrupamento existente, tornando cada objeto editável individualmente novamente

- duplicar\
criar uma cópia do objeto (ou grupo de objetos) selecionado, replicando os atributos e propriedades no novo objeto

- trazer para frente/enviar para trás\
permutar a ordem de empilhamento dos objetos em relação aos demais elementos existentes\
trazer para frente: mover o objeto (ou grupo de objetos) para a camada superior\
enviar para trás: posicionar o objeto atrás dos outros objetos

- inverter verticalmente/horizontalmente:\
criar uma versão espelhada do objeto selecionado (verticalmente ou horizontalmente)

# 2.
- posição horizontal
- posição vertical
- transparência
- inclinação
- cor do traço

# 3.
- retângulos, elipses e estrelas:
  - cor de preenchimento

- estrela:
  - número de vértices
  - quão pontiagudas são as pontas (pointness)
  - posição das pontas em relação ao centro (radial shift)

- reta:
  - tipo de traçado
  - tipo de extremidade

<!--
- imagem (objeto):
  - URL
-->

# 4. [hierarquia de classes](./hierarquia.png)
