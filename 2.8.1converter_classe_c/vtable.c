#include <stdio.h>
#include <stdlib.h>
// structs aninhadas (extends)
// ponteiros para funções (overrides)

#define PI (3.14159)

// ----------------------------------------

typedef struct {
	int r, g, b;
} Color;

// ----------------------------------------

struct Figure;
// aparentemente C distingue entre DECLARAR que um tipo existe
// e DEFINIR seu conteúdo
//
// `struct Figure;` - forward declaration / incomplete type declaration -
// permite que `struct Figure *` seja usado antes de a struct ser definida,
// pois não é necessário conhecer seus membros para declarar um ponteiro
// para ela

// jogar todos os ponteiros para função definidos em Figure para
// a struct Figure_vtable 
typedef struct {
	void (*print) (struct Figure *);
	double (*area) (struct Figure *);
	void (*change_color) (struct Figure *);
} Figure_vtable;

typedef struct Figure {
	int x;
	int y;
	Color fillColor;
	Color borderColor;

	// simplificar os ponteiros para função nessa struct usando
	// a Figure_vtable
	Figure_vtable *vtable;	
} Figure;

// ----------------------------------------

typedef struct {
	Figure super;

	int width;
	int height;
} Rect;

void rect_print(Rect *this) {
	Figure *sup = (Figure *) this;
	printf("tipo: retângulo\n"
	       "posição: (%d ; %d)\n"
	       "largura/altura: (%d ; %d)\n"
	       "cor de preenchimento: (%d ; %d ; %d)\n"
	       "cor de contorno: (%d ; %d ; %d)\n\n"
	       "área: (%f)\n\n",
	       sup->x, sup->y, this->width, this->height,
	       sup->fillColor.r, sup->fillColor.g, sup->fillColor.g,
	       sup->borderColor.r, sup->borderColor.g, sup->borderColor.g,
	       sup->vtable->area(sup)); // poderia ter passado a função diretamente
}
double rect_area(Rect *this) {
	//Figure* sup = (Figure*) this;
	return this->width * this->height;
}

void rect_change_color(Rect *this) {
	Figure* sup = (Figure*) this;
	printf("Selecione a cor de preenchimento [0-255][0-255][0-255]: ");
	scanf("%d%d%d",
		&sup->fillColor.r, &sup->fillColor.g, &sup->fillColor.b);

	printf("Selecione a cor de contorno [0-255][0-255][0-255]: ");
	scanf("%d%d%d",
		&sup->borderColor.r, &sup->fillColor.g, &sup->fillColor.b);
}

// criar uma tabela específica para o retângulo
// 	os demais retângulos irão compartilhar essa mesma tabela
Figure_vtable rect_vtable = {
	(void (*)(struct Figure *)) rect_print,
	(double (*)(struct Figure *)) rect_area,
	(void (*)(struct Figure *)) rect_change_color
};

// construtor do retângulo 
Rect *rect_new(int x, int y, int width, int height) {
	Rect *this = malloc(sizeof(Rect));
	Figure *sup = (Figure *) this;
	sup->x = x;
	sup->y = y;
	sup->vtable = &rect_vtable;
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

void ellipse_print(Ellipse *this) {
	Figure *sup = (Figure *) this;
	printf("tipo: elipse\n"
	       "posição: (%d ; %d)\n"
	       "eixo horizontal/vertical: (%d ; %d)\n"
	       "cor de preenchimento: (%d ; %d ; %d)\n"
	       "cor de contorno: (%d ; %d ; %d)\n"
	       "área: (%f)\n\n",
	       sup->x, sup->y,
	       this->width, this->height,
	       sup->fillColor.r, sup->fillColor.g, sup->fillColor.g,
	       sup->borderColor.r, sup->borderColor.g, sup->borderColor.g,
	       sup->vtable->area(sup)); // poderia ter passado a função diretamente
}
void ellipse_change_color(Rect *this) {
	Figure *sup = (Figure *) this;
	printf("Selecione a cor de preenchimento [0-255][0-255][0-255]: ");
	scanf("%d%d%d",
		&sup->fillColor.r, &sup->fillColor.g, &sup->fillColor.b);

	printf("Selecione a cor de contorno [0-255][0-255][0-255]: ");
	scanf("%d%d%d",
		&sup->borderColor.r, &sup->borderColor.g, &sup->borderColor.b);
}
double ellipse_area(Rect *this) {
	//Figure* sup = (Figure*) this;
	// área: semi eixo maior * semi eixo menor * PI
	return (this->width / 2.0) * (this->height / 2.0) * PI;
}

// criar uma tabela específica para a elipse
// 	os demais elipses irão compartilhar essa mesma tabela
Figure_vtable ellipse_vtable = {
	(void (*)(struct Figure *)) ellipse_print,
	(double (*)(struct Figure *)) ellipse_area,
	(void (*)(struct Figure *)) ellipse_change_color
};

// construtor da elipse
Ellipse *ellipse_new(int x, int y, int width, int height) {
	Ellipse *this = malloc(sizeof(Ellipse));
	Figure *sup = (Figure *) this;
	sup->x = x;
	sup->y = y;
	sup->vtable = &ellipse_vtable;
	this->width = width;
	this->height = height;

	return this;
}

// ----------------------------------------

void main(void) {
#define N 4
	Figure *figs[N] = {
		(Figure*) rect_new(10,10, 50,50),
		(Figure*) ellipse_new(0,0, 3,10),
		(Figure*) ellipse_new(600,150, 99,15),
		(Figure*) ellipse_new(20,50, 100,20)
	};

	figs[1]->vtable->change_color(figs[1]);

	// print em cada objeto
	for (int i = 0; i < N; i++) {
		figs[i]->vtable->print(figs[i]);
	}

	for (int i = 0; i < N; i++) {
		free(figs[i]);
	}
#undef N
}
