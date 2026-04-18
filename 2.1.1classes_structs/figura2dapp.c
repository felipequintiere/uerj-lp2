/*
 * Considere as 2 figuras mais complexas da sua resposta à pergunta 3.1 do módulo 1.
 *
 * 1. implemente uma struct em C para cada uma delas e uma classe em java para a outra
 * 2. implemente uma função em C e um método em Java "print" para elas
 *
 * As informações contidas na struct e class precisam ser suficientes para representar a figura de forma completa. Não é necessário realmente desenhar as figuras na tela, mas somente com essas informações teria que ser possível desenhá-las de forma não ambígua
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


void preenche(Figura *);
void print(Figura *);


int main(void)
{
	Figura *figura = (Figura*) malloc(sizeof(Figura));
	preenche(figura);
	print(figura);

	return (EXIT_SUCCESS);
}

void preenche(Figura *figura)
{
#define PRFSCF(fmt,specifier,item) \
	( printf(fmt) , scanf(specifier, &(item) ) )
	
	PRFSCF("posição x: ","%f",figura->x);
	PRFSCF("posição y: ","%f",figura->y);
	PRFSCF(
	"\ntipos disponíveis\n"
	"    ESTRELA: 0\n"
	"    ELIPSE:  1\n"
	"tipo: "
	, "%d", figura->tipo);

	if (figura->tipo == ESTRELA)
	{
		PRFSCF("\nn° de vértices x: ","%d",figura->dados.estrela.vertices);
		PRFSCF("raio externo: ","%f",figura->dados.estrela.raio_externo);
		PRFSCF("raio interno: ","%f",figura->dados.estrela.raio_interno);
	}
	else if (figura->tipo == ELIPSE)
	{
		PRFSCF("\nsemi-eixo x: ","%f",figura->dados.elipse.eixo_x);
		PRFSCF("semi-eixo y: ","%f",figura->dados.elipse.eixo_y);
	}
	else
	{
		printf("TIPO INVÁLIDO!\n");
		exit(EXIT_FAILURE);
	}
#undef PRFSCF
}

void print(Figura *figura)
{
	printf("\nDADOS ARMAZENADOS:\n");
	printf("posição x: %f\n",figura->x);
	printf("posição y: %f\n",figura->y);
	if (figura->tipo == ESTRELA)
	{
		printf("n° de vértices: %d\n",figura->dados.estrela.vertices);
		printf("raio externo: %f\n",figura->dados.estrela.raio_externo);
		printf("raio interno: %f\n",figura->dados.estrela.raio_interno);
	}
	else if (figura->tipo == ELIPSE)
	{
		printf("semi-eixo x: %f\n",figura->dados.elipse.eixo_x);
		printf("semi-eixo y: %f\n",figura->dados.elipse.eixo_y);
	}
}
