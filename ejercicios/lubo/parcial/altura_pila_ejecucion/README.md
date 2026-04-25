[M] -> 1 + max(altura([F]), altura([T]))
    [F] -> 1 + max(altura([B]), altura([H]))
        [B] -> 1 + max(altura([null]), altura([null])) = 1
        [H] -> 1 + max(altura([L]), altura([null])) = 1
            [L] -> 1 + max(altura([null]), altura([null])) = 1
    [T] -> 1 + max(altura([W]), altura([null]))
        [W] -> 1 + max(altura([null]), altura([null])) = 1
    
Entonces
    [W] = 1
Lo que hace que [T] = 1 + max(1, 0) = 2

Aqui ya tenemos el lado derecho

Ahora
    [L] = 1

Lo que hace que [H] = 1 + max(1, 0) = 2
y ademas [B] = 1

Lo que hace que [F] = 1 + max([B], [H]) = 1 + max(1, 2) = 3

Finalmente
    [M] = 1 + max([F], [T]) = 1 + max(3, 2) = 4

Por lo tanto, la altura del árbol es 4.