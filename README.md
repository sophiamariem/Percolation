# Write a program to estimate the value of the percolation threshold via Monte Carlo simulation

## Percolation

Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.

## The model

We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)

## The problem

In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates. The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (left) and 100-by-100 random grid (right).

## Corner cases  
By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site: Throw an IllegalArgumentException if any argument to open(), isOpen(), or isFull() is outside its prescribed range. Throw an IllegalArgumentException in the constructor if n ≤ 0.

## Performance requirements
The constructor should take time proportional to n2; all methods should take constant time plus a constant number of calls to the union–find methods union(), find(), connected(), and count().

## Monte Carlo simulation

To estimate the percolation threshold, consider the following computational experiment:

- Initialize all sites to be blocked.

    - Repeat the following until the system percolates:

    - Choose a site uniformly at random among all blocked sites.

- Open the site.

- The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.

***
***
Provide Percolation.java and PercolationStats.java implementations.
***

Full specification found here: 

https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php

See also: 

https://lift.cs.princeton.edu/java/linux/ for libs and steps to install

***

### Example Usage

javac-algs4 PercolationStats.java

java-algs4 PercolationStats 200 100

***

### Reporting

spotbugs Percolation.class

pmd Percolation.java

checkstyle -coursera Percolation.java
