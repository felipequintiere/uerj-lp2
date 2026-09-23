#include <stdio.h>
#include <stdlib.h>
// structs aninhadas (extends)
// ponteiros para funções (overrides)

// ----------------------------------------

typedef struct {
	int r;
	int g;
	int b;
} Color;

// ----------------------------------------

typedef struct Figure {
	int x;
	int y;
	Color fillColor;
	Color borderColor;

	// como estamos tentando reescrever o método abstrato
	// é útil ter um ponteiro para uma função, pois podemos
	// atribuir diferentes funções a ele
	void (* print) (struct Figure*);
	void (* change_color) (struct Figure*);
		// ponteiro para uma função que
		// 	tem um parâmero do tipo `struct Figure*`
		// 	não retorna valores (procedimento)
} Figure;
// NOTA: ao usar typedef, apenas é necessário determinar a struct
// tag se a mesma tiver um membro do (struct tag)||*(struct tag)

// ----------------------------------------

typedef struct {
	Figure super;

	int width;
	int height;
} Rect;

void rect_print(Rect* this) {
	Figure* sup = (Figure*) this;
	printf("tipo: retângulo\n"
	       "posição: (%d ; %d)\n"
	       "largura/altura: (%d ; %d)\n"
	       "cor de preenchimento: (%d ; %d ; %d)\n"
	       "cor de contorno: (%d ; %d ; %d)\n\n",
	       sup->x, sup->y, this->width, this->height,
	       sup->fillColor.r, sup->fillColor.g, sup->fillColor.g,
	       sup->borderColor.r, sup->borderColor.g, sup->borderColor.g);
}
void rect_change_color(Rect* this) {
	Figure* sup = (Figure*) this;
	printf("Selecione a cor de preenchimento [0-255][0-255][0-255]: ");
	scanf("%d%d%d",
		&sup->fillColor.r, &sup->fillColor.g, &sup->fillColor.b);

	printf("Selecione a cor de contorno [0-255][0-255][0-255]: ");
	scanf("%d%d%d",
		&sup->borderColor.r, &sup->fillColor.g, &sup->fillColor.b);
}


// construtor do retângulo 
Rect* rect_new(int x, int y, int width, int height) {
	Rect* this = malloc(sizeof(Rect));
	Figure* sup = (Figure*) this;
	sup->print = (void (*)(struct Figure *)) rect_print;
	sup->change_color = (void (*)(struct Figure *)) rect_change_color;
/*
main.c:56:20: error: assignment to ‘void (*)(struct Figure *)’ from incompatible pointer type ‘void (*)(Rect *)’ [-Wincompatible-pointer-types]
   56 |         sup->print = rect_print;
      |                    ^
*/
	sup->x = x;
	sup->y = y;
	this->width = width;
	this->height = height;

	return this;
}

// ----------------------------------------

typedef struct {
	Figure super;

	int width;
	int height;
} Ellipse;

void ellipse_print(Ellipse* this) {
	Figure* sup = (Figure*) this;
	printf("tipo: elipse\n"
	       "posição: (%d ; %d)\n"
	       "eixo horizontal/vertical: (%d ; %d)\n"
	       "cor de preenchimento: (%d ; %d ; %d)\n"
	       "cor de contorno: (%d ; %d ; %d)\n\n",
	       sup->x, sup->y, this->width, this->height,
	       sup->fillColor.r, sup->fillColor.g, sup->fillColor.g,
	       sup->borderColor.r, sup->borderColor.g, sup->borderColor.g);
}
void ellipse_change_color(Rect* this) {
	Figure* sup = (Figure*) this;
	printf("Selecione a cor de preenchimento [0-255][0-255][0-255]: ");
	scanf("%d%d%d",
		&sup->fillColor.r, &sup->fillColor.g, &sup->fillColor.b);

	printf("Selecione a cor de contorno [0-255][0-255][0-255]: ");
	scanf("%d%d%d",
		&sup->borderColor.r, &sup->borderColor.g, &sup->borderColor.b);
}

// construtor da elipse
Ellipse* ellipse_new(int x, int y, int width, int height) {
	Ellipse* this = malloc(sizeof(Ellipse));
	Figure* sup = (Figure*) this;
	sup->print = (void (*)(struct Figure *)) ellipse_print;
	sup->change_color = (void (*)(struct Figure *)) ellipse_change_color;
/*
main.c:92:20: error: assignment to ‘void (*)(struct Figure *)’ from incompatible pointer type ‘void (*)(Ellipse *)’ [-Wincompatible-pointer-types]
   92 |         sup->print = ellipse_print;
      |                    ^
*/
	sup->x = x;
	sup->y = y;
	this->width = width;
	this->height = height;

	return this;
}

// ----------------------------------------

void main(void) {
#define N 4
	Figure* figs[N] = {
		(Figure*) rect_new(10,10, 50,50),
		(Figure*) ellipse_new(0,0, 3,10),
		(Figure*) ellipse_new(600,150, 99,15),
		(Figure*) ellipse_new(20,50, 100,20)
	};

	figs[1]->change_color(figs[1]);

	// print em cada objeto
	for (int i = 0; i < N; i++) {
		figs[i]->print(figs[i]);
	}

	for (int i = 0; i < N; i++) {
		free(figs[i]);
	}
#undef N
}
