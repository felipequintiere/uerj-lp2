``` java
public class RectApp {
	public static void main (String[] args) {
		Rect r1 = new Rect(1,1, 10,10);
		r1.print();
	}
}
class Rect {
	int x, y;
	int w, h;
	Rect (int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	void print () {
		System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
			this.w, this.h, this.x, this.y);
	}
}
```

# 2.1.2
- Descreva todo o processo de *execução* do programa com o foco na propriedade "w" do retângulo.
- Descreva a ordem exata de execução de cada etapa do programa. Exemplo:
1. Inicia o método "main()"
2. ...
3. ...
- Descreva esses passos explicando principalmente os acessos ao campo "w".
- Em outras palavras, descreva por tópicos ou linha a linha tudo o que está relacionado, direta ou indiretamente, com o campo "w".

# Resposta
0. a JVM inicia a execução da aplicação
1. execução do programa a partir do método `main()` da classe `RectApp`
1. dentro de `main()`, ocorre a **alocação** de memória para um novo objeto do tipo `Rect` (instanciação)
	``` java
	    Rect r1 = new Rect(1,1,10,10);
	```
	onde a JVM reserva espaço para os campos x, y, w, h:
	``` java
		int x, y;
		int w, h;
	```

1. execução do construtor `Rect()` <br>
	os campos x, y, w, h foram definidos na classe `Rect`
	``` java
	Rect (int x, int y, int w, int h) {  
		this.x  =  x;  
		this.y  =  y;  
		this.w  =  w;  
		this.h  =  h;  
	}
	```
	parâmetros recebidos (item 2): <br>
	`x=1`, `y=1`, `w=10`, `z=10` <br>
	- **acesso ao campo `w`:** <br>
	```this.w = w;``` <br>
	significa que o campo `w` do objeto `r1` recebe o valor do parâmetro `w` passado para o construtor `Rect()`
1.  dentro de main(), chamda do método `print()` <br>
	```r1.print();``` <br>
	o objeto `r1` chama o seu método `print()`
1. execução do método `print()`
``` java
	void print () {
	System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
		this.w, this.h, this.x, this.y);
	}
```
- **acesso ao campo w:** <br>
```this.w``` <br>
significa que a JVM lê o valor do campo `w` do objeto `r1` (valor 10; item 2)

---

## Ao término, a seguinte mensagem foi impressa:
`Retangulo de tamanho (10,10) na posicao (1,1).`
