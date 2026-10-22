#include <stdio.h>
#include <stdlib.h>
#include "rect.h"

struct Rect {
	int x;
	int y;
	int width;
	int height;
};

Rect * rect_new(void) {
	Rect *this = (Rect *) malloc(sizeof(Rect));

	this->x = 0;
	this->y = 0;
	this->width = 40;
	this->height = 40;

	return this;
}
void rect_drag(Rect *this, int dx, int dy) {
	this->x += dx;
	this->y += dy;
}

void rect_print(Rect *this) {
	printf(
		"Rect (x: %d ; y: %d) (width: %d ; height: %d)\n",
		this->x,
		this->y,
		this->width,
		this->height
	);
}

void rect_free(Rect *this) {
	free(this);
}

