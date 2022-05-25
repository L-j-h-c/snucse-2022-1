#include <iostream>
#include "Point.h"

#ifndef GRID_H
#define GRID_H

//TODO Prob1.4
class Grid {
    int **grid;
    int row, column;

public:
    Grid(int r, int c);
    void initialize_with_zeros();

    int getRow() const;

    int getColumn() const;

    int getAt(int r, int c) const;

    void setAt(int r, int c, int v);

    void printGrid();

    Grid(Grid const &g);

    ~Grid();
    void mark_point(Point p);
    static int count;
};

#endif
