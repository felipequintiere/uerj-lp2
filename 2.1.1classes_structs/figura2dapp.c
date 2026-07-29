/*
 * Considere as 2 figuras mais complexas da sua resposta à pergunta 3.1
 *  do módulo 1.
 *
 * 1. implemente uma struct em C para cada uma delas e uma classe em
 * java para a outra
 * 2. implemente uma função em C e um método em Java "print" para elas
 *
 * As informações contidas na struct e class precisam ser suficientes
 * para representar a figura de forma completa. Não é necessário
 * realmente desenhar as figuras na tela, mas somente com essas
 * informações teria que ser possível desenhá-las de forma não ambígua
*/
#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int vertices;
	float raio_externo;
	float raio_interno;
} Estrela;

typedef struct {
	float eixo_x;  // semi-eixo horizontal
	float eixo_y;  // semi-eixo vertical
} Elipse;

typedef struct {
	float x;
	float y;
	enum {
		ESTRELA = 0,
		ELIPSE
	} tipo ;
	union {
		Estrela estrela;
		Elipse elipse;
	} dados;
} Figura;


void print_estrela(Estrela *estrela);
void print_elipse(Elipse *elipse);
void print(Figura *figura);

int main(void)
{
	Figura *figura = (Figura*) malloc(sizeof(Figura));

	figura->x = 10.f;
	figura->y = 10.f;
	figura->tipo = ELIPSE;
	figura->dados.elipse.eixo_x = 20.0f;
	figura->dados.elipse.eixo_y = 30.0f;

	print(figura);

	free(figura);
	return (EXIT_SUCCESS);
}

void print_estrela(Estrela *estrela) {
	printf("tipo: estrela\n"
		"n° de vértices: %d\n"
		"raio externo: %f\n"
		"raio interno: %f\n",
		estrela->vertices,
		estrela->raio_externo,
		estrela->raio_interno);
}

void print_elipse(Elipse *elipse) {
	printf("tipo: elipse\n"
		"semi-eixo x: %f\n"
		"semi-eixo y: %f\n",
		elipse->eixo_x, elipse->eixo_y);
}

void print(Figura *figura) {
	printf("\nDADOS ARMAZENADOS:\n"
		"posição x: %f\n"
		"posição y: %f\n",
		figura->x, figura->y);

	switch (figura->tipo) {
	case ESTRELA:
		print_estrela(&figura->dados.estrela);
		break;
	case ELIPSE:
		print_elipse(&figura->dados.elipse);
		break;
	default:
		printf("TIPO INVÁLIDO!\n");
		exit(EXIT_FAILURE);
	}
}
