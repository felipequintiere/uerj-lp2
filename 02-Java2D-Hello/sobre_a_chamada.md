### 1.2.2 - Sobre a chamada "super.paint(g)"
[código de exemplo do java2D](https://github.com/fsantanna-uerj/LP2/blob/main/Exemplos/02-Java2D-Hello/Hello2DApp.java#L26)

---

### 1. o que acontece (visualmente) se a linha com a chamada "super.paint(g)" for omitida?

Ao redimensionar a janela, novos objetos são desenhados acima dos já existentes.

---

### 2. tente explicar com as suas próprias palavras a utilidade da chamada "super.paint(g)" e o porquê do comportamento observado.

Nesse contexto, a função é utilizada para limpar o quadro (frame) antes de redesenhá-lo com o intuito de novos objetos não se sobreporem aos já existentes. Sem a chamada do método, o programa deixa de limpar a tela, e assim, um novo desenho é feito por cima do antigo.


Suponha que um programa utilize o número de pixels da janela de ambos os eixos para determinar a posição relativa das formas geométricas:

```java
        g2d.drawLine(w/4,h/2, w/2,0);
        g2d.drawLine(w/2,0,3*(w/4),h/2);
        g2d.drawLine(w/4,h/2, w/2,h);
        g2d.drawLine(w/2,h,3*(w/4),h/2);
```

Quando a janela for redimensionada, o tamanho em pixels será alterado e, consequentemente, a posição relativa das formas geométricas a serem redesenhadas mudará. Para cada redimensionamento, há a necessidade de redesenhar as formas geométricas. No entanto, como o método paint() da superclasse (JFrame) não será executado, a tela não será limpa e novos objetos serão desenhados acima dos já existentes.
